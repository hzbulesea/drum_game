package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton bt_main_start;
    MediaPlayer mp;         //background music declare
    SoundPool soundPool;    // use for button sound effective
    int btn_main_sound;     //main button sound declare

    static int scores;
    static int perfects;
    static int goods;
    static int poors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.title);        //create background music
        mp.start();                                             // play background music
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);   // initial sound pool
        btn_main_sound = soundPool.load(this,R.raw.btn_main, 0);
        bt_main_start = findViewById(R.id.bt_main_start);       // initial main button

        // main button onTouch Listener
        bt_main_start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    bt_main_start.setImageResource(R.mipmap.start_click);
                    soundPool.play(btn_main_sound, 1, 1, 0, 0, 1);

                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    bt_main_start.setImageResource(R.mipmap.start_onfocus);
                    mp.pause();
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}