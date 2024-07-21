package org.example.stream.streamGenerateNum;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        String s1 = "Создайте список заказов с разными продуктами и их стоимостями.";
        List<Order> streamList = orders.stream()
                .toList();

        System.out.println(s1 + "\n");

        streamList.forEach(System.out::println);


        String s2 = "Группируйте заказы по продуктам.";
        Map<String, List<Order>> ordersByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct));

        System.out.println(s2 + "\n");

        ordersByProduct.forEach((product, orderList) -> {
            System.out.println("Product: " + product);
            orderList.forEach(System.out::println);
            System.out.println();
        });


        String s3 = "Для каждого продукта найдите общую стоимость всех заказов.";
        Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                ));

        System.out.println(s3 + "\n");

        totalCostByProduct.forEach((product, totalCost) -> {
            System.out.println("Product: " + product + ", Total Cost: " + totalCost);
        });


        String s4 = "Отсортируйте продукты по убыванию общей стоимости.";
        List<String> sortedProductsByCost = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                ))
                .entrySet().stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
                .map(entry -> "Product: " + entry.getKey() + ", Total Cost: " + entry.getValue())
                .toList();
        System.out.println(s4 + "\n");
        sortedProductsByCost.forEach(System.out::println);


        String s5 = "Выберите три самых дорогих продукта.";
        List<Order> mostExpensiveProd = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(3)
                .toList();

        System.out.println(s5 + "\n");
        mostExpensiveProd.forEach(System.out::println);

        String s6 = "Выведите результат: список трех самых дорогих продуктов и их общая стоимость.";

        double totalCost = mostExpensiveProd.stream()
                .mapToDouble(Order::getCost)
                .sum();
        System.out.println(s6 + "\n");
        mostExpensiveProd.forEach(order -> System.out.println("Product: " + order.getProduct() + ", Price: " + order.getCost()));
        System.out.println("Total Cost: " + totalCost);







    }
}
