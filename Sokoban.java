public class Sokoban{

  public static void main(String[] args) {

   Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to Sokoban!");
    System.out.println("Consider that the Sokoban symbols are represented by the following digits: ");
    System.out.println("1 - blank   2 - box   3 - goal   4 - wall   5 - player");
    System.out.println("The symbolic representation on the right may help you while playing.");
    System.out.println("");

    int[][] grid = {{4,4,4,1,1,1,4,1},
                    {4,3,5,2,1,1,4,1},
                    {4,4,4,1,2,3,4,1},
                    {4,3,4,4,2,1,4,1},
                    {4,1,4,1,3,1,4,4},
                    {4,2,1,2,2,2,3,4},
                    {4,1,1,1,3,1,1,4},
                    {4,4,4,4,4,4,4,4}};

    int[][] goals = {{1,1},
                     {5,2},
                     {1,3},
                     {4,4},
                     {3,5},
                     {6,5},
                     {4,6}};

  //  int[] position = {4,6};

    if (!(isValidGrid(grid) && goalsInGrid(grid,position))) {
      System.out.println("Start the game with a valid grid and goals");
    }

    else {
      do {
        System.out.println("Select one of the following options: (R)ight, (L)eft, (U)p, (D)own, (E)xit ");
        print(grid,goals);
        readScanner(sc);
        if (ableToMove(grid, direction)) {
          move(grid,goals,direction);
          print(grid,goals);
        }
      } while (readScanner(sc) != 'e');
      System.out.println("Thank you for playing Sokoban!");
    }
  }

  public static int[] getPlayer(int[][] grid){
    int[] position = new int[2];

          for (int i = 0;i < grid.length;i++ ) {
            for (int j = 0;j < grid.length;j++ ) {
              if (grid[i][j] == 5) {
                position[0] = i;
                position[1] = j;
              }
            }
          }
  return position;
  }

  public static boolean isAvailable(int[][] grid, int[] position){

    boolean isAvailable = false;
    if (grid[position[0]][position[1]] == 1 || grid[position[0]][position[1]] == 3 ) {
      isAvailable = true;
    }

    return isAvailable;
  }

  public static boolean belongsTo(int[][] grid,int[][] goals,int[] position){

    boolean belongsTo = false;

    for (int x = 0;x < goals.length ;x++ ) {
      if (goals[x][0] == position[0] && goals[x][1] == position[1]) {
        belongsTo = true;
      }
    }

  return belongsTo;
  }


  public static boolean ableToMove(int [][] grid,char direction){
    boolean ableToMove = false;

    int[] playerPosition = new int[2];
    playerPosition[0] = getPlayer(grid);
    playerPosition[1] = getPlayer(grid);

    boolean firstConditionLeft = grid[playerPosition[0]][playerPosition[1]-1] == 1
    || grid[playerPosition[0]][playerPosition[1]-1] == 3;

    boolean secondConditionLeft = (grid[playerPosition[0]][playerPosition[1]-1] == 2)
    && (grid[playerPosition[0]][playerPosition[1]-2] == 1 ||
    grid[playerPosition[0]][playerPosition[1]-2] == 3);

    boolean firstConditionUp = grid[playerPosition[0]-1][playerPosition[1]] == 1
    || grid[playerPosition[0]-1][playerPosition[1]] == 3;

    boolean secondConditionUp = (grid[playerPosition[0]-1][playerPosition[1]] == 2)
    && (grid[playerPosition[0]-1][playerPosition[1]] == 1 ||
    grid[playerPosition[0]-1][playerPosition[1]] == 3);

    boolean firstConditionDown = grid[playerPosition[0]+1][playerPosition[1]] == 1
    || grid[playerPosition[0]+1][playerPosition[1]] == 3;

    boolean secondConditionDown = (grid[playerPosition[0]+1][playerPosition[1]] == 2)
    && (grid[playerPosition[0]+1][playerPosition[1]] == 1 ||
    grid[playerPosition[0]+1][playerPosition[1]] == 3);

    boolean firstConditionRight = grid[playerPosition[0]][playerPosition[1]+1] == 1
    || grid[playerPosition[0]][playerPosition[1]+1] == 3;

    boolean secondConditionRight = (grid[playerPosition[0]][playerPosition[1]+1] == 2)
    && (grid[playerPosition[0]][playerPosition[1]+2] == 1 ||
    grid[playerPosition[0]][playerPosition[1]+2] == 3);

    boolean up = firstConditionUp || secondConditionUp;
    boolean left = firstConditionLeft || secondConditionLeft;
    boolean right = firstConditionRight || secondConditionRight;
    boolean down = firstConditionDown || secondConditionDown;

     if (isValidDirection(direction) && (up||down||left||right) ) {
         ableToMove = true;
     }
     return ableToMove;
  }


  static void move(int [][] grid, int [][] goals, char direction){

          if (ableToMove(grid,direction)) {
            if (direction = 'D') {
              delta('D') = {1,0};
            }
            if (direction = 'E') {
              delta('E') = {-1,0};
            }
            if (direction = 'U') {
              delta('U') = {0,-1};
            }
            if (direction = 'D') {
              delta('D') = {0,1};
            }
          }
        }
  }

  static void print(int[][] grid, int[][] goals){

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.print(" | ");
        for (int m = 0; m < grid[i].length; m++) {

          if (grid[i][m] == 1) {
            System.out.print(" " + " ");
          }
          if (grid[i][m] == 2) {
            System.out.print("B" + " ");
          }
          if (grid[i][m] == 3) {
            System.out.print("G" + " ");
          }
          if (grid[i][m] == 4) {
            System.out.print("-" + " ");
          }
          if (grid[i][m] == 5) {
            System.out.print("P" + " ");
          }
        }
        System.out.println();

      }
    }

  }
