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
     * 
     * @return une chaîne de caractères res.
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
                if (this.g[i - 1][j].estRecouverte() == false) {
                    if (this.g[i - 1][j].getCouleur() == 1) {
                        res = res + "     |";
                    } else {
                        res = res + "  " + this.g[i - 1][j].getCouleur() + "  |";
                    }
                } else if (this.g[i - 1][j].estRecouverte() == true) {
                    res = res + " [" + this.g[i - 1][j].getLettre() + "] |";
                }
            }
            res = res + "\n"
                    + "-----------------------------------------------------------------------------------------------"
                    + "\n";
        }
        return res;
    }

    /**
     * Méthode capeloDico
     * Action: Demande à l'utilisateur de vérifier si le mot est valide dans un
     * dictionnaire, retourne vrai si le mot est valide, faux si le mot ne l'est
     * pas.
     * 
     * @param mot
     * @return un booléen res.
     */
    public boolean capeloDico(String mot) {
        boolean res = false;
        Ut.afficherSL("Le mot " + mot
                + " est il valide (Présent dans un dictionnaire, et saisi en toutes majuscules)?\n Saisir '1' si oui, '2' sinon.");
        int reponse = Ut.saisirEntier();
        if (reponse == 1 || reponse == 2) {
            if (reponse == 1) {
                res = true;
            } else if (reponse == 2) {
                res = false;
            }
        } else {
            Ut.afficherSL("Entrez un choix valide.");
            capeloDico(mot);
        }
        return res;
    }

    /**
     * Méthode contient
     * Action: Vérifie si le mot souhaité est bien présent dans le chevalet du
     * joueur en parcourant tous les caractères du mot.
     * 
     * @param mot
     * @param e
     * @return un booléen res.
     */
    public boolean contient(String mot, MEE e) {
        boolean res = true;
        int[] chevalet = e.getTabFreq();
        int x = 0;
        while (res && x < mot.length()) {
            if (chevalet[Ut.majToIndex(mot.charAt(x))] != 0) {
                chevalet[Ut.majToIndex(mot.charAt(x))] = chevalet[Ut.majToIndex(mot.charAt(x))] - 1;
                x++;
            } else {
                res = false;
            }
        }
        return res;
    }

    /**
     * Méthode placementValide
     * Action: Vérifie toutes les conditions pour que le placement d'un mot soit
     * valide au scrabble, et retourne vrai si
     * toutes ces conditions sont valides.
     * 
     * @param mot
     * @param numLigne
     * @param numColonne
     * @param sens
     * @param e
     * @return un booléen res.
     */
    public boolean placementValide(String mot, int numLigne, int numColonne, char sens, MEE e) {
        boolean res = false;
        StringBuilder mot2 = new StringBuilder(mot);
        int caseRecouverte = 0;
        int caseVide = 0;
        // Cas où le plateau est vide, et donc que la case centrale est vide.
        if (this.g[7][7].estRecouverte() == false) {
            if (mot.length() >= 2) {
                // Cas où le plateau est vide, et que le sens de placement du mot est
                // horizontal.
                if (sens == 'h') {
                    if (numColonne + mot.length() - 1 <= 14) {
                        if (numColonne <= 7 && 7 <= numColonne + mot.length()) {
                            if (contient(mot, e)) {
                                if (capeloDico(mot)) {
                                    res = true;
                                }
                            }
                        }
                    }
                }
                // Cas où le plateau est vide, et que le sens de placement du mot est vertical.
                else if (sens == 'v') {
                    if (numLigne + mot.length() - 1 <= 14) {
                        if (numLigne <= 7 && 7 <= numLigne + mot.length()) {
                            if (contient(mot, e)) {
                                if (capeloDico(mot)) {
                                    res = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        // Cas où le plateau n'est pas vide, et donc que la case centrale est
        // recouverte.
        else if(this.g[7][7].estRecouverte() == true){
            // Cas où le plateau n'est pas vide, et que le sens de placement du mot est
            // horizontal.
            if (sens == 'h') {
                for (int i = 0; i < mot.length() - 1; i++) {
                    if (this.g[numLigne][numColonne + i].estRecouverte() && this.g[numLigne][numColonne + i].getLettre() == mot.charAt(i)) {
                        mot2.deleteCharAt(i);
                        caseRecouverte++;
                    } else if (this.g[numLigne][numColonne + i].estRecouverte() == false) {
                        caseVide++;
                    }
                }
                if (numColonne + mot.length() - 1 <= 14) {
                    if (this.g[numLigne][numColonne - 1].estRecouverte() == false || numColonne == 0) {
                        if (this.g[numLigne][numColonne + mot.length()].estRecouverte() == false
                                || numColonne + mot.length() - 1 == 14) {
                            if (caseRecouverte >= 1 && caseVide >= 1) {
                                if (contient(mot2.toString(), e)) {
                                    if (capeloDico(mot) == true) {
                                        res = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // Cas où le plateau n'est pas vide, et que le sens de placement du mot est
            // vertical.
            else if (sens == 'v') {
                for (int i = 0; i < mot.length() - 1; i++) {
                    if (this.g[numLigne + i][numColonne].estRecouverte() && this.g[numLigne + i][numColonne].getLettre() == mot.charAt(i)) {
                        mot2.deleteCharAt(i);
                        caseRecouverte++;
                    } else if (this.g[numLigne + i][numColonne].estRecouverte() == false) {
                        caseVide++;
                    }
                }
                if (numLigne + mot.length() - 1 <= 14) {
                    if (this.g[numLigne - 1][numColonne].estRecouverte() == false || numLigne == 0) {
                        if (this.g[numLigne + mot.length()][numColonne].estRecouverte() == false
                                || numLigne + mot.length() - 1 == 14) {
                            if (caseRecouverte >= 1 && caseVide >= 1) {
                                if (contient(mot2.toString(), e)) {
                                    if (capeloDico(mot) == true) {
                                        res = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * Méthode nbPointsPlacement
     * Action: Calcule tous les points rapportés par le placement d'un mot, à l'aide
     * de la valeur de la case, et la valeur
     * des lettres du mot.
     * 
     * @param mot
     * @param numLigne
     * @param numColonne
     * @param sens
     * @param nbPointsJetons
     * @return un entier res.
     */
    public int nbPointsPlacement(String mot, int numLigne, int numColonne, char sens, int[] nbPointsJet) {
        int res = 0;
        int multiplicateur = 0;
        if (sens == 'h') {
            for (int i = 0; i < mot.length(); i++) {
                int valeurCaseH = nbPointsJet[Ut.majToIndex(mot.charAt(i))];
                if (this.g[numLigne][numColonne + i].getCouleur() == 2) {
                    valeurCaseH = valeurCaseH * 2;
                } else if (this.g[numLigne][numColonne + i].getCouleur() == 3) {
                    valeurCaseH = valeurCaseH * 3;
                }
                res = res + valeurCaseH;
            }
            for (int i = numColonne; i < numColonne + mot.length(); i++) {
                if (this.g[numLigne][i].getCouleur() == 4) {
                    multiplicateur = multiplicateur + 2;
                } else if (this.g[numLigne][i].getCouleur() == 5) {
                    multiplicateur = multiplicateur + 3;
                }
            }
            res = res * multiplicateur;
        } else if (sens == 'v') {
            for (int i = 0; i < mot.length(); i++) {
                int valeurCaseV = nbPointsJet[Ut.majToIndex(mot.charAt(i))];
                if (this.g[numLigne + i][numColonne].getCouleur() == 2) {
                    valeurCaseV = valeurCaseV * 2;
                } else if (this.g[numLigne + i][numColonne].getCouleur() == 3) {
                    valeurCaseV = valeurCaseV * 3;
                }
                res = res + valeurCaseV;
            }
            for (int i = numLigne; i < numLigne + mot.length(); i++) {
                if (this.g[i][numColonne].getCouleur() == 4) {
                    multiplicateur = multiplicateur + 2;
                } else if (this.g[i][numColonne].getCouleur() == 5) {
                    multiplicateur = multiplicateur + 3;
                }
            }
            res = res * multiplicateur;
        }
        return res;
    }

    /**
     * Méthode place
     * Action: place le mot voulu par l'utilisateur sur la grille / plateau de
     * Scrabble, et retourne le nombre de jetons retirés de e.
     * 
     * @param mot
     * @param numLigne
     * @param numColonne
     * @param sens
     * @param e
     * @return un entier res.
     */
    public int place(String mot, int numLigne, int numColonne, char sens, MEE e) {
        int res = 0;
        int j = 0;
        if (sens == 'h') {
            for (int i = numColonne; i < numColonne + mot.length(); i++) {
                if (e.retire(Ut.majToIndex(mot.charAt(j)))) {
                    res = res + 1;
                }
                g[numLigne][i].setLettre(mot.charAt(j));
                j++;
            }
        } else if (sens == 'v') {
            for (int i = numLigne; i < numLigne + mot.length(); i++) {
                if (e.retire(Ut.majToIndex(mot.charAt(j)))) {
                    res = res + 1;
                }
                g[i][numColonne].setLettre(mot.charAt(j));
                j++;
            }
        }
        return res;
    }

}
