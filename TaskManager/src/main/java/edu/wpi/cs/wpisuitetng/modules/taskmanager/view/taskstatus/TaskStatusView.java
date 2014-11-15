package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class TaskStatusView extends JPanel {

	TaskStatus taskStatusObj;
	
	/**
	 * Create the panel.
	 */
	public TaskStatusView(String title) {
		this.taskStatusObj = new TaskStatus(title);
		setLayout(new MigLayout("", "[236px,grow]", "[26px][200px,grow 500]"));
		
		JList scrollableList = new JList(this.taskStatusObj);
		JScrollPane scrollPane = new JScrollPane(scrollableList);
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//Change Vertical Scroll Bar Policy to AS_NEEDED When Task Cards are developed
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new EtchedBorder());
		this.add(scrollPane, "cell 0 1,grow");
		
		
		StyledDocument document = new DefaultStyledDocument();
		javax.swing.text.Style defaultStyle = document.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(defaultStyle, StyleConstants.ALIGN_CENTER);
		
		JTextPane txtpnTitle = new JTextPane(document);
		txtpnTitle.setBackground(UIManager.getColor("Button.background"));
		txtpnTitle.setBorder(null);
		txtpnTitle.setForeground(new Color(0, 0, 0));
		txtpnTitle.setEditable(false);
		txtpnTitle.setFont(txtpnTitle.getFont().deriveFont(20f));
		txtpnTitle.setText(this.taskStatusObj.getName());		
		this.add(txtpnTitle, "cell 0 0,alignx center,aligny center");
		
	}

}


