import java.util.*;
class Solution {
    static char[][] map;
    static int R_i, R_j, G_i, G_j = 0;
    
    public int solution(String[] board) {
        map = new char[board.length][board[0].length()];
        Queue<int[]> que = new LinkedList<>();
        int[][] dp = new int[board.length][board[0].length()];
        boolean[][] visited = new boolean[board.length][board[0].length()];
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length(); j++){
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R'){
                    R_i = i;
                    R_j = j;
                    continue;
                }
                if(map[i][j] == 'G'){
                    G_i = i;
                    G_j = j;
                    continue;
                }
                //이렇게 저장하면 조건문 2번을 지속적으로 찾아야됨 불편,,
            }
        }
      
        
        // {처음 행, 처음 열, 방향, cnt}
        // 방향-> 1:상, 2:하, 3:좌, 4:우
        dp[R_i][R_j] = 0;
        
        //장애물 조건 추가해야됨.
        // 각각의 방향으로 한칸만 이동해도 장애물 또는 맵을 벗어나지않는 경우
        if(R_i != 0){
            if(map[R_i - 1][R_j] != 'D'){
                que.add(new int[]{R_i, R_j, 0, 1});   
            }    
        }
        if(R_i != map.length - 1){
            if(map[R_i + 1][R_j] != 'D'){
                que.add(new int[]{R_i, R_j, 1, 1});    
            }
        }
        if(R_j != 0){
            if(map[R_i][R_j - 1] != 'D'){
                que.add(new int[]{R_i, R_j, 2, 1});    
            }
        }
        if(R_j != map[0].length - 1){
            if(map[R_i][R_j + 1] != 'D'){
                que.add(new int[]{R_i, R_j, 3, 1});    
            }
        }
        
        while(!que.isEmpty()){
            int[] tmp = que.poll();
            int ci = tmp[0];
            int cj = tmp[1];
            int direction = tmp[2];
            int cnt = tmp[3];
           
            int[] slidingResult = sliding(ci, cj, direction);
            int sri = slidingResult[0];
            int srj = slidingResult[1];
            
            if(sri == G_i && srj == G_j){
                return cnt;
            }
            
            if(sri == R_i && srj == R_j){
                continue;
            }
            
            if(dp[sri][srj] > 0){
                if(dp[sri][srj] <= cnt){
                    continue;
                }else{
                    dp[sri][srj] = cnt;
                }
            }
            
            if(dp[sri][srj] == 0){
                dp[sri][srj] = cnt;
            }
            
            // 각각의 방향으로 한칸만 이동해도 장애물 또는 맵을 벗어나지않는 경우
            if(sri != 0){
                if(map[sri - 1][srj] != 'D'){
                    que.add(new int[]{sri, srj, 0, cnt + 1});
                }  
            }
            if(sri != map.length - 1){
                if(map[sri + 1][srj] != 'D'){
                    que.add(new int[]{sri, srj, 1, cnt + 1});    
                }
            }
            if(srj != 0){
                if(map[sri][srj - 1] != 'D'){
                    que.add(new int[]{sri, srj, 2, cnt + 1});        
                }
            }
            if(srj != map[0].length - 1){
                if(map[sri][srj + 1] != 'D'){
                    que.add(new int[]{sri, srj, 3, cnt + 1});
                }
            }
            
        }
        return -1;
    }
    
    public int[] sliding(int i, int j, int direction){
        if(direction == 0){
            for(int mi = i; mi > 0; mi--){
                if(map[mi - 1][j] == 'D'){
                    return new int[]{mi, j};
                }
            }
            return new int[]{0,j};
        }else if(direction == 1){
            for(int mi = i; mi < map.length - 1; mi++){
                if(map[mi + 1][j] == 'D'){
                    return new int[]{mi, j};
                }
            }
            return new int[]{map.length - 1,j};
        }else if(direction == 2){
            for(int mj = j; mj > 0; mj--){
                if(map[i][mj - 1] == 'D'){
                    return new int[]{i, mj};
                }
            }
            return new int[]{i,0};
        }else{
            for(int mj = j; mj < map[0].length - 1; mj++){
                if(map[i][mj + 1] == 'D'){
                    return new int[]{i, mj};
                }
            }
            return new int[]{i,map[0].length - 1};
        }
    }
}

/*
    1. map[][]으로 바꾸면서 r이랑 g값 어딨는지 저장해두자.
    2. 상,하,좌,우로 이동했을시 어디에 도착하는지 판별해주는 함수
    3. bfs로 접근해서 상하좌우 다 넣어서 가장 먼저 도착하는 곳이 증답
*/
