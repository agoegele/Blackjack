import java.util.*;

class Blackjack{

    //Variable in der Speiler Entscheidungen zwischengespeichert werden
    public static String EntscheidungSpieler;
    //Zählung der einzelnen Runden
    static int runden=1;
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public static void main(String[] args) {
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //boolsche Variablen mit denen überprüft wird ob gespielt werden kann
        //spielen gibt die generelle Spielwiederholung an
        boolean spielen = true;
        //spielerzug besagt ob ein spieler noch aktionen machen darf oder nicht
        boolean spielerzug = true;
        //die anzahl Spieler wird zu beginn verwendet um zu ermitteln wie viele spieler im Spiel sind
        int anzahlSpieler=0;
        //jetons speichert wie einzahlung zu beginn und wenn ein spieler einen einsatz macht
        int jetons=0;
        //Aktive Spieler wird verwendet um zu üerprüfen ob eine Runde gestartet werden kann oder nicht
        int aktiveSpieler;
        //Textausgabe für confirm Funktion
        String enter = "Drücken sie Enter zum fortfahren...";
//---------------------------------------------------------------------------------------        
        ArrayList<String> auswahlfarben = new ArrayList<>();
        auswahlfarben.add("Rot");
        auswahlfarben.add("Grün");
        auswahlfarben.add("Blau");
        auswahlfarben.add("Lila");
//In diesen Arraylists werden die Spielerfarben abgespeichert. Wenn eine Farbe ausgewählt wurde, wird sie gelöscht damit sie nicht doppelt vorkommt
        ArrayList<String> farbcodes = new ArrayList<>();
        farbcodes.add(farbe.RED);
        farbcodes.add(farbe.GREEN);
        farbcodes.add(farbe.BLUE);
        farbcodes.add(farbe.PURPLE);
//---------------------------------------------------------------------------------------
//Initialisieren der SpielerArraylist und des Dealers
        ArrayList<Spieler> spieler = new ArrayList<>();
        Dealer Dealer = new Dealer();        
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------        
        //Begrüßungsnachricht
        Ausgaben.Leerzeile();
        Ausgaben.Linie();
        System.out.println(farbe.RED+"Willkommen beim ObjectJack Cowboy!");
        Ausgaben.Linie();
        Ausgaben.Leerzeile();
        //Abfrage wie viele Spieler spielen möchten
        do{
            //Inputs werden mit einer eigenen Klasse von @ThomasFilzer & @DominicHofer abgehandelt
            anzahlSpieler = (int) Input.get(Input.INT,"Wie viele Spieler wollen spielen?",false);
            if(anzahlSpieler<1 || anzahlSpieler>4) System.out.println("Es können nur 1 bis 4 Spieler spielen.");
            //Es können nur 1 bis 4 Spieler spielen (Theoretisch wären mehr möglich)
        }while(anzahlSpieler<1 || anzahlSpieler>4);
        //Bei allen Ausgaben Funktionen handelt es sich um Konsolenausgaben für Information oder formatierung
        Ausgaben.Linie();
        Ausgaben.Leerzeile();
        
        //Erstellung der einzelnen Spieler
        for(int s = 1; s <= anzahlSpieler; s++){
            //Eingabe des Spieler Namens
            String Name = (String) Input.get(Input.STRING, "Spieler " +s+", wie heißen sie?");
            Ausgaben.Leerzeile();
//-----------------------------------------------------------------------------------------------------
            String f="";
            int counter;
            int fa;
//ERstellung der Textausgabe mit den verfügbaren Farben
            do{
                //Counter ist eine zwischenvariable damit die Aufzählung der Farben mit 1 beginnt und nicht mit 0
                counter = 1;
                f="";
                //Auslesung der verfügbaren Farben und Speicherung in String f für die Ausgabe
                for(String af : auswahlfarben){
                    f = f + "\t"+counter+". "+af;
                    counter++;
                }
                fa = (Integer) Input.get(Input.INT,Name+", welche Farbe möchten sie haben?\n"+f,false);
                Ausgaben.Leerzeile();

                if(fa<1||fa>=counter){
                    System.out.println("Ungültige Eingabe");
                    Ausgaben.Leerzeile();
                } 
            }while(fa<1||fa>=counter);
            //Zuteilung des Farbcodes an f für die Übergabe an den neuen Spieler
            f = farbcodes.get(fa-1);
           
//----------------------------------------------------------------------------------------------------------------------
//Einlesung der Jetons
            do{
                    jetons= (int) Input.get(Input.INT,Name+", mit wie vielen Jetons wollen sie sich einkaufen? Min 10, Max 10.000",false);
                    Ausgaben.Leerzeile();
                    if(jetons<10||jetons>10000) System.out.println("Ungültige EIngabe");
            }while(jetons<10||jetons>10000);
            //Hinzufügen des neuen Spielers mit den Parametern Jetons, Name und den Farbcode 
            spieler.add(new Spieler(jetons, Name, f));
            Ausgaben.Linie();
            //Löschung der ausgewählten Farbe damit sie nicht doppelt verwendet werden kann
            farbcodes.remove(fa-1);
            auswahlfarben.remove(fa-1);
        }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------        
//Hauptschleife damit das Spiel ohne neustart wiederholt werden kann        
    do{
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Vorrunde
        aktiveSpieler=0;
        //Für jeden Spieler in der ArrayList spieler wird die Schleife durchgeführt
        for(Spieler s :  spieler){

            System.out.println(s.getName()+", was möchten Sie tun?");
            
            while(spielerzug == true){
                //Der Spieler kann aus 4 Spielzügen Entscheiden. Bei bestimmten Entscheidungen kann der Spieler keine neue Entscheidung mehr fällen und sein Zug wird beendet
                    EntscheidungSpieler = (String) Input.get(Input.STRING, s.getFarbe()+"1: Setzen, 2: Aussteigen, 3: Guthaben ansehen, 4: Statistik");
                    //if "cases" für die jeweiligen Spielzügen
                        if(EntscheidungSpieler.equals("1")){
                            jetons= (int) Input.get(Input.INT,"Wie viele Jetons wollen sie setzen?",false);
                            s.setEinsatz(jetons);
                            System.out.println("Sie haben "+s.getEinsatz()+" Jetons gesetzt.");
                            aktiveSpieler++;
                            EntscheidungSpieler="";
                            spielerzug = false;
                        }
                        else if(EntscheidungSpieler.equals("2")){
                            System.out.println(s.getName()+", Sie spielen in dieser Partie nicht mit.");
                            EntscheidungSpieler="";
                            spielerzug = false;
                        }
                        else if(EntscheidungSpieler.equals("3")){
                            Ausgaben.Linie();
                            System.out.println(s.getName()+", Ihr Guthaben beträgt: "+s.getGuthaben());
                            Ausgaben.Linie();
                            EntscheidungSpieler="";
                            spielerzug = true;
                        }
                        else if(EntscheidungSpieler.equals("4")){
                            Ausgaben.Linie();
                            System.out.println(s.getStatistik());
                            Ausgaben.Linie();
                            EntscheidungSpieler="";
                            spielerzug = true;
                        }
                        else{
                            System.out.println("Ungültige Eingabe");
                            EntscheidungSpieler="";
                            spielerzug = true;
                        }  
            }
            Input.confirm(s.getFarbe()+enter);
            EntscheidungSpieler="";
            spielerzug = true;
            Ausgaben.Linie();
        }            
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------            
//Rundenzähler        
        Ausgaben.Linie();
        System.out.println(farbe.WHITE_BOLD_BRIGHT+"Runde " +runden);   
        Ausgaben.Linie();
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Erster Zug des Dealers
        if(aktiveSpieler>0){
            //Aufruf der ersterzug Methode des Dealers
            Dealer.getErsterzug(); 
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Jeder Spieler kann wieder entscheidungen treffen wie zusätzliche Karte, Halten oder Verdoppeln
        for(Spieler s :  spieler){
            if(s.getEinsatz()>0){
                //Erstellung und Ausgabe der Starthand jedes Spielers
                s.nKarte();s.nKarte();
                System.out.println(s.getKarten());

                if(s.getKartensumme()==21){
                        Ausgaben.BlackJack(s.getName());
                } 
                else{
                    if(s.getGuthaben()==0 ||(s.getEinsatz()>s.getGuthaben()))  s.vstatus();
                    while(spielerzug == true && s.getVstatus()==true){
                        Ausgaben.Leerzeile();
                        EntscheidungSpieler = (String) Input.get(Input.STRING,s.getName()+", was möchten Sie tun?\n1: Karte, 2: Halten, 3: Verdoppeln");

                        if(EntscheidungSpieler.equals("1")){ 
                            s.nKarte();
                            System.out.println(s.getKarten());
                                if(s.getKartensumme()>=21) spielerzug = false;
                                else    spielerzug = true;
                            s.vstatus();
                        }
                        else if(EntscheidungSpieler.equals("2")){
                            System.out.println(s.getKarten());
                            spielerzug = false;
                        }
                        else if(EntscheidungSpieler.equals("3")){
                            
                            s.setEinsatz(s.getEinsatz());
                            s.nKarte();
                            System.out.println(s.getKarten());
                            spielerzug=false;
                        }
                        else{
                            System.out.println("Ungültige Eingabe");
                            spielerzug = true;
                        }                        
                    }

                    while(spielerzug == true && s.getVstatus()==false){
                        Ausgaben.Leerzeile();
                        EntscheidungSpieler = (String) Input.get(Input.STRING,s.getName()+", was möchten Sie tun?\n1: Karte, 2: Halten");

                        if(EntscheidungSpieler.equals("1")){
                            s.nKarte();
                            System.out.println(s.getKarten());
                                if(s.getKartensumme()>=21) spielerzug = false;
                                else    spielerzug = true;
                        }
                        else if(EntscheidungSpieler.equals("2")){
                            System.out.println(s.getKarten());
                            spielerzug = false;
                        }
                        else{
                            System.out.println("Ungültige Eingabe");
                            spielerzug = true;
                        }                        
                    }      
                    Ausgaben.Leerzeile();
                }
                if(s.getKartensumme()>21) Ausgaben.Bust(s.getName());
                Input.confirm(s.getFarbe()+enter);
            }
            spielerzug=true;
            Ausgaben.Linie();
            
        }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Showdown

    Ausgaben.Linie();
    System.out.println(farbe.WHITE_BOLD_BRIGHT+"SHOWDOWN\tRunde " +runden);   
    Ausgaben.Linie();


        Dealer.Dealerzug();
        System.out.println(Dealer.getKarten());
        if(Dealer.getKartensumme()>21) Ausgaben.Dealerbust();
        Ausgaben.Linie();

        for(Spieler s :  spieler){
            if(s.getEinsatz()>0){
//----------------------------------------------------------------------------------------------------------------------
//Gewinn glorreiche 7
                if(s.getKartensumme()<22 && s.getAnzahlKarten()==7){
                    System.out.println(s.getName()+", sie haben die Glorreichen sieben! Sie haben sich nicht mit 7 Karten überkauft. Glückwunsch!\nSie haben "+farbe.YELLOW_UNDERLINED+(s.getEinsatz()*2)+s.getFarbe()+" Jetons gewonnen.");
                    Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                    
                    s.setGuthaben(s.getEinsatz()*2);
                    s.addstatSieg();
                }
//----------------------------------------------------------------------------------------------------------------------                
//Gewinn Blackjack
                else if((s.getKartensumme()==21&&s.getAnzahlKarten()==2)||(Dealer.getKartensumme()==21&&Dealer.getAnzahlKarten()==2)){
                    //Double Blackjack    
                    if((s.getKartensumme()==21&&s.getAnzahlKarten()==2)&&(Dealer.getKartensumme()==21&&Dealer.getAnzahlKarten()==2)){
                        System.out.println(s.getName()+", sie und der Dealer haben ein Blackjack!\nIhr Einsatz von "+farbe.YELLOW_UNDERLINED+s.getEinsatz()+s.getFarbe()+" Jetons wurde ihnen zurückerstattet.");
                        Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                        s.setGuthaben(s.getEinsatz());
                    }
                    //Spieler Blackjack
                    else if(s.getKartensumme()==21&&s.getAnzahlKarten()==2){
                        
                        double k = (s.getEinsatz()*2.5);
                        int kommarundung = (int) (k + 0.5);
                        
                        System.out.println(s.getName()+", Sie haben ein Blackjack!\nSie haben "+farbe.YELLOW_UNDERLINED+kommarundung+s.getFarbe()+" Jetons gewonnen.");
                        Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                        

                        s.setGuthaben(kommarundung); 
                        s.addstatSieg();
                    }
                    //Dealer Blackjack
                    else if(Dealer.getKartensumme()==21&&Dealer.getAnzahlKarten()==2){
                        System.out.println(s.getName()+", der Dealer hat ein Blackjack!\nSie haben verloren.");
                        Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());

                        s.addstatNiederlage();
                        
                    }                      
                }
//----------------------------------------------------------------------------------------------------------------------
//Bustung
                else if(s.getKartensumme()>21||Dealer.getKartensumme()>21){
                    //Double Bust
                    if(Dealer.getKartensumme()>21&&s.getKartensumme()>21){
                        System.out.println(s.getName()+", Sie und der Dealer haben sich überkauft!\nIhr Einsatz von "+farbe.YELLOW_UNDERLINED+s.getEinsatz()+s.getFarbe()+" wurde ihnen zurückerstattet.");
                        Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                        s.setGuthaben(s.getEinsatz());
                    }
                    //DealerBust
                    else if (Dealer.getKartensumme()>21 && s.getKartensumme()<22){
                       System.out.println(s.getName()+", der Dealer hat sich überkauft.\nSie haben "+farbe.YELLOW_UNDERLINED+(s.getEinsatz()*2)+s.getFarbe()+" Jetons gewonnen.");
                       Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                      

                       s.setGuthaben(s.getEinsatz()*2);
                       s.addstatSieg();
                    }
                    //Spielerbust
                    else if (s.getKartensumme()>21 && Dealer.getKartensumme()<22){
                        System.out.println(s.getName()+", Sie haben sich überkauft, der Dealer hat gewonnen.");
                        Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                        s.addstatNiederlage();
                     }
                }
//----------------------------------------------------------------------------------------------------------------------
//Normaler Verlauf
                else if(s.getKartensumme()<22 && Dealer.getKartensumme()<22){
                    //Unentschieden
                    if(s.getKartensumme()==Dealer.getKartensumme()){
                        System.out.println(s.getName()+", Sie und der Dealer haben die gleichen Karten. Unentschieden!\nIhr Einsatz von "+farbe.YELLOW_UNDERLINED+s.getEinsatz()+s.getFarbe()+" wurde ihnen zurückerstattet.");
                        Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                        
                        s.setGuthaben(s.getEinsatz());
                    }
                    //Spielergewinn
                    else if(s.getKartensumme()>Dealer.getKartensumme()){
                        System.out.println(s.getName()+", ihre Karten sind höher als die des Dealers.\nSie haben "+farbe.YELLOW_UNDERLINED+(s.getEinsatz()*2)+s.getFarbe()+" gewonnen.");
                        Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                        

                        s.setGuthaben(s.getEinsatz()*2);
                        s.addstatSieg();
                    }
                    //Dealergewinn
                    else if(s.getKartensumme()<Dealer.getKartensumme()){
                        System.out.println(s.getName()+", die Karten des Dealers sind höher als ihre, Sie haben verloren.");
                        Ausgaben.ihreKarten(s.getKartensumme(), s.getFarbe());
                        
                        s.addstatNiederlage();
                    }
                }
                Input.confirm(s.getFarbe()+enter);
                Ausgaben.Linie();
            }
            
        }
    }
    else{
        System.out.println("Anscheinend möchte niemand spielen. Enttäuschend.\n"+farbe.ent);
        Ausgaben.Linie();
    } 
        
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Löschen der Karten für eine neue Runde
        for(Spieler s :  spieler){
            s.clear();
        }
        Dealer.clear();

//Löschen von Spielern welche kein Geld mehr haben
    Iterator<Spieler> iterator = spieler.iterator();
    while(iterator.hasNext()){

        Spieler s = iterator.next();
        
        if(s.getGuthaben()<=0){
            Ausgaben.Linie();
            System.out.println(s.getName()+", leider haben sie sich verzockt. Auf Wiedersehen!");
            iterator.remove();
        }  
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
        if(spieler.size()==0){
            spielen= false;
            Ausgaben.Linie();
            Ausgaben.Leerzeile();
            System.out.println(farbe.YELLOW_BOLD+"Das Casino hat gewonnen.");
        }
        else{
            spielen= false;
            Ausgaben.Linie();
            Ausgaben.Leerzeile();
            Input.addTrueValue("Ja");
            spielen = (boolean) Input.get(Input.BOOLEAN, farbe.RED_BOLD+"Wollen sie weiterspielen? %values%");
                    //Zurücksetzung der Entscheidungsvariable um Probleme mit neuen Eingaben zu verhindern
                    EntscheidungSpieler="";
                    //Rundenzähler wird um 1 erhöht da die Runde abgeschlossen wurde
                    runden++;
                    Ausgaben.Leerzeile();
                    Ausgaben.Linie();
        }
    //Dauerschleife welche das Spiel solange wiederholt bis der boolean spielen negativ ist
    }while(spielen ==true);
            Ausgaben.Leerzeile();Ausgaben.Linie();Ausgaben.Linie();
            System.out.println(farbe.CYAN_UNDERLINED+"\tVielen Dank, dass sie Blackjack von Andreas Gögele gespielt haben.");
            Ausgaben.Linie();Ausgaben.Linie();Ausgaben.Leerzeile();
    }
}