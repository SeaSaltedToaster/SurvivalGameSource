package seaSaltedEngine.basic.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	public static String getRealTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
}
