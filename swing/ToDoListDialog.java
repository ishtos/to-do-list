package swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ToDoListDialog extends JFrame {
	static JFrame mainFrame = new JFrame("ToDo List");
	public ToDoListDialog() {
		System.out.println("createAndShowTodoList :"
				+ SwingUtilities.isEventDispatchThread());
		Container contentPane = mainFrame.getContentPane();
        JComponent newContentPane = new ToDoList();
        contentPane.add(newContentPane, BorderLayout.CENTER);

        mainFrame.setBounds(200, 300, 400, 300);
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
