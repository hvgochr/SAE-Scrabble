public class Case {

    private int couleur;
    private boolean recouverte;
    private char lettre;
    
    /**
     * pré-requis : uneCouleur est un entier entre 1 et 5
     * action : constructeur de Case
     */
    public Case (int uneCouleur){
        if(uneCouleur>=1 && uneCouleur<=5){
            this.couleur=uneCouleur;
        }
    }

    /**
     * résultat : la couleur de this, un nombre entre 1 et 5
     */
    public int getCouleur(){
        return this.couleur;
    }

    /**
     * pré-requis : cette case est recouverte
     */
    public char getLettre(){
        //if(la case.estRecouverte){
            return this.lettre;
        //}
    }

    /**
     * pré-requis : let est une lettre majuscule
     */
    public void setLettre(char let){
        if(Character.isUpperCase(let)){
            this.lettre = let;
        }
    }

    /**
     * résultat : vrai ssi la case est recouverte par une lettre
     */
    public boolean estRecouverte (){
        boolean res = false;
        //if(la case est recouverte){
            return res;
        //}
    }

    public String toString (){
        String res = "";
        //faire la méthode toString.
        return res;
    }

}
