package swing;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.CreateToDoList;

public class ToDoList3 extends JPanel {
	private JList<String> toDoList;
	private DefaultListModel<String> toDoListModel;
	private JTextField toDoInputField;
	private JButton addButton;
	private JButton modifyButton;
	private JButton removeButton;
	static private ArrayList<String> al = new ArrayList<String>();

	public ToDoList3() {
		super(new BorderLayout());
		toDoListModel = new DefaultListModel<String>();
		toDoList = new JList<String>(toDoListModel);
		JScrollPane listScrollPane = new JScrollPane(toDoList);

		toDoList.addListSelectionListener(new ToDoListSelectionHandler());

		toDoInputField = new JTextField();

		JPanel buttonPanel = new JPanel();
		addButton = new JButton("add");
		modifyButton = new JButton("modify");
		removeButton = new JButton("delete");

		addButton.addActionListener(new AddActionHandler());
		modifyButton.addActionListener(new ModifyActionHandler());
		removeButton.addActionListener(new RemoveActionHandler());
		buttonPanel.add(addButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(removeButton);
		add(listScrollPane, BorderLayout.NORTH);
		add(toDoInputField, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		init();
    }

	private void init() {
		CreateToDoList ctdl = new CreateToDoList();
		ArrayList<String> list = ctdl.getArrayList();
		for ( int i = 0; i < list.size(); i++ ) {
			String text = list.get(i);
			toDoListModel.addElement(text);
			al.add(text);
		}
	}

	private class ToDoListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			if (toDoList.getSelectedIndices().length != 1){
				return;
			}
			toDoInputField.setText((String)toDoList.getSelectedValue());
		}
    }

	private class AddActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String text = toDoInputField.getText();
			toDoListModel.addElement(text);
			if ( text.length() != 0 ) {
				al.add(text);
			}
			toDoInputField.setText("");
		}
    }

	private class ModifyActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (toDoList.getSelectedIndices().length != 1) {
				return;
			}
			int index = toDoList.getSelectedIndex();
			String text = toDoInputField.getText();
			toDoListModel.set(index, text);
			al.remove(index);
			if ( text.length() != 0 ) {
				al.add(index, text);
			}
			toDoInputField.setText("");
		}
	}

	private class RemoveActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (toDoList.getSelectedIndices().length != 1) {
				return;
			}
			setButtonsEnabled(false);
			int index = toDoList.getSelectedIndex();
			al.remove(index);
			Thread removeThread = new RemoveThread(index);
            removeThread.start();
            toDoInputField.setText("");
        }
	}

	private void setButtonsEnabled(boolean enabled) {
		addButton.setEnabled(enabled);
		modifyButton.setEnabled(enabled);
		removeButton.setEnabled(enabled);
	}

	class RemoveThread extends Thread {
		int index;
		RemoveThread(int index) {
			this.index = index;
		}

		public void run() {
			doLongTask();
			SwingUtilities.invokeLater( new Runnable() {
				public void run() {
					toDoListModel.remove(index);
					setButtonsEnabled(true);
				}
			});
		}
    }

	private void doLongTask() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			ErrorDialog.stackTraceDialog(e);
		} catch (Exception e) {
			ErrorDialog.stackTraceDialog(e);
		}
	}

	public static ArrayList<String> getAl() {
		return ToDoList3.al;
	}
}