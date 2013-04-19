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

import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

import java.awt.*;

public class OverviewTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * Constructor for the overviewtablecellrenderer.
	 * 
	 * This alternative renderer is used to repaint the table cells when they are sorted.
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
        
        // check to see if in multiple requirements editing mode
        if (ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
        	// extract the ID number displayed in the row
        	String rowIDstr = model.getValueAt(row, 0).toString();
        	int rowID = Integer.parseInt(rowIDstr);
        	// retrieve the requirement with ID rowID and the requirement's estimate 
        	Requirement req = RequirementModel.getInstance().getRequirement(rowID);
        	int reqEstimate = req.getEstimate();
        	// extract the value of the cell 
        	String cellEstimateStr = model.getValueAt(row, 7).toString();
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
        		System.out.println("Invalid entry into cell");
        	}
        	else {
        		// compare the estimate in the cell to the requirement's estimate
        		if (!(cellEstimate == reqEstimate)) {
        			//Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        			//cellComponent.
        			// highlight row in green if there is a change in the estimate
        			setBackground(Color.green);
        			//return cellComponent;
        		}
        		// remove the highlight if the estimate is returned to its initial value
        		else setBackground(Color.white);
        	}
        }
                
        Color selectedColor = new Color(176,226,255,255);
        if(isSelected) setBackground(selectedColor);
        
        setText(value != null ? value.toString() : "");
        return this;
    }
}   
   