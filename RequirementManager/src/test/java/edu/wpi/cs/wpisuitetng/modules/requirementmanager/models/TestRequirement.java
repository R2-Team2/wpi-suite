/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Robert Smieja
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.AcceptanceTest;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Attachment;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.DevelopmentTask;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.NoteList;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TestStatus;
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
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ Network.class, IterationModel.class, RequirementModel.class, Gson.class })
public class TestRequirement {

    Network mockNetwork = mock(Network.class);
    IterationModel mockIterationModel = mock(IterationModel.class);
    RequirementModel mockRequirementModel = mock(RequirementModel.class);

    TransactionHistory mockHistory = mock(TransactionHistory.class);
    NoteList mockNotes = mock(NoteList.class);
    AcceptanceTest mockTest = mock(AcceptanceTest.class);
    DevelopmentTask mockTask = mock(DevelopmentTask.class);
    Attachment mockAttachment = mock(Attachment.class);

    //    Gson mockParser = mock(Gson.class);
    Requirement mockRequirement = mock(Requirement.class);
    Requirement[] mockRequirements = new Requirement[1];

    List<Attachment> mockAttachments = new ArrayList<Attachment>();
    List<AcceptanceTest> mockTests = new ArrayList<AcceptanceTest>();
    List<DevelopmentTask> mockTasks = new ArrayList<DevelopmentTask>();
    List<Requirement> mockChildren = new ArrayList<Requirement>();

    Requirement requirement = new Requirement();

    /**
     * Method setUp.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Network.setInstance(mockNetwork);
        IterationModel.setInstance(mockIterationModel);
        RequirementModel.setInstance(mockRequirementModel);

        mockAttachments.add(mockAttachment);

        mockTests.add(mockTest);

        mockChildren.add(mockRequirement);

        mockTasks.add(mockTask);

        when(mockRequirementModel.getChildren(requirement)).thenReturn(mockChildren);

        requirement.setHistory(mockHistory);
        requirement.setAttachments(mockAttachments);
        requirement.setNotes(mockNotes);
        requirement.setTests(mockTests);
        requirement.setTasks(mockTasks);

        mockRequirements[0] = mockRequirement;
    }

    @Test
    public void testConstructor() {
        Requirement newRequirement = new Requirement();
        assertNotNull(newRequirement);
        assertEquals("", newRequirement.getName());
        assertEquals("", newRequirement.getRelease());
        assertEquals(RequirementStatus.NEW, newRequirement.getStatus());
        assertEquals(RequirementPriority.BLANK, newRequirement.getPriority());
        assertEquals(0, newRequirement.getEstimate());
        assertEquals(0, newRequirement.getEffort());
        assertEquals("Backlog", newRequirement.getIteration());
        assertEquals(RequirementType.BLANK, newRequirement.getType());
        assertEquals(-1, newRequirement.getParentID());
        assertNotNull(newRequirement.getHistory());
        assertTrue(newRequirement.getHistory().getHistory().isEmpty());
        assertNotNull(newRequirement.getNotes());
        assertTrue(newRequirement.getNotes().getNotes().isEmpty());
        assertNotNull(newRequirement.getTasks());
        assertTrue(newRequirement.getTasks().isEmpty());
        assertNotNull(newRequirement.getTests());
        assertTrue(newRequirement.getTests().isEmpty());
        assertNotNull(newRequirement.getAttachments());
        assertTrue(newRequirement.getAttachments().isEmpty());
    }

    @Test
    @Ignore
    public void testFromJson() {
        String json = "{\"id\":0,"
                + "\"name\":\"\","
                + "\"release\":\"\","
                + "\"status\":\"NEW\","
                + "\"priority\":\"BLANK\","
                + "\"description\":\"\","
                + "\"estimate\":0,"
                + "\"estimateEdited\":false,"
                + "\"wasCreated\":false,"
                + "\"effort\":0,"
                + "\"history\":{\"history\":[],\"TimeStamp\":0}"
                + ",\"type\":\"BLANK\","
                + "\"notes\":{\"notes\":[]}"
                + ",\"iteration\":\"Backlog\","
                + "\"parentID\":-1,"
                + "\"tasks\":[],"
                + "\"tests\":[],"
                + "\"attachments\":[],"
                + "\"permissionMap\":{}}";
        Requirement newRequirement = new Requirement();
        Requirement result = Requirement.fromJson(json);

        //TODO Will pass once Requirement and all it's dependencies have equals()
        assertEquals(newRequirement, result);
    }

    @Test
    @Ignore
    public void testFromJsonArray() {
        String json = "[{\"id\":0,"
                + "\"name\":\"\","
                + "\"release\":\"\","
                + "\"status\":\"NEW\","
                + "\"priority\":\"BLANK\","
                + "\"description\":\"\","
                + "\"estimate\":0,"
                + "\"estimateEdited\":false,"
                + "\"wasCreated\":false,"
                + "\"effort\":0,"
                + "\"history\":{\"history\":[],\"TimeStamp\":0}"
                + ",\"type\":\"BLANK\","
                + "\"notes\":{\"notes\":[]}"
                + ",\"iteration\":\"Backlog\","
                + "\"parentID\":-1,"
                + "\"tasks\":[],"
                + "\"tests\":[],"
                + "\"attachments\":[],"
                + "\"permissionMap\":{}}]";
        Requirement newRequirement = new Requirement();
        Requirement[] expected = { newRequirement };
        Requirement[] result = Requirement.fromJsonArray(json);

        //TODO Will pass once Requirement and all it's dependencies have equals()
        assertArrayEquals(expected, result);
    }

    @Test
    public void testToJson() {
        Requirement newRequirement = new Requirement();
        String result = newRequirement.toJson();
        String expectedJson = "{\"id\":0,"
                + "\"name\":\"\","
                + "\"release\":\"\","
                + "\"status\":\"NEW\","
                + "\"priority\":\"BLANK\","
                + "\"description\":\"\","
                + "\"estimate\":0,"
                + "\"estimateEdited\":false,"
                + "\"wasCreated\":false,"
                + "\"effort\":0,"
                + "\"history\":{\"history\":[],\"TimeStamp\":0}"
                + ",\"type\":\"BLANK\","
                + "\"notes\":{\"notes\":[]}"
                + ",\"iteration\":\"Backlog\","
                + "\"parentID\":-1,"
                + "\"tasks\":[],"
                + "\"tests\":[],"
                + "\"attachments\":[],"
                + "\"permissionMap\":{}}";

        assertEquals(expectedJson, result);
    }

    @Test
    public void testSetName() {
        requirement.setName("TestName");

        assertEquals("TestName", requirement.getName());
        verify(mockHistory, times(1)).add("Name changed from  to TestName");
    }

    @Test
    public void testSetName_SameName() {
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

        requirement.setName(overMaxChars);

        assertEquals(maxChars, requirement.getName());
        verify(mockHistory, times(1)).add("Name changed from  to " + maxChars);
    }

    @Test
    public void testSetName_wasCreated() {
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

        requirement.setWasCreated(true);

        requirement.setName(overMaxChars);

        assertEquals(maxChars, requirement.getName());
        verify(mockHistory, times(0)).add("Name changed from  to " + maxChars);
    }

    @Test
    public void testSetRelease() {
        requirement.setRelease("TestRelease");

        assertEquals("TestRelease", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number set to 'TestRelease'");
    }

    @Test
    public void testSetRelease_wasCreated() {
        requirement.setWasCreated(true);

        requirement.setRelease("TestRelease");

        assertEquals("TestRelease", requirement.getRelease());
        verify(mockHistory, times(0)).add("Release Number set to 'TestRelease'");
    }

    @Test
    public void testSetRelease_originalReleaseNotEmpty() {
        requirement.setRelease("TestRelease");

        assertEquals("TestRelease", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number set to 'TestRelease'");

        requirement.setRelease("TestRelease2");

        assertEquals("TestRelease2", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number changed from 'TestRelease' to 'TestRelease2'");
    }

    @Test
    public void testSetRelease_newReleaseEmpty() {
        requirement.setRelease("TestRelease");

        assertEquals("TestRelease", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number set to 'TestRelease'");

        requirement.setRelease("");

        assertEquals("", requirement.getRelease());
        verify(mockHistory, times(1)).add("Release Number set to blank from 'TestRelease'");
    }

    @Test
    public void testSetStatus() {
        requirement.setStatus(RequirementStatus.INPROGRESS);

        assertEquals(RequirementStatus.INPROGRESS, requirement.getStatus());
        verify(mockHistory, times(1)).add("Status changed from 'New' to 'In Progress'");
    }

    @Test
    public void testSetStatus_wasCreated() {
        requirement.setWasCreated(true);

        requirement.setStatus(RequirementStatus.INPROGRESS);

        assertEquals(RequirementStatus.INPROGRESS, requirement.getStatus());
        verify(mockHistory, never()).add(anyString());
    }

    @Test
    public void testSetStatus_sameStatus() {
        requirement.setStatus(RequirementStatus.NEW);

        assertEquals(RequirementStatus.NEW, requirement.getStatus());
        verify(mockHistory, never()).add(anyString());
    }

    @Test
    public void testSetDescription() {
        requirement.setDescription("TestDesc");

        assertEquals("TestDesc", requirement.getDescription());
        verify(mockHistory, times(1)).add("Description changed");
    }

    @Test
    public void testSetDescription_sameDescription() {
        requirement.setDescription("TestDesc");

        assertEquals("TestDesc", requirement.getDescription());
        verify(mockHistory, times(1)).add("Description changed");

        requirement.setDescription("TestDesc");

        assertEquals("TestDesc", requirement.getDescription());
        verify(mockHistory, times(1)).add("Description changed");
    }

    @Test
    public void testSetDescription_wasCreated() {
        requirement.setWasCreated(true);

        requirement.setDescription("TestDesc");

        assertEquals("TestDesc", requirement.getDescription());
        verify(mockHistory, never()).add(anyString());
    }

    @Test
    public void testGetChildEstimate() {
        when(mockRequirementModel.getChildren(requirement)).thenReturn(mockChildren);
        when(mockRequirement.getTotalEstimate()).thenReturn(5);

        int result = requirement.getChildEstimate();

        assertEquals(5, result);
    }

    @Test
    public void testGetChildEstimate_noChildren() {
        when(mockRequirementModel.getChildren(requirement)).thenReturn(new ArrayList<Requirement>());

        int result = requirement.getChildEstimate();

        assertEquals(0, result);
    }

    @Test
    public void testSetEstimate() {
        requirement.setEstimate(5);

        assertEquals(5, requirement.getEstimate());
        verify(mockHistory, times(1)).add("Estimate changed from '0' to '5'");
    }

    @Test
    public void testSetEstimate_sameValue() {
        requirement.setEstimate(0);

        assertEquals(0, requirement.getEstimate());
        verify(mockHistory, never()).add(anyString());
    }

    @Test
    public void testSetEstimate_wasCreated() {
        requirement.setWasCreated(true);

        requirement.setEstimate(5);

        assertEquals(5, requirement.getEstimate());
        verify(mockHistory, never()).add(anyString());
    }

    @Test
    public void testSetPriority() {
        requirement.setPriority(RequirementPriority.HIGH);

        assertEquals(RequirementPriority.HIGH, requirement.getPriority());
        verify(mockHistory, times(1)).add("Priority changed from 'None' to 'High'");
    }

    @Test
    public void testSetPriority_samePriority() {
        requirement.setPriority(RequirementPriority.BLANK);

        assertEquals(RequirementPriority.BLANK, requirement.getPriority());
        verify(mockHistory, never()).add(anyString());
    }

    @Test
    public void testSetPriority_wasCreated() {
        requirement.setWasCreated(true);

        requirement.setPriority(RequirementPriority.HIGH);

        assertEquals(RequirementPriority.HIGH, requirement.getPriority());
        verify(mockHistory, never()).add(anyString());
    }

    @Test
    public void testSetType() {
        requirement.setType(RequirementType.SCENARIO);

        assertEquals(RequirementType.SCENARIO, requirement.getType());
        verify(mockHistory, times(1)).add("Type set to 'Scenario'");
    }

    @Test
    public void testAddNote() {
        requirement.addNote("TestNote");

        verify(mockHistory, times(1)).add("Note added");
        verify(mockNotes, times(1)).add("TestNote");
    }

    @Test
    public void testAddNote_wasCreated() {
        requirement.setWasCreated(true);
        requirement.addNote("TestNote");

        verify(mockHistory, never()).add(anyString());
        verify(mockNotes, times(1)).add("TestNote");
    }

    @Test
    public void testRemoveTask() {
        assertEquals(1, mockTasks.size());

        when(mockTask.getId()).thenReturn(5);

        requirement.removeTask(5);

        assertEquals(0, requirement.getTasks().size());
    }

    @Test
    public void testAddTest() {
        assertEquals(1, requirement.getTests().size());
        when(mockTest.getName()).thenReturn("MockTestName");

        requirement.addTest(mockTest);

        assertEquals(2, requirement.getTests().size());
        verify(mockHistory, times(1)).add("Acceptance test 'MockTestName' added.");
    }

    @Test
    public void testAddTest_wasCreated() {
        assertEquals(1, requirement.getTests().size());
        requirement.setWasCreated(true);
        when(mockTest.getName()).thenReturn("MockTestName");

        requirement.addTest(mockTest);

        assertEquals(2, requirement.getTests().size());
        verify(mockHistory, never()).add(anyString());
    }

    @Test
    public void testUpdateTestStatus() {
        when(mockTest.getId()).thenReturn(4);

        requirement.updateTestStatus(4, TestStatus.STATUS_PASSED);

        verify(mockTest, times(1)).setStatus(TestStatus.STATUS_PASSED);
    }

    @Test
    public void testUpdateTestStatus_noMatch() {
        when(mockTest.getId()).thenReturn(5);

        requirement.updateTestStatus(4, TestStatus.STATUS_PASSED);

        verify(mockTest, never()).setStatus(any(TestStatus.class));
    }

    @Test
    public void testRemoveTest() {
        when(mockTest.getId()).thenReturn(5);

        assertEquals(1, mockTests.size());
        requirement.removeTest(5);

        assertEquals(0, mockTests.size());
    }

    @Test
    public void testRemoveTest_noMatch() {
        when(mockTest.getId()).thenReturn(5);

        assertEquals(1, mockTests.size());
        requirement.removeTest(4);

        assertEquals(1, mockTests.size());
    }

    @Test
    public void testRemoveAttachment() {
        when(mockAttachment.getId()).thenReturn(5);

        assertEquals(1, mockAttachments.size());
        requirement.removeAttachment(5);

        assertEquals(0, mockAttachments.size());
    }

    @Test
    public void testRemoveAttachment_noMatch() {
        when(mockAttachment.getId()).thenReturn(5);

        assertEquals(1, mockAttachments.size());
        requirement.removeAttachment(4);

        assertEquals(1, mockAttachments.size());
    }

    @Test
    public void testSetIteration_empty() {
        requirement.setIteration("");

        assertEquals("Backlog", requirement.getIteration());
    }

    @Test
    public void testSetIteration_emptyStringAndIsBacklog() {
        requirement = spy(requirement);

        requirement.setIteration(" ");

        assertEquals("Backlog", requirement.getIteration());
        verify(mockHistory, never()).add(anyString());
        verify(requirement, never()).setStatus(any(RequirementStatus.class));
    }

    @Test
    public void testSetIteration() {
        requirement = spy(requirement);

        requirement.setIteration("TestIteration");

        assertEquals("TestIteration", requirement.getIteration());
        verify(mockHistory, times(1)).add("Moved from 'Backlog' to 'TestIteration'");
        verify(requirement, times(1)).setStatus(RequirementStatus.INPROGRESS);
    }

    @Test
    public void testSetIteration_statusIsOpen() {
        requirement = spy(requirement);
        requirement.setStatus(RequirementStatus.OPEN);

        requirement.setIteration("TestIteration");

        assertEquals("TestIteration", requirement.getIteration());
        verify(mockHistory, times(1)).add("Moved from 'Backlog' to 'TestIteration'");
        verify(requirement, times(1)).setStatus(RequirementStatus.INPROGRESS);
    }

    @Test
    public void testSetIteration_wasCreated() {
        requirement = spy(requirement);
        requirement.setWasCreated(true);

        requirement.setIteration("TestIteration");

        assertEquals("TestIteration", requirement.getIteration());
        verify(mockHistory, never()).add(anyString());
        verify(requirement, times(1)).setStatus(RequirementStatus.INPROGRESS);
    }

    @Test
    public void testSetIteration_isBacklogAndIsInProgress() {
        requirement = spy(requirement);
        requirement.setStatus(RequirementStatus.INPROGRESS);
        verify(mockHistory, times(1)).add("Status changed from 'New' to 'In Progress'");

        requirement.setIteration(" ");

        assertEquals("Backlog", requirement.getIteration());
        verify(mockHistory, times(1)).add("Status changed from 'In Progress' to 'Open'");
        verify(mockHistory, times(2)).add(anyString());
        verify(requirement, times(1)).setStatus(RequirementStatus.OPEN);
    }

    @Test
    public void testSetIteration_isBacklogAndIsInProgressAndWasCreated() {
        requirement = spy(requirement);
        requirement.setWasCreated(true);
        requirement.setStatus(RequirementStatus.INPROGRESS);

        requirement.setIteration(" ");

        assertEquals("Backlog", requirement.getIteration());
        verify(mockHistory, never()).add(anyString());
        verify(requirement, times(1)).setStatus(RequirementStatus.NEW);
    }

    @Test
    public void testSetParentID() throws Exception {
        requirement.setId(4);
        when(mockRequirementModel.getRequirement(5)).thenReturn(mockRequirement);
        when(mockRequirement.isAncestor(4)).thenReturn(Boolean.FALSE);

        requirement.setParentID(5);

        assertEquals(5, requirement.getParentID());
    }

    @Test(expected = Exception.class)
    public void testSetParentID_exception() throws Exception {
        requirement.setId(4);
        when(mockRequirementModel.getRequirement(5)).thenReturn(mockRequirement);
        when(mockRequirement.isAncestor(4)).thenReturn(Boolean.TRUE);

        requirement.setParentID(5);

        assertEquals(5, requirement.getParentID());
    }

    @Test
    public void testSetParentID_negativeOne() throws Throwable {
        requirement.setParentID(-1);

        assertEquals(-1, requirement.getParentID());
    }

    @Test
    public void testHasAncestor() throws Exception {
        requirement.setId(2);
        when(mockRequirementModel.getRequirement(1)).thenReturn(mockRequirement);
        when(mockRequirement.isAncestor(1)).thenReturn(Boolean.TRUE);
        requirement.setParentID(1);

        when(mockRequirement.hasAncestor(1)).thenReturn(true);

        assertTrue(requirement.hasAncestor(1));
    }

    @Test
    public void testHasAncestor_sameAsParentID() throws Exception {
        requirement.setId(2);
        when(mockRequirementModel.getRequirement(1)).thenReturn(mockRequirement);
        when(mockRequirement.isAncestor(1)).thenReturn(Boolean.TRUE);
        requirement.setParentID(1);

        when(mockRequirement.hasAncestor(1)).thenReturn(false);

        assertTrue(requirement.hasAncestor(1));
    }

    @Test
    public void testHasAncestor_negativeOne() {
        assertFalse(requirement.hasAncestor(-1));
    }

    @Test
    public void testIsAncestor() {
        when(mockRequirementModel.getChildren(requirement)).thenReturn(mockChildren);
        when(mockRequirement.getId()).thenReturn(3);

        assertTrue(requirement.isAncestor(3));
    }

    @Test
    public void testIsAncestor_grandAncestor() {
        when(mockRequirementModel.getChildren(requirement)).thenReturn(mockChildren);
        when(mockRequirement.getId()).thenReturn(2);
        when(mockRequirement.isAncestor(3)).thenReturn(Boolean.TRUE);

        assertTrue(requirement.isAncestor(3));
    }

    @Test
    public void testIsAncestor_noAncestor() {
        when(mockRequirementModel.getChildren(requirement)).thenReturn(mockChildren);
        when(mockRequirement.getId()).thenReturn(2);
        when(mockRequirement.isAncestor(3)).thenReturn(Boolean.FALSE);

        assertFalse(requirement.isAncestor(3));
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
