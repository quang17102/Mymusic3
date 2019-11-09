package com.example.mymusic2;

import android.os.Environment;
import android.util.Log;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {



    ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());

    public ArrayList<Song> song( )
    {
        ArrayList<Song> songs;
        songs = new ArrayList<Song>();
        Song song;
        for (int i=0;i<mySongs.size();i++)
        {
            song = new Song();
            song.setTitle( mySongs.get(i).getName());
            song.setLink(mySongs.get(i).toString());
            songs.add(song);
        }
        return songs;
    }

    public ArrayList<File> findSong(File root)
    {
        ArrayList<File> al = new ArrayList<File>();

        File[] files = root.listFiles();
        for(File singleFile : files )
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
            {
                al.addAll(findSong(singleFile));
            }else
            {
                if(singleFile.getName().endsWith(".mp3"))
                {
                    al.add(singleFile);
                }
            }
        }

        return al;
    }




}
