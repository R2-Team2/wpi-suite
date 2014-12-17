/**
 * ****************************************************************************** Copyright (c) 2013
 * WPI-Suite All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team R2-Team2
 **/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.Comment;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.CommentList;


/**
 * The class <code>CommentTest</code> contains tests for the class {@link <code>Comment</code>}
 *
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 12/1/14 10:08 PM
 * @author R2-Team2
 * @version v0
 */
public class CommentTest extends TestCase {

    Comment defaultComment = new Comment(1, "User", 1, "Tester message");
    CommentList defaultList = new CommentList();

    /**
     * Construct new test instance
     *
     * @param name the test name
     */
    public CommentTest(String name) {
        super(name);
    }

    /**
     * To be run before every test.
     */
    @Before
    public void setupTest() {}

    /**
     * To be run after every test.
     */
    @After
    public void teardownTest() {}

    /**
     * Test constructor.
     */
    @Test
    public void testConstructor() {
        final Comment sameAsDefaultComment = new Comment(1, "User", 1, "Tester message");
        final Comment differentFromDefaultComment =
                new Comment(2, "NotUser", 2000, "Not the tester message.");
        assertTrue(defaultComment.equals(sameAsDefaultComment));
        assertFalse(defaultComment.equals(differentFromDefaultComment));
    }

    /**
     * Test list.
     */
    @Test
    public void testList() {
        final Comment first = defaultList.add("First message");
        final Comment second = defaultList.add("Second message");
        assertTrue(second.getMessage().equals("Second message"));
        assertTrue(first.getMessage().equals("First message"));
        assertFalse(first.getMessage().equals("Second message"));
        assertFalse(second.getMessage().equals("First message"));
    }

    @Test
    public void testHashCode() {
        Comment testComment = new Comment(1, "User", 1, "Tester message");
        assertEquals(testComment.hashCode(), defaultComment.hashCode());
    }
}

/*
 * $CPS$ This comment was generated by CodePro. Do not edit it. patternId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern strategyId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase additionalTestNames =
 * assertTrue = false callTestMethod = true createMain = false createSetUp = false createTearDown =
 * false createTestFixture = false createTestStubs = false methods = package =
 * edu.wpi.cs.wpisuitetng.modules.taskmanager.models package.sourceFolder =
 * TaskManager/src/test/java superclassType = junit.framework.TestCase testCase = CommentTest
 * testClassType = edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.Comment
 */
