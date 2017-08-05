package swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	static final JFrame frame = new JFrame("Login Menu");
	public static void main(String[] args) {
		//final JFrame frame = new JFrame("Login Menu");
        final JButton btnLogin = new JButton("Click to login");
        final JButton btnRegister = new JButton("Click to register");

        btnLogin.addActionListener(
        		new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);

                        if(loginDlg.isSucceeded()){
                            frame.setVisible(false);
                            System.out.println("main : "
                        			+ SwingUtilities.isEventDispatchThread());
                        	SwingUtilities.invokeLater(new Runnable() {
                        		public void run() {
                        			new UserListDialog();
                        			new ToDoListDialog();
                        			new HelpDialog();
                        		}
                        	});
                        }
                    }
                });

        btnRegister.addActionListener(
        		new ActionListener(){
        			public void actionPerformed(ActionEvent e) {
        				RegisterDialog registerDlg = new RegisterDialog(frame);
        				registerDlg.setVisible(true);
        				if(registerDlg.isSucceeded()) {
        					System.out.println("success");
        				}
        			}
        		});

        frame.setSize(200, 100);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);
        frame.getContentPane().add(btnRegister);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                JFrame frame = (JFrame)ev.getSource();
                new ExitDialog(frame);
            }
        });
    }
}