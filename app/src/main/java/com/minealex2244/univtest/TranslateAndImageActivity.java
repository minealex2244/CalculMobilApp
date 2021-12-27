package com.minealex2244.univtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TranslateAndImageActivity extends AppCompatActivity {
    TextView txtView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        button = findViewById(R.id.button);
        txtView = findViewById(R.id.textView);
        button.setOnClickListener(v -> new AsyncCaller().execute());
    }

    private class AsyncCaller extends AsyncTask<Void, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
        }
        @Override
        protected String doInBackground(Void... params) {
            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            Log.d("###","Calling the translator API ...");
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType,
                    "text=The%20POST%20method%20has%20several%20advantages%20over%20GET%3A%20it%20is%20more%20secure%20because%20most%20of%20the%20request%20is%20 hidden%20from%20the%20user%3B%20Suitable%20for%20big%20data%20operations.&tl=ro&sl=en");
                    Request request = new Request.Builder()
                            .url("https://google-translate20.p.rapidapi.com/translate")
                            .post(body)
                            .addHeader("content-type", "application/x-www-form-urlencoded")
                            .addHeader("x-rapidapi-host", "google-translate20.p.rapidapi.com")
                            .addHeader("x-rapidapi-key", "cf26dd3c73mshdd5e0ce40dc73f9p173d45jsn8d4ef6944414")
                            .build();
            Response response;
            String r = null;
            try {
                response = client.newCall(request).execute();
                r = response.body().string();

                JSONObject res = new JSONObject(r);
                String data = res.getString("data");
                JSONObject dataRes = new JSONObject(data);
                String data2 = dataRes.getString("translation");
                Log.i("Data", "Post data: " + data2 );
                r = data2;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return r;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //this method will be running on UI thread
            txtView.setText(result);
        }
    }
}
