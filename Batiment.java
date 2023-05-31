import java.util.ArrayList;
import java.util.List;

public class Batiment
{
    private List<Bavard> bavards;

    private Concierge concierge;

    public Batiment()
    {
        this.bavards = new ArrayList<>();
        this.concierge = new Concierge();
    }

    public Concierge getConcierge()
    {
        return concierge;
    }

    public void createConcierge()
    {
        concierge = new Concierge();
    }

    public Bavard createBavard(String name)
    {
        Bavard bavard = new Bavard(name);
        addBavard(bavard);
        return bavard;
    }

    public void addBavard(Bavard bavard)
    {
        bavards.add(bavard);
        concierge.addBavard(bavard);
    }

    public void removeBavard(Bavard bavard)
    {
        bavards.remove(bavard);
        concierge.removeBavard(bavard);
    }

    public void connectBavardToConcierge(Bavard bavard)
    {
        concierge.addBavard(bavard);
    }

    public void disconnectBavardFromConcierge(Bavard bavard)
    {
        concierge.removeBavard(bavard);
    }

    public Bavard getBavardByName(String name)
    {
        for (Bavard bavard : bavards)
        {
            if (bavard.getName().equals(name))
            {
                return bavard;
            }
        }
        return null;
    }

    public List<Bavard> getBavards()
    {
        return bavards;
    }
}
