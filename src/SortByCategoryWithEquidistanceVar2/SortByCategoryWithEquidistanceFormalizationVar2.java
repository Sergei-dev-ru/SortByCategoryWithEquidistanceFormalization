package SortByCategoryWithEquidistanceVar2;

import java.util.*;

//TODO:
public class SortByCategoryWithEquidistanceFormalizationVar2 {

    /**
     * отсортировать лист таким образом, что бы все элементы были равнораспределены по результирующему листу,
     * и при повторном запуске цикла результат не менялся
     *
     * Выборка категории 1,1,1,2,2,3
     * Неправильно: категория 1, 1 .... т.к. две идут подряд
     * Неправильно: категория 1, 2, 1, .... т.к. 1 повторилась до того как вставлено 3
     *
     * Правильно н-р 1, 2, 3, 1, 2, 1
     */
    public static void main(String[] args) {

        var data = List.of(
                new Product("продукт 1", "категория 1"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 2", "категория 1"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 3", "категория 1"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 4", "категория 2"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 5", "категория 2"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 3"),
                //еще варианты для теста
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 56"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 2", "категория 1"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 3", "категория 2"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 2", "категория 1"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 3", "категория 2"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 4", "категория 2"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 5", "категория 39"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 3"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 3"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4444"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4444"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4444"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 4444"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 5"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 56"),
                new SortByCategoryWithEquidistanceFormalizationVar2.Product("продукт 6", "категория 74")
        );


        /** работающий вариант, вывыд в консоль через @Override toString() из Product  */
        newSortMas(data);

    }

    static void newSortMas(List<SortByCategoryWithEquidistanceFormalizationVar2.Product> products){
        Set<Integer> set = new HashSet<>();
        int[] productsMas = new int[products.size()];
        for (int i = 0; i < productsMas.length; i++){
            productsMas[i] = Integer.parseInt(products.get(i).toString().substring(20));
        }
        Arrays.sort(productsMas);
        for(int i = 0; i < products.size(); i++){
            set.add(Integer.parseInt(products.get(i).toString().substring(20)));
        }
        int[][] mas = new int[set.size()][products.size()];
        for(int i = 0; i < mas.length; i++){
            for(Integer integer : set){
                mas[i][0] = integer;
                set.remove(integer);
                break;
            }
        }
        int maxS = 0;
        for (int j = 1; j < productsMas.length; j++) {
            if (productsMas[j] > maxS) {
                maxS = productsMas[j];
            }
        }
        int[] sizeS = new int[mas.length];
        int size = 1;
        int costyl = 0;
        for (int h = 0; h < mas[h].length; h++) {
            for (int j = 1; j < productsMas.length; j++) {
                if(productsMas[j-1] == productsMas[j]){
                    size++;
                }
                else{
                    sizeS[costyl] = size;
                    costyl++;
                    size = 1;
                }
                if (maxS == productsMas[j] && j == productsMas.length-1) {
                    sizeS[costyl] = size;
                }
                }
            break;
        }
        for(int i = 0; i < mas.length; i++){
            for (int h = 1; h < sizeS[i]; h++){
                mas[i][h] = mas[i][h-1];
            }
        }
        costyl = 0;
        int superCostyl = 0;
        while(true){
            for (int j = 0; j < mas[0].length; j++) {
                if(superCostyl >= mas.length){
                    break;
                }
                if (mas[superCostyl][costyl] != 0) {
                    for(int y = 0; y < products.size(); y++){
                        if (Integer.parseInt(products.get(y).toString().substring(20)) == mas[superCostyl][costyl]) {
                            System.out.println(products.get(y).toString());
                            break;
                        }
                    }
                }
                superCostyl++;
            }
            superCostyl = 0;
            costyl++;
            if(costyl >= mas[0].length){
                break;
            }
        }
    }

    /**
     * Можно менять
     */
    public static class Product {

        private final String value;
        private final String category;

        public Product(String value, String category) {
            this.value = value;
            this.category = category;
        }

        public String getValue() {
            return value;
        }

        public String getCategory() {
            return category;
        }

        @Override
        public String toString() {
            return value  + " " + category;
        }

    }
}