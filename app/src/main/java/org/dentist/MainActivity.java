package org.dentist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;

public class MainActivity extends AppCompatActivity implements DentitionUI.Handler {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DentitionUI dentition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dentition = (DentitionUI) findViewById(R.id.dentition);
        this.dentition.setHandler(this);

        SparseIntArray colors = new SparseIntArray();
        for (int i = 0; i < 39; i++) {
            colors.put(i+10, 1);

        }

        dentition.setColors(colors);
    }

    @Override
    public void onToothClicked(int i) {
        Log.d(TAG, "onToothClicked: " + i);
    }
}
