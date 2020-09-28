package SortByCategoryWithEquidistance;

import java.util.Comparator;

class TheComparator<P> implements Comparator<SortByCategoryWithEquidistanceFormalization.Product> {

    @Override
    public int compare(SortByCategoryWithEquidistanceFormalization.Product o1, SortByCategoryWithEquidistanceFormalization.Product o2) {

        int i1 = Integer.parseInt(o1.toString().substring(20));
        int i2 = Integer.parseInt(o2.toString().substring(20));
        System.out.println("i1=" + i1 + " i2=" + i2);
        /*if(i1 > i2){
            return 1;
        }
        else if (i1 < i2){
            return 1;
        }
        else if (i1 == i2){
            return -1;
        }
        else return 0;*/
        //System.out.println(o1.hashCode() + " " + o2.hashCode());

        if(o1.hashCode() > o2.hashCode()){
            return -1;
        }
        else if (o1.hashCode() < o2.hashCode()){
            return 0;
        }
        else if (o1.hashCode() == o2.hashCode()){
            return 1;
        }
        else return 0;
    }

}
