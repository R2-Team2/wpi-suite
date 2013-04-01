package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.IterationController.AddIterationController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.AddRequirementController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;

/**
 * List of Iterations being pulled from the server
 * 
 * @author Gabriel McCormick
 * 
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class IterationModel extends AbstractListModel {

	/**
	 * The list in which all the Iterations for a single project are contained
	 */
	private ArrayList<Iteration> Iterations;
	private int nextID; // the next available ID number for the Iterations that
						// are added.

	// the static object to allow the Iteration model to be
	private static IterationModel instance;

	/**
	 * Constructs an empty list of Iterations for the project
	 */
	private IterationModel() {
		Iterations = new ArrayList<Iteration>();
		nextID = 0;
	}

	/**
	 * Returns the instance of the Iteration model singleton.
	 */
	public static IterationModel getInstance() {
		if (instance == null) {
			instance = new IterationModel();
		}

		return instance;
	}

	/**
	 * Adds a single Iteration to the Iterations of the project
	 * 
	 * @oaram newReq The Iteration to be added to the list of Iterations in the
	 *        project
	 */
	public void addIteration(Iteration newReq) {
		// add the Iteration
		// Iterations.add(Iterations);
		try {
			AddIterationController.getInstance().addIteration(newReq);
			ViewEventController.getInstance().refreshTable();
		} catch (Exception e) {

		}
	}

	/**
	 * Removes the Iteration with the given ID
	 * 
	 * @param removeId
	 *            The ID number of the Iteration to be removed from the list of
	 *            Iterations in the project
	 */
	public void removeIteration(int removeId) {
		// iterate through list of Iterations until id of project is found
		for (int i = 0; i < this.Iterations.size(); i++) {
			// if (Iterations.get(i).getId() == removeId){
			// remove the id
			// Iterations.remove(i);
			// break;
			// }
		}

		ViewEventController.getInstance().refreshTable();
	}

	/**
	 * Provides the number of elements in the list of Iterations for the
	 * project. This function is called internally by the JList in
	 * NewIterationPanel. Returns elements in reverse order, so the newest
	 * Iteration is returned first.
	 * 
	 * @return the number of Iterations in the project
	 */
	public int getSize() {
		return Iterations.size();
	}

	/**
	 * 
	 * Provides the next ID number that should be used for a new Iteration that
	 * is created.
	 * 
	 * @return the next open id number
	 */
	public int getNextID() {

		return this.nextID++;
	}

	/**
	 * This function takes an index and finds the Iteration in the list of
	 * Iterations for the project. Used internally by the JList in
	 * NewIterationModel.
	 * 
	 * @param index
	 *            The index of the Iteration to be returned
	 * @return the Iteration associated with the provided index
	 */
	public Iteration getElementAt(int index) {
		return Iterations.get(Iterations.size() - 1 - index);
	}

	/**
	 * Removes all Iterations from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of the model, because
	 * other classes in this module have references to it. Hence, we manually
	 * remove each Iteration from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Iteration> iterator = Iterations.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		ViewEventController.getInstance().refreshTable();
	}

	/**
	 * Adds the given array of Iterations to the list
	 * 
	 * @param Iterations
	 *            the array of Iterations to add
	 */
	public void addIterations(Iteration[] Iterations) {
		for (int i = 0; i < Iterations.length; i++) {
			// this.Iterations.add(Iterations[i]);
			// if (Iterations[i].getId() >= nextID)
			// nextID = Iterations[i].getId() + 1;
			// }
			// this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
			// ViewEventController.getInstance().refreshTable();
		}
	}

	/**
	 * Returns the list of the Iterations
	 * 
	 * @return the Iterations held within the Iterationmodel.
	 */
	public List<Iteration> getIterations() {
		return Iterations;
	}

}
