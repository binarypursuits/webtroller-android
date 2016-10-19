package com.binarypursuits.android.webtroller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerationHandler implements SensorEventListener {
    private float[] acceleration;

    public AccelerationHandler(Context context) {
        SensorManager manager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor accelerometer = manager.getSensorList(
                    Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // nothing to do here
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        acceleration = event.values;
    }

    public float[] getValues() {
        return acceleration;
    }

    public float x() {
        return acceleration[0];
    }

    public float y() {
        return acceleration[1];
    }

    public float z() {
        return acceleration[2];
    }

}
