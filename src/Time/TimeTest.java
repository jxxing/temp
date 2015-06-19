package Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Calendar ca = Calendar.getInstance();
		System.out.println(date.getTime());
		System.out.println(format.format(date.getTime()));
		System.out.println(format.format(ca.getTime()));
	}

}
