/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

// import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
// import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertNull;

// import java.awt.Dimension;
// import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import org.junit.Ignore;
// import org.junit.Before;
import org.junit.Test;

// import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
// import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
// import
// edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
// import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
// import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
// import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
// import
// edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementInformationPanel;
// import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;
// import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.ViewMode;

/**
 * The class <code>AbstractInformationPanelTest</code> contains 
 * tests for the class {@link <code>AbstractInformationPanel</code>}
 *
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 11/23/14 10:22 PM
 * @author R2-Team2
 * @version v0
 */
public class AbstractInformationPanelTest {


    // // mock requirements data for requirements integration testing
    // private final Iteration mockIteration = mock(Iteration.class);
    // private final IterationModel mockIterationModel = mock(IterationModel.class);
    // private final ViewEventController mockViewEventController = mock(ViewEventController.class);
    // private final Requirement mockRequirement = mock(Requirement.class);
    // private final RequirementModel mockRequirementModel = mock(RequirementModel.class);
    // private final RequirementPanel mockRequirementPanel = mock(RequirementPanel.class);
    // private final ViewMode mode = ViewMode.EDITING;


    //
    // /**
    // * Method requirementsImportTest. tests constructors to assert that requirements code has been
    // * imported properly.
    // */
    // @Test
    // public void requirementsImportTest() {
    // final RequirementInformationPanel newPanel =
    // new RequirementInformationPanel(this.mockRequirementPanel, this.mode,
    // this.mockRequirement);
    //
    // assertNotNull(newPanel);
    // assertNotNull(this.mockRequirement);
    // assertEquals(this.mockRequirement.getParentID(), -1);
    // assertEquals(this.mockIteration.toString(), "");
    // assertEquals(this.mode, ViewMode.EDITING);
    // assertEquals(this.mockRequirement.getStatus(), RequirementStatus.NEW);
    // assertEquals(this.mockIterationModel.getBacklog(), this.mockIteration);
    // assertEquals(new Dimension(500, 200), newPanel.getMinimumSize());
    // assertNotEquals(this.mockRequirement.getParentID(), 10000);
    // assertNotEquals(this.mockIteration.toString(), "An arbitrary string.");
    // assertNotEquals(this.mode, ViewMode.CREATING);
    // assertNotEquals(this.mockRequirement.getStatus(), RequirementStatus.NEW);
    // assertNotEquals(this.mockIterationModel.getBacklog(), this.mockIteration);
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

