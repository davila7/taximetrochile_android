package com.davila.taximetrochile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private EditText to;
    private EditText from;
    private SeekBar seekBar;
    private TextView feeShow;
    private int progress;
    private MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        initializeVariables();
        feeShow.setText(seekBar.getProgress() + "/" + seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                feeShow.setText(progress + "/" + seekBar.getMax());
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        dbHandler = new MyDBHandler(this, null, null, 1);
    }


    // A private method to help us initialize our variables.
    private void initializeVariables() {
        to = (EditText) findViewById(R.id.to);
        from = (EditText) findViewById(R.id.from);
        seekBar = (SeekBar) findViewById(R.id.fee);
        feeShow = (TextView) findViewById(R.id.feeshow);
    }



    public void sendData(View v){
        Toast.makeText(getApplicationContext(), "Guardando Datos.",
                Toast.LENGTH_SHORT).show();
        Record record = new Record(to.getText().toString(), from.getText().toString(), progress);
        dbHandler.addRecord(record);
    }
}
