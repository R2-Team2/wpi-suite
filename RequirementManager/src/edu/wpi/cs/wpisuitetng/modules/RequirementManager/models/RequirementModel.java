package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

/**
 *List of Requirements being pulled from the server
 * 
 * @author Gabriel McCormick
 *
 */
public class RequirementModel extends AbstractListModel{
	
	/**
	 * The list in which all the requirements for a single project are contained
	 */
	private List<Requirement> requirements;
	
	/**
	 * Constructs an empty list of requirements for the project
	 */
	public RequirementModel (){
		requirements = new ArrayList<Requirement>();
	}
	
	/**
	 * adds a single requirement to the requirements of the project
	 */
	public void addRequirement(Requirement newReq){
		// add the requirement
		requirements.add(newReq);
		//in the future we may have to update the GUI to reflect that the requirement was added
	}
	
	/**
	 * Removes the requirement with the given ID
	 */
	public void removeRequirement(int removeId){
		// iterate through list of requirements until id of project is found
		for (int i=0; i < this.requirements.size(); i++){
			
			if (requirements.get(i).getId() == removeId){
				// remove the id
				requirements.remove(i);
				break;
			}
		}
	}

	
	/**
	 * returns the number of requirements in the project.
	 */
	public int getSize() {
		return requirements.size();
	}

	/**
	 * returns the requirement at the provided index
	 */
	public Object getElementAt(int index) {
		return requirements.get(requirements.size() - 1 - index).toString();
	}
	
	
}