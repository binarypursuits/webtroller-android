package com.binarypursuits.android.webtroller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

public class DataGramClient implements Runnable {
    public int port;
    public String address;
    private byte[] message;
    public float[] orientation;

    public DataGramClient(int _port, String _address) {
        port = _port;
        address = _address;
    }

    @Override
    public void run() {
        try {
            DatagramSocket dgs = new DatagramSocket(port);
            InetAddress ia = InetAddress.getByName(address);
            byte[] buffer = message;
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ia, port);
            dgs.send(packet);
        } catch (SocketException e) {
            Log.e("Udp:", "Socket Error:", e);
        } catch (IOException e) {
            Log.e("Udp Send:", "IO Error:", e);
        }
    }

    public void generateMessageJson(float[] values)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"orientation\":[");
        sb.append(Float.toString(values[0]));
        sb.append(",");
        sb.append(Float.toString(values[1]));
        sb.append(",");
        sb.append(Float.toString(values[2]));
        sb.append("]}");
        message = sb.toString().getBytes();
    }

}

