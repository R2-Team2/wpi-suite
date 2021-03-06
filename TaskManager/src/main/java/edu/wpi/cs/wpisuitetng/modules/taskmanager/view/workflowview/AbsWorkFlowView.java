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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

/**
 * Abstract View for all Work Flow Views
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class AbsWorkFlowView extends JPanel {

    /** The task status panel. */
    protected JPanel taskStatusPanel;

    /** The task status views. */
    private final List<TaskStatusView> views;


    /**
     * Constructor for AbsWorkFlowView.
     */
    protected AbsWorkFlowView() {
        views = new ArrayList<TaskStatusView>();
        views.add(new TaskStatusView(new TaskStatus("New")));
        views.add(new TaskStatusView(new TaskStatus("Selected for Development")));
        views.add(new TaskStatusView(new TaskStatus("Currently in Development")));
        views.add(new TaskStatusView(new TaskStatus("Completed")));
        views.add(new TaskStatusView(new TaskStatus("Archived")));

        setLayout(new BorderLayout());

        taskStatusPanel = new JPanel();

        this.add(taskStatusPanel, BorderLayout.CENTER);


        taskStatusPanel.setLayout(new MigLayout("", "[350px:n:500px,grow,left]"
                + "[350px:n:500px,grow,left][350px:n:500px,grow,left]"
                + "[350px:n:500px,grow,left]", "[278px,grow 500]"));
        buildTaskStatusViews();
        this.add(taskStatusPanel, BorderLayout.CENTER);
    }

    /**
     * Method addTaskStatusView.
     *
     * @param taskStatusView TaskStatusView
     */
    public void addTaskStatusView(TaskStatusView taskStatusView) {
        views.add(taskStatusView);
        buildTaskStatusViews();
    }

    /**
     * Method buildTaskStatusViews.
     */
    public void buildTaskStatusViews() {
        for (TaskStatusView t : views) {
            taskStatusPanel.add(t, "grow");
        }
        this.add(taskStatusPanel, BorderLayout.CENTER);
    }

    /**
     * @return the taskStatusPanel
     */
    public JPanel getTaskStatusPanel() {
        return taskStatusPanel;
    }

    /**
     * Refresh.
     */
    public void refresh() {
        for (TaskStatusView v : views) {
            v.requestTasksFromDb();
        }
    }

    /**
     * returns the list of TaskStatusViews in the Workflow
     *
     * @return List<TaskStatusView>
     */
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
            buildTaskStatusViews();
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
            buildTaskStatusViews();
        }
    }

    /**
     * showArchived STUB
     *
     * @param b true if the given view is to be shown, false for hide
     * @param t the task status view to be operated on
     */
    public void showArchived(Boolean b, TaskStatusView t) {}
}
