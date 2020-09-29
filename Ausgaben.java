class Ausgaben{
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Siege
    //Ausgabe bei Blackjack Sieg
    public static void BlackJack(String name){
        System.out.println(name+", sie haben ein BLACKJACK!");
    }
    //Ausgabe wenn Spieler gewinnt
    public static void SpielerGewinn(String name){
        System.out.println(name+ ", Sie haben gewonnen!");
    }
    //Ausgabe wenn Spieler mit 7 Karten nicht überkauft
    public static void Glorreiche7Spieler(String name){
        System.out.println(name+", Sie haben die glorreichen 7! Sie haben gewonnen da sie mit 7 Karten die 21 nicht überschritten haben. Glückwunsch");
    }
    //Ausgabe Dealerbust
    public static void Dealerbust(){
        System.out.println(farbe.YELLOW+"Die Karten des Dealers sind höher als 21! Bust!");
    }

    public static void Gewinn(String name, int gewinn){
        System.out.println(name+ ", Sie haben " +gewinn+" Jetons gewonnen!");
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Niederlagen
    //Ausgabe bei Überkauf
    public static void Bust(String name){
        System.out.println(name+", Ihre Karten sind höher als 21. Bust.");
    }
    //Ausgabe wenn Dealer gewinnt
    public static void DealerGewinn(String farbe){
        System.out.println(farbe+"Der Dealer hat gewonnen! Sie haben verloren.");
    }
     //Ausgabe wenn Dealer ein Blackjack hat
     public static void DealerBlackjack(){
        System.out.println(farbe.YELLOW+"Der Dealer hat ein Blackjack!");
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  
//Unentschieden
    //Ausgabe bei unentschieden
    public static void Unentschieden(String farbe){
        System.out.println(farbe+"Unentschieden! Niemand hat gewonnen.");
    }
    public static void ihreKarten(int kartensumme, String farbe){
        System.out.println(farbe+"Summe ihrer Karten:\t"+kartensumme);
    }
  
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void Linie(){
        System.out.println(farbe.RESET+"-------------------------------------------------------------------------------------");
    }
    public static void Leerzeile(){
        System.out.println(farbe.RESET);
    }
}