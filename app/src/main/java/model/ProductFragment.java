package model;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhle.reviewbeforefinalexams2.R;

public class ProductFragment extends android.app.Fragment {

    //TextView textViewProductName, textViewCost,textViewFees,textViewTotal;
    //ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.productfragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Product product = (Product)getArguments().getSerializable("product");
        TextView textViewProductName = getActivity().findViewById(R.id.textViewProductName);
        TextView textViewCost = getActivity().findViewById(R.id.textViewCost);
        TextView textViewFees = getActivity().findViewById(R.id.textViewFees);
        TextView textViewTotal = getActivity().findViewById(R.id.textViewTotal);
        ImageView imageView = getActivity().findViewById(R.id.imageView);

        textViewProductName.setText(product.getProductName());

        textViewCost.setText(String.valueOf(product.getCost()));
        textViewFees.setText(String.valueOf(String.format("%.0f",product.getFees())));
        textViewTotal.setText(String.valueOf(String.format("%.0f",product.getCost()+product.getFees())));
        int resourceID =  getResources().getIdentifier("drawable/"+product.getPictureName(),null,getActivity().getPackageName());
        imageView.setImageResource(resourceID);
    }
}
