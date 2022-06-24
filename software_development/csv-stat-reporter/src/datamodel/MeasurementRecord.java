package datamodel;

import java.time.LocalDateTime;

public class MeasurementRecord extends TimeRecord{

	private double kitchen, laundry, ac;
	
	public MeasurementRecord(LocalDateTime time, double kitchen, double laundry, double ac) {
		super(time);
		this.kitchen = kitchen;
		this.laundry = laundry;
		this.ac = ac;
	}
	public double getKitchen() {
		return kitchen;
	}
	public double getLaundry() {
		return laundry;
	}
	public double getAC() {
		return ac;
	}
	


}
