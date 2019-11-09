package com.example.mymusic2;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MusicMyService extends Service  implements View.OnClickListener  {
    private MediaPlayer mediaPlayer;
    private WeakReference<ImageButton> btnPause, btnPre, btnNext, btnRandom, btnRotate;
    private WeakReference<TextView> txtTimesong, txtTimeTotal, testTime, posTime, titleSong;
    private WeakReference<TextView> title_song;
    private WeakReference<SeekBar> songProgressBar;

    public static ArrayList<Song> songsListingSD = new ArrayList<Song>();
    private boolean isShuffle = false;
    private boolean isRepeat = false;
    private Handler progressBarHandler = new Handler();
    SimpleDateFormat simpleDateFormat;

    private IBinder binder = new MyBinder();
    public static int currentSongIndex = -1;

    SongDAO songDAO;

    @Override
    public void onCreate() {
        simpleDateFormat = new SimpleDateFormat("mm:ss");
        //Khởi tạo Media
        mediaPlayer = new MediaPlayer();

        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        songDAO = new SongDAO();
        songsListingSD = MainActivity.control_song.songsListData;

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("QUang2211","onStartCommand");
        // Ánh xạ
        UI();
      // AutoNextSong();

        int songIndex = intent.getIntExtra("songIndex", 0);
        Log.d("Quang0011",songIndex + " " + currentSongIndex);

        if (songIndex != currentSongIndex) {
            playSong(songIndex);
            //initNotification(songIndex);
            currentSongIndex = songIndex;
        } else if (currentSongIndex != -1) {

            titleSong.get().setText(songsListingSD.get(currentSongIndex).getTitle());
            if (mediaPlayer.isPlaying())
                btnPause.get().setImageResource(R.drawable.rsz_pause);
            else
                btnPause.get().setImageResource(R.drawable.rsz_play);
        }
        songProgressBar.get().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(songProgressBar.get().getProgress());


            }
        });



        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        currentSongIndex = -1;
        // Remove progress bar update Hanlder callBacks
        //progressBarHandler.removeCallbacks(mUpdateTimeTask);
        Log.d("PlayerService", "Player Service Stopped");
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }

    }

    public MusicMyService() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ServiceDemo", "Đã gọi onBind()");
        return binder;
    }



    public class MyBinder extends Binder {

        // phương thức này trả về đối tượng MyService
        MusicMyService getService() {
            return MusicMyService.this;
        }
    }

    public void UI() {
        btnPause = new WeakReference<ImageButton>(MainActivity.control_song.btnPause);
        btnNext = new WeakReference<ImageButton>(MainActivity.control_song.btnNext);
        btnPre = new WeakReference<ImageButton>(MainActivity.control_song.btnPre);
        btnRotate = new WeakReference<ImageButton>(MainActivity.control_song.btnRotate);
        btnRandom = new WeakReference<ImageButton>(MainActivity.control_song.btnRandom);
        btnPause.get().setOnClickListener(this);
        btnNext.get().setOnClickListener(this);
        btnPre.get().setOnClickListener(this);
        btnRotate.get().setOnClickListener(this);
        btnRandom.get().setOnClickListener(this);

        songProgressBar = new WeakReference<SeekBar>(MainActivity.control_song.skSong);


        txtTimeTotal = new WeakReference<TextView>(MainActivity.control_song.txtTimeTotal);
        txtTimesong = new WeakReference<TextView>(MainActivity.control_song.txtTimesong);

        titleSong = new WeakReference<TextView>(MainActivity.play_song.titleSong);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPause:
                if (currentSongIndex != -1) {
                    Log.d("PlayerService", "Null");
                    if (mediaPlayer.isPlaying()) {
                        if (mediaPlayer != null) {
                            mediaPlayer.pause();
// Changing button image to play button
                            btnPause.get()
                                    .setImageResource(R.drawable.rsz_play);
                            Log.d("Player Service", "Pause");
                        }
                    } else {
// Resume song
                        if (mediaPlayer != null) {
                            mediaPlayer.start();
// Changing button image to pause button
                            btnPause.get().setImageResource(
                                    R.drawable.rsz_pause);
                            //Log.d("Player Service", "Play");
                        }
                    }
                }

                break;
            case R.id.btnNext:
//// check if next song is there or not
                Log.d("PlayerService", "Next");
                if (currentSongIndex < (songsListingSD.size() - 1)) {
                    Log.d("next", "Next");
                    playSong(currentSongIndex + 1);
                    currentSongIndex = currentSongIndex + 1;

                } else {
// play first song
                    Log.d("next1", "Next");
                    playSong(0);
                    currentSongIndex = 0;
                }
                break;

            case R.id.btnPre:
                if (currentSongIndex > 0) {
                    playSong(currentSongIndex - 1);
                    currentSongIndex = currentSongIndex - 1;
                } else {
// play last song
                    playSong(songsListingSD.size() - 1);
                    currentSongIndex = songsListingSD.size() - 1;
                }
                break;

            case R.id.btnRandom:
                if (isRepeat) {
                    isRepeat = false;
//                    Toast.makeText(getApplicationContext(), "Repeat is OFF",
//                            Toast.LENGTH_SHORT).show();
                    btnRotate.get().setImageResource(R.drawable.random);
                } else {
// make repeat to true
                    isRepeat = true;
//                    Toast.makeText(getApplicationContext(), "Repeat is ON",
//                            Toast.LENGTH_SHORT).show();
// make shuffle to false
                    isShuffle = false;
                    btnRotate.get().setImageResource(R.drawable.rotate_off);
                    //btnShuffle.get().setImageResource(R.drawable.btn_shuffle);
                }
                break;
            case R.id.btnRotate:
                if (isShuffle) {
                    isShuffle = false;
//                    Toast.makeText(getApplicationContext(), "Shuffle is OFF",
//                            Toast.LENGTH_SHORT).show();
                    btnRandom.get().setImageResource(R.drawable.rotate_off);
                } else {
// make repeat to true
                    isShuffle = true;
//                    Toast.makeText(getApplicationContext(), "Shuffle is ON",
//                            Toast.LENGTH_SHORT).show();
// make shuffle to false
                    isRepeat = false;
                    btnRandom.get().setImageResource(
                            R.drawable.rotate);
                    //btnRepeat.get().setImageResource(R.drawable.btn_repeat);
                }
                break;
        }
    }

    public void playSong(int songIndex) {
// Play song
        try {
            Uri uri = Uri.parse(songsListingSD.get(songIndex).getLink());
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
            //mediaPlayer.prepare();
            mediaPlayer.start();
// Displaying Song title
            String songTitle = songsListingSD.get(songIndex).getTitle();

            titleSong.get().setText(songTitle);
// Changing Button Image to pause image
            btnPause.get().setImageResource(R.drawable.rsz_pause);
// set Progress bar values
            //songProgressBar.get().setProgress(0);

// Updating progress bar
             updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }
        public void updateProgressBar() {

            progressBarHandler.postDelayed(mUpdateTimeTask, 100);

            // Tự động chuyển bài
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d("Quang1122","Next");
                    if (isRepeat) {
                        // repeat is on play same song again
                        playSong(currentSongIndex);
                    } else if (isShuffle) {
                        // shuffle is on - play a random song
                        Random rand = new Random();
                        currentSongIndex = rand.nextInt((songsListingSD.size() - 1) - 0 + 1) + 0;
                        playSong(currentSongIndex);
                    } else {
                        // no repeat or shuffle ON - play next song
                        if (currentSongIndex < (songsListingSD.size() - 1)) {
                            playSong(currentSongIndex + 1);
                            currentSongIndex = currentSongIndex + 1;
                        } else {
                            // play first song
                            playSong(0);
                            currentSongIndex = 0;
                        }
                    }
                }
            });


        }

//Cập nhật lại thời lượng bài hát

    private Runnable mUpdateTimeTask = new Runnable() {
            public void run() {
                songProgressBar.get().setMax(mediaPlayer.getDuration());
                txtTimeTotal.get().setText(simpleDateFormat.format(mediaPlayer.getDuration()));
                long currentDuration = 0;
                try {
                    currentDuration = mediaPlayer.getCurrentPosition();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                //Updating vị trí thời gian bài hát
                txtTimesong.get().setText(simpleDateFormat.format(currentDuration));

                //Updating vị trí progressbar
                songProgressBar.get().setProgress(mediaPlayer.getCurrentPosition());

// Running this thread after 100 milliseconds

                progressBarHandler.postDelayed(this, 100);

// Log.d("AndroidBuildingMusicPlayerActivity","Runable  progressbar");
            }
        };








}
