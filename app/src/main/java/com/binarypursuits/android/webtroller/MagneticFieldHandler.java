package com.binarypursuits.android.webtroller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;

public class MagneticFieldHandler  implements SensorEventListener {
    private float[] magneticField;

    public MagneticFieldHandler(Context context) {
        SensorManager manager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).size() != 0) {
            Sensor accelerometer = manager.getSensorList(
                    Sensor.TYPE_MAGNETIC_FIELD).get(0);
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
        magneticField = event.values;
    }

    public float[] getValues() {
        return magneticField;
    }

    public float x() {
        return magneticField[0];
    }

    public float y() {
        return magneticField[1];
    }

    public float z() {
        return magneticField[2];
    }

    public String getJSONString() {

        return "";
    }
}
