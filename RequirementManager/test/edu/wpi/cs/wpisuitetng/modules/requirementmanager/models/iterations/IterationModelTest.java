/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTreePanel;

/**
 * Tests the IterationModel.java source file methods
 * @author Rolling Thunder
 *
 * @version $Revision: 1.0 $
 */
public class IterationModelTest {

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
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		OverviewTreePanel ovTree = new OverviewTreePanel();
		viewCon.setOverviewTree(ovTree);
		viewCon.setOverviewTable(ovTable);

		
		Iteration itrList[] = new Iteration[]{
			new Iteration(1, "Iteration1"),
			new Iteration(2, "Iteration2"),
			new Iteration(3, "Iteration3")	
		};
		IterationModel itrModel = IterationModel.getInstance();
		itrModel.addIterations(itrList);
		itrModel.emptyModel();
		assertEquals(0, itrModel.getSize());
	}
	
	/**
	 * Test size and adding
	 */
	@Test
	public void testGetSizeAndAddIterations() {
		Iteration itrList[] = new Iteration[]{
			new Iteration(1, "Iteration1"),
			new Iteration(2, "Iteration2"),
			new Iteration(3, "")	
		};
		IterationModel itrModel = IterationModel.getInstance();
		itrModel.addIterations(itrList);
		assertEquals(3, itrModel.getSize());
	}
	
	/**
	 * test retrieving the element at a given location
	 */
	@Test
	public void testGetElementAt() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		viewCon.setOverviewTable(ovTable);
		OverviewTreePanel ovTree = new OverviewTreePanel();
		viewCon.setOverviewTree(ovTree);
		
		Iteration itrList[] = new Iteration[]{
			new Iteration(1, "Iteration1"),
			new Iteration(2, "Iteration2"),
			new Iteration(3, "")	
		};
		IterationModel itrModel = IterationModel.getInstance();
		itrModel.emptyModel();
		itrModel.addIterations(itrList);
		assertEquals(3, itrModel.getSize());
		assertEquals("Iteration2", itrModel.getElementAt(1).getName());
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
		
		Iteration itrList[] = new Iteration[]{
			new Iteration(1, "Iteration1"),
			new Iteration(2, "Iteration2"),
			new Iteration(3, "")	
		};
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
		
		Iteration itrList[] = new Iteration[]{
			new Iteration(1, "Iteration1"),
			new Iteration(2, "Iteration2"),
			new Iteration(3, "")	
		};
		IterationModel itrModel = IterationModel.getInstance();
		itrModel.emptyModel();
		itrModel.addIterations(itrList);
		List<Iteration> returnList = itrModel.getIterations();
		assertEquals("Iteration1", returnList.get(0).getName());
		assertEquals(1, returnList.get(0).getId());
		assertEquals("Iteration2", returnList.get(1).getName());
		assertEquals(2, returnList.get(1).getId());
		assertEquals("Backlog", returnList.get(2).getName());
		assertEquals(3, returnList.get(2).getId());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetIterationForDateWithBoth1or2IterationsOnTheSameDate() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		viewCon.setOverviewTable(ovTable);
		OverviewTreePanel ovTree = new OverviewTreePanel();
		viewCon.setOverviewTree(ovTree);
		
		Iteration itrList[] = new Iteration[]{
			new Iteration(1, "Iteration1", new Date(2013, 4, 20), new Date(2013, 4, 28)),
			new Iteration(2, "Iteration2", new Date(2013, 4, 10), new Date(2013, 4, 20)),
			new Iteration(3, "Iteration3", new Date(2013, 3, 20), new Date(2013, 3, 28))	
		};
		IterationModel itrModel = IterationModel.getInstance();
		itrModel.emptyModel();
		itrModel.addIterations(itrList);
		
		// date with no iterations
		List<Iteration> returnList = itrModel.getIterationForDate(new Date(2013, 4, 9));
		assertEquals(0, returnList.size());
		
		// null data
		returnList = itrModel.getIterationForDate(null);
		assertEquals(0, returnList.size());
		
		// data in the middle of 1 iteration
		returnList = itrModel.getIterationForDate(new Date(2013, 4, 17));
		assertEquals("Iteration2", returnList.get(0).getName());
		assertEquals(2, returnList.get(0).getId());
		assertEquals(1, returnList.size());
		
		// data where 1 iteration stops and another starts
		returnList = itrModel.getIterationForDate(new Date(2013, 4, 20));
		assertEquals("Iteration1", returnList.get(0).getName());
		assertEquals(1, returnList.get(0).getId());
		assertEquals("Iteration2", returnList.get(1).getName());
		assertEquals(2, returnList.get(1).getId());
		assertEquals(2, returnList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetConflictingIterationFOrVariousDates() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		viewCon.setOverviewTable(ovTable);
		OverviewTreePanel ovTree = new OverviewTreePanel();
		viewCon.setOverviewTree(ovTree);
		
		Iteration itrList[] = new Iteration[]{
			new Iteration(1, "Iteration1", new Date(2013, 4, 20), new Date(2013, 4, 28)),
			new Iteration(2, "Iteration2", new Date(2013, 4, 10), new Date(2013, 4, 20)),
			new Iteration(3, "Iteration3", new Date(2013, 3, 20), new Date(2013, 3, 28))	
		};
		IterationModel itrModel = IterationModel.getInstance();
		itrModel.emptyModel();
		itrModel.addIterations(itrList);
		
		// end date overlaps with a start date, should be fine
		Iteration returnItr = itrModel.getConflictingIteration(new Date(2013, 4, 9), new Date(2013, 4, 10));
		assertNull(returnItr);
		
		// null dates, so there should be no conflicts
		returnItr = itrModel.getConflictingIteration(null, null);
		assertNull(null);
		
		// data in the middle of 1 iteration
		returnItr = itrModel.getConflictingIteration(new Date(2013, 4, 17), new Date(2013, 4, 18));
		assertEquals("Iteration2", returnItr.getName());
		assertEquals(2, returnItr.getId());
		
		// data surrounding 1 iteration
		returnItr = itrModel.getConflictingIteration(new Date(2013, 3, 19), new Date(2013, 4, 2));
		assertEquals("Iteration3", returnItr.getName());
		assertEquals(3, returnItr.getId());
		
		// data starting in 1 iteration and ending in a different iteration
		returnItr = itrModel.getConflictingIteration(new Date(2013, 4, 15), new Date(2013, 4, 25));
		assertEquals("Iteration2", returnItr.getName());
		assertEquals(2, returnItr.getId());
	}
}
