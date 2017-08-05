package swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import model.SaveArchiveList;
import model.SaveFollowingList;
import model.SaveToDoList;

public class LogoutDialogAction extends AbstractAction {
	 private JFrame frame;

	 public LogoutDialogAction(JFrame frame) {
		 super();
		 this.frame = frame;
	 }

	 public void actionPerformed(ActionEvent event) {
		 String command = event.getActionCommand();
		 System.out.println(command);
		 if ("Logout".equals(command)) {
			 new SaveArchiveList(ToDoList.getArchiveAl());
			 new SaveToDoList(ToDoList.getAl());
			 new SaveFollowingList(UserList.getAl());
			 doLongTask();
			 ToDoListDialog.mainFrame.dispose();
			 UserListDialog.mainFrame.dispose();
			 HelpDialog.mainFrame.dispose();
			 frame.dispose();
			 Main.frame.setVisible(true);
		 } else {
			 frame.dispose();
		 }
	}

	 private void doLongTask() {
		 try {
			 Thread.sleep(2000);
		 } catch (InterruptedException e) {
			 ErrorDialog.stackTraceDialog(e);
		 } catch (Exception e) {
			 ErrorDialog.stackTraceDialog(e);
		 }
	 }
}
