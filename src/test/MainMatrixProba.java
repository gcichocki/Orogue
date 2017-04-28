package test;

import proba.BinaryHeap;
import proba.DicoProba;
import proba.MatrixProba;
import proba.Proba;

@SuppressWarnings("unchecked")

/**
 * Created by camil on 21/04/2017.
 */
public class MainMatrixProba {

    public static void main(String[] args) {

        MatrixProba matx = new MatrixProba(5, 5);

        //matx.printMatrix();

        matx.setProba(2,2, (byte) 9);
        /*matx.setProba(0,4, (byte) 1);
        matx.setProba(3,3, (byte) 9);
        matx.setProba(4,2, (byte) 7);
        matx.setProba(1,3, (byte) 9);
        matx.setProba(4,4, (byte) 8);*/

        //matx.printMatrix();

        //matx.updateMapProba((byte)3);
        matx.printMatrix();

        matx.smoothMapProba();
        matx.printMatrix();
        matx.printDico();

        /*
        matx.printMatrix();
        matx.updateMapProba((byte)9);
        matx.printMatrix();
        matx.updateMapProba((byte)8);
        matx.printMatrix();
        matx.updateMapProba((byte)7);
        matx.printMatrix();
        */

        DicoProba dicotest = new DicoProba();

        /*
        dicotest.reset();
        System.out.println(dicotest);

        dicotest.add(matx.getProba(1,1));
        dicotest.add(matx.getProba(0,4));
        dicotest.add(matx.getProba(3,3));
        System.out.println(dicotest);

        System.out.println(dicotest.getListSize(0));
        System.out.println(dicotest.getListSize(2));
        System.out.println(dicotest.randomPick(2));

        System.out.println(dicotest.contains(matx.getProba(1,1)));
        System.out.println(matx.getProba(1,1));
        System.out.println(dicotest.contains(matx.getProba(4,2)));
        System.out.println();
        */

/*
        BinaryHeap<Proba> maxH = new BinaryHeap<Proba>();
        maxH.add(matx.getProba(1,1));
        maxH.add(matx.getProba(0,4));
        maxH.add(matx.getProba(3,3));
        maxH.add(matx.getProba(4,2));

        System.out.println(maxH.toString());

        maxH.add(matx.getProba(1,3));
        maxH.add(matx.getProba(4,4));

        System.out.println(maxH.toString());

        maxH.remove();

        System.out.println(maxH.toString());
*/


    }
}