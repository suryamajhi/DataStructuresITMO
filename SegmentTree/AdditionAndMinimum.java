package SegmentTree;

import java.util.Scanner;

public class AdditionAndMinimum {
    int size;
    long[] operations;
    long[] mins;

    AdditionAndMinimum(int n){
        size = 1;
        while(size < n) size *= 2;
        operations = new long[2 * size];
        mins = new long[2 * size];
    }

    private void add(int l, int r, int v, int x, int lx, int rx){
        if(lx >=r || l >= rx) return;
        if(lx >= l && rx <= r){
            operations[x] += v;
            mins[x] += v;
            return;
        }
        int m = (lx + rx)/2;
        add(l, r, v, 2 * x + 1, lx, m);
        add(l,r,v, 2*x+2, m, rx);
        mins[x] = Math.min(mins[2*x+1], mins[2*x+2]) + operations[x];
    }

    public void add(int l, int r, int v){
        add(l, r, v, 0, 0, size);
    }

    private long min(int l, int r, int x, int lx, int rx){
        if(lx >=r || l >= rx) return Long.MAX_VALUE;
        if(lx >= l && rx <= r){
            return mins[x];
        }
        int m = (lx + rx)/2;
        long min1 = min(l, r, 2 * x + 1, lx, m);
        long min2 = min(l,r, 2*x+2, m, rx);
        return Math.min(min1,min2) + operations[x];
    }

    public long min(int l, int r){
        return min(l, r, 0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        AdditionAndMinimum am = new AdditionAndMinimum(n);
        while(m-->0){
            int op = sc.nextInt();
            if(op == 1){
                int l = sc.nextInt();
                int r = sc.nextInt();
                int v = sc.nextInt();
                am.add(l, r, v);
            }else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(am.min(l, r));
            }
        }
    }
}
