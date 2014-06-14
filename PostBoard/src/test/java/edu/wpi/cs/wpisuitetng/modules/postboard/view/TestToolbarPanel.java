/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.postboard.view;

import static edu.wpi.cs.wpisuitetng.modules.postboard.TestHelpers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;

/**
 * @author Robert Smieja
 */
public class TestToolbarPanel {
    
    PostBoardModel mockBoardModel;
    
    ToolbarPanel toolbarPanel;
    
    @Before
    public void setup() {
        mockBoardModel = mock(PostBoardModel.class);
        
        toolbarPanel = new ToolbarPanel(mockBoardModel);
    }
    
    @Test
    public void testConstructor() {
        assertFalse(toolbarPanel.isOpaque());
        assertNotNull(toolbarPanel.getBtnRefresh());
        
        assertEquals(1, toolbarPanel.getBtnRefresh().getActionListeners().length);
        
        assertTrue(contains(toolbarPanel.getComponents(), toolbarPanel.getBtnRefresh()));
    }
}
