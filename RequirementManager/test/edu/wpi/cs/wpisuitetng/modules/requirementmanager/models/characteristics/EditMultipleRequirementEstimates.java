/**
 *  * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 */

package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

/**
 * @author Kevin
 *
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.MockData;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;

public class EditMultipleRequirementEstimates {

	private ViewEventController vec;
	private OverviewPanel overview;
	private MainView mainView;
	private OverviewTable table;
	
	@Before
	public void setUp(){
		String [][] exData = new String[1][8];
		
		for(int i=0; i<8; i++){
			exData[0][i] = "cats";
		}
		
		vec = ViewEventController.getInstance();
		vec.setMainView(new MainView());
		vec.setOverviewTable(new OverviewTable(exData, null));
		vec.setToolBar(new ToolbarView(true));
	}
	
	@Test
	public void testToggleEditing() {
		assertEquals(false, vec.getOverviewTable().isCellEditable(0,7));
		vec.toggleEditingTable(false);
		assertEquals(true, vec.getOverviewTable().isCellEditable(0, 7));
		vec.toggleEditingTable(false);
		assertEquals(false, vec.getOverviewTable().isCellEditable(0, 7));
	}

}
