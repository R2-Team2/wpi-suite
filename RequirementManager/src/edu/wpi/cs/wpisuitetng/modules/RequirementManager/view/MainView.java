 package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview.OverviewPanel;

/**
 * This class sets the main view when user goes to the RequirementManager tab
 * It also allows opened tabs to be closed by the user
 * 
 * @author Arianna
 *
 */
public class MainView extends JTabbedPane {

	/**
	 * Adds main subtab when user goes to RequirementManager
	 */
	public MainView() {
		OverviewPanel overview = new OverviewPanel();
		this.addTab("Overview", overview);
	}

	/**
	 * Overridden insertTab function to add the closable tab element.
	 */
	@Override
	public void insertTab(String title, Icon icon, Component component,
			String tip, int index) {
		super.insertTab(title, icon, component, tip, index);
		if (!(component instanceof OverviewPanel)) {
			setTabComponentAt(index, new ClosableTabComponent(this));
		}
	}
}
