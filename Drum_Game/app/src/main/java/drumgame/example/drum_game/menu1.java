package drumgame.example.drum_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class menu1 extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6, button7, button8;
    View view1, view2, view3, view4, view5, view6, view7, view8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        LinearLayout gallery = findViewById(R.id.btnCon1);
        LayoutInflater inflater = LayoutInflater.from(this);


        view1 = inflater.inflate(R.layout.button_container, gallery, false);
        button1 = view1.findViewById(R.id.btnCon1);
        button1.setText("Song 1");

        gallery.addView(view1);

        view2 = inflater.inflate(R.layout.button_container, gallery, false);
        button2 = view2.findViewById(R.id.btnCon1);
        button2.setText("Song 2");
        gallery.addView(view2);

        view3 = inflater.inflate(R.layout.button_container, gallery, false);
        button3 = view3.findViewById(R.id.btnCon1);
        button3.setText("Song 3");
        gallery.addView(view3);

        view4 = inflater.inflate(R.layout.button_container, gallery, false);
        button4 = view4.findViewById(R.id.btnCon1);
        button4.setText("Song 4");
        gallery.addView(view4);

        view5 = inflater.inflate(R.layout.button_container, gallery, false);
        button5 = view5.findViewById(R.id.btnCon1);
        button5.setText("Song 5");
        gallery.addView(view5);

        view6 = inflater.inflate(R.layout.button_container, gallery, false);
        button6 = view6.findViewById(R.id.btnCon1);
        button6.setText("Song 6");
        gallery.addView(view6);

        view7 = inflater.inflate(R.layout.button_container, gallery, false);
        button7 = view7.findViewById(R.id.btnCon1);
        button7.setText("Song 7");
        gallery.addView(view7);

        view8 = inflater.inflate(R.layout.button_container, gallery, false);
        button8 = view8.findViewById(R.id.btnCon1);
        button8.setText("Song 8");
        gallery.addView(view8);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBtn1(v);
            }
        });
    }




    public void toBtn1(View v) {
        finish();
    }




}
