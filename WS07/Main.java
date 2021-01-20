
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> lines = new ArrayList<>();
    public static ArrayList<String> boyNames = new ArrayList<>();
    public static ArrayList<String> girlNames = new ArrayList<>();
    public static ArrayList<String> matches = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("please enter a file name: ");
        String filename = sc.next();

        try (BufferedReader br = new BufferedReader(new FileReader(filename));) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

        for (String line : lines) {
            String[] arr = line.split("\t");
            for (String item : arr) {
                item.trim();
            }

            boyNames.add(arr[1]);
            // remove trailing whitespace that trim() somehow misses
            girlNames.add(arr[3].substring(0, arr[3].length() - 1));
        }

        for (String boy : boyNames) {
            for (String girl : girlNames) {
                if (boy.equals(girl)) {
                    matches.add(boy);
                }
            }
        }

        System.out.printf("\n%d names used for both genders: \n", matches.size());
        System.out.println("they are: ");
        for (String name : matches) {
            System.out.printf("%s\t", name);
        }
        System.out.println("");

        // sort
        Collections.sort(boyNames);
        Collections.sort(girlNames);

        // remove dupes
        for (String name : matches) {
            boyNames.remove(name);
            girlNames.remove(name);
        }

        System.out.println("\nboy and girl lists sorted and without duplicates:");
        System.out.println(boyNames);
        System.out.println(girlNames);

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("China", "Beijing");
        map.put("India", "New Delhi");
        map.put("Japan", "Tokyo");
        map.put("Russia", "Moscow");
        map.put("Indonesia", "Jakarta");
        map.put("South Korea", "Seoul");
        map.put("Egypt", "Cairo");
        map.put("Mexico", "Mexico City");
        map.put("United Kingdom", "London");
        map.put("Spain", "Madrid");
        map.put("Afghanistan", "Kabul");
        map.put("Kenya", "Nairobi");
        map.put("Italy", "Rome");
        map.put("France", "Paris");
        map.put("Brazil", "Brasilia");
        map.put("Pakistan", "Islamabad");
        map.put("Venezuela", "Caracas");
        map.put("Poland", "Warsaw");
        map.put("Jamaica", "Kingston");
        map.put("United States", "Washington DC");
        map.put("Bolivia", "Sucre");
        map.put("New Zealand", "Wellington");
        map.put("Canada", "Ottawa");
        map.put("Sweden", "Stockholm");
        map.put("Nepal", "Kathmandu");
        map.put("Trinidad", "Port of Spain");

        System.out.print("\nenter a country to find it's capital: ");
        String country = sc.next();

        if (map.get(country) != null) {
            System.out.printf("the capital of %s is %s\n", country, map.get(country));
        } else {
            System.out.println("sorry that country could not be found");
        }

        sc.close();
    }
}
