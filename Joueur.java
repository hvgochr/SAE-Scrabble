public class Joueur {

    private String nom;
    private MEE chevalet;
    private int score;

    /**
     * Constructeur Joueur
     * Action: Crée un Joueur dont le nom sera la chaîne de caractères passée en
     * paramètre.
     * 
     * @param unNom
     */
    public Joueur(String unNom) {
        this.nom = unNom;
        this.score = 0;
        this.chevalet = new MEE(26);
    }

    /**
     * Méthode toString
     * Action: Affichage du nom de this ainsi que de son score.
     * 
     * @return une chaîne de caractères res
     */
    public String toString() {
        String res = "joueur " + this.nom + " possède " + this.score + " points.\n";
        return res;
    }

    /**
     * Accesseur en lecture de l'attribut chevalet.
     * 
     * @return un MEE this.chevalet
     */
    public MEE getChevalet() {
        return this.chevalet;
    }

    /**
     * Accesseur en lecture de l'attribut score.
     * 
     * @return un entier this.score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Méthode ajouteScore
     * Action: Ajoute nb points au score de this.
     * 
     * @param nb
     * @return void
     */
    public void ajouteScore(int nb) {
        this.score = this.score + nb;
    }

    /**
     * Accesseur en lecture de l'attribut nom.
     * 
     * @return un chaîne de caractères this.nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode nbPointsChevalet
     * Action: Compte le nombre de points du chevalet actuel de this et le retourne.
     * 
     * @param nbPointsJet
     * @return en entier res
     */
    public int nbPointsChevalet(int[] nbPointsJet) {
        int res = this.chevalet.sommeValeurs(nbPointsJet);
        return res;
    }

    /**
     * Méthode prendJetons
     * Action: Simule la prise de nbjetons par this dans la pioche s.
     * 
     * @param s
     * @param nbJetons
     * @return void
     */
    public void prendJetons(MEE s, int nbJetons) {
        s.transfereAleat(this.chevalet, nbJetons);
    }

    /**
     * Méthode joue
     * Action: Demande à this ce qu'il souhaite faire lors du tour actuel: passer
     * son tour, échanger des jetons, ou placer un mot.
     * 
     * @param p
     * @param s
     * @param nbPointsJet
     * @return un entier res
     */
    public int joue(Plateau p, MEE s, int[] nbPointsJet) {
        int res = 0;
        Ut.afficherSL("Voici l'état du plateau actuel:\n" + p.toString());
        Ut.afficherSL("Voici votre chevalet: " + this.getChevalet().toString() + ".");
        Ut.afficherSL(this.nom
                + ", que souhaitez-vous faire?\nTapez '1' pour passer votre tour.\nTapez '2' pour échanger.\nTapez '3' pour placer.");
        int reponse = Ut.saisirEntier();
        while (reponse != 1 && reponse != 2 && reponse != 3) {
            Ut.afficher("Le choix entré n'est pas valide.");
            Ut.afficherSL(this.nom
                    + ", que souhaitez-vous faire?\nTapez '1' pour passer votre tour.\nTapez '2' pour échanger.\nTapez '3' pour placer.");
            reponse = Ut.saisirEntier();
        }
        if (reponse == 1) {
            res = -1;
        } else if (reponse == 2) {
            this.echangeJetons(s);
            res = 0;
        } else if (reponse == 3) {
            if (joueMot(p, s, nbPointsJet) == true) {
                if (this.chevalet.estVide() == true) {
                    res = 1;
                } else {
                    res = 0;
                }
            }
        }
        return res;
    }

    /**
     * Méthode joueMot
     * Action: Simule le placement d'un mot de this. On demande alors la ligne, la
     * colonne, le mot et le sens de placement souhaité.
     * 
     * @param p
     * @param s
     * @param nbPointsJet
     * @return un booléen res
     */
    public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet) {
        boolean res = false;
        Ut.afficherSL(p.toString());
        Ut.afficherSL("Voici votre chevalet: " + this.getChevalet().toString());
        Ut.afficherSL("Dans quel sens voulez vous placer le mot?\nSaisir 'h' pour horizontal, 'v' pour vertical.");
        char sens = Ut.saisirCaractere();
        while (sens != 'h' && sens != 'v') {
            Ut.afficherSL("La saisie entrée n'est pas valide.");
            Ut.afficherSL("Dans quel sens voulez vous placer le mot?\nSaisir 'h' pour horizontal, 'v' pour vertical.");
            sens = Ut.saisirCaractere();
        }
        Ut.afficherSL("Sur quelle ligne voulez-vous placer le mot?");
        int numLigne = Ut.saisirEntier() - 1;
        Ut.afficherSL("Sur quelle colonne voulez-vous placer le mot?");
        int numColonne = Ut.saisirEntier() - 1;
        Ut.afficherSL("Quel est le mot que vous voulez placer?");
        String mot = Ut.saisirChaine();
        if (p.placementValide(mot, numLigne, numColonne, sens, this.chevalet) == true) {
            joueMotAux(p, s, nbPointsJet, mot, numLigne, numColonne, sens);
            res = true;
            Ut.afficherSL(this.nom + ", votre score est désormais de: " + this.score + " points.");
        } else if (p.placementValide(mot, numLigne, numColonne, sens, this.chevalet) == false) {
            Ut.afficherSL("Le placement voulu n'est pas valide. Au tour du joueur suivant.");
            res = false;
        }
        return res;
    }

    /**
     * Méthode joueMotAux
     * Action: Effectue toutes les actions du jeu d'un joueur: placement du mot,
     * rajout des lettres manquantes dans le chevalet, ajout des points au score du
     * joueur.
     * 
     * @param p
     * @param s
     * @param nbPointsJet
     * @param mot
     * @param numLigne
     * @param numColonne
     * @param sens
     * @return void
     */
    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot, int numLigne, int numColonne, char sens) {
        p.place(mot, numLigne, numColonne, sens, this.chevalet);
        this.prendJetons(s, 7 - mot.length());
        this.ajouteScore(p.nbPointsPlacement(mot, numLigne, numColonne, sens, nbPointsJet));
        if (mot.length() == 7) {
            this.ajouteScore(50);
        }
    }

    /**
     * Méthode contient
     * Action: Vérifie si une chaîne de caractères est bien présente dans un
     * ensemble, utile pour vérifier si le joueur possède les lettres qu'il souhaite
     * placer.
     * 
     * @param chaine
     * @param e
     * @return un booléen res.
     */
    public boolean contient(String chaine, MEE e) {
        boolean res = true;
        int[] tabChevalet = this.chevalet.getTabFreq();
        int x = 0;
        while (res && x < chaine.length()) {
            if (tabChevalet[Ut.majToIndex(chaine.charAt(x))] != 0) {
                tabChevalet[Ut.majToIndex(chaine.charAt(x))] = tabChevalet[Ut.majToIndex(chaine.charAt(x))] - 1;
                x++;
            } else {
                res = false;
            }
        }
        return res;
    }

    /**
     * Méthode echangeJetons
     * Action: Effectue la saisie des caractères que le joueur souhaite échanger,
     * puis simule l'échange de ces jetons.
     * 
     * @param sac
     * @retunr void
     */
    public void echangeJetons(MEE sac) {
        Ut.afficherSL("Entrez les lettres en majuscule que vous souhaitez échanger.");
        String chaine = Ut.saisirChaine();
        while (this.estCorrectPourEchange(chaine) == false) {
            Ut.afficherSL("Entrez les lettres en majuscule que vous souhaitez échanger.");
            chaine = Ut.saisirChaine();
        }
        echangeJetonsAux(sac, chaine);
    }

    /**
     * Méthode estCorrectPourEchange
     * Action: Vérifie toutes les conditions des jetons que le joueur souhaite
     * échanger: toutes les lettres sont des majuscules, et les lettres qui doivent
     * être échangées sont un sous ensemble du chevalet.
     * 
     * @param chaine
     * @return un booléen res.
     */
    public boolean estCorrectPourEchange(String chaine) {
        boolean res = true;
        for (int i = 0; i < chaine.length(); i++) {
            if (Ut.estUneMajuscule(chaine.charAt(i)) == false) {
                res = false;
            }
        }
        if (res == false) {
            Ut.afficherSL("Une ou plusieurs lettres saisies ne sont pas en majuscule.");
        }
        if (contient(chaine, this.chevalet)) {
            res = true;
        } else {
            res = false;
            Ut.afficherSL("Une ou plusieurs lettres saisies ne sont pas présentes dans votre chevalet.");
        }
        return res;
    }

    /**
     * Méthode echangeJetonsAux
     * Action: Effectue un échange entre des jetons de ensJetons et des jetons du
     * sac tirés aléatoirement.
     * 
     * @param sac
     * @param ensJetons
     * @retunr void
     */
    public void echangeJetonsAux(MEE sac, String ensJetons) {
        for (int i = 0; i < ensJetons.length(); i++) {
            int lettre = Ut.majToIndex(ensJetons.charAt(i));
            sac.transfereAleat(this.chevalet, 1);
            this.chevalet.transfere(sac, lettre);
        }
    }

}
