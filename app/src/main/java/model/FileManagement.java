package model;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileManagement {

    public static ArrayList<Product> readFile(Context context, String fileName){
        ArrayList<Product> listOfProducts = new ArrayList<>();

        AssetManager assetManager = context.getResources().getAssets();

        try {
            InputStreamReader isr = new InputStreamReader(assetManager.open(fileName));

            BufferedReader br = new BufferedReader(isr);

            String oneLine = br.readLine();

            while (oneLine != null){
                StringTokenizer st = new StringTokenizer(oneLine,",");
                String imageName = st.nextToken();
                String productName = st.nextToken();
                int cost = Integer.parseInt(st.nextToken());
                float fee = Float.parseFloat(st.nextToken());
                listOfProducts.add(new Product(imageName,productName,cost,fee));
                oneLine = br.readLine();
            }
            br.close();
            isr.close();

        } catch (IOException e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return listOfProducts;

    }
}
