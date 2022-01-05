public class Joueur {

    private String nom;
    private MEE chevalet;
    private int score;

    public Joueur(String unNom) {
        this.nom = unNom;
        this.score = 0;
        this.chevalet = new MEE(26);
    }

    public String toString() {
        String res = "Le joueur "+this.nom+" possède "+this.score+" points.\nVoici les lettres présentes dans votre chevalet:\n{";
        int[]tabChevalet = this.chevalet.getTabFreq();
        for(int i=0; i<tabChevalet.length-1;i++){
            if(tabChevalet[i]!=0){
                res = res + Ut.indexToMaj(tabChevalet[i]) + ", ";
            }
        }
        res = res + Ut.indexToMaj(tabChevalet[tabChevalet.length-1]) + "}";
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
        int res = this.chevalet.sommeValeurs(nbPointsJet);
        return res;
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26
     * action : simule la prise de nbJetons jetons par this dans le sac s,
     * dans la limite de son contenu.
     */
    public void prendJetons(MEE s, int nbJetons) {
        if(nbJetons<=s.getNbTotEx()){
            s.transfereAleat(this.chevalet, nbJetons);
        }else{
            Ut.afficherSL("Il n'y a pas assez de jetons dans le sac.");
        }
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
        Ut.afficherSL(this.nom +", que souhaitez-vous faire?\nTapez '1' pour passer votre tour.\nTapez '2' pour échanger.\nTapez '3' pour placer.\n");
        int reponse = Ut.saisirEntier();
        if(reponse == 1 || reponse ==2 || reponse == 3){
            if(reponse == 1){
                res = -1;
            }else if(reponse == 2){
                this.echangeJetons(s);
                res = 0;
            }else if(reponse == 3){
                if(joueMot(p, s, nbPointsJet)==true){
                    res = 1;
                }else{
                    res = 0;
                }
            }
        }else{
            Ut.afficher("Entrez un choix valide.");
            joue(p, s, nbPointsJet);
        }
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
        Ut.afficherSL("Voici l'état du plateau actuel:\n" + p.toString());
        Ut.afficherSL("Dans quel sens voulez vous placer le mot?\nSaisir 'h' pour horizontal, 'v' pour vertical."); char sens = Ut.saisirCaractere();
        Ut.afficherSL("Sur quelle ligne voulez-vous placer le mot?"); int numLigne = Ut.saisirEntier()-1;
        Ut.afficherSL("Sur quelle colonne voulez-vous placer le mot?"); int numColonne = Ut.saisirEntier()-1;
        Ut.afficherSL("Quel est le mot que vous voulez placer?"); String mot = Ut.saisirChaine();
        if(p.capeloDico(mot)==true){
            if(p.placementValide(mot, numLigne, numColonne, sens, this.chevalet)==true){
                joueMotAux(p, s, nbPointsJet, mot, numLigne, numColonne, sens);
                res = true;
            }else{
                Ut.afficherSL("Le placement voulu n'est pas valide.");
            }
        }else{
            Ut.afficherSL("Le mot voulu n'est pas valide.");
        }
        return res;
    }

    /** pré-requis : cf. joueMot et le placement de mot à partir de la case
     *               (numLig, numCol) dans le sens donné par sens est valide
     *  action : simule le placement d’un mot de this
     */
    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot, int numLigne, int numColonne, char sens) {
        if(joueMot(p, s, nbPointsJet)==true){
            p.place(mot, numLigne, numColonne, sens, this.chevalet);
            this.ajouteScore(p.nbPointsPlacement(mot, numLigne, numColonne, sens, nbPointsJet));
            if(mot.length()==7){
                this.ajouteScore(50);
            }
        }
        if(this.chevalet.getNbTotEx()<7){
            this.prendJetons(s, 7 - this.chevalet.getNbTotEx());
        }
    }

    /**
     * pré-requis : les éléments de sac sont inférieurs à 26
     * action : simule l’échange de jetons de ce joueur
     */
    public void echangeJetons(MEE sac) {
        Ut.afficherSL("Entrez les lettres en majuscule que vous souhaitez échanger."); String chaine = Ut.saisirChaine();
        if(this.estCorrectPourEchange(chaine)){
            this.echangeJetonsAux(sac, chaine);
            Ut.afficherSL("Echange effectué.");
        }else{
            Ut.afficherSL("Veuillez saisir à nouveau les lettres que vous souhaitez échanger.");
            echangeJetons(sac);
        }
    }

    /** résultat : vrai ssi les caractères de mot correspondent tous à des
     *             lettres majuscules et l’ensemble de ces caractères est un
     *             sous-ensemble des jetons du chevalet de this
     */
    public boolean estCorrectPourEchange (String chaine) {
        boolean res = true;
        for(int i=0; i<chaine.length(); i++){
            if(Ut.estUneMajuscule(chaine.charAt(i))==false){
                res=false;
            }
        }
        if(res==false){
            Ut.afficherSL("Une ou plusieurs lettres saisies ne sont pas en majuscule.");
        }
        if(this.chevalet.contient(chaine)){
            res = true;
        }else{
            res = false;
            Ut.afficherSL("Une ou plusieurs lettres saisies ne sont pas présentes dans votre chevalet.");
        }
        return res;
    }

    /** pré-requis : sac peut contenir des entiers de 0 à 25 et ensJetons
     *               est un ensemble de jetons correct pour l’échange
     *  action : simule l’échange de jetons de ensJetons avec des
     *           jetons du sac tirés aléatoirement.
     */
    public void echangeJetonsAux(MEE sac, String ensJetons) {
        for(int i=0; i<ensJetons.length(); i++){
            int lettre = Ut.majToIndex(ensJetons.charAt(i));
            sac.transfereAleat(this.chevalet, 1);
            this.chevalet.transfere(sac, lettre);
        }
    }

}
