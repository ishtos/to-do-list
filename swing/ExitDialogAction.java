package swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

public class ExitDialogAction extends AbstractAction {
    private JDialog dialog;

    public ExitDialogAction(JDialog dialog) {
        super();
        this.dialog = dialog;
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        System.out.println(command);
        if ("EXIT".equals(command)) {
            System.exit( 0 );
        } else {
            dialog.dispose();
        }
    }
}