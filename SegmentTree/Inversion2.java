package SegmentTree;

import java.util.Scanner;

public class Inversion2 {
    int size;
    int[] sum;
    Inversion2(int n){
        size = 1;
        while(size < n) size *=2;
        sum = new int[2 * size];
    }
    private void set(int i, int v, int x, int lx, int rx){
        if(rx - lx == 1){
            sum[x] = v;
            return;
        }
        int m = (lx + rx)/2;
        if(i < m) set(i,v,2*x+1,lx, m);
        else set(i, v, 2*x+2,m,rx);
        sum[x] = sum[2*x+1]+sum[2*x+2];
    }
    public void set(int i, int v){
        set(i, v, 0, 0, size);
    }

    public int findK(int k, int x, int lx, int rx){
        if(rx - lx == 1) {
            set(lx, 0);
            return lx+1;
        }
        int m = (lx + rx) /2;
        int sl = sum[2*x+2];
        if(k < sl){
            return findK(k, 2 * x + 2, m, rx);
        } else {
            return findK(k - sl, 2 * x + 1, lx,m);
        }
    }

    public int findK(int k){
        return findK(k, 0,0,size);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        Inversion2 inversion= new Inversion2(n);
        for(int i = 0; i < n; i++){
            inversion.set(i, 1);
        }
        for(int i =0; i < n; i++) arr[i] = sc.nextInt();
        int temp[] = new int[n];
        for(int i = n -1 ; i >=0; i--){
            temp[i]=inversion.findK(arr[i]);
        }
        for(int i = 0; i < n; i++)
            System.out.println(temp[i]);
    }
}
