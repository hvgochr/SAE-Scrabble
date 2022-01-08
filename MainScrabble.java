public class MainScrabble {

    public static void main (String[]args){
        Ut.afficherSL("Veuillez entrer le nombre de joueurs: "); int nbJoueurs = Ut.saisirEntier();
        while(nbJoueurs<2 || nbJoueurs>4){
            Ut.afficherSL("Vous devez saisir un nombre de joueurs compris entre 2 et 4.");
            Ut.afficherSL("Veuillez entrer le nombre de joueurs: "); nbJoueurs = Ut.saisirEntier();
        }
        String[]tabJoueurs = new String[nbJoueurs];
        for(int i=0; i<tabJoueurs.length; i++){
            Ut.afficherSL("Entrez le nom du joueur N°"+(i+1)+": ");
            tabJoueurs[i]=Ut.saisirChaine();
        }
        Scrabble scrabble = new Scrabble(tabJoueurs);
        scrabble.partie();
    }
    
}
