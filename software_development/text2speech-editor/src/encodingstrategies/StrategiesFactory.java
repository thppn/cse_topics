package encodingstrategies;

public class StrategiesFactory {
	
	public static EncodingStrategy create(String strategy) {
		switch(strategy) {
		case "Rot13":
			return new Rot13Encoding();
		case "AtBash":
			return new AtBashEncoding();
		default:
			return null;
		}
	}

}
