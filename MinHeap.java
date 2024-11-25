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
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
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
        System.out.println("Current Heap: " + heap);
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Min-Heap Application!");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert a value");
            System.out.println("2. Remove the minimum value");
            System.out.println("3. Display the heap");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a value to insert: ");
                    int value = scanner.nextInt();
                    minHeap.insert(value);
                    System.out.println(value + " has been added to the heap.");
                    break;

                case 2:
                    try {
                        int minValue = minHeap.removeMin();
                        System.out.println("Removed the minimum value: " + minValue);
                    } catch (NoSuchElementException e) {
                        System.out.println("The heap is empty.");
                    }
                    break;

                case 3:
                    minHeap.printHeap();
                    break;

                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
