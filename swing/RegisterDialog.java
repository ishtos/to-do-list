package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import model.Register;
import model.RegisterLogic;

public class RegisterDialog extends JDialog {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private boolean succeeded;

    public RegisterDialog(Frame parent) {
        super(parent, "Register", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username(10): ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Password(15): ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(15);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String name = getName();
            	String pass = getPass();
            	Register register = new Register(name, pass);
            	RegisterLogic rl = new RegisterLogic();

                if (rl.excute(register)) {
                    JOptionPane.showMessageDialog(RegisterDialog.this,
                            "Hi " + getName() + "! You have successfully registered.",
                            "Register",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else {
                	String msg = "Invalid value.\n";
                	msg = errorMsg(msg, name, pass);
                    JOptionPane.showMessageDialog(RegisterDialog.this,
                            msg,
                            "Register",
                            JOptionPane.ERROR_MESSAGE);
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;

                }
            }
        });


        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel bp = new JPanel();
        bp.add(btnRegister);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String errorMsg(String msg, String name, String pass) {
    	if ( name.length() == 0 ) {
    		msg += "Username is blank.\n";
    	} else if ( name.length() > 10 ) {
    		msg += name + " is too long\n";
    	}

    	if ( pass.length() == 0 ) {
    		msg += "Password is blank.\n";
    	} else if ( pass.length() > 15 ) {
    		msg += pass + " is too long\n";
    	}

    	return msg;
    }

    public String getPass() {
        return new String(pfPassword.getPassword());
    }

    public String getName() {
        return tfUsername.getText().trim();
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
