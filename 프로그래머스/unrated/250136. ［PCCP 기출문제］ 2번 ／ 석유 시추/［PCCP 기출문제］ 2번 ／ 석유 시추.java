import java.util.*;

public class Solution {
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        int[][] group = new int[n][m]; // 각 칸의 석유 덩어리 ID를 저장
        Map<Integer, Integer> groupSize = new HashMap<>(); // 석유 덩어리 ID와 그 크기를 저장
        int groupId = 1; // 석유 덩어리 ID

        // 전체 맵을 스캔하여 석유 덩어리를 찾고 크기를 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && group[i][j] == 0) {
                    int size = bfs(land, group, i, j, groupId);
                    groupSize.put(groupId, size);
                    groupId++;
                }
            }
        }

        // 각 열에 대해 석유 덩어리의 크기를 합산
        int maxOil = 0;
        for (int j = 0; j < m; j++) {
            Set<Integer> countedGroups = new HashSet<>();
            int columnOil = 0;
            for (int i = 0; i < n; i++) {
                int currentGroupId = group[i][j];
                if (currentGroupId != 0 && !countedGroups.contains(currentGroupId)) {
                    columnOil += groupSize.get(currentGroupId);
                    countedGroups.add(currentGroupId);
                }
            }
            maxOil = Math.max(maxOil, columnOil);
        }

        return maxOil;
    }

    private int bfs(int[][] land, int[][] group, int x, int y, int groupId) {
        int n = land.length;
        int m = land[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        group[x][y] = groupId;
        int size = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && land[nx][ny] == 1 && group[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                    group[nx][ny] = groupId;
                    size++;
                }
            }
        }

        return size;
    }
}