package proba;

import map.Tuple;

import java.util.ArrayList;

@SuppressWarnings("unchecked")

/**
 * Created by toon on 21/04/17.
 */
public class MatrixProba {

    private Proba mapProba[][];
    private DicoProba dicoProba;
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
        this.dicoProba = new DicoProba();
    }

    public int getSizeX() { return sizeX; }

    public int getSizeY() { return sizeY; }

    public Proba[][] getMapProba() { return mapProba; }

    public Proba getProba(int x, int y){ return mapProba[x][y]; }

    public void setProba(int x, int y, byte value){ mapProba[x][y].setValue(value); }

    /**
     * generate a list of the adjacent neighbours of
     * a given Proba p
     * @param p
     * @return
     */
    private ArrayList<Proba> getNeighbours(Proba p){

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
     * resets all the values in the matrix to 0
     */
    public void resetMapProba(){
        for(int i=0;i<sizeX;i++){
            for (int j=0; j<sizeY;j++){
                this.mapProba[i][j].setValue((byte) 0);
            }
        }
    }

    /**
     * @param value probas à décrémentées
     * Décrémente toutes les probas égales à celle en paramètre de
     */
    public void updateMapProba(byte value){
        for(int i=0;i<sizeX;i++){
            for (int j=0; j<sizeY;j++){
                if(this.mapProba[i][j].getValue()==value)
                    this.mapProba[i][j].setValue((byte) (value - 1));
            }
        }
    }


    private boolean isFull(int filled){
        return (total == filled);
    }


    /**
     * smoothing technique on a matrix
     */
    public void smoothMapProba(){
        Proba newMapProba[][] = new Proba[sizeX][sizeY];
        Proba maxProba;

        ArrayList<Proba> neighbours;
        this.dicoProba.reset();

        BinaryHeap<Proba> maxHeap = new BinaryHeap<>(false);
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                newMapProba[i][j]= new Proba(i, j, (byte) 0);
                maxHeap.add(this.mapProba[i][j]);
            }
        }
        // nb de case rempli dans notre nouvelle matrice
        //int filled = 0;

        while(!maxHeap.isEmpty()){ //!isFull(filled) &&
            maxProba = maxHeap.remove();

            if(newMapProba[maxProba.getX()][maxProba.getY()].getValue() <= maxProba.getValue()){
                newMapProba[maxProba.getX()][maxProba.getY()].setValue(maxProba.getValue());

                // add the Proba to the dictionary
                if (!this.dicoProba.contains(maxProba)) {
                    this.dicoProba.add(maxProba);
                }
                //filled++;

                neighbours = this.getNeighbours(maxProba);
                byte newValue = (byte)(maxProba.getValue()-1);
                for (Proba p: neighbours) {
                    if(newValue >  mapProba[p.getX()][p.getY()].getValue() && newValue > newMapProba[p.getX()][p.getY()].getValue()) {
                        newMapProba[p.getX()][p.getY()].setValue(newValue);
                        p.setValue(newValue);
                        maxHeap.add(newMapProba[p.getX()][p.getY()]);
                    }
                }
            }
        }

        //this.printMatrix();
        this.mapProba = newMapProba;
        //this.printMatrix();
    }


    public void getListMaxProba(ArrayList<Proba> listProba) {

        for(int i=0;i<sizeX;i++){
            for (int j=0; j<sizeY;j++){

                this.mapProba[i][j] = new Proba(i ,j ,(byte) 0);
            }
        }
    }

    public void setListMaxProba(ArrayList<Proba> listProba) {

        for (Proba p: listProba) {
            if (p.getValue() > this.mapProba[p.getX()][p.getY()].getValue()){
                this.mapProba[p.getX()][p.getY()].setValue(p.getValue());
                dicoProba.add(p);
            }
        }
    }

    /**
     * return a Proba from the dico with a value
     * no lower than lowBound
     * @param lowBound
     * @return
     */
    public Proba pickProba(int lowBound){
        if (lowBound < 0){
            resetMapProba();
            lowBound = 0;
        }
        return this.dicoProba.pickRandomProba(lowBound);
    }

    public String toString(){
        String matrix = "";

        for(int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++){

                matrix += this.mapProba[j][i].getValue() + " ";
            }
            matrix += "\n";
        }
        return matrix;
    }

    public void resetArrayOfProba(ArrayList<Tuple<Integer, Integer>> list){
        Proba p ;
        for (Tuple t : list) {
            mapProba[(int) t.x][(int) t.y].setValue((byte) 0);
            dicoProba.add(getProba((int) t.x, (int) t.y));
        }
    }

    /**
     * prints a matrix with its side
     */
    public void printMatrix(){
        System.out.println("SIZE MATRIX : " + this.sizeX + "x" + this.sizeY + " (" + this.sizeX*this.sizeY + " cases)");
        System.out.println(this.toString());
    }

    /**
     * prints a dico with its number of elements
     */
    public void printDico(){
        System.out.println("SIZE : " + this.dicoProba.size());
        System.out.println(this.dicoProba);
    }


}
