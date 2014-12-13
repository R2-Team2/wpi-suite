/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * Custom cell renderer for lists of Users. This changes the text that JLists displays for Users to
 * username instead of the output of .toString()
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class UserRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(((User) value).getUsername());
        return this;
    }


}
