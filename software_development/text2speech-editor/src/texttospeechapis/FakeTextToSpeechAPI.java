package texttospeechapis;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {

	private String word = "";
	
	private int volume;
	private int pitch;
	private int rate;
	
	@Override
	public void play(String word) {
		this.word += " " + word;
	}

	@Override
	public void setVolume(int n) {
		volume = n;
	}

	@Override
	public void setPitch(int n) {
		pitch = n;
	}

	@Override
	public void setRate(int n) {
		rate = n;
	}
	
	public String getWord() {
		return word.substring(1);
	}
	
	public int getVolume() {
		return volume;
	}
	
	public int getPitch() {
		return pitch;
	}
	
	public int getRate() {
		return rate;
	}
}
