import java.util.ArrayList;
import java.util.List;

public class Concierge
implements PapotageListener
{
    private List<Bavard> bavards;

    private List<PapotageEvent> receivedMessages;

    public Concierge()
    {
        this.bavards = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
    }

    public List<PapotageEvent> getReceivedMessages()
    {
        return receivedMessages;
    }

    public void addBavard(Bavard bavard)
    {
        bavards.add(bavard);
        bavard.addPapotageListener(this);
    }

    public void removeBavard(Bavard bavard)
    {
        bavards.remove(bavard);
        bavard.removePapotageListener(this);
    }

    @Override
    public void onPapotageEvent(PapotageEvent event)
    {
        receivedMessages.add(event);

        for (Bavard bavard : bavards)
        {
            if (!bavard.equals(event.getBavard()))
            {
                bavard.recevoirMessage(event.getSujet(), event.getCorps());
            }
        }
    }
}
