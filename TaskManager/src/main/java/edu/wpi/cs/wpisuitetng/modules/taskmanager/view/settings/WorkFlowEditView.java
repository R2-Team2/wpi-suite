/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
// this class has depricated through task 24- create collapsing sidebar.
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.AbsWorkFlowView;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlowView.
 * @author R2-Team2
 */
public class WorkFlowEditView extends AbsWorkFlowView {


	private JPanel sidePanel = new JPanel(new MigLayout());
	
    /** The default border. */
    protected final Border defaultBorder = BorderFactory.createEtchedBorder();
    protected final Dimension textFieldDimension = new Dimension(200, 10);
    protected final Dimension listFieldDimension = new Dimension(100, 400);
    
	private JButton addButton = new JButton("Add Status");
	private JButton removeButton = new JButton("Remove Status");
	private JButton saveButton = new JButton("Save");
	
	private JButton upButton = new JButton("\u2191");
	private JButton downButton = new JButton("\u2193");
	
	
	// Add Status Fields
	private JTextField newStatusTitleField = new JTextField();
	private JLabel newStatusTitleLabel = new JLabel("New Status Title");
	private JTextField newStatusTypeField = new JTextField();
	private JLabel newStatusTypeLabel = new JLabel("New Status Type");
	
	// Show and Remove Status Components

	DefaultListModel<TaskStatusView> model = new DefaultListModel<TaskStatusView>();
	private JList<TaskStatusView> listOfStatus = new JList<TaskStatusView>(model);

    /**
     * Create the panel.
     */
    public WorkFlowEditView() {
    	//super();
        sidePanel.setMinimumSize(new Dimension(100,1000));
        newStatusTitleField.setBorder(defaultBorder);
        newStatusTitleField.setMinimumSize(textFieldDimension);
        newStatusTypeField.setMinimumSize(textFieldDimension);
        
        // Add status Components
        sidePanel.add(newStatusTitleLabel, "wrap");
        sidePanel.add(newStatusTitleField,"wrap");
        sidePanel.add(newStatusTypeLabel,"wrap");
        sidePanel.add(newStatusTypeField, "wrap");
        addButton.setEnabled(false);
        sidePanel.add(addButton, "wrap");
        
        // Remove Status Components
        buildList();
        //listOfStatus.setMinimumSize(listFieldDimension);
        sidePanel.add(listOfStatus,"span 2 2");
        sidePanel.add(upButton,"wrap");
        sidePanel.add(downButton, "wrap");
        sidePanel.add(removeButton, "wrap");
        
        // Save Changes
        // This needs to work with database to actually save the changes made in this view
        sidePanel.add(saveButton,"wrap");
        
        
        
        taskStatusPanel.add(sidePanel, "dock east");
        setupListeners();
    }
    
    private void buildList(){
        for (TaskStatusView t : getViews()) {
        	if(!model.contains(t)){
            	model.addElement(t);
        	}
        }
    }
    /**
     * Gets the work flow obj.
     *
     * @return the work flow obj
     */
    public WorkFlow getWorkFlowObj() {
        return super.getWorkFlowObj();
    }

    /**
     * Sets the work flow obj.
     *
     * @param workFlowObj the new work flow obj
     */
    public void setWorkFlowObj(WorkFlow workFlowObj) {
        super.setWorkFlowObj(workFlowObj);
    }

    /**
     * Refresh.
     */
    public void refresh() {
        super.refresh();
        buildList();
    }
    protected void setupListeners() {
    	addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addTaskStatusView(new TaskStatusView(newStatusTitleField.getText(), newStatusTitleField.getText()));
            	clearNewStatusFields();
            	refresh();
            }
        });

    	removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// remove selected from JList
            	TaskStatusView taskStatusViewToRemove = (TaskStatusView) listOfStatus.getSelectedValue();
            	model.removeElement(taskStatusViewToRemove);
            	removeTaskStatusView(taskStatusViewToRemove);
            	buildTaskStatusViews();
            	refresh();
            	revalidate();
            	repaint();
            }

        });
    	
    	upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// remove selected from JList
            	TaskStatusView taskStatusViewToMoveUp = listOfStatus.getSelectedValue();
            	moveStatusUp(taskStatusViewToMoveUp);
            	refresh();
            	revalidate();
            	repaint();
            }
        });
    	downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// remove selected from JList
            	TaskStatusView taskStatusViewToMoveDown = (TaskStatusView) listOfStatus.getSelectedValue();
            	moveStatusDown(taskStatusViewToMoveDown);
            	
            	buildTaskStatusViews();
            	refresh();
            	revalidate();
            	repaint();
            }

        });
    	    	
    	newStatusTitleField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            	validateNewStatusFields();
            }

			@Override
            public void removeUpdate(DocumentEvent e) {
				validateNewStatusFields();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            	validateNewStatusFields();
            }
        });
    	newStatusTypeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            	validateNewStatusFields();
            }

			@Override
            public void removeUpdate(DocumentEvent e) {
				validateNewStatusFields();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            	validateNewStatusFields();
            }
        });
    	
    	
    }
    
    protected void moveStatusUp(TaskStatusView taskStatusViewToMoveUp) {
		if(!(model.indexOf(taskStatusViewToMoveUp) <= 0)){
			// Move in views
			moveUp(taskStatusViewToMoveUp);
			// update JList
			int source = model.indexOf(taskStatusViewToMoveUp);
	    	int dest = model.indexOf(taskStatusViewToMoveUp)-1;
	    	
	    	TaskStatusView sourceStatusView = model.get(source);
	    	TaskStatusView	destStatusView = model.get(dest);
	    	
	    	model.set(dest, sourceStatusView);
	    	model.set(source, destStatusView);
	    	listOfStatus.setSelectedValue(taskStatusViewToMoveUp, true);
	    	buildList();
		}
	}
    
	protected void moveStatusDown(TaskStatusView taskStatusViewToMoveDown) {
		if(!(model.indexOf(taskStatusViewToMoveDown) >= model.getSize()-1)){
			// Move in views
			moveDown(taskStatusViewToMoveDown);
			// update JList
			int source = model.indexOf(taskStatusViewToMoveDown);
			int dest = model.indexOf(taskStatusViewToMoveDown)+1;
			
			TaskStatusView sourceStatusView = model.get(source);
			TaskStatusView	destStatusView = model.get(dest);
			
			model.set(dest, sourceStatusView);
			model.set(source, destStatusView);
			listOfStatus.setSelectedValue(taskStatusViewToMoveDown, true);
			buildList();
		}
	}

	

	protected void clearNewStatusFields() {
		newStatusTitleField.setText("");
		newStatusTypeField.setText("");
	}

	private void validateNewStatusFields(){
    	if(validateNewStatusTitleField() && validateNewStatusTypeField()){
    		addButton.setEnabled(true);
    	}
    	else{
    		addButton.setEnabled(false);
    	}
    }
    private boolean validateNewStatusTitleField() {
		// TODO Auto-generated method stub
    	if (newStatusTitleField.getText().length() <= 0){
    		return false;
        }
        else {
           return true;
        }
	}
    
    private boolean validateNewStatusTypeField() {
		// TODO Auto-generated method stub
    	if (newStatusTypeField.getText().length() <=0){
    		return false;
        }
        else {
            return true;
        }
	}
}
