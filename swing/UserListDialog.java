package swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class UserListDialog extends JFrame {
	static JFrame mainFrame = new JFrame("User Lsit");
	public UserListDialog() {
		System.out.println("createAndShowUserList :"
				+ SwingUtilities.isEventDispatchThread());
		Container contentPane = mainFrame.getContentPane();
        JComponent newContentPane = new UserList();
        contentPane.add(newContentPane, BorderLayout.CENTER);

        mainFrame.setBounds(700, 300, 1000, 600);
        mainFrame.pack();
        mainFrame.setVisible(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        mainFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
           /* public void windowClosing(WindowEvent ev) {
            	//ArrayList<String> todo_al = ToDoList.getAl();
            	//ArrayList<String> user_al = UserList.getAl();
            	new SaveArchiveList(ToDoList.getArchiveAl());
            	new SaveToDoList(ToDoList.getAl());
            	new SaveFollowingList(UserList.getAl());
                JFrame frame = (JFrame)ev.getSource();
                new ExitDialog(frame);
            }*/
        });
	}
}
