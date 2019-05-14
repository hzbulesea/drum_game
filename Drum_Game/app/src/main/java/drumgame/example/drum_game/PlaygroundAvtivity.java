package drumgame.example.drum_game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlaygroundAvtivity extends AppCompatActivity {
    private ImageButton mButton01,mButton02;
    private SoundPool sp1, sp2;//声明一个SoundPool
    private int music1, music2;//定义一个整型用load（）；来设置suondID
    MediaPlayer mp;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_avtivity);
        mButton01=(ImageButton)findViewById(R.id.left);
        mButton02=(ImageButton)findViewById(R.id.right);
        sp1= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        sp2= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        music1 = sp1.load(this, R.raw.s2, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
        music2 = sp2.load(this, R.raw.s1, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级

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

    }
}