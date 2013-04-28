/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.MockData;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementEntityManager;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewBarChartPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewPieChartPanel;

/**
 * tests the functionality of the NewBarChatPanel source file
 * @author Rolling Thunder
 *
 * @version $Revision: 1.0 $
 */
public class NewPieChartPanelTest {

	static MockData db;
	static User bob;
	static User sue;
	static Requirement req1;
	static String mockSsid;
	static RequirementEntityManager manager;
	static Session testSession;
	static Project testProject;
	static Requirement req2;
	
	static ViewEventController vec;
	/**
	
	 * @throws Exception
	 * @throws java.lang.Exception */
	@BeforeClass
	public static void setUp() throws Exception {
		String [][] exData = new String[1][8];
		
		for(int i=0; i<8; i++){
			exData[0][i] = "1";
		}
		
		vec = ViewEventController.getInstance();
		vec.setMainView(new MainView());
		vec.setOverviewTable(new OverviewTable(exData, exData[0]));
		vec.setToolBar(new ToolbarView(true));
		
		bob = new User("bob", "bob", "1234", 27);
		sue = new User("sue", "sue", "4321", 26);
	
		req1 = new Requirement(1,  "reqName", "The description");
		req2 = new Requirement(2, "anotherReq", "another description");
		
		IterationModel.getInstance().addIteration(new Iteration(2, "iter 2"));
		IterationModel.getInstance().addIteration(new Iteration(3, "iter 3"));
		List<String> assigned = new ArrayList<String>();
		assigned.add("Bob");
		req1.setAssignedTo(assigned);
		assigned.add("Sue");
		req2.setAssignedTo(assigned);
		RequirementModel.getInstance().emptyModel();
		RequirementModel.getInstance().addRequirement(req1);
		RequirementModel.getInstance().addRequirement(req2);
	}

	@Test
	public void creationOfStatusPieChartTabTest() {
		ToolbarView tb = new ToolbarView(true);
		while(2 < vec.getMainView().getTabCount()) {
			vec.getMainView().removeTabAt(2);	// remove any previously created tabs
		}
		tb.getChartButton().getPieChartButton().doClick();
		assertEquals(3, vec.getMainView().getTabCount());
	}

	@Test
	public void creationOfIterationPieChartTabTest() {
		while(2 < vec.getMainView().getTabCount()) {
			vec.getMainView().removeTabAt(2);	// remove any previously created tabs
		}
		assertEquals(2, vec.getMainView().getTabCount());
		vec.createPieChart("Iteration");
		assertEquals(3, vec.getMainView().getTabCount());
	}
	
	/* any input other than Status or Iteration will create a pie chart that has info 
	 * about the number of requirements a user has assigned
	 */
	@Test
	public void testDataAssignedPieChart() {
		while(2 < vec.getMainView().getTabCount()) {
			vec.getMainView().removeTabAt(2);	// remove any previously created tabs
		}
		assertEquals(2, vec.getMainView().getTabCount());
		vec.createPieChart("Data");
		assertEquals(3, vec.getMainView().getTabCount());
	}
	
	@Test
	public void testGetPieChartTitle() {
		NewPieChartPanel pc = new NewPieChartPanel("The Name");
		assertEquals("The Name", pc.getTitle());
	}
}
