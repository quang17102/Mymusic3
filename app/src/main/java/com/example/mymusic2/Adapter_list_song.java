package com.example.mymusic2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

import static com.example.mymusic2.R.color.colorAccent;

public class Adapter_list_song extends BaseAdapter {


    Context context;
    int layOut;
    List<Song> songArrayList ;

    public Adapter_list_song(Context context, int layOut, List<Song> songArrayList) {
        this.context = context;
        this.layOut = layOut;
        this.songArrayList = songArrayList;
    }


    @Override
    public int getCount() {
        return songArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }
    private class ViewHolder
    {
        TextView Name_song;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
       // AutoNextSong();
        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layOut,null);

            viewHolder.Name_song = (TextView) convertView.findViewById(R.id.name_song_list);

            convertView.setTag(viewHolder);

        }else
            {
               viewHolder = (ViewHolder) convertView.getTag();
            }

        final Song song = songArrayList.get(pos);

        viewHolder.Name_song.setText(song.getTitle().toString());
        //Điều kiện để hiện thị item đang chọn

       if(list_song.mSelectedItem==pos)
       {
           viewHolder.Name_song.setTextColor(Color.parseColor("#FFEB3B"));
           Log.d("QUang1122","pos1");
       }
       else
           if(list_song.mSelectedItem>-1)
           {
               Log.d("QUang1122","pos2");
               viewHolder.Name_song.setTextColor(Color.parseColor("#000000"));
           }

        return convertView;
    }
    private void AutoNextSong() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(this, 100);
                Log.d("Quang11221",list_song.mSelectedItem+"");
            }
        });
    }

}
