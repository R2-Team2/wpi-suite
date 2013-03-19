package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview.OverviewPanel;

public class MainView extends JTabbedPane {

	public MainView()
	{
		OverviewPanel overview = new OverviewPanel();
		this.addTab("Overview", overview);
	}
}
