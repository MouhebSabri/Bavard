import java.util.ArrayList;
import java.util.List;

public class Bavard
{
    private String name;
    private List<PapotageListener> listeners;
    private BavardGUI bavardGUI;

    public Bavard(String name)
    {
        this.name = name;
        listeners = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void addPapotageListener(PapotageListener listener)
    {
        listeners.add(listener);
    }

    public void removePapotageListener(PapotageListener listener)
    {
        listeners.remove(listener);
    }

    public void setBavardGUI(BavardGUI bavardGUI)
    {
        this.bavardGUI = bavardGUI;
    }

    public void envoyerMessage(String sujet, String corps)
    {
        PapotageEvent event = new PapotageEvent(this, sujet, corps);
        for (PapotageListener listener : listeners)
        {
            listener.onPapotageEvent(event);
        }
    }

    public void recevoirMessage(String sujet, String corps)
    {
        if (bavardGUI != null)
        {
            bavardGUI.addMessage(sujet, corps);
        }
    }

    public void connect()
    {
        System.out.println("Bavard " + name + " connecté !");
    }

    public void addEventToList(String abbreviatedMessage)
    {
        if (bavardGUI != null)
        {
            bavardGUI.addEventToList(abbreviatedMessage);
        }
    }
}
