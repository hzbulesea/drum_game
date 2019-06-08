package drumgame.example.drum_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Menu extends AppCompatActivity {
    ImageButton bt_menu;        // left corner image button
    SoundPool soundPool;        // use for button sound effective
    Resources resources;        // use for change group resources  under the file
    HorizontalScrollView h_sv;  // use for get setOnScrollChangeListener
    DisplayMetrics metrics;      // use for get device density, dpi, width and length
    int btn_menu_sound;         // main button sound declare
    int resId;                  // get resource buffer
    final int BUTTON_NUM = 14;

    View view[] = new View[BUTTON_NUM];                     // View array
    ImageButton button[] = new ImageButton[BUTTON_NUM];     // ImageButton array
    MediaPlayer mp[] = new MediaPlayer[BUTTON_NUM];         //background music declare
    float screen_width, screen_height, screen_density, screen_dpi;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //(ZH) localed the screen as landscape
        setContentView(R.layout.activity_menu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // hide the title bar

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);   // initial sound pool
        btn_menu_sound = soundPool.load(this, R.raw.btn_main, 0);
        bt_menu = (ImageButton) findViewById(R.id.bt_menu);
        LinearLayout gallery = findViewById(R.id.btnCon1);
        LayoutInflater inflater = LayoutInflater.from(this);

        metrics = getResources().getDisplayMetrics();
        final DeviceInfor deviceInfor = new DeviceInfor(metrics);

        h_sv = (HorizontalScrollView) findViewById(R.id.scrv_menu);
        bt_menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    bt_menu.setImageResource(R.mipmap.icon_red_press);
                    soundPool.play(btn_menu_sound, 1, 1, 0, 0, 1);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    bt_menu.setImageResource(R.mipmap.icon_red);
                    Intent intent = new Intent(Menu.this, ScorelistActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        for (int index = 0; index < button.length; index++) {
            view[index] = inflater.inflate(R.layout.button_container, gallery, false);
            button[index] = view[index].findViewById(R.id.btnCon1);

            if (index < 3 || index >= button.length - 3)
                button[index].setImageResource(R.mipmap.streamdraw_blank);
            else
                button[index].setImageResource(R.mipmap.streamdraw);
            final int finalIndex = index;


            button[index].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    resources = getResources();
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        resId = resources.getIdentifier("streamdraw" + (finalIndex - 2) + "_press", "mipmap", getPackageName());
                        button[finalIndex].setImageResource(resId);
                    }
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        try {
                            mp[finalIndex].pause();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        switch (finalIndex) {
                            case 3:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw1);
                                MainActivity.song = "Supid Child";
                                Intent intent1 = new Intent(Menu.this, PlaygroundActivity1.class);
                                startActivity(intent1);
                                finish();
                                break;
                            case 4:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw2);
                                MainActivity.song = "Totoro";
                                Intent intent2 = new Intent(Menu.this, PlaygroundActivity2.class);
                                startActivity(intent2);
                                finish();
                                break;
                            case 5:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw3);
                                MainActivity.song = "Bigfish";
                                Intent intent3 = new Intent(Menu.this, PlaygroundActivity3.class);
                                startActivity(intent3);
                                finish();
                                break;
                            case 6:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw4);
                                MainActivity.song = "Lovingyouslowly";
                                Intent intent4 = new Intent(Menu.this, PlaygroundActivity4.class);
                                startActivity(intent4);
                                finish();
                                break;
                            case 7:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw5);
                                MainActivity.song = "Small Fortune";
                                Intent intent5 = new Intent(Menu.this, PlaygroundActivity5.class);
                                startActivity(intent5);
                                finish();
                                break;
                            case 8:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw6);
                                MainActivity.song = "Snowing";
                                Intent intent6 = new Intent(Menu.this, PlaygroundActivity6.class);
                                startActivity(intent6);
                                finish();
                                break;
                            case 9:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw7);
                                MainActivity.song = "Whynot";
                                Intent intent7 = new Intent(Menu.this, PlaygroundActivity7.class);
                                startActivity(intent7);
                                finish();
                                break;
                            case 10:
                                button[finalIndex].setImageResource(R.mipmap.streamdraw8);
                                MainActivity.song = "Visit Near The River";
                                Intent intent8 = new Intent(Menu.this, PlaygroundActivity8.class);
                                startActivity(intent8);
                                finish();
                                break;
                        }
                    }
                    return false;
                }
            });
            gallery.addView(view[index]);
        }

        // scrollView to change the Image of ImageButton and switch Music
        h_sv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(final View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                for (int i = 0; i < 8; i++) {
                    int int_from = deviceInfor.dpiToPx((int)7.5) * i + 1 + deviceInfor.dpiToPx(95) * i;
                    int int_to = int_from + deviceInfor.dpiToPx(95);
                    if (scrollX >= int_from && scrollX <= int_to) {
                        button[i + 3].requestLayout();
                        button[i + 3].getLayoutParams().height = deviceInfor.dpiToPx(249);

                        resources = getResources();
                        resId = resources.getIdentifier("streamdraw" + (i + 1), "mipmap", getPackageName());
                        button[i + 3].setImageResource(resId);

                        resId = resources.getIdentifier("m" + (i + 1), "raw", getPackageName());
                        mp[i + 3] = MediaPlayer.create(Menu.this, resId);
                        final int finalI = i;
                        new Thread() {
                            @Override
                            public void run() {
                                synchronized (this) {
                                    try {
                                        wait(1000);
                                        if(mp[finalI + 3].isPlaying() == false)
                                            mp[finalI + 3].start();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }.start();
                        button[i + 3].setScaleType(ImageButton.ScaleType.FIT_XY);
                    } else {
                        button[i + 3].requestLayout();
                        try {
                            mp[i + 3].pause();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        button[i + 3].getLayoutParams().height = deviceInfor.dpiToPx(112);
                        button[i + 3].setImageResource(R.mipmap.streamdraw);
                        button[i + 3].setScaleType(ImageButton.ScaleType.FIT_XY);
                    }
                }
            }
        });
    }
}
