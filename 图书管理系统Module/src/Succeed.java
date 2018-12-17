import javax.swing.*;

public class Succeed {
    public static void succeed() {
        JFrame frame = new JFrame("SucceedGUI");
        frame.setContentPane(new SucceedGUI().succeedPanel);
        frame.setDefaultCloseOperation(2);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(900,500);
    }
}
