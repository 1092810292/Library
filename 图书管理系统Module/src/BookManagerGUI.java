import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BookManagerGUI {
    public JPanel bottonPanel;
    private JTabbedPane tabbedPane;
    private JPanel searchPanel;
    private JPanel personPanel;
    private JRadioButton bookNameRadioButton;
    private JRadioButton ISBNRadioButton;
    private JRadioButton writerRadioButton;
    private JRadioButton publishingRadioButton;
    private JButton findButton;
    private JButton updateButton;
    private JButton addButton;
    private JTextField inTextField;
    private JTable outTable;
    private JPanel radioButtonPanel;
    private JPanel inPanel;
    private JScrollPane outScrollPane;
    private JPanel buttonPanel;
    private JButton button;
    private JList personList;
    private JPanel personButtonPanel;
    private JScrollPane personScrollPane;
    int choice=0;

    public BookManagerGUI() {
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
    }
}
