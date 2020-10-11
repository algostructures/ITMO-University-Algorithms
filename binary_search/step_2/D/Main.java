

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
            int m = fs.nextInt();
            int n = fs.nextInt();
            long [][] a = new long[n][3];

            for (int i = 0; i < n; i++) a[i] = fs.nextLongArray(3);
            ArrayList<Long> arl = new ArrayList<>();
            long answer = function_binary_search(a, m);
            for (long[] longs : a) {
                double tt = longs[0];
                double z = longs[1];
                double y = longs[2];
                arl.add(solve(tt, z, y, answer));
            }
            out.println(answer);
            long sum = 0;
            for(Long value : arl) {
                sum+=value;
                if(sum <= m)
                    out.print(value+" ");
                else
                    out.print(Math.max(m - (sum-value), 0)+" ");
            }
            out.println();
        }
        out.flush();
        out.close();
    }



    static long function_binary_search(long [][] a, long M){
        long l = -1;
        long r = 2;
        while(!good(a, r, M)) r *=2;
        while(r > l + 1) {
            long m = (r + l)/2;
            //System.out.println(l+" "+r);
            if(!good(a, m, M)) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    static boolean good(long [][] a, long T, long m){
        //ti, zi, yi
        long total = 0;
        for (long[] longs : a) {
            double t = longs[0];
            double z = longs[1];
            double y = longs[2];
            total += solve(t, z, y, T);
        }
        return m <= total;
    }

    private static long solve(double t, double z, double y, long T) {
        long balloons = 0;
        while(true) {
            T -= t;
            if(T >=0) {
                balloons++;
                if(balloons % z == 0)
                    T-=y;
            } else
                break;
        }
        return balloons;
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




