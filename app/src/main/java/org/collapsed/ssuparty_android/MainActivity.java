package org.collapsed.ssuparty_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArray = new ArrayList<>();

        mArray.add("Test");

        Log.d("TAG", mArray.get(0));
    }
}
