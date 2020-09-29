import java.util.*;

class Spieler{
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Objekt Instanzen
    private ArrayList<Integer> karten;      //Karten des Spielers

    private ArrayList<Integer> statistik;   //Erste Stelle Siege, zweite Stelle Niederlagen

    private final int startguthaben;        //Berechnung des Gewinnes/Verlustes erfolgt mittels des Startguthaben

    private int kartensumme;                //Die Summer der gesamten Karten
    private int einsatz;                    // Zwischenspeicher für den getätigten Einsatz
    private String Name;                    //Name des Spielers
    private int guthaben;                   //Gesamtguthaben des Spielers
    private boolean verdoppelung;           //Zwischentoken falls ein Spieler verdoppelt
    private String spielerfarbe;
    
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    
//Konstruktor
    public Spieler(int guthaben, String name, String farbe){  //Konstruktor der Klasse Spieler
        karten = new ArrayList<>();
        statistik = new ArrayList<>();
        statistik.add(0); statistik.add(0);
        Kartensumme();
        setGuthaben(guthaben);
        Name = farbe+name;
        verdoppelung=true;
        spielerfarbe = farbe;
        startguthaben = guthaben;
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Verändernde Methoden für Karten
        public void nKarte(){
            if(karten.size()<7){
                Random NeueKarte = new Random();
                int nk = NeueKarte.nextInt(13) + 1; if(nk==11||nk==12||nk==13) nk=10;
                    Kartensumme();

                        if(nk==1 && kartensumme<11) nk =11;

                        if(kartensumme+nk > 21 && karten.contains(11)) karten.set(karten.indexOf(11), 1);
                        
                karten.add(nk);
            }
        }
        public void Kartensumme(){
        
            kartensumme=0;
            for(int karte : karten){
                kartensumme = kartensumme + karte;
            }
        }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Verändernde Methoden für Statistik    
        public void addstatSieg(){
            statistik.set( 0 , statistik.get(0) + 1 );
        }
        public void addstatNiederlage(){
            statistik.set( 1 , statistik.get(1) + 1 );
        }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//seter Methoden    
        public void setEinsatz(int einsatz){
            if(einsatz > guthaben)  einsatz = guthaben;
            this.einsatz = this.einsatz + einsatz;
            setGuthaben(-einsatz);
        }
        public void setGuthaben(int guthaben){
            this.guthaben = this.guthaben + guthaben;
        }
        public void vstatus(){
            if(verdoppelung == true) verdoppelung=false;
            else if(verdoppelung == false)  verdoppelung = true;
        }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//geter Methoden    
        public int getKartensumme(){
            Kartensumme();
            return kartensumme;
        }
        public int getAnzahlKarten(){
            return karten.size();
        }
        public int getEinsatz(){
            return einsatz;
        }
        public String getName(){
            return Name;
        }
        public int getGuthaben(){
            return guthaben;
        }

        public boolean getVstatus(){
            return verdoppelung;
        }

        public String getStatistik(){
            String sout = spielerfarbe+Name+", Sie haben "+statistik.get(0)+" Siege; "+statistik.get(1)+" Niederlagen; und einen Gewinn/Verlust von "+(guthaben - startguthaben)+" Jetons";
            return sout;
        }
        public String getKarten(){
            String kout = "";
                for(int karte : karten) kout = kout+"\t["+karte+"]";
                kout = spielerfarbe+Name+", ihre Karten sind "+kout+"\nGesamt:\t" +getKartensumme();
            return kout;
        }
        public String getFarbe(){
            return spielerfarbe;
        }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Clear Methode
        public void clear(){
            karten.clear();
            einsatz=0;
            Kartensumme();
            verdoppelung = true;
        }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}