import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class BavardGUI
extends JFrame
{
    private transient Bavard bavard;

    private DefaultListModel<String> listModel;

    private JList<String> messageList;

    private JTextField sujetField;

    private JTextArea corpsTextArea;

    private JButton envoyerButton;

    public BavardGUI(Bavard bavard)
    {
        super("Bavard: " + bavard.getName());
        setBavard(bavard);

        sujetField = new JTextField(20);
        corpsTextArea = new JTextArea(5, 20);
        envoyerButton = new JButton("Envoyer");

        sujetField.setFont(UIManager.getFont("TextField.font").deriveFont(Font.BOLD));

        envoyerButton.addActionListener(e -> {
            String sujet = sujetField.getText();
            String corps = corpsTextArea.getText();
            if (!sujet.isEmpty() && !corps.isEmpty())
            {
                bavard.envoyerMessage(sujet, corps);
                sujetField.setText("");
                corpsTextArea.setText("");
            }
            dispose();
        });

        listModel = new DefaultListModel<>();
        messageList = new JList<>(listModel);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.add(new JLabel("Sujet:"));
        inputPanel.add(sujetField);
        inputPanel.add(new JLabel("Corps:"));
        inputPanel.add(new JScrollPane(corpsTextArea));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(messageList), BorderLayout.CENTER);
        mainPanel.add(envoyerButton, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(mainPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addMessage(String sujet, String corps)
    {
        listModel.addElement(sujet + " - " + corps);
    }

    public Bavard getBavard()
    {
        return bavard;
    }

    public void setBavard(Bavard bavard)
    {
        this.bavard = bavard;
    }

    public DefaultListModel<String> getListModel()
    {
        return listModel;
    }

    public void setListModel(DefaultListModel<String> listModel)
    {
        this.listModel = listModel;
    }

    public JList<String> getMessageList()
    {
        return messageList;
    }

    public void setMessageList(JList<String> messageList)
    {
        this.messageList = messageList;
    }

    public JTextField getSujetField()
    {
        return sujetField;
    }

    public void setSujetField(JTextField sujetField)
    {
        this.sujetField = sujetField;
    }

    public JTextArea getCorpsTextArea()
    {
        return corpsTextArea;
    }

    public void setCorpsTextArea(JTextArea corpsTextArea)
    {
        this.corpsTextArea = corpsTextArea;
    }

    public JButton getEnvoyerButton()
    {
        return envoyerButton;
    }

    public void setEnvoyerButton(JButton envoyerButton)
    {
        this.envoyerButton = envoyerButton;
    }

    public void addEventToList(String abbreviatedMessage)
    {
        listModel.addElement(abbreviatedMessage);
    }
}
