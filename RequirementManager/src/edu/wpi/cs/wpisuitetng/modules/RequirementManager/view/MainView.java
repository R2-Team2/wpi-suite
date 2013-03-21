 package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview.OverviewPanel;

public class MainView extends JTabbedPane {

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
