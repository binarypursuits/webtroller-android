package com.binarypursuits.android.webtroller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by brian on 6/19/2016.
 */
public class OrientationSensor {

    public float[] orientation;

    private AccelerationHandler accelerometer;
    private MagneticFieldHandler magneticField;

    public OrientationSensor(Context context) {
        accelerometer = new AccelerationHandler(context);
        magneticField = new MagneticFieldHandler(context);
    }

    public void calculateOrientation() {
        /**
         * Listing 12-6: Finding the current orientation using the accelerometer and magnetometer
         */
        float[] values = new float[3];
        float[] R = new float[9];
        SensorManager.getRotationMatrix(R, null,
                accelerometer.getValues(),
                magneticField.getValues());
        SensorManager.getOrientation(R, values);

        // Convert from radians to degrees if preferred.
        orientation[0] = (float) Math.toDegrees(values[0]); // Azimuth
        orientation[1] = (float) Math.toDegrees(values[1]); // Pitch
        orientation[2] = (float) Math.toDegrees(values[2]); // Roll
    }



}
