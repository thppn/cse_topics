package model;

import java.util.ArrayList;
import java.util.Arrays;

import encodingstrategies.EncodingStrategy;
import texttospeechapis.TextToSpeechAPI;

public class Line {
	
	private ArrayList<String> words;
	
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	
	public Line(String[] arr) {
		words = new ArrayList<String>(Arrays.asList(arr));
		//words.add("\n");
	}

	public void playLine() {
		
		audioManager.play(toStringSpeech());
		
	}
	
	public void playReverseLine() {
		String output = "";
		for(int i = words.size()-1;i >= 1;i--) {
			output += words.get(i) + " ";
		}
		output+=words.get(0);
		
		audioManager.play(output);
		
	}
	
	public void playEncodedLine() {
		String t = encodingStrategy.encode(toStringSpeech());
		audioManager.play(t);
	}
	
	public void tuneEncodingStrategy(EncodingStrategy strategy) {
		this.encodingStrategy = strategy;
	}
	
	public void tuneAudioManager(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
	}
	
	public String toString() {
		return String.join(" ", words);
	}

	public String toStringSpeech() {
		String output = "";
		for(int i =0 ; i< words.size()-1; i++) {
			
			output += words.get(i) + " ";
		}
		output+= words.get(words.size()-1);
		return output;
	}
}
