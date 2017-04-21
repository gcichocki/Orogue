package proba;

import java.util.ArrayList;

/**
 * Created by toon on 21/04/17.
 */
public class MatrixProba {

    private Proba mapProba[][];
    private byte actualMaxProba;
    private int sizeX;
    private int sizeY;

    public MatrixProba(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
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

    /**
     *
     */
    public void smoothMapProba(){
        /*
        private byte nouvelleMap[x][y]; création d'un eseconde map
        Création d'une maxheap pour gérer les probas
        Fill maxheap avec notre map
        while(nouvelleMap !=Full)
            elem = getMaxHeap()
            list = getvoisin(elem)
            foreach(voisin :O)
                if(O n'est pas dans la nouvelle map ou que O<elem) => nouvelleMap(o)=o.value


         */
    }


}
