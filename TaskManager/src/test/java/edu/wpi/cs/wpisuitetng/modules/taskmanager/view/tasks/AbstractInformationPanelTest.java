/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXDatePicker;
import org.junit.Ignore;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;

/**
 * The class <code>AbstractInformationPanelTest</code> contains tests for the class {@link <code>AbstractInformationPanel</code>}
 *
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 11/23/14 10:22 PM
 * @author R2-Team2
 * @version v0
 */
public class AbstractInformationPanelTest {

    private DefaultListModel<User> chosenAssigneeModel;
    private DefaultListModel<User> possibleAssigneeModel;
    private String[] listOfStatuses = new String[] {new TaskStatus("New").toString(),
            new TaskStatus("Selected for Development").toString(),
            new TaskStatus("Currently in Development").toString(),
            new TaskStatus("Completed").toString()}; // needs to be list of TaskStatus
    private List<String> strListOfRequirements = new ArrayList<String>();
    private Border defaultBorder;
    private JTextField boxTitle;
    private JTextArea boxDescription;
    private JComboBox<String> dropdownStatus;
    private JComboBox<String> dropdownRequirement;
    private JList<User> chosenAssigneeList;
    private JList<User> possibleAssigneeList;
    private JSpinner spinnerEstimatedEffort;
    private JSpinner spinnerActualEffort;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton buttonOpenRequirement;
    private JXDatePicker calStartDate;
    private JXDatePicker calDueDate;
    private JLabel labelStartDate = new JLabel("Start Date: ");
    private JLabel labelDueDate = new JLabel("Due Date: ");
    private ImageIcon icon;

    /**
     * Method requirementsImportTest. tests constructors to assert that requirements code has been
     * imported properly.
     */
    // @Test
    // @Ignore
    // public void requirementsImportTest() {
    // final RequirementInformationPanel newPanel =
    // new RequirementInformationPanel(mockRequirementPanel, mode,
    // mockRequirement);
    //
    // assertNotNull(newPanel);
    // assertNotNull(mockRequirement);
    // assertEquals(mockRequirement.getParentID(), -1);
    // assertEquals(mockIteration.toString(), "");
    // assertEquals(mode, ViewMode.EDITING);
    // assertEquals(mockRequirement.getStatus(), RequirementStatus.NEW);
    // assertEquals(mockIterationModel.getBacklog(), mockIteration);
    // assertEquals(new Dimension(500, 200), newPanel.getMinimumSize());
    // assertNotEquals(mockRequirement.getParentID(), 10000);
    // assertNotEquals(mockIteration.toString(), "An arbitrary string.");
    // assertNotEquals(mode, ViewMode.CREATING);
    // assertNotEquals(mockRequirement.getStatus(), RequirementStatus.NEW);
    // assertNotEquals(mockIterationModel.getBacklog(), mockIteration);
    // assertNotEquals(new Dimension(200, 500), newPanel.getMinimumSize());
    // }

    /**
     * Checks whether the calendar image can be loaded.
     */
    @Test
    public void calendarImageTest() {
        final ImageIcon img = new ImageIcon("calendar.png");
        assertNotNull("Image can't be loaded", img);
    }

    /**
     * Checks whether dates are not set when creating a task
     */
    @Test
    @Ignore
    public void datesInitiallyEmptyTest() {
        final NewTaskPanel ntp = new NewTaskPanel();
        final NewTaskInformationPanel ntip = new NewTaskInformationPanel(ntp);

        assertNull(ntip.calDueDate.getDate());
        assertNull(ntip.calStartDate.getDate());
    }

    /**
     * Checks whether the calendar image can be loaded.
     */
    @Test
    @Ignore
    public void taskInfoValidationTest() {
        final NewTaskPanel ntp = new NewTaskPanel();
        final NewTaskInformationPanel newTaskInfoPanel = new NewTaskInformationPanel(ntp);
        final NewTaskButtonPanel ntbp = new NewTaskButtonPanel(ntp);

        newTaskInfoPanel.boxTitle.setText("  ");
        newTaskInfoPanel.boxDescription.setText("sss");
        assertFalse("Whitespace task title", ntbp.isTaskInfoValid());

        newTaskInfoPanel.boxTitle.setText("");
        newTaskInfoPanel.boxDescription.setText("sss");
        assertFalse("Empty task title", ntbp.isTaskInfoValid());

        newTaskInfoPanel.boxTitle.setText("sss");
        newTaskInfoPanel.boxDescription.setText("");
        assertFalse("Empty task description", ntbp.isTaskInfoValid());

        newTaskInfoPanel.boxTitle.setText("sss");
        newTaskInfoPanel.boxDescription.setText("   ");
        assertFalse("Whitespace task description", ntbp.isTaskInfoValid());
    }

    // /**
    // * Method cleanup. a teardown to be run After each test.
    // */
    // @After
    // public void cleanup() {
    // // from requirements module
    // IterationModel.setInstance(null);
    // ViewEventController.setInstance(null);
    // }

}
/*
 * $CPS$ This comment was generated by CodePro. Do not edit it. patternId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern strategyId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase additionalTestNames =
 * assertTrue = false callTestMethod = true createMain = false createSetUp = false createTearDown =
 * false createTestFixture = false createTestStubs = false methods = package =
 * edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks package.sourceFolder =
 * TaskManager/src/test/java superclassType = junit.framework.TestCase testCase =
 * AbstractInformationPanelTest testClassType =
 * edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractInformationPanel
 */

