package space.harbour.java.hw2;
import java.util.Scanner;

public class Slang {

     public static String fixAbbreviations(String s){
        if (s.contains("PLZ")) s = s.replace("PLZ", "please");
        if (s.contains("FYI")) s = s.replace("FYI", "for your information");
        if (s.contains("GTFO")) s = s.replace("GTFO", "please, leave me alone");
        if (s.contains("ASAP")) s = s.replace("ASAP", "as soon as possible");
        return s;
    }

    public static String fixSmiles(String s){
        if (s.contains(":)")) s = s.replace(":)", "[smiling]");
        if (s.contains(":(")) s = s.replace(":(", "[sad]");
        if (s.contains("¯\\_(ツ)_/¯")) s = s.replace("¯\\_(ツ)_/¯", "[such is life]");
        return s;
    }

    public static void main(String[] args) {

    Scanner abc = new Scanner(System.in);
    String s = abc.nextLine();
    String x = fixAbbreviations(s);
    String y = fixSmiles(x);

    System.out.println(y);
    }

}