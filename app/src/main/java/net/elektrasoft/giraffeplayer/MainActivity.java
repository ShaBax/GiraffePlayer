package net.elektrasoft.giraffeplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import tcking.github.com.giraffeplayer2.GiraffePlayer;
import tcking.github.com.giraffeplayer2.VideoInfo;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    int PERMISSION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device 
                // Your Permission granted already .Do next code
                //Uri fileUri = Uri.fromFile(new File("sdcard/video.mp4"));
                Uri fileUri = Uri.fromFile(new File("sdcard/video2.webm"));
                GiraffePlayer.play(this, new VideoInfo(fileUri));
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {

            // Code for Below 23 API Oriented Device 
            // Do next code

            VideoPlayer();
        }




    }

    void VideoPlayer()
    {
        Uri fileUri = Uri.fromFile(new File("sdcard/video.mp4"));
        VideoInfo videoInfo = new VideoInfo(fileUri)
                .setTitle("MP4 video") //config title
                //.setAspectRatio(aspectRatio) //aspectRatio
                .setShowTopBar(true) //show mediacontroller top bar
                .setPortraitWhenFullScreen(true);//portrait when full screen


        GiraffePlayer.play(this, videoInfo);


    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

}
