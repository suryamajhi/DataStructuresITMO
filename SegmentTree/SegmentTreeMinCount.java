package SegmentTree;

import java.util.Scanner;



public class SegmentTreeMinCount {
    int size;
    Item[] values;
    Item NEUTRAL_ELEMENT = new Item(Integer.MAX_VALUE, 0);

    static class Item{
        int m, c;
        Item(){}
        Item(int m, int c){
            this.m = m;
            this.c = c;
        }
    }

    public SegmentTreeMinCount(int n){
        size = 1;
        while(size < n) size *= 2;
        values = new Item[2*size];
        for(int i = 0; i < values.length; i++){
            values[i] = new Item(0, 0);
        }
    }
    private Item single(int v){
        return new Item(v, 1);
    }
    private Item merge(Item a, Item b){
        if(a.m < b.m) return new Item(a.m, a.c);
        if(a.m > b.m) return new Item(b.m, b.c);
        return new Item(a.m, a.c + b.c);
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
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegmentTreeMinCount st = new SegmentTreeMinCount(n);
        for(int i = 0; i < n; i++){
            int v = sc.nextInt();
            st.set(i, v);
        }
        while(m--> 0){
            int op = sc.nextInt();
            if(op == 1){
                int i = sc.nextInt();
                int v = sc.nextInt();
                st.set(i, v);
            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                Item item = st.calcMin(l, r);
                System.out.println(item.m + " " + item.c);
            }
        }
    }

}
