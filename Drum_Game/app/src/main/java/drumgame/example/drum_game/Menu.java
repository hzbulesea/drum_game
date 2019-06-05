package drumgame.example.drum_game;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

public class Menu extends AppCompatActivity {
    ImageButton bt_menu;    //left corner image button
    SoundPool soundPool;    // use for button sound effective
    int btn_menu_sound;     //main button sound declare
    final int BUTTON_NUM = 14;
    int index;

    View view[] = new View[BUTTON_NUM];
    ImageButton button[] = new ImageButton[BUTTON_NUM];
    HorizontalScrollView h_sv;
    MediaPlayer mp[] = new MediaPlayer[BUTTON_NUM];         //background music declare

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        setContentView(R.layout.activity_menu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // hide the title bar

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);   // initial sound pool
        btn_menu_sound = soundPool.load(this,R.raw.btn_main, 0);
        bt_menu = (ImageButton) findViewById(R.id.bt_menu);
        LinearLayout gallery = findViewById(R.id.btnCon1);
        LayoutInflater inflater = LayoutInflater.from(this);

        h_sv = (HorizontalScrollView)findViewById(R.id.scrv_menu);

        bt_menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    bt_menu.setImageResource(R.mipmap.icon_red_press);
                    soundPool.play(btn_menu_sound, 1, 1, 0, 0, 1);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    bt_menu.setImageResource(R.mipmap.icon_red);
                    Intent intent = new Intent(Menu.this, PlaygroundActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        for (int index = 0; index < button.length; index++){
            view[index] = inflater.inflate(R.layout.button_container, gallery, false);
            button[index]=view[index].findViewById(R.id.btnCon1);
            if(index < 3 || index >= button.length - 3)
                button[index].setImageResource(R.mipmap.streamdraw_blank);
            else
                button[index].setImageResource(R.mipmap.streamdraw);
            final int finalIndex = index;
            button[index].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        switch (finalIndex) {
                            case 3:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw1_press);
                                break;
                            case 4:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw2_press);
                                break;
                            case 5:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw3_press);
                                break;
                            case 6:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw4_press);
                                break;
                            case 7:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw5_press);
                                break;
                            case 8:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw6_press);
                                break;
                            case 9:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw7_press);
                                break;
                            case 10:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw8_press);
                                break;
                        }
                    }
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        try {
                            mp[finalIndex].pause();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        switch (finalIndex) {
                            case 3:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw1);
                                break;
                            case 4:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw2);
                                Intent intent = new Intent(Menu.this, PlaygroundActivity.class);
                                startActivity(intent);
                                break;
                            case 5:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw3);
                                break;
                            case 6:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw4);
                                break;
                            case 7:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw5);
                                break;
                            case 8:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw6);
                                break;
                            case 9:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw7);
                                break;
                            case 10:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw8);
                                break;
                        }
                    }
                    return false;
                }
            });
            gallery.addView(view[index]);
        }

//        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        final int s_width = wm.getDefaultDisplay().getWidth();  // get screen width
//        final int s_height = wm.getDefaultDisplay().getHeight();  // get screen width
//        System.out.println("s_height: " + s_height);
//        new Thread()
//        {
//            @Override
//            public void run()
//            {
//                synchronized(this)
//                {
//                    try
//                    {
//                        wait(1000); //1秒
//                    }
//                    catch (InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//                Log.i("Test", "height：" + view[3].getHeight());
//                Log.i("Test", "width：" +  view[3].getWidth());
//                Log.i("Test", "scrollX：" +  view[3].getRight());
//                Log.i("Test", "scrollY：" +  view[3].getTop());
//
//                int button_number = s_width/ view[0].getWidth() / 2;
//                int half_button_width = view[0].getWidth()/2;
//                int middle_button_scrollX = view[0].getWidth() * button_number;
//                int initial_positionX = half_button_width + middle_button_scrollX;
//                int move_scrollX = (s_width/ 2 - initial_positionX) * (-1);
//                // < 0 move left, >0 move right
//                Log.i("Test", "img_width：" + button[3].getWidth());
//                Log.i("Test", "button_number：" + button_number);
//                Log.i("Test", "half_button_width：" + half_button_width);
//                Log.i("Test", "middle_button_scrollX：" + middle_button_scrollX);
//                Log.i("Test", "initial_positionX：" + initial_positionX);
//                Log.i("Test", "move_scrollX：" + move_scrollX);
//                h_sv.setScrollX(move_scrollX);
//                Field field=R.drawable.class.getField(type);
//            }
//        }.start();


        h_sv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(final View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //System.out.println("scrollX: " + scrollX + " scrollY: " + scrollY + " oldScrollX: " + oldScrollX+ " oldScrollY: " + scrollY);
                //System.out.println("view[3].getWidth(): " + button[3].getWidth());
                for (int i = 0; i < 8; i++) {
                    int int_from = 20*i+1+249*i;
                    int int_to = int_from + 249;
                    if (scrollX >= int_from && scrollX <= int_to) {

                        MarginLayoutParams layoutParams = (MarginLayoutParams) button[i+3].getLayoutParams();
                        layoutParams.setMargins(0, 185, 0, 0);
                        button[i+3].requestLayout();
                        button[i+3].getLayoutParams().height = 646;
                        switch (i){
                            case 0:
                                button[i+3].setImageResource(R.mipmap.streamdraw1);
                                mp[i+3] = MediaPlayer.create(Menu.this, R.raw.m1_stupidchild);

                                break;
                            case 1:
                                button[i+3].setImageResource(R.mipmap.streamdraw2);
                                mp[i+3] = MediaPlayer.create(Menu.this, R.raw.m2_totoro);
                                break;
                            case 2:
                                button[i+3].setImageResource(R.mipmap.streamdraw3);
                                mp[i+3] = MediaPlayer.create(Menu.this, R.raw.m3_bigfish);
                                break;
                            case 3:
                                button[i+3].setImageResource(R.mipmap.streamdraw4);
                                mp[i+3] = MediaPlayer.create(Menu.this, R.raw.m8_demo_lovingyouslowly);
                                break;
                            case 4:
                                button[i+3].setImageResource(R.mipmap.streamdraw5);
                                mp[i+3] = MediaPlayer.create(Menu.this, R.raw.m5_small_fortune);
                                break;
                            case 5:
                                button[i+3].setImageResource(R.mipmap.streamdraw6);
                                mp[i+3] = MediaPlayer.create(Menu.this, R.raw.m6_snowing);
                                break;
                            case 6:
                                button[i+3].setImageResource(R.mipmap.streamdraw7);
                                mp[i+3] = MediaPlayer.create(Menu.this, R.raw.m7_whynot);
                                break;
                            case 7:
                                button[i+3].setImageResource(R.mipmap.streamdraw8);
                                mp[i+3] = MediaPlayer.create(Menu.this, R.raw.m8_visit_near_the_river);
                                break;
                            default:
                                button[i+3].setImageResource(R.mipmap.streamdraw1);
                        }
                        final int finalI = i;
                        new Thread()
                        {
                            @Override
                            public void run()
                            {
                                synchronized(this)
                                {
                                    try
                                    {
                                        wait(1000);
                                        mp[finalI +3].start();
                                    }
                                    catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }.start();
                        button[i+3].setScaleType(ImageButton.ScaleType.FIT_XY);
                    } else {
                        MarginLayoutParams layoutParams = (MarginLayoutParams) button[i+3].getLayoutParams();
                        layoutParams.setMargins(0, 538, 0, 0);
                        button[i+3].requestLayout();
                        try {
                            mp[i+3].pause();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        button[i+3].getLayoutParams().height = 291;
                        button[i+3].setImageResource(R.mipmap.streamdraw);
                        button[i+3].setScaleType(ImageButton.ScaleType.FIT_XY);
                    }
                }
            }
        });
    }
}
