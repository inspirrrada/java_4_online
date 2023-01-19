package ua.com.alevel.matlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class MatListMethodsTest {
    private static MatList matList;
    private static int CAPACITY = 10;
    Random random = new Random();

    public void fillArrayByRandomNumbers(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandonNum();
        }
    }

    public int getRandonNum() {
        Random random = new Random();
        int num = random.nextInt();
        return num;
    }

    public MatList generateRandomMatList() {
        Integer[] array = new Integer[10];
        fillArrayByRandomNumbers(array);
        matList = new MatList<>(array);
        return matList;
    }

    @Test
    @Order(1)
    public void checkCreateMatListWithEmptyConstructor() {
        MatList matList = new MatList<>();

        Assertions.assertEquals(matList.getDefaultCapacity(), CAPACITY);
        Assertions.assertEquals(matList.size(), 0);
    }

    @Test
    @Order(2)
    public void checkCreateMatListFromFewArrays() {
        Integer[] array1 = new Integer[5];
        fillArrayByRandomNumbers(array1);
        Integer[] array2 = new Integer[5];
        fillArrayByRandomNumbers(array2);
        MatList matList = new MatList<>(array1, array2);

        Assertions.assertEquals(matList.size(), array1.length + array2.length);
    }

    @Test
    @Order(3)
    public void checkCreateMatListFromFewMatLists() {
        Integer[] array3 = new Integer[5];
        fillArrayByRandomNumbers(array3);
        Integer[] array4 = new Integer[5];
        fillArrayByRandomNumbers(array4);
        MatList matList1 = new MatList<>(array3);
        MatList matList2 = new MatList<>(array4);
        MatList matList = new MatList<>(matList1, matList2);

        Assertions.assertEquals(matList.size(), matList1.size() + matList2.size());
    }

    @Test
    @Order(4)
    public void checkAddOneElementToMatList() {
        MatList matList = generateRandomMatList();
        int size = matList.size();
        int num = getRandonNum();
        matList.add(num);

        Assertions.assertEquals(matList.size(), size + 1);
        Assertions.assertEquals(matList.get(size), num);

    }

    @Test
    @Order(5)
    public void checkAddOneElementToExactIndex() {
        MatList matList = generateRandomMatList();
        int size = matList.size();
        int index = random.nextInt(0, size-1);
        int num = getRandonNum();
        matList.add(index, num);

        Assertions.assertEquals(matList.size(), size + 1);
        Assertions.assertEquals(matList.get(index), num);
    }

    @Test
    @Order(6)
    public void checkAddFewElementToMatList() {
        MatList matList = generateRandomMatList();
        int size = matList.size();
        int num1 = getRandonNum();
        int num2 = getRandonNum();
        int num3 = getRandonNum();
        int num4 = getRandonNum();
        matList.add(num1, num2, num3, num4);

        Assertions.assertEquals(matList.size(), size + 4);
        Assertions.assertEquals(matList.get(size), num1);
        Assertions.assertEquals(matList.get(size+1), num2);
        Assertions.assertEquals(matList.get(size+2), num3);
        Assertions.assertEquals(matList.get(size+3), num4);
    }

    @Test
    @Order(7)
    public void checkJoinFewMatLists() {
        MatList matList = generateRandomMatList();
        int matListSize = matList.size();
        MatList matList1 = generateRandomMatList();
        int matList1Size = matList1.size();
        MatList matList2 = generateRandomMatList();
        int matList2Size = matList2.size();
        matList.join(matList1, matList2);

        Assertions.assertEquals(matList.size(), matListSize + matList1Size + matList2Size);
    }

    @Test
    @Order(8)
    public void checkJoinAndLeaveOnlyCommonElements() {
        Integer[] array = new Integer[] {15, 20, 25, 30, 35};
        MatList matList = new MatList(array);
        Integer[] array1 = new Integer[] {5, 10, 15, 20, 25}; //common 15, 20, 25
        Integer[] array2 = new Integer[] {30, 35, 40, 45, 50}; //common 30, 35
        MatList matList1 = new MatList<>(array1);
        MatList matList2 = new MatList<>(array2);
        matList.intersection(matList1, matList2);
        System.out.println(matList);

        Assertions.assertEquals(matList.size(), 5);
        Assertions.assertEquals(matList.contains(5), false);
    }

    @Test
    @Order(9)
    public void checkSortDesc() {
        MatList matList = generateRandomMatList();
        int lastIndex = matList.size() - 1;
        int min = (int) matList.getMin();
        int max = (int) matList.getMax();
        matList.sortDesc();

        Assertions.assertEquals(matList.get(0), max);
        Assertions.assertEquals(matList.get(lastIndex), min);
    }

    @Test
    @Order(10)
    public void checkSortDescBetweenIndexes() {
        MatList matList = generateRandomMatList();
        MatList matListCopy = new MatList(matList);
        matListCopy.cut(0, 5);
        int min = (int) matListCopy.getMin();
        int max = (int) matListCopy.getMax();
        matList.sortDesc(0, 5);

        Assertions.assertEquals(matList.get(0), max);
        Assertions.assertEquals(matList.get(5), min);
    }

    @Test
    @Order(11)
    public void checkSortDescStartFromExactValue() {
        MatList matList = generateRandomMatList();
        int value = (int) matList.get(10);
        int lastIndex = matList.size() - 1;
        int min = (int) matList.getMin();
        int max = (int) matList.getMax();
        matList.sortDesc(value);

        Assertions.assertEquals(matList.get(10), max);
        Assertions.assertEquals(matList.get(lastIndex), min);
    }

    @Test
    @Order(12)
    public void checkSortAsc() {
        MatList matList = generateRandomMatList();
        int lastIndex = matList.size() - 1;
        int min = (int) matList.getMin();
        int max = (int) matList.getMax();
        matList.sortAsc();

        Assertions.assertEquals(matList.get(0), min);
        Assertions.assertEquals(matList.get(lastIndex), max);
    }

    @Test
    @Order(13)
    public void checkSortAscBetweenIndexes() {
        MatList matList = generateRandomMatList();
        MatList matListCopy = new MatList(matList);
        matListCopy.cut(0, 5);
        int min = (int) matListCopy.getMin();
        int max = (int) matListCopy.getMax();
        matList.sortAsc(0, 5);

        Assertions.assertEquals(matList.get(0), min);
        Assertions.assertEquals(matList.get(5), max);
    }

    @Test
    @Order(14)
    public void checkSortAscStartFromExactValue() {
        MatList matList = generateRandomMatList();
        MatList matListCopy = new MatList(matList);
        int lastIndex = matList.size() - 1;
        matListCopy.cut(6, lastIndex);
        int value = (int) matList.get(6);
        int min = (int) matListCopy.getMin();
        int max = (int) matListCopy.getMax();
        matList.sortAsc(value);

        Assertions.assertEquals(matList.get(6), min);
        Assertions.assertEquals(matList.get(lastIndex), max);
    }

    @Test
    @Order(15)
    public void checkGetElementByIndex() {
        Integer[] array = new Integer[] {15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
        MatList matList = new MatList(array);

        Assertions.assertEquals(matList.get(0), 15);
        Assertions.assertEquals(matList.get(5), 40);
        Assertions.assertEquals(matList.get(9), 60);
    }

    @Test
    @Order(16)
    public void checkGetMax() {
        MatList matList = generateRandomMatList();
        int max = (int) matList.getMax();
        MatList matListCopy = new MatList<>(matList);
        matListCopy.sortDesc();

        Assertions.assertEquals(max, matListCopy.get(0));
    }

    @Test
    @Order(17)
    public void checkGetMin() {
        MatList matList = generateRandomMatList();
        int min = (int) matList.getMin();
        MatList matListCopy = new MatList<>(matList);
        matListCopy.sortAsc();

        Assertions.assertEquals(min, matListCopy.get(0));
    }

    @Test
    @Order(18)
    public void checkGetAverage() {
        Integer[] array = new Integer[] {15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
        MatList matList = new MatList(array);
        double average = (15 + 20 + 25 + 30 + 35 + 40 + 45 + 50 + 55 + 60) / 10.0;

        Assertions.assertEquals(matList.getAverage(), average);
    }

    @Test
    @Order(19)
    public void checkGetMedianForOddQtyElements() {
        Integer[] array = new Integer[] {15, 20, 25, 30, 35, 40, 45, 50, 55};
        MatList matList = new MatList(array);
        int median = 35;

        Assertions.assertEquals(matList.getMedian(), median);
    }

    @Test
    @Order(20)
    public void checkGetMedianForEvenQtyElements() {
        Integer[] array = new Integer[] {15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
        MatList matList = new MatList(array);

        Assertions.assertEquals(matList.getMedian(), (35 + 40) / 2.0);
    }

    @Test
    @Order(21)
    public void checktoArrayAllMatList() {
        MatList matList = generateRandomMatList();
        Number[] array = matList.toArray();

        Assertions.assertEquals(matList.get(0), array[0]);
        Assertions.assertEquals(matList.get(6), array[6]);
        Assertions.assertEquals(matList.size(), array.length);
    }

    @Test
    @Order(22)
    public void checktoArrayPartOfMatList() {
        MatList matList = generateRandomMatList();
        Number[] array = matList.toArray(6,9);

        Assertions.assertEquals(array.length, (9-6)+1);
        Assertions.assertEquals(matList.get(6), array[0]);
        Assertions.assertEquals(matList.get(9), array[array.length-1]);
    }

    @Test
    @Order(23)
    public void checkCutBetweenIndexes() {
        matList = generateRandomMatList();
        int num6 = (int) matList.get(6);
        matList.cut(6, 9);

        Assertions.assertEquals(matList.get(0), num6);
        Assertions.assertEquals(matList.size(), (9-6)+1);
    }

    @Test
    @Order(24)
    public void checkClearValuesOfMatList() {
        matList = generateRandomMatList();
        matList.clear();

        Assertions.assertEquals(matList.size(), 0);
    }

    @Test
    @Order(25)
    public void checkClearBetweenIndexes() {
        Integer[] array = new Integer[] {15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
        MatList matList = new MatList(array);
        int sizeBefore = matList.size();
        Number[] clearArray = new Number[] {20, 25, 100};
        matList.clear(clearArray);
        int sizeAfter = matList.size();

        Assertions.assertTrue(sizeBefore > sizeAfter);
        Assertions.assertFalse(matList.contains(20));
        Assertions.assertEquals(sizeAfter, 8);
    }
}
