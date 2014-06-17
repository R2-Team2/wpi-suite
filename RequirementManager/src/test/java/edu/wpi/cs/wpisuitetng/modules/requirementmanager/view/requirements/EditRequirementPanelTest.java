/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.constructorsDeclaredIn;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import java.util.ListIterator;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

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
@RunWith(PowerMockRunner.class)
@PrepareForTest({ RequirementPanel.class, RequirementButtonPanel.class, RequirementTabsPanel.class, RequirementInformationPanel.class })
public class EditRequirementPanelTest {
    ViewEventController mockViewEventController;
    UpdateRequirementController mockUpdateRequirementController;
    Network mockNetwork;
    
    Requirement mockRequirement;
    TransactionHistory mockHistory;
    ListIterator<Transaction> mockHistoryIterator;
    
    RequirementButtonPanel mockButtonPanel;
    RequirementTabsPanel mockTabsPanel;
    RequirementInformationPanel mockInfoPanel;
    
    RequirementPanel requirementPanel;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setup() throws Exception {
        mockViewEventController = mock(ViewEventController.class);
        mockUpdateRequirementController = mock(UpdateRequirementController.class);
        mockNetwork = mock(Network.class);
        
        mockRequirement = mock(Requirement.class);
        mockHistory = mock(TransactionHistory.class);
        mockHistoryIterator = mock(ListIterator.class);
        
        mockButtonPanel = mock(RequirementButtonPanel.class);
        mockTabsPanel = mock(RequirementTabsPanel.class);
        mockInfoPanel = mock(RequirementInformationPanel.class);
        
        mockStatic(RequirementButtonPanel.class);
        mockStatic(RequirementTabsPanel.class);
        mockStatic(RequirementInformationPanel.class);
        
        suppress(constructorsDeclaredIn(RequirementButtonPanel.class, RequirementTabsPanel.class, RequirementInformationPanel.class));
        
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
        
        requirementPanel = Whitebox.newInstance(RequirementPanel.class);
        requirementPanel.setViewEventController(mockViewEventController);
        requirementPanel.setUpdateRequirementController(mockUpdateRequirementController);
        requirementPanel.setDisplayRequirement(mockRequirement);
    }
}
