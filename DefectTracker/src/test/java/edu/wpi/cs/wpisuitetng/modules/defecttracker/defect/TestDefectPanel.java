package edu.wpi.cs.wpisuitetng.modules.defecttracker.defect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.defecttracker.defect.DefectPanel.Mode;
import edu.wpi.cs.wpisuitetng.modules.defecttracker.models.Defect;

public class TestDefectPanel {

    Defect defect = new Defect();
    DefectView view = new DefectView();
    DefectPanel panel = new DefectPanel(view, defect, Mode.CREATE);

    @Test
    public void testConstructor() {
        assertEquals(view, panel.getParent());
        assertEquals(defect, panel.getModel());
        assertEquals(Mode.CREATE, panel.getMode());

        assertTrue(panel.isInputEnabled());

        //TODO SpringLayout
        //TODO addComponents();

        //TODO listeners

        //TODO updateFields()
    }
}
