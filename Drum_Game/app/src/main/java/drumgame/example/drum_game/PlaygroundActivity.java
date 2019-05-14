package drumgame.example.drum_game;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PlaygroundActivity extends AppCompatActivity {

    private ImageButton mButton01,mButton02;
    private SoundPool sp1, sp2;
    private int music1, music2;
    MediaPlayer mp;

    ImageView image1,image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_playground);

        mButton01=(ImageButton)findViewById(R.id.left);
        mButton02=(ImageButton)findViewById(R.id.right);
        sp1= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        sp2= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music1 = sp1.load(this, R.raw.s2, 1);
        music2 = sp2.load(this, R.raw.s1, 1);

        mp = MediaPlayer.create(this, R.raw.m1);
        mp.start();


        mButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp1.play(music1, 1, 1, 0, 0, 1);
            }
        });
        mButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp2.play(music2, 1, 1, 0, 0, 1);
            }
        });

        move();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(mp != null ) {
            mp.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mp != null) {
            if (!mp.isPlaying()){
                mp.start();
            }
        }
    }

    private void move() {
        boolean f = true;
        for (int i = 0; i < 3; i++){
            if (f == false){
                image1 = findViewById(R.id.imageView);
                Animation img = new TranslateAnimation(Animation.ABSOLUTE, -6000, Animation.ABSOLUTE,Animation.ABSOLUTE);
                img.setDuration(5000);
                img.setFillAfter(true);
                f = true;
                image1.startAnimation(img);

            }
            else{
                image2 = findViewById(R.id.imageView3);
                Animation img2 = new TranslateAnimation(Animation.ABSOLUTE, -4000, Animation.ABSOLUTE,Animation.ABSOLUTE);
                img2.setDuration(5000);
                img2.setFillAfter(true);
                f = false;
                image2.startAnimation(img2);
            }
        }



    }
}
