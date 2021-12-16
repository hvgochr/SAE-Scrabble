public class MEE{

    private int[] tabFreq;
    private int nbTotEx;

    /**
     * Constructeur MEE
     * Pré-requis: max >= O
     * Action: Crée un multi-ensemble MEE vide dont les éléments sont inférieurs à max
     * @param max
     */
    public MEE (int max){  
        this.tabFreq = new int [max];
        this.nbTotEx = 0;   
    }

    /**
     * Constructeur MEE
     * Pré-requis: les éléments de tab sont positifs ou nuls
     * Action: Crée un multi-ensemble MEE dont le tableau de fréquences est une copie de tab
     * @param tab
     */
    public MEE(int[] tab){
        for(int i=0; i<tab.length; i++){
            this.tabFreq[i] = tab[i];
        }
        this.nbTotEx = 0;
        for(int i=0; i<this.tabFreq.length; i++){
            this.nbTotEx = this.nbTotEx + tabFreq[i];
        }
    }

    /**
     * Constructeur MEE
     * Action: Crée un multi-ensemble MEE qui est une copie du multi-ensemble passé en paramètre.
     * @param e
     */
    public MEE(MEE e){
        this.tabFreq = e.tabFreq;
        this.nbTotEx = e.nbTotEx;
    }

    /**
     * Accesseur en lecture de l'attribut nbTotEx (utile pour savoir si un ensemble est vide sans parcourir le tableau).
     * @return un entier this.nbTotEx
     */
    public int getNbTotEx(){
        return this.nbTotEx;
    }

    /**
     * Méthode estVide
     * Action: Parcourt le tableau du multi-ensemble this et retourne vrai si l'ensemble est vide.
     * @return un booléen res
     */
    public boolean estVide(){
        boolean res = false;
        if(this.nbTotEx==0){
            res = true;
        }
        return res;
    }

    /**
     * Méthode ajoute
     * Pré-requis: 0 <= i < tabFreq.length
     * Action: Ajoute un exemplaire de i à l'ensemble this
     * @param i
     * @return void
     */
    public void ajoute(int i){
        this.tabFreq[i]=this.tabFreq[i]+1;
        this.nbTotEx=this.nbTotEx+1;
    }

    /**
     * Méthode retire
     * Pré-requis: 0 <= i < tabFreq.length
     * Action: Retire un exemplaire de i de this s'il en existe un, et retourne vrai si cette action a pu être effectuée
     * @param i
     * @return un booléen res
     */
    public boolean retire(int i){
        boolean res = false;
        for(int j=0; j<this.nbTotEx-1; j++){
            if(this.tabFreq[j]==i){
                this.tabFreq[j]=this.tabFreq[j]-1;
                this.nbTotEx=this.nbTotEx-1;
                res=true;
            }
        }
        return res;
    }

    /**
     * Méthode retireAleat
     * Pré-requis: this est non vide
     * Action: Retire un exemplaire de this choisi aléatoirement et le retourne
     * @return un entier i
     */
    public int retireAleat(){
        int i = Ut.randomMinMax(0, this.tabFreq.length-1);
        int res = this.tabFreq[i];
        this.retire(i);
        return res;
    }

    /**
     * Méthode transfère
     * Pré-requis: 0 <= i < tabFreq.length
     * Action: Transfère un exemplaire de i de this vers e, s'il en existe, et retourne vrai si cette action a pu être effectuée
     * @param e 
     * @param i 
     * @return un booléen res
     */
    public boolean transfere (MEE e, int i) {
        boolean res = false;
        for(int j=0; j<tabFreq.length; j++){
            if(this.tabFreq[j]==i){
                this.retire(i);
                e.ajoute(i);
                res=true;
            }
        }
        return res;
    }

    /**
     * Méthode transfereAleat
     * Pré-requis: k >= 0
     * Action: transfère k exemplaires choisis aléatoirement de this vers e dans la limite du contenu de this
     * @param e
     * @param k
     * @return un entier res
     */
    public int transfereAleat (MEE e, int k) {
        for(int j=k; j>0; j--){
            int i = Ut.randomMinMax(0, this.tabFreq.length-1);
            this.transfere(e, i);
        }
        return k;
    }

    /**
     * Méthode sommeValeurs
     * Pré-requis: tabFreq.length <= v.length
     * Action: retourne la somme des valeurs des exemplaires des éléments de this, la valeur d'un exemplaire d'un élément de this étant égal à v[i]
     * @param v
     * @return un entier res
     */
    public int sommeValeurs (int[] v){
        int res = 0;
        for(int i=0; i<this.tabFreq.length; i++){
            res=res+(this.tabFreq[i]*v[i]);
        }
        return res;
    }
}