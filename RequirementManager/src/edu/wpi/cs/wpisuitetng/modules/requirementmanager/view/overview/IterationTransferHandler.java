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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class IterationTransferHandler extends TransferHandler {
	DataFlavor nodesFlavor;
	DataFlavor[] flavors = new DataFlavor[1];

	public IterationTransferHandler() {
		nodesFlavor = new DataFlavor(DefaultMutableTreeNode.class, "TreeNode");
		flavors[0] = nodesFlavor;
	}

	public boolean canImport(TransferHandler.TransferSupport support) {
		if(!support.isDrop()) {
			return false;
		}
		support.setShowDropLocation(true);
		if(!support.isDataFlavorSupported(nodesFlavor)) {
			return false;
		}
		
		//make sure the drop location is valid.
		JTree.DropLocation dl = (JTree.DropLocation)support.getDropLocation();
		JTree tree = (JTree)support.getComponent();
		
		int dropRow = tree.getRowForPath(dl.getPath());
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)dl.getPath().getLastPathComponent();
		Object nodeObject = node.getUserObject();
		
		//make sure the drop location is an iteration.
		if(!(nodeObject instanceof Iteration)) return false;
		
	    // Do not allow a drop on the drag source selections.
		int[] selRows = tree.getSelectionRows();
		for(int i = 0; i < selRows.length; i++) {
			if(selRows[i] == dropRow) {
				return false;
			}
		}
	
		return true;
	}

	protected Transferable createTransferable(JComponent c) {
		JTree tree = (JTree)c;
		TreePath path = tree.getSelectionPath();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
		
		boolean validRequirements = false;
		
		if(node.getUserObject() instanceof Requirement)
		{
			Requirement req = (Requirement)node.getUserObject();
			validRequirements = req.getEstimate() > 0;
		}
	
		if(path != null && validRequirements) {
			return new NodesTransferable(node);
		}
		
		return null;
	}

	public int getSourceActions(JComponent c) {
		return MOVE;
	}

	public boolean importData(TransferHandler.TransferSupport support) {
		if(!canImport(support)) {
			return false;
		}
		
		// Get drop location info.
		JTree.DropLocation dl = (JTree.DropLocation)support.getDropLocation();
		TreePath dest = dl.getPath();
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode)dest.getLastPathComponent();
		
		JTree tree = (JTree)support.getComponent();
		TreePath select = tree.getSelectionPath();
		DefaultMutableTreeNode child = (DefaultMutableTreeNode)select.getLastPathComponent();
		
		if(parent.getUserObject() instanceof Iteration)
		{
			Iteration newIteration = (Iteration)parent.getUserObject();
			
			if(child.getUserObject() instanceof Requirement)
			{
				Requirement req = (Requirement)child.getUserObject();
				req.setIteration(newIteration.getName());
				UpdateRequirementController.getInstance().updateRequirement(req);
				ViewEventController.getInstance().refreshTable();
				ViewEventController.getInstance().refreshTree();
				return true;
			}
		}
		
		return false;
	}

	public class NodesTransferable implements Transferable {
		DefaultMutableTreeNode node;

		public NodesTransferable(DefaultMutableTreeNode node) {
			this.node = node;
		}

		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException {
			if(!isDataFlavorSupported(flavor))
				throw new UnsupportedFlavorException(flavor);
			return node;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return flavors;
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return nodesFlavor.equals(flavor);
		}
	}
}