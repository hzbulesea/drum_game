package drumgame.example.drum_game;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class songPage extends AppCompatActivity {
    SoundPool sp;
    int mSound1, mSound2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_page);
        getSupportActionBar().hide(); // hide the title bar
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC,100);
        mSound1 = sp.load(this, R.raw.s1, 0);
        mSound2 = sp.load(this, R.raw.s2, 0);
    }


    public void song1(View view) {
        sp.play(mSound1, 1, 1, 0, 0, 1);
        finish();
    }

    public void song2(View view) {
        sp.play(mSound1, 1, 1, 0, 0, 1);
        finish();
    }

    public void song3(View view) {
        sp.play(mSound1, 1, 1, 0, 0, 1);
        finish();
    }
}
