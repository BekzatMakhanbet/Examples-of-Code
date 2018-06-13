import java.util.Scanner;

public class komanda988A {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int size=1;
        int array[]=new int[n];
        int array2[]=new int[k];
        String result="1 ";
        for (int i = 0; i <n; i++) {
            array[i]=sc.nextInt();
        }
        array2[0]=array[0];
        for (int i = 1; i <n; i++) {
            boolean bool=false;
            for (int j = 0; j <size; j++) {
                if (array[i]==array2[j]){
                    bool=true;
                }
            }
            if (bool==false){
                if (size>=k){
                    break;
                }else {
                    array2[size] = array[i];
                    size++;
                    result = result + (i + 1) + " ";
                }
            }
        }

        if (size<k){
            System.out.println("NO");
        }
        else {
            System.out.println("YES");
            System.out.println(result);
        }
    }
}
