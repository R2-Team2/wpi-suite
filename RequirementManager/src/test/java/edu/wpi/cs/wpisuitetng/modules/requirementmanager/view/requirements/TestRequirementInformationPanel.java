package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Dimension;

import javax.swing.DefaultListModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class TestRequirementInformationPanel {
    //TODO Finish

    private Iteration mockIteration = mock(Iteration.class);
    private IterationModel mockIterationModel = mock(IterationModel.class);
    private ViewEventController mockViewEventController = mock(ViewEventController.class);
    private Requirement mockRequirement = mock(Requirement.class);
    private RequirementModel mockRequirementModel = mock(RequirementModel.class);
    private RequirementPanel mockRequirementPanel = mock(RequirementPanel.class);
    private ViewMode mode = ViewMode.EDITING;

    @Before
    public void setup() {
        when(mockRequirement.getParentID()).thenReturn(-1);
        when(mockRequirement.getStatus()).thenReturn(RequirementStatus.NEW);
        when(mockRequirementModel.getPossibleParents(any(Requirement.class))).thenReturn(new DefaultListModel<Requirement>());
        when(mockIteration.toString()).thenReturn("");
        when(mockIterationModel.getBacklog()).thenReturn(mockIteration);

        RequirementModel.setInstance(mockRequirementModel);
        IterationModel.setInstance(mockIterationModel);
        ViewEventController.setInstance(mockViewEventController);
    }

    @Test
    public void testConstructor() {

        RequirementInformationPanel newPanel = new RequirementInformationPanel(mockRequirementPanel, mode, mockRequirement);

        assertNotNull(newPanel);
        assertEquals(mockRequirement, newPanel.getCurrentRequirement());
        assertEquals(mockRequirementPanel, newPanel.getParentPanel());
        assertEquals(mode, newPanel.getViewMode());
        assertEquals(new Dimension(500, 200), newPanel.getMinimumSize());
    }

    @After
    public void cleanup() {
        IterationModel.setInstance(null);
        ViewEventController.setInstance(null);
    }
}
