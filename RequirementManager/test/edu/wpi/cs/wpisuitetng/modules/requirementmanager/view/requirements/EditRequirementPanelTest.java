/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.BorderLayout;
import java.util.ListIterator;

import javax.swing.JSplitPane;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs.RequirementTabsPanel;
import edu.wpi.cs.wpisuitetng.network.Network;

/**
 * @author Robert Smieja
 */
public class EditRequirementPanelTest {
	ViewEventController mockViewEventController;
	UpdateRequirementController mockUpdateRequirementController;
	Network mockNetwork;
	
	Requirement mockRequirement;
	TransactionHistory mockHistory;
	ListIterator<Transaction> mockHistoryIterator;
	
	@Mock(name = "buttonPanel")
	RequirementButtonPanel mockButtonPanel; // note the mock name attribute
	
	@Mock(name = "tabsPanel")
	RequirementTabsPanel mockTabsPanel;
	
	@Mock(name = "infoPanel")
	RequirementInformationPanel mockInfoPanel;
	
	@InjectMocks
	RequirementPanel requirementPanel;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		mockViewEventController = mock(ViewEventController.class);
		mockUpdateRequirementController = mock(UpdateRequirementController.class);
		mockNetwork = mock(Network.class);
		
		mockRequirement = mock(Requirement.class);
		mockHistory = mock(TransactionHistory.class);
		mockHistoryIterator = mock(ListIterator.class);
		// mockButtonPanel = mock(RequirementButtonPanel.class);
		// mockTabsPanel = mock(RequirementTabsPanel.class);
		// mockInfoPanel = mock()
		
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
		
		when(mockHistory.getIterator(0)).thenReturn(mockHistoryIterator);
		
		when(mockHistoryIterator.hasNext()).thenReturn(false);
		
		requirementPanel = new RequirementPanel();
		requirementPanel.setViewEventController(mockViewEventController);
		requirementPanel.setUpdateRequirementController(mockUpdateRequirementController);
		// requirementPanel.setAd
		mockRequirement.getHistory();
		
		requirementPanel.setDisplayRequirement(mockRequirement);
	}
	
	@Test
	public void testConstructor_ParentId() {
		RequirementPanel newRequirementPanel = new RequirementPanel(-1);
		
		assertNotNull(newRequirementPanel);
		assertEquals(ViewMode.CREATING, newRequirementPanel.getViewMode());
		assertNotNull(newRequirementPanel.getDisplayRequirement());
		assertEquals(-2, newRequirementPanel.getDisplayRequirement().getId());
		assertEquals(-1, newRequirementPanel.getDisplayRequirement()
		        .getParentID());
		
		assertNotNull(newRequirementPanel.getButtonPanel());
		assertEquals(newRequirementPanel, newRequirementPanel.getButtonPanel()
		        .getParent());
		assertEquals(ViewMode.CREATING, newRequirementPanel.getButtonPanel()
		        .getViewMode());
		// assertEquals(mockRequirement,
		// newRequirementPanel.getButtonPanel().getCurrentRequirement());
		assertTrue(newRequirementPanel.getListeners().contains(
		        newRequirementPanel.getButtonPanel()));
		
		assertNotNull(newRequirementPanel.getTabsPanel());
		assertEquals(newRequirementPanel, newRequirementPanel.getTabsPanel()
		        .getParent());
		assertEquals(ViewMode.CREATING, newRequirementPanel.getTabsPanel()
		        .getViewMode());
		assertEquals(mockRequirement, newRequirementPanel.getTabsPanel()
		        .getCurrentRequirement());
		assertTrue(newRequirementPanel.getListeners().contains(
		        newRequirementPanel.getTabsPanel()));
		
		assertNotNull(newRequirementPanel.getInfoPanel());
		assertEquals(newRequirementPanel, newRequirementPanel.getInfoPanel()
		        .getParent());
		assertEquals(ViewMode.CREATING, newRequirementPanel.getInfoPanel()
		        .getViewMode());
		assertEquals(mockRequirement, newRequirementPanel.getInfoPanel()
		        .getCurrentRequirement());
		assertTrue(newRequirementPanel.getListeners().contains(
		        newRequirementPanel.getInfoPanel()));
		
		assertNotNull(newRequirementPanel.getLayout());
		assertTrue(newRequirementPanel.getLayout() instanceof BorderLayout);
		
		assertNotNull(newRequirementPanel.getComponent(0));
		assertTrue(newRequirementPanel.getComponent(0) instanceof JSplitPane);
		assertEquals(JSplitPane.HORIZONTAL_SPLIT,
		        ((JSplitPane) newRequirementPanel.getComponent(0))
		                .getOrientation());
		assertTrue(((JSplitPane) newRequirementPanel.getComponent(0))
		        .isContinuousLayout());
		assertEquals(newRequirementPanel.getInfoPanel(),
		        ((JSplitPane) newRequirementPanel.getComponent(0))
		                .getLeftComponent());
		assertEquals(newRequirementPanel.getTabsPanel(),
		        ((JSplitPane) newRequirementPanel.getComponent(0))
		                .getRightComponent());
		
		assertNotNull(newRequirementPanel.getComponent(1));
		assertTrue(newRequirementPanel.getComponent(1) instanceof RequirementButtonPanel);
		assertEquals(newRequirementPanel.getButtonPanel(),
		        newRequirementPanel.getComponent(1));
	}
	
	@Test
	public void testConstructor_Requirement() {
		RequirementPanel newRequirementPanel = new RequirementPanel(
		        mockRequirement);
	}
}
