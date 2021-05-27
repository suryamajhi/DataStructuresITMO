package SegmentTree;

import java.util.Scanner;

public class SegmentTreeFirstElementAtLeastXRange {
    int size;
    int[] max;
    public SegmentTreeFirstElementAtLeastXRange(int n){
        size = 1;
        while(size < n) size *= 2;
        max = new int[2 * size];
    }
    private void set(int i, int v, int x, int lx, int rx) {
        if(rx - lx == 1){
            max[x] = v;
            return;
        }
        int m = (lx + rx) /2;
        if(i < m){
            set(i, v, 2 * x + 1, lx, m);
        } else {
            set(i, v, 2 * x + 2, m, rx);
        }
        max[x] = Math.max(max[2 * x + 1], max[2 * x +2]);
    }
    private void set(int i, int v) {
        set(i, v, 0, 0, size);
    }

    private int findAtLeastX(int v, int l, int x, int lx, int rx) {
        if(max[x] < v) return -1;
        if(rx <= l) return -1;
        if(rx - lx == 1) return lx;
        int m = (lx + rx) / 2;
        int res = findAtLeastX(v,l,  2 * x + 1, lx, m);
        if(res == -1)
            res = findAtLeastX(v,l,  2 * x + 2, m, rx);
        return res;
    }

    private int findAtLeastX(int v, int l) {
        return findAtLeastX(v, l,0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegmentTreeFirstElementAtLeastXRange st = new SegmentTreeFirstElementAtLeastXRange(n);
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
                int x = sc.nextInt();
                int l = sc.nextInt();
                System.out.println(st.findAtLeastX(x, l));
            }
        }

    }

}
