class Main {
  
  private static final int gridSize = 9;
  
  public static void main(String[] args) {
    
    int[][] board = {
        {4 , 8, 0, 0, 3, 5, 0, 9, 2},
        {6, 3, 9, 2, 0, 4, 0, 0, 8},
        {7, 5, 2, 9, 0, 8, 1, 4, 3},
        {0, 0, 7, 0, 4, 3, 0, 1, 0},
        {0, 6, 5, 1, 0, 0, 4, 0, 0},
        {1, 0, 0, 0, 0, 0, 0, 2, 0},
        {8, 0, 6, 5, 0, 1, 0, 3, 4},
        {5, 9, 0, 3, 0, 0, 8, 0, 0},
        {2, 0, 3, 0, 8, 6, 0, 0, 5} 
      };
    //The 0's represent an empty square on a real sudoku
    System.out.println();
    
    printBoard(board);
    
    if (solveBoard(board)) {
      
      System.out.println();
      System.out.println("Solved successfully!");
      System.out.println();
    }
    else {
      
      System.out.println("Unsolvable board :(");
    }
    
    printBoard(board);
    
  }
  
  
  private static void printBoard(int[][] board) {
    
    for (int row = 0; row < gridSize; row++) {
      
      if (row % 3 == 0 && row != 0) {
        
        System.out.println("-----------");
        
      }
      for (int column = 0; column < gridSize; column++) {
        if (column % 3 == 0 && column != 0) {
          
          System.out.print("|");
          
        }
        
        System.out.print(board[row][column]);
        
      }
      
      System.out.println();
      
    }
  }


  private static boolean isNumberInRow(int[][] board, int number, int row) {
    
    for (int i = 0; i < gridSize; i++) {
      
      if (board[row][i] == number) {
        
        return true;
        
      }
    }
    
    return false;
    
  }
  
  private static boolean isNumberInColumn(int[][] board, int number, int column) {
    
    for (int i = 0; i < gridSize; i++) {
      
      if (board[i][column] == number) {
        
        return true;
        
      }
    }
    
    return false;
    
  }
  
  private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
    
    int localBoxRow = row - row % 3;
    
    int localBoxColumn = column - column % 3;
    
    for (int i = localBoxRow; i < localBoxRow + 3; i++) {
      
      for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
        
        if (board[i][j] == number) {
          
          return true;
          
        }
      }
    }
    
    return false;
  }
  
  private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
    
    return !isNumberInRow(board, number, row) &&
        !isNumberInColumn(board, number, column) &&
        !isNumberInBox(board, number, row, column);
  }
  
  private static boolean solveBoard(int[][] board) {
    
    for (int row = 0; row < gridSize; row++) {
      
      for (int column = 0; column < gridSize; column++) {
        
        if (board[row][column] == 0) {
          
          for (int numberToTry = 1; numberToTry <= gridSize; numberToTry++) {
            
            if (isValidPlacement(board, numberToTry, row, column)) {
              
              board[row][column] = numberToTry;
              
              if (solveBoard(board)) {
                
                return true;
                
              }
                
              else {
                
                board[row][column] = 0;
                
              }
            }
          }
          
          return false;
          
        }
      }
    }
    
    return true;
    
  }
}