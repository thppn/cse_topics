package datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Result implements IResult {

	private HashMap<String, ArrayList<MeasurementRecord>> results;
	
	private HashMap<String, Double> kitchen;
	private HashMap<String, Double> laundry;
	private HashMap<String, Double> ac;
	
	private String aggFunction;
	private String description;
	
	public Result(String aggFunction, String description) {
		
		this.aggFunction = aggFunction;
		this.description = description;
		
		results = new HashMap<String,ArrayList<MeasurementRecord>>();
		kitchen = new HashMap<String, Double>();
		laundry = new HashMap<String, Double>();
		ac = new HashMap<String, Double>();
	}
	
	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		ArrayList<MeasurementRecord> timeUnitCollection = results.get(timeUnit);
		if(timeUnitCollection == null) {
			timeUnitCollection = new ArrayList<MeasurementRecord>();
			results.put(timeUnit,timeUnitCollection);
		}
		timeUnitCollection.add(record);
		return timeUnitCollection.size();
	}
	@Override
	public String getDescription() {return description;}
	
	@Override
	public HashMap<String, ArrayList<MeasurementRecord>> getDetailedResults() {return results;}

	@Override
	public HashMap<String, Double> getAggregateMeterKitchen() {return kitchen;}

	@Override
	public HashMap<String, Double> getAggregateMeterLaundry() {return laundry;}

	@Override
	public HashMap<String, Double> getAggregateMeterAC() {return ac;}

	@Override
	public String getAggregateFunction() {return aggFunction;}
}
