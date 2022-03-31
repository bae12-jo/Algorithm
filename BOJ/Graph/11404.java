import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_11404 {
	static int N, M;
	static int[][] dist;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// 플로이드 초기 거리 테이블 초기화
		dist = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 자기 자신으로 가는 길은 최소 비용이 0이다.
				if (i == j)	continue;
				// 자기 자신으로 가는 경우를 제외하고는 매우 큰 값(N개의 노드를 모두 거쳐서 가더라도 더 큰 값).
				dist[i][j] = INF;
			}
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 가는 경로가 하나가 아닐 수 있다. 따라서 그 중 최소 비용을 저장해두면 된다. (유방향 그래프인 경우 더 작은 경로 저장)
			dist[a][b] = Math.min(dist[a][b], cost);
			// dist[b][a] = Math.min(dist[b][a], cost);
		}

		// 플로이드 워셜 알고리즘
		// 노드를 1개부터 N개까지 거쳐가는 경우를 모두 고려한다.
		for (int k = 1; k <= N; k++) {
			// 노드 i에서 j로 가는 경우.
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					// k번째 노드를 거쳐가는 비용이 기존 비용보다 더 작은 경우 갱신
					// 또는 연결이 안되어있던 경우(INF) 연결 비용 갱신.
					if(dist[i][k]!=INF && dist[k][j]!=INF) dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 출력
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 연결이 안되어 있는 경우
				if (dist[i][j] == INF) {
					sb.append("0 ");
				} else {
					sb.append(dist[i][j] + " ");
				}
			}
			sb.append("\n");
		}
		System.out.println(String.valueOf(sb));
	}
}
