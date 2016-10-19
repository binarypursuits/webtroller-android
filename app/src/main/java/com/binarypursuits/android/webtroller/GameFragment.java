package com.binarypursuits.android.webtroller;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GameFragment extends Fragment {

    SensorHandler sensor;
    private UdpClient client;
    private Button button;

    private int port = 64321;
    private String address = "node.binarypursuits.com";

    private Handler mHandler = new Handler();

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        client = new UdpClient();
        client.address = address;
        client.port = port;

        sensor = new SensorHandler(this.getContext());

        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    public void startGame (View v) {
        mHandler.postDelayed(mUpdateTask, 100);
    }

    private Runnable mUpdateTask = new Runnable() {
        public void run() {
            client.Message = orientationJson();
            client.Send();
            mHandler.postDelayed(this,  100);
        }
    };

    private String orientationJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(Float.toString(sensor.heading));
        sb.append(",");
        sb.append(Float.toString(sensor.pitch));
        sb.append(",");
        sb.append(Float.toString(sensor.roll));
        sb.append("]");

        return sb.toString();
    }

    @Override
    public void onStart(){
        super.onStart();
        mHandler.removeMessages(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeMessages(0);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
