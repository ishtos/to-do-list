package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ErrorDialog {
    public static void stackTraceDialog(Exception e) {
    	String message = new String();
        StackTraceElement[] stackFrames = e.getStackTrace();
        for (int i = 0 ; i < stackFrames.length ; i++) {
            String stackFrame = stackFrames[i].toString();
            message = message + stackFrame + "\n";
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Error");

        JTextArea area = new JTextArea();
        area.setText(message);
        area.setCaretPosition(0);
        area.setEditable(false);

        JScrollPane scrollpane = new JScrollPane(area);
        scrollpane.setPreferredSize(new Dimension(600, 200));

        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollpane, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, panel, "Error", JOptionPane.ERROR_MESSAGE);
    }
}