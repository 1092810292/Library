import javax.swing.*;

/**
 * 错误弹窗，使用方式为Error.error();
 * @author 王陆斌
 * @since JDK1.8
 */
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
