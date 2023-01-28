package ua.com.alevel.service;

import ua.com.alevel.entity.WordStatistics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class TextStatisticsMethods {

    public static List<WordStatistics> getWordStatisticsList(String text) {
        String[] textArray = text.replace(".", " ").toLowerCase().trim().split("\\s+");
        long[] countOfPreviousElem = {0};
        int[] ratingPoint = {0};
        return Arrays.stream(textArray)
                .distinct()
                .map(currentWord -> {
                    WordStatistics wordStatistics = new WordStatistics();
                    wordStatistics.setWord(currentWord);
                    wordStatistics.setCount(Arrays.stream(textArray)
                            .filter(word -> word.equals(currentWord))
                            .count());
                    long percentage = Math.round((100.0 * wordStatistics.getCount() / textArray.length));
                    wordStatistics.setPercentage(percentage);
                    return wordStatistics;
                })
                .sorted(Comparator.reverseOrder())
                .toList().stream()
                .map(currentWordStatistics -> {
                    if (countOfPreviousElem[0] == 0) {
                        ratingPoint[0] = ratingPoint[0] + 1;
                        currentWordStatistics.setRating(ratingPoint[0]);
                        countOfPreviousElem[0] = currentWordStatistics.getCount();
                    } else {
                        if (currentWordStatistics.getCount() < countOfPreviousElem[0]) {
                            ratingPoint[0]++;
                            currentWordStatistics.setRating(ratingPoint[0]);
                            countOfPreviousElem[0] = currentWordStatistics.getCount();
                        } else {
                            currentWordStatistics.setRating(ratingPoint[0]);
                        }
                    }
                    return currentWordStatistics;
                })
                .toList();
    }

    public static void printTable(List<WordStatistics> wordStatisticsList) {
        String tableTopFormat = "_%1$-30s_%2$-20s_%3$-20s_%4$-20s_\n";
        String tableCellFormat = "|%1$-30s|%2$-20s|%3$-20s|%4$-20s|\n";
        String borderWide = "______________________________"; //30s
        String borderStandard = "____________________"; //20s
        System.out.format(tableTopFormat, borderWide, borderStandard, borderStandard, borderStandard);
        System.out.format(tableCellFormat, "", "  Rating", "  Count", "  Percentage");
        System.out.format(tableCellFormat, borderWide, borderStandard, borderStandard, borderStandard);
        wordStatisticsList.stream()
                .forEach(currentElem -> {
                    System.out.format(tableCellFormat, "  " + currentElem.getWord(), "  " + currentElem.getRating(), "  " + currentElem.getCount(), "  " + currentElem.getPercentage());
                    System.out.format(tableCellFormat, borderWide, borderStandard, borderStandard, borderStandard);
                });
    }
}
