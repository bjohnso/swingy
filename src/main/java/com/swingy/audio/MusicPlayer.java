package com.swingy.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.security.PublicKey;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class MusicPlayer implements Runnable {

    private ArrayList<AudioFile> musicFiles;
    private int currentSongIndex = 0;
    private boolean running;

    public MusicPlayer(String... files){
        musicFiles = new ArrayList<AudioFile>();
        for(String file : files)
            musicFiles.add(new AudioFile("./res/audio/" + file + ".wav"));
    }

    @Override
    public void run() {
        running = true;
        AudioFile audioFile = musicFiles.get(currentSongIndex);
        audioFile.play();
        while (running){
            if (!audioFile.isPlaying()){
                currentSongIndex++;
                if (currentSongIndex >= musicFiles.size())
                    currentSongIndex = 0;
                audioFile = musicFiles.get(currentSongIndex);
                audioFile.play();
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
