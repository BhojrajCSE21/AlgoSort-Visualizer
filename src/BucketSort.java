import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {

    double increment = 0.05;

    public void sort(ArrayList<Integer> arr, DrawRect rectArr, Visualizer vis) throws InterruptedException {
        int max = 1100; // assuming max value for visual range
        int min = 265;  // assuming min value for visual range
        int bucketCount = 10;
        
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        
        int range = (max - min) / bucketCount;
        for (int value : arr) {
            int bucketIndex = (value - min) / range;
            if (bucketIndex >= bucketCount) bucketIndex = bucketCount - 1; // handle max value edge case
            buckets.get(bucketIndex).add(value);
        }
        
        // Sort each bucket and merge the buckets back to arr in sorted order
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket, Collections.reverseOrder()); // Sort in descending order for visual effect
            for (int value : bucket) {
                arr.set(index++, value);
                updateVisualization(arr, rectArr, vis);
            }
        }

        vis.comparisons.setText("No. of Comparisons: " + Visualizer.noOfComparisons);
        Visualizer.noOfComparisons = 0;
    }

    private void updateVisualization(ArrayList<Integer> arr, DrawRect rectArr, Visualizer vis) throws InterruptedException {
        Thread.sleep(30);
        rectArr.setArr(arr);
        rectArr.paintImmediately(0, 230, Visualizer.WIDTH, 900);
        vis.setSize(Visualizer.WIDTH, (int) (Visualizer.HEIGHT + increment));
        increment = -increment;
    }
}
