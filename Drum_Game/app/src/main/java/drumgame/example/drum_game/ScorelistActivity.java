package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ScorelistActivity extends AppCompatActivity {

    private ImageButton back_btn;

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

        back_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    back_btn.setImageResource(R.mipmap.back_click);

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
        ListView mListView =(ListView) findViewById(R.id.list);
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
