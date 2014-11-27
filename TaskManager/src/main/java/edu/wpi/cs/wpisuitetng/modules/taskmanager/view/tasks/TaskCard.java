/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskCard.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class TaskCard extends JPanel implements Transferable, DragSourceListener,
DragGestureListener {

	/** The task obj. */
	private Task taskObj;

	private DragSource source;
	private TransferHandler transferHandler;

	private String name;
	private String date;
	private String userName;
	private TaskStatusView parent;

	/** The task name. */
	JTextPane taskName = new JTextPane();

	public TaskCard(String nameData, String dateData, String userNameData, TaskStatusView parent) {
		this(nameData, dateData, userNameData);
		this.parent = parent;
	}

	/**
	 * Create the panel.
	 *
	 * @param nameData the name data
	 * @param dateData the date data
	 * @param userNameData the user name data
	 */
	public TaskCard(String nameData, String dateData, String userNameData) {
		setBorder(new LineBorder(Color.black));
		setLayout(new MigLayout("", "[grow,fill]", "[grow][bottom]"));

		name = nameData;
		date = dateData;
		userName = userNameData;

		// truncates the displayed task title if it's longer than 25 characters. if
		if (nameData.length() > 45) {
			taskName.setToolTipText(nameData);
			nameData = nameData.substring(0, 45).concat("...");
		}

		taskName.setText(nameData);
		taskName.setFont(new Font("Tahoma", Font.BOLD, 14));
		taskName.setEditable(false);
		taskName.setBackground(UIManager.getColor("Button.background"));
		this.add(taskName, "cell 0 0,alignx center,aligny center");

		final JPanel infoPanel = new JPanel();
		this.add(infoPanel, "cell 0 1,grow");
		infoPanel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));

		final JLabel date = new JLabel(dateData);
		date.setFont(new Font("Tahoma", Font.PLAIN, 12));
		infoPanel.add(date, "cell 0 0,alignx left");

		final JLabel userName = new JLabel(userNameData);
		userName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		infoPanel.add(userName, "cell 1 0,alignx right");

		TransferHandler transfer = new TransferHandler("text");
		setTransferHandler(transfer);

		final String tNameData = nameData;

		transferHandler = new TransferHandler() {
			@Override
			public Transferable createTransferable(JComponent c) {
				return new TaskCard(tNameData, dateData, userNameData);
			}
		};
		setTransferHandler(transferHandler);
		source = new DragSource();
		source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] {new DataFlavor(TaskCard.class, "TaskCard")};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return true;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) {
		return this;
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		source.startDrag(dge, DragSource.DefaultMoveDrop, this.createImage(this), new Point(
				-getWidth() / 2, -getHeight() / 2), new TaskCard(name, date,
						userName), this);
	}

	private BufferedImage createImage(JPanel panel) {
		int w = panel.getWidth();
		int h = panel.getHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		panel.paint(g);
		return bi;
	}

	/**
	 * Gets the task obj.
	 *
	 * @return the task obj
	 */
	public Task getTaskObj() {
		return taskObj;
	}

	/**
	 * Sets the task obj.
	 *
	 * @param taskObj the new task obj
	 */
	public void setTaskObj(Task taskObj) {
		this.taskObj = taskObj;
	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
		System.out.println("f*ck");

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
