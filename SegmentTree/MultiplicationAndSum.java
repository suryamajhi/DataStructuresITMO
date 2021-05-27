package SegmentTree;

import java.util.Scanner;

public class MultiplicationAndSum {
    int size;
    long[] operations;
    long[] values;
    private static final long MOD = 1000000007;
    private static final long NEUTRAL_ELEMENT = 0;

    MultiplicationAndSum(int n){
        size = 1;
        while(size < n) size *= 2;
        operations = new long[2 * size];
        values = new long[2 * size];
        for(int i = 0; i < 2 * size; i++) {
            operations[i] = 1;
        }
        build(0, 0, size);
    }
    private void build(int x, int lx, int rx){
        if(rx == lx + 1){
            values[x] = 1;
            return;
        }
        int m = (lx + rx)/2;
        build(2*x + 1, lx, m);
        build(2*x + 2, m, rx);
        values[x] = calc_op(values[2*x+1], values[2*x+2]);
    }

    private long modify_op(long a, long b){
        return (a * b) % MOD;
    }
    private long calc_op(long a, long b){
        return (a + b) % MOD;
    }

    private void apply_mod_op1(int i, long b){
        operations[i] = modify_op(operations[i], b);
    }
    private void apply_mod_op2(int i, long b){
        values[i] = modify_op(values[i], b);
    }
    private void apply_mod_op3(int i){
        values[i] = modify_op(values[i], operations[i]);
    }


    private void modify(int l, int r, int v, int x, int lx, int rx){
        if(l >= rx || lx >= r) return;
        if(lx >= l && r >= rx) {
            apply_mod_op1(x, v);
            apply_mod_op2(x, v);
            return;
        }
        int m = (lx + rx)/2;
        modify(l, r,v, 2 * x + 1, lx, m);
        modify(l, r, v, 2 * x + 2, m , rx);
        values[x] = calc_op(values[2*x+1] , values[2*x+2]);
        apply_mod_op3(x);
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
        return ((m1 + m2) * operations[x])%MOD;
    }

    private long calc(int l, int r) {
        return calc(l, r, 0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        MultiplicationAndSum ms = new MultiplicationAndSum(n);

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
