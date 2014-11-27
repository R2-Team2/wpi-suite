/**
 *
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.JPanel;

/**
 * @author dbogatov
 */
@SuppressWarnings("serial")
public class StagePanel extends JPanel implements DragSourceListener, DragGestureListener {

	private DragSource source;


	public StagePanel() {
		this.source = new DragSource();
		this.source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
		System.out.println("Drage enter view");
	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragExit(DragSourceEvent dse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {
		// TODO Auto-generated method stub

	}

}
