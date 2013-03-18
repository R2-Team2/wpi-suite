package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import javax.swing.JTabbedPane;

public class MainView extends JTabbedPane {

	public MainView()
	{
		OverviewPanel overview = new OverviewPanel();
		this.addTab("Overview", overview);
	}
}
