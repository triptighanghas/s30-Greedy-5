public class WildcardMatching {

    //TC: O(s.length*p.length)
    //SC: O(s.length*p.length)
    //approach: DP
    public boolean isMatchDPSolution(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i=1;i<=p.length();i++){
            if(p.charAt(i-1)=='*'){
                dp[0][i] = dp[0][i-1];
            }
        }
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=p.length();j++){
                if(p.charAt(j-1)=='*'){
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }else if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //TC: O(m*n) for worst case
    //SC: O(1)
    //approach: two pointers with also keeping track of last star appearance
    public boolean isMatchOptimizedSolution(String s, String p) {
        int sp = 0;
        int pp=0;
        int sstar=-1;
        int pstar=-1;
        while(sp<s.length()){
            if(pp< p.length() && (s.charAt(sp)==p.charAt(pp) || p.charAt(pp)=='?')){
                sp++;
                pp++;
            }else if(pp<p.length() && p.charAt(pp)=='*'){
                sstar = sp;
                pstar = pp;
                pp++;
            }else if(pstar==-1){
                return false;
            }else{
                pp=pstar+1;
                sp=sstar+1;
                sstar=sp;
            }

        }
        while(pp < p.length()){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }
        return true;
    }
}
