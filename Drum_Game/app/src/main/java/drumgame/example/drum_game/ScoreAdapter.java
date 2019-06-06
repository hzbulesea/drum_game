package drumgame.example.drum_game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

class ScoreAdapter extends ArrayAdapter<Contact> {

    Context icontext;
    int ilayoutResourceId;
    ArrayList<Contact> idata;

    public ScoreAdapter(Context context, int layoutResourceId, ArrayList<Contact> data) {
        super(context, layoutResourceId, data);
        ilayoutResourceId = layoutResourceId;
        icontext = context;
        idata = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ScoreHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)icontext).getLayoutInflater();
            row = inflater.inflate(ilayoutResourceId, parent, false);

            holder = new ScoreHolder();
            holder.song = (TextView)row.findViewById(R.id.song);
            holder.score = (TextView)row.findViewById(R.id.score);
            row.setTag(holder);
        }
        else
        {
            holder = (ScoreHolder)row.getTag();
        }

        Contact picture = idata.get(position);
        holder.song.setText(picture.getSong());
        holder.score.setText(String.valueOf(picture.getScore()));

        return row;
    }

    static class ScoreHolder
    {
        TextView song;
        TextView score;
    }
}
