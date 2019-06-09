package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScorelistActivity extends AppCompatActivity {

    private ImageButton back_btn;
    private SoundPool soundPool;
    int btn_menu_sound;
    DisplayMetrics displayMetrics;
    LinearLayout l_top,l_bottom;
    ListView l_middle;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scorelist);
        scoreList();

        back_btn = findViewById(R.id.back_btn);
        displayMetrics = getResources().getDisplayMetrics();
        DeviceInfor deviceInfor = new DeviceInfor(displayMetrics);

        l_top = (LinearLayout)findViewById(R.id.layout1);
        l_top.getLayoutParams().height = deviceInfor.dpiToPx(62);
        textView = (TextView)findViewById(R.id.textView);
        textView.setTextSize(deviceInfor.pxTodpi(l_top.getLayoutParams().height - deviceInfor.dpiToPx(16)));

        l_middle=(ListView)findViewById(R.id.l_middle);
        l_middle.getLayoutParams().height = (int)(deviceInfor.getHeight()*0.6);

        l_bottom = (LinearLayout)findViewById(R.id.l_bottom);
        l_bottom.getLayoutParams().height = (int)(deviceInfor.getHeight()*0.2);




        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);   // initial sound pool
        btn_menu_sound = soundPool.load(this,R.raw.btn_main, 0);

        back_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    back_btn.setImageResource(R.mipmap.back_click);
                    soundPool.play(btn_menu_sound, 1, 1, 0, 0, 1);
                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    back_btn.setImageResource(R.mipmap.back_onfocus);
                    Intent intent = new Intent(ScorelistActivity.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });
    }

    private void scoreList() {
        ListView mListView =(ListView) findViewById(R.id.l_middle);
        Cursor cursor = MainActivity.db.view();
        Log.d("cursor", String.valueOf(cursor));
        ArrayList<Contact> listData = new ArrayList<>();
        while(cursor.moveToNext()){
            Contact imageList = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            listData.add(imageList);
        }
        ScoreAdapter adapter = new ScoreAdapter(this, R.layout.score_list, listData);
        mListView.setAdapter(adapter);
    }
}
