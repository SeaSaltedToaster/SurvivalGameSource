package seaSaltedEngine.tools;

public class ByteUtils {

	public static byte[] floatToByteArray(float value) {
	    int intBits =  Float.floatToIntBits(value);
	    return new byte[] {
	      (byte) (intBits >> 24), (byte) (intBits >> 16), (byte) (intBits >> 8), (byte) (intBits) };
	}
	
}
