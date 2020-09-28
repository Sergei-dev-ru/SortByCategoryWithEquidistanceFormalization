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

        //test();
        //sort(data);
        //sortQueue(data);

        /** работающий вариант, вывыд в консоль через @Override toString() из Product  */
        newSort(data);

        //System.out.println(sortedData); //TODO
    }

    static void newSort(List<SortByCategoryWithEquidistanceFormalization.Product> products){
        Set<Integer> set = new HashSet<>();
        List<ArrayList<Integer>> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        List<SortByCategoryWithEquidistanceFormalization.Product> resultProd = new ArrayList<>();
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
        System.out.println(result);
        for(int y = 0; y < products.size(); y++){
            for(int y1 = 0; y1 < products.size(); y1++) {
                if (Integer.parseInt(products.get(y1).toString().substring(20)) == result.get(y)) {
                    resultProd.add(products.get(y1));
                    break;
                }
            }
        }
        for(int h = 0; h < resultProd.size(); h++){
            System.out.println(resultProd.get(h).toString());
        }
    }

    /**Здесь хотел через PriorityQueue и Comparator*/

    static void sortQueue(List<SortByCategoryWithEquidistanceFormalization.Product> products){
        Product prod;
        Queue<Product> queue = new PriorityQueue<>(new TheComparator<Product>());
        for(int i = products.size()-1; i >= 0; i--){
            prod = products.get(i);
            queue.add(prod);
        }

        for(Product pr : queue) {
            System.out.println(pr);
        }
        queue.remove();
        System.out.println();
        for(Product pr : queue) {
            System.out.println(pr);
        }
    }

    /**хотел найти алгоритм обхода дерева чтобы получилась
     * отсортированная согласно условию результирующая выборка*/

    static void sort(List<SortByCategoryWithEquidistanceFormalization.Product> products){
        NavigableMap<String, String> sortedData = new TreeMap();
        Map.Entry<String, String> beforeMap;

        for(int i = 0; i < products.size(); i++){
            sortedData.put(products.get(i).toString().substring(0, 9), products.get(i).toString().substring(10,21));
        }

        Iterator<Map.Entry<String,String>> iterator = sortedData.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> pair = iterator.next();
            beforeMap = sortedData.higherEntry(pair.getKey());
            System.out.println("before " + beforeMap);
        }
    }

    /** здесь в "лоб" пытался решить */

    public static void test(){
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(6);
        list.add(6);
        list.add(6);
        list.add(6);
        list.add(11);
        list.add(0);
        list.add(9);
        list.add(9);

        Collections.sort(list);
        int max = 0;
        int indexMaxEl = 0;

        for(int i = 0; i < list.size(); i++){
            for(int j = 1; j < list.size(); j++){
                if(list.get(i) < list.get(j)){
                    if(list.get(j) <= max && j <= indexMaxEl){
                        for(int k = 0; k <= indexMaxEl; k++){
                            //list2.add(list.get(k));
                        }
                        for(int k = 0; k <= indexMaxEl; k++){
                            //list.remove(k);
                        }
                        i = 0;
                        j = 1;
                        max = 0;
                        indexMaxEl = 0;
                        System.out.println("здесь");
                        break;
                    }
                    list.add(i+1, list.remove(j));
                    if(list.get(i+1) > max){
                        max = list.get(i+1);
                        indexMaxEl = i + 1;
                    }
                    break;
                }
            }
        }
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        for(int i = 0; i < list2.size(); i++){
            System.out.print(list2.get(i) + " ");
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