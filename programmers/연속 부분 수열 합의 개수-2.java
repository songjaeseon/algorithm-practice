import java.util.*;
class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> hs = new HashSet<>();
        for(int j = 0; j < elements.length; j++){
            for(int i = 1; i <= elements.length; i++){
                int res = 0;
                for(int mi = j; mi < j + i; mi++){
                    res += elements[mi % elements.length];
                }
                hs.add(res);
            }    
        }
        return hs.size();
    }
}
