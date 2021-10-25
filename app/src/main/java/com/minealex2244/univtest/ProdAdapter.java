package com.minealex2244.univtest;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProdAdapter extends RecyclerView.Adapter<ProdAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public Button messageButton;
        private final Context context;

        public ViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            nameTextView = (TextView) itemView.findViewById(R.id.product_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
            messageButton.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            final Intent intent;
            intent =  new Intent(context, ProdDetails.class);
            context.startActivity(intent);
        }
    }

    private List<Product> mProducts;

    public ProdAdapter(List<Product> products) {
        mProducts = products;
    }

    @Override
    public ProdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View productView = inflater.inflate(R.layout.item_product, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(productView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ProdAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Product contact = mProducts.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(contact.getName());
        Button button = holder.messageButton;
        button.setText(contact.getStock() > 0 ? "Buy" : "Out of stock");
        button.setEnabled(contact.getStock() > 0);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mProducts.size();
    }

}
