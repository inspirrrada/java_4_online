# hw_6_mat_list #
### <u> For start please run file </u> `run.sh` <u> from directory 'hw_6_mat_list'. </u> ###
## Task: ##
#### Реалізація класу MatList<Number>

Необхідно написати клас MatList який би насдідувався від інтерфейсу List. Реалізацію продумуєте самі (массив чи лінкед).

Ваш класс можна буде ініціювати наступними конструкторами:

1. MatList()
2. MatList(E[] ... numbers)
3. MatList(MatList … numbers)

Окрім методів інтерфейсу List ваш класс має мати наступні методи:

1. void add(E n) - додає елемент
2. void add(E ... n) - додає n едементів
3. void join(MatList ... ml) - об'єднує з іншими MatList
4. void intersection (MatList ... ml) - об'єднує з іншими MatList, залишаючи тільки ті елементи, які є в усіх колекціях
5. void sortDesc() - сортує колекцію від найбільшого
6. void sortDesc(int firstIndex, int lastIndex) - сортує колекцію від найбільшого тільки ті елементи, які лежать між firstIndex та lastIndex
7. void sortDesc(E value) - сортує колекцію від найбільшого починаючи з value
8. void sortAsc() - сортує колекцію від найменшого
9. void sortAsc(int firstIndex, int lastIndex)
10. void sortAsc(E value)
11. Number get(int index)
12. Number getMax()
13. Number getMin()
14. Number getAverage() - віддає середнє значення
15. Number getMedian() - віддає медіану
16. Number[] toArray();
17. Number[] toArray(int firstIndex, int lastIndex);
    18.MatList cut(int firstIndex, int lastIndex) - вирізає з firstIndex по lastIndex
19. void clear()
    20.void clear(Number[] numbers)
####