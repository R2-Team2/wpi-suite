/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.network.Network;

/**
 * Tests getters and setters with control logic
 * TODO: Control logic should NOT be in a model, it should be in a controller!
 * 
 * @author Robert Smieja
 * @version $Revision: 1.0 $
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Network.class, IterationModel.class, RequirementModel.class })
public class TestRequirement {
    
    Network mockNetwork;
    IterationModel mockIterationModel;
    RequirementModel mockRequirementModel;
    
    Gson mockParser;
    Requirement mockRequirement;
    
    Requirement requirement;
    
    /**
     * Method setUp.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        mockStatic(Network.class);
        mockStatic(IterationModel.class);
        mockStatic(RequirementModel.class);
        
        mockNetwork = mock(Network.class);
        mockIterationModel = mock(IterationModel.class);
        mockRequirementModel = mock(RequirementModel.class);
        
        when(Network.getInstance()).thenReturn(mockNetwork);
        when(IterationModel.getInstance()).thenReturn(mockIterationModel);
        when(RequirementModel.getInstance()).thenReturn(mockRequirementModel);
        
        mockParser = mock(Gson.class);
        mockRequirement = mock(Requirement.class);
        
        requirement = new Requirement();
        Requirement.parser = mockParser;
    }
    
    @Test
    public void testConstructor() {
        assertEquals("", requirement.getName());
        assertEquals("", requirement.getRelease());
        assertEquals(RequirementStatus.NEW, requirement.getStatus());
        assertEquals(RequirementPriority.BLANK, requirement.getPriority());
        assertEquals(0, requirement.getEstimate());
        assertEquals(0, requirement.getEffort());
        assertEquals("Backlog", requirement.getIteration());
        assertEquals(RequirementType.BLANK, requirement.getType());
        assertEquals(-1, requirement.getParentID());
        assertNotNull(requirement.getHistory());
        assertTrue(requirement.getHistory().getHistory().isEmpty());
        assertNotNull(requirement.getNotes());
        assertTrue(requirement.getNotes().getNotes().isEmpty());
        assertNotNull(requirement.getTasks());
        assertTrue(requirement.getTasks().isEmpty());
        assertNotNull(requirement.getTests());
        assertTrue(requirement.getTests().isEmpty());
        assertNotNull(requirement.getAttachments());
        assertTrue(requirement.getAttachments().isEmpty());
    }
    
    @Test
    public void testFromGson() {
        when(mockParser.fromJson("Test JSON", Requirement.class)).thenReturn(mockRequirement);
        
        Requirement result = Requirement.fromJson("Test JSON");
        
        assertEquals(mockRequirement, result);
    }
    
    @Test
    public void testFromJsonArray() {
        
    }
    
    @Test
    public void testToJson() {
        
    }
    
    @Test
    public void testSetName() {
        
    }
    
    @Test
    public void testSetRelease() {
        
    }
    
    @Test
    public void testSetStatus() {
        
    }
    
    @Test
    public void testSetDescription() {
        
    }
    
    @Test
    public void testGetChildEstimate() {
        
    }
    
    @Test
    public void testSetEstimate() {
        
    }
    
    @Test
    public void testSetPriority() {
        
    }
    
    @Test
    public void testSetType() {
        
    }
    
    @Test
    public void testAddNote() {
        
    }
    
    @Test
    public void testRemoveTask() {
        
    }
    
    @Test
    public void testAddTest() {
        
    }
    
    @Test
    public void testUpdateTestStatus() {
        
    }
    
    @Test
    public void testRemoveTest() {
        
    }
    
    @Test
    public void testRemoveAttachment() {
        
    }
    
    @Test
    public void testSetIteration() {
        
    }
    
    @Test
    public void testSetParentID() {
        
    }
    
    @Test
    public void testHasAncestor() {
        
    }
    
    @Test
    public void testIsAncestor() {
        
    }
    
    @Test
    public void testCopyFrom() {
        
    }
}
