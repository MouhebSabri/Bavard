import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class BatimentGUI
extends JFrame
{
    private transient Batiment batiment;

    private DefaultListModel<String> bavardListModel;

    private JList<String> bavardList;

    private JButton createBavardButton;

    private JButton connectButton;

    public BatimentGUI(Batiment batiment)
    {
        super("Interface Bâtiment");
        this.batiment = batiment;

        bavardListModel = new DefaultListModel<>();
        bavardList = new JList<>(bavardListModel);
        createBavardButton = new JButton("Créer Bavard");
        connectButton = new JButton("Connecter");

        createBavardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = JOptionPane.showInputDialog("Nom du Bavard :");
                if (name != null && !name.isEmpty())
                {
                    Bavard existingBavard = batiment.getBavardByName(name);
                    if (existingBavard != null)
                    {
                        JOptionPane.showMessageDialog(null, "Un Bavard avec ce nom existe déjà.");
                        setLocationRelativeTo(null);
                    }
                    else
                    {
                        batiment.createBavard(name);
                        JOptionPane.showMessageDialog(null, "Bavard " + name + " créé avec succès !");
                        setLocationRelativeTo(null);
                        updateBavardList();
                    }
                }
            }
        });

        connectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedBavard = bavardList.getSelectedValue();
                if (selectedBavard != null)
                {
                    JOptionPane.showMessageDialog(null, "Bavard " + selectedBavard + " connecté !");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createBavardButton);
        buttonPanel.add(connectButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(bavardList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public void updateBavardList()
    {
        bavardListModel.clear();
        for (Bavard bavard : batiment.getBavards())
        {
            bavardListModel.addElement(bavard.getName());
        }
    }

    public Batiment getBatiment()
    {
        return batiment;
    }

    public void setBatiment(Batiment batiment)
    {
        this.batiment = batiment;
    }

    public DefaultListModel<String> getBavardListModel()
    {
        return bavardListModel;
    }

    public void setBavardListModel(DefaultListModel<String> bavardListModel)
    {
        this.bavardListModel = bavardListModel;
    }

    public JButton getCreateBavardButton()
    {
        return createBavardButton;
    }

    public void setCreateBavardButton(JButton createBavardButton)
    {
        this.createBavardButton = createBavardButton;
    }

    public JButton getConnectButton()
    {
        return connectButton;
    }

    public void setConnectButton(JButton connectButton)
    {
        this.connectButton = connectButton;
    }

    public void setBavardList(JList<String> bavardList)
    {
        this.bavardList = bavardList;
    }

    public JList<String> getBavardList()
    {
        return bavardList;
    }
}
