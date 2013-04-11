package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverviewFilterPanel extends JPanel {
	
	/**
	 * Sets up the left hand panel of the overview
	 */
	public OverviewFilterPanel()
	{	
		JLabel filterPanel = new JLabel ("Filtering Options Go Here");
		
		this.add(filterPanel);
	}
}
