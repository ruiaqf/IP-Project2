import java.util.Scanner;

public class Sokoban{

  public static boolean isValidGrid(int[][] grid){

    boolean isValidGrid = true;
    int counterOf5 = 0;
    int counterOf2 = 0;
    int counterOf3 = 0;

    if (grid == null || grid.length < 2 || grid[0].length < 2) {
      isValidGrid = false;
    }

    for (int i = 0;i < grid.length;i++ ) {
      for (int j = 0;j < grid[0].length;j++ ) {
        if (grid[i][j] < 1 || grid[i][j] > 5) {
          isValidGrid = false;
        }
        if (grid[i][j] == 5) {
          counterOf5++;
        }
        if (grid[i][j] == 2) {
          counterOf2++;
        }
        if (grid[i][j] == 3) {
          counterOf3++;
        }
      }
    }

    if (counterOf5 != 1) {
      isValidGrid = false;
    }
    if (counterOf2 < 1) {
      isValidGrid = false;
    }
    if (counterOf3 > counterOf2) {
      isValidGrid = false;
    }

    return isValidGrid;
  }


  public static boolean positionInGrid(int[][] grid, int[] position){
    boolean positionInGrid = position != null && position.length == 2 && (position[0] >= 0 && position[0] <= grid.length) &&
    (position[1] >= 0 && position[1] <= grid[0].length);
  return positionInGrid;
  }


  public static boolean goalsInGrid(int[][] grid,int[][] goals){

    boolean condition1 = goals != null;
    boolean condition2 = true;
    boolean condition3 = true;
    int occurrencesOf2 = 0;
    for (int i = 0;i < goals.length ;i++ ) {
        if (!(grid[goals[i][0]][goals[i][1]] == 2 || grid[goals[i][0]][goals[i][1]] == 3)) {
          condition3 = false;
        }
        if (!(positionInGrid(grid,goals[i]))) {
          condition2 = false;
        }
      }
    for (int i = 0;i < grid.length ;i++ ) {
      for (int j = 0;j < grid[0].length ;j++ ) {
        if (grid[i][j] == 2) {
          occurrencesOf2++;
        }
      }
    }
    boolean condition4 = goals.length == occurrencesOf2;
    boolean goalsInGrid = condition1 || condition2 || condition3 || condition4;
    return goalsInGrid;
  }


  public static boolean isValidDirection(char direction){

    boolean directionUpperCase = (direction == 'R' || direction == 'L' ||
    direction == 'U' || direction == 'D' || direction == 'E');

    boolean directionLowerCase = (direction == 'r' || direction == 'l' ||
    direction == 'u' || direction == 'd' || direction == 'e');

    return directionLowerCase || directionUpperCase;

  }

  public static void readOption(Scanner sc){

    String string = sc.nextLine();
    char readOption = string.charAt(0);
      if (isValidDirection(readOption)) {
        System.out.println(readOption);
      }
      else {
        do {
          System.out.println("Error!");
          System.out.println("Select one of the following options: (R)ight, (L)eft, (U)p, (D)own, (E)xit");
          string = sc.nextLine();
          readOption = string.charAt(0);
        } while (!(isValidDirection(readOption)));
      }
  }

  public static int[] delta(char direction){
    int[] delta = new int[2];
    if (direction == 'l' || direction == 'L') {
      delta[0] = 0; delta[1] = -1;
    }
    if (direction == 'u' || direction == 'U') {
      delta[0] = -1; delta[1] = 0;
    }
    if (direction == 'd' || direction == 'D') {
      delta[0] = 1; delta[1] = 0;
    }
    if (direction == 'r' || direction == 'R') {
      delta[0] = 0; delta[1] = 1;
    }

    return delta;
  }

  public static int[] getPlayer(int[][] grid){
    int[] position = new int[2];

          for (int i = 0;i < grid.length;i++ ) {
            for (int j = 0;j < grid[0].length;j++ ) {
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

    boolean conditionA = (grid[getPlayer(grid)[0] + delta(direction)[0]][getPlayer(grid)[1] + delta(direction)[1]] == 1) ||
    (grid[getPlayer(grid)[0] + delta(direction)[0]][getPlayer(grid)[1] + delta(direction)[1]] == 3);

    boolean conditionB = (grid[getPlayer(grid)[0] + delta(direction)[0]][getPlayer(grid)[1] + delta(direction)[1]] == 2) &&
    ((grid[getPlayer(grid)[0] + (delta(direction)[0]*2)][getPlayer(grid)[1] + (delta(direction)[1]*2)] == 1) ||
    (grid[getPlayer(grid)[0] + (delta(direction)[0]*2)][getPlayer(grid)[1] + (delta(direction)[1]*2)] == 3));

    boolean ableToMove = conditionA || conditionB;
    return ableToMove;
  }


  static void move(int [][] grid, int [][] goals, char direction){

          if (ableToMove(grid,direction)) {
            grid[getPlayer(grid)[0] + delta(direction)[0]][getPlayer(grid)[1] + delta(direction)[1]] = 5;
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
          int[] position = new int[2];
          position[0] = i;
          position[1] = m;
          if (grid[i][m] == 2 && belongsTo(grid,goals,position)) {
            System.out.print("*" + " ");
          }
          if (grid[i][m] == 2 && !(belongsTo(grid,goals,position))) {
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
                       {2,5},
                       {3,1},
                       {4,4},
                       {5,3},
                       {5,6},
                       {6,4}};
                       move(grid,goals,'d');
                       print(grid,goals);

    }
  }
