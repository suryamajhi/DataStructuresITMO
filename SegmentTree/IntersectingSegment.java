package SegmentTree;

import java.util.Arrays;
import java.util.Scanner;

public class IntersectingSegment {
    int size;
    int[] sums;
    IntersectingSegment(int n){
        size = 1;
        while(size < n) size *= 2;
        sums = new int[2 * size];
    }
    private void set(int i, int v, int x, int lx, int rx){
        if(rx - lx == 1){
            sums[x] = v;
            return;
        }
        int m = (lx + rx)/2;
        if(i < m){
            set(i, v, 2*x + 1, lx, m);
        }else set(i, v, 2*x+2, m, rx);

        sums[x] = sums[2*x+1]+ sums[2*x+2];
    }
    private void set(int i, int v) {
        set(i, v, 0,0,size);
    }
    private int sum(int l, int r, int x, int lx, int rx){
        if(lx >=r || l >=rx) return 0;
        if(lx>=l && rx <= r) return sums[x];
        int m = (lx + rx)/2;
        int s1 = sum(l, r, 2*x+1, lx, m);
        int s2 = sum(l, r, 2*x+2, m, rx);
        return s1 + s2;
    }
    public int sum(int l, int r){
        return sum(l,r, 0,0,size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        IntersectingSegment ns = new IntersectingSegment(2 * n);

        int[] visited = new int[n];
        int[] result = new int[n];
        int[] arr = new int[2 * n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        for(int i = 0; i < 2 * n; i++){
            int x = sc.nextInt();
            arr[i] = x;
            if(visited[x-1] != Integer.MAX_VALUE){
                ns.set(visited[x-1], 0);
                result[x-1] = ns.sum(visited[x-1], i);
            }else {
                ns.set(i, 1);
                visited[x - 1] = i;
            }
        }

        reverse(arr, arr.length);
        int[] result2 = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        for(int i = 0; i < 2 * n; i++){
            int x = arr[i];
            if(visited[x-1] != Integer.MAX_VALUE){
                ns.set(visited[x-1], 0);
                result2[x-1] = ns.sum(visited[x-1], i);
            }else {
                ns.set(i, 1);
                visited[x - 1] = i;
            }
        }
        for(int i = 0; i < n; i++)
            System.out.println(result2[i] + result[i]);

    }

    static void reverse(int a[], int n)
    {
        int i, k, t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }
    }
}
