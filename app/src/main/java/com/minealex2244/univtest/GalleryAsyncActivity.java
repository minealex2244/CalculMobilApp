package com.minealex2244.univtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.InputStream;
import java.net.URL;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ImageView;

public class GalleryAsyncActivity extends AppCompatActivity {
    private String url = "https://nucbm.github.io/joker.png";
    ImageView img;
    Button downloadBtn;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_gallery);
        img = findViewById(R.id.imageView1);
        downloadBtn = findViewById(R.id.buttonAsync);
        downloadBtn.setOnClickListener(arg0 -> {
            new Downloader().execute(url);
        });
    }

    private class Downloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //CREATE PD,SET PROPERTIES
            pd = new ProgressDialog(GalleryAsyncActivity.this);
            pd.setTitle("Image Downloader");
            pd.setMessage("Downloading...");
            pd.setIndeterminate(false);
            pd.show();
        }

        protected Bitmap doInBackground(String... url) {
            String myurl = url[0];
            Bitmap bm = null;
            InputStream is = null;
            try {
                is = new URL(myurl).openStream();
                //DECODE
                bm = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            // SET TO IMG
            img.setImageBitmap(result);
            // DISMISS
            pd.dismiss();
        }
    }
}
