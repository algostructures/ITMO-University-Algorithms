

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

        char [] data = fs.nextCharArray();
        char [] pattern = fs.nextCharArray();
        int [] delete = fs.nextIntArray(data.length);
        out.println(binary_search(data, pattern, delete)+1);
        out.flush();
        out.close();
    }

    static int binary_search(char [] data, char [] pattern, int [] a) {
        int l = -1;
        int r = a.length;
        while(r > l+1) {
            int m = (l + r)/2;
            if(good_data(m, a, data, pattern)) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    }

    static boolean isSubsequence (char [] data, char [] pattern) {
        int data_pointer = 0;
        int pattern_pointer = 0;

        while(data_pointer < data.length && pattern_pointer < pattern.length) {
            if(pattern[pattern_pointer] == data[data_pointer]) {
                data_pointer++;
                pattern_pointer++;
            } else {
                data_pointer++;
            }
        }
        return pattern_pointer == pattern.length;
    }

    static boolean good_data(int index, int [] a, char [] data, char [] pattern) {
        char [] temp = data.clone();
        for(int i = 0; i <= index; i++) {
            temp[a[i]-1] = '1';
        }
        return isSubsequence(temp, pattern);
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




