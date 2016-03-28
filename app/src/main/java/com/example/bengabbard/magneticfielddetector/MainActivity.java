package com.example.bengabbard.magneticfielddetector;


        import android.app.Activity;
        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mMagField;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD		);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float DetectorValue;
        DetectorValue = event.values[0];
        // Do something with this sensor data.
        Log.i("Info", "  "+ DetectorValue+ " μT Microteslas\t");

        TextView textViewpressure = (TextView) findViewById(R.id.textViewinfo);
        if( DetectorValue <20) {textViewpressure.setText("No Magnetic Field present:     "+DetectorValue+" µT (Microteslas"   );}
        if( DetectorValue >=35){textViewpressure.setText("Magnetic Field May Be present: "+DetectorValue+" µT (Microteslas"    );}
        if( DetectorValue >40){textViewpressure.setText("Magnetic Field IS present:     "+DetectorValue+" µT (Microteslas)"    );}
        if( DetectorValue <-40){textViewpressure.setText("Magnetic Field IS present:     "+DetectorValue+" µT (Microteslas)"    );}

    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(this, mMagField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}