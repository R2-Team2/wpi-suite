/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveUsersController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.Comment;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.tabs.CommentPanel;

/**
 * The Class ViewTaskInformationPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class ViewTaskInformationPanel extends AbstractInformationPanel {

    /** The requirements. */
    private final List<Requirement> requirements = new ArrayList<Requirement>();
    private JScrollPane commentScroll;

    /**
     * Constructor for the ViewTaskButtonPanel.
     *
     * @param aParentPanel the parent panel
     */
    public ViewTaskInformationPanel(ViewTaskPanel aParentPanel) {
        parentPanel = aParentPanel;
        buildLayout();
        setupListeners();

        final List<Iteration> iterations = IterationModel.getInstance().getIterations();
        Collections.sort(iterations, new IterationComparator());
        for (int i = 0; i < iterations.size(); i++) {

            requirements.addAll(iterations.get(i).getRequirements());
            // gets the list of requirements that is associated with the iteration

        }
        Collections.sort(requirements, new RequirementComparator());
    }

    @Override
    public void buildLayout() {
        setMinimumSize(new Dimension(540, 200));
        // Set the Panel
        final ScrollablePanel contentPanel = new ScrollablePanel();
        contentPanel.setLayout(new MigLayout("", "20[grow,fill]20",
                "[][]3[]24[]3[]24[]3[]24[]3[]24[]24[]24"));

        // Instantiate GUI Elements
        // Labels
        final Task viewTask = parentPanel.aTask;
        final JLabel labelTitle = new JLabel("<html><h1>" + viewTask.getTitle() + "</h1></html>");
        final JLabel labelDescr = new JLabel("<html><h3>Description</h3></html>");
        final JLabel labelDescrBody = new JLabel("<html>" + viewTask.getDescription() + "</html>");
        final JLabel labelDetails = new JLabel("<html><h3>Details</h3></html>");
        final JLabel labelStatus = new JLabel("Status: ");
        final JLabel labelEstimatedEffort = new JLabel("Estimated Effort: ");
        final JLabel labelActualEffort = new JLabel("Actual Effort: ");
        final JLabel labelDates = new JLabel("<html><h3>Dates</h3></html>");
        final JLabel labelDueDate = new JLabel(" Due Date:");
        final JLabel labelStartDate = new JLabel("Start Date:");
        final JLabel labelRequirement = new JLabel("Requirement: ");
        final JLabel labelPeople = new JLabel("<html><h3>People</h3></html>");
        final JLabel labelAssignees = new JLabel("Assignees: ");
        final JLabel labelComments = new JLabel("<html><h3>Comments</h3></html>");
        final JLabel labelActivityLog = new JLabel("<html><h3>Activity Log</h3></html>");

        buttonOpenRequirement = new JButton("<");
        buttonOpenRequirement.setPreferredSize(new Dimension(16, 16));

        // Comment Box
        commentScroll = new JScrollPane();
        commentScroll
                .setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        commentScroll.setMinimumSize(new Dimension(100, 150));
        commentScroll.setMaximumSize(new Dimension(800, 300));
        fillCommentScroll();
        final JScrollBar vertical = commentScroll.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

        // Populate ContentPanel
        // **Title**
        final JPanel titlePanel = new JPanel(new MigLayout());
        titlePanel.add(labelTitle, "cell 0 0");
        contentPanel.add(titlePanel, "cell 0 0");

        // **Description**

        // Description Title and Separator
        contentPanel.add(labelDescr, "cell 0 1, split 2, span");
        contentPanel.add(new JSeparator(), "cell 0 1, growx, wrap");

        // Description Body
        contentPanel.add(labelDescrBody, "cell 0 2");

        // **Details**

        // Details Title and Separator
        contentPanel.add(labelDetails, "cell 0 3, split 2, span");
        contentPanel.add(new JSeparator(), "cell 0 3, growx, wrap");

        // Details Body
        final JPanel detailsPanel = new JPanel(new MigLayout("", "[]100[]", "[][][][]"));

        detailsPanel.add(labelStatus, "cell 0 0");
        detailsPanel.add(new JLabel("" + viewTask.getStatus()), "cell 1 0");
        detailsPanel.add(labelEstimatedEffort, "cell 0 1");
        detailsPanel.add(new JLabel("" + viewTask.getEstimatedEffort()), "cell 1 1");
        detailsPanel.add(labelActualEffort, "cell 0 2");
        detailsPanel.add(new JLabel("" + viewTask.getActualEffort()), "cell 1 2");
        detailsPanel.add(labelRequirement, "cell 0 3");

        final String requirementText;
        if (viewTask.getRequirement() == -1) {
            requirementText = "None";
            buttonOpenRequirement.setEnabled(false);
        } else {
            requirementText =
                    RequirementModel.getInstance().getRequirement(viewTask.getRequirement())
                            .getName();
            buttonOpenRequirement.setEnabled(true);
        }
        detailsPanel.add(new JLabel(requirementText), "cell 1 3");
        detailsPanel.add(buttonOpenRequirement, "cell 2 3, left");

        contentPanel.add(detailsPanel, "cell 0 4, left, growy, push, span, wrap");

        // **Dates**

        // Dates Title and Separator
        contentPanel.add(labelDates, "cell 0 5, split 2, span");
        contentPanel.add(new JSeparator(), "cell 0 5, growx, wrap");

        // Dates Panel
        final JPanel datesPanel = new JPanel(new MigLayout("", "[]135[]", "[][]"));

        datesPanel.add(labelStartDate, "cell 0 0");
        datesPanel.add(new JLabel(formatDate(viewTask.getStartDate())), "cell 1 0");
        datesPanel.add(labelDueDate, "cell 0 1");
        datesPanel.add(new JLabel(formatDate(viewTask.getDueDate())), "cell 1 1");
        contentPanel.add(datesPanel, "cell 0 6, left, growy, push, span, wrap");

        // **People**

        // People Title and Separator
        contentPanel.add(labelPeople, "cell 0 7, split 2, span");
        contentPanel.add(new JSeparator(), "cell 0 7, growx, wrap");

        // People Panel
        final JPanel peoplePanel = new JPanel();
        peoplePanel.setLayout(new MigLayout("", "[]130[]", "[]"));
        peoplePanel.add(labelAssignees, "cell 0 0");
        String usernameString = "";
        for (String username : viewTask.getAssignedUsers()) {
            usernameString += (username + ", ");
        }
        if (usernameString.length() == 0) {
            usernameString = "...";
        } else {
            usernameString = usernameString.substring(0, usernameString.length() - 2);
        }
        peoplePanel.add(new JLabel(usernameString), "cell 1 0");
        contentPanel.add(peoplePanel, "cell 0 8,grow");

        // **Comments**

        // Comment Title and Separator
        contentPanel.add(labelComments, "cell 0 9, split 2, span");
        contentPanel.add(new JSeparator(), "cell 0 9, growx, wrap");

        contentPanel.add(commentScroll, "cell 0 10, grow, wrap");

        // **Activity Log**

        // Activity Title and Separator
        contentPanel.add(labelActivityLog, "cell 0 11, split 2, span");
        contentPanel.add(new JSeparator(), "cell 0 11, growx, wrap");
        final List<String> tempList = new ArrayList();
        for (int i = 0; i < viewTask.getActivityList().size(); i++) {
            if (viewTask.getActivityList().get(i).length() > 75) {
                tempList.add("<html>"
                        + viewTask.getActivityList().get(i).substring(0, 75)
                        + "<br/>"
                        + viewTask.getActivityList().get(i)
                        .substring(76, viewTask.getActivityList().get(i).length())
                        + "</html>");
            } else {
                tempList.add("<html>" + viewTask.getActivityList().get(i) + "</html>");
            }
        }
        activities = new JList(tempList.toArray());
        contentPanel.add(activities, "cell 0 12,grow");

        setViewportView(contentPanel);
    }

    /**
     * Sets the fields from the class's task object.
     */
    public void setupTask() {
        final Task viewTask = parentPanel.aTask;

        final String t = viewTask.getTitle();
        boxTitle.setText(t);
        boxDescription.setText(viewTask.getDescription());
        dropdownStatus.setSelectedItem(viewTask.getStatus().toString());
        // requirement
        for (String username : viewTask.getAssignedUsers()) {
            new RetrieveUsersController(chosenAssigneeModel).requestUser(username);
        }
        calStartDate.setDate(viewTask.getStartDate());
        calDueDate.setDate(viewTask.getDueDate());
        spinnerEstimatedEffort.setValue(viewTask.getEstimatedEffort());
        spinnerActualEffort.setValue(viewTask.getActualEffort());
        activities = new JList(viewTask.getActivityList().toArray());
    }

    /**
     * Sets up listeners for members of panel.
     */
    protected void setupListeners() {
        buttonOpenRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.infoPanel.openRequirement();
            }
        });
    }

    /**
     * <<<<<<< HEAD <<<<<<< HEAD >>>>>>> develop Returns the formatted due date of a task. =======
     * Returns the formatted due date of a task. >>>>>>> origin/feature-143-activities_to_edit
     * ======= <<<<<<< HEAD Returns the formatted due date of a task. ======= >>>>>>> develop
     * Returns the formatted due date of a task. >>>>>>> develop >>>>>>> develop
     *
     * @param date the date
     * @return dateString Formatted Due Date of Task t in mm/dd/yy
     */
    private String formatDate(Date date) {
        String strDate = null;

        if (date == null) {
            strDate = "...";
        } else {
            final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
            final String dateString = dateFormatter.format(date);
            strDate = dateString;
        }
        return strDate;
    }

    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractInformationPanel#getTask()
     */
    @Override
    public Task getTask() {
        return parentPanel.aTask;
    }

    /**
     * Gets the current requirement.
     *
     * @return selected requirement object
     * @throws Exception the exception
     */
    private Requirement getCurrentRequirement() throws Exception {
        final int req = parentPanel.aTask.getRequirement();

        for (Requirement requirement : requirements) {
            if (requirement.getId() == req) {
                return requirement;
            }
        }

        throw new Exception("Invalid requirement selected");
    }

    /**
     * Open requirement.
     */
    @Override
    protected void openRequirement() {
        try {
            edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController
                    .getInstance().editRequirement(getCurrentRequirement());
            edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController.getInstance()
                    .openRequirementsTab();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    /**
     * Refreshes the note panel
     */
    private void fillCommentScroll() {

        final JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE); // Background color is white
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints(); // Create layout for adding notes
        c.gridy = GridBagConstraints.RELATIVE; // Make a new row and add it to it
        c.anchor = GridBagConstraints.NORTH; // Anchor to top of panel
        c.fill = GridBagConstraints.HORIZONTAL; // Fill elements horizontally
        c.weightx = 1;// Fill horizontally
        c.gridy = 0; // Row 0
        c.insets = new Insets(5, 5, 5, 5); // Creates margins between notes

        // Get iterator of the list of notes
        parentPanel.aTask.getComments();
        final ListIterator<Comment> itt = parentPanel.aTask.getComments().getIterator(0);

        // Add each note to panel individually
        while (itt.hasNext()) {
            // Create a new NotePanel for each Note and add it to the panel
            panel.add(new CommentPanel(itt.next()), c);
            c.gridy++; // Next Row
        }

        // Create a dummy panel to take up space at the bottom
        c.weighty = 1;
        final JPanel dummy = new JPanel();
        dummy.setBackground(Color.WHITE);
        panel.add(dummy, c);

        commentScroll.setViewportView(panel);
    }
}
