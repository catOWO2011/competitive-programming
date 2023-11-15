package Algorithms.Sorting;

import java.util.Arrays;

class MergeSort {
  public void merge(int[] arr, int left, int middle, int right) {
    int[] leftSection = new int[middle - left + 1];
    int[] rightSection = new int[right - middle];

    for (int i = 0; i < leftSection.length; i++) {
      leftSection[i] = arr[i + left];
    }

    for (int i = 0; i < rightSection.length; i++) {
      // System.out.println(middle + i);
      rightSection[i] = arr[middle + i + 1];
    }

    // System.out.println(left + " , middle = " + middle + " , " + right + "...");
    // System.out.println("Left Section: " + Arrays.toString(leftSection));
    // System.out.println("Rigth Section: " + Arrays.toString(rightSection));

    int rs = 0;
    int ls = 0;
    for (int i = left; i <= right; i++) {
      if (ls < leftSection.length && rs < rightSection.length) {
        if (leftSection[ls] <= rightSection[rs]) {
          arr[i] = leftSection[ls++];
        } else {
          arr[i] = rightSection[rs++];
        }
      } else if (ls < leftSection.length) {
        arr[i] = leftSection[ls++];
      } else if (rs < rightSection.length) {
        arr[i] = rightSection[rs++];
      }
    }
    // System.out.println(Arrays.toString(arr) + " arr");
  }

  public void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      // A[left...mid] [mid + 1, right]
      int middle = (left + right) / 2;
      mergeSort(arr, left, middle);
      mergeSort(arr, middle + 1, right);
      merge(arr, left, middle, right);
    }
  }

  public void solve() {
    int[] arr = new int[] { 7, 1 };

    mergeSort(arr, 0, 1);
    System.out.println(Arrays.toString(arr));

    arr = new int[] { 7, 1, 2 };
    mergeSort(arr, 0, 2);
    System.out.println(Arrays.toString(arr));

    arr = new int[] { 7, 1, 3, 2 };
    mergeSort(arr, 0, 3);
    System.out.println(Arrays.toString(arr));

    arr = new int[] { 7, 1, 3, -1, 3 };
    mergeSort(arr, 0, 4);
    System.out.println(Arrays.toString(arr));
  }

  public static void main(String[] args) {
    new MergeSort().solve();
  }
}