package view;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.CommandsFactory;
import model.Document;

public class Text2SpeechEditorView {

	private static Text2SpeechEditorView singletonView;

	private static Document currentDocument;
	private CommandsFactory factory;
	private static JTextArea textArea;
	private static JFrame mainFrame;
	private static JLabel replayStatus;
	private static JLabel imgLabel;
	
	private static JPanel headerPanel;

	private Text2SpeechEditorView() {
		mainFrame = new JFrame("Text2SpeechEditor");
		factory = new CommandsFactory();
		currentDocument = Document.getInstance();
		replayStatus = new JLabel();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createMenu("Document",new String[] {"New Document","Open Document","Edit Document","Save Document"}));
		menuBar.add(createMenu("Text2Speech",new String[] {"Document to Speech","Line to Speech"}));
		menuBar.add(createMenu("Tune",new String[] {"Tune Audio","Tune Encoding"}));
		menuBar.add(createMenu("Replay",new String[] {"Replay Command"}));
		
		menuBar.add(replayStatus);
		replayStatus.setVisible(false);
		
		imgLabel = new JLabel(new ImageIcon("resources/full-logo.png"));
		mainFrame.setIconImage(new ImageIcon("resources/logo.png").getImage());
		
		textArea = new JTextArea();
		textArea.setVisible(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.add(imgLabel, BorderLayout.NORTH);
		mainFrame.add(textArea, BorderLayout.CENTER);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setSize(500, 250);
		mainFrame.setVisible(true);
	}
	
	private JMenu createMenu(String title, String[] items) {
		JMenu menu = new JMenu(title);
		for(String item: items) {
			menu.add(createMenuItem(item));
		}
		return menu;
	}
	
	private JMenuItem createMenuItem(String title) {
		String command = title.toLowerCase().replace(" ", "_");
		JMenuItem item = new JMenuItem(title);
		item.addActionListener(factory.createCommand(command));
		return item;
	}

	public boolean isReversed() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to play it on reverse?", "Reverse", JOptionPane.YES_NO_OPTION);
		if(dialogResult == 0) {
		  return true;
		}
		return false; 
	}

	public boolean isEncoded() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to play it encoded?", "Encode", JOptionPane.YES_NO_OPTION);
		if(dialogResult == 0) {
		  return true;
		}
		return false; 
	}

	public int getVolume() {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 1));
		JOptionPane.showMessageDialog(null, spinner,"Set volume",JOptionPane.QUESTION_MESSAGE);
		return (int) spinner.getValue();
	}

	public int getRate() {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 1));
		JOptionPane.showMessageDialog(null, spinner,"Set rate",JOptionPane.QUESTION_MESSAGE);
		return (int) spinner.getValue();
	}

	public int getPitch() {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 1));
		JOptionPane.showMessageDialog(null, spinner,"Set pitch",JOptionPane.QUESTION_MESSAGE);
		return (int) spinner.getValue();
	}
	
	public String getEncoding() {
		JSpinner spinner = new JSpinner(new SpinnerListModel(new String[]{"AtBash","Rot13"}));
		JOptionPane.showMessageDialog(null, spinner,"Set encoding",JOptionPane.QUESTION_MESSAGE);
		return (String) spinner.getValue();
	}
	
	public String getAuthor() {
		return JOptionPane.showInputDialog("Author: ");
	}
	
	public String getTitle() {
		return JOptionPane.showInputDialog("Title: ");
	}
	
	public int getLine() {
		return Integer.parseInt(JOptionPane.showInputDialog("Line number: "));
	}
	
	public void setHeader() {
		String[] header = currentDocument.getHeader();
		headerPanel = new JPanel();
		for(String item: header) {
			headerPanel.add(new JLabel(item));
		}
		mainFrame.add(headerPanel, BorderLayout.SOUTH);
		headerPanel.revalidate();
	}

	public Document getCurrentDocument() {
		currentDocument.setContents(textArea.getText().split("\n"));
		return currentDocument;
	}
	
	public void setCurrentDocument() { 
		imgLabel.setVisible(false);
		currentDocument = Document.getInstance();
		textArea.setText(currentDocument.toString());
		textArea.repaint();
		textArea.setVisible(true);
	}

	public void setText(String cont) {
		textArea.setText(cont);
	}
	
	public String getOpenPath() {
		JFileChooser j = new JFileChooser("files");
		j.setAcceptAllFileFilterUsed(false);
		j.addChoosableFileFilter(new FileNameExtensionFilter("TTS file", "tts"));
		j.showOpenDialog(null);
		return j.getSelectedFile().getAbsolutePath();
	}
	
	public String getSavePath() {
		JFileChooser j = new JFileChooser("files");
		j.showSaveDialog(null);
		return j.getSelectedFile().getAbsolutePath();
	}
	
	public void setReplayStatus(boolean enable) {
		replayStatus.setVisible(true);
		replayStatus.setText(enable?"Not recording":"Recording");
		replayStatus.repaint();
	}

	public static Text2SpeechEditorView getSingletonView() {
		if (singletonView == null) {
			singletonView = new Text2SpeechEditorView();
		}
		return singletonView;
	}

	public static void main(String[] arg) {
		getSingletonView();
	}
}