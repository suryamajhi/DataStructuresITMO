package SegmentTree;

import java.util.Scanner;

public class SegmentTreeForMinimum {
    int size;
    int[] min;
    public SegmentTreeForMinimum(int n){
        size = 1;
        while(size < n) size *= 2;
        min = new int[2 * size];
    }
    private void set(int i, int v, int x, int lx, int rx) {
        if(rx - lx == 1){
            min[x] = v;
            return;
        }
        int m = (lx + rx) /2;
        if(i < m){
            set(i, v, 2 * x + 1, lx, m);
        } else {
            set(i, v, 2 * x + 2, m, rx);
        }
        min[x] = Math.min(min[2 * x + 1], min[2 * x +2]);
    }
    private void set(int i, int v) {
        set(i, v, 0, 0, size);
    }

    private int calcMin(int l, int r, int x, int lx, int rx) {
        if(lx >= r || l >= rx) return Integer.MAX_VALUE;
        if(lx >=l && rx <= r) return min[x];
        int m = (lx + rx) / 2;
        int s1 = calcMin(l, r, 2 * x + 1, lx, m);
        int s2 = calcMin(l, r, 2 * x + 2, m, rx);
        return Math.min(s1,s2);
    }

    private int calcMin(int l, int r) {
        return calcMin(l, r, 0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegmentTreeForMinimum st = new SegmentTreeForMinimum(n);
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
                System.out.println(st.calcMin(l, r));
            }
        }

    }

}
