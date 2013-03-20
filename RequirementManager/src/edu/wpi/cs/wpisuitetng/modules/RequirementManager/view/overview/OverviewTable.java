package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview;

import javax.swing.JTable;

public class OverviewTable extends JTable 
{
	
	public OverviewTable(Object[][] data, String[] columnNames)
	{
		super(data, columnNames);
		
		setFillsViewportHeight(true);
	}
	
}
