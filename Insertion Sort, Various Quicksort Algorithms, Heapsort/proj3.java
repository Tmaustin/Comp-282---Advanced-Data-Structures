/*
 * Taylor Austin
 * Comp 282 Mon / Wed
 * Programming Assignment #3
 * 4/19/2015
 * Inside this file is a bunch of QuickSort methods with different complexities
 * based on wether it uses a one pointer or two pointer partitioning algorthim, or
 * if it chooses a random pivot or it uses the Left most element, and depending
 * on what size it starts to use insertion sort. Then heap sort is also inside
 * this program using Top-Down intergration or Bottom-Up integration.
 *
 */

class proj3 {

    //QuickSort1: book’s partition, random pivot, easiest case = 20
    public static void QuickSort1(int[] a, int n) {
        //Driver for recusrive moethod
        QuickSort1(a, 0, n - 1);
        InsertionSort(a, n);

    }
    private static void QuickSort1(int[] a, int LF, int RT) {
        while (RT - LF >= 20) { //easiest case 20
            int counter = 0;
            int reversecount = 0;
            int index = LF;
            //Random Pivot
            int pivot = LF + (int)((Math.random() * ((RT - LF) + 1)));
            //while loop to check for duplicates
            while (counter != (RT - LF) && reversecount != 1) {
                if (index < a.length) { //makes sure that index is less
                    //than the length of the array
                    if (a[pivot] == a[index]) {
                        counter++; //increase counter if pivot is 
                        //equal to the element 
                    } else {
                        //increase if there is no duplicate
                        reversecount++;
                    }
                }
                index++; //next element in the array
            }
            //if the counter is the same as the length of secition it is testing
            //and if the RT point doesnt equal the pivot then go inside the
            //if statement
            if (counter == (RT - LF) && RT != pivot) {
                //If Left element is bigger than Right element then swap
                if (a[LF] > a[RT]) {
                    swap(a, LF, RT);
                }
                LF = RT;
                pivot = RT;

                //change the pointers to skip the duplicates        
            }
            //setting pivot to the new pointer that is returned
            pivot = BookPartition(a, LF, RT, pivot);
            if (pivot == a.length + 1) {;
            }
            // left side is smaller
            else if ((pivot - 1) - LF < RT - (1 + pivot)) {
                QuickSort1(a, LF, pivot - 1);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                LF = pivot + 1;
            } else { // right side is smaller
                QuickSort1(a, pivot + 1, RT);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                RT = pivot - 1;

            }

        }
    }

    //QuickSort2: 2 ptr partition, random pivot, easiest case = 20
    public static void QuickSort2(int[] a, int n) {
        //Driver for recusrive moethod
        QuickSort2(a, 0, n - 1);
        InsertionSort(a, n);

    }
    private static void QuickSort2(int[] a, int LF, int RT) {
        while (RT - LF >= 20) { //easiest case 20
            int counter = 0;
            int reversecount = 0;
            int index = LF;
            //Random Pivot
            int pivot = LF + (int)((Math.random() * ((RT - LF) + 1)));
            //while loop to check for duplicates
            while (counter != (RT - LF) && reversecount != 1) {
                if (index < a.length) { //makes sure that index is less
                    //than the length of the array
                    if (a[pivot] == a[index]) {
                        counter++; //increase counter if pivot is 
                        //equal to the element 
                    } else {
                        //increase if there is no duplicate
                        reversecount++;
                    }
                }
                index++; //next element in the array
            }
            //if the counter is the same as the length of secition it is testing
            //and if the RT point doesnt equal the pivot then go inside the
            //if statement
            if (counter == (RT - LF) && RT != pivot) {
                //If Left element is bigger than Right element then swap
                if (a[LF] > a[RT]) {
                    swap(a, LF, RT);
                }
                LF = RT;
                pivot = RT;
                //change the pointers to skip the duplicates        
            }
            //setting pivot to the new pointer that is returned
            pivot = FastPartition(a, LF, RT, pivot);
            if (pivot == a.length + 1) {;
            }
            // left side is smaller
            else if ((pivot - 1) - LF < RT - (1 + pivot)) {
                QuickSort2(a, LF, pivot - 1);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                LF = pivot + 1;
            } else { // right side is smaller
                QuickSort2(a, pivot + 1, RT);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                RT = pivot - 1;

            }

        }
    }

    //QuickSort3: book’s partition, pivot = a[lf], easiest case = 20
    public static void QuickSort3(int[] a, int n) {
        //Driver for recusrive moethod
        QuickSort3(a, 0, n - 1);
        InsertionSort(a, n);

    }
    private static void QuickSort3(int[] a, int LF, int RT) {
        while (RT - LF >= 20) { //easiest case 20
            int counter = 0;
            int reversecount = 0;
            int index = LF;
            //Pivot Is always the Left pointer
            int pivot = LF + (int)((Math.random() * ((RT - LF) + 1)));
            //while loop to check for duplicates
            while (counter != (RT - LF) && reversecount != 1) {
                if (index < a.length) { //makes sure that index is less
                    //than the length of the array
                    if (a[pivot] == a[index]) {
                        counter++; //increase counter if pivot is 
                        //equal to the element 
                    } else {
                        //increase if there is no duplicate
                        reversecount++;
                    }
                }
                index++; //next element in the array
            }
            //if the counter is the same as the length of secition it is testing
            //and if the RT point doesnt equal the pivot then go inside the
            //if statement
            if (counter == (RT - LF) && RT != pivot) {
                //If Left element is bigger than Right element then swap
                if (a[LF] > a[RT]) {
                    swap(a, LF, RT);
                }
                LF = RT;
                pivot = RT;
                //change the pointers to skip the duplicates        
            }
            //setting pivot to the new pointer that is returned
            pivot = BookPartition(a, LF, RT, pivot);
            if (pivot == a.length + 1) {;
            }
            // left side is smaller
            else if ((pivot - 1) - LF < RT - (1 + pivot)) {
                QuickSort3(a, LF, pivot - 1);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                LF = pivot + 1;
            } else { // right side is smaller
                QuickSort3(a, pivot + 1, RT);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                RT = pivot - 1;

            }

        }
    }

    //QuickSort4: 2 ptr partition, random pivot, easiest case = 1
    public static void QuickSort4(int[] a, int n) {
        //Driver for recusrive moethod
        QuickSort4(a, 0, n - 1);

    }
    private static void QuickSort4(int[] a, int LF, int RT) {
        while (RT - LF >= 1) { //easiest case 1
            //Random Pivot
            int counter = 0;
            int reversecount = 0;
            int index = LF;
            //Random Pivot
            int pivotTEST = LF + (int)((Math.random() * ((RT - LF) + 1)));
            //while loop to check for duplicates

            //setting pivot to the new pointer that is returned
            int pivot = FastPartition(a, LF, RT, pivotTEST);
            while (RT - LF > 1 && counter != (RT - LF) && reversecount != 1) {
                if (index < a.length) { //makes sure that index is less
                    //than the length of the array
                    if (a[pivotTEST] == a[index]) {
                        counter++; //increase counter if pivot is 
                        //equal to the element 
                    } else {
                        //increase if there is no duplicate
                        reversecount++;
                    }
                }
                index++; //next element in the array
            }
            //if the counter is the same as the length of secition it is testing
            //and if the RT point doesnt equal the pivot then go inside the
            //if statement
            if (counter == (RT - LF) && RT != pivotTEST) {
                //If Left element is bigger than Right element then swap
                if (a[LF] > a[RT]) {
                    swap(a, LF, RT);
                }
                LF = RT;
                pivot = RT;
                //change the pointers to skip the duplicates        
            }
            // left side is smaller
            if ((pivot - LF < RT - pivot)) {
                QuickSort4(a, LF, pivot - 1);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                LF = pivot;
            } else { // right side is smaller
                QuickSort4(a, pivot, RT);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                RT = pivot - 1;

            }

        }
    }

    //QuickSort5: 2 ptr partition, random pivot, easiest case = 500
    public static void QuickSort5(int[] a, int n) {
        //Driver for recusrive moethod
        QuickSort5(a, 0, n - 1);
        InsertionSort(a, n);

    }
    public static void QuickSort5(int[] a, int LF, int RT) {
        while (RT - LF >= 500) { //easiest case 500
            int counter = 0;
            int reversecount = 0;
            int index = LF;
            //Random Pivot
            int pivot = LF + (int)((Math.random() * ((RT - LF) + 1)));
            //while loop to check for duplicates
            while (counter != (RT - LF) && reversecount != 1) {
                if (index < a.length) { //makes sure that index is less
                    //than the length of the array
                    if (a[pivot] == a[index]) {
                        counter++; //increase counter if pivot is 
                        //equal to the element 
                    } else {
                        //increase if there is no duplicate
                        reversecount++;
                    }
                }
                index++; //next element in the array
            }
            //if the counter is the same as the length of secition it is testing
            //and if the RT point doesnt equal the pivot then go inside the
            //if statement
            if (counter == (RT - LF) && RT != pivot) {
                //If Left element is bigger than Right element then swap
                if (a[LF] > a[RT]) {
                    swap(a, LF, RT);
                }
                LF = RT;
                pivot = RT;
                //change the pointers to skip the duplicates        
            }
            //setting pivot to the new pointer that is returned
            pivot = FastPartition(a, LF, RT, pivot);
            if (pivot == a.length + 1) {;
            }
            // left side is smaller
            else if ((pivot - 1) - LF < RT - (1 + pivot)) {
                QuickSort5(a, LF, pivot - 1);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                LF = pivot + 1;
            } else { // right side is smaller
                QuickSort5(a, pivot + 1, RT);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                RT = pivot - 1;

            }

        }
    }

    //QuickSort6: book’s partition, random pivot, easiest case = 1
    public static void QuickSort6(int[] a, int n) {
        //Driver for recusrive moethod
        QuickSort6(a, 0, n - 1);
    }
    private static void QuickSort6(int[] a, int LF, int RT) {
        while (RT - LF >= 1) {
            int counter = 0;
            int reversecount = 0;
            int index = LF;
            //Random Pivot
            int pivot = LF + (int)((Math.random() * ((RT - LF) + 1)));
            //while loop to check for duplicates
            while (counter != (RT - LF) && reversecount != 1) {
                if (index < a.length) { //makes sure that index is less
                    //than the length of the array
                    if (a[pivot] == a[index]) {
                        counter++; //increase counter if pivot is 
                        //equal to the element 
                    } else {
                        //increase if there is no duplicate
                        reversecount++;
                    }
                }
                index++; //next element in the array
            }
            //if the counter is the same as the length of secition it is testing
            //and if the RT point doesnt equal the pivot then go inside the
            //if statement
            if (counter == (RT - LF) && RT != pivot) {
                //If Left element is bigger than Right element then swap
                if (a[LF] > a[RT]) {
                    swap(a, LF, RT);
                }
                LF = RT;
                pivot = RT;
                //change the pointers to skip the duplicates        
            }
            //setting pivot to the new pointer that is returned
            pivot = BookPartition(a, LF, RT, pivot);
            if (pivot == a.length + 1) {;
            }
            // left side is smaller
            else if ((pivot - 1) - LF < RT - (1 + pivot)) {
                QuickSort6(a, LF, pivot - 1);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                LF = pivot + 1;
            } else { // right side is smaller
                QuickSort6(a, pivot + 1, RT);
                // eliminate the tail recursion by setting the 
                //parameters accordingly
                RT = pivot - 1;

            }

        }
    }

    //Book's Partition OnePointer
    public static int BookPartition(int[] a, int LF, int RT, int p) {
        int pivot = a[p]; //reference pivot
        //Left Element and Pivot swap
        swap(a, LF, p);
        int lastItem = LF; //Last reference Item
        int firstUnknown = LF + 1; //Unknown region
        while (firstUnknown <= RT) {
            //move one item at a time until the unknown region is empty
            if (a[firstUnknown] < pivot) {
                //if the element if less than the pivot swap them
                lastItem++;
                swap(a, firstUnknown, lastItem);
            }
            firstUnknown++;
        }
        //swap the pivot for the last known item
        swap(a, LF, lastItem);

        return lastItem;
    }

    //Two Pointer Partition
    public static int FastPartition(int[] a, int LF, int RT, int p) {
        int pivot = a[p];
        //Run the loop til the values cross
        while (LF <= RT) {
            //increase the Left pointer till you find a Value 
            //greater than the pivot
            while (a[LF] < pivot) {
                LF++;
            }
            //Decrease the Right pointer till you find a Value 
            //smaller than the pivot
            while (a[RT] > pivot) {
                RT--;
            }
            //Swap if the Left count is less than or equal to the Right count
            if (LF <= RT) {
                //swap Left and Right pointer
                swap(a, LF, RT);
                LF++;
                RT--;
            }
        }
        return LF;
    }

    //Insertion Sort
    public static void InsertionSort(int[] a, int n) {

        for (int unsorted = 1; unsorted < n; unsorted++) {
            int nextItem = a[unsorted];
            int index;
            for (index = unsorted; index > 0 &&
                a[index - 1] > nextItem; index--) {
                ////Shift the array to the right
                a[index] = a[index - 1];
                //decriment index
            }
            a[index] = nextItem;
        }
    }

    //Swap 2 elements on each array position
    private static void swap(int[] a, int Start, int End) {
        int temp = a[Start];
        a[Start] = a[End];
        a[End] = temp;
    }

    //Slow Heap Sort
    private static void trickleUp(int a[], int n) {
        int parent = (n - 1) / 2; //Formula for parent of current array spot
        int element = a[n]; //Using the spot get the element
        //Trickle Up until you find the spot for the element
        //Run the while loop til you hit the top or you hit a spot where the 
        //parent is larger than the element
        while (n > 0 && a[parent] < element) {
            //move the element up to the parents spot
            a[n] = a[parent];
            //now n moves up and checking its parent to see if its bigger
            n = parent;
            parent = (parent - 1) / 2;
        }
        //setting the new element to the spot
        a[n] = element;
    }

    //Fast Heap Sort
    private static void trickleDown(int[] a, int n, int index) {
        int BigChild; //Biggest Child
        int Parent = a[index]; //Parent
        Boolean end = false;
        while (index < n / 2 && end != true) { //If node has 2 or more elements
            int LFChild = 2 * index + 1;
            int RTChild = LFChild + 1;
            // Find larger child and is the Right one There?
            if (RTChild < n && a[LFChild] < a[RTChild])
                BigChild = RTChild;
            else
                BigChild = LFChild;
            // Is the parent bigger than its children
            if (Parent >= a[BigChild]) {
                end = true; //end the loop
            } else { //skip the rest of the loop if end is true
                //Shift the child up
                a[index] = a[BigChild];
                //moves the parent down
                index = BigChild;
            }
        }
        a[index] = Parent; //Root->Index
    }

    //Top Down
    public static void HeapSort1(int[] a, int n) {
        //Building the Heap

        for (int index = 1; index < n; index++) {

            trickleUp(a, index);
        }
        //Sort the Heap
        while (n > 1) {
            n--;
            swap(a, 0, n);
            //sort the heap by trickling down
            trickleDown(a, n, 0);
        }
    }

    //Bottom Up
    public static void HeapSort2(int[] a, int n) {
        //Building the heap
        for (int index = ((n / 2) - 1); index >= 0; index--) {
            //build the heap using Bottom Up
            trickleDown(a, n, index);

        }
        //Sort the Heap
        while (n > 1) {
            n--;
            swap(a, 0, n);
            //sort the heap by trickling down
            trickleDown(a, n, 0);
        }

    }

    public static String myName() {
        return "Taylor Austin";
    }
}
