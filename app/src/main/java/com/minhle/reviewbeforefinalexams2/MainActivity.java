package com.minhle.reviewbeforefinalexams2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import model.FileManagement;
import model.Product;
import model.ProductFragment;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Button button;
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
        //send data from the activity -> Fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",productsList.get(position));

        //replace the linear layout with the fragment
        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);

        //reference the fragment manager
        FragmentManager fragmentManager = getFragmentManager();

        //begin the transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            fragmentTransaction.setReorderingAllowed(false);
//        }
        //fragmentTransaction.
            fragmentTransaction.detach(productFragment).attach(productFragment);
            fragmentTransaction.replace(R.id.mainUI, productFragment);
            //commit the transaction
            fragmentTransaction.commit();
            setTitle(productsList.get(position).getProductName());

            drawerLayout.closeDrawer(listViewProducts);

    }
}
