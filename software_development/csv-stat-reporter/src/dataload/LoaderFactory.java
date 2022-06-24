package dataload;

import datamodel.MeasurementRecord;

public class LoaderFactory {
	
	public ILoader<MeasurementRecord> create(String loaderType) {
		if(loaderType.equals("hepc")) 
			return new HepcLoader();
		else 
			return null;
	}

}
