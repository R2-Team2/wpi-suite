/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

/**
 * This panel fills the main content area of the tab for this module.
 * It is currently blank.
 */
@SuppressWarnings("serial")
public class MainView extends JTabbedPane {
    
    private final JPopupMenu popup = new JPopupMenu();
    private JMenuItem closeAll = new JMenuItem("Close All Tabs");
    private JMenuItem closeOthers = new JMenuItem("Close Others");
    
    /**
     * Construct the panel. There is some test text inside the panel.
     */
    public MainView() {
    	this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    	
    	
    	
    	
    	
    	
    	
    	closeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ViewEventController.getInstance().closeAllTabs();
            }
        });
    	
    	closeOthers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ViewEventController.getInstance().closeOthers();
            }
        });
    	
    	popup.add(closeAll);
        popup.add(closeOthers);
        
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger())
                    popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        
    	/*
    	JLabel testLabel = new JLabel();
    	testLabel.setText("We're 80% done, Your Excellence!");
    	this.add(testLabel);
    	testLabel.setVisible(true);
    	*/
    }
    
    
}
