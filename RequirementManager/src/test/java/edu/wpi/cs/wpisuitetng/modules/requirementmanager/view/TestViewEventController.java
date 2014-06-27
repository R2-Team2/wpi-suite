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
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import javax.swing.table.TableCellEditor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations.IterationPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewBarChartPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewPieChartPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;

/**
 * TODO: Since ViewEventController is in PrepareForTest, Code Coverage will not
 * be accurate. Code should be refactored so PowerMock can be removed.
 * 
 * @author Robert Smieja
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "javax.swing" })
@PrepareForTest({ ViewEventController.class, IterationModel.class, UpdateRequirementController.class, RequirementPanel.class, IterationPanel.class, NewPieChartPanel.class, NewBarChartPanel.class })
public class TestViewEventController {
    IterationModel mockIterationModel;
    UpdateRequirementController mockUpdateRequirementController;
    
    MainView mockMainView;
    RequirementPanel mockRequirementPanel;
    IterationPanel mockIterationPanel;
    ToolbarView mockToolbarView;
    Iteration mockIteration;
    NewPieChartPanel mockPieChartPanel;
    NewBarChartPanel mockBarChartPanel;
    Requirement mockRequirement;
    TransactionHistory mockHistory;
    OverviewTable mockOverviewTable;
    TableCellEditor mockTableCellEditor;
    
    ViewEventController viewEventController;
    
    @Before
    public void setup() throws Exception {
        mockStatic(IterationModel.class);
        mockStatic(UpdateRequirementController.class);
        
        mockUpdateRequirementController = mock(UpdateRequirementController.class);
        mockIterationModel = mock(IterationModel.class);
        
        mockMainView = mock(MainView.class);
        mockRequirementPanel = mock(RequirementPanel.class);
        mockIterationModel = mock(IterationModel.class);
        mockToolbarView = mock(ToolbarView.class);
        mockIteration = mock(Iteration.class);
        mockIterationPanel = mock(IterationPanel.class);
        mockPieChartPanel = mock(NewPieChartPanel.class);
        mockBarChartPanel = mock(NewBarChartPanel.class);
        mockRequirement = mock(Requirement.class);
        mockHistory = mock(TransactionHistory.class);
        mockOverviewTable = mock(OverviewTable.class);
        mockTableCellEditor = mock(TableCellEditor.class);
        
        when(UpdateRequirementController.getInstance()).thenReturn(mockUpdateRequirementController);
        when(IterationModel.getInstance()).thenReturn(mockIterationModel);
        when(mockRequirementPanel.getDisplayRequirement()).thenReturn(mockRequirement);
        when(mockRequirement.getId()).thenReturn(4);
        when(mockRequirement.getHistory()).thenReturn(mockHistory);
        when(mockOverviewTable.getCellEditor()).thenReturn(mockTableCellEditor);
        
        whenNew(RequirementPanel.class).withArguments(-1).thenReturn(mockRequirementPanel);
        whenNew(RequirementPanel.class).withArguments(3).thenReturn(mockRequirementPanel);
        whenNew(IterationPanel.class).withNoArguments().thenReturn(mockIterationPanel);
        whenNew(IterationPanel.class).withArguments(mockIteration).thenReturn(mockIterationPanel);
        whenNew(NewPieChartPanel.class).withArguments("TestPieChart").thenReturn(mockPieChartPanel);
        whenNew(NewBarChartPanel.class).withArguments("TestBarChart").thenReturn(mockBarChartPanel);
        
        viewEventController = new ViewEventController();
        viewEventController.setMainView(mockMainView);
        viewEventController.getListOfIterationPanels().add(mockIterationPanel);
        viewEventController.getListOfRequirementPanels().add(mockRequirementPanel);
        viewEventController.setOverviewTable(mockOverviewTable);
    }
    
    @Test
    public void testSetToolbar() {
        viewEventController.setToolBar(mockToolbarView);
        
        assertEquals(mockToolbarView, viewEventController.getToolbar());
        verify(mockToolbarView, times(1)).repaint();
    }
    
    @Test
    public void testCreateRequirement() {
        viewEventController.createRequirement();
        
        verify(mockMainView, times(1)).addTab("New Req.", null, mockRequirementPanel, "New Requirement");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockRequirementPanel);
    }
    
    @Test
    public void testCreateIteration() {
        viewEventController.createIteration();
        
        verify(mockMainView, times(1)).addTab("New Iter.", null, mockIterationPanel, "New Iteration");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockIterationPanel);
    }
    
    @Test
    public void testEditIteration_backlog() {
        when(mockIterationModel.getBacklog()).thenReturn(mockIteration);
        
        viewEventController.editIteration(mockIteration);
        
        verify(mockIterationModel, times(1)).getBacklog();
    }
    
    @Test
    public void testEditIteration() {
        when(mockIterationModel.getBacklog()).thenReturn(null);
        when(mockIterationPanel.getDisplayIteration()).thenReturn(mockIteration);
        
        viewEventController.editIteration(mockIteration);
        
        verify(mockIterationModel, times(1)).getBacklog();
        verify(mockMainView, times(1)).setSelectedComponent(mockIterationPanel);
    }
    
    @Test
    public void testEditIteration_doesntExist() {
        when(mockIterationModel.getBacklog()).thenReturn(null);
        when(mockIterationPanel.getDisplayIteration()).thenReturn(null);
        when(mockIteration.getName()).thenReturn("TestIteration");
        
        viewEventController.editIteration(mockIteration);
        
        assertEquals(2, viewEventController.getListOfIterationPanels().size());
        verify(mockIterationModel, times(1)).getBacklog();
        verify(mockMainView, times(1)).addTab("TestIteration", null, mockIterationPanel, "Editing TestIteration");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockIterationPanel);
    }
    
    @Test
    public void testCreatePieChart() {
        when(mockMainView.getTabCount()).thenReturn(1);
        when(mockMainView.getTitleAt(0)).thenReturn("Not a Pie Chart");
        
        viewEventController.createPieChart("TestPieChart");
        
        verify(mockMainView, times(1)).addTab("Pie Chart", null, mockPieChartPanel, "PieChart");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockPieChartPanel);
    }
    
    @Test
    public void testCreatePieChart_chartAlreadyExists() {
        when(mockMainView.getTabCount()).thenReturn(1);
        when(mockMainView.getTitleAt(0)).thenReturn("Pie Chart");
        when(mockMainView.getTabComponentAt(0)).thenReturn(mockPieChartPanel);
        when(mockPieChartPanel.getTitle()).thenReturn("Pie Chart");
        
        viewEventController.createPieChart("Pie Chart");
        
        verify(mockMainView, times(1)).setSelectedIndex(0);
    }
    
    @Test
    public void testCreateBarChart() {
        when(mockMainView.getTabCount()).thenReturn(1);
        when(mockMainView.getTitleAt(0)).thenReturn("Not a Bar Chart");
        
        viewEventController.createBarChart("TestBarChart");
        
        verify(mockMainView, times(1)).addTab("Bar Graph", null, mockBarChartPanel, "BarGraph");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockBarChartPanel);
    }
    
    @Test
    public void testCreateBarChart_chartAlreadyExists() {
        when(mockMainView.getTabCount()).thenReturn(1);
        when(mockMainView.getTitleAt(0)).thenReturn("Bar Graph");
        when(mockMainView.getTabComponentAt(0)).thenReturn(mockBarChartPanel);
        when(mockBarChartPanel.getTitle()).thenReturn("Bar Graph");
        
        viewEventController.createBarChart("Bar Graph");
        
        verify(mockMainView, times(1)).setSelectedIndex(0);
    }
    
    @Test
    public void testCreateChildRequirement() {
        viewEventController.createChildRequirement(3);
        
        verify(mockMainView, times(1)).addTab("Add Child Req.", null, mockRequirementPanel, "Add Child Requirement");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockRequirementPanel);
    }
    
    @Test
    public void testEditRequirement() throws Exception {
        when(mockRequirement.getName()).thenReturn("Short");
        when(mockRequirementPanel.getDisplayRequirement()).thenReturn(null);
        
        whenNew(RequirementPanel.class).withArguments(mockRequirement).thenReturn(mockRequirementPanel);
        
        viewEventController.editRequirement(mockRequirement);
        
        assertEquals(2, viewEventController.getListOfRequirementPanels().size());
        verify(mockMainView, times(1)).addTab("4. Short", null, mockRequirementPanel, "Short");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockRequirementPanel);
    }
    
    @Test
    public void testEditRequirement_nameTooLong() throws Exception {
        when(mockRequirement.getName()).thenReturn("TestName");
        when(mockRequirementPanel.getDisplayRequirement()).thenReturn(null);
        
        whenNew(RequirementPanel.class).withArguments(mockRequirement).thenReturn(mockRequirementPanel);
        
        viewEventController.editRequirement(mockRequirement);
        
        assertEquals(2, viewEventController.getListOfRequirementPanels().size());
        verify(mockMainView, times(1)).addTab("4. TestNam..", null, mockRequirementPanel, "TestName");
        verify(mockMainView, times(1)).invalidate();
        verify(mockMainView, times(1)).repaint();
        verify(mockMainView, times(1)).setSelectedComponent(mockRequirementPanel);
    }
    
    @Test
    public void testEditRequirement_alreadyExists() {
        viewEventController.editRequirement(mockRequirement);
        
        verify(mockHistory, times(1)).setTimestamp(anyLong());
        verify(mockMainView, times(1)).setSelectedComponent(mockRequirementPanel);
    }
    
    @Test
    public void testToggleEditingTable_onToOff_cancelTrue() {
        when(mockOverviewTable.getEditFlag()).thenReturn(true, true, false);
        when(mockOverviewTable.isEditing()).thenReturn(false);
        
        viewEventController.toggleEditingTable(true);
        
        verify(mockOverviewTable, times(1)).setEditFlag(false);
        verify(mockOverviewTable, times(1)).refresh();
    }
    
    @Test
    public void testToggleEditingTable_onToOff_cancelTrue_isEditing() {
        when(mockOverviewTable.getEditFlag()).thenReturn(true, true, false);
        when(mockOverviewTable.isEditing()).thenReturn(true);
        
        viewEventController.toggleEditingTable(true);
        
        verify(mockTableCellEditor, times(1)).stopCellEditing();
        verify(mockOverviewTable, times(1)).setEditFlag(false);
        verify(mockOverviewTable, times(1)).refresh();
    }
    
    @Test
    public void testToggleEditingTable_onToOff_cancelFalse() {
        when(mockOverviewTable.getEditFlag()).thenReturn(true, true, false);
        when(mockOverviewTable.isEditing()).thenReturn(false);
        
        viewEventController.toggleEditingTable(false);
        
        verify(mockOverviewTable, times(1)).setEditFlag(false);
        verify(mockOverviewTable, times(1)).saveChanges();
    }
    
    @Test
    public void testToggleEditingTable_onToOff_cancelFalse_isEditing() {
        when(mockOverviewTable.getEditFlag()).thenReturn(true, true, false);
        when(mockOverviewTable.isEditing()).thenReturn(true);
        
        viewEventController.toggleEditingTable(false);
        
        verify(mockTableCellEditor, times(1)).stopCellEditing();
        verify(mockOverviewTable, times(1)).setEditFlag(false);
        verify(mockOverviewTable, times(1)).saveChanges();
    }
    
    @Test
    public void testToggleEditingTable_offToOn_cancelTrue() {
        when(mockOverviewTable.getEditFlag()).thenReturn(false, false, true);
        when(mockOverviewTable.isEditing()).thenReturn(false);
        
        viewEventController.toggleEditingTable(true);
        
        verify(mockOverviewTable, times(1)).setEditFlag(true);
    }
    
    @Test
    public void testToggleEditingTable_offToOn_cancelTrue_isEditing() {
        when(mockOverviewTable.getEditFlag()).thenReturn(false, false, true);
        when(mockOverviewTable.isEditing()).thenReturn(false);
        
        viewEventController.toggleEditingTable(true);
        
        verify(mockOverviewTable, times(1)).setEditFlag(true);
    }
    
    @Test
    public void testToggleEditingTable_offToOn_cancelFalse() {
        when(mockOverviewTable.getEditFlag()).thenReturn(false, false, true);
        when(mockOverviewTable.isEditing()).thenReturn(false);
        
        viewEventController.toggleEditingTable(false);
        
        verify(mockOverviewTable, times(1)).setEditFlag(true);
    }
    
    @Test
    public void testToggleEditingTable_offToOn_cancelFalse_isEditing() {
        when(mockOverviewTable.getEditFlag()).thenReturn(false, false, true);
        when(mockOverviewTable.isEditing()).thenReturn(true);
        
        viewEventController.toggleEditingTable(false);
        
        verify(mockOverviewTable, times(1)).setEditFlag(true);
    }
    
    @Test
    public void testRemoveTab() {
        fail();
    }
    
    @Test
    public void testRefreshTable() {
        fail();
    }
    
    @Test
    public void testGetTableSelection() {
        fail();
    }
    
    @Test
    public void testAssignSelectionToBacklog() {
        fail();
    }
    
    @Test
    public void testEditSelectedRequirement() {
        fail();
    }
    
    @Test
    public void testCloseAllTabs() {
        fail();
    }
    
    @Test
    public void testCloseOthers() {
        fail();
    }
    
    @Test
    public void testRefreshEditRequirementPanel() {
        fail();
    }
    
    @Test
    public void testEditSelectedIteration() {
        fail();
    }
}
