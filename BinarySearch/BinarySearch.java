package BinarySearch;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        while(k-->0){
            int x = sc.nextInt();
            int l = 0;
            int r = n - 1;
            boolean ok = false;
            while(r >= l){
                int m = (l + r)/2;
                if(arr[m] == x){
                    ok = true;
                    break;
                } else if( arr[m] < x){
                    l = m + 1;
                } else r = m -1;
            }
            System.out.println(ok?"YES":"NO");
        }
    }
}
