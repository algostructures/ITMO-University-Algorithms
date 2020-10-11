

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    final static long MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        char [] rec = fs.nextCharArray();
        long [] have = fs.nextLongArray(3);
        long [] price = fs.nextLongArray(3);
        long money = fs.nextLong();
        long [] recipe = new long[3];

        for (char c : rec) {
            if (c == 'B') {
                recipe[0]++;
            } else if (c == 'S') {
                recipe[1]++;
            } else if (c == 'C') {
                recipe[2]++;
            }
        }
        out.println(binary_search_function(recipe, have, price, money));
        out.flush();
        out.close();
    }

    static long binary_search_function(long [] recipe, long [] have, long [] price, long money){
        long l = 0;
        long r = 1;
        while(is_good(recipe, have, price, money, r)) r*=2;
        while(r > l+1) {
            long m = (r + l)/2;
            if(is_good(recipe, have, price, money, m)) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    }


    static boolean is_good(long [] recipe, long [] have, long [] price, long money, long hamburgers) {
        long total_B_money = (recipe[0] * hamburgers - have[0]) * price[0];
        long total_S_money= (recipe[1] * hamburgers - have[1]) * price[1];
        long total_C_money = (recipe[2] * hamburgers - have[2]) * price[2];
        long total_money = Math.max(0, total_B_money) + Math.max(0, total_S_money) + Math.max(0, total_C_money);
        return total_money <= money;
    }

    static void shuffleArray(long[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            long a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
                // br = new BufferedReader(new FileReader("chat.txt"));
                st = new StringTokenizer("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String next() {
            if (st.hasMoreTokens())
                return st.nextToken();
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            String line = "";
            try {
                line = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return line;
        }

        public char nextChar() {
            return next().charAt(0);
        }

        public Integer[] nextIntegerArray(int n) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public char[] nextCharArray() {
            return nextLine().toCharArray();
        }
    }

}




