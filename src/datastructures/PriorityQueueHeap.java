package datastructures;

import model.Order;
import java.util.ArrayList;

public class PriorityQueueHeap {

    private ArrayList<Order> heap;

    public PriorityQueueHeap() {
        heap = new ArrayList<>();
    }

    public void enqueue(Order order) {
        heap.add(order);
        heapifyUp(heap.size() - 1);
    }

    public Order dequeue() {
        if (heap.isEmpty()) return null;
        Order top = heap.get(0);
        Order last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return top;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && compare(heap.get(left), heap.get(largest)) > 0) largest = left;
            if (right < size && compare(heap.get(right), heap.get(largest)) > 0) largest = right;

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else break;
        }
    }

    private int compare(Order o1, Order o2) {
        // Express orders have higher priority
        if (o1.isExpress() && !o2.isExpress()) return 1;
        if (!o1.isExpress() && o2.isExpress()) return -1;

        // If same type, smaller orderId gets priority
        return o2.getOrderId() - o1.getOrderId();
    }

    private void swap(int i, int j) {
        Order temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void display() {
        if (heap.isEmpty()) {
            System.out.println("Heap is empty.");
            return;
        }
        System.out.println("Current Priority Queue (Heap):");
        for (Order o : heap) {
            System.out.println("OrderID: " + o.getOrderId() + ", UserID: " + o.getUserId() +
                    ", Total: " + o.getTotal() + ", Express: " + o.isExpress());
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }
}
