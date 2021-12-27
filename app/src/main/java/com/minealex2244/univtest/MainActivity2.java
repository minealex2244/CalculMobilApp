package com.minealex2244.univtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
        Button btn = findViewById(R.id.btn_get_result);

        /** Setting click listener for the button GetResult */
        btn.setOnClickListener(listener);

        // Gallery load button

        Button btnGal = findViewById(R.id.buttonGalleryThread);

        View.OnClickListener listenerGal = v -> {
            Intent i = new Intent(getApplicationContext(), GalleryActivity.class);
            startActivity(i);
        };

        btnGal.setOnClickListener(listenerGal);

        // Gallery Async button

        Button btnGalAsync = findViewById(R.id.btnGalAsync);

        View.OnClickListener listenerGalAsync = v -> {
            Intent i = new Intent(getApplicationContext(), GalleryAsyncActivity.class);
            startActivity(i);
        };

        btnGalAsync.setOnClickListener(listenerGalAsync);

        // Server button

        Button serverBtn = findViewById(R.id.serverBtn);

        View.OnClickListener listenerServerBtn = v -> {
            Intent i = new Intent(getApplicationContext(), ServerActivity.class);
            startActivity(i);
        };

        serverBtn.setOnClickListener(listenerServerBtn);

        // Client button

        Button clientBtn = findViewById(R.id.clientBtn);

        View.OnClickListener listenerClientBtn = v -> {
            Intent i = new Intent(getApplicationContext(), ClientActivity.class);
            startActivity(i);
        };

        clientBtn.setOnClickListener(listenerClientBtn);

        // Translate button

        Button trBtn = findViewById(R.id.trBtn);

        View.OnClickListener listenerTrBtn = v -> {
            Intent i = new Intent(getApplicationContext(), TranslateAndImageActivity.class);
            startActivity(i);
        };

        trBtn.setOnClickListener(listenerTrBtn);

        // Retrofit button

        Button rfBtn = findViewById(R.id.btnRetrofit);

        View.OnClickListener listenerRfBtn = v -> {
            Intent i = new Intent(getApplicationContext(), RetrofitActivity.class);
            startActivity(i);
        };

        rfBtn.setOnClickListener(listenerRfBtn);

        // Firebase button

        Button fbBtn = findViewById(R.id.btnFirebase);

        View.OnClickListener listenerFbBtn = v -> {
            Intent i = new Intent(getApplicationContext(), FirebaseActivity.class);
            startActivity(i);
        };

        fbBtn.setOnClickListener(listenerFbBtn);

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

        // Fib Service

        Button buttonFib = findViewById(R.id.buttonFib);

        buttonFib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MyService.class);
                intent.putExtra("message", "10");
                startService(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
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

    // Fib stuff

    public static final String FILTER_ACTION_KEY = "999";
    MyReceiver myReceiver;
    private void setReceiver() {
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FILTER_ACTION_KEY);
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        setReceiver();
        super.onStart();
    }

    @Override
    protected void onStop() {
        //unregisterReceiver(myReceiver);
        super.onStop();
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("broadcastMessage");
            Toast.makeText(getApplicationContext(), "The result was: " + message, Toast.LENGTH_SHORT).show();
        }
    }
}