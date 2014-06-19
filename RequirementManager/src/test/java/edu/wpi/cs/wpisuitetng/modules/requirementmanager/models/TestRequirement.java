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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.AcceptanceTest;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.NoteList;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
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
@PrepareForTest({ Network.class, IterationModel.class, RequirementModel.class, Gson.class })
public class TestRequirement {
    
    Network mockNetwork;
    IterationModel mockIterationModel;
    RequirementModel mockRequirementModel;
    
    TransactionHistory mockHistory;
    NoteList mockNotes;
    ArrayList<AcceptanceTest> mockTests;
    
    Gson mockParser;
    Requirement mockRequirement;
    Requirement[] mockRequirements;
    
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
        
        mockHistory = mock(TransactionHistory.class);
        mockNotes = mock(NoteList.class);
        mockTests = new ArrayList<AcceptanceTest>();
        
        when(Network.getInstance()).thenReturn(mockNetwork);
        when(IterationModel.getInstance()).thenReturn(mockIterationModel);
        when(RequirementModel.getInstance()).thenReturn(mockRequirementModel);
        
        mockParser = mock(Gson.class);
        mockRequirement = mock(Requirement.class);
        
        requirement = new Requirement();
        Requirement.parser = mockParser;
        
        mockRequirements = new Requirement[1];
        mockRequirements[0] = mockRequirement;
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
        when(mockParser.fromJson("Test JSON Array", Requirement[].class)).thenReturn(mockRequirements);
        
        Requirement[] result = Requirement.fromJsonArray("Test JSON Array");
        
        assertArrayEquals(mockRequirements, result);
    }
    
    @Test
    public void testToJson() {
        when(mockParser.toJson(requirement, Requirement.class)).thenReturn("Test To JSON");
        
        String result = requirement.toJson();
        
        assertEquals("Test To JSON", result);
    }
    
    @Test
    public void testSetName() {
        requirement.setHistory(mockHistory);
        
        requirement.setName("TestName");
        
        assertEquals("TestName", requirement.getName());
        verify(mockHistory, times(1)).add("Name changed from  to TestName");
    }
    
    @Test
    public void testSetName_SameName() {
        requirement.setHistory(mockHistory);
        
        requirement.setName("TestName");
        
        assertEquals("TestName", requirement.getName());
        verify(mockHistory, times(1)).add("Name changed from  to TestName");
        
        requirement.setName("TestName");
        
        //Make sure nothing changed
        assertEquals("TestName", requirement.getName());
        verify(mockHistory, times(1)).add("Name changed from  to TestName");
    }
    
    @Test
    public void testSetName_Over100Characters() {
        String maxChars = "";
        for (int i = 0; i < 100; i++) {
            maxChars += 'a';
        }
        
        String overMaxChars = maxChars;
        overMaxChars += 'a';
        
        requirement.setHistory(mockHistory);
        
        requirement.setName(overMaxChars);
        
        assertEquals(maxChars, requirement.getName());
        verify(mockHistory, times(1)).add("Name changed from  to " + maxChars);
    }
    
    @Test
    public void testSetName_wasCreated() {
        requirement.setHistory(mockHistory);
        requirement.setWasCreated(true);
        
        requirement.setName("TestName");
        
        assertEquals("TestName", requirement.getName());
        verify(mockHistory, times(0)).add("Name changed from  to TestName");
    }
    
    @Test
    public void testSetName_wasCreatedAndOver100Characters() {
        String maxChars = "";
        for (int i = 0; i < 100; i++) {
            maxChars += 'a';
        }
        
        String overMaxChars = maxChars;
        overMaxChars += 'a';
        
        requirement.setHistory(mockHistory);
        requirement.setWasCreated(true);
        
        requirement.setName(overMaxChars);
        
        assertEquals(maxChars, requirement.getName());
        verify(mockHistory, times(0)).add("Name changed from  to " + maxChars);
    }
    
    @Test
    public void testSetRelease() {
        requirement.setHistory(mockHistory);
        
        requirement.setRelease("TestRelease");
        
        assertEquals("TestRelease", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number set to 'TestRelease'");
    }
    
    @Test
    public void testSetRelease_wasCreated() {
        requirement.setHistory(mockHistory);
        requirement.setWasCreated(true);
        
        requirement.setRelease("TestRelease");
        
        assertEquals("TestRelease", requirement.getRelease());
        verify(mockHistory, times(0)).add("Release Number set to 'TestRelease'");
    }
    
    @Test
    public void testSetRelease_originalReleaseNotEmpty() {
        requirement.setHistory(mockHistory);
        
        requirement.setRelease("TestRelease");
        
        assertEquals("TestRelease", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number set to 'TestRelease'");
        
        requirement.setRelease("TestRelease2");
        
        assertEquals("TestRelease2", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number changed from 'TestRelease' to 'TestRelease2'");
    }
    
    @Test
    public void testSetRelease_newReleaseEmpty() {
        requirement.setHistory(mockHistory);
        
        requirement.setRelease("TestRelease");
        
        assertEquals("TestRelease", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number set to 'TestRelease'");
        
        requirement.setRelease("");
        
        assertEquals("", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number set to blank from 'TestRelease'");
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
    public void testCopyFrom() throws Exception {
        requirement.setWasCreated(true);
        
        requirement.setDescription("TestDesc");
        requirement.setName("TestName");
        requirement.setEffort(1);
        requirement.setEstimate(2);
        requirement.setIteration("TestIteration");
        requirement.setPriority(RequirementPriority.BLANK);
        requirement.setRelease("TestRelease");
        requirement.setStatus(RequirementStatus.NEW);
        requirement.setType(RequirementType.BLANK);
        requirement.setHistory(mockHistory);
        requirement.setNotes(mockNotes);
        requirement.setTests(mockTests);
        requirement.setParentID(-1);
        
        Requirement newRequirement = new Requirement();
        
        newRequirement.copyFrom(requirement);
        
        assertEquals("TestDesc", newRequirement.getDescription());
        assertEquals("TestName", newRequirement.getName());
        assertEquals(1, newRequirement.getEffort());
        assertEquals(2, newRequirement.getEstimate());
        assertEquals("TestIteration", newRequirement.getIteration());
        assertEquals(RequirementPriority.BLANK, newRequirement.getPriority());
        assertEquals("TestRelease", newRequirement.getRelease());
        assertEquals(RequirementStatus.NEW, newRequirement.getStatus());
        assertEquals(RequirementType.BLANK, newRequirement.getType());
        assertEquals(mockHistory, newRequirement.getHistory());
        assertEquals(mockNotes, newRequirement.getNotes());
        assertEquals(mockTests, newRequirement.getTests());
        assertEquals(-1, newRequirement.getParentID());
        
    }
}
