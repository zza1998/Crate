package com.work.algorithm;

import java.util.Arrays;

public class sortAlgorithm {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 2, 34, 11, 23, 22, 1, 2};
//        selectSort(arr);
//        bubbleSort(arr);
//        insertSort(arr);
//          sellSort(arr);
//        arr = mergeSort(arr);
            quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    /**
     * 丨最好情况 丨最坏情况丨平均情况丨
     * 丨--------丨-------丨-------丨
     * 冒泡排序  丨 O(n)   丨O(n^2) 丨 O(n^2)丨下同
     */
    private static int[] bubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 选择排序  O(n) O(n) O(n)
     */
    private static int[] selectSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int minNumIndex = i;
            // 找出最小数下标
            for (int j = i; j < array.length; j++) {
                minNumIndex = array[j] < array[minNumIndex] ? j : minNumIndex;
            }
            // 把找出最小数与未排序最前端数交换
            int temp = array[i];
            array[i] = array[minNumIndex];
            array[minNumIndex] = temp;
        }
        return array;
    }

    /**
     * 插入排序 O(n) O(n^2) O(n^2)
     */
    private static int[] insertSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            // 对数组进行移动
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            // 找到属于数的正确位置并替换
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     * 希尔排序  O(nlog2 n)  O(nlog2 n)  O(nlog2 n)
     */
    private static int[] sellSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int gap = array.length / 2;
        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {
                int current = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && current < array[preIndex]) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = current;
            }
            gap /= 2;
        }
        return array;
    }

    /**
     * 归并排序  O(n) O(nlog n) O(nlog n)
     *
     * @param array
     * @return
     */
    private static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return Merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 将排序好的两个数组合并排序
     *
     * @param left
     * @param right
     * @return
     */
    private static int[] Merge(int[] left, int[] right) {
        int[] newArr = new int[left.length + right.length];

        for (int index = 0, l = 0, f = 0; index < newArr.length; index++) {
            if (l > left.length - 1) {
                newArr[index] = right[f++];
            } else if (f > right.length - 1) {
                newArr[index] = left[l++];
            } else if (left[l] > right[f]) {
                newArr[index] = right[f++];
            } else {
                newArr[index] = left[l++];
            }

        }
        return newArr;
    }
    /**
     * 快速排序 O(nlogn) O(n^2) O(nlogn)
     * @param arrays
     * @return
     */
    private static int[] quickSort(int[] arrays){
        if (arrays.length==0){
            return arrays;
        }
        quickSort(arrays,0,arrays.length-1);
        return arrays;
    }
    private static void quickSort(int[] arr,int start,int end){

        int mid=arr[start];
        int low=start;
        int high=end;
        if(low>=high)
            return ;
        while(low<high){
            while(arr[high]>=mid&&low<high){
                high--;
            }
            arr[low]=arr[high];
            while(arr[low]<=mid&&low<high){
                low++;
            }
            arr[high]=arr[low];

        }

        arr[low]=mid;
        quickSort(arr,start,low-1);
        quickSort(arr,low+1>end?end:low+1,end);

    }


    /**
     * 堆排序  O(nlog n) O(nlog n) O(nlog n)
     * @param
     * @return
     */
    public static void headSort(int[] list) {
        // 构造初始堆,从第一个非叶子节点开始调整,左右孩子节点中较大的交换到父节点中
        for (int i = (list.length) / 2 - 1; i >= 0; i--) {
            headAdjust(list, list.length, i);
        }
        // 排序，将最大的节点放在堆尾，然后从根节点重新调整
        for (int i = list.length - 1; i >= 1; i--) {
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;
            headAdjust(list, i, 0);
        }
    }

    private static void headAdjust(int[] list, int len, int i) {
        int k = i, temp = list[i], index = 2 * k + 1;
        while (index < len) {
            if (index + 1 < len) {
                if (list[index] < list[index + 1]) {
                    index = index + 1;
                }
            }
            if (list[index] > temp) {
                list[k] = list[index];
                k = index;
                index = 2 * k + 1;
            } else {
                break;
            }
        }
        list[k] = temp;
    }
}

