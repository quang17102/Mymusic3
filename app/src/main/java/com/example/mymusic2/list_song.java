package com.example.mymusic2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.ref.WeakReference;


public class list_song extends Fragment  {

    static int mSelectedItem=-2;
    ListView listView;

    Adapter_list_song adapter_list_song;

    SongDAO d;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_song,container,false);

        listView = (ListView) view.findViewById(R.id.listView);

        d = new SongDAO();
        // Đổ list bài hát ra màn hình
        adapter_list_song = new Adapter_list_song(getActivity(),R.layout.title_song_list,d.song());
        listView.setAdapter(adapter_list_song);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Khi click thì thay cập nhật lại listView

                Intent intent =  new Intent(getContext(),MusicMyService.class);
                intent.putExtra("songIndex",position);
                getActivity().startService(intent);
                adapter_list_song.notifyDataSetChanged();


                view.setSelected(true);
            }
        });


        ChangeColor();

        return view;
    }
    /// Hàm thay đổi màu trên listView
    private void ChangeColor() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(this, 500);
                if(MusicMyService.currentSongIndex != mSelectedItem)
                {
                    mSelectedItem = MusicMyService.currentSongIndex;
                    Log.d("QUang112233","has change");
                    adapter_list_song.notifyDataSetChanged();
                    Log.d("Quang112233",mSelectedItem+"");
                }
            }
        });
    }




    //    public String chuanHoa(String data)
//    {
//        String[] arrayStr = data.split("");
//        String tam ="";
//        for(int i=arrayStr.length-1;i>=0;i--)
//        {
//            if(arrayStr[i].equals("/")){
//                break;
//            }
//            tam = tam + arrayStr[i];
//        }
//        String reverse = new StringBuffer(tam).reverse().toString();
//        return reverse;
//    }


}
