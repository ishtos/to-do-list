package swing;

import java.awt.Button;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LogoutDialog {
	 private Button yesButton, noButton;

	    public LogoutDialog() {
	    	JFrame frame = new JFrame("Logout");
	    	frame.setVisible(true);
	        //super(host, "Logout", true );

	        frame.setBounds(128, 256, 300, 60);

	        JPanel cp = new JPanel();
	        cp.setLayout(new BoxLayout(cp, BoxLayout.LINE_AXIS));

	        yesButton = new Button("yes");
	        cp.add(yesButton);

	        noButton = new Button("no");
	        cp.add(noButton);
	        frame.add(cp);


	        LogoutDialogAction action = new LogoutDialogAction(frame);
	        yesButton.addActionListener(action);
	        yesButton.setActionCommand("Logout");
	        noButton.addActionListener(action);
	        noButton.setActionCommand("NOT Logout");
	    }
}
