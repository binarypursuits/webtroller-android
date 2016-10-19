package com.binarypursuits.android.webtroller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

public class UdpClient
{
    private AsyncTask<Void, Void, Void> async_cient;
    public String address;
    public int port;
    public String Message;

    @SuppressLint("NewApi")
    public void Send() {
        async_cient = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                DatagramSocket ds = null;

                try {
                    ds = new DatagramSocket();
                    DatagramPacket dp;
                    dp = new DatagramPacket(Message.getBytes(), Message.length(), InetAddress.getByName(address), port);
                    ds.setBroadcast(true);
                    ds.send(dp);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (ds != null) {
                        ds.close();
                    }
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };

        if (Build.VERSION.SDK_INT >= 11) {
            async_cient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            async_cient.execute();
        }

    }
}