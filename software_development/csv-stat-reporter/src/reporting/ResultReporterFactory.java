package reporting;

public final class ResultReporterFactory {
	
	public static IResultReporter create(String resultType) {
		return ResultReporterGenerator.valueOf(resultType.toUpperCase()).getType();
	}
}
