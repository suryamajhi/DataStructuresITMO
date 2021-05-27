package SegmentTree;

import java.util.Scanner;

public class AddingToSegment {
    int size;
    long[] operations;
    AddingToSegment(int n){
        size = 1;
        while(size<n) size*=2;
        operations = new long[2 * size];
    }

    private void add(int l, int r, int v, int x, int lx, int rx) {
        if(lx >= r || l >= rx) return;
        if(lx >=l && rx <= r){
            operations[x] += v;
            return;
        }
        int m = (lx + rx) / 2;
        add(l, r, v, 2 * x + 1, lx, m);
        add(l, r, v, 2 * x + 2, m, rx);
    }

    private void add(int l, int r, int v) {
         add(l, r, v, 0, 0, size);
    }

    private long get(int i, int x, int lx, int rx){
        if(rx - lx == 1){
            return operations[x];
        }
        int m = (lx + rx)/2;
        long res;
        if(i < m ) res = get(i, 2 * x + 1, lx, m);
        else res = get(i, 2 * x + 2, m , rx);
        return res + operations[x];
    }

    public long get(int i ){
        return get(i, 0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        AddingToSegment as = new AddingToSegment(n);

        while(m--> 0){
            int op = sc.nextInt();
            if(op == 1){
                int l = sc.nextInt();
                int r = sc.nextInt();
                int v = sc.nextInt();
                as.add(l, r, v);
            }else {
                int i = sc.nextInt();
                System.out.println(as.get(i));
            }
        }
    }
}
