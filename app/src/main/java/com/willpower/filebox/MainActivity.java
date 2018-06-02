package com.willpower.filebox;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.willpower.filebox.ablum.FolderActivity;
import com.willpower.filebox.widget.BaseButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    BaseButton album,words,music,video;
    FloatingActionButton camera;

    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};

    int REQUEST_CODE_PERMISSION = 1101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        album = findViewById(R.id.btn_album);
        words = findViewById(R.id.btn_file);
        camera = findViewById(R.id.btn_camera);
        music = findViewById(R.id.btn_music);
        video = findViewById(R.id.btn_video);
        album.setOnClickListener(this);
        words.setOnClickListener(this);
        camera.setOnClickListener(this);
        music.setOnClickListener(this);
        video.setOnClickListener(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,permission[0]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,permission,REQUEST_CODE_PERMISSION);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_album:
                startActivity(new Intent(this,FolderActivity.class).putExtra("type",0));
                break;
            case R.id.btn_file:
                break;
            case R.id.btn_camera:
                break;
            case R.id.btn_music:
                startActivity(new Intent(this,FolderActivity.class).putExtra("type",1));
                break;
            case R.id.btn_video:
                startActivity(new Intent(this,FolderActivity.class).putExtra("type",2));
                break;
        }
    }
}
