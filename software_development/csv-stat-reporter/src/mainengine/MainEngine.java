package mainengine;

import java.util.ArrayList;

import dataload.ILoader;
import dataload.LoaderFactory;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import reporting.IResultReporter;
import reporting.ResultReporterFactory;
import timeaggregation.IAggregator;
import timeaggregation.AggregatorFactory;

public class MainEngine implements IMainEngine{

	@Override
	public int loadData(String fileName, String delimeter, Boolean hasHeaderLine, int numFields,
			ArrayList<MeasurementRecord> objCollection) {
		LoaderFactory factory = new LoaderFactory();
		ILoader<MeasurementRecord> loader = factory.create("hepc");
		return loader.load(fileName, delimeter, hasHeaderLine, numFields, objCollection);
	}

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggregatorType,
			String aggFunction, String description) {
		IAggregator aggregator = AggregatorFactory.create(aggregatorType);
		return aggregator.aggregateByTimeUnit(inputMeasurements, aggFunction, description);
	}

	@Override
	public int reportResultInFile(IResult result, String reportType, String filename) {
		
		IResultReporter reporter = ResultReporterFactory.create(reportType);
		
		return reporter.reportResultInFile(result, filename);
	}
	


}
