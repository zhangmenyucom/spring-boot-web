package com.taylor.common;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author taylor
 */
public class SoundUtil {
    public static void main(String... args) {
        paly("audio/alarm.wav");
    }
    public static void paly(String audioPath) {
        try {
            InputStream resourceAsStream = SoundUtil.class.getClassLoader().getResourceAsStream(audioPath);
            AudioStream as = new AudioStream(resourceAsStream);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}