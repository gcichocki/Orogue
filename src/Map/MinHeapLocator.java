package Map;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.floor;

/**
 * Created by tahel on 24/11/16.
 */
public class MinHeapLocator {

    // locator
    HashMap<Tile, Integer> locator;

    // Minheap
    ArrayList<HeapData> heap;

    MinHeapLocator() {
        locator = new HashMap<>();
        heap = new ArrayList<>();
    }

    public boolean contains(Tile t){
        return locator.containsKey(t);
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    private int parent(int pos) {
         return (int) floor((pos - 1) /2);
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private boolean isLeaf(int pos) {
        return pos >= (heap.size()/2) && pos < heap.size();
    }

    private void swap(int pos1, int pos2) {

        // we swap the locator at the same time
        locator.put(heap.get(pos1).tile, pos2);
        locator.put(heap.get(pos2).tile, pos1);

        HeapData tmp = new HeapData(heap.get(pos1).tile, heap.get(pos1).cost);
        heap.set(pos1, heap.get(pos2));
        heap.set(pos2, tmp);
    }


    public void Insert(Tile tile, double cost) {
        if (locator.containsKey(tile)) {
            // we check if the time is smaller
            Update(tile, cost);

        } else {
            heap.add(new HeapData(tile, cost));

            locator.put(tile, heap.size()-1);
            SiftUp(tile, heap.size()-1);
        }
    }

    private void SiftUp(Tile tile, int pos) {

        int current = pos;

        while((current != 0) && (heap.get(current).cost < heap.get(parent(current)).cost)) {
            swap(current, parent(current));
            current = parent(current);
        }
        // we set the locator
        locator.put(tile, current);
    }

    private void SiftDown(int pos) {

        while (!isLeaf(pos)) {
            int j = leftChild(pos);
            if (j < heap.size()-1 && heap.get(j).cost > heap.get(j+1).cost)
                j++; // right child is smaller

            if (heap.get(pos).cost <= heap.get(j).cost) return; // we're done

            swap(pos, j);
            locator.put(heap.get(j).tile, j);
            locator.put(heap.get(pos).tile, pos);
            pos = j;
        }
        locator.put(heap.get(pos).tile, pos);
    }

    private void Update(Tile tile, double cost) {
        // we update
        heap.get(locator.get(tile)).cost = cost;
        //we siftUp
        SiftUp(tile, locator.get(tile));
    }

    public double GetCostTile(Tile tile) {
        if (!locator.containsKey(tile)) {
            return 0d;
        } else {
            return heap.get(locator.get(tile)).cost;
        }
    }
    
    public void printTab() {
        for (Tile t : locator.keySet()
             ) {
            System.out.print(t + " " +  heap.get(locator.get(t)).cost + " ");
        }
        System.out.println();
    }

    public Tile getMin() {
        return heap.get(0).tile;
    }

    public void removeMin() {
        Tile tmp = heap.get(0).tile;

        swap(0, heap.size()-1);

        locator.remove(tmp);

        heap.remove(heap.size()-1);
        if (heap.size() != 0 )
            SiftDown(0);
    }

    /*public static void main(String[] args) {
        MinHeapLocator heap = new MinHeapLocator();

        heap.Insert("A", new Time(20,35,0));
        heap.Insert("B", new Time(10,45,0));
        heap.Insert("C", new Time(8,12,0));
        heap.Insert("D", new Time(23,25,0));
        heap.Insert("E", new Time(2,5,0));

        heap.print();
        heap.printTab();

        System.out.println(heap.getMin());
        heap.removeMin();
        heap.print();
        heap.printTab();

        heap.Update("D", new Time(6, 5, 0));
        System.out.println();
        heap.print();
        heap.printTab();


        heap.Insert("G", new Time(1, 12, 0));
        heap.Insert("F", new Time(23, 12, 0));
        System.out.println();
        heap.print();
        heap.printTab();

    }*/
}
