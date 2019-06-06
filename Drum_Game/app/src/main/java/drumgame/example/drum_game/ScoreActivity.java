package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView scores, perfects, goods, poors;
    private ImageButton go_to_menu;
    int btn_main_sound;
    private SoundPool sp;
    private int music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score);

        scores = findViewById(R.id.scores);
        perfects = findViewById(R.id.perfects);
        goods = findViewById(R.id.goods);
        poors = findViewById(R.id.poors);
        scores.setText(String.valueOf(MainActivity.scores));
        perfects.setText(String.valueOf(MainActivity.perfects));
        goods.setText(String.valueOf(MainActivity.goods));
        poors.setText(String.valueOf(MainActivity.poors));

        go_to_menu = findViewById(R.id.imageButton);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);   // initial sound pool
        btn_main_sound = sp.load(this,R.raw.btn_main, 0);

        // main button onTouch Listener
        go_to_menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    go_to_menu.setImageResource(R.mipmap.start_click);
                    sp.play(btn_main_sound, 1, 1, 0, 0, 1);

                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    go_to_menu.setImageResource(R.mipmap.start_onfocus);
                    Intent intent = new Intent(ScoreActivity.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });

    }


    private View.OnTouchListener gotomenu = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    //press down image button
                    go_to_menu.setImageResource(R.mipmap.start_click);

                }
                case MotionEvent.ACTION_UP: {
                    go_to_menu.setImageResource(R.mipmap.start_onfocus);
                    Intent intent = new Intent(ScoreActivity.this, Menu.class);
                    startActivity(intent);
                    finish();
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

}
