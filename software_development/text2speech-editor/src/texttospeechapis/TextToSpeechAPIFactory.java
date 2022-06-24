package texttospeechapis;

public class TextToSpeechAPIFactory {
	
	public static TextToSpeechAPI create(String api) {
		switch(api) {
		case "FreeTTS":
			return new FreeTTSAdapter();
		case "FakeTTS":
			return new FakeTextToSpeechAPI();
		default:
			return null;
		}
	}
}
