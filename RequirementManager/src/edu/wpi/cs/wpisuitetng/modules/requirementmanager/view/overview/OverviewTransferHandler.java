/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

/**
 *
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class OverviewTransferHandler extends TransferHandler{
	
	 /**
	  * Method getSourceActions.
	  * @param comp JComponent
	  * @return int
	  */
	 public int getSourceActions(JComponent comp) {
	        return COPY;
	    }

	 /**
	  * Method createTransferable.
	  * @param comp JTable
	  * @return Transferable
	  */
	 public Transferable createTransferable(JTable comp) {
		 return new StringSelection(comp.getName());
	 }
}
