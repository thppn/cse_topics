package commands.replay;

import java.util.ArrayList;

public class ReplayManager {
	
	private ArrayList<Replayable> actionList;
	private boolean enabled;

	public ReplayManager() {

		actionList = new ArrayList<Replayable>();
		enabled = false;
	}
	
	public void addToList(Replayable action) {
		actionList.add(action);
	}
	
	public void replayAll() {
		for(Replayable action: actionList) {
			action.execute();
		}
		actionList.clear();
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void toggle() {
		enabled = !enabled;
	}

	public int getListSize() {
		return actionList.size();
	}
}
