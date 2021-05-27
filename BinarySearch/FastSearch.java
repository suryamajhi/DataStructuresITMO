package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class FastSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        int k = sc.nextInt();
        while(k-->0){
            int p = sc.nextInt();
            int q = sc.nextInt();

            int l = -1;
            int r = n;
            while(r > l + 1){
                int m = (l + r)/2;
                if(arr[m] <= q)
                    l = m;
                else r = m;
            }
            int i = l + 1;

            l = -1;
            r = n;
            while(r > l + 1){
                int m = (l + r)/2;
                if(arr[m] < p)
                    l = m;
                else r = m;
            }
            int j = r + 1;

            System.out.println( i - j + 1);

        }
    }
}
