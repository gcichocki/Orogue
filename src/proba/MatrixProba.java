package proba;

import java.util.ArrayList;

@SuppressWarnings("unchecked")

/**
 * Created by toon on 21/04/17.
 */
public class MatrixProba {

    private Proba mapProba[][];
    private byte actualMaxProba;
    private int sizeX;
    private int sizeY;
    private int total;

    public MatrixProba(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.total = sizeX*sizeY;

        this.mapProba = new Proba[sizeX][sizeY];
        for(int i=0;i<sizeX;i++){
            for (int j=0; j<sizeY;j++){
                this.mapProba[i][j] = new Proba(i ,j ,(byte) 0);
            }
        }
        this.actualMaxProba = 0;
    }

    public int getSizeX() { return sizeX; }

    public int getSizeY() { return sizeY; }

    public void setActualMaxProba(byte actualMaxProba) { this.actualMaxProba = actualMaxProba; }

    public byte getActualMaxProba() { return actualMaxProba; }

    public Proba[][] getMapProba() { return mapProba; }

    public Proba getProba(int x, int y){ return mapProba[x][y]; }

    public void setProba(int x, int y, byte value){ mapProba[x][y].setValue(value); }

    public ArrayList<Proba> getNeighbours(Proba p){

        ArrayList<Proba> neighbours = new ArrayList<>();

        if(p.getX()!=0)
            neighbours.add(getProba(p.getX()-1, p.getY()));
        if(p.getX()!=sizeX-1)
            neighbours.add(getProba(p.getX()+1, p.getY()));
        if(p.getY()!=0)
            neighbours.add(getProba(p.getX(), p.getY()-1));
        if(p.getY()!=sizeY-1)
            neighbours.add(getProba(p.getX(), p.getY()+1));

        return neighbours;
    }


    /**
     * @param value probas à décrémentées
     * Décrémente toutes les probas égales à celle en paramètre de
     */
    public void updateMapProba(byte value){}


    public boolean isFull(int filled){
        return (total == filled);
    }

    /**
     *
     */
    public void smoothMapProba(){
        /*
        private byte nouvelleMap[x][y]; création d'une seconde map
        Création d'une maxheap pour gérer les probas
        Fill maxheap avec notre map
        while(nouvelleMap !=Full)
            elem = getMaxHeap()
            list = getvoisin(elem)
            foreach(voisin :O)
                if(O n'est pas dans la nouvelle map ou que O<elem) => nouvelleMap(o)=o.value


         */

       Proba newMapProba[][] = new Proba[sizeX][sizeY];

       byte maxValue = 0;
       Proba maxProba;

       MaxHeap maxHeap = new MaxHeap();
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){

                maxHeap.add(this.mapProba[i][j]);
                //newMapProba[i][j].setValue((byte) 0);
            }
        }
        //System.out.println(maxHeap.toString());

       // nb de case rempli dans notre nouvelle matrice
       int filled = 0;

        /*
       while(!isFull(filled)){

           maxProba = (Proba) maxHeap.remove();
           maxValue = maxProba.getValue();
           System.out.println(maxProba);


           ArrayList<Proba> listNeighbours = this.getNeighbours(maxProba);

           for (Proba next : listNeighbours) {


               if (newMapProba[next.getX()][next.getY()].getValue() < next.getValue()
                       || next.getValue() < maxValue){

                   newMapProba[next.getX()][next.getY()].setValue(next.getValue());
                   filled++;
               }
           }

           listNeighbours.clear();

       }

       this.printMatrix();
       this.mapProba = newMapProba;
       this.printMatrix();
       */
    }


    public void printMatrix(){

        String matrix = "";

        for(int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++){

                matrix += this.mapProba[j][i].getValue() + " ";
            }
            matrix += "\n";
        }

        System.out.println(matrix);

    }

}
