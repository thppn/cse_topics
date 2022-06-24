package datamodel;

import java.time.LocalDateTime;

public class TimeRecord {

	private LocalDateTime time;
	
	public TimeRecord(LocalDateTime time) {
		this.time = time;
	}
	
	public String getDayOfWeek() {
		return "0"+Integer.toString(time.getDayOfWeek().getValue());}
	
	public String getMonth() {
		int month = time.getMonthValue();
		if(month < 10) {
			return "0"+String.valueOf(month);
		}
		return String.valueOf(time.getMonthValue());}
	
	public String getSeason() {
		switch(getMonth()) {
		case"0":
		case"1":
		case"11":
			return "WINTER";
		case"2":
		case"3":
		case"4":
			return "SPRING";
		case"5":
		case"6":
		case"8":
			return "SUMMER";
		default:
			return "AUTUMN";
		}
	}
	public String getPeriodOfDay() {
		switch(time.getHour()) {
		case 5:
		case 6:
		case 7:
		case 8:
			return "EARLY_MORNING";
		case 9:
		case 10:
		case 11:
		case 12:
			return "MORNING";
		case 13:
		case 14:
		case 15:
		case 16:
			return "AFTERNOON";
		case 17:
		case 18:
		case 19:
		case 20:
			return "EVENING";
		default:
			return "NIGHT";
		}
	}
}
