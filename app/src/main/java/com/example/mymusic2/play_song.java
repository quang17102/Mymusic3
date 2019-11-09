package com.example.mymusic2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class play_song extends Fragment {
    public  Intent intent;
    private Animation animation;
    private ImageView icon_cd;
    public static TextView titleSong;



    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.play_song, container, false);

        Anhxa();

        intent = new Intent(getContext(),MusicMyService.class);
        intent.putExtra("songIndex", MusicMyService.currentSongIndex);
        getActivity().startService(intent);


        return view;
    }


    private void load_icon_cd() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotati);
        icon_cd.startAnimation(animation);
    }
////////////////////////////////////////////////////////////////////////////////////////////

    void Anhxa()
    {

        titleSong =(TextView) view.findViewById(R.id.TitleSong);
        icon_cd = (ImageView)view.findViewById(R.id.icon_cd);


    }


}
