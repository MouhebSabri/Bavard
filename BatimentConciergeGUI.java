import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class BatimentConciergeGUI
extends JFrame
{
    private transient Concierge concierge;

    private JTextArea messageTextArea;

    private JButton refreshButton;

    public BatimentConciergeGUI(Concierge concierge)
    {
        super("Batiment Concierge");
        this.concierge = concierge;

        messageTextArea = new JTextArea(10, 30);
        messageTextArea.setEditable(false);

        refreshButton = new JButton("Rafraîchir");
        refreshButton.addActionListener(e -> refreshMessages());
        JPanel panel = new JPanel();
        panel.add(refreshButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(messageTextArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocation(100, 80);
        setVisible(true);

        refreshMessages();
    }

    public void refreshMessages()
    {
        List<PapotageEvent> messages = concierge.getReceivedMessages();
        messageTextArea.setText("");

        for (PapotageEvent message : messages)
        {
            String formattedMessage = String.format("Envoyé par: %s\nSujet: %s\n\n%s\n\n", message.getBavard().getName(), message.getSujet(), message.getCorps());
            messageTextArea.append(formattedMessage);
        }
    }

}
