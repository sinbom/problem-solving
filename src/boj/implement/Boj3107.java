package boj.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Boj3107 {

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String str = reader.readLine().trim();
            String[] list = str.split(":");
            List<String> li = new ArrayList<>();
            List<String> temp = new ArrayList<>();
            int part = 0;
            boolean add = false;

            for (String s : list) {
                int len = s.length();

                if (len > 0) {
                    part++;
                    if (len < 4) {
                        StringBuilder sBuilder = new StringBuilder(s);

                        while (len < 4) {
                            sBuilder.insert(0, "0");
                            len++;
                        }

                        s = sBuilder.toString();
                    }
                } else {
                    s = "-1";
                }

                li.add(s);
            }


            for (String ss : li) {
                if (ss.equals("-1")) {
                    if (add) {
                        continue;
                    }
                    int repeat = 8 - part;

                    while (repeat > 0) {
                        temp.add("0000");
                        repeat--;
                    }

                    add = true;
                } else {
                    temp.add(ss);
                }
            }

            while (part < 8) {
                temp.add("0000");
                part++;
            }

            for (int i = 0; i < 8; i++) {
                writer.write(temp.get(i) + "");

                if (i < 7) {
                    writer.write(":");
                }
            }
        }
    }

}


