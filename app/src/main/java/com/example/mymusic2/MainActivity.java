package com.example.mymusic2;


import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity  {

    private ViewPager viewPager;

    public static control_song control_song;
    public static play_song play_song;
    public static list_song list_song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //Quyền truy cập bộ nhớ
        CheckPermission();

        control_song = new control_song();
        play_song = new play_song();
        list_song = new list_song();


        getSupportFragmentManager().beginTransaction().replace(R.id.playSong,control_song).commit();



        viewPager = (ViewPager) findViewById(R.id.viewPager);
        FragmentPage page = new FragmentPage(getSupportFragmentManager());
        page.AddFragment(new play_song());
        page.AddFragment(new list_song());
        viewPager.setAdapter(page);


    }















    public void CheckPermission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        }else{
            Log.i("HELLO","Nothing");
        }
    }


//    @Override
//    public void onInputAsent(int sequence,String link) {
//            control_song.upDateData(sequence,link);
//    }
//    @Override
//    public void onInputBsent(int sequence) {
//            list_song.upDateData(sequence);
//    }




}
