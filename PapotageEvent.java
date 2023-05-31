
public class PapotageEvent
{
    private String sujet;

    private String corps;

    private Bavard bavard;

    public PapotageEvent(Bavard bavard, String sujet, String corps)
    {
        this.bavard = bavard;
        this.sujet = sujet;
        this.corps = corps;
    }

    public String getSujet()
    {
        return sujet;
    }

    public void setSujet(String nom)
    {
        this.sujet = nom;
    }

    public String getCorps()
    {
        return corps;
    }

    public void setCorps(String corp)
    {
        this.corps = corp;
    }

    public Bavard getBavard()
    {
        return bavard;
    }

    public void setBavard(Bavard bavard)
    {
        this.bavard = bavard;
    }
}
