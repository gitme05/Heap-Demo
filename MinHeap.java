import java.util.*;

public class MinHeap {
    private List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp();
    }

    public int removeMin() {
        if (heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (heap.size() > 0) {
            heap.set(0, last);
            heapifyDown();
        }
        return min;
    }

    private void heapifyUp() {
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) < heap.get(parentIndex)) {
                Collections.swap(heap, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown() {
        int index = 0;
        int leftChild, rightChild, smallestChild;
        while (index < heap.size()) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            smallestChild = index;

            if (leftChild < heap.size() && heap.get(leftChild) < heap.get(smallestChild)) {
                smallestChild = leftChild;
            }

            if (rightChild < heap.size() && heap.get(rightChild) < heap.get(smallestChild)) {
                smallestChild = rightChild;
            }

            if (smallestChild == index) {
                break;
            }

            Collections.swap(heap, index, smallestChild);
            index = smallestChild;
        }
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(30);
        minHeap.insert(3);
        minHeap.insert(15);

        System.out.println("Heap after insertions: ");
        minHeap.printHeap();

        System.out.println("Removing minimum value: " + minHeap.removeMin());
        minHeap.printHeap();
    }
}
