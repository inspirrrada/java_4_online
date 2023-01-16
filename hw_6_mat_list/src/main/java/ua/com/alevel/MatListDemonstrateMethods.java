package ua.com.alevel;

import java.util.Arrays;

public class MatListDemonstrateMethods {
    MatList matList;

    public void start() {
        //demonstrate constructors
        create();
        System.out.println();
        addOneElement();
        addOneElementToTheExactgIndex();
        addFewElements();
        System.out.println();
        //demonstrate join and intersection
        joinFewMatlists();
        joinAndLeaveOnlyCommonElements();
        System.out.println();
        //demonstrate max and min
        getElementByIndex();
        getMax();
        getMin();
        System.out.println();
        //demonstrate average and median
        getAverage();
        getMedianForOddQty();
        getMedianForEvenQty();
        System.out.println();
        //demonstrate toArray methods
        toArrayAllMatList();
        toArrayPartOfMatList();
        System.out.println();
        //demonstrate cut
        cutBetweenIndexesIncludingIndexes();
        System.out.println();
        //demonstrate clear
        clearValuesOfMatList();
        System.out.println();
        clearSeparateValuesinMatlist();
        //demonstrate sorting methods
        sortDesc();
        System.out.println();
        sortDescBetweenIndexes();
        System.out.println();
        sortDescStartFromExactValue();
        System.out.println();
        sortAsc();
        System.out.println();
        sortAscBetweenIndexes();
        System.out.println();
        sortAscStartFromExactValue();
        System.out.println();
    }

    public void create() {
        matList = new MatList<>();
        System.out.println("Create MatList with constructor MatList(): ");
        System.out.println(matList.toString());
        Integer[] array1 = new Integer[] {1, 2, 3, 4, 5};
        Integer[] array2 = new Integer[] {6, 7, 8, 9, 10};
        matList = new MatList<>(array1, array2);
        System.out.println("Create MatList with constructor MatList(E[] ... numbers): ");
        System.out.println(matList.toString());
        Integer[] array3 = new Integer[] {11, 12, 13, 14, 15};
        Integer[] array4 = new Integer[] {16, 17, 18, 19, 20};
        MatList matList1 = new MatList<>(array3);
        MatList matList2 = new MatList<>(array4);
        matList = new MatList<>(matList1, matList2);
        System.out.println("Create MatList with constructor MatList(MatList ... numbers): ");
        System.out.println(matList.toString());
    }

    public void addOneElement() {
        matList.add(21);
        System.out.println("MatList after add one element: ");
        System.out.println(matList.toString());
    }

    public void addOneElementToTheExactgIndex() {
        matList.add(5, 10000);
        System.out.println("MatList after add one element to the exact index: ");
        System.out.println(matList.toString());
    }

    public void addFewElements() {
        matList.add(22, 23, 24, 25);
        System.out.println("MatList after add few elements at once: ");
        System.out.println(matList.toString());
    }

    public void joinFewMatlists() {
        Integer[] array1 = new Integer[] {26, 27, 28, 29, 30};
        Integer[] array2 = new Integer[] {31, 32, 33, 34, 35};
        MatList matList1 = new MatList<>(array1);
        MatList matList2 = new MatList<>(array2);
        matList.join(matList1, matList2);
        System.out.println("MatList after join few MatLists: ");
        System.out.println(matList.toString());
    }

    public void joinAndLeaveOnlyCommonElements() {
        Integer[] array1 = new Integer[] {5, 10, 15, 20, 25}; //common 15, 20, 25
        Integer[] array2 = new Integer[] {30, 35, 40, 45, 50}; //common 30, 35
        MatList matList1 = new MatList<>(array1);
        MatList matList2 = new MatList<>(array2);
        matList.intersection(matList1, matList2);
        System.out.println("MatList after join and leave only common elements from few MatLists: ");
        System.out.println(matList.toString());
    }

    public MatList createMatListForSorting() {
        Integer[] array = new Integer[] {5, 0, 10, 15, 19, 18, 17, 23, 20, 25, 32, 31, 30, 34, 33, 35, 40, 26, 27, 29, 45, 28, 16, 10000, 50};
        MatList matListUnsorted = new MatList(array);
        System.out.println("MatList unsorted before: ");
        System.out.println(matListUnsorted);
        return matListUnsorted;
    }

    public void sortDesc() {
        MatList matListUnsorted = createMatListForSorting();
        matListUnsorted.sortDesc();
        System.out.println("MatList after sorting in descending order: ");
        System.out.println(matListUnsorted);
    }

    public void sortDescBetweenIndexes() {
        MatList matListUnsorted = createMatListForSorting();
        matListUnsorted.sortDesc(1, 24);
        System.out.println("MatList after sorting in descending order between indexes: ");
        System.out.println(matListUnsorted);
    }

    public void sortDescStartFromExactValue() {
        MatList matListUnsorted = createMatListForSorting();
        matListUnsorted.sortDesc(20);
        System.out.println("MatList after sorting in descending order, started from exact value: ");
        System.out.println(matListUnsorted);
    }

    public void sortAsc() {
        MatList matListUnsorted = createMatListForSorting();
        matListUnsorted.sortAsc();
        System.out.println("MatList after sorting in ascending order: ");
        System.out.println(matListUnsorted);
    }

    public void sortAscBetweenIndexes() {
        MatList matListUnsorted = createMatListForSorting();
        matListUnsorted.sortAsc(1, 24);
        System.out.println("MatList after sorting in ascending order between indexes: ");
        System.out.println(matListUnsorted);
    }

    public void sortAscStartFromExactValue() {
        MatList matListUnsorted = createMatListForSorting();
        matListUnsorted.sortAsc(20);
        System.out.println("MatList after sorting in ascending order, started from exact value: ");
        System.out.println(matListUnsorted);
    }

    public void getElementByIndex() {
        Number num = matList.get(2); //25
        System.out.println("Element of MatList by index 2: ");
        System.out.println(num);
    }

    public void getMax() {
        Number max = matList.getMax(); //35
        System.out.println("Max element of MatList: ");
        System.out.println(max);
    }

    public void getMin() {
        Number max = matList.getMin(); //15
        System.out.println("Min element of MatList: ");
        System.out.println(max);
    }

    public void getAverage() {
        Number average = matList.getAverage(); //25
        System.out.println("Average value of all elements of MatList: ");
        System.out.println(average);
    }

    public void getMedianForOddQty() {
        Number median = matList.getMedian(); //25
        System.out.println("Median value of MatList with odd qty of elements: ");
        System.out.println(median);
    }

    public void getMedianForEvenQty() {
        matList.remove(0);
        Number median = matList.getMedian(); //27.5
        System.out.println("Median value of MatList with even qty of elements: ");
        System.out.println(median);
    }

    public void toArrayAllMatList() {
        matList.add(0, 15);
        Number[] array = matList.toArray();
        System.out.println("Array of MatList: ");
        System.out.println(Arrays.toString(array));
    }

    public void toArrayPartOfMatList() {
        Number[] array = matList.toArray(2, 5);
        System.out.println("Array of MatList from index to index: ");
        System.out.println(Arrays.toString(array));
    }

    public void cutBetweenIndexesIncludingIndexes() {
        System.out.println(matList.toString());
        matList.cut(1, 3);
        System.out.println("Cut MatList from index to index: ");
        System.out.println(matList.toString());
    }

    public void clearValuesOfMatList() {
        MatList matListCurrent = new MatList(matList);
        System.out.println("Current MatList before clear: ");
        System.out.println(matListCurrent);
        matListCurrent.clear();
        System.out.println("Current MatList after clear: " + matListCurrent + ", size is " + matListCurrent.size());
    }

    public void clearSeparateValuesinMatlist() {
        MatList matListCurrent = new MatList(matList);
        System.out.println("Current MatList before clear: ");
        System.out.println(matListCurrent);
        Number[] clearArray = new Number[] {20, 25};
        matListCurrent.clear(clearArray);
        System.out.println("Current MatList after clear: " + matListCurrent + ", size is " + matListCurrent.size());
    }

}
