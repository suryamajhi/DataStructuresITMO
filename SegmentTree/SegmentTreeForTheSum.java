package SegmentTree;

import java.util.Scanner;

//Constructing a segment tree
public class SegmentTreeForTheSum {

    int size;
    long[] sums;
    public SegmentTreeForTheSum(int n){
        size = 1;
        while(size < n) size *= 2;
        sums = new long[2 * size];
    }
    private void set(int i, int v, int x, int lx, int rx) {
        if(rx - lx == 1){
            sums[x] = v;
            return;
        }
        int m = (lx + rx) /2;
        if(i < m){
            set(i, v, 2 * x + 1, lx, m);
        } else {
            set(i, v, 2 * x + 2, m, rx);
        }
        sums[x] = sums[2 * x + 1] + sums[2 * x +2];
    }
    private void set(int i, int v) {
        set(i, v, 0, 0, size);
    }

    private long sum(int l, int r, int x, int lx, int rx) {
        if(lx >= r || l >= rx) return 0;
        if(lx >=l && rx <= r) return sums[x];
        int m = (lx + rx) / 2;
        long s1 = sum(l, r, 2 * x + 1, lx, m);
        long s2 = sum(l, r, 2 * x + 2, m, rx);
        return s1 + s2;
    }

    private long sum(int l, int r) {
        return sum(l, r, 0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegmentTreeForTheSum st = new SegmentTreeForTheSum(n);
        for(int i = 0; i < n; i++){
            int v = sc.nextInt();
            st.set(i, v);
        }
        while(m--> 0){
            int op = sc.nextInt();
            if(op == 1){
                int i = sc.nextInt();
                int v = sc.nextInt();
                st.set(i, v);
            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(st.sum(l, r));
            }
        }

    }
}
