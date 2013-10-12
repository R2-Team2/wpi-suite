/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;

/**
 * This is a test for basic functionality of the ViewEventController.java source file
 * @author Rolling Thunder
 *
 * @version $Revision: 1.0 $
 */
public class ViewEventControllerTest {

	ViewEventController vec;
	
	/**
	
	 * @throws Exception
	 * @throws java.lang.Exception */
	@Before
	public void setUp() throws Exception {
		String [][] exData = new String[1][8];
		
		for(int i=0; i<8; i++){
			exData[0][i] = "1";
		}
		
		vec = ViewEventController.getInstance();
		vec.setMainView(new MainView());
		vec.setOverviewTable(new OverviewTable(exData, exData[0]));
		vec.setToolBar(new ToolbarView(true));
		RequirementModel.getInstance().addRequirement(new Requirement(1, "Name", "desc."));
	}

	@Test
	public void testCreatingARequirementTab() {
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createRequirement();
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
	}

	@Test
	public void testCreatingAnIterationTab() {
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createIteration();
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
	}
	
	@Test
	public void testTheRemovalOfACreatedTab() {
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createRequirement();
		vec.getMainView().removeTabAt(prevTabCount);
		assertEquals(prevTabCount, vec.getMainView().getTabCount());
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreatingAPanelAndTabForEditingAnIteration() {
		int prevTabCount = vec.getMainView().getTabCount();
		int numItrs = vec.getListOfIterationPanels().size();
		Iteration itr = new Iteration(1, "Test Itr", new Date(2013, 4, 20), new Date(2013, 4, 27));
		vec.editIteration(itr);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		assertEquals(numItrs + 1, vec.getListOfIterationPanels().size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testThatAnotherTabIsNotOpenedForAnIterationThatAlreadyHasATabOpen() {
		int prevTabCount = vec.getMainView().getTabCount();
		int numItrs = vec.getListOfIterationPanels().size();
		Iteration itr = new Iteration(1, "Test Itr", new Date(2013, 4, 20), new Date(2013, 4, 27));
		vec.editIteration(itr);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		assertEquals(numItrs + 1, vec.getListOfIterationPanels().size());
		vec.editIteration(itr);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		assertEquals(numItrs + 1, vec.getListOfIterationPanels().size());
	}
	
	@Test
	public void testCreationOfTheFirstPieChart() {
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createPieChart("Iteration");
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
	}
	
	@Test
	public void testThatCreatingASecondPieChartDoesNotMakeANewTab() {
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createPieChart("Iteration");
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		vec.createPieChart("Status");
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
	}
	
	@Test
	public void testCreationOfTheFirstBarChart() {
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createPieChart("Iteration");
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
	}
	
	@Test
	public void testThatCreatingASecondBarChartDoesNotMakeANewTab() {
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createBarChart("Iteration");
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		vec.createBarChart("Status");
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
	}
	
	@Test
	public void testCreatingAPanelAndTabForEditingARequirement() {
		int prevTabCount = vec.getMainView().getTabCount();
		int numReqs = vec.getListOfRequirementPanels().size();
		Requirement req = new Requirement(1, "TestReq", "The Description");
		vec.editRequirement(req);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		assertEquals(numReqs + 1, vec.getListOfRequirementPanels().size());
	}
	
	@Test
	public void testThatAnotherTabIsNotOpenedForARequirementThatAlreadyHasATabOpen() {
		int prevTabCount = vec.getMainView().getTabCount();
		int numReqs = vec.getListOfRequirementPanels().size();
		Requirement req = new Requirement(1, "TestReq", "The Description");
		vec.editRequirement(req);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		assertEquals(numReqs + 1, vec.getListOfRequirementPanels().size());
		vec.editRequirement(req);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		assertEquals(numReqs + 1, vec.getListOfRequirementPanels().size());
	}
	
	@Test
	public void testThatATabCanBeOpenedForCreatingAChildRequirement() {
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createChildRequirement(1);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
		// multiple children can be made for the same parent
		vec.createChildRequirement(1);
		assertEquals(prevTabCount + 2, vec.getMainView().getTabCount());
	}
}
