/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * The class <code>SearchBarGroupTest</code> contains 
 * tests for the class {@link <code>SearchBarGroup</code>}
 *
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 12/11/14 9:41 PM
 * @author dbogatov
 * @version $Revision$
 */
public class SearchBarGroupTest {

    /**
     * Checks whether the cancel button clears the test field and check boxes.
     */
    @Test
    public void cancelButtonTest() {
        final SearchBarGroup sbg = new SearchBarGroup();
        sbg.chckbxArchieved.setSelected(true);
        sbg.chckbxAssignee.setSelected(true);
        sbg.chckbxDescription.setSelected(false);
        sbg.chckbxRequirements.setSelected(false);

        sbg.resetFields();

        System.out.print(sbg.chckbxArchieved.isSelected() + 
                " " + sbg.chckbxAssignee.isSelected() + " " +
                sbg.chckbxDescription.isSelected() + " " + sbg.chckbxRequirements.isSelected());
        
        assertTrue(!(sbg.chckbxArchieved.isSelected() || sbg.chckbxAssignee.isSelected()
                || sbg.chckbxDescription.isSelected() || sbg.chckbxRequirements.isSelected()));
    }
}

/*
 * $CPS$ This comment was generated by CodePro. Do not edit it. patternId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern strategyId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase additionalTestNames =
 * assertTrue = false callTestMethod = true createMain = false createSetUp = false createTearDown =
 * false createTestFixture = false createTestStubs = false methods = package =
 * edu.wpi.cs.wpisuitetng.modules.taskmanager.view package.sourceFolder = TaskManager/src/test/java
 * superclassType = junit.framework.TestCase testCase = SearchBarGroupTest testClassType =
 * SearchBarGroup
 */
