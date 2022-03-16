package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

    public class Boj2064 {
        public static void main(String[] args) throws IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
                int n = Integer.parseInt(reader.readLine());
                int[] netIp = new int[n];
                int netAddress;
                int netMask = 0;

                for (int i = 0; i < n; i++) {
                    StringTokenizer st = new StringTokenizer(reader.readLine(), ".");
                    int temp = 0;

                    for (int j = 0; j < 4; j++) {
                        int m = Integer.parseInt(st.nextToken());
                        temp <<= 8;
                        temp += m;
                    }

                    netIp[i] = temp;
                }

                for (int i = 31; i >= 0; i--) {
                    int bit = 1 << i;
                    boolean check = false;

                    for (int j = 1; j < n; j++) {
                        if ((netIp[0] & bit) != (netIp[j] & bit)) {
                            check = true;
                            break;
                        }
                    }

                    if (check) {
                        break;
                    } else {
                        netMask |= bit;
                    }
                }

                netAddress = netIp[0] & netMask;

                StringBuilder address = new StringBuilder();
                StringBuilder mask = new StringBuilder();

                int check = (1 << 8) - 1;
                int k = 3;

                while (true) {
                    int aNum = netAddress >> (8 * k) & check;
                    int mNum = netMask >> (8 * k) & check;

                    address.append(aNum);
                    mask.append(mNum);
                    netAddress &= ((1 << (8 * k)) - 1);
                    netMask &= ((1 << (8 * k)) - 1);
                    k--;

                    if (k == -1) {
                        break;
                    }

                    address.append(".");
                    mask.append(".");
                }

                writer.write(address.toString() + "\n");
                writer.write(mask.toString());
            }
        }
    }


