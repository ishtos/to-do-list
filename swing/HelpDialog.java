package swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class HelpDialog extends JFrame {
	static JFrame mainFrame = new JFrame("Help List");
	public HelpDialog() {
		System.out.println("createAndShowTHelp :"
				+ SwingUtilities.isEventDispatchThread());
		Container contentPane = mainFrame.getContentPane();
        JComponent newContentPane = new Help();
        contentPane.add(newContentPane, BorderLayout.CENTER);

        mainFrame.setBounds(300, 0, 1000, 450);
        mainFrame.pack();
        mainFrame.setVisible(true);

        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      /*
       *
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        mainFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                JFrame frame = (JFrame)ev.getSource();
                new ExitDialog(frame);
            }
        });
        *
        */
	}
}
