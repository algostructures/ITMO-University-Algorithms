import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
    final static long MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = fs.nextInt();
        int m = fs.nextInt();

        int a[] = fs.nextIntArray(n);
        SegmentTree st = new SegmentTree(n);
        st.build(a);
        for(int i = 0; i < m; i++) {
            int type = fs.nextInt();
            if(type == 2) {
                int l = fs.nextInt();
                int r = fs.nextInt();
                out.println(st.sum(l, r));
            }
            else {
                int index = fs.nextInt();
                int value = fs.nextInt();
                st.set(index, value);
            }
        }
        out.flush();
        out.close();
    }

    static class SegmentTree {
        int size;
        long sum[];

        SegmentTree(int n) {
            size = 1;
            while(size < n) size *= 2;
            sum = new long[2 * size];
        }

        public void build(int a[]) {
            build(a, 0, 0, size);
        }


        public void set(int i, int v) {
            set(i, v, 0, 0, size);
        }

        public long sum(int l, int r) {
            return sum(l, r, 0,0, size);
        }

        private void build(int[] a, int x, int lx, int rx) {
            if(rx - lx  == 1) {
                if (lx < a.length)
                    sum[x] = a[lx];
                return;
            }
            int m = (lx + rx) / 2;
            build(a, 2 * x + 1, lx, m);
            build(a, 2 * x + 2, m, rx);
            sum[x] = sum[2 * x + 1] + sum[2 * x + 2];
        }


        private void set(int i, int v, int x, int lx, int rx) {
            if(rx - lx == 1) {
                sum[x] = v;
                return;
            }
            int m = (lx + rx) / 2;
            if(i < m) {
                set(i, v, 2 * x + 1, lx, m);
            } else {
                set(i, v, 2 * x + 2, m, rx);
            }
            sum[x] = sum[x] = sum[2 * x + 1] + sum[2 * x + 2];
        }

        private long sum(int l, int r, int x, int lx, int rx) {
            if(lx >= r || l >= rx) return 0;
            if(lx >= l && rx <= r) return sum[x];

            int m = (lx + rx) / 2;

            long s1 = sum(l, r, 2 * x + 1, lx, m);
            long s2 = sum(l, r, 2 * x + 2, m, rx);

            return s1 + s2;
        }

    }

    public static class Pair<F, S> {
        private final F first; //first member of pair
        private final S second; //second member of pair

        public Pair(F first, S second) {
            super();
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }


        @Override
        public int hashCode() {
            return 31 * hashcode(first) + hashcode(second);
        }

        // todo move this to a helper class.
        private static int hashcode(Object o) {
            return o == null ? 0 : o.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pair))
                return false;
            if (this == obj)
                return true;
            return equal(first, ((Pair) obj).first)
                    && equal(second, ((Pair) obj).second);
        }

        // todo move this to a helper class.
        private boolean equal(Object o1, Object o2) {
            return o1 == null ? o2 == null : (o1 == o2 || o1.equals(o2));
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




