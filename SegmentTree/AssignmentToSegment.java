package SegmentTree;

import java.util.Scanner;

public class AssignmentToSegment {
    int size;
    long[] operations;
    long NO_OPERATION = Long.MAX_VALUE;
    AssignmentToSegment(int n){
        size = 1;
        while(size<n) size*=2;
        operations = new long[2 * size];
    }

    private long operation(long a, long b){
        if(b == NO_OPERATION) return a;
        return b;
    }

    public void apply_operation(int i, long b){
        long a = operations[i];
        operations[i] = operation(a, b);
    }


    public void propagate(int x, int lx, int rx){
        if(rx - lx == 1) return;
        apply_operation(2 * x + 1, operations[x]);
        apply_operation(2*x + 2, operations[x]);
        operations[x] = NO_OPERATION;
    }


    private void add(int l, int r, int v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if(lx >= r || l >= rx) return;
        if(lx >=l && rx <= r){
            apply_operation(x, v);
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
        return operation(res, operations[x]);
    }

    public long get(int i ){
        return get(i, 0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        AssignmentToSegment as = new AssignmentToSegment(n);

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
