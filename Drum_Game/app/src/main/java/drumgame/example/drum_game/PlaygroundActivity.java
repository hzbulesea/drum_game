package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PlaygroundActivity extends AppCompatActivity {

    private ImageButton mButton01, mButton02;
    private SoundPool sp1, sp2;
    private int music1, music2;
    MediaPlayer mediaPlayer;

    ImageView image1, image2, image3, image4;
    int drum;

    TextView score;
    int scores = 0;

    private int screenWidth, screenHeight;

    private float image1X, image2X, image3X, image4X;

    private Handler handler = new Handler();
    private Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_playground);

        score = findViewById(R.id.scores);
        score.setText(String.valueOf(scores));
        mButton01 = (ImageButton) findViewById(R.id.left);
        mButton01.setOnTouchListener(leftButtonTouchListener);
        mButton02 = (ImageButton) findViewById(R.id.right);
        mButton02.setOnTouchListener(rightButtonTouchListener);
        sp1 = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        sp2 = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music1 = sp1.load(this, R.raw.s1, 1);
        music2 = sp2.load(this, R.raw.s2, 1);

        mediaPlayer = MediaPlayer.create(this, R.raw.m2);
        mediaPlayer.start();

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);

        image1.setX(200.0f);
        image2.setX(200.0f);
        image3.setX(200.0f);
        image4.setX(200.0f);
//        image1.setY(0.0f);

        image1X = screenWidth;
        image2X = screenWidth+1500;
        image3X = screenWidth+1000;
        image4X = screenWidth+500;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);


//        move();


    }

    private void changePos() {
        image1X -=10;
        if (image1.getX() < 200){
            image1X = screenWidth;
        }
        image1.setX(image1X);

        image2X -=10;
        if (image2.getX() < 200){
            image2X = screenWidth;
        }
        image2.setX(image2X);

        image3X -=10;
        if (image3.getX() < 200){
            image3X = screenWidth;
        }
        image3.setX(image3X);

        image4X -=10;
        if (image4.getX() < 200){
            image4X = screenWidth;
        }
        image4.setX(image4X);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            if (!mediaPlayer.isPlaying()) {
                Log.d("mediaPlayer", "not playing");
                mediaPlayer.start();
            }
        }
    }

    private View.OnTouchListener leftButtonTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    //press down image button
                    mButton01.setImageResource(R.mipmap.dumpling5_press);
                    sp1.play(music1, 1, 1, 0, 0, 1);
                    if (image1X < 240){
                        scores += 200;
                        score.setText(String.valueOf(scores));
                        Log.d("click1", "perfect");     // click baozi button in this is perfect
                    }                                              // if perfect, add some point to score
                    else if (image1X >= 240 && image1X < 270){
                        scores += 100;
                        score.setText(String.valueOf(scores));
                        Log.d("click1", "good");
                    }
                    else if (image1X >= 270 && image1X < 300){
                        scores += 50;
                        score.setText(String.valueOf(scores));
                        Log.d("click1", "cool");
                    }else{
                        Log.d("click1", "bad");
                    }
                    if (image2X < 240){
                        scores += 200;
                        score.setText(String.valueOf(scores));
                        Log.d("click2", "perfect");
                    }
                    else if (image2X >= 240 && image2X < 270){
                        scores += 100;
                        score.setText(String.valueOf(scores));
                        Log.d("click2", "good");
                    }
                    else if (image2X >= 270 && image2X < 300){
                        scores += 50;
                        score.setText(String.valueOf(scores));
                        Log.d("click2", "cool");
                    }else{
                        Log.d("click2", "bad");
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    //after Image button release
                    mButton01.setImageResource(R.mipmap.dumpling5);

                    break;
                }
                default:{
                    Log.d("button", "not click");
                    break;
                }
            }

            return false;
        }

    };

    private View.OnTouchListener rightButtonTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    //press down image button
                    mButton02.setImageResource(R.mipmap.dumpling6_press);
                    sp2.play(music2, 1, 1, 0, 0, 1);
                    if (image3X < 240){
                        scores += 200;
                        score.setText(String.valueOf(scores));
                        Log.d("click3", "perfect");
                    }
                    else if (image3X >= 240 && image3X < 270){
                        scores += 200;
                        score.setText(String.valueOf(scores));
                        Log.d("click3", "good");
                    }
                    else if (image3X >= 270 && image3X < 300){
                        scores += 200;
                        score.setText(String.valueOf(scores));
                        Log.d("click3", "cool");
                    }else{
                        Log.d("click3", "bad");
                    }
                    if (image4X < 240){
                        scores += 200;
                        score.setText(String.valueOf(scores));
                        Log.d("click4", "perfect");
                    }
                    else if (image4X >= 240 && image4X < 270){
                        scores += 200;
                        score.setText(String.valueOf(scores));
                        Log.d("click4", "good");
                    }
                    else if (image4X >= 270 && image4X < 300){
                        scores += 200;
                        score.setText(String.valueOf(scores));
                        Log.d("click4", "cool");
                    }else{
                        Log.d("click4", "bad");
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    //after Image button release
                    mButton02.setImageResource(R.mipmap.dumpling6);
                    break;
                }
                default:
                    break;
            }
            return false;
        }
    };

//
//    private void move() {
//        Log.d("screenWidth", ""+screenWidth);
//        Log.d("screenHeight", ""+screenHeight);
//        boolean f = true;
//        for (int i = 0; i < 3; i++) {
//            if (f == false) {
//                image1 = findViewById(R.id.imageView);
//                Animation img = new TranslateAnimation(Animation.ABSOLUTE, -6000, Animation.ABSOLUTE, Animation.ABSOLUTE);
//                img.setDuration(5000);
//                img.setFillAfter(true);
//                f = true;
//                image1.startAnimation(img);
//
//            } else {
//                image2 = findViewById(R.id.imageView3);
//                Animation img2 = new TranslateAnimation(Animation.ABSOLUTE, -4000, Animation.ABSOLUTE, Animation.ABSOLUTE);
//                img2.setDuration(5000);
//                img2.setFillAfter(true);
//                f = false;
//                image2.startAnimation(img2);
//            }
//        }
//        Log.d("click", "drum: "+drum);
//
//    }


}
