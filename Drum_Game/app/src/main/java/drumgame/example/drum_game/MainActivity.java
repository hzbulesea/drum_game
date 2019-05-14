package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton bt_main_start;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        setContentView(R.layout.activity_main);
        bt_main_start =findViewById(R.id.bt_main_start);
        bt_main_start.setOnTouchListener(imageButtonTouchListener);

        mp = MediaPlayer.create(this, R.raw.m1);
        mp.start();
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


    //(ZH) press image button change image
    private View.OnTouchListener imageButtonTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                {
                    //press down image button
                    bt_main_start.setImageResource(R.mipmap.start_click);
                    break;
                }
                case MotionEvent.ACTION_UP:
                {
                    //after Image button release
                    bt_main_start.setImageResource(R.mipmap.start_onfocus);
                    Intent intent = new Intent(MainActivity.this, menu1.class);
                    startActivity(intent);
                    break;
                }
                default:
                    break;
            }
            return false;
        }
    };
}