

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

        int t = 1;//fs.nextInt();
        while(t > 0) {
            t-=1;
            int n = fs.nextInt();

            int [] a = fs.nextIntArray(n);
            Arrays.sort(a);
            int k = fs.nextInt();
            for (int i = 0; i < k; i++) {
                int l = fs.nextInt();
                int r = fs.nextInt();
                int index_r = binary_search_upper(a, n, l);
                int index_l = binary_search_lower(a, n, r);
                out.println(index_l - index_r + 1);
            }
        }
        out.flush();
        out.close();
    }

    static int binary_search_upper (int [] a, int n, int x) {
        int l = -1; //
        int r = n; // a[r] >= x
        while(r > l + 1) {
            int m = (l+r)/2;
            if(a[m] < x) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    static int binary_search_lower(int [] a, int n, int x) {
        int l = -1; // a[l] <= x
        int r = n; //
        while(r > l + 1) {
            int m = (l+r)/2;
            if(a[m] <= x) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
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




