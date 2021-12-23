public class Plateau {

    private Case[][] g = new Case[15][15];

    /**
     * Constructeur Plateau
     * Action: Crée un plateau Plateau à partir d'un tableau d'entiers compris entre
     * 1 et 5 représentant les couleurs des cases du plateau de Scrabble original.
     */
    public Plateau() {
        int[][] plateau = 
        { { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 },
        { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
        { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
        { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
        { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
        { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
        { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
        { 5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5 },
        { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
        { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
        { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
        { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
        { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
        { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
        { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 } };
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                this.g[i][j] = new Case(plateau[i][j]);
            }
        }
    }

    /**
     * Constructeur Plateau
     * Action: Crée un plateau à partir d'un plateau rempli au préalable.
     * 
     * @param plateau
     */
    public Plateau(Case[][] plateau) {
        this.g = plateau;
    }

    /**
     * Méthode toString
     * Action: Initialise une chaîne de caractères qui affichera le plateau avec
     * notamment les indices de colonnes, les indices de lignes, et le contenu de ce
     * plateau.
     */
    public String toString() {
        String res = "      C01 | C02 | C03 | C04 | C05 | C06 | C07 | C08 | C09 | C10 | C11 | C12 | C13 | C14 | C15 "
                + "\n"
                + "-----------------------------------------------------------------------------------------------"
                + "\n";
        for (int i = 1; i < this.g.length + 1; i++) {
            if (i < 10) {
                res = res + "L" + i + "  :";
            } else {
                res = res + "L" + i + " :";
            }
            for (int j = 0; j < this.g[0].length; j++) {
                res = res + "  " + this.g[i - 1][j].toString() + "  |";
            }
            res = res + "\n"
                    + "-----------------------------------------------------------------------------------------------"
                    + "\n";
        }
        return res;
    }

    public boolean placementValide(String mot, int numLigne, int numColonne, char sens, MEE e){
        boolean res;
        boolean vide = true;
        if(vide){
            if(mot.length()>=2 /* && reste des conditions dans le cas où le plateau est vide*/){
                res = true;
                vide = false;
            }
        }else{
            if(/*conditions dans le cas où le plateau n'est pas vide*/){
                res=true;
                vide = false;
            }
        }
        return res;
    }

}
