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

import org.junit.Before;
import org.junit.Test;

/**
 * @author Robert Smieja
 */
public class TestPostBoard {

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
