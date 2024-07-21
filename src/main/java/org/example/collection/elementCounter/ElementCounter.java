package org.example.collection.elementCounter;

import java.util.HashMap;
import java.util.Map;

public class ElementCounter {

    public static <T> Map<T, Integer> countElementsMap(T[] array) {
        Map<T, Integer> resultMap = new HashMap<>();

        for (T element : array) {
            if (resultMap.containsKey(element)) {
                resultMap.put(element, resultMap.get(element) + 1);
            } else {
                resultMap.put(element, 1);
            }
        }

        return resultMap;
    }

    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 1, 2, 3, 4, 5, 1, 2};
        Map<Integer, Integer> countElementsMap = countElementsMap(numbers);

        for (Map.Entry<Integer, Integer> entry : countElementsMap.entrySet()) {
            System.out.println("Элемент: " + entry.getKey() + ", Количество: " + entry.getValue());
        }
    }
}
