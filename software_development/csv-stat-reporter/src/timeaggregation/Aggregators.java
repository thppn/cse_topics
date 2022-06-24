package timeaggregation;

import java.util.ArrayList;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;

public enum Aggregators implements IAggregator {
		
	MONTH {String getTimeUnit(MeasurementRecord rec) 
		{return rec.getMonth();}},
	
	DAYOFWEEK {String getTimeUnit(MeasurementRecord rec)
		{return rec.getDayOfWeek();}},
	
	SEASON {String getTimeUnit(MeasurementRecord rec)
		{return rec.getSeason();}},
	
	PERIODOFDAY {String getTimeUnit(MeasurementRecord rec) 
		{return rec.getPeriodOfDay();}};
	
	abstract String getTimeUnit(MeasurementRecord rec);
	
	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		IResult result = new Result(aggFunction, description);
		for(MeasurementRecord rec : inputMeasurements) {result.add(getTimeUnit(rec), rec);}
		return AggregateFunctions.valueOf(aggFunction.toUpperCase()).aggregate(result);
	}

	@Override
	public String getTimeUnitType() {return name();}

}
