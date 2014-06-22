/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations.IterationPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;

/**
 * TODO: Since ViewEventController is in PrepareForTest, Code Coverage will not
 * be accurate.
 * Code should be refactored so PowerMock can be replaced.
 * 
 * @author Robert Smieja
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "javax.swing" })
@PrepareForTest({ IterationModel.class, UpdateRequirementController.class, RequirementPanel.class, ViewEventController.class, IterationPanel.class })
public class TestViewEventController {
    IterationModel mockIterationModel;
    UpdateRequirementController mockUpdateRequirementController;
    
    MainView mockMainView;
    RequirementPanel mockRequirementPanel;
    IterationPanel mockIterationPanel;
    
    ViewEventController viewEventController;
    
    @Before
    public void setup() throws Exception {
        mockStatic(IterationModel.class);
        mockStatic(UpdateRequirementController.class);
        
        mockUpdateRequirementController = mock(UpdateRequirementController.class);
        mockIterationModel = mock(IterationModel.class);
        
        when(UpdateRequirementController.getInstance()).thenReturn(mockUpdateRequirementController);
        when(IterationModel.getInstance()).thenReturn(mockIterationModel);
        
        mockMainView = mock(MainView.class);
        mockRequirementPanel = mock(RequirementPanel.class);
        mockIterationModel = mock(IterationModel.class);
        
        whenNew(RequirementPanel.class).withAnyArguments().thenReturn(mockRequirementPanel);
        whenNew(IterationPanel.class).withAnyArguments().thenReturn(mockIterationPanel);
        
        viewEventController = new ViewEventController();
        viewEventController.setMainView(mockMainView);
    }
    
    @Test
    public void testCreateRequirement() {
        viewEventController.createRequirement();
        
        verify(mockMainView, times(1)).addTab("New Req.", null, mockRequirementPanel, "New Requirement");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockRequirementPanel);
    }
    
    @Test
    public void testCreateIteration() {
        viewEventController.createIteration();
        
        verify(mockMainView, times(1)).addTab("New Iter.", null, mockIterationPanel, "New Iteration");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockIterationPanel);
    }
    
    @Test
    public void testEditIteration() {
        fail();
    }
    
    @Test
    public void testCreatePieChart() {
        fail();
    }
    
    @Test
    public void testCreateBarChart() {
        fail();
    }
    
    @Test
    public void testCreateChildRequirement() {
        fail();
    }
    
    @Test
    public void testEditRequirement() {
        fail();
    }
    
    @Test
    public void testToggleEditingTable() {
        fail();
    }
    
    @Test
    public void testRemoveTab() {
        fail();
    }
    
    @Test
    public void testRefreshTable() {
        fail();
    }
    
    @Test
    public void testGetTableSelection() {
        fail();
    }
    
    @Test
    public void testAssignSelectionToBacklog() {
        fail();
    }
    
    @Test
    public void testEditSelectedRequirement() {
        fail();
    }
    
    @Test
    public void testCloseAllTabs() {
        fail();
    }
    
    @Test
    public void testCloseOthers() {
        fail();
    }
    
    @Test
    public void testRefreshEditRequirementPanel() {
        fail();
    }
    
    @Test
    public void testEditSelectedIteration() {
        fail();
    }
}
