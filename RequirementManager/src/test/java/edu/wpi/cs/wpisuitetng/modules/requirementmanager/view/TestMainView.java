/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;

/**
 * @author Robert Smieja
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.swing.*")
@PrepareForTest(ViewEventController.class)
public class TestMainView {
    
    ViewEventController mockViewEventController;
    OverviewTable mockOverviewTable;
    TableModel mockTableModel;
    
    MainView mainView;
    
    @Before
    public void setUp() throws Exception {
        mockStatic(ViewEventController.class);
        
        mockViewEventController = mock(ViewEventController.class);
        mockOverviewTable = mock(OverviewTable.class);
        mockTableModel = mock(TableModel.class);
        
        when(ViewEventController.getInstance()).thenReturn(mockViewEventController);
        when(mockViewEventController.getOverviewTable()).thenReturn(mockOverviewTable);
        when(mockOverviewTable.getModel()).thenReturn(mockTableModel);
        
        mainView = new MainView();
        
    }
    
    //TODO: A constructor should not have 200 lines of code in it... hard to test...
    @Test
    public void testConstructor() {
        assertEquals(2, mainView.getTabCount());
        assertEquals("Requirement Overview", mainView.getTitleAt(0));
        assertEquals("Iteration Overview", mainView.getTitleAt(1));
        assertEquals(mainView.getOverview(), mainView.getComponentAt(0));
        assertEquals(mainView.getIterationOverview(), mainView.getComponentAt(1));
        
        verify(mockTableModel, times(1)).addTableModelListener(any(TableModelListener.class));
    }
    
    @Test
    public void testSerialVersionUID() {
        assertEquals(7957462092495599897L, MainView.serialVersionUID);
    }
}
