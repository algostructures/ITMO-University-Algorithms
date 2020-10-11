

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class A {
    final static long MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int t = 1;//fs.nextInt();
        while(t > 0) {
            t-=1;
            int n = fs.nextInt();
            int [] a = fs.nextIntArray(n);
            int [] filler_array = new int[a.length];
            int [] answer = new int[n];
            SegmentTree segmentTree = new SegmentTree(n);
            Arrays.fill(filler_array, 1);
            segmentTree.build(filler_array);

            for(int i = n-1; i >= 0; --i) {
                int lx = (int) segmentTree.find(a[i]).value;
                answer[i] = lx + 1;
                segmentTree.set(lx, 0);
            }
            for (int i = 0; i < n; i++) {
                out.print(answer[i]+" ");
            }
            out.println();
        }
        out.flush();
        out.close();
    }

    static class ITEM {
        long value;
        public ITEM(long sum) {
            this.value = sum;
        }
    }

    static class SegmentTree {
        int size;
        ITEM [] calc;

        private ITEM NUETRAL_ELEMENT() {
            return new ITEM(0);
        }

        private ITEM MERGE(ITEM left, ITEM right) {
            return new ITEM(left.value + right.value);
        }

        private ITEM SINGLE_ELEMENT(int v) {
            return new ITEM(v);
        }

        SegmentTree(int n) {
            size = 1;
            while(size < n) size*=2;
            calc = new ITEM[2 * size];
        }

        public void build(int a[]) {
            build(a, 0, 0, size);
        }

        public void set(int i, int v) {
            set(i, v, 0, 0, size);
        }

        public ITEM calc(int l, int r) {
            return calc(l, r, 0, 0, size);
        }

        public ITEM find(int k) {
            return find(k, 0, 0, size);
        }

        private ITEM find(int k, int x, int lx, int rx) {
            //if(calc[x].value < k) return new ITEM(-1);
            if(rx - lx == 1) return new ITEM(lx);
            int m = (lx + rx) / 2;
            ITEM right = calc[2* x + 2];
            //ITEM left = calc[2 * x + 1];
            if(right.value > k) {
                return find(k, 2 * x + 2, m, rx);
            } else {
                return find(k - (int)right.value, 2 * x + 1, lx, m);
            }
        }

        private void build(int[] a, int x, int lx, int rx) {
            if(rx - lx == 1) {
                calc[x] = lx < a.length ? SINGLE_ELEMENT(a[lx]) : NUETRAL_ELEMENT();
                return;
            }
            int m = (lx + rx) / 2;
            build(a, 2 * x + 1, lx, m);
            build(a, 2 * x + 2, m, rx);
            calc[x] = MERGE(calc[2 * x + 1], calc[2 * x + 2]);
        }

        private void set(int i, int v, int x, int lx, int rx) {
            if(rx - lx == 1) {
                calc[x] = new ITEM(SINGLE_ELEMENT(v).value);
                return;
            }
            int m = (lx + rx) / 2;
            if(i < m) {
                set(i, v, 2 * x + 1, lx, m);
            } else {
                set(i, v, 2 * x + 2, m, rx);
            }
            calc[x] = MERGE(calc[2 * x + 1], calc[2 * x + 2]);
        }

       private ITEM calc(int l, int r, int x, int lx, int rx) {
            if(lx >= r || l >= rx) return NUETRAL_ELEMENT();
            if(lx >= l && rx <= r) return calc[x];

            int m = (lx + rx) / 2;

            ITEM left = calc(l, r, 2 * x + 1, lx, m);
            ITEM right = calc(l, r, 2 * x + 2, m, rx);

            return MERGE(left, right);
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




