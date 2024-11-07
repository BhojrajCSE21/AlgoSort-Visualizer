import java.util.ArrayList;
import java.util.Collections;

public class QuickSort {

    double increment = 0.05;

    public void sort(ArrayList<Integer> arr, DrawRect rectArr, Visualizer vis) throws InterruptedException {
        quickSort(arr, 0, arr.size() - 1, rectArr, vis);
        vis.comparisons.setText("No. of Comparisons: " + Visualizer.noOfComparisons);
        Visualizer.noOfComparisons = 0;
    }

    private void quickSort(ArrayList<Integer> arr, int low, int high, DrawRect rectArr, Visualizer vis) throws InterruptedException {
        if (low < high) {
            int pi = partition(arr, low, high, rectArr, vis);
            quickSort(arr, low, pi - 1, rectArr, vis);
            quickSort(arr, pi + 1, high, rectArr, vis);
        }
    }

    private int partition(ArrayList<Integer> arr, int low, int high, DrawRect rectArr, Visualizer vis) throws InterruptedException {
        int pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            Visualizer.noOfComparisons++;
            if (arr.get(j) < pivot) { // Corrected to sort in ascending order
                i++;
                Collections.swap(arr, i, j);
                updateVisualization(arr, rectArr, vis);
            }
        }
        Collections.swap(arr, i + 1, high);
        updateVisualization(arr, rectArr, vis);
        return i + 1;
    }

    private void updateVisualization(ArrayList<Integer> arr, DrawRect rectArr, Visualizer vis) throws InterruptedException {
        Thread.sleep(30);
        rectArr.setArr(arr);
        rectArr.paintImmediately(0, 230, Visualizer.WIDTH, 900);
        vis.setSize(Visualizer.WIDTH, (int) (Visualizer.HEIGHT + increment));
        increment = -increment;
    }
}
