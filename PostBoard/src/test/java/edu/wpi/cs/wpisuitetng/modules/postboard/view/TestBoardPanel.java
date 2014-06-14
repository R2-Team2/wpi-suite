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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;

/**
 * @author Robert Smieja
 */
public class TestBoardPanel {
    
    PostBoardModel mockPostBoardModel;
    
    BoardPanel boardPanel;
    
    @Before
    public void setup() {
        mockPostBoardModel = mock(PostBoardModel.class);
        
        boardPanel = new BoardPanel(mockPostBoardModel);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(mockPostBoardModel, boardPanel.getLstBoardModel());
        
        assertNotNull(boardPanel.getLstBoard());
        //        assertEquals(11, boardPanel.getLstBoard().getFont().getStyle());
        
        assertNotNull(boardPanel.getLayout());
        assertTrue(boardPanel.getLayout() instanceof BoxLayout);
        assertEquals(boardPanel, ((BoxLayout) boardPanel.getLayout()).getTarget());
        assertEquals(BoxLayout.PAGE_AXIS, ((BoxLayout) boardPanel.getLayout()).getAxis());
        
        assertNotNull(boardPanel.getBtnSubmit());
        assertEquals(4, boardPanel.getTxtNewMessage().getMouseListeners().length); //Why is this 4? Is this consistent?
        assertEquals(Component.CENTER_ALIGNMENT, boardPanel.getBtnSubmit().getAlignmentX(), 0.1F);
        assertEquals("Submit", boardPanel.getBtnSubmit().getText());
        
        assertNotNull(boardPanel.getLstScrollPane());
        assertEquals(new Dimension(500, 400), boardPanel.getLstScrollPane().getPreferredSize());
        
        assertTrue(contains(boardPanel.getComponents(), boardPanel.getLstScrollPane()));
        assertTrue(contains(boardPanel.getComponents(), boardPanel.getTxtNewMessage()));
        assertTrue(contains(boardPanel.getComponents(), boardPanel.getBtnSubmit()));
    }
    
    @Test
    public void testSerialVersionUID() {
        assertEquals(6568533712473785570L, BoardPanel.getSerialversionuid());
    }
}
