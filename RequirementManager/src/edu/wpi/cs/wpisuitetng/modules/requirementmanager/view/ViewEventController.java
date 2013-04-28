/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations.IterationOverviewPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations.IterationPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTreePanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewBarChartPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewPieChartPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;


/**
 * Provides an interface for interaction with the main GUI elements
 * All actions on GUI elements should be conducted through this controller.
 * @version $Revision: 1.0 $
 * @author justinhess
 */

public class ViewEventController {
	private static ViewEventController instance = null;
	private MainView main = null;
	private ToolbarView toolbar = null;
	private OverviewTable overviewTable = null;
	private OverviewTreePanel overviewTree = null;
	private ArrayList<RequirementPanel> listOfEditingPanels = new ArrayList<RequirementPanel>();
	private ArrayList<IterationPanel> listOfIterationPanels = new ArrayList<IterationPanel>();
	private IterationOverviewPanel iterationOverview;
	
	/**
	 * Sets the OverviewTable for the controller
	 * @param overviewTable a given OverviewTable
	 */
	public void setOverviewTable(OverviewTable overviewTable) {
		this.overviewTable = overviewTable;
	}

	/**
	 * Default constructor for ViewEventController.  Is protected to prevent instantiation.
	 */
	private ViewEventController() {}

	/**
	 * Returns the singleton instance of the vieweventcontroller.
	
	 * @return The instance of this controller. */
	public static ViewEventController getInstance() {
		if (instance == null) {
			instance = new ViewEventController();
		}
		return instance;
	}

	/**
	 * Sets the main view to the given view.
	
	 * @param mainview MainView
	 */
	public void setMainView(MainView mainview) {
		main = mainview;
	}

	/**
	 * Sets the toolbarview to the given toolbar
	 * @param tb the toolbar to be set as active.
	 */
	public void setToolBar(ToolbarView tb) {
		toolbar = tb;
		toolbar.repaint();
	}

	/**
	 * Opens a new tab for the creation of a requirement.
	 */
	public void createRequirement() {
		RequirementPanel newReq = new RequirementPanel(-1);
		main.addTab("New Req.", null, newReq, "New Requirement");
		main.invalidate(); //force the tabbedpane to redraw.
		main.repaint();
		main.setSelectedComponent(newReq);
	}
	
	/**
	 * Opens a new tab for the creation of a iteration.
	 */
	public void createIteration() {
		IterationPanel newIter = new IterationPanel();
		main.addTab("New Iter.", null, newIter, "New Iteration");
		main.invalidate(); //force the tabbedpane to redraw.
		main.repaint();
		main.setSelectedComponent(newIter);
	}
	
	/**
	 * Opens a new tab for the editing of a iteration.
	 * @param iter Iteration
	 */
	public void editIteration(Iteration iter) {
		if(iter == IterationModel.getInstance().getBacklog()) return;
		
		IterationPanel exists = null;
		
		for(IterationPanel panel : listOfIterationPanels)
		{
			if(panel.getDisplayIteration() == iter)
			{
				exists = panel;
				break;
			}
		}	
		
		if(exists == null)
		{
			IterationPanel editIter = new IterationPanel(iter);
			listOfIterationPanels.add(editIter);
			main.addTab(iter.getName(), null, editIter, "Editing " + iter.getName());
			main.invalidate(); //force the tabbedpane to redraw.
			main.repaint();
			main.setSelectedComponent(editIter);
		}
		else
		{
			main.setSelectedComponent(exists);
		}
	}

	/**
	 * Opens a new tab for the creation of a pie chart.
	 * @param title the title of the pie chart
	 */
	public void createPieChart(String title){
		int i;
		for (i = 0; i < main.getTabCount(); i++) {
			if (main.getTitleAt(i).equals("Pie Chart")) {
				if(main.getTabComponentAt(i) instanceof NewPieChartPanel && (((NewPieChartPanel) main.getTabComponentAt(i)).getTitle().equals(title))){
					main.setSelectedIndex(i);
					return;
				}  
				else{
					main.remove(i);
					
				}				
			}
		}
		NewPieChartPanel newPie = new NewPieChartPanel(title); 
		main.addTab("Pie Chart", null, newPie, "PieChart");
		main.invalidate();
		main.repaint();
		main.setSelectedComponent(newPie);
		
	}
	
	/**
	 * Method createBarChart.
	 * @param title String
	 */
	public void createBarChart(String title){
		for(int i = 0; i < main.getTabCount(); i++){
			if(main.getTitleAt(i).equals("Bar Graph")){ 
					if(main.getTabComponentAt(i) instanceof NewBarChartPanel && (((NewBarChartPanel) main.getTabComponentAt(i)).getTitle().equals(title))){
						main.setSelectedIndex(i);
						return;
					}
					else{
						main.remove(i);
					}
			}
		}
		NewBarChartPanel newBar = new NewBarChartPanel(title);
		main.addTab("Bar Graph", null, newBar, "BarGraph");
		main.invalidate();
		main.repaint();
		main.setSelectedComponent(newBar);
	}
	


	/**
	 * Opens a child requirement panel to create the child requirement for the given parent.
	 * @param parentID
	 */
	public void createChildRequirement(int parentID) {
		RequirementPanel newReq = new RequirementPanel(parentID);
		main.addTab("Add Child Req.", null, newReq, "Add Child Requirement");
		main.invalidate(); //force the tabbedpane to redraw.
		main.repaint();
		main.setSelectedComponent(newReq);
	}
	/**
	 * Opens a new tab for the editing of a requirement
	 * @param toEdit the req to edit
	 */
	public void editRequirement(Requirement toEdit)
	{
		RequirementPanel exists = null;
		
		// set time stamp for transactions
		toEdit.getHistory().setTimestamp(System.currentTimeMillis());
		
		for(RequirementPanel panel : listOfEditingPanels)
		{
			if(panel.getDisplayRequirement() == toEdit)
			{
				exists = panel;
				break;
			}
		}	
		
		if(exists == null)
		{
			RequirementPanel editPanel = new RequirementPanel(toEdit);
			
			StringBuilder tabName = new StringBuilder();
			tabName.append(toEdit.getId()); 
			tabName.append(". ");
			int subStringLength = toEdit.getName().length() > 6 ? 7 : toEdit.getName().length();
			tabName.append(toEdit.getName().substring(0,subStringLength));
			if(toEdit.getName().length() > 6) tabName.append("..");
			
			main.addTab(tabName.toString(), null, editPanel, toEdit.getName());
			this.listOfEditingPanels.add(editPanel);
			main.invalidate();
			main.repaint();
			main.setSelectedComponent(editPanel);
		}
		else
		{
			main.setSelectedComponent(exists);
		}
	}


	/**
	 * Toggles the Overview Table multiple requirement editing mode
	 * @param cancel whether to cancel or not
	 */
	public void toggleEditingTable(boolean cancel){
		// check to see if Multiple Requirement Editing Mode is enabled and if the user is editing a cell		
		if (this.overviewTable.getEditFlag() && this.overviewTable.isEditing()) {
			// ends the cell editing and stores the entered value			
			this.overviewTable.getCellEditor().stopCellEditing();
		}
		
		// toggle the edit flag
		this.overviewTable.setEditFlag(!this.overviewTable.getEditFlag());
		
		// check to see if the overview table is now out of editing mode
		if (!this.overviewTable.getEditFlag()) {
			if (cancel) this.overviewTable.refresh();			
			else this.overviewTable.saveChanges();
		}	
	}



	/** 
	
	 * @return overviewTable */
	public OverviewTable getOverviewTable(){
		return overviewTable;

	}

	/**
	
	 * @return toolbar */
	public ToolbarView getToolbar() {
		return toolbar;
	}

	/**
	 * Removes the tab for the given JComponent
	 * @param comp the component to remove
	 */
	public void removeTab(JComponent comp)
	{
		if(comp instanceof RequirementPanel)
		{
			if(!((RequirementPanel)comp).readyToRemove()) return;
			this.listOfEditingPanels.remove(comp);

		}
		
		if(comp instanceof IterationPanel)
		{
			if(!((IterationPanel)comp).readyToRemove()) return;
			this.listOfIterationPanels.remove(comp);
		}
		main.remove(comp);
	}

	/**Tells the table to update its listings based on the data in the requirement model
	 * 
	 */
	public void refreshTable() {
		overviewTable.refresh();
	}

	/**
	 * Returns an array of the currently selected rows in the table.
	
	 * @return the currently selected rows in the table */
	public int[] getTableSelection()
	{
		return overviewTable.getSelectedRows();
	}
	
	/**
	 * Returns the main view
	
	 * @return the main view */
	public MainView getMainView() {
		return main;
	}

	/**
	 * Assigns all currently selected rows to the backlog.
	 */
	public void assignSelectionToBacklog()
	{
		int[] selection = overviewTable.getSelectedRows();

		// Set to false to indicate the requirement is being newly created
		boolean created = false;

		for(int i = 0; i < selection.length; i++)
		{
			Requirement toSendToBacklog = (Requirement)overviewTable.getValueAt(selection[i], 1);
			toSendToBacklog.setIteration("Backlog");
			UpdateRequirementController.getInstance().updateRequirement(toSendToBacklog);
		}

		this.refreshTable();
		this.refreshTree();
	}

	/**
	 * Edits the currently selected requirement.  If more than 1 requirement is selected, does nothing.
	 */
	public void editSelectedRequirement()
	{
		int[] selection = overviewTable.getSelectedRows();

		if(selection.length != 1) return;

		Requirement toEdit = (Requirement)overviewTable.getValueAt(selection[0],1);
		
		editRequirement(toEdit);
	}

	/**
	 * Closes all of the tabs besides the overview tab in the main view.
	 */
	public void closeAllTabs() {

		int tabCount = main.getTabCount();

		for(int i = tabCount - 1; i >= 0; i--)
		{
			Component toBeRemoved = main.getComponentAt(i);

			if(toBeRemoved instanceof OverviewPanel) continue;
			
			if(toBeRemoved instanceof IterationOverviewPanel) continue;

			if(toBeRemoved instanceof RequirementPanel)
			{
				if(!((RequirementPanel)toBeRemoved).readyToRemove()) continue;
				this.listOfEditingPanels.remove(toBeRemoved);
			}
			
			if(toBeRemoved instanceof IterationPanel)
			{
				if(!((IterationPanel)toBeRemoved).readyToRemove()) continue;
				this.listOfIterationPanels.remove(toBeRemoved);
			}

			main.removeTabAt(i);
		}

		main.repaint();
	}

	/**
	 * Closes all the tabs except for the one that was clicked.
	 * 
	 */
	public void closeOthers() {
		int tabCount = main.getTabCount();
		Component selected = main.getSelectedComponent();

		for(int i = tabCount - 1; i >= 0; i--)
		{
			Component toBeRemoved = main.getComponentAt(i);

			if(toBeRemoved instanceof OverviewPanel){
				continue;}
			if(toBeRemoved instanceof IterationOverviewPanel) continue;
			if(toBeRemoved == selected){
				continue;}

			if(toBeRemoved instanceof RequirementPanel)
			{
				if(!((RequirementPanel)toBeRemoved).readyToRemove()){
					break;}
				this.listOfEditingPanels.remove(toBeRemoved);
			}
			
			if(toBeRemoved instanceof IterationPanel)
			{
				if(!((IterationPanel)toBeRemoved).readyToRemove()) continue;
				this.listOfIterationPanels.remove(toBeRemoved);
			}

			main.removeTabAt(i);
		}
		main.repaint();

	}
	
	/**
	 * Refreshes the EditRequirementPanel after creating a new child
	 * 
	 * @param newChild req that is being created
	 */
	public void refreshEditRequirementPanel(Requirement newChild) {
		for(RequirementPanel newEditPanel : listOfEditingPanels)
		{
			if(newEditPanel.getDisplayRequirement() == newChild)
			{
				newEditPanel.fireRefresh();
				break;
			}
			
		}
		
	}

	/**
	 * Gets the iteration overview
	 * @return IterationOverviewPanel
	 */
	public IterationOverviewPanel getIterationOverview()
	{
		return iterationOverview;
	}
	
	/**
	 * Sets the iteration overview
	 * @param iOverview iterationoverview
	 */
	public void setIterationOverview(IterationOverviewPanel iOverview)
	{
		iterationOverview = iOverview;
	}
	
	/**
	 * Method getOverviewTree.
	
	 * @return OverviewTreePanel */
	public OverviewTreePanel getOverviewTree() {
		return overviewTree;
	}

	/**
	 * Method setOverviewTree.
	 * @param overviewTree OverviewTreePanel
	 */
	public void setOverviewTree(OverviewTreePanel overviewTree) {
		this.overviewTree = overviewTree;
	}
	
	public void refreshTree(){
		this.overviewTree.refresh();
	}
	
	public void editSelectedIteration() {
		DefaultMutableTreeNode selected = (DefaultMutableTreeNode)overviewTree.getTree().getLastSelectedPathComponent();
		if(selected.getUserObject() instanceof Iteration)
		{
			Iteration iter = ((Iteration)((DefaultMutableTreeNode)overviewTree.getTree().getLastSelectedPathComponent()).getUserObject());
		
			ViewEventController.getInstance().editIteration(iter);
		}
	}

	/**
	 * Method getListOfIterationPanels.
	 * @return ArrayList<IterationPanel>
	 */
	public ArrayList<IterationPanel> getListOfIterationPanels() {
		return listOfIterationPanels;
	}

	/**
	 * Method getListOfRequirementPanels.
	 * @return ArrayList<RequirementPanel>
	 */
	public ArrayList<RequirementPanel> getListOfRequirementPanels() {
		return listOfEditingPanels;
	}
}
