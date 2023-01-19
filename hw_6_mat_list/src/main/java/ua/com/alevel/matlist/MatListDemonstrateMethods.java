package ua.com.alevel.matlist;

import com.diogonunes.jcolor.AnsiFormat;

import java.util.Arrays;
import java.util.Random;

import static com.diogonunes.jcolor.Attribute.*;

public class MatListDemonstrateMethods {
    MatList matList;
    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());

    public int getRandonNum() {
        Random random = new Random();
        int num = random.nextInt(0, 100);
        return num;
    }

    public void fillArrayByRandomNumbers(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandonNum();
        }
    }

    public MatList generateRandomMatList(int size) {
        Integer[] array = new Integer[size];
        fillArrayByRandomNumbers(array);
        MatList matList = new MatList<>(array);
        return matList;
    }

    public void printMatList(MatList matList) {
        System.out.println(matList);
    }

    public void start() {
        System.out.println();
        System.out.println(reverse.format("WELCOME TO THE DEMOSTRATION OF MATLIST METHODS "));
        System.out.println("(on examples of random integers)");
        System.out.println();
        createDemonstration();
        addMethodsDemonstration();
        joinMethodsDemonstration();
        sortingMethodsDemonstration();
        getMaxMinDemonstration();
        averageMedianDemonstration();
        toArrayDemonstration();
        cutAndClearDemonstration();
    }

    public void createMatListWithEmptyConstructor() {
        matList = new MatList<>();
        System.out.println(underlinedText.format("Create MatList with constructor MatList()"));
        System.out.println(blueText.format("Matlist result: " + matList.toString()));
    }

    public void createMatListFromFewArrays() {
        Integer[] array1 = new Integer[5];
        fillArrayByRandomNumbers(array1);
        Integer[] array2 = new Integer[5];
        fillArrayByRandomNumbers(array2);
        matList = new MatList<>(array1, array2);
        System.out.println(underlinedText.format("Create MatList with constructor MatList(E[] ... numbers)"));
        System.out.println("Array 1: " + Arrays.toString(array1) + ", array 2: " + Arrays.toString(array2));
        System.out.println(blueText.format("MatList result: " + matList.toString()));
    }

    public void createMatListFromFewMatLists() {
        MatList matList1 = generateRandomMatList(5);
        MatList matList2 = generateRandomMatList(5);
        matList = new MatList<>(matList1, matList2);
        System.out.println(underlinedText.format("Create MatList with constructor MatList(MatList ... numbers)"));
        System.out.println("MatList 1: " + matList1 + ", matList 2: " + matList2);
        System.out.println(blueText.format("MatList result: " + matList.toString()));
    }

    public void createDemonstration() {
        System.out.println(yellowText.format("CREATE MATLIST"));
        createMatListWithEmptyConstructor();
        createMatListFromFewArrays();
        createMatListFromFewMatLists();
        System.out.println();
    }

    public void addOneElement() {
        System.out.println(underlinedText.format("Add one element to MatList"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        matList.add(getRandonNum());
        System.out.println(blueText.format("MatList result: " + matList));
    }

    public void addOneElementToTheExactgIndex() {
        int index = 0;
        System.out.println(underlinedText.format("Add one element to the exact index"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        System.out.println("Add to the index " + index);
        matList.add(index, getRandonNum());
        System.out.println(blueText.format("MatList after: " + matList));
    }

    public void addFewElements() {
        System.out.println(underlinedText.format("Add few elements at once"));
        int num1 = getRandonNum();
        int num2 = getRandonNum();
        int num3 = getRandonNum();
        int num4 = getRandonNum();
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        System.out.println("Add numbers: " + num1 + ", " + num2 + ", " + num3 + ", " + num4);
        matList.add(num1, num2, num3, num4);
        System.out.println(blueText.format("MatList after: " + matList));
    }

    public void addMethodsDemonstration() {
        System.out.println(yellowText.format("ADD METHODS"));
        addOneElement();
        addOneElementToTheExactgIndex();
        addFewElements();
        System.out.println();
    }

    public void joinFewMatLists() {
        System.out.println(underlinedText.format("Join few MatLists"));
        matList = generateRandomMatList(5);
        System.out.print("MatList before: ");
        printMatList(matList);
        MatList matList1 = generateRandomMatList(5);
        MatList matList2 = generateRandomMatList(5);
        System.out.println("MatList 1: " + matList1 + ", matList 2: " + matList2);
        matList.join(matList1, matList2);
        System.out.println(blueText.format("MatList after: " + matList));
    }

    public void joinAndLeaveOnlyCommonElements() {
        System.out.println(underlinedText.format("Join and leave only common elements"));
        System.out.println("Main matlist before: " + matList);
        MatList matList1 = generateRandomMatList(10);
        MatList matList2 = generateRandomMatList(10);
        System.out.println("MatList 1: " + matList1 + ", matList 2: " + matList2);
        matList.intersection(matList1, matList2);
        System.out.println(blueText.format("MatList after: " + matList));
    }

    public void joinMethodsDemonstration() {
        System.out.println(yellowText.format("JOIN METHODS"));
        joinFewMatLists();
        joinAndLeaveOnlyCommonElements();
        System.out.println();
    }

    public void sortDesc() {
        System.out.println(underlinedText.format("Sorting in descending order all MatList"));
        MatList matListUnsorted = generateRandomMatList(15);
        System.out.println("Matlist before: " + matListUnsorted);
        matListUnsorted.sortDesc();
        System.out.println(blueText.format("MatList after: " + matListUnsorted));
    }

    public void sortDescBetweenIndexes() {
        System.out.println(underlinedText.format("Sorting in descending order between indexes"));
        MatList matListUnsorted = generateRandomMatList(15);
        System.out.println("Matlist before: " + matListUnsorted);
        int indexFrom = 0;
        int indexTo = 5;
        System.out.println("Sorting between indexes: " + indexFrom + " and " + indexTo);
        matListUnsorted.sortDesc(indexFrom, indexTo);
        System.out.println(blueText.format("MatList after: " + matListUnsorted));
    }

    public void sortDescStartFromExactValue() {
        System.out.println(underlinedText.format("Sorting in descending order started from exact value"));
        MatList matListUnsorted = generateRandomMatList(15);
        int value = (int) matListUnsorted.get(10);
        System.out.println("Matlist before: " + matListUnsorted);
        System.out.println("Sorting, start from value " + value + " (index 10)");
        matListUnsorted.sortDesc(value);
        System.out.println(blueText.format("MatList after: " + matListUnsorted));
    }

    public void sortAsc() {
        System.out.println(underlinedText.format("Sorting in ascending order all MatList"));
        MatList matListUnsorted = generateRandomMatList(15);
        System.out.println("Matlist before: " + matListUnsorted);
        matListUnsorted.sortAsc();
        System.out.println(blueText.format("MatList after: " + matListUnsorted));
    }

    public void sortAscBetweenIndexes() {
        System.out.println(underlinedText.format("Sorting in ascending order between indexes"));
        MatList matListUnsorted = generateRandomMatList(15);
        System.out.println("Matlist before: " + matListUnsorted);
        int indexFrom = 0;
        int indexTo = 5;
        System.out.println("Sorting between indexes: " + indexFrom + " and " + indexTo);
        matListUnsorted.sortAsc(indexFrom, indexTo);
        System.out.println(blueText.format("MatList after: " + matListUnsorted));
    }

    public void sortAscStartFromExactValue() {
        System.out.println(underlinedText.format("Sorting in asscending order started from exact value"));
        MatList matListUnsorted = generateRandomMatList(15);
        int value = (int) matListUnsorted.get(10);
        System.out.println("Matlist before: " + matListUnsorted);
        System.out.println("Sorting, start from value " + value + " (index 10)");
        matListUnsorted.sortAsc(value);
        System.out.println(blueText.format("MatList after: " + matListUnsorted));
    }

    public void sortingMethodsDemonstration() {
        System.out.println(yellowText.format("SORTING METHODS"));
        sortDesc();
        sortDescBetweenIndexes();
        sortDescStartFromExactValue();
        System.out.println();
        sortAsc();
        sortAscBetweenIndexes();
        sortAscStartFromExactValue();
        System.out.println();
    }

    public void getElementByIndex() {
        System.out.println(underlinedText.format("Get element by index"));
        matList = generateRandomMatList(10);
        Random random = new Random();
        int index = random.nextInt(0, matList.size());
        System.out.print("MatList before: ");
        printMatList(matList);
        System.out.println(blueText.format("Element of MatList by index " + index + ": " + matList.get(index)));
    }

    public void getMax() {
        System.out.println(underlinedText.format("Get max element"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        System.out.println(blueText.format("Max element of MatList: " + matList.getMax()));
    }

    public void getMin() {
        System.out.println(underlinedText.format("Get min element"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        System.out.println(blueText.format("Min element of MatList: " + matList.getMin()));
    }

    public void getMaxMinDemonstration() {
        System.out.println(yellowText.format("GET/MAX/MIN METHODS"));
        getElementByIndex();
        getMax();
        getMin();
        System.out.println();
    }

    public void getAverage() {
        System.out.println(underlinedText.format("Get average element"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        System.out.println(blueText.format("Average value is: " + matList.getAverage()));
    }

    public void getMedianForOddQty() {
        System.out.println(underlinedText.format("Get median for odd quantity elements in Matlist"));
        matList = generateRandomMatList(9);
        System.out.print("MatList before: ");
        printMatList(matList);
        System.out.println(blueText.format("Median value: " + matList.getMedian()));
    }

    public void getMedianForEvenQty() {
        System.out.println(underlinedText.format("Get median for even quantity elements in Matlist"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        System.out.println(blueText.format("Median value: " + matList.getMedian()));
    }

    public void averageMedianDemonstration() {
        System.out.println(yellowText.format("METHODS OF AVERAGE AND MEDIAN"));
        getAverage();
        getMedianForOddQty();
        getMedianForEvenQty();
        System.out.println();
    }

    public void toArrayAllMatList() {
        System.out.println(underlinedText.format("To Array all Matlist"));
        matList = generateRandomMatList(9);
        System.out.print("MatList before: ");
        printMatList(matList);
        Number[] array = matList.toArray();
        System.out.println(blueText.format("Array of MatList: " + Arrays.toString(array)));
    }

    public void toArrayPartOfMatList() {
        System.out.println(underlinedText.format("To Array part of Matlist between indexes"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        int indexFrom = 6;
        int indexTo = 10;
        Number[] array = matList.toArray(indexFrom, indexTo);
        System.out.println("To Array from index " + indexFrom + " to " + indexTo);
        System.out.println(blueText.format("Array of MatList: " + Arrays.toString(array)));
    }

    public void toArrayDemonstration() {
        System.out.println(yellowText.format("TO ARRAY METHODS"));
        toArrayAllMatList();
        toArrayPartOfMatList();
        System.out.println();
    }

    public void cutBetweenIndexesIncludingIndexes() {
        System.out.println(underlinedText.format("Cut MatList between indexes"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        int indexFrom = 6;
        int indexTo = 9;
        System.out.println("Cut from index " + indexFrom + " to " + indexTo);
        matList.cut(indexFrom, indexTo);
        System.out.println(blueText.format("Cut MatList: " + matList));
    }

    public void clearValuesOfMatList() {
        System.out.println(underlinedText.format("Clear all MatList"));
        matList = generateRandomMatList(10);
        System.out.print("MatList before: ");
        printMatList(matList);
        matList.clear();
        System.out.println(blueText.format("MatList after: " + matList));
    }

    public void clearSeparateValuesinMatlist() {
        System.out.println(underlinedText.format("Clear part of MatList"));
        Integer[] array = new Integer[]{15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
        MatList matListCurrent = new MatList(array);
        System.out.println("MatList before: " + matListCurrent);
        Number[] clearArray = new Number[]{20, 25, 100};
        System.out.println("Data for clear in array: " + Arrays.toString(clearArray));
        matListCurrent.clear(clearArray);
        System.out.println(blueText.format("MatList after: " + matListCurrent));
    }

    public void cutAndClearDemonstration() {
        System.out.println(yellowText.format("CUT AND CLEAR METHODS"));
        cutBetweenIndexesIncludingIndexes();
        System.out.println();
        clearValuesOfMatList();
        clearSeparateValuesinMatlist();
        System.out.println();
    }
}
