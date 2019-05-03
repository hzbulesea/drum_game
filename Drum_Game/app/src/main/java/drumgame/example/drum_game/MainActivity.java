package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton bt_main_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        setContentView(R.layout.activity_main);
        bt_main_start = (ImageButton) findViewById(R.id.bt_main_start);
        bt_main_start.setOnTouchListener(imageButtonTouchListener);

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
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
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