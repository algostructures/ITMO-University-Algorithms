import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Main {
    final static long MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        char [] input = (fs.next()+"$").toCharArray();

        int length = input.length;
        int [] p = new int[length]; //ordering of indexes
        int [] c = new int[length]; //equivalence classes
        {
            //k=0;
            ArrayList<Pair<Integer, Integer>> a = new ArrayList<>();
            for (int i = 0; i < length; i++) a.add(new Pair((int)input[i], i));

            a.sort((o1, o2) -> o1.first.equals(o2.first) ? Integer.compare(o1.second, o2.second) : Integer.compare(o1.first, o2.first));

            for (int i = 0; i < length; i++) {
                p[i] = a.get(i).getSecond();
            }

            c[p[0]] = 0;

            for (int i = 1; i < length; i++) {
                if(a.get(i).first.equals(a.get(i-1).first))
                    c[p[i]] = c[p[i-1]];
                else
                    c[p[i]] = c[p[i-1]] + 1;
            }

        }

        int k = 0;

        while((1 << k) < length){

            for(int i = 0; i < length; i++) {
                p[i] = (p[i] - (1<<k) + length) % length;
            }

            p = count_sort(p, c);

            int [] c_new = new int[length];
            c_new[p[0]] = 0;
            for(int i = 1; i < length; i++) {
                Pair<Integer, Integer> prev = new Pair<>(c[p[i-1]], c[(p[i-1] + (1<<k)) % length]);
                Pair<Integer, Integer> now = new Pair<>(c[p[i]], c[(p[i] + (1<<k)) % length]);
                if(now.equals(prev))
                    c_new[p[i]] = c_new[p[i-1]];
                else
                    c_new[p[i]] = c_new[p[i-1]] + 1;
            }
            c=c_new;
            k++;
        }

        for (int i = 0; i < length; i++) {
            out.print(p[i]+" ");
        }

        out.close();
    }

    public static int[] count_sort(int [] p, int [] c) {
        int n = p.length;
        int [] count = new int[n];

        for(int i : c)
            count[i]++;

        int [] p_new = new int[n];
        int [] pos = new int[n];
        pos[0] = 0;
        for(int i = 1; i < n; i++) {
            pos[i] = pos[i-1] + count[i-1];
        }

        for(int current : p) {
            int i = c[current];
            p_new[pos[i]] = current;
            pos[i]++;
        }
        return p_new;
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
    

    public static int powoftwo(long n){
        int count = 0;
        while(n != 0) {
            n >>= 1;
            count++;
        }
        return count - 1;
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


