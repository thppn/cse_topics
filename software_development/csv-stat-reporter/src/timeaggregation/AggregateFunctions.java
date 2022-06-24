package timeaggregation;

import datamodel.IResult;
import datamodel.MeasurementRecord;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public enum AggregateFunctions {
	
	SUM {public double getFunc(DescriptiveStatistics stat)
		{return stat.getSum();}},
	
	AVG {public double getFunc(DescriptiveStatistics stat)
		{return stat.getMean();}};
	
	public abstract double getFunc(DescriptiveStatistics stat);

	public IResult aggregate(IResult aggResult) {
		HashMap<String,ArrayList<MeasurementRecord>> results = aggResult.getDetailedResults();
		for(String timeUnit : results.keySet()) {
			getStatistics(results.get(timeUnit));
			
			aggResult.getAggregateMeterKitchen().put(timeUnit, getFunc(kitchen));
			aggResult.getAggregateMeterLaundry().put(timeUnit, getFunc(laundry));
			aggResult.getAggregateMeterAC().put(timeUnit, getFunc(ac));
		}
		return aggResult;
	}
	
	DescriptiveStatistics kitchen, laundry, ac;
	
	public void getStatistics(ArrayList<MeasurementRecord> timeUnitRecs) {
		
		kitchen = new DescriptiveStatistics();
		laundry = new DescriptiveStatistics();
		ac = new DescriptiveStatistics();
		
		for(MeasurementRecord rec: timeUnitRecs) {
		
			kitchen.addValue(rec.getKitchen());
			laundry.addValue(rec.getLaundry());
			ac.addValue(rec.getAC());
		}
		
	}
}
