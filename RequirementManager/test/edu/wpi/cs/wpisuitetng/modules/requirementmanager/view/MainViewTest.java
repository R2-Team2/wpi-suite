/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;

/**
 * @author Rolling Thunder
 *
 */
public class MainViewTest {

	private ViewEventController vec;
	/**
	 * @throws java.lang.Exception
	 */
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
	public void numberOfCreatedTabtest() {
		assertEquals(1,vec.getMainView().getTabCount());
		vec.getToolbar().getReqButton().getCreateButton().doClick();
		assertEquals(2,vec.getMainView().getTabCount());
		vec.getToolbar().getReqButton().getCreateIterationButton().doClick();
		assertEquals(3,vec.getMainView().getTabCount());
	}

}
