/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.iterationcontroller.AddIterationController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.IterationDate;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTreePanel;

/**
 * Tests the IterationModel.java source file methods
 * 
 * @author Rolling Thunder
 * @version $Revision: 1.0 $
 */
public class IterationModelTest {
	
	ViewEventController mockViewEventController;
	AddIterationController mockAddIterationController;
	
	IterationModel iterationModel;
	
	Iteration mockBacklog, mockIteration1, mockIteration2, mockIteration3;
	IterationDate mockIteration1DateStart, mockIteration1DateEnd;
	IterationDate mockIteration2DateStart, mockIteration2DateEnd;
	IterationDate mockIteration3DateStart, mockIteration3DateEnd;
	Iteration[] mockIterations;
	ArrayList<Iteration> mockIterationList;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		mockViewEventController = mock(ViewEventController.class);
		mockAddIterationController = mock(AddIterationController.class);
		
		iterationModel = new IterationModel();
		iterationModel.setViewEventController(mockViewEventController);
		iterationModel.setAddIterationController(mockAddIterationController);
		
		mockIteration1 = mock(Iteration.class);
		mockIteration2 = mock(Iteration.class);
		mockIteration3 = mock(Iteration.class);
		mockBacklog = mock(Iteration.class);
		
		mockIteration1DateStart = mock(IterationDate.class);
		mockIteration1DateEnd = mock(IterationDate.class);
		
		mockIteration2DateStart = mock(IterationDate.class);
		mockIteration2DateEnd = mock(IterationDate.class);
		
		mockIteration3DateStart = mock(IterationDate.class);
		mockIteration3DateEnd = mock(IterationDate.class);
		
		when(mockIteration1DateStart.getDate()).thenReturn(new Date(2013, 4, 20));
		when(mockIteration1DateEnd.getDate()).thenReturn(new Date(2013, 4, 28));
		
		when(mockIteration2DateStart.getDate()).thenReturn(new Date(2013, 4, 10));
		when(mockIteration2DateEnd.getDate()).thenReturn(new Date(2013, 4, 20));
		
		when(mockIteration3DateStart.getDate()).thenReturn(new Date(2013, 3, 20));
		when(mockIteration3DateEnd.getDate()).thenReturn(new Date(2013, 3, 28));
		
		when(mockIteration1.getName()).thenReturn("Iteration 1");
		when(mockIteration1.getId()).thenReturn(1);
		when(mockIteration1.getStart()).thenReturn(mockIteration1DateStart);
		when(mockIteration1.getEnd()).thenReturn(mockIteration1DateEnd);
		
		when(mockIteration2.getName()).thenReturn("Iteration 2");
		when(mockIteration2.getId()).thenReturn(2);
		when(mockIteration2.getStart()).thenReturn(mockIteration2DateStart);
		when(mockIteration2.getEnd()).thenReturn(mockIteration2DateEnd);
		
		when(mockIteration3.getName()).thenReturn("Iteration 3");
		when(mockIteration3.getId()).thenReturn(3);
		when(mockIteration3.getStart()).thenReturn(mockIteration3DateStart);
		when(mockIteration3.getEnd()).thenReturn(mockIteration3DateEnd);
		
		when(mockBacklog.getName()).thenReturn("Backlog");
		when(mockBacklog.getId()).thenReturn(0);
		
		mockIterations = new Iteration[] { mockBacklog, mockIteration1, mockIteration2, mockIteration3 };
		
		mockIterationList = new ArrayList<Iteration>();
		mockIterationList.add(mockBacklog);
		mockIterationList.add(mockIteration1);
		mockIterationList.add(mockIteration2);
		mockIterationList.add(mockIteration3);
	}
	
	/**
	 * Test getting the iteration model
	 */
	@Test
	public void createNotNullIterationModel() {
		assertNotNull(IterationModel.getInstance());
	}
	
	/**
	 * Test emptying the model.
	 */
	@Test
	public void testEmptyModel() {
		iterationModel.addIterations(mockIterations);
		
		assertEquals(4, iterationModel.getListOfIterations().size());
		assertEquals(iterationModel.getBacklog(), mockBacklog);
		
		verify(mockBacklog, times(1)).getName();
		verify(mockIteration1, times(1)).getName();
		verify(mockIteration2, times(1)).getName();
		verify(mockIteration3, times(1)).getName();
		
		verify(mockViewEventController, times(1)).refreshTree();
		
		iterationModel.emptyModel();
		
		assertEquals(0, iterationModel.getSize());
		
		verify(mockViewEventController, times(2)).refreshTree();
		verify(mockViewEventController, times(1)).refreshTable();
	}
	
	/**
	 * Test size and adding
	 */
	@Test
	public void testGetSizeAndAddIterations() {
		iterationModel.addIterations(mockIterations);
		
		assertEquals(4, iterationModel.getListOfIterations().size());
		assertEquals(iterationModel.getBacklog(), mockBacklog);
		
		verify(mockBacklog, times(1)).getName();
		verify(mockIteration1, times(1)).getName();
		verify(mockIteration2, times(1)).getName();
		verify(mockIteration3, times(1)).getName();
		
		verify(mockViewEventController, times(1)).refreshTree();
	}
	
	/**
	 * test retrieving the element at a given location
	 */
	@Test
	public void testGetElementAt() {
		iterationModel.addIterations(mockIterations);
		
		assertEquals(4, iterationModel.getListOfIterations().size());
		assertEquals(iterationModel.getBacklog(), mockBacklog);
		
		assertEquals(mockBacklog, iterationModel.getElementAt(3));
		assertEquals(mockIteration1, iterationModel.getElementAt(2));
		assertEquals(mockIteration2, iterationModel.getElementAt(1));
		assertEquals(mockIteration3, iterationModel.getElementAt(0));
		
		verify(mockBacklog, times(1)).getName();
		verify(mockIteration1, times(1)).getName();
		verify(mockIteration2, times(1)).getName();
		verify(mockIteration3, times(1)).getName();
		
		verify(mockViewEventController, times(1)).refreshTree();
	}
	
	/**
	 * Test getting a single iteration
	 */
	@Test
	public void testGetIteration() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		viewCon.setOverviewTable(ovTable);
		OverviewTreePanel ovTree = new OverviewTreePanel();
		viewCon.setOverviewTree(ovTree);
		
		Iteration itrList[] = new Iteration[] { new Iteration(1, "Iteration1"), new Iteration(2, "Iteration2"), new Iteration(3, "") };
		IterationModel itrModel = IterationModel.getInstance();
		itrModel.emptyModel();
		itrModel.addIterations(itrList);
		assertEquals(3, itrModel.getSize());
		assertEquals(2, itrModel.getIteration("Iteration2").getId());
		assertEquals("Iteration2", itrModel.getIteration("Iteration2").getName());
		assertEquals(3, itrModel.getIteration("Backlog").getId());
		assertEquals("Backlog", itrModel.getIteration("Backlog").getName());
		assertEquals(3, itrModel.getIteration(null).getId());
		assertEquals("Backlog", itrModel.getIteration(null).getName());
		assertEquals(3, itrModel.getIteration("").getId());
		assertEquals("Backlog", itrModel.getIteration("").getName());
	}
	
	/**
	 * test getting all iterations
	 */
	@Test
	public void testGetIterations() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		viewCon.setOverviewTable(ovTable);
		OverviewTreePanel ovTree = new OverviewTreePanel();
		viewCon.setOverviewTree(ovTree);
		
		Iteration itrList[] = new Iteration[] { new Iteration(1, "Iteration1"), new Iteration(2, "Iteration2"), new Iteration(3, "") };
		IterationModel itrModel = IterationModel.getInstance();
		itrModel.emptyModel();
		itrModel.addIterations(itrList);
		List<Iteration> returnList = itrModel.getIterations();
		assertEquals("Backlog", returnList.get(0).getName());
		assertEquals(3, returnList.get(0).getId());
		assertEquals("Iteration2", returnList.get(1).getName());
		assertEquals(2, returnList.get(1).getId());
		assertEquals("Iteration1", returnList.get(2).getName());
		assertEquals(1, returnList.get(2).getId());
	}
	
	@Test
	public void testGetIterationForDate_Null() {
		iterationModel.setListOfIterations(mockIterationList);
		
		List<Iteration> returnList = iterationModel.getIterationForDate(null);
		assertEquals(0, returnList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetIterationForDate_NoDateWithNoIteration() {
		iterationModel.setListOfIterations(mockIterationList);
		iterationModel.setBacklog(mockBacklog);
		
		List<Iteration> returnList = iterationModel.getIterationForDate(new Date(2013, 4, 9));
		assertEquals(0, returnList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetIterationForDate_IterationInMiddleOfDates() {
		iterationModel.setListOfIterations(mockIterationList);
		iterationModel.setBacklog(mockBacklog);
		
		// data in the middle of 1 iteration
		List<Iteration> returnList = iterationModel.getIterationForDate(new Date(2013, 4, 17));
		assertEquals(1, returnList.size());
		assertEquals(2, returnList.get(0).getId());
		assertEquals("Iteration 2", returnList.get(0).getName());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetIterationForDate_IterationDatesOverlap() {
		iterationModel.setListOfIterations(mockIterationList);
		iterationModel.setBacklog(mockBacklog);
		
		// data where 1 iteration stops and another starts
		List<Iteration> returnList = iterationModel.getIterationForDate(new Date(2013, 4, 20));
		assertEquals(2, returnList.size());
		assertEquals("Iteration 1", returnList.get(0).getName());
		assertEquals(1, returnList.get(0).getId());
		assertEquals("Iteration 2", returnList.get(1).getName());
		assertEquals(2, returnList.get(1).getId());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetConflictingIteration() {
		iterationModel.setListOfIterations(mockIterationList);
		iterationModel.setBacklog(mockBacklog);
		
		// end date overlaps with a start date, should be fine
		Iteration returnItr = iterationModel.getConflictingIteration(new Date(2013, 4, 9), new Date(2013, 4, 10));
		assertNull(returnItr);
	}
	
	@Test
	public void testGetConflictingIteration_NullDates() {
		iterationModel.setListOfIterations(mockIterationList);
		iterationModel.setBacklog(mockBacklog);
		
		// end date overlaps with a start date, should be fine
		Iteration returnItr = iterationModel.getConflictingIteration(null, null);
		assertNull(returnItr);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetConflictingIteration_OverlapInMiddleOfIteration() {
		iterationModel.setListOfIterations(mockIterationList);
		iterationModel.setBacklog(mockBacklog);
		
		// data in the middle of 1 iteration
		Iteration returnItr = iterationModel.getConflictingIteration(new Date(2013, 4, 17), new Date(2013, 4, 18));
		assertEquals("Iteration 2", returnItr.getName());
		assertEquals(2, returnItr.getId());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetConflictingIteration_OverlapEntireIteration() {
		iterationModel.setListOfIterations(mockIterationList);
		iterationModel.setBacklog(mockBacklog);
		
		// data surrounding 1 iteration
		Iteration returnItr = iterationModel.getConflictingIteration(new Date(2013, 3, 19), new Date(2013, 4, 2));
		assertEquals("Iteration 3", returnItr.getName());
		assertEquals(3, returnItr.getId());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetConflictingIteration_OverlapTwoIterationsWithOneDateAtEnd() {
		iterationModel.setListOfIterations(mockIterationList);
		iterationModel.setBacklog(mockBacklog);
		
		// data starting in 1 iteration and ending in a different iteration
		Iteration returnItr = iterationModel.getConflictingIteration(new Date(2013, 4, 15), new Date(2013, 4, 25));
		assertEquals("Iteration 2", returnItr.getName());
		assertEquals(2, returnItr.getId());
	}
}
