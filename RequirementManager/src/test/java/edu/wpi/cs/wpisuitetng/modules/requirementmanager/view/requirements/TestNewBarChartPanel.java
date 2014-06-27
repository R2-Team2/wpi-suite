/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * TODO: Finish rewrite of tests in NewBarChartPanelTest.java
 * 
 * @author Robert Smieja
 */
public class TestNewBarChartPanel {
    
    NewBarChartPanel barChartPanel;
    
    @Before
    public void setup() {
        barChartPanel = new NewBarChartPanel("TestBarChart");
    }
    
    @Test
    public void testConstructor() {
        assertEquals("TestBarChart", barChartPanel.getTitle());
        assertNotNull(barChartPanel.getBarChart());
        assertNotNull(barChartPanel.getViewport());
    }
    
    @Test
    public void testSerialVersionUID() {
        assertEquals(2930864775768057902L, NewBarChartPanel.serialVersionUID);
    }
}
