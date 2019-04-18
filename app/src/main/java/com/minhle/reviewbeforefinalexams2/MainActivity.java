package com.minhle.reviewbeforefinalexams2;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import model.FileManagement;
import model.Product;
import model.ProductFragment;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    ListView listViewProducts;
    ArrayList<Product> productsList;
    ArrayList<String> productNamesList;
    ArrayAdapter<String> productArrayAdapter;
    int currentPosition;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize(){
        listViewProducts = findViewById(R.id.listViewProduct);
        listViewProducts.setOnItemClickListener(this);
        drawerLayout = findViewById(R.id.drawner_layout);

        //Initialize productNamesList and productsList
        productNamesList = new ArrayList<String>();
        productsList = FileManagement.readFile(this,"products.txt");
        for (Product product: productsList){
            productNamesList.add(product.getProductName());
        }

        //Toast.makeText(this,productsList.get(0).toString(),Toast.LENGTH_LONG).show();
        productArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, productNamesList);
        listViewProducts.setAdapter(productArrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getFragmentManager().executePendingTransactions();

        //send data from the activity -> Fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",productsList.get(position));

        //replace the linear layout with the fragment
        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);

        //reference the fragment manager
        android.app.FragmentManager fragmentManager = getFragmentManager();

        //begin the transaction
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //replace the linear layout main_ui with the fragment
        fragmentTransaction.replace(R.id.mainUI,productFragment);
        //commit the transaction
        fragmentTransaction.commit();
        setTitle(productsList.get(position).getProductName());

        drawerLayout.closeDrawer(listViewProducts);

    }
}
