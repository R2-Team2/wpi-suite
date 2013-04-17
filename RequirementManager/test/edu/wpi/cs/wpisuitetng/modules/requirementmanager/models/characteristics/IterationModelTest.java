/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;

/**
 * Tests the IterationModel.java source file methods
 * @author Rolling Thunder
 *
 */
public class IterationModelTest {

	@Test
	public void createNotNullIterationModel() {
		assertNotNull(IterationModel.getInstance());
	}

	@Test
	public void testEmptyModel() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
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
	
	@Test
	public void testGetElementAt() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		viewCon.setOverviewTable(ovTable);
		
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
	
	@Test
	public void testGetIteration() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		viewCon.setOverviewTable(ovTable);
		
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
	
	@Test
	public void testGetIterations() {
		ViewEventController viewCon = ViewEventController.getInstance();
		OverviewTable ovTable = new OverviewTable(null, null);
		viewCon.setOverviewTable(ovTable);
		
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
	
	
}
