import java.util.*;
public class NumberGame{
   static int maxAttempt=5;
    public static void main(String args[]){
      Scanner in =new Scanner(System.in);
      Random random=new Random();
      System.out.println("Welcome to Number Game\nRULES:\n1.Guess a number between 1 to 100\n2.If you guess the correct number you WIN.\n3.You have maximum 5 guess");
      System.out.println("Enter your guess");
      int guess=in.nextInt();
      int max=100,min=1;
      int r=random.nextInt((max-min+1)-min);
      checkGuess(guess,r);  
    }
    static void checkGuess(int g,int r){
      Scanner in =new Scanner(System.in);
      int attempt=0;
      while(attempt<=maxAttempt){
        if(g ==r){
          System.out.println("YOU WIN!!! (^_^)");
          return;
        }
        else if(g>r){
         System.out.println("Guess is higher than the number\nChoose a lower number:");
         g=in.nextInt();
        }
        else{
         System.out.println("Guess is lower than the number\nChoose a higher number:");
         g=in.nextInt();      
        }
        attempt++;
      }
      System.out.println("YOU LOSE (ToT)");
    }
}