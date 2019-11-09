package com.example.mymusic2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class control_song extends Fragment {

    public static ImageButton btnPause, btnPre, btnNext, btnRandom, btnRotate;
    public static SeekBar skSong;
    public static TextView txtTimesong, txtTimeTotal, testTime, posTime ;


    private int Position = 0;
    private int k = 0;

    private boolean Random = true;
    private boolean Rotate = true;
    static ArrayList<Song> songsListData = new ArrayList<Song>();
    SongDAO songDAO;
    View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.control_song,container,false);

        Anhxa();
        songDAO = new SongDAO();
        try {
                for (int i = 0; i < songDAO.song().size(); i++) {
                    // adding HashList to ArrayList
                    songsListData.add(songDAO.song().get(i));
                }
// Adding song Items to ListView Adapter
//            adapter = new SimpleAdapter()r(this, songsListData,
//                    R.layout.listsong_item, new String[] { "songTitle" },
//                    new int[] { R.id.songTitle });
        } catch (RuntimeException e) {
            e.printStackTrace();

        }

//BUTTON Pause
//            btnPause.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                        getActivity().startService(new Intent(getActivity(),MusicMyService.class));
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Dulieu",songDAO.song().get(Position).getLink());
//                    intent.putExtras(bundle);
//
//
//
//
//
//                    if (mediaPlayer.isPlaying()) {
//                        mediaPlayer.pause();
//                        btnPause.setImageResource(R.drawable.rsz_play);
//
//
//                    } else {
//
//                        mediaPlayer.start();
//                        btnPause.setImageResource(R.drawable.rsz_pause);
//
//
//                    }
//                    UpdateTime();
//
//
//
//                }
//
//            });
////// //BUTTON Next;
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (Random) {
//                    //mỗi lần phát là mình sẽ lưu lại danh sách bài hát đã phát
//                    Position = dsBaiHatDaPhat.get(k);
//                    k++;
//
//                    if (k > songDAO.song().size() - 1)
//                    {
//                        if (Rotate) {
//                            k = 0;
//                            Collections.shuffle(dsBaiHatDaPhat);
//                        } else {
//                            k = 0;
//
//                        }
//                    }
//
//
//                } else {
//                    Position++;
//                    if (Position > songDAO.song().size() - 1) {
//                        Position = 0;
//                    }
//                }
//
//
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.release();
//                }
//                btnPause.setImageResource(R.drawable.rsz_pause);
//
//                listener.onInputBsent(Position);
//
//                KhoitaoMediaPlayer(songDAO.song().get(Position).getLink());
//                mediaPlayer.start();
//
//            }
//        });
//////// BUTTON Pre
//        btnPre.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (Random) {
//                    //mỗi lần phát là mình sẽ lưu lại danh sách bài hát đã phát
//                    Position = dsBaiHatDaPhat.get(k);
//                    k++;
//                    if (k > songDAO.song().size() - 1) {
//                        k = 0;
//                        Collections.shuffle(dsBaiHatDaPhat);
//                    }
//                } else {
//                    Position--;
//                }
//
//
//                if (Position < 0) {
//                    Position = songDAO.song().size() - 1;
//                }
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.release();
//                }
//
//                KhoitaoMediaPlayer(songDAO.song().get(Position).getLink());
//                mediaPlayer.start();
//
//
//                SetTimeTotal();
//            }
//        });
////////BUTTON Random
//        btnRandom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (Random) {
//                    btnRandom.setImageResource(R.drawable.rotate_off);
//                    Random = false;
//                } else {
//
//                    btnRandom.setImageResource(R.drawable.random);
//                    Random = true;
//                }
//            }
//        });
//        btnRotate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (Rotate) {
//                    Rotate = false;
//                    btnRotate.setImageResource(R.drawable.rotate_off);
//                } else {
//                    Rotate = true;
//                    btnRotate.setImageResource(R.drawable.rotate);
//                }
//            }
//        });
////
////
//// SEEKBar
//        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                mediaPlayer.seekTo(skSong.getProgress());
//            }
//        });
//

        //Log.d("AAA","Oncreate");

        return view;
    }


    public void Anhxa()
    {
        btnNext = (ImageButton) view.findViewById(R.id.btnNext);
        btnPause = (ImageButton)view.findViewById(R.id.btnPause);
        btnPre = (ImageButton) view.findViewById(R.id.btnPre);

        skSong = (SeekBar) view.findViewById(R.id.skSong);

        btnRandom = (ImageButton) view.findViewById(R.id.btnRandom);
        btnRotate = (ImageButton) view.findViewById(R.id.btnRotate);
        txtTimesong = (TextView)view.findViewById(R.id.txtSong);
        txtTimeTotal = (TextView)view.findViewById(R.id.txtSongTotal);
    }


//        private void  SetTimeTotal()
//    {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//        txtTimeTotal.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
//        skSong.setMax(mediaPlayer.getDuration());
//
//    }









}
