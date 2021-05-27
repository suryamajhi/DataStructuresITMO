package SegmentTree;

import java.util.Scanner;

//Constructing a segment tree
public class SegmentTreeKthOne {

    int size;
    int[] sums;
    public SegmentTreeKthOne(int n){
        size = 1;
        while(size < n) size *= 2;
        sums = new int[2 * size];
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

    public int find(int k, int x, int lx, int rx){
        if(rx - lx == 1)
            return lx;
        int m = (lx + rx) /2;
        int sl = sums[2*x+1];
        if(k < sl){
            return find(k, 2 * x + 1, lx, m);
        } else {
            return find(k - sl, 2 * x + 2, m, rx);
        }
    }

    public int find(int k){
        return find(k, 0,0,size);
    }

    private int get(int i, int x, int lx, int rx){
        if(rx - lx == 1){
            return x;
        }
        int m = (lx + rx) /2;
        if(i < m){
            return get(i, 2 * x + 1, lx, m);
        } else {
            return get(i, 2 * x + 2, m, rx);
        }
    }

    public int get(int i){
        return get(i,0,0,size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegmentTreeKthOne st = new SegmentTreeKthOne(n);
        for(int i = 0; i < n; i++){
            int v = sc.nextInt();
            st.set(i, v);
        }
        while(m--> 0){
            int op = sc.nextInt();
            if(op == 1){
                int i = sc.nextInt();
                st.set(i, 1 - st.sums[st.get(i)]);
            } else {
                int k = sc.nextInt();
                System.out.println(st.find(k));
            }
        }
    }
}
