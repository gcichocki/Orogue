package proba;

/**
 * Created by camil on 21/04/2017.
 */
public class MaxHeap extends BinaryHeap {

    private BinaryHeap<Proba> maxHeap;

    public MaxHeap(){
        this.maxHeap = new BinaryHeap<Proba>();
    }
}
