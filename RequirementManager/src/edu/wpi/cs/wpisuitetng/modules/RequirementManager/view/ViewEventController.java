package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements.NewRequirementPanel;

public class ViewEventController {
	private static ViewEventController instance = null;
	private MainView main = null;
	private ToolbarView tool = null;

	/**
	 * Default constructor for ViewEventController.  Is protected to prevent instantiation.
	 */
	protected ViewEventController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the singleton instance of the vieweventcontroller.
	 * @return The instance of this controller.
	 */
	public static ViewEventController getInstance() {
		if (instance == null) {
			instance = new ViewEventController();
		}
		return instance;
	}

	/**
	 * Sets the main view to the given view.
	 * @param main2 the main view to be set as active.
	 */
	public void setMainView(MainView main2) {
		main = main2;
	}

	/**
	 * 
	 * Sets the toolbarview to the given toolbar
	 * @param tool2 the toolbar to be set as active.
	 */
	public void setToolBar(ToolbarView tool2) {
		tool = tool2;
	}

	/**
	 * Opens a new tab for the creation of a requirement.
	 */
	public void createRequirement() {
		NewRequirementPanel newReq = new NewRequirementPanel();
		main.addTab("Create Requirement", newReq);
		main.invalidate(); //force the tabbedpane to redraw.
		main.repaint();
	}
}