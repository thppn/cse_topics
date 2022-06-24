package texttospeechapis;

import com.sun.speech.freetts.*;



public class FreeTTSAdapter implements TextToSpeechAPI {

	private Voice voice;
	
	public FreeTTSAdapter() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		voice = VoiceManager.getInstance().getVoice("kevin16");
		voice.allocate();		
	}
	
	@Override
	public void play(String word) {
		voice.speak(word);
	}

	@Override
	public void setVolume(int n) {
		voice.setVolume(n);
	}

	@Override
	public void setPitch(int n) {
		voice.setPitch(n*20);
	}
	
	@Override
	public void setRate(int n) {
		voice.setRate(n*20);
		
	}
}
