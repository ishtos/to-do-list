package swing;

import java.awt.Button;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class ExitDialog extends JDialog {
    private Button yesButton, noButton, logoutButton;

    public ExitDialog(JFrame host) {
        super(host, "window dispose", true );

        setBounds(128, 256, 300, 60);

        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.LINE_AXIS));

        yesButton = new Button("yes");
        cp.add(yesButton);

        noButton = new Button("no");
        cp.add(noButton);

        logoutButton = new Button("logout");
        cp.add(logoutButton);


        ExitDialogAction action = new ExitDialogAction(this);
        yesButton.addActionListener(action);
        yesButton.setActionCommand("EXIT");
        noButton.addActionListener(action);
        noButton.setActionCommand("NOT EXIT");

        setVisible(true);

        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		setVisible(false);
        	}
        });
    }
}
