import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; // Changed SeLf to self
	private java.util.Calendar calendar; // Changed calendar to calendar
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();
	}
	
	public static Calendar instance() { // INSTANCE to instance
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) { // Set_dATE to setDate
		try {
			calendar.setTime(date);
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date date() { // Date to date
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date dueDate(int loanPeriod){  // Due_Date to dueDate
		Date now = Date(); // NoW to now
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = calendar.getTime(); // DuEdAtE to dueDate
		calendar.setTime(now);
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) { // Get_Days_Difference to getDaysDifference
		
		long diffMillis = Date().getTime() - targetDate.getTime(); // Diff_Millis to diffMillis
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS); // Diff_Days to diffDays
	    return diffDays;
	}

}
