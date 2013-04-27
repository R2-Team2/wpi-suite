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


import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;

import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class OverviewTableTransferHandler extends TransferHandler {
	private final DataFlavor localObjectFlavor;
	private JTable table;

	public OverviewTableTransferHandler(JTable table) {
		localObjectFlavor = new DataFlavor(Requirement.class, "Requirement");
		this.table = table;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		assert (c == table);
		Requirement req = (Requirement)table.getValueAt(table.getSelectedRow(), 1);
		
		if(req.getEstimate() > 0 && req.getStatus() != RequirementStatus.COMPLETE && req.getStatus() != RequirementStatus.DELETED)
		{
			return new DataHandler(table.getValueAt(table.getSelectedRow(), 1), localObjectFlavor.getMimeType());
		}
		
		return null;	
	}

	@Override
	public boolean canImport(TransferHandler.TransferSupport info) {
		table.setCursor(DragSource.DefaultMoveNoDrop);
		return false;
	}

	@Override
	public int getSourceActions(JComponent c) {
		return TransferHandler.MOVE;
	}

	@Override
	public boolean importData(TransferHandler.TransferSupport info) {
		return false;
	}

	@Override
	protected void exportDone(JComponent c, Transferable t, int act) {
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

}