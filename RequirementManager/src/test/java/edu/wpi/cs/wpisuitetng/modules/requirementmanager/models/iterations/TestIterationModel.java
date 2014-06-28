/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author Robert Smieja
 */
public class TestIterationModel {
    
    ViewEventController mockViewEventController;
    
    IterationModel iterationModel;
    
    @Before
    public void setup() {
        mockViewEventController = mock(ViewEventController.class);
        
        iterationModel = new IterationModel();
    }
    
    @Test
    public void testAddIteration() {
        fail();
    }
    
    @Test
    public void testAddIterations() {
        fail();
    }
    
    @Test
    public void testEmptyModel() {
        fail();
    }
    
    @Test
    public void testGetIteration() {
        
    }
    
    @Test
    public void testGetConflictingIteration() {
        
    }
    
    @Test
    public void testGetIterationForDate() {
        
    }
}
