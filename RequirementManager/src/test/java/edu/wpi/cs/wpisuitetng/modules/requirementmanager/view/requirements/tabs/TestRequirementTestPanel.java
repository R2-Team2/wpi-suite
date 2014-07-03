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
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.ViewMode;

/**
 * @author Robert Smieja
 */
//TODO: All of the logic is in the constructor... needs to be refactored and then can be tested
public class TestRequirementTestPanel {
    JTextField mockTitle;
    JTextArea mockMessage;
    
    RequirementTestPanel testPanel;
    
    @Before
    public void setup() throws Exception {
        mockTitle = mock(JTextField.class);
        mockMessage = mock(JTextArea.class);
        
        testPanel = Whitebox.newInstance(RequirementTestPanel.class);
        testPanel.setTestTitle(mockTitle);
        testPanel.setTestMessage(mockMessage);
    }
    
    @Test
    public void testReadyToRemove() {
        when(mockMessage.getText()).thenReturn("");
        when(mockTitle.getText()).thenReturn("");
        testPanel.setTestsAdded(0);
        testPanel.setViewMode(ViewMode.EDITING);
        
        assertTrue(testPanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_hasMessage() {
        when(mockMessage.getText()).thenReturn("Test Message");
        when(mockTitle.getText()).thenReturn("");
        testPanel.setTestsAdded(0);
        testPanel.setViewMode(ViewMode.EDITING);
        
        assertFalse(testPanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_hasTitle() {
        when(mockMessage.getText()).thenReturn("");
        when(mockTitle.getText()).thenReturn("Test Title");
        testPanel.setTestsAdded(0);
        testPanel.setViewMode(ViewMode.EDITING);
        
        assertFalse(testPanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_creating() {
        when(mockMessage.getText()).thenReturn("");
        when(mockTitle.getText()).thenReturn("");
        testPanel.setTestsAdded(0);
        testPanel.setViewMode(ViewMode.CREATING);
        
        assertTrue(testPanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_testsAdded() {
        when(mockMessage.getText()).thenReturn("");
        when(mockTitle.getText()).thenReturn("");
        testPanel.setTestsAdded(2);
        testPanel.setViewMode(ViewMode.EDITING);
        
        assertTrue(testPanel.readyToRemove());
    }
    
    @Test
    public void testReadyToRemove_testsAddedAndCreating() {
        when(mockMessage.getText()).thenReturn("");
        when(mockTitle.getText()).thenReturn("");
        testPanel.setTestsAdded(2);
        testPanel.setViewMode(ViewMode.CREATING);
        
        assertFalse(testPanel.readyToRemove());
    }
    
    @Test
    public void testSerialVersionUID() {
        assertEquals(-4854352702064502285L, RequirementTestPanel.serialVersionUID);
    }
}
