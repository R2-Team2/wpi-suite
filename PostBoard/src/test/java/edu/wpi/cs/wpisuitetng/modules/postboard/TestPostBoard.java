/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.postboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.network.Network;

/**
 * Example of how to test a module using mocks, so intermittent tests can be
 * avoided
 * 
 * @author Robert Smieja
 */
public class TestPostBoard {
    
    //In a UnitTest, you want to remove all 'dependencies'
    //A dependency is anything that is NOT the 'Unit' under test
    //In this test, the 'Unit' is PostBoard, and just about everything else is a dependency
    
    //To make mocks:
    //Use the static Mock.mock() method:
    Network mockNetwork = mock(Network.class);
    
    //The above examples are not used in this Unit test,
    //but more examples can be found throughout all projects
    
    PostBoard postBoard;
    
    @Before
    public void setup() {
        postBoard = new PostBoard();
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(postBoard.boardModel);
        assertNotNull(postBoard.tabs);
        assertNotNull(postBoard.toolbarView);
        assertNotNull(postBoard.mainView);
        assertNotNull(postBoard.tab1);
    }
    
    @Test
    public void testGetName() {
        assertEquals("PostBoard", postBoard.getName());
    }
    
    @Test
    public void testGetTabs() {
        assertEquals(postBoard.tabs, postBoard.getTabs());
    }
}
