package timeaggregation;

public final class AggregatorFactory {
	
	public static IAggregator create(String aggregatorType) {
		return Aggregators.valueOf(aggregatorType.toUpperCase());
	}
}
