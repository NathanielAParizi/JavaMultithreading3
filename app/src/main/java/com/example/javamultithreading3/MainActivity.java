package com.example.javamultithreading3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Example of a visibility problem. (This actually worked on my machine.)

    private static int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Consumer().start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Producer().start();
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {

            int localVal = -1;
            while (true) {
                if (localVal != mCount) {
                    Log.d("TAG", "Consumer detected food available " + mCount);
                    localVal = mCount;
                }
                if (mCount >= 5) {
                    break;
                }
            }
            Log.d("TAG", "Consumer terminating. Count:" + mCount);

        }
    }

    public static class Producer extends Thread {

        @Override
        public void run() {

            int localVal = mCount;
            while (mCount < 5) {

                localVal++;
                Log.d("TAG", "Producer makes 1 unit of the count: " + mCount);
                mCount = localVal;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }

            }
            Log.d("TAG", "Producer is terminating");


        }
    }
}

