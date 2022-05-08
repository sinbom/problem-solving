package boj.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj3176 {

    public static int N;
    
    public static int K;

    public static int M;

    public static int[] depth;

    public static int[][] parent;

    public static List[] tree;

    // 도로 네트워크 추가변수
    public static int[][] minDist;

    public static int[][] maxDist;

    public static int min, max;

    public static class edge {
        int target, cost;

        public edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 1. 입력 & 변수 준비
        N = Integer.parseInt(br.readLine());

        // 2^K > N인 K 찾기
        K = 0;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }

        // LCA 관련 변수 초기화
        depth = new int[N + 1];
        parent = new int[K][N + 1];

        // 도로 네트워크 변수 초기화
        minDist = new int[K][N + 1];
        maxDist = new int[K][N + 1];

        // TREE 변수 초기화
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<edge>();
        }

        int a, b, c;
        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            // 양방향 간선
            tree[a].add(new edge(b, c));
            tree[b].add(new edge(a, c));
        }

        // 2. DEPTH 확인
        dfs(1, 1);

        // 3. 2^N 까지 parent 채우기
        fillParent();

        // 4. LCA 진행
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            lca(a, b);
            sb.append(min + " " + max + "\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int id, int cnt) {
        depth[id] = cnt;

        int len = tree[id].size();
        for (int i = 0; i < len; i++) {
            edge next = (edge) tree[id].get(i);
            if (depth[next.target] == 0) {
                dfs(next.target, cnt + 1);
                parent[0][next.target] = id;

                minDist[0][next.target] = next.cost;
                maxDist[0][next.target] = next.cost;

            }
        }
    }

    public static void fillParent() {
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];

                minDist[i][j] = Math.min(minDist[i - 1][j], minDist[i - 1][parent[i - 1][j]]);
                maxDist[i][j] = Math.max(maxDist[i - 1][j], maxDist[i - 1][parent[i - 1][j]]);
            }
        }
    }

    public static void lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        min = Integer.MAX_VALUE;
        max = -1;

        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                min = Math.min(min, minDist[i][a]);
                max = Math.max(max, maxDist[i][a]);

                a = parent[i][a];
            }
        }

        if (a == b) {
            return;
        }

        for (int i = K - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                min = Math.min(min, Math.min(minDist[i][a], minDist[i][b]));
                max = Math.max(max, Math.max(maxDist[i][a], maxDist[i][b]));

                a = parent[i][a];
                b = parent[i][b];
            }
        }

        min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
        max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));

    }
}


