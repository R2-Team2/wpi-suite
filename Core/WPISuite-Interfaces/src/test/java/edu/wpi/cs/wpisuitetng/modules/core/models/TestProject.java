/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    twack
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.core.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Test suite for the Project model
 * 
 * @author twack
 */
public class TestProject {
    User user1 = new User("James Bond", "jbond", "abcde", 7);
    User user2 = new User("Money Penny", "mpenny", null, 2);
    User user3 = new User("Q", "q", "whatup", 1);
    User user4 = new User("M", "m", null, 0);

    User[] team = { user1, user2, user3 };
    String[] support = { "defecttracker", "postboard" };

    Project project1 = new Project("defectTracker", "proj1");
    Project project2 = new Project("postBoard", "proj2");
    Project project3 = new Project("calendar", "proj3", user1, team, support);

    @Test
    public void testNewProjectWithNullTeam()
    {
        Project project = new Project("calendar", "proj3", user1, null, support);

        assertNotNull(project);
        assertEquals(0, project.getTeam().length);
    }

    /**
     * Tests the Object.equals override implemented by Project
     */
    @Test
    public void testEquals()
    {
        Object p1match = new Project("defectTracker", "proj1");

        assertTrue(project1.equals(p1match));
        assertFalse(project1.equals(project2));
    }

    /**
     * Tests the getName function of Project
     */
    @Test
    public void testGetName()
    {
        assertEquals("defectTracker", project1.getName());
    }

    @Test
    public void testSetName()
    {
        String name = "messageBoard";

        assertTrue(project1.getName().equals("defectTracker"));

        project1.setName(name);

        assertTrue(project1.getName().equals(name));
    }

    @Test
    public void testSetIdNum()
    {
        project1.setIdNum("test1");
        assertEquals("test1", project1.getIdNum());
    }

    @Test
    public void testGetIdNum()
    {
        assertTrue(project1.getIdNum().equals("proj1"));
    }

    @Test
    public void testGetProjectName()
    {
        assertEquals("defectTracker", project1.getProjectName());
    }

    /**
     * Tests that addTeamMember function. Tests the error-checking boolean
     * return values as well as the size of the team.
     */
    @Test
    public void testAddTeamMember()
    {
        assertTrue(project3.addTeamMember(user4));
        assertEquals(4, project3.getTeam().length);

        assertFalse(project3.addTeamMember(user4));
    }

    /**
     * Tests removing a team member from the list.
     */
    @Test
    public void testRemoveTeamMember()
    {
        int initCount = project3.getTeam().length;

        assertTrue(project3.removeTeamMember(user1));
        assertFalse(project3.removeTeamMember(user4));
        assertEquals(initCount - 1, project3.getTeam().length);
    }

    @Test
    public void testGetTeam()
    {
        ArrayList<User> teamList = new ArrayList<User>();
        teamList.add(user1);
        teamList.add(user2);
        teamList.add(user3);

        User[] team = project3.getTeam();
        int teamSize = team.length;

        assertEquals(teamList.size(), teamSize);

        // check that the lists are the same
        for (int i = 0; i < teamSize; i++)
        {
            assertTrue(teamList.contains(team[i]));
        }
    }

    @Test
    public void testGetOwner()
    {
        User owner = project3.getOwner();

        assertTrue(owner.equals(user1));
    }

    @Test
    public void testSetOwner()
    {
        User oldOwner = project3.getOwner();

        project3.setOwner(user2);
        assertFalse(project3.getOwner().equals(oldOwner));
        assertTrue(project3.getOwner().equals(user2));
    }

    @Test
    public void testToJson() {
        String deflated = project3.toJson();

        assertTrue(deflated.startsWith("{"));
        assertTrue(deflated.endsWith("}"));

        assertTrue(deflated.contains("name"));
        assertTrue(deflated.contains("calendar"));
        assertTrue(deflated.contains("name"));

        assertTrue(deflated.contains("idNum"));
        assertTrue(deflated.contains("proj3"));

        assertTrue(deflated.contains("owner"));
        assertTrue(deflated.contains(user1.toJson()));

        assertTrue(deflated.contains("team"));

        assertTrue(deflated.contains("supportedModules"));
    }

    @Test
    public void testToString() {
        assertEquals(project3.toJson(), project3.toString());
    }

    @Test
    public void testIdentify_anotherProject() {
        Project project = new Project("copyOfDefectTracker", "proj1");

        assertTrue(project1.identify(project));
    }

    @Test
    public void testIdentify_string() {
        assertTrue(project1.identify("proj1"));
    }

    /*
     * Unimplemented methods
     */

    /**
     * Ensures that the Project.getProject() method returns null. A project should
     * not belong to another project. No recursive relationships.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetProject()
    {
        assertNull(project1.getProject());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetProject()
    {
        project1.setProject(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSave()
    {
        project1.save();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete()
    {
        project1.delete();
    }
}
