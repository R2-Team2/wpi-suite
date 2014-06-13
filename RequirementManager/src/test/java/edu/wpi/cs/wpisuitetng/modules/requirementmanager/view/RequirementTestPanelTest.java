/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs.RequirementTestPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * tests the functionality of the RequirementTestPanel.java source file
 * @author Rolling Thunder
 *
 * @version $Revision: 1.0 $
 */
public class RequirementTestPanelTest {

	RequirementTestPanel testPan;
	Requirement req;
	RequirementPanel reqPan;
	
	/**
	
	 * @throws Exception
	 * @throws java.lang.Exception */
	@Before
	public void setUp() throws Exception {
		req = new Requirement(1, "Name", "Description");
		reqPan = new RequirementPanel(req);

		// Mock Network
		Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
		// Mock Iteration
		Iteration iterationTest = new Iteration(0,"Backlog");
		IterationModel.getInstance().setBacklog(iterationTest);	 
	}

	@Test
	public void testForAddingATestWithoutTheProperFieldsFilledIn() {
		int prevNumTests = reqPan.getTabsPanel().getTestPanel().getTestsAdded();
		reqPan.getTabsPanel().getTestPanel().getAddTestButton().setEnabled(true);
		reqPan.getTabsPanel().getTestPanel().getAddTestButton().doClick();
		assertEquals(prevNumTests, reqPan.getTabsPanel().getTestPanel().getTestsAdded());
	}

	@Test
	public void testForAddingATestWithTheProperFieldsFilledIn() {
		int prevNumTests = reqPan.getTabsPanel().getTestPanel().getTestsAdded();
		reqPan.getTabsPanel().getTestPanel().getTestTitle().setText("A Title");
		reqPan.getTabsPanel().getTestPanel().getTestMessage().setText("A Message");
		reqPan.getTabsPanel().getTestPanel().getAddTestButton().setEnabled(true);
		reqPan.getTabsPanel().getTestPanel().getAddTestButton().doClick();
		assertEquals(prevNumTests + 1, reqPan.getTabsPanel().getTestPanel().getTestsAdded());
	}
	
	@Test
	public void testClearButton() {
		reqPan.getTabsPanel().getTestPanel().getTestTitle().setText("A Title");
		reqPan.getTabsPanel().getTestPanel().getTestMessage().setText("A Message");
		reqPan.getTabsPanel().getTestPanel().getClearButton().setEnabled(true);
		reqPan.getTabsPanel().getTestPanel().getClearButton().doClick();
		assertEquals("", reqPan.getTabsPanel().getTestPanel().getTestTitle().getText());
		assertEquals("", reqPan.getTabsPanel().getTestPanel().getTestMessage().getText());
	}
}
