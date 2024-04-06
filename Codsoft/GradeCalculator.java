import java.util.*;
public class GradeCalculator{
    public static void main(String args[]){
      input();
    }
    static void input(){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter total number of subjects");
        int n=in.nextInt();
        double m[]=new double[n];
        System.out.println("Enter your marks in all subjects");
        for(int i=0;i<n;i++){
           m[i]=in.nextDouble();
        }
        totalMarks(m);
    }
    static void totalMarks(double m[]){
        double sum=0.0;
        for(int i=0;i<m.length;i++){
            sum+=m[i];
        }
        System.out.println("Total marks = "+sum);
        percent(sum,m);
    }
    static void percent(double s,double m[]){
        double p=s/m.length;
        System.out.println("Average percentage= "+p+"%");
        grade( p);
    }
   static void grade(double p){
    char grade='\u0000';
    if(p>=90)
      grade='A';
    else if(p<90 && p>=80)
      grade='A';
    else if(p<80 && p>=70)
      grade='B';
    else if(p<70 && p>=60)
      grade='C';
    else if(p<60 && p>=50)
      grade='D';
    else if(p<50 && p>=35)
      grade='E';
    else
      grade='F';
    System.out.println("Grade = "+grade);
   }

}