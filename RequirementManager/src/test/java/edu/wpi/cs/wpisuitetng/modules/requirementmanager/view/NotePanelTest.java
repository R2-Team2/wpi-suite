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
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs.RequirementNotePanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * Tests the functionality of the Note Panel inside of a Requirement
 * @author Rolling Thunder
 *
 * @version $Revision: 1.0 $
 */
public class NotePanelTest {

	
	private RequirementPanel reqPan;
	private Requirement req;
	/**
	 * Sets up the mock network, iteration, and local variables
	
	
	 * @throws Exception * @throws java.lang.Exception */
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
	public void AddNoteTest() {				
		// make sure there are no notes yet
		assertEquals(0, reqPan.getTabsPanel().getNotePanel().
				getRequirement().getNotes().getNotes().toArray().length);
		assertFalse(reqPan.getTabsPanel().getNotePanel().
				getAddNoteButton().isEnabled());
		
		reqPan.getTabsPanel().getNotePanel().getNoteMessage().setText("Test string");
		assertEquals("Test string", reqPan.getTabsPanel().getNotePanel().
				getNoteMessage().getText());
		reqPan.getTabsPanel().getNotePanel().getAddNoteButton().setEnabled(true);
		assertTrue(reqPan.getTabsPanel().getNotePanel().getAddNoteButton().isEnabled());
		reqPan.getTabsPanel().getNotePanel().getAddNoteButton().doClick();	// add a new note
		assertFalse(reqPan.getTabsPanel().getNotePanel().getAddNoteButton().isEnabled());
		
		// check to see if the note was added
		assertEquals(1, reqPan.getTabsPanel().getNotePanel().getRequirement().getNotes().getNotes().toArray().length);
		assertEquals("Test string", reqPan.getTabsPanel().getNotePanel().getRequirement().getNotes().getItem(0).getMessage());
	}
	
	

	@Test
	public void NoteClearButtonTest() {
		assertEquals("", reqPan.getTabsPanel().getNotePanel().getNoteMessage().getText());
		reqPan.getTabsPanel().getNotePanel().getNoteMessage().setText("This should not be here when this test is over");
		assertEquals("This should not be here when this test is over", reqPan.getTabsPanel().getNotePanel().
				getNoteMessage().getText());
		reqPan.getTabsPanel().getNotePanel().getClearButton().setEnabled(true);
		reqPan.getTabsPanel().getNotePanel().getClearButton().doClick();	// clear the note text box
		assertFalse(reqPan.getTabsPanel().getNotePanel().getClearButton().isEnabled());
		assertEquals("", reqPan.getTabsPanel().getNotePanel().getNoteMessage().getText());
	}
}
