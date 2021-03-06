/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

/**
 * The Class TempPanel.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class TempPanel extends JPanel {

    /** The text field. */
    private final JTextField textField;

    /**
     * Create the panel.
     */
    public TempPanel() {
        setLayout(new MigLayout("", "[61px,grow]", "[16px][]"));

        final JLabel label = new JLabel("New label");
        add(label, "cell 0 0,alignx left,aligny top");

        textField = new JTextField();
        add(textField, "cell 0 1,growx");
        textField.setColumns(10);

    }

}
