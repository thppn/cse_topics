package texttospeechapis;

public interface TextToSpeechAPI {
	
	public void play(String word);
	
	public void setVolume(int n);
	
	public void setPitch(int n);
	
	public void setRate(int n);
}
