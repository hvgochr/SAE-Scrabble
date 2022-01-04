public class Joueur {

    private String nom;
    private MEE chevalet;
    private int score;

    public Joueur(String unNom) {
        this.nom = unNom;
    }

    public String toString() {
        String res = "";
        // faire la méthode toString
        return res;
    }

    public MEE getChevalet() {
        return this.chevalet;
    }

    public int getScore() {
        return this.score;
    }

    public void ajouteScore(int nb) {
        this.score = this.score + nb;
    }

    /**
     * pré-requis : nbPointsJet indique le nombre de points rapportés par
     * chaque jeton/lettre
     * résultat : le nombre de points total sur le chevalet de ce joueur
     * suggestion : bien relire la classe MEE !
     */
    public int nbPointsChevalet(int[] nbPointsJet) {
        int res = 0;
        // faire la méthode nbPointsChevalet
        return res;
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26
     * action : simule la prise de nbJetons jetons par this dans le sac s,
     * dans la limite de son contenu.
     */
    public void prendJetons(MEE s, int nbJetons) {
        // faire la méthode prendJetons
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26
     * et nbPointsJet.length >= 26
     * action : simule le coup de this : this choisit de passer son tour,
     * d’échanger des jetons ou de placer un mot
     * résultat : -1 si this a passé son tour, 1 si son chevalet est vide,
     * et 0 sinon
     */
    public int joue(Plateau p, MEE s, int[] nbPointsJet) {
        int res = 0;
        // faire la méthode joue
        return res;
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26
     * et nbPointsJet.length >= 26
     * action : simule le placement d’un mot de this (le mot, sa position
     * sur le plateau et sa direction, sont saisies au clavier)
     * résultat : vrai ssi ce coup est valide, c’est-à-dire accepté par
     * CapeloDico et satisfaisant les règles détaillées plus haut
     */
    public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet) {
        boolean res = false;
        // faire la méthode joueMot
        return res;
    }

    /**
     * pré-requis : les éléments de sac sont inférieurs à 26
     * action : simule l’échange de jetons de ce joueur
     */
    public void echangeJetons(MEE sac) {
        // faire la méthode echangeJetons
    }

}
