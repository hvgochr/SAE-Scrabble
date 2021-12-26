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
        boolean res = false;
        //Cas où le plateau est vide, et donc que la case centrale est vide.
        if(this.g[7][7].estRecouverte()==false){
            if(mot.length()>=2){
                //Cas où le plateau est vide, et que le sens de placement du mot est horizontal.
                if(sens=='h'){
                    if(numColonne+mot.length()<=15 && numColonne<=8 && 8<=numColonne+mot.length()/*manque mot présent dans chevalet*/){
                        
                    }
                }
                //Cas où le plateau est vide, et que le sens de placement du mot est vertical.
                else if(sens=='v'){
                    if(numLigne+mot.length()<=15 && numLigne<=8 && 8<= numLigne+mot.length()/*manque mot présent dans chevalet*/){
                        
                    }
                }
            }    
        }
        //Cas où le plateau n'est pas vide, et donc que la case centrale est recouverte.
        else{
            //Cas où le plateau est vide, et que le sens de placement du mot est horizontal.
            if(sens=='h'){
                if(numColonne+mot.length()<=15 && this.g[numLigne][numColonne-1].estRecouverte()==false && this.g[numLigne][numColonne+mot.length()].estRecouverte()==false){

                }
            }
            //Cas où le plateau est vide, et que le sens de placement du mot est vertical.
            else if(sens=='v'){
                if(numLigne+mot.length()<=15 && this.g[numLigne-1][numColonne].estRecouverte()==false && this.g[numLigne+mot.length()][numColonne].estRecouverte()==false){
                    //Manque si la zone avec case avec jeton et case sans jeton, si la lettre du jeton est la même que celle du mot à placer dans la case, et si le mot est présent dans le chevalet.
                }
            }
        }
        return res;
    }

    public int nbPointsPlacement(String mot, int numLigne, int numColonne, char sens, int[] nbPointsJetons){
        int res=0;
        return res;
    }

    public int place(String mot, int numLigne, int numColonne, char sens, MEE e){
        int res = 0;
        return res;
    }

    public static void main (String[]args){
        Plateau p = new Plateau();
        Ut.afficher(p.toString());
    }

}
