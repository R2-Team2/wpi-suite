/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveTaskStatusController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AbsView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

/**
 * Abstract View for all Work Flow Views
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public abstract class AbsWorkFlowView extends AbsView {

    /** The task status panel. */
    public JPanel taskStatusPanel = new JPanel();

    /** The task status views. */
    private final List<TaskStatusView> views = new ArrayList<TaskStatusView>();
    
    /** The task status objects */
    List<TaskStatus> statuses = new ArrayList<TaskStatus>();

    /**
     * Constructor for AbsWorkFlowView.
     */
    protected AbsWorkFlowView() {
        setLayout(new BorderLayout());
        
        taskStatusPanel.setLayout(new MigLayout(
                "",
                "[350px:n:500px,grow,left][350px:n:500px,grow,left]"
                        + "[350px:n:500px,grow,left][350px:n:500px,grow,left]",
                "[278px,grow 500]"));
        //buildTaskStatusViews();
        this.add(taskStatusPanel, BorderLayout.CENTER);
    }
    
    /**
     * Refresh.
     */
    public void refresh() {
    	updateTaskStatuses();
    	updateTaskStatusViews();
        for (TaskStatusView v : views) {
            v.requestTasksFromDb();
        }
        updateView();
        revalidate();
        repaint();
    }
    
    public void updateTaskStatuses(){
    	RetrieveTaskStatusController retrieveTS = new RetrieveTaskStatusController(this);
        retrieveTS.requestTaskStatuses();
        System.out.println("Begin Building TS Views.");
    }
    
    public void updateTaskStatusViews(){
    	for (TaskStatus status : statuses) {
    		TaskStatusView aView = new TaskStatusView(status);
            if(!views.contains(aView)){
            	views.add(aView);
            }
            	
    	}
    }
    
    /**
     * Method updateView.
     */
    public void updateView() {
        this.remove(taskStatusPanel);
    	for (TaskStatusView t : views) {
            taskStatusPanel.add(t, "grow");
    	}
        this.add(taskStatusPanel, BorderLayout.CENTER);
    }
    
    public void buildTaskStatusView() {
        if (statuses.size() > 0) {
            for (int i = 0; i < statuses.size(); i++) {
                TaskStatusView aView =
                        new TaskStatusView(new TaskStatus(statuses.get(i).getName()));
                aView.setTaskStatusObj(statuses.get(i));
                // System.out.println("Print task status: " + i + " - " + statuses.get(i).toJson());
                //taskStatusPanel.add(aView, "cell " + i + " 0,grow");
                addTaskStatusView(aView);
            }
        } else {
            System.out.println("Currently No Statuses");
            //rebuildWF();
        }
    }
    
    /**
     * Sets the objects list of task status objects
     *
     * @param listOfStatus the list of status objects.
     */
    public void setStatuses(List<TaskStatus> listOfStatus) {
        statuses = listOfStatus;
        //instance = this;
    }

    /**
     * Returns the statuses field
     *
     * @return array of task statuses.
     */
    public List<TaskStatus> getStatuses() {
        //instance = this;
        return statuses;
    }
    /**
     * Method addTaskStatusView.
     *
     * @param taskStatusView TaskStatusView
     */
    public void addTaskStatusView(TaskStatusView taskStatusView) {
        if(!views.contains(taskStatusView)){
        	views.add(taskStatusView);
        }
        updateView();
    }

    /**
     * @return the taskStatusPanel
     */
    public JPanel getTaskStatusPanel() {
        return taskStatusPanel;
    }

    public List<TaskStatusView> getViews() {
    	return views;
    }

    /**
     * Method removeTaskStatusView.
     *
     * @param taskStatusView TaskStatusView
     * @return boolean
     */
    public boolean removeTaskStatusView(TaskStatusView taskStatusView) {
        taskStatusPanel.remove(taskStatusView);
        return views.remove(taskStatusView);
    }

    /**
     * Method moveUp.
     *
     * @param taskStatusViewToMoveUp TaskStatusView
     */
    public void moveUp(TaskStatusView taskStatusViewToMoveUp) {
        if (!(views.indexOf(taskStatusViewToMoveUp) <= 0)) {
            // find index of tomove
            // get index moveto
            // move that to next
            final int source = views.indexOf(taskStatusViewToMoveUp);
            final int dest = views.indexOf(taskStatusViewToMoveUp) - 1;

            final TaskStatusView sourceStatusView = views.get(source);
            final TaskStatusView destStatusView = views.get(dest);

            views.set(dest, sourceStatusView);
            views.set(source, destStatusView);
            refresh();
        }
    }

    /**
     * Method moveDown.
     *
     * @param taskStatusViewToMoveDown TaskStatusView
     */
    public void moveDown(TaskStatusView taskStatusViewToMoveDown) {
        if (!(views.indexOf(taskStatusViewToMoveDown) >= views.size() - 1)) {
            // find index of tomove
            // get index moveto
            // move that to next
            final int source = views.indexOf(taskStatusViewToMoveDown);
            final int dest = views.indexOf(taskStatusViewToMoveDown) + 1;

            final TaskStatusView sourceStatusView = views.get(source);
            final TaskStatusView destStatusView = views.get(dest);

            views.set(dest, sourceStatusView);
            views.set(source, destStatusView);
            refresh();
        }
    }

}
