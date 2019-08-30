package com.swingy.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.security.PublicKey;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class MusicPlayer implements Runnable {

    private String files[];
    private ArrayList<AudioFile> musicFiles;
    private boolean running;
    private int volumeMod = -20;

    public MusicPlayer(String... files){
        this.files = files;
        musicFiles = new ArrayList<AudioFile>();
        for(String file : files)
            musicFiles.add(new AudioFile("./res/audio/" + file + ".wav"));
    }

    public void increaseVolume(){
        volumeMod += 10;
    }

    public void decreaseVolume(){
        volumeMod -= 10;
    }

    @Override
    public void run() {
        running = true;
        AudioFile audioFile = musicFiles.get(shuffle());
        audioFile.play();
        while (running){
            audioFile.setVolume(volumeMod);
            if (!audioFile.isPlaying()){
                musicFiles.remove(audioFile);
                if (musicFiles.size() <= 0) {
                    for(String file : files)
                        musicFiles.add(new AudioFile("./res/audio/" + file + ".wav"));
                }
                audioFile = musicFiles.get(shuffle());
                audioFile.play();
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int shuffle(){
        return  0 + (int)(Math.random() * ((musicFiles.size() - 1) + 1));
    }
}
