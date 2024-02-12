import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {

    //creates an array of specified length filled with random integers
    //parameter arrayLength: the desired size of the array
    //return: returns an integer array of random integers
    public static int[] createRandomArray(int arrayLength){
        int[] array = new int[arrayLength];
        Random rand = new Random();
        for(int i = 0; i < arrayLength; i++){
            array[i] = rand.nextInt(101);
        }
        return array;
    }

    //sorts an array from largest to smallest through merge sort 
    //parameter array: the array to be sorted
    //parameter left: the leftmost index of the array
    //parameter right: the rightmost part of the array, exclusive
    public static void mergeSort(int[] array, int left, int right){
        if(right-left>1){
        int mid = (left+right)/2;
        mergeSort(array, left, mid);
        mergeSort(array, mid , right);
        merge(array, left, mid, right);
        }

    }
    
    //merge sorting with only the array as a parameter
    public static void mergeSort(int[] array){
        mergeSort(array, 0, array.length);
    }

    //merges two parts of an array back into the original array int smallest
    //to largest order
    //parameter array: the full array to be merged into
    //parameter left, mid right: the leftmost, middle, and rightmost indexes
    //of the array to be split into and then merged
    public static void merge(int[] array, int left, int mid, int right){
        int[] leftArray = Arrays.copyOfRange(array, left, mid);
        int[] rightArray = Arrays.copyOfRange(array, mid, right);
        int i = 0, j = 0;
        for(int k = left; k < right; k++){
            if(i == leftArray.length){
                array[k] = rightArray[j++];
            }else if(j == rightArray.length){
                array[k] = leftArray[i++];
            }else if(leftArray[i]<rightArray[j]){
                array[k] = leftArray[i++];
            }else{
                array[k] = rightArray[j++];
            }
        }
    }

    //Uses bubble sorting to sort an array of integers from the smallest to largest number
    //parameter array: the array of integers to be sorted
    //parameter n: the largest index of the array desired to be sorted
    public static void bubbleSort(int[] array, int n){
        //stops if array is null or if there is only one element left
        if(array == null || n < 1){
            return;
        }
        //compares up to the index n
        for(int i = 0; i < n; i++){
            //swaps elements if the left is greater than the right element
            if(array[i] > array[i+1]){
                //swaps array elements
                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
            }
        }
        //recursion to sort through whole array
        bubbleSort(array, n-1);
    }

    //sorts integer array from smallest to largest using bubble sort. No n needed.
    //parameter array: the integer array to be sorted
    public static void bubbleSort(int[] array){
        bubbleSort(array, array.length-1);
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int[] array = null;
        
        boolean valid = false;
        System.out.println("Please input the desired length of the random array:");
        while(!valid){
            try {
                int length = scan.nextInt();
                //catches negative cases so that it doesnt scan next line twice
                if(length >=0){
                    scan.nextLine();
                }
                //creation of random array
                array = createRandomArray(length);
                //prints array to terminal if not too long
                if(array.length<30){
                    System.out.println("Array before sorting:");
                    System.out.println(Arrays.toString(array));
                }
                //will continue to sorting
                valid = true;
            //invalid inputs
            } catch (Exception e) {
                System.out.println("Invalid Input. Please enter a positive number:");
                scan.nextLine();
            }
        }

        valid = false;
        System.out.println("Merge sort (enter 1) ot Bubble sort(enter 2)?:");
        while(!valid){
            String sortType = scan.nextLine();

            //for merge sort
            if(sortType.equals("1")){
                long startTime = System.currentTimeMillis();
                mergeSort(array);
                long endTime = System.currentTimeMillis();
                //calculates time taken sorted
                long timeTaken = endTime-startTime;
                System.out.println("Sorting took "+timeTaken+" milliseconds");
                //moves on
                valid = true;

            //for bubble sort
            }else if(sortType.equals("2")){
                long startTime = System.currentTimeMillis();
                bubbleSort(array);
                long endTime = System.currentTimeMillis();
                //calculates time taken sorted
                long timeTaken = endTime-startTime;
                System.out.println("Sorting took "+timeTaken+" milliseconds");
                //moves on
                valid = true;
            //any other input

            }else{
                System.out.println("Invalid Input. Try again:");
            }
        }

        //prints array if short enough
        if(array.length<30){
            System.out.println("Array after sorting:");
            System.out.println(Arrays.toString(array));
        }
        scan.close();
    }
}
