package edu.wpi.cs.wpisuitetng.modules.defecttracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import javax.swing.KeyStroke;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.cs.wpisuitetng.janeway.gui.widgets.KeyboardShortcut;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.defecttracker.tabs.MainTabController;
import edu.wpi.cs.wpisuitetng.modules.defecttracker.tabs.MainTabView;
import edu.wpi.cs.wpisuitetng.modules.defecttracker.toolbar.ToolbarView;

public class TestJanewayModule {
    private JanewayModule janewayModule = new JanewayModule();

    @Test
    public void testConstructor() {
        assertNotNull(janewayModule);

        //Check MainTabController
        assertNotNull(janewayModule.mainTabController);
        assertNotNull(janewayModule.mainTabController.getView());

        //Check ToolbarController
        assertNotNull(janewayModule.toolbarController);
        assertNotNull(janewayModule.toolbarController.getToolbarView());
        assertEquals(2, janewayModule.mainTabController.getView().getChangeListeners().length);
        assertEquals(janewayModule.toolbarController, janewayModule.mainTabController.getView().getChangeListeners()[0]);
        assertNotNull(janewayModule.getTabs());
        assertEquals(1, janewayModule.getTabs().size());

        //Check Tabs
        ToolbarView toolbarView = (ToolbarView) janewayModule.toolbarController.getToolbarView();
        MainTabView mainTabView = janewayModule.mainTabController.getView();
        JanewayTabModel tab = janewayModule.getTabs().get(0);

        assertNotNull(tab);
        assertEquals("Defects", tab.getName());
        assertNotNull(tab.getIcon());
        assertEquals(toolbarView, tab.getToolbar());
        assertEquals(mainTabView, tab.getMainComponent());
    }

    @Test
    public void testGetName() {
        assertEquals("Defect Tracker", janewayModule.getName());
    }

    @Test
    public void testRegisterKeyboardShortcut_Mac() {
        JanewayTabModel mockTab = mock(JanewayTabModel.class);
        MainTabController mockMainTabController = mock(MainTabController.class);
        String osName = "some kind of mac";
        ArgumentCaptor<KeyboardShortcut> keyboardShortcutCaptor = ArgumentCaptor.forClass(KeyboardShortcut.class);

        janewayModule.registerKeyboardShortcuts(mockMainTabController, mockTab, osName);

        verify(mockTab, times(3)).addKeyboardShortcut(keyboardShortcutCaptor.capture());

        List<KeyboardShortcut> keyboardShortcuts = keyboardShortcutCaptor.getAllValues();

        assertEquals(KeyStroke.getKeyStroke("control TAB"), keyboardShortcuts.get(0).getKeyStroke());
        keyboardShortcuts.get(0).getActions().get(0).actionPerformed(null);
        verify(mockMainTabController, times(1)).switchToRightTab();

        assertEquals(KeyStroke.getKeyStroke("control shift TAB"), keyboardShortcuts.get(1).getKeyStroke());
        keyboardShortcuts.get(1).getActions().get(0).actionPerformed(null);
        verify(mockMainTabController, times(1)).switchToLeftTab();

        assertEquals(KeyStroke.getKeyStroke("meta W"), keyboardShortcuts.get(2).getKeyStroke());
        keyboardShortcuts.get(2).getActions().get(0).actionPerformed(null);
        verify(mockMainTabController, times(1)).closeCurrentTab();
    }

    @Test
    public void testRegisterKeyboardShortcut_NotMac() {
        JanewayTabModel mockTab = mock(JanewayTabModel.class);
        MainTabController mockMainTabController = mock(MainTabController.class);
        String osName = "archlinux because I can";
        ArgumentCaptor<KeyboardShortcut> keyboardShortcutCaptor = ArgumentCaptor.forClass(KeyboardShortcut.class);

        janewayModule.registerKeyboardShortcuts(mockMainTabController, mockTab, osName);

        verify(mockTab, times(3)).addKeyboardShortcut(keyboardShortcutCaptor.capture());

        List<KeyboardShortcut> keyboardShortcuts = keyboardShortcutCaptor.getAllValues();

        //First action
        assertEquals(KeyStroke.getKeyStroke("control TAB"), keyboardShortcuts.get(0).getKeyStroke());
        keyboardShortcuts.get(0).getActions().get(0).actionPerformed(null);
        verify(mockMainTabController, times(1)).switchToRightTab();

        assertEquals(KeyStroke.getKeyStroke("control shift TAB"), keyboardShortcuts.get(1).getKeyStroke());
        keyboardShortcuts.get(1).getActions().get(0).actionPerformed(null);
        verify(mockMainTabController, times(1)).switchToLeftTab();

        assertEquals(KeyStroke.getKeyStroke("ctrl W"), keyboardShortcuts.get(2).getKeyStroke());
        keyboardShortcuts.get(2).getActions().get(0).actionPerformed(null);
        verify(mockMainTabController, times(1)).closeCurrentTab();
    }
}
