package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Approval;
import model.CreateFollowingList;
import model.CreateToDoList;
import model.CreateUserList;
import model.Login;
import model.RemoveUserList;

public class UserList extends JPanel implements ChangeListener {
	private JTabbedPane tabbedpane;
	private JLabel indexLabel;

	private JList<String> userList;
	private DefaultListModel<String> userListModel;
	private ArrayList<String> useral = new ArrayList<String>();

	private JList<String> followingList;
	private DefaultListModel<String> followingListModel;
	static private ArrayList<String> followingal = new ArrayList<String>();
	private JButton followingButton;

	private JList<String> unfollowList;
	private DefaultListModel<String> unfollowListModel;
	private ArrayList<String> unfollowal = new ArrayList<String>();
	private JButton unfollowButton;

	private JList<String> taskList;
	private DefaultListModel<String> taskListModel;
	private HashMap<String, ArrayList<String> > taskhm = new HashMap<String, ArrayList<String> >();
	private JButton renewButton;

	private JList<String> approvalList;
	private DefaultListModel<String> approvalListModel;
	private ArrayList<String> approvalal = new ArrayList<String>();
	private ArrayList<String> approvalinit = new ArrayList<String>();
	private JButton approvalButton;

	private JButton AdminButton;

	//private JButton LogoutButton;

	public UserList() {
		super(new BorderLayout());

		userListModel = new DefaultListModel<String>();
		userList = new JList<String>(userListModel);
		JScrollPane userListScrollPane = new JScrollPane(userList);

		followingListModel = new DefaultListModel<String>();
		followingList = new JList<String>(followingListModel);
		JScrollPane followingListScrollPane = new JScrollPane(followingList);
		followingList.addListSelectionListener(new follwingListSelectionHandler());

		unfollowListModel = new DefaultListModel<String>();
		unfollowList = new JList<String>(unfollowListModel);
		JScrollPane unfollowListScrollPane = new JScrollPane(unfollowList);
		unfollowList.addListSelectionListener(new unfollwListSelectionHandler());

		taskListModel = new DefaultListModel<String>();
		taskList = new JList<String>(taskListModel);
		JScrollPane taskListScrollPane = new JScrollPane(taskList);

		approvalListModel = new DefaultListModel<String>();
		approvalList = new JList<String>(approvalListModel);
		JScrollPane approvalListScrollPane = new JScrollPane(approvalList);
		approvalList.addListSelectionListener(new approvalListSelectionHandler());

	    indexLabel = new JLabel();

	    JPanel displayPanel = new JPanel();
	    displayPanel.add(indexLabel);

	    tabbedpane = new JTabbedPane();
	    tabbedpane.addChangeListener(this);

	    JPanel allUser = new JPanel();
	    allUser.setLayout(new BorderLayout());
	    allUser.add(userListScrollPane, BorderLayout.NORTH);
	    if ( Login.getStaticName().equals("java") ) {
			AdminButton = new JButton("remove");
			AdminButton.addActionListener(new AdminActionHandler());
			allUser.add(AdminButton, BorderLayout.CENTER);
	    }

	    /*
	    LogoutButton = new JButton("Logout");
	    LogoutButton.addActionListener(new LogoutActionHandler());
	    allUser.add(LogoutButton, BorderLayout.SOUTH);
	    */

	    JPanel following = new JPanel();
	    following.setLayout(new BorderLayout());
	    followingButton = new JButton("unfollow");
	    followingButton.addActionListener(new FollowingActionHandler());
	    following.add(followingListScrollPane, BorderLayout.NORTH);
		following.add(followingButton, BorderLayout.SOUTH);

	    JPanel unfollow = new JPanel();
	    unfollow.setLayout(new BorderLayout());
	    unfollowButton = new JButton("follow");
	    unfollowButton.addActionListener(new UnfollowActionHandler());
	    unfollow.add(unfollowListScrollPane, BorderLayout.NORTH);
		unfollow.add(unfollowButton, BorderLayout.SOUTH);

		JPanel task = new JPanel();
		task.setLayout(new BorderLayout());
		renewButton = new JButton("renew");
	    renewButton.addActionListener(new RenewActionHandler());
		task.add(taskListScrollPane, BorderLayout.NORTH);
		task.add(renewButton, BorderLayout.SOUTH);

		JPanel approval = new JPanel();
		approval.setLayout(new BorderLayout());
		approvalButton = new JButton("approval");
		approvalButton.addActionListener(new ApprovalActionHandler());
		approval.add(approvalListScrollPane, BorderLayout.NORTH);
		approval.add(approvalButton, BorderLayout.SOUTH);

	    tabbedpane.addTab("User List", allUser);
	    tabbedpane.addTab("Following", following);
	    tabbedpane.addTab("Unfollow", unfollow);
	    tabbedpane.addTab("ToDo List(following)", task);
	    tabbedpane.addTab("Approval", approval);

	    setDetail();

	    add(tabbedpane, BorderLayout.CENTER);
	    add(displayPanel, BorderLayout.PAGE_END);

	    init();
	}

	private void init() {
		CreateUserList cul = new CreateUserList();
		useral = cul.getUserList();
		for ( int i = 0; i < useral.size(); i++ ) {
			String s = (i + 1) + ": " + useral.get(i);
			if ( useral.get(i).equals(Login.getStaticName()) ) s += " (you)";
			userListModel.addElement(s);
		}

		CreateFollowingList cfl = new CreateFollowingList();
		followingal = cfl.getFollowingList();
		Collections.sort(followingal);
		for ( int i = 0; i < followingal.size(); i++ ) {
			followingListModel.addElement(followingal.get(i));
		}

		Approval app = new Approval();
		approvalal = app.loadRequest();
		for ( int i = 0; i < approvalal.size(); i++ ) {
			approvalListModel.addElement(approvalal.get(i));
		}

		approvalinit = app.loadInit();
		for ( int i = 0; i < useral.size(); i++ ) {
			String s = useral.get(i);
			if ( followingal.contains(s) || s.equals(Login.getStaticName())) continue;
			else {
				if ( approvalinit.contains(s) ) {
					s = s + "(Allow waiting)";
				}
				unfollowal.add(s);
				unfollowListModel.addElement(s);
			}
		}

		makeTaskListModel();
		doLongTask();
	}

	private class AdminActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (userList.getSelectedIndices().length != 1) {
				return;
			}
			String name = (String)userList.getSelectedValue();
			if ( name.endsWith("(you)") ) {
				return;
			}
			name = name.substring(3, name.length());
			RemoveUserList rul = new RemoveUserList();
			if ( rul.removeUserList(name) ) {
				System.out.println("Delete:" + name + " success");
			} else {
				System.out.println("Delete:" + name + " false");
			}

			userListModel.clear();
			followingListModel.clear();
			unfollowListModel.clear();
			useral.clear();
			followingal.clear();
			unfollowal.clear();
			init();
			doLongTask();
        }
	}

	private class follwingListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			if (followingList.getSelectedIndices().length != 1) {
				return;
			}
		}
    }

	private class FollowingActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (followingList.getSelectedIndices().length != 1) {
				return;
			}

			String text = (String)followingList.getSelectedValue();
			followingListModel.clear();
			if ( followingal.contains(text) ) {
				followingal.remove(followingal.indexOf(text));
				for ( int i = 0; i < followingal.size(); i++ ) {
					followingListModel.addElement(followingal.get(i));
				}
			}
			if ( text.length() != 0 ) {
				unfollowal.add(text);
				Collections.sort(unfollowal);
				unfollowListModel.clear();
				for ( int i = 0; i < unfollowal.size(); i++ ) {
					unfollowListModel.addElement(unfollowal.get(i));
				}
			}
			doLongTask();
			makeTaskListModel(text);
		}
    }

	private class unfollwListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			if (unfollowList.getSelectedIndices().length != 1){
				return;
			}
		}
    }

	private class UnfollowActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (unfollowList.getSelectedIndices().length != 1) {
				return;
			}

			String text = (String)unfollowList.getSelectedValue();
			if ( unfollowal.contains(text) ) {
				String s = unfollowal.get(unfollowal.indexOf(text));
				unfollowal.remove(unfollowal.indexOf(text));
				if ( s.endsWith("(Allow waiting)") ) {
					return;
				} else {
					Approval.sendRequest(text);
					s = s + "(Allow waiting)";
					unfollowListModel.clear();
					unfollowal.add(s);
					Collections.sort(unfollowal);
					for ( int i = 0; i < unfollowal.size(); i++ ) {
						unfollowListModel.addElement(unfollowal.get(i));
					}
				}
			}
		}
    }

	private class approvalListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			if (approvalList.getSelectedIndices().length != 1){
				return;
			}
		}
    }

	private class ApprovalActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (approvalList.getSelectedIndices().length != 1) {
				return;
			}
			String text = (String)approvalList.getSelectedValue();
			if ( Approval.approval(text) ) {
				approvalListModel.remove(approvalListModel.indexOf(text));
				System.out.println("Approval is success.");

			} else {
				System.out.println("Approval is false.");
			}
			doLongTask();
		}
    }

	private class RenewActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			doLongTask();
			makeTaskListModel();
		}
    }

	/*
	private class LogoutActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            new LogoutDialog();
		}
	}
	*/

	private void makeTaskListModel() {
		setButtonsEnabled(false);
		taskListModel.clear();
		for ( int i = 0; i < followingal.size(); i++ ) {
			String s = followingal.get(i);
			ArrayList<String> al = taskhm.get(s);
			if ( taskhm.containsKey(s) ) {
				al = taskhm.get(s);
			} else {
				CreateToDoList ctdl = new CreateToDoList(s);
				al = ctdl.getArrayList();
				taskhm.put(s, al);
			}
			for ( int j = 0; j < al.size(); j++ ) {
				String t = s + ": " + al.get(j);
				taskListModel.addElement(t);
			}
		}
		setButtonsEnabled(true);
		doLongTask();
	}

	private void makeTaskListModel(String text) {
		if ( taskhm.containsKey(text) ) {
			makeTaskListModel();
		}
	}

	public void stateChanged(ChangeEvent e) {
		int index = tabbedpane.getSelectedIndex();
		indexLabel.setText("Selected Index ： " + index);
	}

	private void setDetail() {
		tabbedpane.setToolTipTextAt(0, "User List");
		tabbedpane.setToolTipTextAt(1, "Following");
		tabbedpane.setToolTipTextAt(2, "Unfollow");
		tabbedpane.setToolTipTextAt(3, "ToDo List");
		tabbedpane.setToolTipTextAt(4, "Approval List");

		tabbedpane.setForegroundAt(0, Color.GREEN);
		tabbedpane.setForegroundAt(1, Color.BLUE);
		tabbedpane.setForegroundAt(2, Color.RED);
		tabbedpane.setForegroundAt(3, Color.YELLOW);
		tabbedpane.setForegroundAt(4, Color.CYAN);
	}

	private void setButtonsEnabled(boolean enabled) {
		followingButton.setEnabled(enabled);
		unfollowButton.setEnabled(enabled);
		renewButton.setEnabled(enabled);
		approvalButton.setEnabled(enabled);
	}

	private void doLongTask() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			ErrorDialog.stackTraceDialog(e);
		} catch (Exception e) {
			ErrorDialog.stackTraceDialog(e);
		}
	}

	public static ArrayList<String> getAl() {
		return UserList.followingal;
	}
}