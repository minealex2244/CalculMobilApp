package com.minealex2244.univtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    class MyItem {
        int image;
        String name;

        public MyItem(int image, String name) {
            this.image = image;
            this.name = name;
        }
    }

    private MyItem myitems[] = {
            new MyItem(R.drawable.img9, "nine"),
            new MyItem(R.drawable.img8, "eight"),
            new MyItem(R.drawable.img7, "seven"),
            new MyItem(R.drawable.img6, "six"),
            new MyItem(R.drawable.img5, "five"),
            new MyItem(R.drawable.img4, "four"),
            new MyItem(R.drawable.img3, "three"),
            new MyItem(R.drawable.img2, "two"),
            new MyItem(R.drawable.img1, "one"),
            new MyItem(R.drawable.img0, "zero"),
            new MyItem(R.drawable.joker, "E N D"),
            new MyItem(R.drawable.imga, "Img A"),
            new MyItem(R.drawable.imgb, "Img B")
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);
        textView = findViewById(R.id.image_name);
        imageView = findViewById(R.id.item_picture);
        textView.setText(myitems[0].name);
        imageView.setImageResource(myitems[0].image);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (currentIndex == myitems.length) break;
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(myitems[currentIndex].name);
                            imageView.setImageResource(myitems[currentIndex].image);
                            currentIndex++;
                            //currentIndex %= myitems.length;
                        }
                    });
                }
            }
        }).start();
    }
}
