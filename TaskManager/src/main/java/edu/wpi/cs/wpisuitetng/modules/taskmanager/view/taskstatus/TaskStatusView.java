/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveTasksController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AbsView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.TaskCard;

/**
 * The Class TaskStatusView.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class TaskStatusView extends AbsView {

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return taskStatusObj.toString();
    }

    /** The task status obj. */
    TaskStatus taskStatusObj;

    Task[] allTasks;
    List<Task> displayTasks = new ArrayList<Task>();

    /** The txtpn title. */
    JTextPane txtpnTitle = new JTextPane();

    /** The panel. */
    JPanel panel = new JPanel();

    /** Represents whether the view has been initialized. */
    private boolean initialized;

    /**
     * Create the panel.
     *
     * @param taskStatusObject the Task Status Object
     */
    public TaskStatusView(TaskStatus taskStatusObject) {

        initialized = false;

        setLayout(new MigLayout("", "[236px,grow]", "[26px][200px,grow 500]"));
        taskStatusObj = taskStatusObject;

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // Change Vertical Scroll Bar Policy to AS_NEEDED When Task Cards are developed
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EtchedBorder());
        this.add(scrollPane, "cell 0 1,grow");

        // Create Format and Add Title JTextPane
        final StyledDocument document = new DefaultStyledDocument();
        final javax.swing.text.Style defaultStyle = document.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(defaultStyle, StyleConstants.ALIGN_CENTER);
        final JTextPane txtpnTitle = new JTextPane(document);
        txtpnTitle.setBackground(UIManager.getColor("Button.background"));
        txtpnTitle.setBorder(null);
        txtpnTitle.setForeground(Color.black);
        txtpnTitle.setEditable(false);
        txtpnTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        txtpnTitle.setText(taskStatusObj.getName());
        this.add(txtpnTitle, "cell 0 0,alignx center,aligny center");
        panel.setBackground(Color.WHITE);

        scrollPane.setViewportView(panel);
        panel.setLayout(new MigLayout("", "[236px,grow,fill]", "[]"));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((taskStatusObj == null) ? 0 : taskStatusObj.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TaskStatusView))
            return false;
        TaskStatusView other = (TaskStatusView) obj;
        if (taskStatusObj == null) {
            if (other.taskStatusObj != null)
                return false;
        } else if (!taskStatusObj.equals(other.taskStatusObj))
            return false;
        return true;
    }

    // public void getTaskStatusFromDB() {
    // RetrieveTaskStatusController retrieveTS = new RetrieveTaskStatusController(this);
    // retrieveTS.requestTaskStatuses();
    // initialized = true;
    // }

    /**
     * Populate TaskStatusView with Cards Associated with the Status.
     */
    public void requestTasksFromDb() {
        final RetrieveTasksController retrieveTasks = new RetrieveTasksController(this);
        retrieveTasks.requestTasks();

    }

    /**
     * Fill task list.
     *
     * @param taskArray the task array
     */
    public void fillTaskList(Task[] taskArray) {
        System.out.println("Parsing all tasks.");
        allTasks = taskArray;
        for (long id : taskStatusObj.getTaskList()) {
            for (int i = 0; i < allTasks.length; i++) {
                if (allTasks[i].getTaskID() == id) {
                    displayTasks.add(i, allTasks[i]);
                }
            }
        }
        System.out.println("Number of tasks, all: " + allTasks.length);
        System.out.println("Number of tasks to display: " + displayTasks.size());
        System.out.println(taskStatusObj.toJson());
        // System.out.println(displayTasks.get(0).toJson());

        populateTaskStatusViewCards();
    }

    /**
     * Populate task status view cards.
     */
    public void populateTaskStatusViewCards() {
        panel.removeAll();
        for (Task t : displayTasks) {
            String dateString = formateDate(t);
            TaskCard card = new TaskCard(t.getTitle(), dateString, t.getUserForTaskCard(), t);
            panel.add(card, "newline");
        }
        revalidate();
    }

    public void setTaskStatusObj(TaskStatus taskStatus) {
        taskStatusObj = taskStatus;
    }

    public TaskStatus getTaskStatusObj() {
        return taskStatusObj;
    }

    /**
     * Returns the formatted due date of a task.
     *
     * @param t A given Task Object
     * @return dateString Formatted Due Date of Task t in mm/dd/yy
     */
    private String formateDate(Task t) {
        final Date date = t.getDueDate();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        final String dateString = dateFormatter.format(date);
        return dateString;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!initialized) {
            requestTasksFromDb();
            initialized = true;
        }
        super.paintComponent(g);
    }

    /**
     * Acts as a setter for taskStatusObj when taskStatus is retrieved from DB.
     */
    @Override
    public void utilizeTaskStatuses(TaskStatus[] taskStatusArray) {
        for (TaskStatus ts : taskStatusArray) {
            if (ts.getTaskStatusID() == taskStatusObj.getTaskStatusID()) {
                taskStatusObj = ts;
                break;
            }
        }

    }

}
