package SegmentTree;

import java.util.Scanner;

//Hell yeah...I am awesome
public class SignAlternation {
    int size;
    long[] sums;
    SignAlternation(int n){
        size = 1;
        while(size < n) size *= 2;
        sums = new long[2 * size];
    }
    private void set(int i, int v, int x, int lx, int rx){
        if(rx -lx == 1){
            sums[x] = v;
            return;
        }
        int m =(lx + rx)/2;
        if(i < m) set(i, v, 2 * x +1, lx, m);
        else set(i, v, 2 * x + 2, m, rx);
        sums[x] = sums[2*x+1] + sums[2 *x +2];
    }

    public void set(int i, int v){
        set(i, v, 0, 0, size);
    }

    private int get(int i, int x, int lx, int rx){
        if(rx - lx == 1) return (int) sums[x];
        int m = (lx + rx)/2;
        int v;
        if(i < m) v = get(i , 2*x+1, lx, m);
        else v = get(i, 2*x +2, m, rx);
        return v;
    }

    public int get(int i){
        return get(i, 0, 0, size);
    }

    private long sum(int l, int r,int x, int lx, int rx){
        if(r<=lx || l>=rx) return 0;
        if(l<=lx && r >= rx) return sums[x];
        int m = (lx + rx)/2;
        long sum1 = sum(l, r, 2*x+1, lx, m);
        long sum2 = sum(l, r, 2*x + 2, m, rx);
        return sum1 + sum2;
    }

    public long sum(int l, int r){
        return sum(l, r,0, 0, size);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SignAlternation sa = new SignAlternation(n);
        SignAlternation sa2 = new SignAlternation(n);
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        boolean bool = true;
        for(int i = 0; i < n; i++){
            if(bool) {
                sa.set(i, arr[i]);
                sa2.set(i, -arr[i]);
                bool = false;
            }else {
                sa.set(i, -arr[i]);
                sa2.set(i, arr[i]);
                bool = true;
            }
        }

        int m = sc.nextInt();
        while(m-->0){
            int op = sc.nextInt();
            if(op == 0){
                int x = sc.nextInt();
                int v = sc.nextInt();
                int p = sa.get(x-1);
                if(p < 0) sa.set(x-1, -v);
                else sa.set(x - 1, v);
                int q = sa.get(x-1);
                if(q > 0) sa2.set(x -1, -v);
                else sa2.set(x-1, v);
            }else{
                int l = sc.nextInt();
                int r = sc.nextInt();
                int x = sa.get(l-1);
                if(x < 0){
                    System.out.println(sa2.sum(l-1, r));
                } else System.out.println(sa.sum(l-1, r));
            }
        }

    }
}
