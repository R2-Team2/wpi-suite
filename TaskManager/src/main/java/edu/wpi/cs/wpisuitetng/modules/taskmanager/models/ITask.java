/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    R2-Team2
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

/**
 * Basic Task interface
 *  More methods will be added as necessary.
 *
 */
public interface ITask {
	
	String getTitle();
	ITask setStatus(String status);
	
}
