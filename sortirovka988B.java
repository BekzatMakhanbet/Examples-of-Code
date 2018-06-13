import java.util.Scanner;

public class sortirovka988B {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String resultArray[]=new String[n];
        String array[]=new String[n];
        boolean check=true;
        for (int i = 0; i <n; i++) {
            array[i]=sc.next();
        }
        for (int i = 1; i <array.length; i++) {
            for (int j = 0; j <array.length-i ; j++) {
                if (array[j].contains(array[j+1])){
                    String temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        for (int i = 1; i <array.length; i++) {
            for (int j = 0; j <array.length-i ; j++) {
                if (!array[j+1].contains(array[j])){
                    check=false;
                }
            }
        }
        if (check) {
            System.out.println("YES");
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        }
        else {
            System.out.println("NO");
        }
    }
}
