/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.ViewMode;

/**
 * @author Robert Smieja
 */
public class TestRequirementNotePanel {
    //TODO: All of the logic is in the constructor... needs to be refactored and then can be tested
    
    RequirementTabsPanel mockRequirementTabsPanel;
    RequirementPanel mockRequirementPanel;
    Requirement mockRequirement;
    JTextArea mockTextArea;
    
    RequirementNotePanel notePanel;
    
    @Before
    public void setup() {
        mockRequirementTabsPanel = mock(RequirementTabsPanel.class);
        mockRequirementPanel = mock(RequirementPanel.class);
        mockRequirement = mock(Requirement.class);
        mockTextArea = mock(JTextArea.class);
        
        notePanel = Whitebox.newInstance(RequirementNotePanel.class);
    }
    
    @Test
    public void testReadyToRemove() {
        when(mockTextArea.getText()).thenReturn("");
        
        notePanel.setNotesAdded(0);
        notePanel.setViewMode(ViewMode.EDITING);
        notePanel.setNoteMessage(mockTextArea);
        
        assertTrue(notePanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_hasText() {
        when(mockTextArea.getText()).thenReturn("TestText");
        
        notePanel.setNotesAdded(0);
        notePanel.setViewMode(ViewMode.EDITING);
        notePanel.setNoteMessage(mockTextArea);
        
        assertFalse(notePanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_creating() {
        when(mockTextArea.getText()).thenReturn("");
        
        notePanel.setNotesAdded(0);
        notePanel.setViewMode(ViewMode.CREATING);
        notePanel.setNoteMessage(mockTextArea);
        
        assertTrue(notePanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_hasNotesAdded() {
        when(mockTextArea.getText()).thenReturn("");
        
        notePanel.setNotesAdded(2);
        notePanel.setViewMode(ViewMode.EDITING);
        notePanel.setNoteMessage(mockTextArea);
        
        assertTrue(notePanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_hasNotesAddedAndNotEditing() {
        when(mockTextArea.getText()).thenReturn("");
        
        notePanel.setNotesAdded(2);
        notePanel.setViewMode(ViewMode.CREATING);
        notePanel.setNoteMessage(mockTextArea);
        
        assertFalse(notePanel.readyToRemove());
    }
    
    @Test
    public void testSerialVersionUID() {
        assertEquals(-3114980850099223953L, RequirementNotePanel.serialVersionUID);
    }
}
