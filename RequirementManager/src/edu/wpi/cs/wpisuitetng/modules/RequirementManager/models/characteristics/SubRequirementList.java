/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;

/**
 * A list of the IDs of the Sub-Requirements that have been associated with a Requirement
 * @author Raphael
 *
 */
public class SubRequirementList {

	private LinkedList<Integer> subreqids;


	/**
	 * Constructs an empty Sub-Requirements List
	 */
	public SubRequirementList()
	{
		this.subreqids = new LinkedList<Integer>();
	}

	/**
	 * use this function to get a list iterator that you can use to cycle through the elements of the list
	 * @param index the index of the list that you want the iterator to start on
	 * @return the iterator containing all the elements of the list
	 */
	public ListIterator<Integer> getIterator(int index){
		return this.subreqids.listIterator(index);
	}

	/**
	 * getter for the linked list of sub-requirement IDs
	 * @return the linked list of transactions
	 */
	public LinkedList<Integer> getHistory(){
		return this.subreqids;
	}

	/**
	 * allows you to add to the list of sub-requirement IDs by taking the sub-requirement and extracting its ID 
	 * always adds new sub-requirement ID to the end of the list
	 * @param ID the ID of the Requirement being added as a sub-requirement
	 * @return the sub-requirement that was just added to the history
	 */
	public Requirement add(Requirement subreq){
		int subreqID = subreq.getId();
		subreqids.add(subreqID);
		return subreq;
	}

	/**
	 * allows you to get the item at the given index in the list
	 * @param index the index at which the desired sub-requirement ID resides
	 * @return the sub-requirement with the ID at the index given by the parameter
	 */
	public Requirement getItem(int index){
		// get the ID numbers stored at the index
		int subreqID = this.subreqids.get(index);
		// get the list of requirements stored on server
		List<Requirement> requirements = RequirementModel.getInstance().getRequirements();
		// look for the requirement with the specified ID number
		for (int i = 0; i < requirements.size(); i++) {
			Requirement req = requirements.get(i);
			if (subreqID == req.getId()) {
				// return the Requirement if the ID matches
				return req;
			}
		}
		return null;		
	}
}
