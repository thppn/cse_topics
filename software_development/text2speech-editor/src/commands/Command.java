package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Command implements ActionListener {

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		execute();
	}
	
	public abstract void execute();
	
}
