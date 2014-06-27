/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.MockData;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementEntityManager;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;

/**
 * tests the functionality of the NewBarChatPanel source file
 * 
 * @author Rolling Thunder
 * @version $Revision: 1.0 $
 */
//TODO: Finish rewrite in TestNewBarChartPanel.java
@Deprecated
public class NewBarChartPanelTest {
    
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
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        String[][] exData = new String[1][8];
        
        for (int i = 0; i < 8; i++) {
            exData[0][i] = "1";
        }
        
        vec = ViewEventController.getInstance();
        vec.setMainView(new MainView());
        vec.setOverviewTable(new OverviewTable(exData, exData[0]));
        vec.setToolBar(new ToolbarView(true));
        
        bob = new User("bob", "bob", "1234", 27);
        sue = new User("sue", "sue", "4321", 26);
        
        req1 = new Requirement(1, "reqName", "The description");
        req2 = new Requirement(2, "anotherReq", "another description");
    }
    
    @Test
    public void creationOfStatusBarChartTabTest() {
        ToolbarView tb = new ToolbarView(true);
        assertEquals(2, vec.getMainView().getTabCount());
        tb.getChartButton().getBarChartButton().doClick();
        assertEquals(3, vec.getMainView().getTabCount());
    }
}
