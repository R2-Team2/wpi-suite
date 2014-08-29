/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Robert Smieja
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs.RequirementTabsPanel;
import edu.wpi.cs.wpisuitetng.network.Network;

/**
 * @author Robert Smieja
 */
public class TestRequirementPanel {
    ViewEventController mockViewEventController = mock(ViewEventController.class);
    UpdateRequirementController mockUpdateRequirementController;
    Network mockNetwork = mock(Network.class);

    Requirement mockRequirement;

    RequirementButtonPanel mockButtonPanel;
    RequirementTabsPanel mockTabsPanel;
    RequirementInformationPanel mockInfoPanel;

    List<RequirementPanelListener> mockListeners;
    RequirementPanelListener mockListener1;
    RequirementPanelListener mockListener2;

    RequirementConfirmationDialog mockConfirmDialog = mock(RequirementConfirmationDialog.class);

    ErrorPanel mockErrorPanel;

    RequirementPanel requirementPanel;

    @Before
    public void setup() throws Exception {
        mockUpdateRequirementController = mock(UpdateRequirementController.class);

        mockRequirement = mock(Requirement.class);

        mockButtonPanel = mock(RequirementButtonPanel.class);
        mockTabsPanel = mock(RequirementTabsPanel.class);
        mockInfoPanel = mock(RequirementInformationPanel.class);

        mockListeners = new ArrayList<RequirementPanelListener>();
        mockListener1 = mock(RequirementPanelListener.class);
        mockListener2 = mock(RequirementPanelListener.class);

        mockListeners.add(mockListener1);
        mockListeners.add(mockListener2);

        mockErrorPanel = mock(ErrorPanel.class);

        when(mockButtonPanel.getErrorPanel()).thenReturn(mockErrorPanel);

        String testName = "test: Name";
        String testDescription = "test: Description";
        String testRelease = "1.0.2";
        int testEstimate = 0;

        when(mockRequirement.getName()).thenReturn(testName);
        when(mockRequirement.getDescription()).thenReturn(testDescription);
        when(mockRequirement.getRelease()).thenReturn(testRelease);
        when(mockRequirement.getStatus()).thenReturn(RequirementStatus.NEW);
        when(mockRequirement.getPriority()).thenReturn(RequirementPriority.LOW);
        when(mockRequirement.getEstimate()).thenReturn(testEstimate);
        when(mockRequirement.getType()).thenReturn(RequirementType.EPIC);

        requirementPanel = Whitebox.newInstance(RequirementPanel.class);

        requirementPanel.setViewEventController(mockViewEventController);
        requirementPanel.setUpdateRequirementController(mockUpdateRequirementController);
        requirementPanel.setDisplayRequirement(mockRequirement);
        requirementPanel.setViewMode(ViewMode.CREATING);
        requirementPanel.setButtonPanel(mockButtonPanel);
        requirementPanel.setInfoPanel(mockInfoPanel);
        requirementPanel.setTabsPanel(mockTabsPanel);
        requirementPanel.setListeners(mockListeners);
        requirementPanel.setConfirmDialog(mockConfirmDialog);
    }

    @Test
    public void testOKPressed_validInfoPanel() {
        when(mockInfoPanel.validateFields(true)).thenReturn(true);

        requirementPanel.OKPressed();

        assertTrue(requirementPanel.isReadyToClose());

        verify(mockInfoPanel, times(1)).update();
        verify(mockViewEventController, times(1)).removeTab(requirementPanel);
    }

    @Test
    public void testOKPressed_invalidInfoPanel() {
        when(mockInfoPanel.validateFields(true)).thenReturn(false);

        requirementPanel.OKPressed();

        assertFalse(requirementPanel.isReadyToClose());

        verify(mockInfoPanel, times(0)).update();
        verify(mockViewEventController, times(0)).removeTab(requirementPanel);
    }

    @Test
    public void testClearPressed() {
        requirementPanel.clearPressed();

        verify(mockInfoPanel, times(1)).clearInfo();
    }

    @Test
    public void testCancelPressed() {
        requirementPanel.cancelPressed();

        verify(mockViewEventController, times(1)).refreshTable();
        verify(mockViewEventController, times(1)).refreshTree();
        verify(mockViewEventController, times(1)).removeTab(requirementPanel);
    }

    @Test
    public void testDeletePressed() {
        when(mockRequirement.getStatus()).thenReturn(RequirementStatus.COMPLETE);

        requirementPanel.deletePressed();

        assertTrue(requirementPanel.isReadyToClose());

        verify(mockRequirement, times(1)).setStatus(RequirementStatus.DELETED);
        verify(mockUpdateRequirementController, times(1)).updateRequirement(mockRequirement);
        verify(mockViewEventController, times(1)).refreshTable();
        verify(mockViewEventController, times(1)).refreshTree();
        verify(mockViewEventController, times(1)).removeTab(requirementPanel);
    }

    @Test
    public void testDeletePressed_InProgress() {
        when(mockRequirement.getStatus()).thenReturn(RequirementStatus.INPROGRESS);

        requirementPanel.deletePressed();

        assertFalse(requirementPanel.isReadyToClose());

        verify(mockRequirement, times(0)).setStatus(RequirementStatus.DELETED);
        verify(mockUpdateRequirementController, times(0)).updateRequirement(mockRequirement);
        verify(mockViewEventController, times(0)).refreshTable();
        verify(mockViewEventController, times(0)).refreshTree();
        verify(mockViewEventController, times(0)).removeTab(requirementPanel);
    }

    @Test
    public void testFireDeleted() {
        requirementPanel.fireDeleted(true);

        verify(mockListener1, times(1)).fireDeleted(true);
        verify(mockListener2, times(1)).fireDeleted(true);
    }

    @Test
    public void testFireValid() {
        requirementPanel.fireValid(true);

        verify(mockListener1, times(1)).fireValid(true);
        verify(mockListener2, times(1)).fireValid(true);
    }

    @Test
    public void testFireChanges() {
        requirementPanel.fireChanges(true);

        verify(mockListener1, times(1)).fireChanges(true);
        verify(mockListener2, times(1)).fireChanges(true);
    }

    @Test
    public void testFireRefresh() {
        requirementPanel.fireRefresh();

        verify(mockListener1, times(1)).fireRefresh();
        verify(mockListener2, times(1)).fireRefresh();
    }

    @Test
    public void testDisplayError() {
        requirementPanel.displayError("Test Message");

        verify(mockErrorPanel, times(1)).displayError("Test Message");
    }

    @Test
    public void testRemoveError() {
        requirementPanel.removeError("Test Message");

        verify(mockErrorPanel, times(1)).removeError("Test Message");
    }

    @Test
    public void testReadyToRemove_readyToClose() {
        requirementPanel.setReadyToClose(true);

        assertTrue(requirementPanel.readyToRemove());
    }

    @Test
    public void testReadyToRemove_allListenersAreReady() {
        when(mockListener1.readyToRemove()).thenReturn(Boolean.TRUE);
        when(mockListener2.readyToRemove()).thenReturn(Boolean.TRUE);

        assertTrue(requirementPanel.readyToRemove());
    }

    @Test
    public void testReadyToRemove_optionPaneYes() {
        when(mockListener1.readyToRemove()).thenReturn(Boolean.TRUE);
        when(mockListener2.readyToRemove()).thenReturn(Boolean.FALSE);

        //        when(JOptionPane.showConfirmDialog(requirementPanel, "Discard unsaved changes and close tab?", "Discard Changes?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE))
        //                .thenReturn(0);

        assertTrue(requirementPanel.readyToRemove());
    }

    @Test
    public void testReadyToRemove_optionPaneNo() {
        when(mockListener1.readyToRemove()).thenReturn(Boolean.FALSE);
        when(mockListener2.readyToRemove()).thenReturn(Boolean.FALSE);

        //        when(JOptionPane.showConfirmDialog(requirementPanel, "Discard unsaved changes and close tab?", "Discard Changes?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE))
        //                .thenReturn(1);

        assertFalse(requirementPanel.readyToRemove());
    }

    @Test
    public void testSerialVersionUID() {
        assertEquals(-4952819151288519999L, RequirementPanel.serialVersionUID);
    }
}
