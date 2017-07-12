package com.tamalsamui.bars;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tamalsamui.library.OnSeekBarChangeListener;
import com.tamalsamui.library.VerticalProgressBar;
import com.tamalsamui.library.VerticalSeekBar;

public class MainActivity extends AppCompatActivity {

    VerticalProgressBar verticalProgressBar;
    VerticalSeekBar verticalSeekBar;
    TextView tvSeekBarProgress;
    EditText enterProgress;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verticalProgressBar = (VerticalProgressBar)findViewById(R.id.verticalProgressBar);
        verticalSeekBar = (VerticalSeekBar)findViewById(R.id.verticalSeekBar);
        tvSeekBarProgress = (TextView) findViewById(R.id.tvSeekBarProgress);
        button = (Button) findViewById(R.id.button);

        manageSeekBar();
        manageProgressBar();
    }

    private void manageSeekBar(){
        verticalSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(double progressInPercent) {
                tvSeekBarProgress.setText(String.format("%.2f", progressInPercent) + "%");
            }

            @Override
            public void onStopTrackingTouch(double progressInPercent) {
                tvSeekBarProgress.setText(String.format("%.2f", progressInPercent) + "%");
            }

            @Override
            public void onProgressChange(double progressInPercent) {
                tvSeekBarProgress.setText(String.format("%.2f", progressInPercent) + "%");
            }
        });

        verticalSeekBar.setProgressBarBackgroundColor(Color.DKGRAY);
        verticalSeekBar.setProgressBarColor(Color.BLUE);
        verticalSeekBar.setThumbColor(getResources().getColor(R.color.colorAccent));
    }

    private void manageProgressBar(){
        enterProgress = (EditText)findViewById(R.id.enterProgress);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(enterProgress.getText().toString().trim())) {
                    verticalProgressBar.setProgress(Double.parseDouble(enterProgress.getText().toString()));
                }
            }
        });
    }
}
