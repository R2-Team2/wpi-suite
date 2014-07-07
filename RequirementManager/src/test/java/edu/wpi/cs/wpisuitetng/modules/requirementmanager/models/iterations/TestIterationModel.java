/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.iterationcontroller.AddIterationController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.IterationDate;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author Robert Smieja
 */
public class TestIterationModel {
    
    ViewEventController mockViewEventController;
    AddIterationController mockAddIterationController;
    
    IterationModel iterationModel;
    
    Iteration mockBacklogIteration;
    Iteration mockIteration1;
    Iteration mockIteration2;
    
    Iteration[] iterationsWithBacklog = new Iteration[2];
    Iteration[] iterationsWithoutBacklog = new Iteration[1];
    
    IterationDate mockStartDate1;
    IterationDate mockEndDate1;
    IterationDate mockStartDate2;
    IterationDate mockEndDate2;
    
    @Before
    public void setup() {
        mockViewEventController = mock(ViewEventController.class);
        mockAddIterationController = mock(AddIterationController.class);
        mockBacklogIteration = mock(Iteration.class);
        mockIteration1 = mock(Iteration.class);
        mockIteration2 = mock(Iteration.class);
        
        when(mockBacklogIteration.getName()).thenReturn("Backlog");
        when(mockIteration1.getName()).thenReturn("Not Backlog");
        when(mockIteration2.getName()).thenReturn("Test");
        
        iterationsWithBacklog[0] = mockBacklogIteration;
        iterationsWithBacklog[1] = mockIteration1;
        iterationsWithoutBacklog[0] = mockIteration2;
        
        mockStartDate1 = mock(IterationDate.class);
        mockEndDate1 = mock(IterationDate.class);
        mockStartDate2 = mock(IterationDate.class);
        mockEndDate2 = mock(IterationDate.class);
        
        iterationModel = new IterationModel();
        iterationModel.setAddIterationController(mockAddIterationController);
        iterationModel.setViewEventController(mockViewEventController);
    }
    
    @Test
    public void testAddIteration() {
        iterationModel.addIteration(mockBacklogIteration);
        
        assertEquals(1, iterationModel.getSize());
        
        verify(mockAddIterationController, times(1)).addIteration(mockBacklogIteration);
        verify(mockViewEventController, times(1)).refreshTree();
    }
    
    @Test
    public void testAddIterations() {
        iterationModel.addIterations(iterationsWithBacklog);
        
        assertEquals(2, iterationModel.getSize());
        
        verify(mockViewEventController, times(1)).refreshTree();
    }
    
    @Test
    public void testAddIterations_noBacklog() {
        iterationModel.setBacklog(null);
        
        iterationModel.addIterations(iterationsWithoutBacklog);
        
        assertEquals(2, iterationModel.getSize());
        
        verify(mockViewEventController, times(2)).refreshTree();
    }
    
    @Test
    public void testEmptyModel() {
        assertEquals(0, iterationModel.getSize());
        
        iterationModel.addIterations(iterationsWithBacklog);
        
        verify(mockViewEventController, times(1)).refreshTree();
        
        iterationModel.emptyModel();
        
        assertEquals(0, iterationModel.getSize());
        
        verify(mockViewEventController, times(1)).refreshTable();
    }
    
    @Test
    public void testGetIteration_nameNull() {
        String iterationName = null;
        Iteration iteration = iterationModel.getIteration(iterationName);
        assertNull(iteration);
    }
    
    @Test
    public void testGetIteration_nameEmpty() {
        String iterationName = "";
        Iteration iteration = iterationModel.getIteration(iterationName);
        assertNull(iteration);
    }
    
    @Test
    public void testGetIteration_nameBacklog() {
        String iterationName = "Backlog";
        Iteration iteration = iterationModel.getIteration(iterationName);
        assertNull(iteration);
    }
    
    @Test
    public void testGetIteration() {
        String iterationName = mockIteration1.getName();
        iterationModel.addIterations(iterationsWithBacklog);
        
        Iteration iteration = iterationModel.getIteration(iterationName);
        
        assertEquals(mockIteration1, iteration);
    }
    
    @Test
    public void testGetIteration_nonExistant() {
        Iteration iteration = iterationModel.getIteration("Unknown Name");
        assertNull(iteration);
    }
    
    @Test
    public void testGetIterations() {
        iterationModel.addIterations(iterationsWithBacklog);
        
        List<Iteration> listOfIterations = iterationModel.getIterations();
        
        assertArrayEquals(iterationsWithBacklog, listOfIterations.toArray());
    }
    
    @Test
    public void testGetConflictingIteration_sameDates() {
        when(mockIteration1.getStart()).thenReturn(mockStartDate1);
        when(mockIteration1.getEnd()).thenReturn(mockEndDate1);
        when(mockStartDate1.getDate()).thenReturn(createDate(2014, 5, 29, 0, 0, 0));
        when(mockEndDate1.getDate()).thenReturn(createDate(2014, 5, 30, 0, 0, 0));
        iterationModel.addIteration(mockIteration1);
        
        Iteration result = iterationModel.getConflictingIteration(createDate(2014, 5, 29, 0, 0, 0), createDate(2014, 5, 30, 0, 0, 0));
        
        assertEquals(mockIteration1, result);
    }
    
    @Test
    public void testGetConflictingIteration_overlapsDates() {
        when(mockIteration1.getStart()).thenReturn(mockStartDate1);
        when(mockIteration1.getEnd()).thenReturn(mockEndDate1);
        when(mockStartDate1.getDate()).thenReturn(createDate(2014, 5, 29, 0, 0, 0));
        when(mockEndDate1.getDate()).thenReturn(createDate(2014, 5, 30, 0, 0, 0));
        iterationModel.addIteration(mockIteration1);
        
        Iteration result = iterationModel.getConflictingIteration(createDate(2014, 5, 28, 0, 0, 0), createDate(2014, 6, 1, 0, 0, 0));
        
        assertEquals(mockIteration1, result);
    }
    
    @Test
    public void testGetConflictingIteration_backlog() {
        iterationModel.setBacklog(mockBacklogIteration);
        
        Iteration result = iterationModel.getConflictingIteration(createDate(2014, 5, 29, 0, 0, 0), createDate(2014, 5, 30, 0, 0, 0));
        
        assertNull(result);
    }
    
    @Test
    public void testGetConflictingIteration_null() {
        Date start = null;
        Date end = null;
        Iteration result = iterationModel.getConflictingIteration(start, end);
        assertNull(result);
    }
    
    @Test
    public void testGetIterationForDate() {
        when(mockIteration1.getStart()).thenReturn(mockStartDate1);
        when(mockIteration1.getEnd()).thenReturn(mockEndDate1);
        when(mockStartDate1.getDate()).thenReturn(createDate(2014, 5, 29, 0, 0, 0));
        when(mockEndDate1.getDate()).thenReturn(createDate(2014, 5, 30, 0, 0, 0));
        iterationModel.addIteration(mockIteration1);
        
        List<Iteration> result = iterationModel.getIterationForDate(createDate(2014, 5, 29, 0, 0, 0));
        
        assertEquals(1, result.size());
        assertTrue(result.contains(mockIteration1));
    }
    
    @Test
    public void testGetIterationForDate_middleOFIteration() {
        when(mockIteration1.getStart()).thenReturn(mockStartDate1);
        when(mockIteration1.getEnd()).thenReturn(mockEndDate1);
        when(mockStartDate1.getDate()).thenReturn(createDate(2014, 5, 29, 0, 0, 0));
        when(mockEndDate1.getDate()).thenReturn(createDate(2014, 6, 1, 0, 0, 0));
        iterationModel.addIteration(mockIteration1);
        
        List<Iteration> result = iterationModel.getIterationForDate(createDate(2014, 5, 30, 0, 0, 0));
        
        assertEquals(1, result.size());
        assertTrue(result.contains(mockIteration1));
    }
    
    @Test
    public void testGetIterationForDate_twoIterations() {
        when(mockIteration1.getStart()).thenReturn(mockStartDate1);
        when(mockIteration1.getEnd()).thenReturn(mockEndDate1);
        when(mockStartDate1.getDate()).thenReturn(createDate(2014, 5, 29, 0, 0, 0));
        when(mockEndDate1.getDate()).thenReturn(createDate(2014, 5, 30, 0, 0, 0));
        
        iterationModel.addIteration(mockIteration1);
        
        //Ensure the iteration was added properly
        assertEquals(1, iterationModel.getSize());
        assertTrue(iterationModel.getIterations().contains(mockIteration1));
        
        List<Iteration> result = iterationModel.getIterationForDate(createDate(2014, 5, 29, 0, 0, 0));
        
        //Ensure getIterationForDate worked
        assertEquals(1, result.size());
        assertTrue(result.contains(mockIteration1));
        
        when(mockIteration2.getStart()).thenReturn(mockStartDate2);
        when(mockIteration2.getEnd()).thenReturn(mockEndDate2);
        when(mockStartDate2.getDate()).thenReturn(createDate(2014, 5, 28, 0, 0, 0));
        when(mockEndDate2.getDate()).thenReturn(createDate(2014, 5, 29, 0, 0, 0));
        
        iterationModel.addIteration(mockIteration2);
        
        //Ensure the iteration was added properly
        assertEquals(2, iterationModel.getSize());
        assertTrue(iterationModel.getIterations().contains(mockIteration2));
        
        result = iterationModel.getIterationForDate(createDate(2014, 5, 29, 0, 0, 0));
        
        //Ensure getIterationForDate worked
        assertEquals(2, result.size());
        assertTrue(result.contains(mockIteration1));
        assertTrue(result.contains(mockIteration2));
    }
    
    @Test
    public void testGetIterationForDate_null() {
        List<Iteration> result = iterationModel.getIterationForDate(null);
        
        assertEquals(0, result.size());
    }
    
    @Test
    public void testGetIterationForDate_backlog() {
        iterationModel.setBacklog(mockBacklogIteration);
        List<Iteration> result = iterationModel.getIterationForDate(createDate(2014, 5, 29, 0, 0, 0));
        
        assertEquals(0, result.size());
    }
    
    //Helper
    private Date createDate(int year, int month, int day, int hour, int min, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, min, second);
        return cal.getTime();
    }
}
