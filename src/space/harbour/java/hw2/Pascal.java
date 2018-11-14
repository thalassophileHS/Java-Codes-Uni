package space.harbour.java.hw2;
import java.util.Scanner;

public class Pascal {
    
    static int factorial(int n) {
      int f;

      for(f = 1; n > 1; n--){
         f *= n;
      }
      return f;
   }
   static int ncr(int n,int r) {
      return factorial(n) / ( factorial(n-r) * factorial(r) );
   }
   public static void main(String args[]){
      Scanner num = new Scanner(System.in);
      System.out.println("Enter a number:");
      String n = num.next();
      n = n.replaceAll("[A-Z]","");
      int x = Integer.parseInt(n);
      System.out.println();
      int i, j;

      for(i = 0; i <= x; i++) {
         for(j = 0; j <= x-i; j++){
            System.out.print(" ");
         }
         for(j = 0; j <= i; j++){
            System.out.print(" "+ncr(i, j));
         }
         System.out.println();
      }
   }
}
