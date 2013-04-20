/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class OverviewTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * Constructor for the overviewtablecellrenderer.
	 * 
	 * This alternative renderer is used to repaint the table cells when they are sorted
	 * and paint cells for Multiple Requirement editing mode
	 */
    public OverviewTableCellRenderer() {
        super();
        setOpaque(true);
        this.setFont(new Font(this.getFont().getName(), Font.PLAIN, this.getFont().getSize()));
    }

    /**
     * Returns the table cell renderer so that the table can draw it.
     * 
     */
    public Component getTableCellRendererComponent(JTable table, 
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, 
                                                   int row, 
                                                   int column) {

        setBackground(Color.white);
        setForeground(Color.black);

        TableModel model = table.getModel();
        int modelRow = table.getRowSorter().convertRowIndexToModel(row);
        int columnRequirementPosition = 1;
        Requirement statusColumnValue = (Requirement) model.getValueAt(modelRow, columnRequirementPosition);

        if (statusColumnValue.getStatus() == RequirementStatus.DELETED) {
            setBackground(Color.LIGHT_GRAY);
        }
        else
        {
            setBackground(Color.WHITE);
        }
        
        Color selectedColor = new Color(176,226,255,255);
        if(isSelected) setBackground(selectedColor);
        
        // check to see if in multiple requirements editing mode
        if (ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
        	// extract the ID number displayed in the row
        	String rowIDstr = model.getValueAt(modelRow, 0).toString();
        	int rowID = Integer.parseInt(rowIDstr);
        	// retrieve the requirement with ID rowID and the requirement's estimate 
        	Requirement req = RequirementModel.getInstance().getRequirement(rowID);
        	int reqEstimate = req.getEstimate();
        	// extract the value of the cell 
        	String cellEstimateStr = model.getValueAt(modelRow, 7).toString();
        	boolean formatError = false;
        	int cellEstimate = 0;
        	// make sure the value in the cell is a valid integer
        	try {
        		cellEstimate = Integer.parseInt(cellEstimateStr);
        	}
        	catch (NumberFormatException nfe){
        		formatError = true;
        	}

        	if (formatError) {
        		// highlight the cell in red if there is an invalid entry and add a tool tip
        		if (column == 7) {
        			setBackground(Color.red);	 
        			setToolTipText("Estimate must be an integer. This value will be ignored if changes are saved.");
        		}
        	}
        	else {
        		// compare the estimate in the cell to the requirement's estimate
        		if (!(cellEstimate == reqEstimate) && column == 7) {
        			// highlight the cell in green if there is a change in the estimate
        			setBackground(Color.green);        		
        		}
        		// else, remove the highlight and tool tip if the estimate is returned to its initial value
        		setToolTipText(null);
        	}
        }
        
        setText(value != null ? value.toString() : "");
        return this;
    }
}   
   
