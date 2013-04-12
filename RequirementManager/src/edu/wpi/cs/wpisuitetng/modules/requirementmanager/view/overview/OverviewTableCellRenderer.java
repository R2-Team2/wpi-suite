/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;

import java.awt.*;

public class OverviewTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * Constructor for the overviewtablecellrenderer.
	 * 
	 * This alternative renderer is used to repaint the tablecells when they are sorted.
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
        
        setText(value != null ? value.toString() : "");
        return this;
    }
}