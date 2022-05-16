package DB_ASSIGNMENT;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class test {
    static NavigableSet<String> animalSet = new TreeSet<>(Arrays.asList("Dog", "Cat", "Tiger", "Lion"));
    
    public static void main(String[] args){
        int num = 10;
        String str = "안녕, 자바";
        for (String animal : animalSet){
            System.out.println(animal);
        }
        System.out.println(animalSet.higher("Lion"));
        System.out.println(animalSet.floor("Dog"));

        System.out.println("Hello World!\n");
        System.out.println(num);
        System.out.println(str);
        System.out.println("내 나이는"+num+"살 입니다.\n");
    }
}
// NavigableSet<String> animalSet = new TreeSet