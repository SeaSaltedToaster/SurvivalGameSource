package seaSaltedEngine.audio;

import org.lwjgl.openal.AL10;

public class AudioSource {

	private int sourceID;
	public boolean isPlaying;
	  
	public AudioSource() {
		this.sourceID = AL10.alGenSources();
		AL10.alSourcef(this.sourceID, 4106, 1.0F);
		AL10.alSourcef(this.sourceID, 4099, 1.0F);
		AL10.alSourcef(this.sourceID, 4100, 1.0F);
	}
	  
	public void Play(int buffer) {
		this.isPlaying = true;
		AL10.alSourcei(this.sourceID, 4105, buffer);
		AL10.alSourcePlay(this.sourceID);
		this.isPlaying = false;
	}
	  
	public void delete() {
		AL10.alDeleteSources(this.sourceID);
	}

}
