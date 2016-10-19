package com.binarypursuits.android.webtroller;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

public class WebtrollerActivity extends AppCompatActivity {

    HomeFragment mHomeFragment;
    JoinFragment mJoinFragment;
    GameFragment mGameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtroller);

        mHomeFragment = new HomeFragment();
        mJoinFragment = new JoinFragment();
        mGameFragment = new GameFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, mHomeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void joinGame(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, mJoinFragment);
        fragmentTransaction.commit();
    }
}
