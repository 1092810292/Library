import javax.swing.*;

public class Error {
    public static void error(){
        JFrame frame = new JFrame("ErrorGUI");
        frame.setContentPane(new ErrorGUI().errorPanel);
        frame.setDefaultCloseOperation(2);
        frame.pack();
        frame.setVisible(true);
    }
}
