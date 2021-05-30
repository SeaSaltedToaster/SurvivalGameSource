package seaSaltedEngine.audio.management;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;

public class AudioMaster {

	private static List<Integer> buffers = new ArrayList<>();
	  
	  private static long device;
	  
	  public static void init() {
		  device = alcOpenDevice((ByteBuffer) null);
	      if (device == NULL) {
	            throw new IllegalStateException("Failed to open the default OpenAL device.");
	      }
	        ALCCapabilities deviceCaps = ALC.createCapabilities(device);
	        long context = alcCreateContext(device, (IntBuffer) null);
	        if (context == NULL) {
	            throw new   IllegalStateException("Failed to create OpenAL context.");
	        }
	        ALC10.alcMakeContextCurrent(context);
	        AL.createCapabilities(deviceCaps);
	    
	    getListenerData();
	  }
	  
	  public static void getListenerData() {
		  AL10.alListener3f(4100, 0.0F, 0.0F, 0.0F);
		  AL10.alListener3f(4102, 0.0F, 0.0F, 0.0F);
	  }
	  
	  public static int loadSound(String file) {
	    int buffer = AL10.alGenBuffers();
	    buffers.add(Integer.valueOf(buffer));
	    WaveData waveFile = WaveData.create("res/audio/" + file + ".wav");
	    AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
	    waveFile.dispose();
	    return buffer;
	  }
	  
	  public static void cleanUp() {
	    for (Iterator<Integer> iterator = buffers.iterator(); iterator.hasNext(); ) {
	      int buffer = ((Integer)iterator.next()).intValue();
	      AL10.alDeleteBuffers(buffer);
	    } 
	  }
	
	
}
