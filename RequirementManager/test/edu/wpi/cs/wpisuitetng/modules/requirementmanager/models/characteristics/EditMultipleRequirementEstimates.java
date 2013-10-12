
/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: TEAM ROLLING THUNDER
 */

/**
 * @author Kevin
 *
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class EditMultipleRequirementEstimates {

	private ViewEventController vec;
	
	/**
	 * Setup the tests
	 */
	@Before
	public void setUp(){
		String [][] exData = new String[1][8];
		
		for(int i=0; i<8; i++){
			exData[0][i] = "1";
		}
		
		vec = ViewEventController.getInstance();
		vec.setMainView(new MainView());
		vec.setOverviewTable(new OverviewTable(exData, exData[0]));
		vec.setToolBar(new ToolbarView(true));
	}
	
	/**
	 * Test toggle editing
	 */
	@Test
	public void testToggleEditing() {
		// need requirement to be referred to in the overview table
		RequirementModel.getInstance().addRequirement(new Requirement(1, "Name", "desc."));
		
		assertEquals(false, vec.getOverviewTable().isCellEditable(0,7));
		vec.toggleEditingTable(false);
		assertEquals(true, vec.getOverviewTable().isCellEditable(0, 7));
		vec.toggleEditingTable(false);
		assertEquals(false, vec.getOverviewTable().isCellEditable(0, 7));
	}

}
