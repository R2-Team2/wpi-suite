package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class MainView extends JPanel {

	public MainView()
	{
		JButton testButton = new JButton("Test");
		testButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel newFrame = new JLabel("Test");
				add(newFrame);
				repaint();
			}
		
		});
		add(testButton);
		add(new JLabel("PostBoard placeholder"));
		setBorder(BorderFactory.createLineBorder(Color.green, 2));
	}
}
