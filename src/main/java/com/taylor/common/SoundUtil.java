package com.taylor.common;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;

/**
 * @author taylor
 */
public class SoundUtil {
    public static void main(String... args) {
        paly("audio/sale.wav");
    }
    public static void paly(String audioPath) {
        try {
            FileInputStream fileau = new FileInputStream(SoundUtil.class.getClassLoader().getResource(".").getPath() + audioPath);
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}