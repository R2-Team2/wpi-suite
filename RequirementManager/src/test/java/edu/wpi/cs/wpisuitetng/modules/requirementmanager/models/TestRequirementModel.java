/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.AddRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author Robert Smieja
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.swing.*")
@PrepareForTest({ ViewEventController.class, AddRequirementController.class })
public class TestRequirementModel {
    ViewEventController mockViewEventController;
    AddRequirementController mockAddRequirementController;
    Requirement mockRequirement;
    Requirement[] mockRequirementArray;
    List<Requirement> mockRequirementList;
    
    RequirementModel requirementModel;
    
    @Before
    public void setUp() throws Exception {
        mockStatic(ViewEventController.class);
        mockStatic(AddRequirementController.class);
        
        mockViewEventController = mock(ViewEventController.class);
        mockAddRequirementController = mock(AddRequirementController.class);
        mockRequirement = mock(Requirement.class);
        
        mockRequirementArray = new Requirement[1];
        mockRequirementArray[0] = mockRequirement;
        
        mockRequirementList = new ArrayList<Requirement>();
        mockRequirementList.add(mockRequirement);
        
        when(ViewEventController.getInstance()).thenReturn(mockViewEventController);
        when(AddRequirementController.getInstance()).thenReturn(mockAddRequirementController);
        
        requirementModel = new RequirementModel();
    }
    
    @Test
    public void testAddRequirement() {
        requirementModel.addRequirement(mockRequirement);
        
        assertEquals(1, requirementModel.getSize());
        
        verify(mockAddRequirementController, times(1)).addRequirement(mockRequirement);
        verify(mockViewEventController, times(1)).refreshTable();
        verify(mockViewEventController, times(1)).refreshTree();
    }
    
    @Test
    public void testRemoveRequirement() {
        when(mockRequirement.getId()).thenReturn(3);
        
        requirementModel.addRequirement(mockRequirement);
        
        assertEquals(1, requirementModel.getSize());
        
        verify(mockAddRequirementController, times(1)).addRequirement(mockRequirement);
        verify(mockViewEventController, times(1)).refreshTable();
        verify(mockViewEventController, times(1)).refreshTree();
        
        requirementModel.removeRequirement(3);
        
        assertEquals(0, requirementModel.getSize());
        
        verify(mockViewEventController, times(2)).refreshTable();
        verify(mockViewEventController, times(2)).refreshTree();
    }
    
    @Test
    public void testAddRequirements() throws Exception {
        requirementModel = spy(requirementModel);
        
        doNothing().when(requirementModel, "fireIntervalAdded", requirementModel, 0, 0);
        
        requirementModel.addRequirements(mockRequirementArray);
        
        verifyPrivate(requirementModel, times(1)).invoke("fireIntervalAdded", requirementModel, 0, 0);
        verify(mockViewEventController, times(1)).refreshTable();
        verify(mockViewEventController, times(1)).refreshTree();
    }
    
    @Test
    public void testGetRequirements() {
        requirementModel.addRequirement(mockRequirement);
        
        assertEquals(1, requirementModel.getSize());
        
        verify(mockAddRequirementController, times(1)).addRequirement(mockRequirement);
        verify(mockViewEventController, times(1)).refreshTable();
        verify(mockViewEventController, times(1)).refreshTree();
        
        List<Requirement> result = requirementModel.getRequirements();
        
        assertEquals(mockRequirementList, result);
    }
    
    @Test
    public void testSerialVersionUID() {
        assertEquals(8609340016893431330L, RequirementModel.serialVersionUID);
    }
}
