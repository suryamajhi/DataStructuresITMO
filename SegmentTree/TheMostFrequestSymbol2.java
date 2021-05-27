package SegmentTree;

import java.util.*;
//not done...
class TheMostFrequentSymbol2 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String[] S = new String[26];
        int n = s.length();
        for(int i = 0; i < 26; i++){
            S[i] = binaryString(s, i);
        }
        int[][] D = new int[26][n+1];
        for(int x = 0; x < 26; x++){
            D[x][1] = Character.getNumericValue(S[x].charAt(1));
            for(int i = 2; i <= n; i++){
                D[x][i] = D[x][i-1] + Integer.parseInt(String.valueOf(S[x].charAt(i)));
            }
        }
        int q = sc.nextInt();
        while(q--> 0){
            int l = sc.nextInt();
            int r = sc.nextInt();

            int res = 0;
            int ans = 0;
            for(int i = 1; i < 26; i++){
                if(D[i][r] - D[i][l-1] > res){
                    ans = i;
                    res = D[i][r] - D[i][l-1];
                }
            }
            System.out.println((char)('a' + ans));
        }

    }

    public static String binaryString(String s, int x){
        char ch = (char)('a' + x);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ch)
                sb.append(1);
            else sb.append(0);
        }
        return sb.toString();
    }
}