/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Dimension;

import javax.swing.DefaultListModel;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementInformationPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.ViewMode;

/**
 * The class <code>AbstractInformationPanelTest</code> contains tests for the class {@link <code>AbstractInformationPanel</code>}
 *
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 11/23/14 10:22 PM
 * @author R2-Team2
 * @version v0
 */
/**
 * Description
 *
 * @author Evan
 * @version Nov 24, 2014
 */
public class AbstractInformationPanelTest extends TestCase {


    // mock requirements data for requirements integration testing
    private final Iteration mockIteration = mock(Iteration.class);
    private final IterationModel mockIterationModel = mock(IterationModel.class);
    private final ViewEventController mockViewEventController = mock(ViewEventController.class);
    private final Requirement mockRequirement = mock(Requirement.class);
    private final RequirementModel mockRequirementModel = mock(RequirementModel.class);
    private final RequirementPanel mockRequirementPanel = mock(RequirementPanel.class);
    private final ViewMode mode = ViewMode.EDITING;

    /**
     * Construct new test instance
     *
     * @param name the test name
     */
    public AbstractInformationPanelTest(String name) {
        super(name);
    }



    /**
     * Method testSetup. anything to be run Before each of the tests.
     */
    @Before
    public void testSetup() {

        // requirements integration testing (from requirements module)
        when(mockRequirement.getParentID()).thenReturn(-1);
        when(mockRequirement.getStatus()).thenReturn(RequirementStatus.NEW);
        when(mockRequirementModel.getPossibleParents(any(Requirement.class))).thenReturn(
                new DefaultListModel<Requirement>());
        when(mockIteration.toString()).thenReturn("");
        when(mockIterationModel.getBacklog()).thenReturn(mockIteration);

        RequirementModel.setInstance(mockRequirementModel);
        IterationModel.setInstance(mockIterationModel);
        ViewEventController.setInstance(mockViewEventController);
        assertEquals(1, 1);
    }


    /**
     * Method requirementsImportTest. tests constructors to assert that requirements code has been
     * imported properly.
     */
    @Test
    public void requirementsImportTest() {
        final RequirementInformationPanel newPanel =
                new RequirementInformationPanel(mockRequirementPanel, mode, mockRequirement);

        assertNotNull(newPanel);
        assertNotNull(mockRequirement);
        assertEquals(mockRequirement.getParentID(), -1);
        assertEquals(mockIteration.toString(), "");
        assertEquals(mode, ViewMode.EDITING);
        assertEquals(mockRequirement.getStatus(), RequirementStatus.NEW);
        assertEquals(mockIterationModel.getBacklog(), mockIteration);
        assertEquals(new Dimension(500, 200), newPanel.getMinimumSize());
        assertNotEquals(mockRequirement.getParentID(), 10000);
        assertNotEquals(mockIteration.toString(), "An arbitrary string.");
        assertNotEquals(mode, ViewMode.CREATING);
        assertNotEquals(mockRequirement.getStatus(), RequirementStatus.NEW);
        assertNotEquals(mockIterationModel.getBacklog(), mockIteration);
        assertNotEquals(new Dimension(200, 500), newPanel.getMinimumSize());
    }

    /**
     * Method cleanup. a teardown to be run After each test.
     */
    @After
    public void cleanup() {
        // from requirements module
        IterationModel.setInstance(null);
        ViewEventController.setInstance(null);
    }
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
