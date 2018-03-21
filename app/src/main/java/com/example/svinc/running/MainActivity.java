package com.example.svinc.running;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;
import android.os.Handler;


public class MainActivity  extends AppCompatActivity implements SensorEventListener, StepListener, GestureDetector.OnGestureListener {
    private TextView stepIndicator;
    private TextView timeTracker;
    private Button runStart;
    private Button runStop;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "";
    private int numSteps;
    private int seconds = 0;
    private boolean running;
    private GestureDetectorCompat detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
        stepIndicator = findViewById(R.id.steptext);
        runStart = findViewById(R.id.start);
        runStop = findViewById(R.id.stop);
        timeTracker = findViewById(R.id.timetracker);
        runTimer();


        detector = new GestureDetectorCompat(this, this);



        runStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                runStart.setVisibility(View.GONE);
                runStop.setVisibility(View.VISIBLE);
                runStop.setText("Stop");
                running = true;

                numSteps = 0;
                sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });


        runStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                runStop.setVisibility(View.GONE);
                runStart.setVisibility(View.VISIBLE);
                runStart.setText("GO!");
                stepClear();
                running = false;

                sensorManager.unregisterListener(MainActivity.this);

            }
        });



    }


    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.timetracker);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void stepClear() {
        stepIndicator.setText("0");
        numSteps = 0;
    }





    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        stepIndicator.setText(TEXT_NUM_STEPS + numSteps);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        startActivity(new Intent(MainActivity.this, Settings.class));
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}

