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

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;

public class OverviewTreePanel extends JScrollPane implements TreeSelectionListener{
	private JTree tree;
	/**
	 * Sets up the left hand panel of the overview
	 */
	public OverviewTreePanel()
	{	
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("BEHOLD THE TREE");
		List<Iteration> iterations = IterationModel.getInstance().getIterations();
		for(int i=0; i<iterations.size(); i++){
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(iterations.get(i).getName());
			top.add(newNode);
		}
        
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
 
        tree.addTreeSelectionListener(this);
        this.setViewportView(tree);
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
