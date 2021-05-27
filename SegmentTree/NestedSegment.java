package SegmentTree;

import java.util.Arrays;
import java.util.Scanner;

public class NestedSegment {

    int size;
    int[] sums;
    NestedSegment(int n){
        size = 1;
        while(size < n) size*=2;
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
        NestedSegment ns = new NestedSegment(2 * n);

        int[] visited = new int[n];
        int[] result = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        for(int i = 0; i < 2 * n; i++){
            int x = sc.nextInt();
            if(visited[x-1] != Integer.MAX_VALUE){
                result[x-1] = ns.sum(visited[x-1], i);
                ns.set(visited[x-1], 1);
            }else {
                visited[x - 1] = i;
            }
        }
        for(int i = 0; i < n; i++)
            System.out.println(result[i]);
    }

}
