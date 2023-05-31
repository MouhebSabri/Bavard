import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class MainApp
{
    private Batiment batiment;

    private BatimentGUI batimentGUI;

    private BatimentConciergeGUI batimentConciergeGUI;

    private BavardGUI bavardGUI;

    public MainApp()
    {
        batiment = new Batiment();
        batimentGUI = new BatimentGUI(batiment);

        batimentGUI.getCreateBavardButton();

        batimentGUI.getBavardList().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (SwingUtilities.isRightMouseButton(e))
                {
                    int index = batimentGUI.getBavardList().locationToIndex(e.getPoint());
                    batimentGUI.getBavardList().setSelectedIndex(index);
                    Bavard selectedBavard = batiment.getBavardByName(batimentGUI.getBavardList().getSelectedValue());
                    showUserOptions(selectedBavard, e.getX(), e.getY());
                }
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2)
                {
                    setBavardGUI(new BavardGUI(batiment.getBavardByName(batimentGUI.getBavardList().getSelectedValue())));
                }
            }
        });

        batimentConciergeGUI = new BatimentConciergeGUI(batiment.getConcierge());

    }

    private void showUserOptions(Bavard selectedBavard, int x, int y)
    {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem sendMessageItem = new JMenuItem("Envoyer Message");
        sendMessageItem.addActionListener(e -> {
            showSendMessageDialog(selectedBavard);
        });
        popupMenu.add(sendMessageItem);

        JMenuItem deleteUserItem = new JMenuItem("Supprimer Bavard");
        deleteUserItem.addActionListener(e -> {
            deleteBavard(selectedBavard);
            batimentGUI.updateBavardList();
        });
        popupMenu.add(deleteUserItem);
        popupMenu.show(batimentGUI.getBavardList(), x, y);
    }

    private void showSendMessageDialog(Bavard selectedBavard)
    {
        setBavardGUI(new BavardGUI(selectedBavard));
    }

    private void deleteBavard(Bavard selectedBavard)
    {
        batiment.removeBavard(selectedBavard);
        JOptionPane.showMessageDialog(null, "Bavard " + selectedBavard.getName() + " supprimé !");
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MainApp();
            }
        });
    }

    public BatimentConciergeGUI getBatimentConciergeGUI()
    {
        return batimentConciergeGUI;
    }

    public void setBatimentConciergeGUI(BatimentConciergeGUI batimentConciergeGUI)
    {
        this.batimentConciergeGUI = batimentConciergeGUI;
    }

    public BavardGUI getBavardGUI()
    {
        return bavardGUI;
    }

    public void setBavardGUI(BavardGUI bavardGUI)
    {
        this.bavardGUI = bavardGUI;
    }
}
