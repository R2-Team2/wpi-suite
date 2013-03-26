package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;

public class OverviewTable extends JTable 
{
	private DefaultTableModel tableModel = null;
	
	/**
	 * Sets initial table view
	 * 
	 * @param data	Initial data to fill OverviewTable
	 * @param columnNames	Column headers of OverviewTable
	 */
	public OverviewTable(Object[][] data, String[] columnNames)
	{
		this.tableModel = new DefaultTableModel(columnNames, 0);
		this.setModel(tableModel);
		this.getTableHeader().setReorderingAllowed(false);
		this.setAutoCreateRowSorter(true);
		setFillsViewportHeight(true);
		ViewEventController.getInstance().setOverviewTable(this);
	}
	
	/**
	 * updates OverviewTable as requirements are added
	 * 
	 * @param requirements	Elements of new requirements added
	 */
	public void updateTable(Requirement[] requirements) {
		tableModel.setRowCount(0);
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
	
	/**
	 * Overrides isCellEditable to ensure that no cells are editable.
	 */
	@Override
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}
	
}
