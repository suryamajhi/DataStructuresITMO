package SegmentTree;

import java.util.Scanner;

public class Cryptography {
    static class Matrix{
        long a,b,c,d, modulo;
        private static final Matrix NEUTRAL = new Matrix(1,0,0,1);
        Matrix(){}
        Matrix(int m){
            this.modulo = m;
        }

        Matrix(long a, long b, long c, long d){
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
        @Override
        public String toString(){
            return a%modulo + " " + b%modulo + "\n" + c%modulo + " " + d%modulo;
        }
    }

    int size;
    Matrix[] matrices;
    Cryptography(int n, int r){
        size = 1;
        while(size < n) size *= 2;
        matrices = new Matrix[2 * size];
        for(int i =0;i < 2*size;i++)
            matrices[i] = new Matrix();
    }

    private Matrix copy(Matrix m){
        return new Matrix(m.a,m.b,m.c,m.d);
    }
    private Matrix merge(Matrix m1, Matrix m2){
        return new Matrix((m1.a*m2.a+m1.b*m2.c),
                (m1.a*m2.b+m1.b*m2.d),
                (m1.c*m2.a+m1.d*m2.c),
                (m1.c*m2.b+m1.d*m2.d));
    }

    private void set(int i, Matrix v, int x, int lx, int rx){
        if(rx - lx == 1) {
            matrices[x] = copy(v);
            return;
        }
        int m = (lx + rx)/2;
        if(i < m) set(i, v, 2*x+1, lx, m);
        else set(i, v, 2 * x + 2, m, rx);
        matrices[x] = merge(matrices[2*x+1], matrices[2*x+2]);
    }

    public void set(int i, Matrix v){
        set(i, v, 0, 0, size);
    }

    private Matrix sum(int l, int r, int x , int lx, int rx){
        if(l>=rx || r<= lx) return Matrix.NEUTRAL;
        if(l<=lx && rx<=r) return matrices[x];
        int m = (lx + rx)/2;
        Matrix m1 = sum(l, r, 2*x+1, lx, m);
        Matrix m2 = sum(l, r, 2*x+2, m, rx);
        return merge(m1, m2);
    }

    public Matrix sum(int l, int r){
        return sum(l, r, 0, 0, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        Cryptography cryptography = new Cryptography(n, r);
        for(int i = 0; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();
            Matrix matrix = new Matrix(a,b,c,d);
            cryptography.set(i, matrix);
        }
        while(m-->0){
            int l = sc.nextInt();
            int x = sc.nextInt();
            Matrix matrix = cryptography.sum(l-1, x);
            matrix.modulo = r;
            System.out.println(matrix);
            System.out.println();
        }

    }
}
