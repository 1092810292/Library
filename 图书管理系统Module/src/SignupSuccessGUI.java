import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupSuccessGUI {
    public JPanel signupSuccessPanel;
    private JLabel signupSuccessLabel;

    public SignupSuccessGUI() {
        signupSuccessLabel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                signupSuccessLabel.setHorizontalAlignment(SwingConstants.CENTER);
            }
        });
    }
}
