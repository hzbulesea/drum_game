package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PlaygroundActivity1 extends AppCompatActivity {

    private ImageButton mButton01, mButton02, back_btn;
    private SoundPool sp1, sp2;
    private int music1, music2;
    MediaPlayer mediaPlayer;

    ImageView image1, image2, image3, image4, bear;
    int drum;

    TextView score, show;

    int i = 0;

    private int screenWidth, screenHeight;

    private float image1X, image2X, image3X, image4X;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    DisplayMetrics displayMetrics;
    LinearLayout layout1,layout1_1,layout1_2,layout2;
    ConstraintLayout layout3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_playground1);

        MainActivity.scores = 0;
        MainActivity.perfects = 0;
        MainActivity.goods = 0;
        MainActivity.poors = 0;




        score = findViewById(R.id.scores);
        score.setText(String.valueOf(MainActivity.scores));
        mButton01 = (ImageButton) findViewById(R.id.left);
        mButton01.setOnTouchListener(leftButtonTouchListener);
        mButton02 = (ImageButton) findViewById(R.id.right);
        mButton02.setOnTouchListener(rightButtonTouchListener);
        sp1 = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        sp2 = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music1 = sp1.load(this, R.raw.s1, 1);
        music2 = sp2.load(this, R.raw.s2, 1);

        mediaPlayer = MediaPlayer.create(this, R.raw.m1);
//        mediaPlayer.start();
        mediaPlayer.setVolume(13,13);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                performOnEnd();
            }
        });


        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();




        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        displayMetrics = getResources().getDisplayMetrics();
        DeviceInfor deviceInfor = new DeviceInfor(displayMetrics);
        layout1 = findViewById(R.id.layout1);
        layout1_1 = findViewById(R.id.layout1_1);
        layout1_2 = findViewById(R.id.layout1_2);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);

        layout1.getLayoutParams().height = (int)(screenHeight*0.25);
        layout2.getLayoutParams().height = (int)(screenHeight*0.375);
        layout3.getLayoutParams().height = (int)(screenHeight*0.375);
        layout1_1.getLayoutParams().height = (int)(layout1.getLayoutParams().height*0.6);
        layout1_2.getLayoutParams().height = (int)(layout1.getLayoutParams().height*0.4);


        Log.d("layout1 height",""+screenWidth);

        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);

        image1.setX(300.0f);
        image2.setX(300.0f);
        image3.setX(300.0f);
        image4.setX(300.0f);
//        image1.setY(0.0f);

        image1X = screenWidth;
        image2X = screenWidth+800;
        image3X = screenWidth+300;
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


        bear = findViewById(R.id.imageView5);
//        move();

        show = findViewById(R.id.shows);



        back_btn = findViewById(R.id.back_btn);       // initial main button

        // main button onTouch Listener
        back_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    back_btn.setImageResource(R.mipmap.icon_back_press);

                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    back_btn.setImageResource(R.mipmap.icon_back);
                    Intent intent = new Intent(PlaygroundActivity1.this, ScoreActivity.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });
    }

    private void performOnEnd() {
        if (!mediaPlayer.isPlaying()){ //basically BACK was pressed from this activity
//            mediaPlayer.stop();
            Log.d("performOnEnd ", "mediaPlayer stop "+i);
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //https://www.youtube.com/watch?v=UxbJKNjQWD8
    private void changePos() {
        image1X -=10;
        if (image1.getX() < (bear.getWidth()-image1.getWidth())){
            i++;
            image1X = screenWidth;
            if (i == 106){
                image1.setVisibility(View.INVISIBLE);
            }
        }
        image1.setX(image1X);

        image2X -=10;
        if (image2.getX() < (bear.getWidth()-image2.getWidth())){
            image2X = screenWidth;
            if (i == 105){
                image2.setVisibility(View.INVISIBLE);
            }
        }
        image2.setX(image2X);

        image3X -=10;
        if (image3.getX() < (bear.getWidth()-image3.getWidth())){
            image3X = screenWidth;
            if (i == 105){
                image3.setVisibility(View.INVISIBLE);
            }
        }
        image3.setX(image3X);

        image4X -=10;
        if (image4.getX() < (bear.getWidth()-image4.getWidth())){
            image4X = screenWidth;
            if (i == 78){
                image4.setVisibility(View.INVISIBLE);
            }
        }
        image4.setX(image4X);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()){ //basically BACK was pressed from this activity
            mediaPlayer.stop();
            Log.d("mediaPlayer", "stop");
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
                    Log.d("-------",""+mButton01.getHeight()+" -- "+screenHeight);
                    bear.setImageResource(R.mipmap.bear_eat);
                    Log.d("click1", ""+image2.getWidth());
                    sp1.play(music1, 1, 1, 0, 0, 1);
                    if (image1X < (bear.getWidth()-image1.getWidth()+50)){
                        MainActivity.scores += 200;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.perfects +=1;
                        show.setText("perfect");
                        Log.d("click1", "perfect");     // click baozi button in this is perfect
                    }                                              // if perfect, add some point to score
                    else if (image1X >= (bear.getWidth()-image1.getWidth()+50) && image1X < (bear.getWidth()-image1.getWidth()+100)){
                        MainActivity.scores += 100;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.goods += 1;
                        show.setText("good");
                        Log.d("click1", "good");
                    }
                    else if (image1X >= (bear.getWidth()-image1.getWidth()+100) && image1X < (bear.getWidth()-image1.getWidth()+150)){
                        MainActivity.scores += 50;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.poors += 1;
                        show.setText("poor");
                        Log.d("click1", "poor");
                    }else{
                        Log.d("click1", "bad");
                    }
                    if (image2X < (bear.getWidth()-image2.getWidth()+50)){
                        MainActivity.scores += 200;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.perfects +=1;
                        show.setText("perfect");
                        Log.d("click2", "perfect");
                    }
                    else if (image2X >= (bear.getWidth()-image2.getWidth()+50) && image2X < (bear.getWidth()-image2.getWidth()+100)){
                        MainActivity.scores += 100;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.goods += 1;
                        show.setText("good");
                        Log.d("click2", "good");
                    }
                    else if (image2X >= (bear.getWidth()-image2.getWidth()+100) && image2X < (bear.getWidth()-image2.getWidth()+150)){
                        MainActivity.scores += 50;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.poors += 1;
                        show.setText("poor");
                        Log.d("click2", "poor");
                    }else{
                        Log.d("click2", "bad");
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    //after Image button release
                    mButton01.setImageResource(R.mipmap.dumpling5);
                    bear.setImageResource(R.mipmap.bear_normal);

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
                    bear.setImageResource(R.mipmap.bear_eat);
                    sp2.play(music2, 1, 1, 0, 0, 1);
                    if (image3X < (bear.getWidth()-image1.getWidth()+50)){
                        MainActivity.scores += 200;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.perfects +=1;
                        show.setText("perfect");
                        Log.d("click3", "perfect");
                    }
                    else if (image3X >= (bear.getWidth()-image1.getWidth()+50) && image3X < (bear.getWidth()-image1.getWidth()+100)){
                        MainActivity.scores += 200;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.goods += 1;
                        show.setText("good");
                        Log.d("click3", "good");
                    }
                    else if (image3X >= (bear.getWidth()-image1.getWidth()+100) && image3X < (bear.getWidth()-(bear.getWidth()-image1.getWidth()+150))){
                        MainActivity.scores += 200;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.poors += 1;
                        show.setText("poor");
                        Log.d("click3", "poor");
                    }else{
                        Log.d("click3", "bad");
                    }
                    if (image4X < (bear.getWidth()-image1.getWidth()+50)){
                        MainActivity.scores += 200;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.perfects +=1;
                        show.setText("perfect");
                        Log.d("click4", "perfect");
                    }
                    else if (image4X >= (bear.getWidth()-image1.getWidth()+50) && image4X < (bear.getWidth()-image1.getWidth()+100)){
                        MainActivity.scores += 200;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.goods += 1;
                        show.setText("good");
                        Log.d("click4", "good");
                    }
                    else if (image4X >= (bear.getWidth()-image1.getWidth()+100) && image4X < (bear.getWidth()-(bear.getWidth()-image1.getWidth()+150))){
                        MainActivity.scores += 200;
                        score.setText(String.valueOf(MainActivity.scores));
                        MainActivity.poors += 1;
                        show.setText("poor");
                        Log.d("click4", "poor");
                    }else{
                        Log.d("click4", "bad");
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    //after Image button release
                    mButton02.setImageResource(R.mipmap.dumpling6);
                    bear.setImageResource(R.mipmap.bear_normal);
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
