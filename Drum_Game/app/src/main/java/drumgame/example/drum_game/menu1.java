package drumgame.example.drum_game;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class menu1 extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6, button7, button8;
    View view1, view2, view3, view4, view5, view6, view7, view8;
    SoundPool sp;
    int mSound1, mSound2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        LinearLayout gallery = findViewById(R.id.btnCon1);
        LayoutInflater inflater = LayoutInflater.from(this);
        getSupportActionBar().hide(); // hide the title bar
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC,100);
        mSound1 = sp.load(this, R.raw.s1, 0);
        mSound2 = sp.load(this, R.raw.s2, 0);



        view1 = inflater.inflate(R.layout.button_container, gallery, false);
        button1 = view1.findViewById(R.id.btnCon1);
        button1.setText("Songs");
        sp.play(mSound1, 1, 1, 0, 0, 1);
        gallery.addView(view1);

        view2 = inflater.inflate(R.layout.button_container, gallery, false);
        button2 = view2.findViewById(R.id.btnCon1);
        button2.setText("button 2");
        sp.play(mSound1, 1, 1, 0, 0, 1);
        gallery.addView(view2);

        view3 = inflater.inflate(R.layout.button_container, gallery, false);
        button3 = view3.findViewById(R.id.btnCon1);
        button3.setText("button 3");
        sp.play(mSound1, 1, 1, 0, 0, 1);
        gallery.addView(view3);

        view4 = inflater.inflate(R.layout.button_container, gallery, false);
        button4 = view4.findViewById(R.id.btnCon1);
        button4.setText("button 4");
        sp.play(mSound1, 1, 1, 0, 0, 1);
        gallery.addView(view4);

        view5 = inflater.inflate(R.layout.button_container, gallery, false);
        button5 = view5.findViewById(R.id.btnCon1);
        button5.setText("button 5");
        sp.play(mSound1, 1, 1, 0, 0, 1);
        gallery.addView(view5);

        view6 = inflater.inflate(R.layout.button_container, gallery, false);
        button6 = view6.findViewById(R.id.btnCon1);
        button6.setText("button 6");
        sp.play(mSound1, 1, 1, 0, 0, 1);
        gallery.addView(view6);

        view7 = inflater.inflate(R.layout.button_container, gallery, false);
        button7 = view7.findViewById(R.id.btnCon1);
        button7.setText("button 7");
        sp.play(mSound1, 1, 1, 0, 0, 1);
        gallery.addView(view7);

        view8 = inflater.inflate(R.layout.button_container, gallery, false);
        button8 = view8.findViewById(R.id.btnCon1);
        button8.setText("Ready");
        gallery.addView(view8);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn1(v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn2(v);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn3(v);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn4(v);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn5(v);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn6(v);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn7(v);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn8(v);
            }
        });
    }



    public void toBtn1(View v) {
        Intent intent = new Intent(this, songPage.class);
        sp.play(mSound1, 1, 1, 0, 0, 1);
        startActivity(intent);
    }
    public void toBtn2(View v) {
        Intent intent = new Intent(this, songPage.class);
        sp.play(mSound1, 1, 1, 0, 0, 1);
        startActivity(intent);
    }
    public void toBtn3(View v) {
        Intent intent = new Intent(this, songPage.class);
        sp.play(mSound1, 1, 1, 0, 0, 1);
        startActivity(intent);
    }
    public void toBtn4(View v) {
        Intent intent = new Intent(this, songPage.class);
        sp.play(mSound1, 1, 1, 0, 0, 1);
        startActivity(intent);
    }
    public void toBtn5(View v) {
        Intent intent = new Intent(this, songPage.class);
        sp.play(mSound1, 1, 1, 0, 0, 1);
        startActivity(intent);
    }
    public void toBtn6(View v) {
        Intent intent = new Intent(this, songPage.class);
        sp.play(mSound1, 1, 1, 0, 0, 1);
        startActivity(intent);
    }
    public void toBtn7(View v) {
        Intent intent = new Intent(this, songPage.class);
        sp.play(mSound1, 1, 1, 0, 0, 1);
        startActivity(intent);
    }
    public void toBtn8(View v) {
        Intent intent = new Intent(this, PlaygroundAvtivity.class);
        sp.play(mSound2, 1, 1, 0, 0, 1);
        startActivity(intent);
    }





}
