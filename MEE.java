public class MEE {

    private int[] tabFreq;
    private int nbTotEx;

    /**
     *  pré-requis : max >= 0
     *  action : crée un multi-ensemble vide dont les éléments seront
     *          inférieurs à max
     */
    public MEE (int max){
        if(max>=0){
            this.tabFreq = new int [max];
            this.nbTotEx = 0;
        }
    }


    /**
     *  pré-requis : les éléments de tab sont positifs ou nuls
     *  action : crée un multi-ensemble dont le tableau de fréquences est
     *           une copie de tab
     */
    public MEE(int[] tab){
        this.tabFreq = tab;
        for(int i=0; i<tab.length; i++){
            if(tab[i]>=0){
                this.tabFreq[i] = tab[i];
            }
            else{
                Ut.afficher("Un élément du tableau est inférieur à 0. \n");
            }
        }
        this.nbTotEx = 0;
        for(int i=0; i<this.tabFreq.length; i++){
            if(this.tabFreq[i]>0){
                this.nbTotEx = this.nbTotEx + tabFreq[i];
            }
        }
    }

    /**
     * constructeur par copie
     */
    public MEE(MEE e){
        this.tabFreq = e.tabFreq;
        this.nbTotEx = e.nbTotEx;
    }

    /**
     * Accesseur en lecture de l'attribut nbTotEx (utile pour savoir si un ensemble est vide sans parcourir le tableau).
     * @return this.nbTotEx
     */
    public int getNbTotEx(){
        return this.nbTotEx;
    }

    /**
     *  résultat : vrai ssi cet ensemble est vide
     */
    public boolean estVide(){
        boolean res = true;
        for(int i=0; i<this.tabFreq.length; i++){
            if(this.getNbTotEx()>0){
                res = false;
            }
        }
        return res;
    }

    /**
     * pré-requis : 0 <= i < tabFreq.length
     * action : ajoute un exemplaire de i à this
     */
    public void ajoute(int i){
        if(i<tabFreq.length && i>0){
            this.tabFreq[i]=this.tabFreq[i]+1;
            this.nbTotEx=this.nbTotEx+1;
        }
        else{
            Ut.afficher("L'élément à ajouter n'est pas compris entre 0 et la longueur du tableau. \n");
        }
    }

    /**
     * pré-requis : 0 <= i < tabFreq.length
     * action/résultat : retire un exemplaire de i de this s’il en existe,
     *    et retourne vrai ssi cette action a pu être effectuée
     */
    public boolean retire(int i){
        boolean res = false;
        if(i<tabFreq.length && i>0){
            this.tabFreq[i]=this.tabFreq[i]-1;
            this.nbTotEx=this.nbTotEx-1;
            res=true;
        }
        return res;
    }

    /**
     * pré-requis : this est non vide
     * action/résultat : retire de this un exemplaire choisi aléatoirement
     *                   et le retourne
     */
    public int retireAleat(){
        int i = Ut.randomMinMax(0, this.tabFreq.length-1);
        if(this.estVide()==false){
            if(this.tabFreq[i]>0){
                this.tabFreq[i]=this.tabFreq[i]-1;
                this.nbTotEx=this.nbTotEx-1;
            }
            else{
                retireAleat();
            }
        }
        return i;
    }

    /**
     * pré-requis : 0 <= i < tabFreq.length
     * action/résultat : transfère un exemplaire de i de this vers e s’il
     *     en existe, et retourne vrai ssi cette action a pu être effectuée
     */
    public boolean transfere (MEE e, int i) {
        boolean res = false;
        if(i>0 && i<this.tabFreq.length){
            e.ajoute(i);
            this.retire(i);
            res=true;
        }
        return res;
    }

    /** pré-requis : k >= 0
     *  action : tranfère k exemplaires choisis aléatoirement de this vers e
     *           dans la limite du contenu de this
     */
    public void transfereAleat (MEE e, int k) {
        int i = Ut.randomMinMax(0, this.tabFreq.length-1);
        if(k>=0){
            if(this.tabFreq[i]>k){
                for(int j=0; j<k; j++){
                    e.ajoute(i);
                    this.retire(i);
                }
            }
            else{
                Ut.afficher("L'élément aléatoirement choisi est inférieur au nombre d'éléments que l'on souhaite enlever. \n");
            }
        }
        else{
            Ut.afficher("Le nombre d'exemplaires à retirer est inférieur à 0. \n");
        }
    }

    /**
     * pré-requis : tabFreq.length <= v.length
     * résultat : retourne la somme des valeurs des exemplaires des
     *     éléments de this, la valeur d’un exemplaire d’un élément i
     *     de this étant égale à v[i]
     */
    public int sommeValeurs (int[] v){
        int res = 0;
        if(this.tabFreq.length<=v.length){
            for(int i=0; i<this.tabFreq.length; i++){
                res=res+(this.tabFreq[i]*v[i]);
            }
        }
        return res;
    }
    
}
