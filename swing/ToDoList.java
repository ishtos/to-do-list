package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.CreateArchiveList;
import model.CreateToDoList;

public class ToDoList extends JPanel implements ChangeListener {
	private JTabbedPane tabbedpane;
	private JLabel indexLabel;

	private JList<String> toDoList;
	private DefaultListModel<String> toDoListModel;
	private JTextField toDoInputField;
	private JButton addButton;
	private JButton modifyButton;
	private JButton removeButton;
	private JButton archiveButton;
	static private ArrayList<String> toDoal = new ArrayList<String>();

	private JList<String> archiveList;
	private DefaultListModel<String> archiveListModel;
	private JButton remove2Button;
	static private ArrayList<String> archiveal = new ArrayList<String>();

	public ToDoList() {
		super(new BorderLayout());

		toDoListModel = new DefaultListModel<String>();
		toDoList = new JList<String>(toDoListModel);
		JScrollPane userListScrollPane = new JScrollPane(toDoList);
		toDoList.addListSelectionListener(new ToDoListSelectionHandler());

		toDoInputField = new JTextField();

		JPanel buttonPanel = new JPanel();
		addButton = new JButton("add");
		modifyButton = new JButton("modify");
		removeButton = new JButton("delete");
		archiveButton = new JButton("archive");

		addButton.addActionListener(new AddActionHandler());
		modifyButton.addActionListener(new ModifyActionHandler());
		removeButton.addActionListener(new RemoveActionHandler());
		archiveButton.addActionListener(new ArchiveActionHandler());

		buttonPanel.add(addButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(archiveButton);

		archiveListModel = new DefaultListModel<String>();
		archiveList = new JList<String>(archiveListModel);
		JScrollPane archiveListScrollPane = new JScrollPane(archiveList);
		archiveList.addListSelectionListener(new archiveListSelectionHandler());

		remove2Button = new JButton("delete");
		remove2Button.addActionListener(new Remove2ActionHandler());


	    indexLabel = new JLabel();

	    JPanel displayPanel = new JPanel();
	    displayPanel.add(indexLabel);

	    tabbedpane = new JTabbedPane();
	    tabbedpane.addChangeListener(this);

	    JPanel toDoList = new JPanel();
	    toDoList.setLayout(new BorderLayout());
	    toDoList.add(userListScrollPane, BorderLayout.NORTH);
	    toDoList.add(toDoInputField, BorderLayout.CENTER);
	    toDoList.add(buttonPanel, BorderLayout.SOUTH);

	    JPanel archive = new JPanel();
	    archive.setLayout(new BorderLayout());
	    archive.add(archiveListScrollPane, BorderLayout.NORTH);
		archive.add(remove2Button, BorderLayout.SOUTH);

	    tabbedpane.addTab("ToDo List", toDoList);
	    tabbedpane.addTab("Archive", archive);

	    add(tabbedpane, BorderLayout.CENTER);
	    add(displayPanel, BorderLayout.PAGE_END);

	    init();
	}

	private void init() {
		CreateToDoList ctdl = new CreateToDoList();
		ArrayList<String> list = ctdl.getArrayList();
		for ( int i = 0; i < list.size(); i++ ) {
			String text = list.get(i);
			toDoListModel.addElement(text);
			toDoal.add(text);
		}

		CreateArchiveList cal = new CreateArchiveList();
		ArrayList<String> archivelist = cal.getArrayList();
		for ( int i = 0; i < archivelist.size(); i++ ) {
			String text = archivelist.get(i);
			archiveListModel.addElement(text);
			archiveal.add(text);
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
			text = text.replaceAll("\n", "");
			if ( text == null ) {
				return;
			}
			if ( text.length() >= 1 ) {
				toDoal.add(text);
				toDoListModel.addElement(text);
			}
			toDoInputField.setText(null);
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
			toDoal.remove(index);
			if ( text.length() != 0 ) {
				toDoal.add(index, text);
			}
			toDoInputField.setText(null);
		}
	}

	private class RemoveActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (toDoList.getSelectedIndices().length != 1) {
				return;
			}
			setButtonsEnabled(false);
			int index = toDoList.getSelectedIndex();
			toDoal.remove(index);
			Thread removeThread = new RemoveThread(index);
            removeThread.start();
            toDoInputField.setText(null);
        }
	}

	private class ArchiveActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (toDoList.getSelectedIndices().length != 1) {
				return;
			}
			setButtonsEnabled(false);
			int index = toDoList.getSelectedIndex();
			archiveal.add(toDoal.get(index));
			archiveListModel.addElement(toDoal.get(index));
			toDoal.remove(index);
			Thread removeThread = new RemoveThread(index);
            removeThread.start();
            toDoInputField.setText(null);
		}
	}

	private class Remove2ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (archiveList.getSelectedIndices().length != 1) {
				return;
			}
			setButtonsEnabled(false);
			int index = archiveList.getSelectedIndex();
			archiveal.remove(index);
			Thread remove2Thread = new Remove2Thread(index);
            remove2Thread.start();
        }
	}

	public class archiveListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			if (toDoList.getSelectedIndices().length != 1){
				return;
			}
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

	class Remove2Thread extends Thread {
		int index;
		Remove2Thread(int index) {
			this.index = index;
		}

		public void run() {
			doLongTask();
			SwingUtilities.invokeLater( new Runnable() {
				public void run() {
					archiveListModel.remove(index);
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

	public void stateChanged(ChangeEvent e) {
		int index = tabbedpane.getSelectedIndex();
		indexLabel.setText("Selected Index ： " + index);
	}

	public static ArrayList<String> getAl() {
		return ToDoList.toDoal;
	}

	public static ArrayList<String> getArchiveAl() {
		return ToDoList.archiveal;
	}
}

