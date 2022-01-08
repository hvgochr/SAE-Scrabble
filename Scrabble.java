public class Scrabble {

    private static int[] nbPointsJet = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10,
            10, 10 };
    private Joueur[] joueurs;
    private int numJoueur; // joueur courant (entre 0 et joueurs.length-1)
    private Plateau plateau;
    private MEE sac;

    /**
     * Constructeur Scrabble
     * Action: Crée une liste de joueurs, un plateau, un sac (pioche) et initialise
     * ce sac avec la quantité de lettres contenues dans la pioche du Scrabble
     * officiel.
     * 
     * @param tabJoueurs
     */
    public Scrabble(String[] tabJoueurs) {
        this.joueurs = new Joueur[tabJoueurs.length];
        for (int i = 0; i < tabJoueurs.length; i++) {
            this.joueurs[i] = new Joueur(tabJoueurs[i]);
        }
        int[] tabQuantiteLettres = { 9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 6, 6, 2, 1, 1, 1, 1 };
        this.plateau = new Plateau();
        this.sac = new MEE(tabQuantiteLettres);
    }

    /**
     * Méthode partie
     * Action: Construit une partie de Scrabble: initialise les chevalets de tous
     * les joueurs, et organise le déroulement d'une partie.
     * 
     * @return void
     */
    public void partie() {
        boolean stop = false;
        int passe = 0;
        for (int i = 0; i < this.joueurs.length; i++) {
            this.joueurs[i].prendJetons(this.sac, 7);
            while(this.joueurs[i].getChevalet().getNbTotEx()<7){
                this.joueurs[i].prendJetons(this.sac, 1);
            }
        }
        this.numJoueur = Ut.randomMinMax(0, joueurs.length - 1);
        while (stop == false) {
            int joue = joueurs[this.numJoueur].joue(this.plateau, this.sac, this.nbPointsJet);
            if (joue == -1) {
                passe = passe + 1;
                if (passe == joueurs.length) {
                    stop = true;
                    for (int i = 0; i < joueurs.length; i++) {
                        int ptsChevalet = joueurs[i].nbPointsChevalet(nbPointsJet);
                        joueurs[i].ajouteScore(-ptsChevalet);
                    }
                }
            } else if (joue == 1) {
                stop = true;
                int sommePointsChevalet = 0;
                for (int i = 0; i < joueurs.length; i++) {
                    sommePointsChevalet = sommePointsChevalet + joueurs[i].nbPointsChevalet(nbPointsJet);
                    joueurs[i].ajouteScore(-joueurs[i].nbPointsChevalet(nbPointsJet));
                }
                joueurs[this.numJoueur].ajouteScore(sommePointsChevalet);
            }
            if (this.numJoueur == joueurs.length - 1 && stop == false) {
                this.numJoueur = 0;
                passe = 0;
            } else {
                if (stop == false) {
                    this.numJoueur++;
                }
            }
        }
        Ut.afficherSL(toString());
    }

    /**
     * Méthode toString
     * Action: Comptel es points de chaque joueur, choisi le joueur avec le plus de
     * points, et affiche ce joueur ainsi que son score.
     * 
     * @return un chaîne de caractères String
     */
    public String toString() {
        String res = "";
        int numGagnant = 1;
        int[] gagnants = new int[joueurs.length];
        for (int i = 0; i < joueurs.length; i++) {
            if (joueurs[numGagnant].getScore() == joueurs[i].getScore()) {
                gagnants[i] = i;
            } else if (joueurs[numGagnant].getScore() < joueurs[i].getScore()) {
                numGagnant = i;
            }
        }
        if (numGagnant == gagnants[1] && gagnants[1] != 0) {
            res = res + "Egalité entre " + joueurs[numGagnant].getNom() + " et ";
            for (int i = 1; i < gagnants.length; i++) {
                if (gagnants[i] != 0) {
                    res = res + joueurs[gagnants[i]].getNom() + " avec " + joueurs[gagnants[i]].getScore()
                            + " points.\n";
                }
            }
        } else {
            res = res + "Le gagnant de la partie est " + joueurs[numGagnant].getNom() + " avec "
                    + joueurs[numGagnant].getScore() + " points.\n";
        }
        return res;
    }

}
