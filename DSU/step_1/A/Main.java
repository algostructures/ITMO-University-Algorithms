

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
        int t = 1;//fs.nextInt();
        while(t > 0) {
            t-=1;
            int n = fs.nextInt();
            int op = fs.nextInt();
            DSU dsu = new DSU(n);
            for (int i = 0; i < op; i++) {
                String s = fs.next();
                int a = fs.nextInt()-1;
                int b = fs.nextInt()-1;
                if(s.equals("union")) {
                    dsu.union(a, b);
                } else {
                    int A = dsu.get(a);
                    int B = dsu.get(b);
                    out.println(A == B ? "YES" : "NO");
                }
            }
        }
        out.flush();
        out.close();
    }

    static class DSU {
        int [] ranks;
        int [] parents;
        // int [] a;

        public DSU (int n) {
            ranks = new int[n];
            parents = new int[n];

            for(int i = 0; i < n; i++) {
                ranks[i] = 0;
                parents[i] = i;
            }
        }

        public int get(int a) {
            return parents[a] = (parents[a] == a ? a : get(parents[a]));
        }

        public void union(int a, int b) {
            a = get(a);
            b = get(b);
            if(ranks[a] == ranks[b]) {
                ranks[a]++;
            }
            if(ranks[a] > ranks[b]) {
                parents[b] = a;
            } else {
                parents[a] = b;
            }
        }
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




