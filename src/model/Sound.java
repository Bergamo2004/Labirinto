package model;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[10];

	public Sound() {
		soundURL[0] = getClass().getResource("/suoni/Soundtrack.wav");
		soundURL[1] = getClass().getResource("/suoni/chiave.wav");
		soundURL[2] = getClass().getResource("/suoni/porta.wav");
	}

	public void setFile(int i) {
		try {
			AudioInputStream sndtrck = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(sndtrck);
		} catch (Exception e) {
		}
	}

	public void play() {
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}
}
