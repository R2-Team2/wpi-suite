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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;

/**
 * This panel fills the main content area of the tab for this module. It
 * contains one inner JPanel, the BoardPanel.
 * 
 * @author Chris Casola
 */
public class TestMainView {
    
    MainView mainView;
    PostBoardModel mockBoardPanel;
    
    @Before
    public void setup() {
        mockBoardPanel = mock(PostBoardModel.class);
        
        mainView = new MainView(mockBoardPanel);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(mainView.getBoardPanel());
        assertEquals("Enter a message here.", mainView.getBoardPanel().getTxtNewMessage().getText());
    }
}
