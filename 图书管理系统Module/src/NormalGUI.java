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
    private JButton affirmButton;
    int choice = 0;
    boolean verify=false;
    private boolean result=false;
    String string = "";
    int count=0;

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
//        outTextArea.addMouseWheelListener(new MouseWheelListener() {
//            @Override
//            public void mouseWheelMoved(MouseWheelEvent e) {
//                outTextArea.dispatchEvent(e);
//            }
//        });

        changeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                phoneInText.setEditable(true);//设置文本框可修改
                changeButton.setEnabled(false);//设置修改按钮不可按
                affirmButton.setEnabled(true);//设置确认按钮可按
                phoneInText.setFocusable(true);
            }
        });
        affirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (verify) {//判断输入是否正确
                    string = phoneInText.getText();//输入正确就接受JTextField
                    result = new Update(string).update();//连接数据库更新数据
                    if (result) {//如果有结果
                        Succeed.succeed();//弹出修改成功窗口
                        phoneInText.setEditable(false);//设置不可更改
                        changeButton.setEnabled(true);//设置修改按钮可按
                        affirmButton.setEnabled(false);//设置确认按钮不可按
                        phoneInText.setFocusable(true);
                        string = new PersonQuery("phone", LibraryGUI.userName).personQuery();//搜索数据库
                        phoneInText.setText(string);//将搜索结果并显示在文本框
                    } else {
                        Error.error("无法修改请重试！若多次修改无效请联系管理员寻求帮助！");
                        phoneInText.setEditable(false);
                        string = new PersonQuery("phone", LibraryGUI.userName).personQuery();
                        phoneInText.setText(string);
                    }
                }
            }
        });
        phoneInText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String str=phoneInText.getText();
                if(str.equals(string)){
                   phoneInText.setText("");
                }
            }
        });
        phoneInText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String phoneTextText = phoneInText.getText();
                if (phoneTextText==null||phoneTextText.equals("")||phoneTextText.length()!=11){
                    Error.error("限制11位请重新输入！");
                    phoneInText.setText("");
                }else verify=true;
            }
        });

    }
}
