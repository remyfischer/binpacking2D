/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacking;
import java.util.*;

/**
 *
 * @author remy.fischer
 */
public class BinPacking {
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        Scanner sc = new Scanner(System.in);

        int tailleX;
        int tailleY;
        int maxDimItemX, maxDimItemY;
        int nbSousConteneur;
        double firstfit;
        double firstfitDecreasing;

        TabItem tabItem = new TabItem();

        // int tailleY, tailleZ;


       /* System.out.println("Veuillez saisir une taille X pour l'avion");
        tailleX = sc.nextInt();
        System.out.println("Veuillez saisir une taille Y pour l'avion");
        tailleY = sc.nextInt();


        tabItem.initTabItem();
        tabItem.afficherContenu();

        Conteneur avion = new Conteneur(tailleX, tailleY);

        avion.init();
        avion.afficherContenu();

        maxDimItemX = tabItem.getPlusGrandeDimensionX();
        maxDimItemY = tabItem.getPlusGrandeDimensionY();
        
        avion.afficherID();
        
        
        // Il y a un problème car avion.split() renvoie le nombre de conteneurs +1 à chaque fois, d'où le -1.
        nbSousConteneur = avion.split(maxDimItemX, maxDimItemY) - 1;
        
        avion.afficherID(); */
        
        // TEST
        
        Conteneur avionTest = new Conteneur(5,5);
        TabItem tabItemTest = new TabItem();
        
        avionTest.init();
        
        Item item1 = new Item(3,2);
        Item item2 = new Item(2,1);
        Item item3 = new Item(3,3);
        Item item4 = new Item(1,2);
        Item item5 = new Item(1,1);
        Item item6 = new Item(1,3);
        Item item7 = new Item(2,2);
        Item item8 = new Item(3,1);
        
        tabItemTest.AjoutItem(item1);
        tabItemTest.AjoutItem(item2);
        tabItemTest.AjoutItem(item3);
        tabItemTest.AjoutItem(item4);
        tabItemTest.AjoutItem(item5);
        tabItemTest.AjoutItem(item6);
        tabItemTest.AjoutItem(item7);
        tabItemTest.AjoutItem(item8);
        

        for (int i = 0 ; i < tabItemTest.getNbItem() ; i++){
            
            System.out.println("id item "+i+ " : "+tabItemTest.getItem(i).getID());
            
        }
        
        maxDimItemX = tabItemTest.getPlusGrandeDimensionX();
        maxDimItemY = tabItemTest.getPlusGrandeDimensionY();
        
        System.out.println("maxDimX = "+ maxDimItemX + "maxDimY = "+maxDimItemY);
        
        nbSousConteneur = avionTest.split(maxDimItemX, maxDimItemY);
        
        avionTest.afficherID();
        
        avionTest.afficherValues();
        
        firstfit=firstFit(tabItemTest, avionTest, nbSousConteneur);
        
        avionTest.afficherValues();
        
        // END TEST
        
        
        
        /*
        
        Conteneur cFirstFit = new Conteneur();
        
        cFirstFit.clone(avion);
        
        Conteneur cFirstFitD = new Conteneur();
        
        cFirstFitD.clone(avion);
        
        firstfit = firstFit(tabItem, cFirstFit, nbSousConteneur);
        
        cFirstFit.afficherValues();
        
        */
        
        
        /*
        firstfit = firstFit(tabItem, cFirstFit, nbSousConteneur);
        firstfitDecreasing = firstFitDecreasing(tabItem, cFirstFitD, nbSousConteneur);
        
        cFirstFit.afficherContenu();
        cFirstFitD.afficherContenu();
        
        System.out.println("Methode First Fit : "+firstfit+"% de remplissage");
        System.out.println("Methode First Fit Decreasing : "+firstfitDecreasing+"% de remplissage");
        
        if (firstfit >= firstfitDecreasing){
            
            System.out.println("La solution firstfit est la plus optimisée, voici le résultat");
            avion.clone(cFirstFit);
            avion.afficherContenu();
            
        }
        
        if (firstfit < firstfitDecreasing){
            
            System.out.println("La solution firstfit decreasing est la plus optimisée, voici le résultat");
            avion.clone(cFirstFitD);
            avion.afficherContenu();
        }
        
        
        //firstfit = firstFit(tabItem, avion, nbSousConteneur);
        
        //avion.afficherContenu();*/
    
    
    
        
    }
    
    public static double firstFit(TabItem _tabItem, Conteneur _conteneur, int _nbSousConteneur){
        
        int compteurX;
        int compteurY;
        boolean sousConteneurLibre;
        int cptSousConteneur;
        int numConteneur;
        double remplissage = 0;
        int indiceX;
        int indiceY;
        boolean _indiceX;
        
        
        // On parcours le tableau contenant tous les item pour les places un par un
        for(int k = 0 ; k < _tabItem.getNbItem() ; k++){
            
            // Initialisation des variables qui vont être vérifiées par la suite
            sousConteneurLibre = false;
            cptSousConteneur = 1;
            numConteneur = 0;
            
            /* Boucle vérifiant si un sous conteneur est libre pour un item donné (on sort de cette boucle si on trouve un conteneur ou si on a vérifié 
             tous les conteneurs. */
            
            do{
                // Initialisation des variables pour se placer au début du conteneur
                
                compteurX = 0;
                compteurY = 0;
                indiceX = 0;
                indiceY = 0;
                int valeurPrec = 0;
                int sortieRoutine=0;
                
                // Boucle permettant de tourner dans le sous conteneur dans les valeurs X
                for(int i = 0 ; i < _conteneur.getTailleX() ; i++){
                    
                    compteurY=0;
                    _indiceX=false;
                    
                    // Boucle permettant de tourner dans le sous conteneur dans les valeurs Y
                    for(int j = 0 ; j < _conteneur.getTailleY() ; j++){
                        
                        //if(sousConteneurLibre == true) break;
                        
                        if(sortieRoutine != 1 ){
                            
                            System.out.println(_conteneur.getID(i, j)+" "+cptSousConteneur+" "+_conteneur.getXY(i,j));
                        
                            if(_conteneur.getID(i,j) == cptSousConteneur && _conteneur.getXY(i, j) == -1){

                                compteurY++;
                                valeurPrec = 1;

                            } else if (sortieRoutine != 1) indiceY=j+1;

                            if (valeurPrec == 0) compteurY=0;

                            if(compteurY == _tabItem.getItem(k).getTailleY()){

                                compteurX++;
                                _indiceX = true;

                            }

                            if(compteurX == _tabItem.getItem(k).getTailleX()){

                                sousConteneurLibre=true;

                            }

                            if (sousConteneurLibre == true ) sortieRoutine = 1;
                            
                        }

                           
                        
                        /*if(_conteneur.getID(i, j) == cptSousConteneur && _conteneur.getXY(i, j) == -1) compteurY++;
                        else {
                            
                            compteurY = 0;
                            indiceY = j+1;
                            
                        }
                        if(compteurY == _tabItem.getItem(k).getTailleY()){
                            
                            compteurX++;
                            _indiceX = true;
                            //break;
                            
                        }
                        if(compteurX == _tabItem.getItem(k).getTailleX()){
                            
                            sousConteneurLibre = true;
                            
                        }*/
                        
                    }
                    
                    if(_indiceX != true && sortieRoutine != 1) indiceX=i+1;
                    
                    //if(_indiceX != true) indiceX = i+1;
                    //if(sousConteneurLibre == true) break;
                    
                    
                }
                
                if(sousConteneurLibre == true) break;
                cptSousConteneur++;
                
            } while(sousConteneurLibre = false && cptSousConteneur <= _nbSousConteneur);
            
            if(sousConteneurLibre = true){
                
                compteurX = 0;
                compteurY = 0;
                
                
                
                for (int i = 0 ; i < _conteneur.getTailleX() ; i++){
                    
                    for (int j = 0 ; j < _conteneur.getTailleY() ; j++){
                        
                        if(_conteneur.getID(i, j) == cptSousConteneur && _conteneur.getXY(i, j) == -1 ){
                            
                            _conteneur.setXY(i, j, _tabItem.getItem(k).getID());
                            _conteneur.afficherValues();
                            compteurY++;
                            
                        }
                        if(compteurY == _tabItem.getItem(k).getTailleY()){
                            
                            compteurX++;
                            break;
                        }
                        
                    }
                    if(compteurX == _tabItem.getItem(k).getTailleX()) break;
                    
                    
                }
                
                /*for(int i = indiceX ; i < indiceX + _tabItem.getItem(k).getTailleX() ; i++){
                    
                    for(int j = indiceY ; j < indiceY + _tabItem.getItem(k).getTailleY() ; j++){
                        
                        if(i >= _conteneur.getTailleX() || j >= _conteneur.getTailleY()) break;
                        _conteneur.setXY(i, j, _tabItem.getItem(k).getID());
                        
                    }
                    
                }*/
                
            }
            
            
        }
        
        for(int i = 0 ; i < _conteneur.getTailleX() ; i++){
            
            for(int j = 0 ; j < _conteneur.getTailleY() ; j++){
                
                if(_conteneur.getXY(i, j) != -1) remplissage++;
                
            }
            
        }
        
        remplissage = (remplissage/(_conteneur.getTailleX()*_conteneur.getTailleY()))*100;
        
        return remplissage;
        
    }
    
    /*public static double firstFit(TabItem _tabItem, Conteneur _conteneur, int _nbSousConteneur){
        
        int compteur;
        boolean sousConteneurLibre;
        int cptSousConteneur;
        int i;
        int numConteneur;
        double remplissage = 0;
       
        for(int k = 0 ; k < _tabItem.getNbItem() ; k++){
           
           sousConteneurLibre = false;
           cptSousConteneur = 1;
           numConteneur = 0;
           i = 0;
           
            do{

                compteur = 0;
                if ( i >= _conteneur.getTailleX()) break;
                
                do{
                    
                    //if ( i >= _conteneur.getTailleX()) break;
                    if(_conteneur.getX(i)== -1) compteur++;
                    i++;
                    if ( i >= _conteneur.getTailleX()) break;
                    
                } while (_conteneur.getID(i) == cptSousConteneur);

                if (compteur >= (_tabItem.getItem(k).getTailleX())){ 

                    sousConteneurLibre = true;
                    numConteneur = cptSousConteneur;

                }

                cptSousConteneur++;

            } while(sousConteneurLibre == false && cptSousConteneur <= _nbSousConteneur);

            if (sousConteneurLibre == true ){

                i=0;


                while (_conteneur.getID(i) != numConteneur || _conteneur.getX(i) != -1 ){
                    
                    i++;

                }

                for(int j = i ; j < _tabItem.getItem(k).getTailleX()+i ; j++){

                    _conteneur.setX(j, _tabItem.getItem(k).getID());

                }


            }
        }
        
        for (i = 0 ; i < _conteneur.getTailleX() ; i++){
            
            if (_conteneur.getX(i) != -1) remplissage++;
            
        }
        
        remplissage = (remplissage/_conteneur.getTailleX())*100;
        return remplissage;
        
    
    
    }
    
    public static double firstFitDecreasing(TabItem _tabItem, Conteneur _conteneur, int _nbSousConteneur){
        
        double remplissage;
        
        _tabItem.triSelectionDecroissant();
        remplissage = firstFit(_tabItem, _conteneur, _nbSousConteneur);
        
        return remplissage;
        
    }*/
    
}
