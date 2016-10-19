package com.binarypursuits.android.webtroller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorHandler implements SensorEventListener {
    public float heading;
    public float pitch;
    public float roll;

    public float accelX;
    public float accelY;
    public float accelZ;

    public float[] mGravity;
    public float[] mMagnetic;
    public float[] mLinear;

    public SensorHandler(Context context) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        Sensor mAccelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        Sensor mLinear = manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        manager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        Sensor mField = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        manager.registerListener(this, mField, SensorManager.SENSOR_DELAY_GAME);
    }

    private void updateDirection() {
        float[] temp = new float[9];
        float[] R = new float[9];

        SensorManager.getRotationMatrix(temp, null, mGravity, mMagnetic);
        SensorManager.remapCoordinateSystem(temp, SensorManager.AXIS_X, SensorManager.AXIS_MINUS_Y, R);

        float[] values = new float[3];
        SensorManager.getOrientation(R, values);

        for (int i = 0; i < values.length; i++) {
            Double degrees = (values[i] * 180) / Math.PI;
            values[i] = degrees.floatValue();
        }

        heading = values[0];
        pitch = values[1];
        roll = values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accelX = event.values[0];
        accelY = event.values[1];
        accelZ = event.values[2];

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                mGravity = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetic = event.values.clone();
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                mLinear = event.values.clone();
                break;
            default:
                return;
        }

        if (mGravity != null && mMagnetic != null) {
            updateDirection();
        }
    }
}