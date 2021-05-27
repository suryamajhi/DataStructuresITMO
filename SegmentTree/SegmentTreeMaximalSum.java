package SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SegmentTreeMaximalSum {
    int size;
    Item[] values;
    Item NEUTRAL_ELEMENT = new Item(0,0,0,0);

    static class Item{
        long seg, pref, suf, sum;
        Item(){}
        Item(long seg, long pref, long suf, long sum){
            this.seg = seg;
            this.pref = pref;
            this.suf = suf;
            this.sum = sum;
        }
    }

    public SegmentTreeMaximalSum(int n){
        size = 1;
        while(size < n) size *= 2;
        values = new Item[2*size];
        for(int i = 0; i < values.length; i++){
            values[i] = new Item(0, 0,0,0);
        }
    }
    private Item single(int v){
        if(v > 0){
            return new Item(v, v, v, v);
        } else {
            return new Item(0, 0, 0, v);
        }
    }
    private Item merge(Item a, Item b){
        return new Item(Math.max(a.seg, Math.max(b.seg, a.suf + b.pref)),
                Math.max(a.pref, a.sum + b.pref),
                Math.max(b.suf, b.sum + a.suf),
                a.sum + b.sum);
    }

    private void set(int i, int v, int x, int lx, int rx) {
        if(rx - lx == 1){
            values[x] = single(v);
            return;
        }
        int m = (lx + rx) /2;
        if(i < m){
            set(i, v, 2 * x + 1, lx, m);
        } else {
            set(i, v, 2 * x + 2, m, rx);
        }
        values[x] = merge(values[2 * x + 1], values[2 * x +2]);
    }
    private void set(int i, int v) {
        set(i, v, 0, 0, size);
    }

    private Item calcMin(int l, int r, int x, int lx, int rx) {
        if(lx >= r || l >= rx) return NEUTRAL_ELEMENT;
        if(lx >=l && rx <= r) return values[x];
        int m = (lx + rx) / 2;
        Item s1 = calcMin(l, r, 2 * x + 1, lx, m);
        Item s2 = calcMin(l, r, 2 * x + 2, m, rx);
        return merge(s1,s2);
    }

    private Item calcMin(int l, int r) {
        return calcMin(l, r, 0, 0, size);
    }

    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegmentTreeMaximalSum st = new SegmentTreeMaximalSum(n);
        for(int i = 0; i < n; i++){
            int v = sc.nextInt();
            st.set(i, v);
        }
        System.out.println(st.calcMin(0,n).seg);
        while(m--> 0){
                int i = sc.nextInt();
                int v = sc.nextInt();
                st.set(i, v);
            System.out.println(st.calcMin(0,n).seg);
        }
    }
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }

        long nextLong() { return Long.parseLong(next()); }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}
