package SegmentTree;

import java.util.Scanner;

public class Inversions {
    int size;
    int[] sum;
    public Inversions(int n){
        size = 1;
        while(size < n) size *= 2;
        sum = new int[2 * size];
    }

    private void set(int i, int v, int x, int lx, int rx){
        if(rx-lx == 1){
            sum[x] = v;
            return;
        }
        int m = (lx + rx)/2;
        if(i < m){
            set(i, v, 2 * x + 1, lx, m);
        } else{
            set(i, v, 2 * x + 2, m, rx);
        }
        sum[x] = sum[2*x+1] + sum[2*x+2];
    }
    public void set(int i, int v){
        set(i, v, 0, 0, size);
    }
    private int sum(int l, int r, int x, int lx, int rx){
        if(lx >= r || l >= rx) return 0;
        if(lx >= l && rx <= r) return sum[x];
        int m = (lx + rx)/2;
        int s1 = sum(l, r, 2 * x + 1, lx, m);
        int s2 = sum(l, r, 2*x+2, m,rx);
        return s1 + s2;
    }
    public int sum(int l, int r){
        return sum(l, r, 0, 0, size);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Inversions inversions = new Inversions(n);
        for(int i = 1; i < n; i++){
           inversions.set(i, 0);
        }
        for(int i = 1; i <= n; i++){
            int l = sc.nextInt();
            inversions.set(l-1, 1);
            System.out.print(inversions.sum(l, n)+" ");
        }
    }
}
