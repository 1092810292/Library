import javax.swing.*;
//错误弹窗，使用方式为Error.error();
public class Error {
    public static void error(String errorHint){
        JFrame frame = new JFrame("ErrorGUI");
        frame.setContentPane(new ErrorGUI(errorHint).errorPanel);
        frame.setDefaultCloseOperation(2);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(900,500);
    }
}
