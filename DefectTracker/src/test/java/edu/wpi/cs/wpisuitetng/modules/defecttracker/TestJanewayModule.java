package edu.wpi.cs.wpisuitetng.modules.defecttracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
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

        //TODO Register KeyboardShortcut
    }

    @Test
    public void testGetName() {
        assertEquals("Defect Tracker", janewayModule.getName());
    }
}
