
/* This program allows you to play a level from Sokoban.
 *
 * The goal of the game is to put every box in a goal.
 *
 * Compile: javac Sokoban.java
 * Execute: java Sokoban
 *
 * @author RuiFerreira 58837
 */
import java.util.Scanner;

public class Sokoban{

 public static void main(String[] args) {

   System.out.println("Welcome to Sokoban!");
   Scanner sc = new Scanner(System.in);
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
   char direction;

   if (isValidGrid(grid) && goalsInGrid(grid,goals)) {
      print(grid,goals);
      do {
       direction = readOption(sc);
          if (direction != 'E') {
            move(grid,goals,direction);
            print(grid,goals);
          }
          else {
            System.out.println("Thank you for playing Sokoban!");
          }
      } while (direction != 'E');
   }

   else {
      System.out.println("Error! Start the game with valid grid and goals.");
   }
 }

 /**
  * Verifies if grid != null, grid is a matrix, grid.length>2, grid[0].length>2,
  * grid contains only digits from 1 to 5, grid contains exactly one occurrence of 5,
  * grid contains at least one occurrence of 2 and the number of occurences of 3 does
  * not exceed the number of occurences of 2.
  *
  * @param grid   matrix that represents the game map.
  * @return grid is valid or not
  * @ensures {@code true || false}
  */
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

 /**
  * Verifies if a position is valid on the grid which means that position != null,
  * position.length == 2, 0≤position[0]<grid.length and 0≤position[1]<grid[0].length.
  *
  * @param grid      matrix that represents the game map
  * @param position  array with length = 2 that serves as coordinates to verify it's validity
  * @return position is valid or not
  * @requires {@code isValidGrid(grid)}
  * @ensures {@code true || false}
  */
  public static boolean positionInGrid(int[][] grid, int[] position){
    
  return position != null && position.length == 2 && (position[0] >= 0 && position[0] <= grid.length) &&
  (position[1] >= 0 && position[1] <= grid[0].length);
  }

 /**
  * Verifies if the goals are assigned in the grid. It verifies four conditions:
  * (1) goals!=null
  * (2) positionInGrid(grid,goals[i]) for every 0≤i<goals.length
  * (3) every goal is represented either with a number 3 or 2 on the grid
  * (4) goals.length == occurencesOf2 on the grid
  *
  * @param grid     matrix that represents the game map
  * @param goals    coordinates of the goals represented on the goalsInGrid
  * @return if all the goals are assigned on the goalsInGrid
  * @requires {@code isValidGrid(grid)}
  * @ensures {@code true || false}
  */
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
    boolean goalsInGrid = condition1 && condition2 && condition3 && condition4;
    return goalsInGrid;
  }

  /**
   * Verifies if the character given by the user is a valid direction which is either
   * 'D','L','U','R' or 'E'.
   *
   * @param direction     character to verify if its valid
   * @return valid character or not
   * @ensures {@code true || false}
   */
  public static boolean isValidDirection(char direction){
    return (direction == 'R' || direction == 'L' ||
    direction == 'U' || direction == 'D' || direction == 'E');
  }

  /**
   * Reads a character from the input of the user, returns if valid,
   * while it's not valid it prints an error message and asks the player to
   * input the character until it's valid.
   *
   *
   * @param sc     Scanner to verify the character
   * @return valid character or not
   * @requires {@code sc != null}
   * @ensures {@code direction == 'E' || direction == 'U' || direction == 'D' || direction == 'L' || direction == 'R' ||}
   */
  public static char readOption(Scanner sc){

    String string = sc.nextLine();
    char direction = string.charAt(0);
      if (isValidDirection(direction)) {
      }
      else {
        do {
          System.out.println("Error!");
          System.out.println("Select one of the following options: (R)ight, (L)eft, (U)p, (D)own, (E)xit");
          string = sc.nextLine();
          direction = string.charAt(0);
        } while (!(isValidDirection(direction)));
      }
      return direction;
  }

  /**
   * Returns the moving array with two positions, the first indicates the movement
   * on the rows and the second the movement on the colums.
   *
   * @param direction     character with direction
   * @return array with coordinates for the direction the player wants to move
   * @requires {@code isValidDirection(direction)}
   * @ensures {@code \result.length==2 && \result[0]*\result[1]==0 && -1≤\result[i]≤1}
   */
  public static int[] delta(char direction){
    int[] delta = new int[2];
    if (direction == 'L') {
      delta[0] = 0; delta[1] = -1;
    }
    if (direction == 'U') {
      delta[0] = -1; delta[1] = 0;
    }
    if (direction == 'D') {
      delta[0] = 1; delta[1] = 0;
    }
    if (direction == 'R') {
      delta[0] = 0; delta[1] = 1;
    }

    return delta;
  }

  /**
   * This function returns an array with the position of the player on the grid.
   *
   *
   * @param grid     matrix that represents the game map
   * @return position of the player on the grid
   * @requires {@code isValidGrid(grid)}
   * @ensures {@code positionInGrid(grid,\result)}
   */
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

  /**
   * Returns if the position is free, which means is either a '1'(empty) or a '3'(goal).
   *
   * @param grid     matrix that represents the game map
   * @param position array with coordinates to verify on grid
   * @return if it's free or not
   * @requires {@code isValidGrid(grid) && positionInGrid(grid, position)}
   * @ensures {@code true || false}
   */
  public static boolean isAvailable(int[][] grid, int[] position){
    return grid[position[0]][position[1]] == 1 || grid[position[0]][position[1]] == 3;
  }

  /**
   * Verifies if the position belongs to a goal on the array goals
   *
   * @param grid     matrix that represents the game map
   * @param goals    coordinates of the goals represented on the goalsInGrid
   * @param position array with coordinates to verify on grid
   * @return if position belongs to a goal or not
   * @requires {@code isValidGrid(grid) && isValidDirection(direction) && goalsInGrid(grid,goals)}
   * @ensures {@code true || false}
   */
  public static boolean belongsTo(int[][] grid,int[][] goals,int[] position){
    boolean belongsTo = false;

    for (int x = 0;x < goals.length ;x++ ) {
      if (goals[x][0] == position[0] && goals[x][1] == position[1]) {
        belongsTo = true;
      }
    }
  return belongsTo;
  }

  /**
   * Verifies if the player is able to move in the direction specified
   *
   * @param grid      matrix that represents the game map
   * @param direction character with direction
   * @return if the player is able to move or not
   * @requires {@code isValidGrid(grid) && isValidDirection(direction)}
   * @ensures {@code true || false}
   */
  public static boolean ableToMove(int [][] grid,char direction){

    boolean ableToMove = false;

    int[] player = getPlayer(grid);

    int[] move = delta(direction);

    int[] position = new int [2];
    position[0] = player[0] + move[0];
    position[1] = player[1] + move[1];

    int[] position2 = new int [2];
    position2[0] = player[0] + move[0]*2;
    position2[1] = player[1] + move[1]*2;

    if (positionInGrid(grid,position)) {

      if (isAvailable(grid,position) || grid[position[0]][position[1]] == 2 && isAvailable(grid,position2)) {
      ableToMove = true;
      }
    }
    return ableToMove;
  }

  /**
   * Procedure that moves the player in the given direction if it's able to move
   * changing the grid numbers to an updated form. If it isn't able to move the procedure does nothing.
   *
   * @param grid      matrix that represents the game map
   * @param goals    coordinates of the goals represented on the goalsInGrid
   * @param direction character with direction
   * @requires {@code isValidGrid(grid) && isValidDirection(direction) && goalsInGrid(grid,goals)}
   */
  static void move(int [][] grid, int [][] goals, char direction){

    int[] player = getPlayer(grid);

    int[] move = delta(direction);

    int[] position = new int [2];
    position[0] = player[0] + move[0];
    position[1] = player[1] + move[1];

    int[] position2 = new int [2];
    position2[0] = player[0] + move[0]*2;
    position2[1] = player[1] + move[1]*2;

    if (ableToMove(grid,direction)) {
      if (belongsTo(grid,goals,player)) {
        grid[player[0]][player[1]] = 3;
      }
      else {
        grid[player[0]][player[1]] = 1;
      }
      if (isAvailable(grid,position)) {
        grid[position[0]][position[1]] = 5;
      }
      else {
        grid[position[0]][position[1]] = 5;
        grid[position2[0]][position2[1]] = 2;
      }
    }
  }

  /**
   * Procedure that prints the grid and a symbolic representation of the map complemented with
   * information on how to play.
   *
   *
   * @param grid      matrix that represents the game map
   * @param goals    coordinates of the goals represented on the goalsInGrid
   * @requires {@code isValidGrid(grid) && goalsInGrid(grid,goals)}
   */
  static void print(int[][] grid, int[][] goals){

    System.out.println("Consider that the Sokoban symbols are represented by the following digits: ");
    System.out.println("1 - blank   2 - box   3 - goal   4 - wall   5 - player");
    System.out.println("The symbolic representation on the right may help you while playing.");
    System.out.println("");

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
    System.out.println("Select one of the following options: (R)ight, (L)eft, (U)p, (D)own, (E)xit");
    }
  }
