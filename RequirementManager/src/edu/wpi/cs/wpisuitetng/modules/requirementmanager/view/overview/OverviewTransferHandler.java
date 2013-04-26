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
 */
public class OverviewTransferHandler extends TransferHandler{
	
	 public int getSourceActions(JComponent comp) {
	        return COPY;
	    }

	 public Transferable createTransferable(JTable comp) {
		 return new StringSelection(comp.getName());
	 }
}
