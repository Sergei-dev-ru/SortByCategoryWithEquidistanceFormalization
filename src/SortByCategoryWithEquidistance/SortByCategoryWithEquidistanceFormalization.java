package SortByCategoryWithEquidistance;

import java.util.*;

//TODO:
public class SortByCategoryWithEquidistanceFormalization {

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
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 2", "категория 1"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 3", "категория 1"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 4", "категория 2"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 5", "категория 2"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 3"),
                //еще варианты для теста
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 56"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 2", "категория 1"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 3", "категория 2"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 4", "категория 2"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 5", "категория 39"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 3"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 3"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 4"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 4444"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 5"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 56"),
                new SortByCategoryWithEquidistanceFormalization.Product("продукт 6", "категория 5")
        );


        /** работающий вариант, вывыд в консоль через @Override toString() из Product  */
        newSort(data);

    }

    static void newSort(List<SortByCategoryWithEquidistanceFormalization.Product> products){
        Set<Integer> set = new HashSet<>();
        List<ArrayList<Integer>> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < products.size(); i++){
            set.add(Integer.parseInt(products.get(i).toString().substring(20)));
        }
        for(int i = 0; i < products.size(); i++){
            ArrayList<Integer> list1 = new ArrayList<>();
            for(Integer integer : set){
                list1.add(integer);
                set.remove(integer);
                list.add(list1);
                break;
            }
        }
        for(int i = 0; i < products.size(); i++){
            for(int j = 0; j < list.size(); j++)
            if(Integer.parseInt(products.get(i).toString().substring(20)) == list.get(j).get(0)){
                list.get(j).add(Integer.parseInt(products.get(i).toString().substring(20)));
            }
        }
        int maxSize = 0;
        for(int i = 0; i < list.size(); i++){
            maxSize += list.get(i).size();
            list.get(i).remove(0);
        }
        maxSize = maxSize - list.size();
        int i = 0;
        while (result.size() < maxSize){
            if(!list.get(i).isEmpty()) {
                result.add(list.get(i).remove(0));
            }
            i++;
            if(i == list.size()){
                i = 0;
            }
        }
        for(int y = 0; y < products.size(); y++){
            for(int y1 = 0; y1 < products.size(); y1++) {
                if (Integer.parseInt(products.get(y1).toString().substring(20)) == result.get(y)) {
                    System.out.println(products.get(y1).toString());
                    break;
                }
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