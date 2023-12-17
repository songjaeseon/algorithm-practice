import java.util.*;
class Solution {
    static int mineralsSize;
    static String[] mineral;
    public int solution(int[] picks, String[] minerals) {
        int min = 1250;
        Queue<int[]> que = new LinkedList<>();
        mineralsSize = minerals.length;
        mineral = minerals;
        
        if(picks[0] > 0){
            que.add(new int[]{picks[0]-1, picks[1], picks[2], 0, 5, 0});
        }
        if(picks[1] > 0){
            que.add(new int[]{picks[0], picks[1]-1, picks[2], 0, 5, 1});
        }
        if(picks[2] > 0){
            que.add(new int[]{picks[0], picks[1], picks[2]-1, 0, 5, 2});
        }
        
        while(!que.isEmpty()){
            int[] tmp = que.poll();
            int pick_0 = tmp[0];
            int pick_1 = tmp[1];
            int pick_2 = tmp[2];
            int res = tmp[3];
            int cnt = tmp[4];
            int whatpicks = tmp[5];
    
            res += counting(whatpicks, cnt);
            if(pick_0 == 0 && pick_1 == 0 && pick_2 == 0){
                min = Math.min(res, min);
                continue;    
            }
            
            if(cnt >= minerals.length){
                min = Math.min(res, min);
                continue;
            }
           
            if(pick_0 > 0){
                que.add(new int[]{pick_0-1, pick_1, pick_2, res, cnt + 5, 0});
            }
            if(pick_1 > 0){
                que.add(new int[]{pick_0, pick_1-1, pick_2, res, cnt + 5, 1});
            }
            if(pick_2 > 0){
                que.add(new int[]{pick_0, pick_1, pick_2-1, res, cnt + 5, 2});
            }
        }
        return min;
    }
    
    public int counting(int whatpicks, int cnt){
        int check_cnt = cnt;
        int tired = 0;
        int[][] tired_map = new int[][]{{1,1,1},{5,1,1},{25,5,1}};
        if(cnt >= mineralsSize){
            check_cnt = mineralsSize;
        }
        
        for(int i = cnt - 5; i < check_cnt; i++){
            if(mineral[i].equals("diamond")){
                tired += tired_map[whatpicks][0];
                continue;
            }
            if(mineral[i].equals("iron")){
                tired += tired_map[whatpicks][1];
                continue;
            }
            if(mineral[i].equals("stone")){
                tired += tired_map[whatpicks][2];
                continue;
            }
            
        }
        return tired;
    }
}
