package BinarySearch;

import java.util.Scanner;

public class ClosestToTheLeft {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = sc.nextInt();
        while(k--> 0){
            int x = sc.nextInt();

            int l = -1; //a[l]<=x
            int r = n; //a[r] > x
            while(r> l + 1){
                int m = (l + r)/2;
                if(a[m] <= x)
                    l = m;
                else r = m;
            }
            System.out.println(l + 1);

        }
    }
}
