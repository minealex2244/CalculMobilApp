package com.minealex2244.univtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private final int REQUEST1 = 1;
    private final int REQUEST2 = 2;
    private String[] things = new String[5];
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        things[0] = "Ana are mere";
        things[1] = "Ana nu are mere";
        things[2] = "Alex are mere";
        things[3] = "Alex nu are mere";
        things[4] = "Stau prost cu ideile...";

        View.OnClickListener listener = v -> {
            /** Creating an intent object to call the activity ActivityResult */
            Intent i = new Intent(getApplicationContext(), SecondActivity.class);

            /** Starting the activity ActivityResultDemo */
            startActivityForResult(i, REQUEST1);
        };

        /** Getting a reference to the "GetResult" button from the layout activity_main */
        Button btn = (Button) findViewById(R.id.btn_get_result);

        /** Setting click listener for the button GetResult */
        btn.setOnClickListener(listener);

        // RecyclerView code
        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvProducts);

        // Initialize contacts
        products = Product.createProductsList(23);
        // Create adapter passing in the sample user data
        ProdAdapter adapter = new ProdAdapter(products);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * requestCode : an integer code passed to the called activity set by caller activity
         * resultCode : an integer code returned from the called activity
         * data : an intent containing data set by the called activity
         */
        if(requestCode == REQUEST1 && resultCode == RESULT_OK){
            Toast.makeText(getBaseContext(),
                    data.getStringExtra("data"),
                    Toast.LENGTH_SHORT).show();
            Button btn = findViewById(R.id.btn_get_result);
            btn.setText(data.getStringExtra("data"));
        }

        if(requestCode == REQUEST2 && resultCode == RESULT_OK){
            EditText edt = findViewById(R.id.searchField);
            for (int i = 0; i < things.length; i++)
                if (things[i].contains(edt.getText())) {
                    things[i] = data.getStringExtra("data");
                    Log.d("MA2", data.getStringExtra("data"));
                }
        }
    }

    public void searchWord(View v) {
        EditText edt = findViewById(R.id.searchField);
        for (int i = 0; i < things.length; i++)
            if (things[i].contains(edt.getText())) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("data", things[i]);
                startActivityForResult(intent, REQUEST2);
                break;
            }
    }
}