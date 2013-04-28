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

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.icons.IterationIcon;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.icons.RequirementIcon;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
	Icon requirementIcon;
	Icon iterationIcon;

	public CustomTreeCellRenderer() {
		super();
		requirementIcon = new RequirementIcon();
		iterationIcon = new IterationIcon();
	}

	/**
	 * Method getTreeCellRendererComponent.
	 * 
	 * @param tree
	 *            JTree
	 * @param value
	 *            Object
	 * @param sel
	 *            boolean
	 * @param expanded
	 *            boolean
	 * @param leaf
	 *            boolean
	 * @param row
	 *            int
	 * @param hasFocus
	 *            boolean
	
	
	 * @return Component * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(JTree,
	 *      Object, boolean, boolean, boolean, int, boolean) */
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		if (node.getUserObject() instanceof Requirement) {
			setIcon(requirementIcon);
		} else {
			setIcon(iterationIcon);
			/*
			DefaultMutableTreeNode firstLeaf = ((DefaultMutableTreeNode) tree
					.getModel().getRoot());
			tree.setSelectionPath(new TreePath(firstLeaf.getPath()));
			String label = firstLeaf.toString(); //Does not work

			setToolTipText("" + value);*/
		}
		return this; 
	}
}
