package swing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Login;

public class Help extends JPanel implements ChangeListener {
	private JTabbedPane tabbedpane;
	private JLabel indexLabel;

	private JList<String> howToDo;
	private DefaultListModel<String> howToDoModel;

	private JList<String> howUser;
	private DefaultListModel<String> howUserModel;

	private JList<String> noticeList;
	private DefaultListModel<String> noticeListModel;

	public Help() {
		super(new BorderLayout());

		howToDoModel = new DefaultListModel<String>();
		howToDo = new JList<String>(howToDoModel);
		JScrollPane howToDoScrollPane = new JScrollPane(howToDo);

		howUserModel = new DefaultListModel<String>();
		howUser = new JList<String>(howUserModel);
		JScrollPane howUserScrollPane = new JScrollPane(howUser);

		noticeListModel = new DefaultListModel<String>();
		noticeList = new JList<String>(noticeListModel);
		JScrollPane noticeListScrollPane = new JScrollPane(noticeList);

	    indexLabel = new JLabel();

	    JPanel displayPanel = new JPanel();
	    displayPanel.add(indexLabel);

	    tabbedpane = new JTabbedPane();
	    tabbedpane.addChangeListener(this);

	    JPanel howToDo = new JPanel();
	    howToDo.setLayout(new BorderLayout());
	    howToDo.add(howToDoScrollPane, BorderLayout.CENTER);

	    JPanel howUser = new JPanel();
	    howUser.setLayout(new BorderLayout());
	    howUser.add(howUserScrollPane, BorderLayout.CENTER);

	    JPanel notice = new JPanel();
	    notice.setLayout(new BorderLayout());
	    notice.add(noticeListScrollPane, BorderLayout.CENTER);

	    tabbedpane.addTab("How to use:ToDo List", howToDo);
	    tabbedpane.addTab("How to use:User List", howUser);
	    tabbedpane.addTab("Notice", notice);

	    setDetail();

	    add(tabbedpane, BorderLayout.CENTER);
	    add(displayPanel, BorderLayout.PAGE_END);

	    init();
	}

	public void init() {
		initHowTo();
		initHowUser();
		initNotice();
	}

	public void initHowTo() {
		howToDoModel.addElement("::Add::");
		howToDoModel.addElement("Input text field.");
		howToDoModel.addElement("Push add.");
		howToDoModel.addElement("　");
		howToDoModel.addElement("::Modify::");
		howToDoModel.addElement("Select list.");
		howToDoModel.addElement("Modify text field.");
		howToDoModel.addElement("Push modify");
		howToDoModel.addElement("　");
		howToDoModel.addElement("::Remove::");
		howToDoModel.addElement("Select list.");
		howToDoModel.addElement("Push remove.");
		howToDoModel.addElement(" ");
		howToDoModel.addElement("::Save::");
		howToDoModel.addElement("Push close box.");
	}

	public void initHowUser() {
		howUserModel.addElement("**User list**");
		howUserModel.addElement("Show all users");
		if ( Login.getStaticName().equals("java") ) {
			howUserModel.addElement("::Remove user::");
			howUserModel.addElement("Select list.");
			howUserModel.addElement("Push remove.");
			howUserModel.addElement("");
		}
		howUserModel.addElement("　");
		howUserModel.addElement("**Following**");
		howUserModel.addElement("Show following users");
		howUserModel.addElement("::unfollow::");
		howUserModel.addElement("Select list.");
		howUserModel.addElement("Push unfollow.");
		howUserModel.addElement(" ");
		howUserModel.addElement("**Unfollow**");
		howUserModel.addElement("Show unfollow users.");
		howUserModel.addElement("::Following::");
		howUserModel.addElement("Select list.");
		howUserModel.addElement("Push following.");
		howUserModel.addElement(" ");
		howUserModel.addElement("**ToDoList(follwoing)");
		howUserModel.addElement("Show ToDoList.");
		howUserModel.addElement(" ");
		howUserModel.addElement("::Renew::");
		howUserModel.addElement("Push renew.");
		howUserModel.addElement(" ");
		howUserModel.addElement("::Save::");
		howUserModel.addElement("Push close box");
		howUserModel.addElement(" ");
		howUserModel.addElement("**Approval**");
		howUserModel.addElement("Show request follow.");
		howUserModel.addElement("::Approval::");
		howUserModel.addElement("Select list.");
		howUserModel.addElement("Push approval.");
	}

	public void initNotice() {
		noticeListModel.addElement("**Notice**");
		noticeListModel.addElement("Save if you push close box.");
		noticeListModel.addElement("Otherwise modifing will be lost.");
		if ( Login.getStaticName().equals("java") ) {
			noticeListModel.addElement(" ");
			noticeListModel.addElement("Show debug in console.");
		}
	}

	public void stateChanged(ChangeEvent e) {
		int index = tabbedpane.getSelectedIndex();
		indexLabel.setText("Selected Index ： " + index);
	}

	private void setDetail() {
		tabbedpane.setToolTipTextAt(0, "How to use: ToDo list");
		tabbedpane.setToolTipTextAt(1, "How to use: User list");
		tabbedpane.setToolTipTextAt(2, "Notice");

		tabbedpane.setForegroundAt(0, Color.BLUE);
		tabbedpane.setForegroundAt(1, Color.GREEN);
		tabbedpane.setForegroundAt(2, Color.RED);
	}
}