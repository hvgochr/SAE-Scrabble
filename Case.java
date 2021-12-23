public class Case {

    private int couleur;
    private boolean recouverte;
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
     * Action: Initialise un booléen res, qui prendre la valeur vrai si la case est
     * recouverte.
     * 
     * @return un booleén res
     */
    public boolean estRecouverte() {
        boolean res = false;
        if (this.recouverte == true) {
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
        if (estRecouverte()) {
            if (Character.isLowerCase(this.lettre)) {
                Character.toUpperCase(this.lettre);
            }
            res = res + this.lettre;
        } else {
            if (this.couleur != 1) {
                res = res + this.couleur;
            } else {
                res = res + " ";
            }
        }
        return res;
    }

}
