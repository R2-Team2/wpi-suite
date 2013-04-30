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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
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
     * @param table JTable
     * @param value Object
     * @param isSelected boolean
     * @param hasFocus boolean
     * @param row int
     * @param column int
    
    
     * @return Component * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int) * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, 
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, 
                                                   int row, 
                                                   int column) {

        setBackground(Color.white);
        setForeground(Color.black);
        setText(value != null ? value.toString() : "");

        TableModel model = table.getModel();
        int modelRow = table.getRowSorter().convertRowIndexToModel(row);
        int columnRequirementPosition = 1;
        Requirement statusColumnValue = (Requirement) model.getValueAt(modelRow, columnRequirementPosition);

        ViewEventController.getInstance().getOverviewTable().validateEdits();

        if (!ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
        	// set deleted requirement backgrounds to gray if not in Multiple Requirement Editing Mode
        	if (statusColumnValue.getStatus() == RequirementStatus.DELETED)  setBackground(Color.LIGHT_GRAY);

        	else setBackground(Color.WHITE);
        	
        	setToolTipText(null);
        }

        // set deleted, in progress and complete foregrounds to gray and assign tool tips in Mult. Req. Editing Mode
        else {
        	if (statusColumnValue.getStatus() == RequirementStatus.DELETED) {
        		setForeground(Color.LIGHT_GRAY);
        		if (column == 7) setToolTipText("Cannot edit the Estimate of a Requirement that is Deleted.");
        		else setToolTipText(null);
        	}
        	if (statusColumnValue.getStatus() == RequirementStatus.COMPLETE) {
        		setForeground(Color.LIGHT_GRAY);
        		if (column == 7) setToolTipText("Cannot edit the Estimate of a Requirement that is Complete.");   
        		else setToolTipText(null);
        	}
        	if (statusColumnValue.getStatus() == RequirementStatus.INPROGRESS) {
        		setForeground(Color.LIGHT_GRAY);
        		if (column == 7) setToolTipText("Cannot edit the Estimate of a Requirement that is In Progress.");
        		else setToolTipText(null);
        	}
        	// make sure all the cells that should not have tool tips do not have tool tips 
        	if ((statusColumnValue.getStatus() == RequirementStatus.OPEN) || (statusColumnValue.getStatus() == RequirementStatus.NEW)){
        		setToolTipText(null);
        	}
        }

        
        
        Color selectedColor = new Color(176,226,255,255);
        if(isSelected) setBackground(selectedColor);
        
        // check to see if in multiple requirements editing mode
        if (ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
        	
        	// extract the ID number displayed in the row
        	String rowIDstr = model.getValueAt(modelRow, 0).toString();
        	int rowID = Integer.parseInt(rowIDstr);
        	// retrieve the requirement with ID rowID 
        	Requirement req = RequirementModel.getInstance().getRequirement(rowID);  
        	// retrieve the requirement's estimate
        	int reqEstimate = req.getEstimate();      		
        	
        	// extract the value of the cell, trimming beginning and ending spaces 
        	String cellEstimateStr = model.getValueAt(modelRow, 7).toString().trim();
        	boolean formatError = false;
        	int cellEstimate = 0;
        	// make sure the value in the cell is a valid integer
        	try {
        		cellEstimate = Integer.parseInt(cellEstimateStr);
        	}
        	// set formatError to true is there was an inability to parse and celleststr is not null
        	catch (NumberFormatException nfe){
        		if (!cellEstimateStr.isEmpty()) formatError = true;
        	}

        	if (formatError) {
        		// highlight the cell in red if there is an invalid entry and add a tool tip
        		if (column == 7) {
        			setBackground(Color.red);
        			setToolTipText("Estimate must be a valid, non-negative integer.");
        		}
        	}
        	else {
        		// set cell text to original estimate if the cell estimate was deleted
        		if ((cellEstimateStr.isEmpty()) && column == 7) {
        			cellEstimate = reqEstimate;
        			setValue(reqEstimate);
        		}
        		
        		// compare the estimate in the cell to the requirement's estimate
        		if (!(cellEstimate == reqEstimate) && column == 7) {
        			// highlight the cell in green if there is a change in the estimate
        			setBackground(Color.green);        		
        		}
        		// else, remove the highlight and tool tip if the estimate is returned to its initial value
        		if(	  (statusColumnValue.getStatus() != RequirementStatus.DELETED) 
        			&&(statusColumnValue.getStatus() != RequirementStatus.COMPLETE)
        			&&(statusColumnValue.getStatus() != RequirementStatus.INPROGRESS)) {            		
        		setToolTipText(null);
        		}
        	}
        }

        return this;
    }
}   
   
