package SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class TheMostFrequentSymbol {

    public static class Item{
        int[] letter = new int[26];
    }

    int size;
    Item[] items;

    TheMostFrequentSymbol(int n){
        size = 1;
        while(size < n) size *= 2;
        items = new Item[2 * size];
        for(int i = 0; i < 2 * size; i++){
            items[i] = new Item();
        }
    }
    private Item single(char v){
        Item item = new Item();
        item.letter[v - 'a'] = 1;
        return item;
    }
    private Item merge(Item item1, Item item2){
        Item result = new Item();
        for(int i= 0; i < 26; i++){
            result.letter[i] = item1.letter[i] + item2.letter[i];
        }
        return result;
    }

    private void set(int i, char v, int x, int lx, int rx){
        if(rx - lx == 1){
            items[x] = single(v);
            return;
        }
        int m = (lx + rx)/2;
        if(i < m){
            set(i, v, 2 * x + 1, lx, m);
        }else{
            set(i, v, 2 * x + 2, m, rx);
        }
        items[x] = merge(items[2*x + 1], items[2*x + 2]);
    }

    public void set(int i, char v){
        set(i, v, 0, 0, size);
    }
    private Item copy(Item item){
        Item itemCopied = new Item();
        for(int i =0; i < 26; i++)
            itemCopied.letter[i] = item.letter[i];
        return itemCopied;
    }

    private Item frequent(int l, int r, int x, int lx , int rx){
        if(l>=rx || r <= lx) return new Item();
        if(l<=lx && r >= rx) return copy(items[x]);
        int m = (lx + rx)/2;
        Item item1 = frequent(l, r, 2*x + 1, lx, m);
        Item item2 = frequent(l, r, 2*x + 2, m, rx);
        return merge(item1, item2);
    }

    public char frequent(int l, int r){
        Item item = frequent(l, r, 0, 0, size);
        int max = 0;
        for(int i = 1; i < 26; i++){
            if(item.letter[i] > item.letter[max]){
                max = i;
            }
        }
        return (char) ('a' + max);
    }

    public static void main(String[] args){
        FastReader sc = new FastReader();
        String s = sc.next();
        TheMostFrequentSymbol mfs = new TheMostFrequentSymbol(s.length());
        for(int i = 0; i < s.length(); i++){
            mfs.set(i, s.charAt(i));
        }
        int q = sc.nextInt();
        while(q--> 0){
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(mfs.frequent(l-1, r));
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}