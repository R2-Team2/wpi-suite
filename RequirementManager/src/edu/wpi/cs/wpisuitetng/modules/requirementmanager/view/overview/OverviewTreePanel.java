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

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 */
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
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
 
        tree.addTreeSelectionListener(this);
        
        this.setViewportView(tree);
        
        ViewEventController.getInstance().setOverviewTree(this);
        
        System.out.println("finished constructing the tree");
	}
	
	/**
	 * Method valueChanged.
	 * @param e TreeSelectionEvent
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
	}
	
	public void refresh(){

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("BEHOLD THE TREE");
		List<Iteration> iterations = IterationModel.getInstance().getIterations();

		for(int i=0; i<iterations.size(); i++){
			DefaultMutableTreeNode newIterNode = new DefaultMutableTreeNode(iterations.get(i));
			List<Requirement> requirements = iterations.get(i).getRequirements();

			if(requirements != null){
				for(int j=0; j<requirements.size(); j++){
					DefaultMutableTreeNode newReqNode = new DefaultMutableTreeNode(requirements.get(j));
					newIterNode.add(newReqNode);
					System.out.println("added a requirement to the tree");
				}
			}

			top.add(newIterNode);
		}

        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
 
        tree.addTreeSelectionListener(this);
        this.setViewportView(tree);
        
        System.out.println("finished refreshing the tree");
	}
}
