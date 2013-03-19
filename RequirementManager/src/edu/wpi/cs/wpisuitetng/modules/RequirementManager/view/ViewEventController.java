package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements.NewRequirementPanel;

public class ViewEventController {
	private static ViewEventController instance = null;
	private MainView main = null;
	private ToolbarView tool = null;

	protected ViewEventController() {
		// TODO Auto-generated constructor stub
	}

	public static ViewEventController getInstance() {
		if (instance == null) {
			instance = new ViewEventController();
		}
		return instance;
	}

	public void setMainView(MainView main2) {
		main = main2;
	}

	public void setToolBar(ToolbarView tool2) {
		tool = tool2;
	}

	public void createRequirement() {
		NewRequirementPanel newReq = new NewRequirementPanel();
		main.addTab("Create Requirement", newReq);
		main.invalidate();
		main.repaint();
	}
}