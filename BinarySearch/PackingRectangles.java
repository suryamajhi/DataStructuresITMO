package BinarySearch;

import java.util.Scanner;

public class PackingRectangles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long w = sc.nextInt();
        long h = sc.nextInt();
        long n = sc.nextInt();

        long l = 0; // l is bad
        long r = 1; // r is good
        while(!good(r, w, h, n)) r *= 2;
        while(r > l + 1){
            long m = (l + r)/2;
            if(good(m, w, h, n)){
                r = m;
            }else l = m;
        }
        System.out.println(r);
    }

    private static boolean good(long x, long w, long h, long n) {
        return (x/w) * (x/h) >= n;
    }
}
