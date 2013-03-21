package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;

public class OverviewTable extends JTable 
{
	
	private DefaultTableModel tableModel = null;
	
	public OverviewTable(Object[][] data, String[] columnNames)
	{
		this.tableModel = new DefaultTableModel(columnNames, 0);
		this.setModel(tableModel);
		setFillsViewportHeight(true);
		ViewEventController.getInstance().setOverviewTable(this);
	}
	
	public void updateTable(Requirement[] requirements) {
		tableModel.setRowCount(0);
		//for (int i = 0; i < requirements.length; i++) {
		for (int i = 0; i < requirements.length; i++) {
			if (!requirements[i].isDeleted()) {
				tableModel.addRow(new Object[]{ requirements[i].getId(), 
												requirements[i].getName(),
												requirements[i].getStatus(),
												requirements[i].getPriority(),
												requirements[i].getRelease() });
			}
		}
			
	}
	
}
