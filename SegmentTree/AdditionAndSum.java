package SegmentTree;

import java.util.Scanner;

public class AdditionAndSum {
    int size;
    long[] operations;
    long[] values;
    private static final long NEUTRAL_ELEMENT = 0;

    AdditionAndSum(int n){
        size = 1;
        while(size < n) size *= 2;
        operations = new long[2 * size];
        values = new long[2 * size];
    }

    private long modify_op(long a, long b, long len){
        return a + (b * len);
    }
    private long calc_op(long a, long b){
        return (a + b);
    }

    private void apply_mod_op1(int i, long b, int x){
        operations[i] = modify_op(operations[i], b, x);
    }
    private void apply_mod_op2(int i, long b, int x){
        values[i] = modify_op(values[i], b, x);
    }
    private void apply_mod_op3(int i, int x){
        values[i] = modify_op(values[i], operations[i], x);
    }


    private void modify(int l, int r, int v, int x, int lx, int rx){
        if(l >= rx || lx >= r) return;
        if(lx >= l && r >= rx) {
            apply_mod_op1(x, v, 1);
            apply_mod_op2(x, v, rx - lx);
            return;
        }
        int m = (lx + rx)/2;
        modify(l, r,v, 2 * x + 1, lx, m);
        modify(l, r, v, 2 * x + 2, m , rx);
        values[x] = calc_op(values[2*x+1] , values[2*x+2]);
        apply_mod_op3(x, rx - lx);
    }

    private void modify(int l, int r, int v) {
        modify(l, r, v, 0, 0, size);
    }

    private long calc(int l , int r, int x, int lx, int rx){
        if(l >= rx || lx >= r) return NEUTRAL_ELEMENT;
        if(lx >= l && r >= rx) {
            return values[x];

        }
        int m = (lx + rx)/2;
        long m1 = calc(l, r, 2 * x + 1, lx, m);
        long m2 = calc(l, r, 2 * x + 2, m , rx);
        return ((m1 + m2) + operations[x] * (Math.min(rx, r) - Math.max(lx, l)));
    }

    private long calc(int l, int r) {
        return calc(l, r, 0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        AdditionAndSum ms = new AdditionAndSum(n);

        while(m--> 0){
            int op = sc.nextInt();
            if(op == 1){
                int l = sc.nextInt();
                int r = sc.nextInt();
                int v = sc.nextInt();
                ms.modify(l, r, v);
            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(ms.calc(l, r));
            }
        }
    }
}
