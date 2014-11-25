/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXDatePicker;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractInformationPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class AbstractInformationPanel extends JScrollPane {

    /** The parent panel. */
    protected AbstractTaskPanel parentPanel;

    /** The list of chosen assignees. */
    protected User[] listOfChosenAssignees = new User[] {};

    /** The list of possible assignees. */
    protected User[] listOfPossibleAssignees = new User[] {};

    /** The list of statuses. */
    protected String[] listOfStatuses = new String[] {new TaskStatus("new").toString(),
            new TaskStatus("scheduled").toString(),
            new TaskStatus("in progress").toString(),
            new TaskStatus("complete").toString()}; // needs to be list of TaskStatus

    /** The list of requirements. */
    protected String[] listOfRequirements = new String[] {"None"};

    /** The default border. */
    protected final Border defaultBorder = BorderFactory.createEtchedBorder();

    /** The box title. */
    protected JTextField boxTitle;

    /** The box description. */
    protected JTextArea boxDescription;

    /** The dropdown status. */
    protected JComboBox<String> dropdownStatus;

    /** The dropdown requirement. */
    protected JComboBox<String> dropdownRequirement;

    /** The list chosen assignees. */
    protected JList<User> listChosenAssignees;

    /** The list possible assignees. */
    protected JList<User> listPossibleAssignees;

    /** The spinner estimated effort. */
    protected JSpinner spinnerEstimatedEffort;

    /** The spinner actual effort. */
    protected JSpinner spinnerActualEffort;

    /** The button add. */
    protected JButton buttonAdd;

    /** The button remove. */
    protected JButton buttonRemove;

    /** The cal start date. */
    protected JXDatePicker calStartDate;

    /** The cal due date. */
    protected JXDatePicker calDueDate;

    /**
     * Builds the layout.
     */
    protected void buildLayout() {
        this.setMinimumSize(new Dimension(540, 200));
        // Set the Panel
        final ScrollablePanel contentPanel = new ScrollablePanel();
        contentPanel.setLayout(new MigLayout("", "20[]20", "shrink"));
        // contentPanel.setLayout(new MigLayout("", "[500px:n:500px,left]", "shrink"));
        // Instantiate GUI Elements
        // Labels
        final JLabel labelTitle = new JLabel("<html>Title: <font color='red'>*</font></html>");
        final JLabel labelDescription =
                new JLabel("<html>Description: <font color='red'>*</font></html>");
        final JLabel labelStatus = new JLabel("Status: ");
        final JLabel labelEstimatedEffort = new JLabel("Estimated Effort: ");
        final JLabel labelActualEffort = new JLabel("Actual Effort: ");
        final JLabel labelDueDate = new JLabel("Due Date: ");
        final JLabel labelStartDate = new JLabel("Start Date: ");
        final JLabel labelRequirement = new JLabel("Requirement: ");
        final JLabel labelPossibleAssignee = new JLabel("Open Assignees: ");
        final JLabel labelChosenAssignee = new JLabel("Chosen Assignees: ");
        // Text Areas
        boxTitle = new JTextField("");
        boxTitle.setBorder(defaultBorder);

        final JScrollPane descrScroll = new JScrollPane();
        boxDescription = new JTextArea();
        boxDescription.setLineWrap(true);
        boxDescription.setBorder(null);
        descrScroll.setBorder(defaultBorder);
        descrScroll.setViewportView(boxDescription);
        // Drop Down Menus
        dropdownRequirement = new JComboBox<String>();
        dropdownRequirement.setModel(new DefaultComboBoxModel<String>(listOfRequirements));
        dropdownRequirement.setEnabled(false);
        dropdownRequirement.setBackground(Color.WHITE);
        dropdownStatus = new JComboBox<String>();
        dropdownStatus.setModel(new DefaultComboBoxModel<String>(listOfStatuses));
        dropdownStatus.setEnabled(true);
        dropdownStatus.setBackground(Color.WHITE);
        // Lists
        listChosenAssignees = new JList<User>();
        listPossibleAssignees = new JList<User>();
        // Spinners
        spinnerEstimatedEffort = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        spinnerActualEffort = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        // Buttons
        buttonAdd = new JButton(">>");
        buttonAdd.setEnabled(false);
        buttonRemove = new JButton("<<");
        buttonRemove.setEnabled(false);
        // Calendars
        calStartDate = new JXDatePicker();
        calDueDate = new JXDatePicker();

        // Setup GUI


        // Setup Columns
        final JPanel leftColumn = new JPanel(new MigLayout());
        final JPanel rightColumn = new JPanel(new MigLayout());

        final JPanel bottom = new JPanel(new MigLayout());

        final JPanel bottomLeft = new JPanel(new MigLayout());
        final JPanel bottomCenter = new JPanel(new MigLayout());
        final JPanel bottomRight = new JPanel(new MigLayout());

        // Assignee view created and populated to the bottom Panel
        listPossibleAssignees.setBorder(defaultBorder);
        bottomLeft.add(labelPossibleAssignee, "left, wrap");
        bottomLeft.add(listPossibleAssignees, "left, width 200px, height 150px, wrap");

        bottomCenter.add(buttonAdd, "center, wrap");
        bottomCenter.add(buttonRemove, "center, wrap");

        listChosenAssignees.setBorder(defaultBorder);
        bottomRight.add(labelChosenAssignee, "left, wrap");
        bottomRight.add(listChosenAssignees, "left, width 200px, height 150px, wrap");

        bottom.add(bottomLeft);
        bottom.add(bottomCenter);
        bottom.add(bottomRight);
        bottom.setBorder(defaultBorder);


        // left and right columns
        leftColumn.add(labelStatus, "left, wrap");
        leftColumn.add(dropdownStatus, "left, width 200px, wrap");
        leftColumn.add(labelRequirement, "left, wrap");
        leftColumn.add(dropdownRequirement, "left, width 200px, wrap");
        leftColumn.add(labelStartDate, "left, wrap");
        leftColumn.add(calStartDate, "left, wrap");
        rightColumn.add(labelEstimatedEffort, "left, wrap");
        rightColumn.add(spinnerEstimatedEffort, "left, width 200px, height 25px, wrap");
        rightColumn.add(labelActualEffort, "left, wrap");
        rightColumn.add(spinnerActualEffort, "left, width 200px, height 25px, wrap");
        rightColumn.add(labelDueDate, "left, wrap");
        rightColumn.add(calDueDate, "left, wrap");

        // Populate contentPanel
        contentPanel.add(labelTitle, "wrap");
        contentPanel.add(boxTitle, "growx, pushx, shrinkx, span, wrap");

        contentPanel.add(labelDescription, "wrap");
        contentPanel.add(descrScroll, "growx, pushx, shrinkx, span, height 200px, wmin 10, wrap");

        contentPanel.add(leftColumn, "left, spany, growy, push");
        contentPanel.add(rightColumn, "right, spany, growy, push");

        contentPanel.add(bottom, "left 5, dock south, spany, growy, push");

        setupListeners();

        this.setViewportView(contentPanel);
    }

    /**
     * Sets up the listeners for the buttons in the New Task Information Panel.
     */
    protected void setupListeners() {
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if(!listPossibleAssignees.isSelectionEmpty()) {
                // String[] a = listOfPossibleAssignees;
                // String[] b = listOfChosenAssignees;
                // int[] c = listPossibleAssignees.getSelectedIndices();
                // String[] tempA = new String[a.length - c.length];
                // String[] tempB = new String[b.length + c.length];
                // for(int i = 0; i < b.length; i++) {
                // tempB[i] = b[i];
                // }
                // int counterA = 0;
                // int counterB = b.length;
                // for(int i = 0; i < a.length; i++) {
                // boolean canAdd = true;
                // for(int x : c) {
                // if(x == i) {
                // tempB[counterB] = a[i];
                // counterB++;
                // }
                // else {
                // tempA[counterA] = a[i];
                // counterA++;
                // }
                // }
                // }
                // listOfPossibleAssignees = tempA;
                // listOfChosenAssignees = tempB;
                // //Repaint the GUI
                // listChosenAssignees.repaint();
                // listPossibleAssignees.repaint();
                // }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if(!listChosenAssignees.isSelectionEmpty()) {
                // String[] a = listOfChosenAssignees;
                // String[] b = listOfPossibleAssignees;
                // int[] c = listChosenAssignees.getSelectedIndices();
                // String[] tempA = new String[a.length - c.length];
                // String[] tempB = new String[b.length + c.length];
                // for(int i = 0; i < b.length; i++) {
                // tempB[i] = b[i];
                // }
                // int counterA = 0;
                // int counterB = b.length;
                // for(int i = 0; i < a.length; i++) {
                // boolean canAdd = true;
                // for(int x : c) {
                // if(x == i) {
                // tempB[counterB] = a[i];
                // counterB++;
                // }
                // else {
                // tempA[counterA] = a[i];
                // counterA++;
                // }
                // }
                // }
                // listOfPossibleAssignees = tempB;
                // listOfChosenAssignees = tempA;
                // //Repaint the GUI
                // listChosenAssignees.repaint();
                // listPossibleAssignees.repaint();
                // }
            }

        });

        /**
         * Text Field (Title) Listeners
         */
        boxTitle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.validateTaskInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.validateTaskInfo();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.validateTaskInfo();
            }
        });


        /**
         * Text Field (Description) Listeners
         */
        boxDescription.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.validateTaskInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.validateTaskInfo();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.validateTaskInfo();
            }
        });

    }

    /**
     * Returns the JTextField holding the title.
     *
     * @return JTextField
     */
    public JTextField getTitle() {
        return boxTitle;
    }

    /**
     * Returns the JTextArea holding the description.
     *
     * @return JTextArea
     */
    public JTextArea getDescription() {
        return boxDescription;
    }

    /**
     * Returns the JSpinner holding the estimated effort.
     *
     * @return JSpinner
     */
    public JSpinner getEstimatedEffort() {
        return spinnerEstimatedEffort;
    }

    /**
     * Returns the JSpinner holding the actual effort.
     *
     * @return JSpinner
     */
    public JSpinner getActualEffort() {
        return spinnerEstimatedEffort;
    }

    /**
     * Returns the JComboBox holding the status.
     *
     * @return JComboBox<String>
     */
    public JComboBox<String> getStatus() {
        return dropdownStatus;
    }

    /**
     * Returns the JComboBox holding the Requirement.
     *
     * @return JComboBox<String>
     */
    public JComboBox<String> getRequirement() {
        return dropdownRequirement;
    }

    /**
     * Returns the Start Date.
     *
     * @return Date
     */
    public Date getStartDate() {
        return calStartDate.getDate();
    }

    /**
     * Returns the Due Date.
     *
     * @return Date
     */
    public Date getDueDate() {
        return calDueDate.getDate();
    }

    /**
     * Returns the JList holding the Chosen Members.
     *
     * @return JList<String>
     */
    public List<User> getAssignedUsers() {
        return new ArrayList<User>(Arrays.asList(listOfChosenAssignees));
    }

}
