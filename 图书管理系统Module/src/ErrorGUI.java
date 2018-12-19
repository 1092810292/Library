import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ErrorGUI {
    public JPanel errorPanel;
    private JLabel errorLabel;

    public ErrorGUI(String errorHint) {
        errorLabel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                errorLabel.setText(errorHint);
            }
        });
    }
}
