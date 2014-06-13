/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public interface RequirementSelectorListener 
{
	/**
	 * Tells the listener that a requirement was selected.
	 * @param requirements Object[]
	 */
	public void requirementSelected(Object[] requirements);
}
