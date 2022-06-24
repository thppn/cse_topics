package model;

import java.util.ArrayList;

import encodingstrategies.EncodingStrategy;
import texttospeechapis.TextToSpeechAPI;

public class Document {
	
	private static Document singletonDocument;

	private ArrayList<Line> contents;
	private String[] header;
	
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;

	private Document() {
		contents = new ArrayList<Line>();
	}
	
	public void playContents() {
		String output = "";
		for(int i=0;i < contents.size()-1;i++) {
			output += contents.get(i) + " ";
		}
		output += contents.get(contents.size()-1);
		audioManager.play(output);
	}
	
	public void playReverseContents() {
		for(int i = contents.size()-1;i >= 0;i--) {
			playReverseLine(i+1);
		}
	}
	
	public void playEncodedContents() {
		for(Line line: contents) {
			String encoded = encodingStrategy.encode(line.toString());
			audioManager.play(encoded);
		}
	}
	
	public void playLine(int number) {
		contents.get(number-1).playLine();
	}
	
	public void playReverseLine(int number) {
		contents.get(number-1).playReverseLine();
	}
	
	public void playEncodedLine(int number) {
		contents.get(number-1).playEncodedLine();
	}
	
	public void tuneEncodingStrategy(EncodingStrategy strategy) {
		this.encodingStrategy = strategy;
		for(Line l: contents) {
			l.tuneEncodingStrategy(strategy);
		}
	}
	
	public void tuneAudioManager(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
		for(Line l: contents) {
			l.tuneAudioManager(audioManager);
		}
	}
	
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	public String[] getHeader() {
		return header;
	}
	
	public void setDate(String date) {
		header[3] = date;
	}
	
	public void setContents(String[] contents) {
		this.contents.clear();
		for(String line: contents) {
			this.contents.add(new Line(line.split(" ")));
		}
		tuneAudioManager(audioManager);
		tuneEncodingStrategy(encodingStrategy);
	}
	
	public String toString() {
		String output = "";
		for(Line line:contents) {
			output+=line.toString() + "\n";
		}
		return output;
	}
	
	public EncodingStrategy getEncodingStrategy() {
		return encodingStrategy;
	}
	
	public TextToSpeechAPI getAudioManager() {
		return audioManager;
	}
	
	public static Document getInstance() {
		if(singletonDocument == null) {
			singletonDocument = new Document();
		}
		return singletonDocument;
	}
}
