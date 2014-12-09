/**
 *
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;

/**
 * @author dbogatov
 */
public class SearchBarGroup extends ToolbarGroupView {

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	private final JTextField searchBar = new JTextField();
	private final JButton btnCancel;
	private final JCheckBox chckbxDescription;

	public SearchBarGroup() {
		super("");
		setPreferredWidth(350);
		contentPanel.setLayout(null);
		searchBar.setBounds(62, 20, 280, 28);

		contentPanel.add(searchBar);
		contentPanel.setOpaque(false);

		this.add(contentPanel);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(6, 26, 61, 16);
		contentPanel.add(lblSearch);

		chckbxDescription = new JCheckBox("Description");
		chckbxDescription.setToolTipText("Search through descriptions");
		chckbxDescription.setBounds(16, 60, 105, 23);
		chckbxDescription.setOpaque(false);
		contentPanel.add(chckbxDescription);

		chckbxDescription.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().length() != 0) {
					searchBarTextChanged();
				}
			}
		});

		JCheckBox chckbxArchieved = new JCheckBox("Archived");
		chckbxArchieved.setEnabled(false);
		chckbxArchieved.setBounds(133, 60, 94, 23);
		chckbxArchieved.setOpaque(false);
		contentPanel.add(chckbxArchieved);

		btnCancel = new JButton("Cancel");
		btnCancel.setEnabled(false);
		btnCancel.setBounds(225, 60, 117, 29);
		contentPanel.add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchBar.setText("");
				searchBarTextChanged();
			}
		});

		searchBar.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				searchBarTextChanged();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				searchBarTextChanged();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				searchBarTextChanged();

			}
		});
	}

	private void searchBarTextChanged() {
		System.out.println("Search string: " + searchBar.getText());
		if (chckbxDescription.isSelected()) {
			ViewEventController.getInstance().filterTasksWithDescription(searchBar.getText());
		} else {
			ViewEventController.getInstance().filterTasks(searchBar.getText());
		}
		ViewEventController.getInstance().revalidateAll();

		btnCancel.setEnabled(searchBar.getText().length() != 0);
	}

	/**
	 * Called when the mouse enters this toolbar group
	 */
	@Override
	public void mouseEntered() {}

	/**
	 * Called when the mouse exits this toolbar group
	 */
	@Override
	public void mouseExited() {}
}
