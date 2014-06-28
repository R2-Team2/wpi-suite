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
    
    //    @Test
    //    public void AddNoteTest() {
    //        // make sure there are no notes yet
    //        assertEquals(0, reqPan.getTabsPanel().getNotePanel().
    //                getRequirement().getNotes().getNotes().toArray().length);
    //        assertFalse(reqPan.getTabsPanel().getNotePanel().
    //                getAddNoteButton().isEnabled());
    //        
    //        reqPan.getTabsPanel().getNotePanel().getNoteMessage().setText("Test string");
    //        assertEquals("Test string", reqPan.getTabsPanel().getNotePanel().
    //                getNoteMessage().getText());
    //        reqPan.getTabsPanel().getNotePanel().getAddNoteButton().setEnabled(true);
    //        assertTrue(reqPan.getTabsPanel().getNotePanel().getAddNoteButton().isEnabled());
    //        reqPan.getTabsPanel().getNotePanel().getAddNoteButton().doClick();	// add a new note
    //        assertFalse(reqPan.getTabsPanel().getNotePanel().getAddNoteButton().isEnabled());
    //        
    //        // check to see if the note was added
    //        assertEquals(1, reqPan.getTabsPanel().getNotePanel().getRequirement().getNotes().getNotes().toArray().length);
    //        assertEquals("Test string", reqPan.getTabsPanel().getNotePanel().getRequirement().getNotes().getItem(0).getMessage());
    //    }
    //    
    //    @Test
    //    public void NoteClearButtonTest() {
    //        assertEquals("", reqPan.getTabsPanel().getNotePanel().getNoteMessage().getText());
    //        reqPan.getTabsPanel().getNotePanel().getNoteMessage().setText("This should not be here when this test is over");
    //        assertEquals("This should not be here when this test is over", reqPan.getTabsPanel().getNotePanel().
    //                getNoteMessage().getText());
    //        reqPan.getTabsPanel().getNotePanel().getClearButton().setEnabled(true);
    //        reqPan.getTabsPanel().getNotePanel().getClearButton().doClick();	// clear the note text box
    //        assertFalse(reqPan.getTabsPanel().getNotePanel().getClearButton().isEnabled());
    //        assertEquals("", reqPan.getTabsPanel().getNotePanel().getNoteMessage().getText());
    //    }
    
    @Test
    public void testSerialVersionUID() {
        assertEquals(-3114980850099223953L, RequirementNotePanel.serialVersionUID);
    }
}
