package proba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by camil on 28/04/2017.
 */
public class DicoProba {

    private HashMap<Integer, ArrayList<Proba>> dico;

    public DicoProba() {

        this.dico = new HashMap<>();

        for (int i=0; i<10; i++) {
            this.dico.put(i, new ArrayList<>());
        }
    }

    /**
     * add a Probas to the ArrayList of the chosen proba value
     * @param index
     * @param p
     */
    public void put(int index, Proba p){
        this.dico.get(index).add(p);
    }

    /**
     * add a Proba to the dico
     * @param p
     */
    public void add(Proba p){
        int index = p.getValue();
        if(this.dico.get(index).contains(p)){
            this.dico.get(index).remove(p);
        }
        this.dico.get(index).add(p);
    }

    /**
     * returns whether a proba is contained in the dico
     * @param p
     * @return
     */
    public boolean contains(Proba p){
        int index = p.getValue();
        return this.dico.get(index).contains(p);
    }

    /**
     * returns the total number of elements in the dico
     * @return
     */
    public int size(){
        int size = 0;
        for (int i=0; i<10; i++) {
            size += this.getListSize(i);
        }
        return size;
    }

    /**
     * returns the size of a list at a specific proba value
     * @param index
     * @return
     */
    public int getListSize(int index){
        return this.dico.get(index).size();
    }

    /**
     * picks a random Proba inside a list of the specified proba value
     * @param index
     * @return
     */
    private Proba randomPick(int index){
        int size = this.getListSize(index);

        if (size != 0) {
            Random r = new Random();
            int randind = r.nextInt(size);
            //System.out.println();
            return this.dico.get(index).get(randind);
        }
        System.out.println("*** DICOPROBAS : No element in the list " + index + " ***");
        return null;
        /*
        Random r = new Random();
        int Low = 0;
        int High = 10;
        int randind = r.nextInt(High-Low) + Low;
        */
    }


    public Proba pickRandomProba(int lowBound){
        ArrayList<Proba> listRandom = new ArrayList<>();

        for (int i=lowBound; i<10; i++) {
            for (Proba tmp_p : dico.get(i)) {
                if (tmp_p != null) {
                    listRandom.add(tmp_p);
                }
            }

        }
        if (!listRandom.isEmpty()){
            Random r = new Random();
            int randind = r.nextInt(listRandom.size());
           /* System.out.println("random index " + randind);
            System.out.println("Print list " + listRandom.size());
            /*for (Proba p: listRandom) {
                System.out.println("P : " + p);
            }*/
            return listRandom.get(randind);
        }

        System.out.println("***DICOPROBAS : NO PROBA PICK IN RANDOM ***");
        return null;
    }

    /**
     * resets the lists of the dico
     */
    public void reset(){
        for (int i=0; i<10; i++) {
            this.dico.get(i).clear();
        }
    }

    public int getMaxIndex(){
        for (int res = 9; res >=0; res--){
            if(!this.dico.get(res).isEmpty())
                return res;
        }
        return 0;
    }

    public String toString(){
        String print = "";

        for (int i=0; i<10; i++) {
            print += i + " -> ";

            for (Proba p: this.dico.get(i)) {
                print += p + " : ";
            }
            print += "\n";
        }
        return print;
    }

}
