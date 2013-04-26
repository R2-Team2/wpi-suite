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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;


/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class OverviewTreePanel extends JScrollPane implements MouseListener{

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
  

        
        this.setViewportView(tree);
        ViewEventController.getInstance().setOverviewTree(this);

		this.refresh();

        
        System.out.println("finished constructing the tree");
	}
	
	/**
	 * Method valueChanged.
	
	
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(TreeSelectionEvent) *//*
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
	}*/
	
	/**
	 * This will wipe out the current tree and rebuild it
	 */
	public void refresh(){
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("BEHOLD THE TREE"); //makes a starting node
		List<Iteration> iterations = IterationModel.getInstance().getIterations(); //retreive the list of all iterations
		System.out.println("Num Iterations: " + iterations.size());
		for(int i=0; i<iterations.size(); i++){

			DefaultMutableTreeNode newIterNode = new DefaultMutableTreeNode(iterations.get(i)); //make a new iteration node to add
			List<Requirement> requirements = iterations.get(i).getRequirements(); //gets the list of requirements that is associated with the iteration

			//check to see if there are any requirements for the iteration
			if(requirements != null){
				//if so make a node for each one and add it to the iteration's node
				for(int j=0; j<requirements.size(); j++){
					DefaultMutableTreeNode newReqNode = new DefaultMutableTreeNode(requirements.get(j));
					List<Requirement> children = requirements.get(j).getChildren();

					//check to see if there are children for this requirement
					if(children != null){
						//if so make a node for each child
						for(int k=0; k<children.size(); k++){
							DefaultMutableTreeNode newChildNode = new DefaultMutableTreeNode(children.get(k));
							newReqNode.add(newChildNode);
						}
							newIterNode.add(newReqNode);
					}
				}
			}

			top.add(newIterNode); //add the iteration's node to the top node
		}

        tree = new JTree(top); //create the tree with the top node as the top
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION); //tell it that it can only select one thing at a time
        tree.setToggleClickCount(0);
 
        tree.setCellRenderer(new CustomTreeCellRenderer()); //set to custom cell renderer so that icons make sense
        tree.addMouseListener(this); //add a listener to check for clicking
        this.setViewportView(tree); //make panel display the tree
        
        ViewEventController.getInstance().setOverviewTree(this); //update the ViewEventControler so it contains the right tree

        System.out.println("finished refreshing the tree");
	}
	
	/**
	 * Method mouseClicked.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mouseClicked(MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		if(node.getUserObject() instanceof Iteration)
		{
			ViewEventController.getInstance().getIterationOverview().highlight((Iteration)node.getUserObject());
		}
		if (e.getClickCount() == 2)
		{
			TreePath treePath = tree.getPathForLocation(e.getX(), e.getY());
			Object[] path = null;
			if(treePath != null)
				path = treePath.getPath();
			
			if ((path != null) && (path.length == 2)) ViewEventController.getInstance().editSelectedIteration();
			else if ((path != null) && (path.length > 2)) {
				Requirement req = ((Requirement)((DefaultMutableTreeNode)tree.getLastSelectedPathComponent()).getUserObject());
				ViewEventController.getInstance().editRequirement(req);
			}
		}
/*
		    if (SwingUtilities.isRightMouseButton(e)) {

		        int row = tree.getClosestRowForLocation(e.getX(), e.getY());
		        tree.setSelectionRow(row);
		        String label = "popup: ";
		        JPopupMenu popup = new JPopupMenu();
				popup.add(new JMenuItem(label));
				popup.show(tree, x, y);
		    }
		    */
	}

	/**
	 * Method mousePressed.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {		
	}

	/**
	 * Method mouseReleased.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mouseReleased(MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {		
	}

	/**
	 * Method mouseEntered.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mouseEntered(MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Method mouseExited.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mouseExited(MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {		
	}
	/**
	
	 * @return the tree */
	public JTree getTree() {
		return tree;
	}
}
