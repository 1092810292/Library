import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class NormalGUI {
    public JPanel bottomPanel;
    private JTabbedPane tabbedPane;
    private JPanel personPanel;
    private JPanel queryPanel;
    private JTextField inTextField;
    private JTextArea outTextArea;
    private JButton findButton;
    private JRadioButton bookNameRadioButton;
    private JRadioButton ISBNRadioButton;
    private JRadioButton writerRadioButton;
    private JRadioButton publishingRadioButton;
    private JPanel findPanel;
    private JPanel ioPanel;
    private JScrollPane outScrollPane;
    private JPanel bookInformationButtonPanel;
    private JButton changeButton;
    private JTextField phoneInText;
    private JLabel nameLabel;
    private JLabel personIDLabel;
    private JLabel sexLabel;
    private JLabel phoneLabel;
    private JLabel userNameLabel;
    private JLabel nameInlabel;
    private JLabel personIDInlabel;
    private JLabel sexInlabel;
    private JLabel userNameInlabel;
    int choice = 0;
    private boolean result=false;
    String string = "";

    public NormalGUI() throws SQLException {
        string = new PersonQuery("name", LibraryGUI.userName).personQuery();
        nameInlabel.setText(string);
        string = new PersonQuery("personID", LibraryGUI.userName).personQuery();
        personIDInlabel.setText(string);
//        string=new PersonQuery("age",LibraryGUI.userName).personQuery();
//        ageInlabel.setText(string);
        string = new PersonQuery("sex", LibraryGUI.userName).personQuery();
        sexInlabel.setText(string);
        string = new PersonQuery("userName", LibraryGUI.userName).personQuery();
        userNameInlabel.setText(string);
        string = new PersonQuery("phone", LibraryGUI.userName).personQuery();
        phoneInText.setText(string);
        findButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<String> list;
                String list_str = "";
                String bookInformation = inTextField.getText();
                String queryKind = "";
                outTextArea.setText("");
                if (bookNameRadioButton.isSelected()) {
                    choice = 1;
                } else if (ISBNRadioButton.isSelected()) {
                    choice = 2;
                } else if (writerRadioButton.isSelected()) {
                    choice = 3;
                } else if (publishingRadioButton.isSelected()) {
                    choice = 4;
                }
                switch (choice) {
                    case 1:
                        queryKind = "bookName";
                        list = new Query(queryKind, bookInformation).query();
                        for (Object object : list) {
                            list_str = StringUtils.join(list, "");
                        }
                        outTextArea.append(list_str);
                        break;
                    case 2:
                        queryKind = "ISBN";
                        list = new Query(queryKind, bookInformation).query();
                        for (Object object : list) {
                            list_str = StringUtils.join(list, "");
                        }
                        outTextArea.append(list_str);
                        break;
                    case 3:
                        queryKind = "writer";
                        list = new Query(queryKind, bookInformation).query();
                        for (Object object : list) {
                            list_str = StringUtils.join(list, "");
                        }
                        outTextArea.append(list_str);
                        break;
                    case 4:
                        queryKind = "publishing";
                        list = new Query(queryKind, bookInformation).query();
                        for (Object object : list) {
                            list_str = StringUtils.join(list, "");
                        }
                        outTextArea.append(list_str);
                        break;
                }
            }
        });
        outTextArea.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                outTextArea.dispatchEvent(e);
            }
        });

        changeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                phoneInText.setEditable(true);
                changeButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        string=phoneInText.getText();
                        result=new Update(string).update();
                        if(result){
                            Succeed.succeed();
                            phoneInText.setEditable(false);
                            string = new PersonQuery("phone", LibraryGUI.userName).personQuery();
                            phoneInText.setText(string);
                        }else{
                            Error.error();
                            phoneInText.setEditable(false);
                            string = new PersonQuery("phone", LibraryGUI.userName).personQuery();
                            phoneInText.setText(string);
                        }
                    }
                });
            }
        });
        phoneInText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String str = phoneInText.getText();
                if (null != str && phoneInText.getText().equals(string)) {
                    phoneInText.setText("");
                }
            }
        });
        phoneInText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String str = phoneInText.getText();
                if (null == str || phoneInText.getText().equals("")) {
                    phoneInText.setText("请输入新手机号");
                }
            }
        });
    }
}
