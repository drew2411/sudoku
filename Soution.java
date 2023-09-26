class Solution {
    boolean modify[][]=new boolean[9][9];
    int result[][]=new int[9][9];
    public void solveSudoku(char[][] board) {
        markModifiable(board);
        recurse(0);
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                board[i][j]= Integer.toString(result[i][j]).charAt(0);
            }
        }
    }

    void markModifiable(char[][]board)
    {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.')
                {
                    modify[i][j]=true;
                }
                else modify[i][j]=false;
                if(modify[i][j]==false)result[i][j]=Integer.parseInt(String.valueOf(board[i][j]));
            }
        }
    }
    boolean recurse(int idx){
        if(idx==81) return true;

        int row=idx/9;
        int col=idx%9;
        for(int candidate=1;candidate<=9;candidate++)
        {
                if(modify[row][col]==true)
                {
                    result [row][col]=candidate;
                    if(isValid(row,col)&&recurse(idx+1)) return true;
                }
                else return recurse(idx+1);
                

        }
        result[row][col]=0;
        return false;
    }
    
    boolean isValid(int i,int j)
    {
        return validRow(i)&&validCol(j)&&validBlock(i,j);
    }
    boolean validRow(int i)
    {
        boolean seen[]=new boolean[10];
        for(int j=0;j<9;j++)
        {
            int temp=result[i][j];
            if(seen[temp] && temp!=0) return false;
            seen[temp]=true;
        }
        return true;
    }
    boolean validCol(int j)
    {
        boolean seen[]=new boolean[10];
        for(int i=0;i<9;i++)
        {
            int temp=result[i][j];
            if(seen[temp] && temp!=0) return false;
            seen[temp]=true;
        }
        return true;
    }
    boolean validBlock(int r,int c)
    {
        boolean seen[]=new boolean[10];
        for(int i=(r/3)*3; i<(r/3)*3+3 ;i++ ){
            for(int j= (c/3)*3 ;j<(c/3)*3+3;j++){
                int temp=result[i][j];
                if(seen[temp] && temp!=0) return false;
                seen[temp]=true;
            }
        }
        return true;
    }

}
