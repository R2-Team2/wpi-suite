/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Robert Smieja
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations.IterationPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTable;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewTreePanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewBarChartPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewPieChartPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;

/**
 * @author Robert Smieja
 */
public class TestViewEventController {
    UpdateRequirementController mockUpdateRequirementController = mock(UpdateRequirementController.class);
    IterationModel mockIterationModel = mock(IterationModel.class);

    MainView mockMainView = mock(MainView.class);
    RequirementPanel mockRequirementPanel = mock(RequirementPanel.class);
    IterationPanel mockIterationPanel = mock(IterationPanel.class);
    ToolbarView mockToolbarView = mock(ToolbarView.class);
    Iteration mockIteration = mock(Iteration.class);
    NewPieChartPanel mockPieChartPanel = mock(NewPieChartPanel.class);
    NewBarChartPanel mockBarChartPanel = mock(NewBarChartPanel.class);
    Requirement mockRequirement = mock(Requirement.class);
    TransactionHistory mockHistory = mock(TransactionHistory.class);
    OverviewTable mockOverviewTable = mock(OverviewTable.class);
    OverviewTreePanel mockOverviewTreePanel = mock(OverviewTreePanel.class);
    JTree mockTree = mock(JTree.class);
    DefaultMutableTreeNode mockTreeNode = mock(DefaultMutableTreeNode.class);
    TableCellEditor mockTableCellEditor = mock(TableCellEditor.class);

    ViewEventController viewEventController = new ViewEventController();

    @Before
    public void setup() throws Exception {
        UpdateRequirementController.setInstance(mockUpdateRequirementController);
        IterationModel.setInstance(mockIterationModel);

        when(mockRequirementPanel.getDisplayRequirement()).thenReturn(mockRequirement);
        when(mockRequirement.getId()).thenReturn(4);
        when(mockRequirement.getHistory()).thenReturn(mockHistory);
        when(mockOverviewTable.getCellEditor()).thenReturn(mockTableCellEditor);
        when(mockOverviewTreePanel.getTree()).thenReturn(mockTree);

        //        whenNew(RequirementPanel.class).withArguments(-1).thenReturn(mockRequirementPanel);
        //        whenNew(RequirementPanel.class).withArguments(3).thenReturn(mockRequirementPanel);
        //        whenNew(IterationPanel.class).withNoArguments().thenReturn(mockIterationPanel);
        //        whenNew(IterationPanel.class).withArguments(mockIteration).thenReturn(mockIterationPanel);
        //        whenNew(NewPieChartPanel.class).withArguments("TestPieChart").thenReturn(mockPieChartPanel);
        //        whenNew(NewBarChartPanel.class).withArguments("TestBarChart").thenReturn(mockBarChartPanel);

        viewEventController.setMainView(mockMainView);
        viewEventController.getListOfIterationPanels().add(mockIterationPanel);
        viewEventController.getListOfRequirementPanels().add(mockRequirementPanel);
        viewEventController.setOverviewTable(mockOverviewTable);
        viewEventController.setOverviewTree(mockOverviewTreePanel);
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

        //        whenNew(RequirementPanel.class).withArguments(mockRequirement).thenReturn(mockRequirementPanel);

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

        //        whenNew(RequirementPanel.class).withArguments(mockRequirement).thenReturn(mockRequirementPanel);

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
    public void testRemoveTab_requirementPanel() {
        assertEquals(1, viewEventController.getListOfRequirementPanels().size());

        when(mockRequirementPanel.readyToRemove()).thenReturn(true);

        viewEventController.removeTab(mockRequirementPanel);

        assertEquals(0, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(1)).remove(mockRequirementPanel);
    }

    @Test
    public void testRemoveTab_requirementPanel_notReadyToRemove() {
        assertEquals(1, viewEventController.getListOfRequirementPanels().size());

        when(mockRequirementPanel.readyToRemove()).thenReturn(false);

        viewEventController.removeTab(mockRequirementPanel);

        assertEquals(1, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(0)).remove(mockRequirementPanel);
    }

    @Test
    public void testRemoveTab_requirementPanel_notInList() {
        viewEventController.getListOfRequirementPanels().remove(mockRequirementPanel);
        assertEquals(0, viewEventController.getListOfRequirementPanels().size());

        when(mockRequirementPanel.readyToRemove()).thenReturn(true);

        viewEventController.removeTab(mockRequirementPanel);
        assertEquals(0, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(1)).remove(mockRequirementPanel);
    }

    @Test
    public void testRemoveTab_requirementPanel_notInList_notReadyToRemove() {
        viewEventController.getListOfRequirementPanels().remove(mockRequirementPanel);
        assertEquals(0, viewEventController.getListOfRequirementPanels().size());

        when(mockRequirementPanel.readyToRemove()).thenReturn(false);

        viewEventController.removeTab(mockRequirementPanel);
        assertEquals(0, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(0)).remove(mockRequirementPanel);
    }

    @Test
    public void testRemoveTab_iterationPanel() {
        assertEquals(1, viewEventController.getListOfIterationPanels().size());

        when(mockIterationPanel.readyToRemove()).thenReturn(true);

        viewEventController.removeTab(mockIterationPanel);

        assertEquals(0, viewEventController.getListOfIterationPanels().size());

        verify(mockMainView, times(1)).remove(mockIterationPanel);
    }

    @Test
    public void testRemoveTab_iterationPanel_notReadyToRemove() {
        assertEquals(1, viewEventController.getListOfIterationPanels().size());

        when(mockIterationPanel.readyToRemove()).thenReturn(false);

        viewEventController.removeTab(mockIterationPanel);

        assertEquals(1, viewEventController.getListOfIterationPanels().size());

        verify(mockMainView, times(0)).remove(mockIterationPanel);
    }

    @Test
    public void testRemoveTab_iterationPanel_notInList() {
        viewEventController.getListOfIterationPanels().remove(mockIterationPanel);
        assertEquals(0, viewEventController.getListOfIterationPanels().size());

        when(mockIterationPanel.readyToRemove()).thenReturn(true);

        viewEventController.removeTab(mockIterationPanel);
        assertEquals(0, viewEventController.getListOfIterationPanels().size());

        verify(mockMainView, times(1)).remove(mockIterationPanel);
    }

    @Test
    public void testRemoveTab_iterationPanel_notInList_notReadyToRemove() {
        viewEventController.getListOfIterationPanels().remove(mockIterationPanel);
        assertEquals(0, viewEventController.getListOfIterationPanels().size());

        when(mockIterationPanel.readyToRemove()).thenReturn(false);

        viewEventController.removeTab(mockIterationPanel);
        assertEquals(0, viewEventController.getListOfIterationPanels().size());

        verify(mockMainView, times(0)).remove(mockIterationPanel);
    }

    @Test
    public void testRefreshTable() {
        viewEventController.refreshTable();

        verify(mockOverviewTable, times(1)).refresh();
    }

    @Test
    public void testGetTableSelection() {
        when(mockOverviewTable.getSelectedRows()).thenReturn(new int[] { 0, 1, 2 });
        int[] result = viewEventController.getTableSelection();

        assertArrayEquals(new int[] { 0, 1, 2 }, result);

        verify(mockOverviewTable, times(1)).getSelectedRows();
    }

    @Test
    public void testAssignSelectionToBacklog() {
        viewEventController = spy(viewEventController);
        doNothing().when(viewEventController).refreshTable();
        doNothing().when(viewEventController).refreshTree();

        when(mockOverviewTable.getSelectedRows()).thenReturn(new int[] { 0 });
        when(mockOverviewTable.getValueAt(0, 1)).thenReturn(mockRequirement);

        viewEventController.assignSelectionToBacklog();

        verify(mockRequirement, times(1)).setIteration("Backlog");
        verify(mockUpdateRequirementController, times(1)).updateRequirement(mockRequirement);

        verify(viewEventController, times(1)).refreshTable();
        verify(viewEventController, times(1)).refreshTree();
    }

    @Test
    public void testEditSelectedRequirement() {
        viewEventController = spy(viewEventController);
        doNothing().when(viewEventController).editRequirement(mockRequirement);

        when(mockOverviewTable.getSelectedRows()).thenReturn(new int[] { 0 });
        when(mockOverviewTable.getValueAt(0, 1)).thenReturn(mockRequirement);

        viewEventController.editSelectedRequirement();

        verify(viewEventController, times(1)).editRequirement(mockRequirement);
    }

    @Test
    public void testEditSelectedRequirement_none() {
        viewEventController = spy(viewEventController);
        doNothing().when(viewEventController).editRequirement(mockRequirement);

        when(mockOverviewTable.getSelectedRows()).thenReturn(new int[] {});

        viewEventController.editSelectedRequirement();

        verify(viewEventController, times(0)).editRequirement(mockRequirement);
    }

    @Test
    public void testEditSelectedRequirement_moreThanOne() {
        viewEventController = spy(viewEventController);
        doNothing().when(viewEventController).editRequirement(mockRequirement);

        when(mockOverviewTable.getSelectedRows()).thenReturn(new int[] { 0, 1 });

        viewEventController.editSelectedRequirement();

        verify(viewEventController, times(0)).editRequirement(mockRequirement);
    }

    @Test
    public void testCloseAllTabs() {
        when(mockMainView.getTabCount()).thenReturn(2);
        when(mockMainView.getComponentAt(0)).thenReturn(mockRequirementPanel);
        when(mockMainView.getComponentAt(1)).thenReturn(mockIterationPanel);

        when(mockRequirementPanel.readyToRemove()).thenReturn(true);
        when(mockIterationPanel.readyToRemove()).thenReturn(true);

        viewEventController.closeAllTabs();

        assertEquals(0, viewEventController.getListOfIterationPanels().size());
        assertEquals(0, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(1)).removeTabAt(0);
        verify(mockMainView, times(1)).removeTabAt(1);

        verify(mockMainView, times(1)).repaint();
    }

    @Test
    public void testCloseAllTabs_notReadyToRemove() {
        when(mockMainView.getTabCount()).thenReturn(2);
        when(mockMainView.getComponentAt(0)).thenReturn(mockRequirementPanel);
        when(mockMainView.getComponentAt(1)).thenReturn(mockIterationPanel);

        when(mockRequirementPanel.readyToRemove()).thenReturn(false);
        when(mockIterationPanel.readyToRemove()).thenReturn(false);

        viewEventController.closeAllTabs();

        assertEquals(1, viewEventController.getListOfIterationPanels().size());
        assertEquals(1, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(0)).removeTabAt(0);
        verify(mockMainView, times(0)).removeTabAt(1);

        verify(mockMainView, times(1)).repaint();
    }

    @Test
    public void testCloseOthers_requirementPanelSelected() {
        when(mockMainView.getTabCount()).thenReturn(2);
        when(mockMainView.getComponentAt(0)).thenReturn(mockRequirementPanel);
        when(mockMainView.getComponentAt(1)).thenReturn(mockIterationPanel);
        when(mockMainView.getSelectedComponent()).thenReturn(mockRequirementPanel);

        when(mockIterationPanel.readyToRemove()).thenReturn(true);

        viewEventController.closeOthers();

        assertEquals(0, viewEventController.getListOfIterationPanels().size());
        assertEquals(1, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(0)).removeTabAt(0);
        verify(mockMainView, times(1)).removeTabAt(1);

        verify(mockMainView, times(1)).repaint();
    }

    @Test
    public void testCloseOthers_iterationPanelSelected() {
        when(mockMainView.getTabCount()).thenReturn(2);
        when(mockMainView.getComponentAt(0)).thenReturn(mockRequirementPanel);
        when(mockMainView.getComponentAt(1)).thenReturn(mockIterationPanel);
        when(mockMainView.getSelectedComponent()).thenReturn(mockIterationPanel);

        when(mockRequirementPanel.readyToRemove()).thenReturn(true);

        viewEventController.closeOthers();

        assertEquals(1, viewEventController.getListOfIterationPanels().size());
        assertEquals(0, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(1)).removeTabAt(0);
        verify(mockMainView, times(0)).removeTabAt(1);

        verify(mockMainView, times(1)).repaint();
    }

    @Test
    public void testCloseOthers_requirementPanelSelected_notReadyToRemove() {
        when(mockMainView.getTabCount()).thenReturn(2);
        when(mockMainView.getComponentAt(0)).thenReturn(mockRequirementPanel);
        when(mockMainView.getComponentAt(1)).thenReturn(mockIterationPanel);
        when(mockMainView.getSelectedComponent()).thenReturn(mockRequirementPanel);

        when(mockRequirementPanel.readyToRemove()).thenReturn(false);

        viewEventController.closeOthers();

        assertEquals(1, viewEventController.getListOfIterationPanels().size());
        assertEquals(1, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(0)).removeTabAt(0);
        verify(mockMainView, times(0)).removeTabAt(1);

        verify(mockMainView, times(1)).repaint();
    }

    @Test
    public void testCloseOthers_iterationPanelSelected_notReadyToRemove() {
        when(mockMainView.getTabCount()).thenReturn(2);
        when(mockMainView.getComponentAt(0)).thenReturn(mockRequirementPanel);
        when(mockMainView.getComponentAt(1)).thenReturn(mockIterationPanel);
        when(mockMainView.getSelectedComponent()).thenReturn(mockIterationPanel);

        when(mockRequirementPanel.readyToRemove()).thenReturn(false);

        viewEventController.closeOthers();

        assertEquals(1, viewEventController.getListOfIterationPanels().size());
        assertEquals(1, viewEventController.getListOfRequirementPanels().size());

        verify(mockMainView, times(0)).removeTabAt(0);
        verify(mockMainView, times(0)).removeTabAt(1);

        verify(mockMainView, times(1)).repaint();
    }

    @Test
    public void testRefreshEditRequirementPanel() {
        viewEventController.refreshEditRequirementPanel(mockRequirement);

        verify(mockRequirementPanel, times(1)).fireRefresh();
    }

    @Test
    public void testEditSelectedIteration() {
        viewEventController = spy(viewEventController);
        doNothing().when(viewEventController).editIteration(mockIteration);

        when(mockTree.getLastSelectedPathComponent()).thenReturn(mockTreeNode);
        when(mockTreeNode.getUserObject()).thenReturn(mockIteration);

        viewEventController.editSelectedIteration();

        verify(viewEventController, times(1)).editIteration(mockIteration);
    }
}
