/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.buttons;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

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

    /** The help button. */
    private final JButton helpButton = new JButton("<html>Help</html>");

    /** The content panel. */
    private final JPanel contentPanel = new JPanel();


    private final JCheckBox chckbxShowArchivedTasks = new JCheckBox(
            "<html>Show<br />Archived Tasks</html>");

    /**
     * Instantiates a new task buttons panel.
     */
    public TaskButtonsPanel() {
        super("");

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        setPreferredWidth(700);

        createButton.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            Image img = ImageIO.read(this.getClass().getResourceAsStream("new_task.png"));
            createButton.setIcon(new ImageIcon(img));
            img = ImageIO.read(this.getClass().getResourceAsStream("reports.png"));
            img = ImageIO.read(this.getClass().getResourceAsStream("settings.png"));
            img = ImageIO.read(this.getClass().getResourceAsStream("help.png"));
            helpButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            // Hopefully, won't get here
            System.err.println("Populating Top Bar Buttons Exception");
            ex.printStackTrace();
        }

        // the action listener for the Create Action Button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // bring up a create task pane
                ViewEventController.getInstance().createTask();
            }
        });

        // the action listener for the Help Button
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    final Desktop desktop = Desktop.getDesktop();
                    if (!desktop.isSupported(Action.BROWSE)) {
                        System.err.println("Browse Action is not supported on this platform!");
                    } else {
                        try {
                            desktop.browse(new URL(
                                    "http://r2-team2.com:8090/display/WPIS/Task+Manager+Wiki")
                                    .toURI());
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    System.err.println("User's current platform is not supported!");
                }

            }
        });
        helpButton.setEnabled(true);
        createButton.setEnabled(true);
        contentPanel.add(helpButton);
        contentPanel.add(createButton);
        contentPanel.setOpaque(false);

        if (Desktop.isDesktopSupported()) {
            final Desktop desktop = Desktop.getDesktop();
            if (!desktop.isSupported(Action.BROWSE)) {
                helpButton.setEnabled(false);
            }
        } else {
            helpButton.setEnabled(false);
        }

        this.add(contentPanel);
        chckbxShowArchivedTasks.setSelected(false);
        chckbxShowArchivedTasks.setHorizontalAlignment(SwingConstants.LEFT);
        chckbxShowArchivedTasks.setOpaque(false);
        final TaskStatusView archived = new TaskStatusView(new TaskStatus("Archived"));

        // the action listener for the Settings Button
        chckbxShowArchivedTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chckbxShowArchivedTasks.isSelected()) {
                    ViewEventController.getInstance().showArchived(true, archived);
                } else {
                    ViewEventController.getInstance().showArchived(false, archived);
                }
            }
        });
        contentPanel.add(chckbxShowArchivedTasks);
    }

    /**
     * Method getCreateButton.
     *
     * @return JButton
     */
    public JButton getCreateButton() {
        return createButton;
    }

    /**
     * Method getHelpButton.
     *
     * @return JButton
     */
    public JButton getHelpButton() {
        return helpButton;
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
