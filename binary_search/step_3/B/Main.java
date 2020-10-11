

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    final static long MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        int n = fs.nextInt();
        int k = fs.nextInt();
        long [] a = fs.nextLongArray(n);

        out.println((long) binary_search_function(a, k));

        out.flush();
        out.close();
    }

    static double binary_search_function(long [] a, int k){
        long l = 0;
        long r = Arrays.stream(a).sum();
        while(!is_good(a, r, k)) r*=2;
        while(r > l + 1) {
            long m = (r + l)/2;
            if(!is_good(a, m, k)) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }


    static boolean is_good(long [] a, long sum, int k) {
        long running_sum = 0;
        int count = 1;
        if(a.length < k) return false;
        if(Arrays.stream(a).max().getAsLong() > sum) return false;
        for(int i = 0; i < a.length; i++) {
            if(running_sum + a[i] > sum) {
                running_sum = a[i];
                count++;
            } else {
                running_sum += a[i];
            }
        }
        return count <= k;
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




