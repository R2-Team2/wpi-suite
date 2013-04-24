/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import java.awt.Component;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;

/**
 * @author Raphael
 *
 */
public class OverviewTableIterationCellEditor extends DefaultCellEditor {
	
	/**
	 * @param comboBox
	 */
	public OverviewTableIterationCellEditor(JComboBox comboBox) {
		super(comboBox);		
	}
	@Override
	/**
	 * Returns the table cell editor so that the table can edit the cells text fields
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, final boolean isSelected, int row, int column) {
		// extract the cell's text field
		final JComboBox c = (JComboBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
		
		// Retrieve all Iterations
		List<Iteration> iterations = IterationModel.getInstance().getIterations();
		
		// get the requirement's iteration
		TableModel model = table.getModel();
		int modelRow = table.getRowSorter().convertRowIndexToModel(row);
		int columnRequirementPosition = 1;
		Requirement req = (Requirement) model.getValueAt(modelRow, columnRequirementPosition);
		String reqIterationName = req.getIteration();
		Iteration reqIteration = null;
		for (Iteration iteration : iterations) {
			if (iteration.getName() == reqIterationName) {
				reqIteration = iteration;
			}
		}
		
		// set the default selection to the requirement's iteration
		c.setSelectedItem(reqIteration);
		
		c.setVisible(true);
	
		return c;
	}
}
