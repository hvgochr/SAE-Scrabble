public class Case {

    private int couleur;
    private char lettre;

    /**
     * Constructeur Case
     * Pré-requis: uneCouleur est un entier entre 1 et 5.
     * Action: Crée une case Case dont la couleur est un entier compris entre 1 et
     * 5.
     * 
     * @param uneCouleur
     */
    public Case(int uneCouleur) {
        this.couleur = uneCouleur;
        this.lettre = 0;
    }

    /**
     * Pré-requis: l'attribut couleur de this est un entier compris entre 1 et 5.
     * Accesseur en lecture de l'attribut couleur.
     * 
     * @return un entier this.couleur
     */
    public int getCouleur() {
        return this.couleur;
    }

    /**
     * Pré-requis: La case est recouverte.
     * Accesseur en lecture de l'attribut lettre.
     * 
     * @return un caractère this.lettre
     */
    public char getLettre() {
        return this.lettre;
    }

    /**
     * Pré-requis : le caractère let passé en paramètre est une lettre majuscule.
     * Définition de la valeur de this.lettre
     * 
     * @param let
     * @return void
     */
    public void setLettre(char let) {
        this.lettre = let;
    }

    /**
     * Méthode estRecouverte
     * Action: Initialise un booléen res, qui prendra la valeur vrai si la case est
     * recouverte.
     * 
     * @return un booleén res
     */
    public boolean estRecouverte() {
        boolean res = false;
        if (this.lettre != 0) {
            res = true;
        }
        return res;
    }

    /**
     * Méthode toString
     * Action: initialise une chaîne de caractère qui donnera les informations sur
     * la case.
     * 
     * @return une chaîne de caractères res
     */
    public String toString() {
        String res = "";
        if (this.estRecouverte()==false) {
            if (this.getCouleur() != 1) {
                res = res + this.getCouleur();
            } else {
                res = res + " ";
            }
        } else {
            res = res + this.getLettre();
        }
        return res;
    }

}
