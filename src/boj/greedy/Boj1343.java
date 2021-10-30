package boj.greedy;

import java.io.*;

public class Boj1343 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String s = reader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;

            for (int i = 0; i < s.length() + 1; i++) {
                boolean isLast = i == s.length();
                if (isLast || s.charAt(i) == '.') {
                    int divided = count / 4;
                    count -= divided * 4;

                    for (int j = 0; j < divided; j++) {
                        stringBuilder.append("AAAA");
                    }

                    int remained = count % 2;

                    if (remained != 0) {
                        stringBuilder = new StringBuilder("-1");
                        break;
                    }

                    divided = count / 2;
                    count = 0;

                    for (int j = 0; j < divided; j++) {
                        stringBuilder.append("BB");
                    }

                    if (!isLast) {
                        stringBuilder.append('.');
                    }
                } else {
                    count++;
                }

            }
            writer.write(stringBuilder.toString());
        }

    }
}
