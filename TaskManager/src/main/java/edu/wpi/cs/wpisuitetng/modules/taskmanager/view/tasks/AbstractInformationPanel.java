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
import java.util.Collections;
import java.util.Comparator; // wpi-38
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXDatePicker;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
// requirement module integration
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.tabs.TaskTabPane;

/**
 * The Class AbstractInformationPanel. This class behaves as an abstract class.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */

@SuppressWarnings("serial")
public class AbstractInformationPanel extends JScrollPane {

    /** The parent panel. */
    protected AbstractTaskPanel parentPanel;

    /** The list of chosen assignees. */
    protected DefaultListModel<User> chosenAssigneeModel;

    /** The list of possible assignees. */
    protected DefaultListModel<User> possibleAssigneeModel;

    /** The list of statuses. */
    protected String[] listOfStatuses = new String[] {new TaskStatus("New").toString(),
            new TaskStatus("Selected for Development").toString(),
            new TaskStatus("Currently in Development").toString(),
            new TaskStatus("Completed").toString(), new TaskStatus("Archived").toString()};
    // the above needs to be converted to a list of TaskStatus

    /** The default border. */
    protected final Border defaultBorder = BorderFactory.createEtchedBorder();

    /** The box title. */
    protected JTextField boxTitle;

    /** The box description. */
    protected JTextArea boxDescription;

    /** The dropdown status. */
    protected JComboBox<String> dropdownStatus;

    /** The dropdown requirement. */
    protected JComboBox<Requirement> dropdownRequirement;

    /** The list chosen assignees. */
    protected JList<User> chosenAssigneeList;

    /** The list possible assignees. */
    protected JList<User> possibleAssigneeList;

    /** The spinner estimated effort. */
    protected JSpinner spinnerEstimatedEffort;

    /** The spinner actual effort. */
    protected JSpinner spinnerActualEffort;

    /** The List of activities **/
    protected JList<String> activities;

    /** The button add. */
    protected JButton buttonAdd;

    /** The button remove. */
    protected JButton buttonRemove;

    /** The button that opens the requirement in RequirementManager. */
    protected JButton buttonOpenRequirement;

    /** The cal start date. */
    protected JXDatePicker calStartDate;

    /** The cal due date. */
    protected JXDatePicker calDueDate;

    /** The cal start label. */
    protected JLabel labelStartDate = new JLabel("Start Date: ");

    /** The cal due label. */
    protected JLabel labelDueDate = new JLabel("Due Date: ");

    /** Calendar Button Dropdown Icon. */
    protected ImageIcon icon;

    /** The requirements. */
    private final List<Requirement> requirements = new ArrayList<Requirement>();

    protected TaskTabPane attributePane;

    private final List<String> activityList = new ArrayList<String>();

    /**
     * Builds the layout.
     */
    @SuppressWarnings("unchecked")
    protected void buildLayout() {
        setMinimumSize(new Dimension(540, 200));
        // Set the Panel
        final ScrollablePanel contentPanel = new ScrollablePanel();
        contentPanel.setLayout(new MigLayout("", "20[]20", "shrink"));

        // get latest list of requirement objects and sort them
        // (code partially from requirements module overviewtreepanel.java)
        final List<Iteration> iterations = IterationModel.getInstance().getIterations();
        Collections.sort(iterations, new IterationComparator());
        requirements.add(new Requirement(-1, "None", "Easter Egg"));
        for (int i = 0; i < iterations.size(); i++) {

            requirements.addAll(iterations.get(i).getRequirements());
            // gets the list of requirements that is associated with the iteration

        }
        Collections.sort(requirements, new RequirementComparator());

        // Instantiate GUI Elements
        // Labels
        final JLabel labelTitle = new JLabel("<html>Title: <font color='red'>*</font></html>");
        final JLabel labelDescription =
                new JLabel("<html>Description: <font color='red'>*</font></html>");
        final JLabel labelStatus = new JLabel("Status: ");
        final JLabel labelEstimatedEffort = new JLabel("Estimated Effort: ");
        final JLabel labelActualEffort = new JLabel("Actual Effort: ");
        final JLabel labelDueDate =
                new JLabel("<html>Due Date <font color='red'>*</font></html>: ");
        final JLabel labelStartDate = new JLabel("<html>Start Date: </html> ");
        final JLabel labelRequirement = new JLabel("Requirement: ");
        final JLabel labelPossibleAssignee = new JLabel("Open Assignees: ");
        final JLabel labelChosenAssignee = new JLabel("Chosen Assignees: ");

        // TODO use a nice icon
        buttonOpenRequirement = new JButton("<");
        // TODO force the button to be this small
        buttonOpenRequirement.setPreferredSize(new Dimension(16, 16));

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
        final Vector<Requirement> reqVec = new Vector<Requirement>();
        reqVec.addAll(requirements);
        dropdownRequirement = new JComboBox<Requirement>(reqVec);

        dropdownRequirement.setEnabled(true);
        dropdownRequirement.setBackground(Color.WHITE);
        dropdownStatus = new JComboBox<String>();
        dropdownStatus.setModel(new DefaultComboBoxModel<String>(listOfStatuses));
        dropdownStatus.setEnabled(true);
        dropdownStatus.setBackground(Color.WHITE);
        // Lists and Models
        chosenAssigneeModel = new DefaultListModel<User>();
        chosenAssigneeList = new JList<User>(chosenAssigneeModel);
        chosenAssigneeList.setCellRenderer(new UserRenderer());
        possibleAssigneeModel = new DefaultListModel<User>();
        possibleAssigneeList = new JList<User>(possibleAssigneeModel);
        possibleAssigneeList.setCellRenderer(new UserRenderer());
        // Spinners
        spinnerEstimatedEffort = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        spinnerActualEffort = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        // Buttons
        buttonAdd = new JButton(">>");
        buttonAdd.setEnabled(false);
        buttonRemove = new JButton("<<");
        buttonAdd.setEnabled(false);
        buttonRemove.setEnabled(false);

        // Calendars
        calStartDate = new JXDatePicker();
        calStartDate.setName("start date");
        calDueDate = new JXDatePicker();
        calDueDate.setName("due date");
        icon = new ImageIcon(this.getClass().getResource("calendar.png"));
        final ImageIcon scaledIcon =
                new ImageIcon(icon.getImage()
                        .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        // Code taken from:
        // http://stackoverflow.com/questions/8406200/swingx-personalize-jxdatepicker
        ((JButton) calStartDate.getComponent(1)).setIcon(scaledIcon);

        ((JButton) calDueDate.getComponent(1)).setIcon(scaledIcon);

        // Setup GUI

        // Setup Columns
        final JPanel leftColumn = new JPanel(new MigLayout());
        final JPanel rightColumn = new JPanel(new MigLayout());

        final JPanel assigneeCell = new JPanel(new MigLayout());

        final JPanel possibleAssigneeCell = new JPanel(new MigLayout());
        final JPanel manageAssigneeCell = new JPanel(new MigLayout());
        final JPanel chosenAssigneeCell = new JPanel(new MigLayout());

        boolean addComCell = false;

        attributePane = new TaskTabPane(getTask(), parentPanel);
        if (getTask() != null) {
            attributePane.loadComments();
            attributePane.loadActivities();
            addComCell = true;
        }

        attributePane.setMaximumSize(new Dimension(600, 400));

        // Assignee view created and populated to the bottom Panel
        possibleAssigneeList.setBorder(defaultBorder);
        possibleAssigneeCell.add(labelPossibleAssignee, "left, wrap");
        possibleAssigneeCell.add(possibleAssigneeList, "left, width 200px, height 150px, wrap");

        manageAssigneeCell.add(buttonAdd, "center, wrap");
        manageAssigneeCell.add(buttonRemove, "center, wrap");

        chosenAssigneeList.setBorder(defaultBorder);
        chosenAssigneeCell.add(labelChosenAssignee, "left, wrap");
        chosenAssigneeCell.add(chosenAssigneeList, "left, width 200px, height 150px, wrap");

        assigneeCell.add(possibleAssigneeCell);
        assigneeCell.add(manageAssigneeCell);
        assigneeCell.add(chosenAssigneeCell);
        assigneeCell.setBorder(defaultBorder);

        // left and right columns
        leftColumn.add(labelStatus, "left, wrap");
        leftColumn.add(dropdownStatus, "left, width 200px, wrap");
        leftColumn.add(labelRequirement, "left, wrap");
        leftColumn.add(dropdownRequirement, "left, width 200px");

        leftColumn.add(buttonOpenRequirement, "left, wrap");
        validateRequirementView();
        dropdownRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateRequirementView();
            }
        });

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

        contentPanel.add(leftColumn, "left, split 2, spanx");
        contentPanel.add(rightColumn, "right, growx, wrap");

        contentPanel.add(assigneeCell, "spanx, growy, wrap");

        if (addComCell) {
            contentPanel.add(attributePane, "spanx, grow, wrap");
        }

        setViewportView(contentPanel);
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
        return spinnerActualEffort;
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
     * @return JComboBox<Requirement>
     */
    // TODO rename this to getRequirementComboBox
    public JComboBox<Requirement> getRequirement() {
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
     * @return List<User>
     */
    public List<User> getAssignedUsers() {
        final List<User> userList = new ArrayList<User>();
        for (int i = 0; i < chosenAssigneeModel.size(); i++) {
            userList.add(chosenAssigneeModel.elementAt(i));
        }
        return userList;
    }

    /**
     * Gets the selected requirement.
     *
     * @return selected requirement object
     * @throws Exception the exception
     */
    private Requirement getSelectedRequirement() throws Exception {
        final Requirement req = (Requirement) dropdownRequirement.getSelectedItem();

        for (Requirement requirement : requirements) {
            if (requirement.getId() == req.getId()) {
                return requirement;
            }
        }

        throw new Exception("Invalid requirement selected");
    }

    /**
     * @param reqId ID number of requirement
     * @return requirement with given ID
     * @throws Exception
     */
    public Requirement findRequirement(int reqId) throws Exception {
        for (Requirement requirement : requirements) {
            if (requirement.getId() == reqId) {
                return requirement;
            }
        }
        throw new Exception("Invalid requirement selected");
    }

    /**
     * Open selected requirement.
     *
     * @throws Exception invalid requirement selected
     */
    public void openSelectedRequirement() throws Exception {
        edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController.getInstance()
                .editRequirement(getSelectedRequirement());
        edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController.getInstance()
                .openRequirementsTab();
    }

    /**
     * Validate requirement view.
     */
    private void validateRequirementView() {
        if (getRequirement() == null
                || ((Requirement) getRequirement().getSelectedItem()).getId() == -1) {
            buttonOpenRequirement.setEnabled(false);
        } else {
            buttonOpenRequirement.setEnabled(true);
        }
    }

    /**
     * Disables all of the text fields based on boolean io.
     *
     * @param io is a flag that if true disables fields, if false enables all fields.
     */
    public void disableAll(Boolean io) {
        io = !io;
        boxTitle.setEnabled(io);
        boxDescription.setEnabled(io);
        dropdownStatus.setEnabled(io);
        // requirement
        chosenAssigneeList.setEnabled(io);
        possibleAssigneeList.setEnabled(io);
        calStartDate.setEnabled(io);
        calDueDate.setEnabled(io);
        spinnerEstimatedEffort.setEnabled(io);
        spinnerActualEffort.setEnabled(io);
    }

    /**
     * opens selected requirement. May be overridden
     */
    protected void openRequirement() {
        try {
            parentPanel.openSelectedRequirement();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Gets the task.
     *
     * @return Task
     */
    public Task getTask() {
        return parentPanel.aTask;
    }

    /**
     * Validate assignee buttons
     */
    public void validateAssigneeButtons() {};

}


/**
 * @version legacy
 * @author Kevin from the requirements manager sorts the Iterations by date
 */
class IterationComparator implements Comparator<Iteration> {
    @Override
    public int compare(Iteration I1, Iteration I2) {
        int result = 0;
        if (I1.getStart() == null) {
            result = -1;
        } else if (I2.getStart() == null) {
            result = 1;
        } else {
            result = I1.getStart().getDate().compareTo(I2.getStart().getDate());
        }
        return result;
    }
}


/**
 * @version legacy
 * @author Kevin from the requirements manager sorts Requirements by name
 */
class RequirementComparator implements Comparator<Requirement> {
    @Override
    public int compare(Requirement R1, Requirement R2) {
        return R1.getName().compareTo(R2.getName());
    }

}
