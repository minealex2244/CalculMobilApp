package com.minealex2244.univtest;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
public class MyService extends IntentService {
    public MyService() {
        super("MyService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String message = intent.getStringExtra("message");
        intent.setAction(MainActivity2.FILTER_ACTION_KEY);
        //This wait can be for downloading, sending a request, decoding a file etc.
        SystemClock.sleep(2000);
        int fibNo = Integer.parseInt(message);
        String resultMessage = "After 2 seconds of processing... Here's the Fib term: " + fib(fibNo);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(intent.putExtra("broadcastMessage", resultMessage));
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Toast.makeText(getApplicationContext(), "Intent SERVICE CREATED", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Intent SERVICE DESTROYED", Toast.LENGTH_SHORT).show();
    }

    public int fib(int n)
    {
        if (n <= 1)
            return n;
        return fib(n-1) + fib(n-2);
    }
}