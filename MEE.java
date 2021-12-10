public class MEE {

    private int[] tabFreq;
    private int nbTotEx;

    /**
     *  pré-requis : max >= 0
     *  action : crée un multi-ensemble vide dont les éléments seront
     *          inférieurs à max
     */
    public MEE (int max){
        this.tabFreq = new int [max];
        this.nbTotEx = 0;
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
                Ut.afficher("Un élément du tableau est inférieur à 0.");
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
     *  résultat : vrai ssi cet ensemble est vide
     */
    public boolean estVide(){
        boolean res = true;
        for(int i=0; i<this.tabFreq.length; i++){
            if(this.tabFreq[i]>0){
                res = false;
            }
        }
        return res;
    }

    /**
     * pré-requis : 0 <= i < tabFreq.length
     * action : ajoute un exemplaire de i à this
     */
    public void ajoute (int i) {
        if(i<tabFreq.length && i>0){
            
        }
    }

    public int[] getTabFreq(){
        return this.tabFreq;
    }

    public void setTabFreq(int[] tabFreq){
        this.tabFreq = tabFreq;
    }

    public int getNbTotEx(){
        return this.nbTotEx;
    }

    public void setNbTotEx(int nbTotEx){
        this.nbTotEx = nbTotEx;
    }

    
}
