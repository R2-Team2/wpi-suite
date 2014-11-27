/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
// this class has depricated through task 24- create collapsing sidebar.
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.workflow.AddWorkFlowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.workflow.RetrieveWorkFlowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlowView.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class WorkFlowView extends JPanel {

    /** The work flow obj. */
    private WorkFlow workFlowObj;

    // /** The txt text. */
    // private JTextField txtText;

    /** The task status panel. */
    private final JPanel taskStatusPanel;

    // /** The title. */
    // private String title;

    /** The task status views. */
    List<TaskStatusView> taskStatusViews;

    /** Timer that handles automatic updating of the view */
    Timer refreshTimer;

    private boolean initialized = false;

    /**
     * Create the panel.
     */
    public WorkFlowView() {

        ViewEventController.getInstance().setWorkFlowView(this);
        taskStatusViews = new ArrayList<TaskStatusView>();

        setLayout(new BorderLayout());

        taskStatusPanel = new JPanel();
        this.add(taskStatusPanel, BorderLayout.CENTER);
        taskStatusPanel.setLayout(new MigLayout(
                "",
                "[350px:n:500px,grow,left][350px:n:500px,grow,left]"
                        + "[350px:n:500px,grow,left][350px:n:500px,grow,left]",
                "[278px,grow 500]"));

        ViewEventController.getInstance().setWorkFlowView(this);

        // Initialize the timer
        refreshTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("In Timer ActionListener()");
                    // if (!initialized) {
                    // initWorkFlowView();
                    // }
                    // else {
                    retrieveWorkFlowFromDb();
                    // }

                } catch (Exception ex) {
                } // This is so we ignore the exception thrown due to non-initialized exception.
                  // It may/may not be worth it to just make sure this doesn't run until after
                  // everything is initialized, but that seems like a lot of work.
            }
        });
        refreshTimer.setDelay(5000); // delay for 5 seconds
        refreshTimer.start();

    }

    /**
     * Retrieves and uses workflow stored in database if it exists. Else create new. This method is
     * called by the workflow controller.
     *
     * @param workFlow
     */
    public void initWorkFlowView() {
        // If workflow does not exist
        // Add this new workflow to the database too
        workFlowObj = new WorkFlow();
        List<TaskStatus> tempTaskStatusList = new ArrayList<TaskStatus>();
        tempTaskStatusList.add(new TaskStatus("New"));
        tempTaskStatusList.add(new TaskStatus("Selected for Development"));
        tempTaskStatusList.add(new TaskStatus("Currently in Development"));
        tempTaskStatusList.add(new TaskStatus("Completed"));

        for (int i = 0; i < tempTaskStatusList.size(); i++) {
            taskStatusViews.add(new TaskStatusView(tempTaskStatusList.get(i)));
            taskStatusPanel.add(taskStatusViews.get(i), "cell " + i + " 0,grow");
        }

        workFlowObj.setTaskStatusList(tempTaskStatusList);

        final AddWorkFlowController addWorkFlow =
                new AddWorkFlowController(this);
        addWorkFlow.addWorkFlow();

        revalidate();
        initialized = true;
        System.out.println("New WorkFlow has been created!");

    }

    public void updateWorkFlowView(WorkFlow workFlow) {
        // Use acquired workflow
        workFlowObj = workFlow;
        populateWorkFlowViewTaskStatus(workFlowObj.getTaskStatusList());
        System.out.println("Old WorkFlow has been retrieved!");
        ViewEventController.getInstance().setWorkFlowView(this);
        // ViewEventController.refreshWorkFlowView();
    }

    /**
     * Populates a workflow with task status views, double check this with Selim! Will be called by
     * retrieve workflow controller when data has been retrieved.
     *
     * @param taskStatusList
     */
    public void populateWorkFlowViewTaskStatus(List<TaskStatus> taskStatusList) {
        taskStatusPanel.removeAll();
        for (int i = 0; i < taskStatusList.size(); i++) {
            TaskStatusView taskStatusView = new TaskStatusView(
                    taskStatusList.get(i));
            taskStatusPanel.add(taskStatusView, "cell " + i + " 0,grow");
        }
        revalidate();
    }

    /**
     * Has to be called outside workflowview by a class that stores the state of workflowview
     */
    public void retrieveWorkFlowFromDb() {
        final RetrieveWorkFlowController retrieveWorkFlow = new RetrieveWorkFlowController(this);
        retrieveWorkFlow.requestWorkFlow();
    }

    /**
     * Gets the work flow obj.
     *
     * @return the work flow obj
     */
    public WorkFlow getWorkFlowObj() {
        return workFlowObj;
    }

    /**
     * Sets the work flow obj.
     *
     * @param workFlowObj the new work flow obj
     */
    public void setWorkFlowObj(WorkFlow workFlowObj) {
        this.workFlowObj = workFlowObj;
    }

    /**
     * Refresh.
     */
    public void refresh() {
        // Not sure how this method works, or how it's called
        System.out.println("In refresh method in WorkFlowView.");
        // requestWorkFlowFromDb();
        // for (TaskStatusView v : taskStatusViews) {
        // v.requestTaskStatusFromDb();
        // }
    }

}
