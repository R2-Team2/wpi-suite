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
	 * 
	 * @oaram newReq The requirement to be added to the list of requirements in the project
	 */
	public void addRequirement(Requirement newReq){
		// add the requirement
		requirements.add(newReq);
		//in the future we may have to update the GUI to reflect that the requirement was added
	}
	
	/**
	 * Removes the requirement with the given ID
	 * 
	 * @param removeId The ID number of the requirement to be removed from the list of requirements in the project
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
	 * Provides the number of elements in the list of requirements for the project. This
	 * function is called internally by the JList in NewRequirementPanel. Returns elements
	 * in reverse order, so the newest requirement is returned first.
	 * 
	 * @return the number of requirements in the project
	 */
	public int getSize() {
		return requirements.size();
	}

	/**
	 * This function takes an index and finds the requirement in the list of requirements
	 * for the project. Used internally by the JList in NewRequirementModel.
	 * 
	 * @param index The index of the requirement to be returned
	 * @return the requirement associated with the provided index
	 */
	public Object getElementAt(int index) {
		return requirements.get(requirements.size() - 1 - index).toString();
	}
	
	
}