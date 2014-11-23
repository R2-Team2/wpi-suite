/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.buttons;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskButtonsPanel.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class TaskButtonsPanel extends ToolbarGroupView {
    // initialize the main view toolbar buttons
    /** The create button. */
    private final JButton createButton = new JButton("<html>Create<br />Task</html>");

    /** The reports button. */
    private final JButton reportsButton = new JButton("<html>Reports</html>");

    /** The settings button. */
    private final JButton settingsButton = new JButton("<html>Settings</html>");

    /** The help button. */
    private final JButton helpButton = new JButton("<html>Help</html>");

    /** The content panel. */
    private final JPanel contentPanel = new JPanel();

    /**
     * Instantiates a new task buttons panel.
     */
    public TaskButtonsPanel() {
        super("");

        this.contentPanel.setLayout(new BoxLayout(this.contentPanel, BoxLayout.X_AXIS));
        this.setPreferredWidth(700);

        this.createButton.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            Image img = ImageIO.read(this.getClass().getResourceAsStream("new_task.png"));
            this.createButton.setIcon(new ImageIcon(img));
            img = ImageIO.read(this.getClass().getResourceAsStream("reports.png"));
            this.reportsButton.setIcon(new ImageIcon(img));
            img = ImageIO.read(this.getClass().getResourceAsStream("settings.png"));
            this.settingsButton.setIcon(new ImageIcon(img));
            img = ImageIO.read(this.getClass().getResourceAsStream("help.png"));
            this.helpButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            // Hopefully, won't get here
            System.out.print("exception");
        }

        // the action listener for the Create Action Button
        this.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // bring up a create task pane
                ViewEventController.getInstance().createTask();
            }
        });

        // the action listener for the Reports Button
        this.reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // bring up a create task pane
                // TODO Action on ViewController ViewEventController.getInstance().createTask();
            }
        });

        // the action listener for the Settings Button
        this.settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // bring up a create task pane
                // TODO Action on ViewController ViewEventController.getInstance().createTask();
            }
        });

        // the action listener for the Help Button
        this.helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // bring up a create task pane
                // TODO Action on ViewController ViewEventController.getInstance().createTask();
            }
        });

        // Gray out top bar buttons that are not currently being used
        this.reportsButton.setEnabled(false);
        this.settingsButton.setEnabled(false);
        this.helpButton.setEnabled(false);

        this.contentPanel.add(this.reportsButton);
        this.contentPanel.add(this.settingsButton);
        this.contentPanel.add(this.helpButton);
        this.contentPanel.add(this.createButton);
        this.contentPanel.setOpaque(false);

        this.add(this.contentPanel);
    }

    /**
     * Method getCreateButton.
     * 
     * @return JButton
     */
    public JButton getCreateButton() {
        return this.createButton;
    }

    /**
     * Method getReportsButton.
     * 
     * @return JButton
     */
    public JButton getReportsButton() {
        return this.reportsButton;
    }

    /**
     * Method getSettingsButton.
     * 
     * @return JButton
     */
    public JButton getSettingsButton() {
        return this.settingsButton;
    }

    /**
     * Method getHelpButton.
     * 
     * @return JButton
     */
    public JButton getHelpButton() {
        return this.helpButton;
    }

    /**
     * Called when the mouse enters this toolbar group
     */
    @Override
    public void mouseEntered() {}

    /**
     * Called when the mouse exits this toolbar group
     */
    @Override
    public void mouseExited() {}

}
