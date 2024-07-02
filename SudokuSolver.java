/**
 * Simple SudokuSolver using Recursion Backtracking Method.
 *
 * June 2024
 */
public class SudokuSolver {
    /**
     * Solution.
     * 4 1 5 3 6 7 8 2 9
     * 2 3 7 8 9 1 5 4 6
     * 8 6 9 2 4 5 1 7 3
     * 3 7 6 4 1 2 9 8 5
     * 9 2 8 7 5 3 6 1 4
     * 5 4 1 9 8 6 2 3 7
     * 7 5 3 1 2 9 4 6 8
     * 6 8 2 5 3 4 7 9 1
     * 1 9 4 6 7 8 3 5 2
     */
    int[][] problem = new int[][]{  // Sample Problem
            {0,0,5,0,6,7,0,2,9},
            {0,3,0,0,9,0,5,0,0},
            {0,0,0,0,4,0,0,7,0},
            {0,7,0,0,0,0,0,8,5},
            {0,0,8,0,5,3,0,0,0},
            {5,4,1,0,8,0,2,0,0},
            {7,5,3,0,0,9,4,0,8},
            {0,8,0,5,0,0,0,9,1},
            {1,9,0,0,0,8,0,5,2}
    };
    final int sudokuSize = 9;
    int step = 0;
    public SudokuSolver() {
        System.out.println("Sudoku Size: "+sudokuSize);
        System.out.println("Problem: ");
        printSudoku(problem);
        solve();
    }

    public void solve(){
        if(solve(0,0)){     // start at the first cell
            System.out.println();
            System.out.println("Solution: ");
            printSudoku(problem);
        }else {
            System.out.println("Solution does not exist!");
        }
    }

    /**
     * Recursion method, recurse itself until the code reaches at the end of sudoku without returning false.
     * if the case returned false, go back to previous cell then check next number.
     * @param r
     * @param c
     * @return
     */
    public boolean solve(int r, int c){
        if(r == sudokuSize){    // ** base case -> if the sudoku is completely filled.
            return true;
        }
        if(c == sudokuSize) {     // move to next row
            return solve(r+1, 0);
        }
        if(problem[r][c] != 0){    // if already numbered, move to next cell
            return solve(r, c+1);
        }

        for (int i = 1; i <= sudokuSize; i++) {
            if(isSafe(r,c,i)){  // if number is safe, fill in this cell
                problem[r][c] = i;

                System.out.println();       // numbering step ++
                printSudoku(problem);
                step++;
                System.out.println("step: "+step);

                if(solve(r,c+1)){   // move to next cell
                    return true;    // ** if all the cells are filled, eventually return true
                }
                problem[r][c] = 0;     // if false returned, replace this cell to 0

                System.out.println();   // backtracking step ++
                printSudoku(problem);
                step++;
                System.out.println("step: "+step);
            }
        }
        return false;   // return false if the cell cannot be filled in any number.
    }

    /**
     * Return True if the number is safe to fill in the current cell.
     * @param r
     * @param c
     * @param n
     * @return
     */
    public boolean isSafe(int r, int c, int n){
        return !isInRow(r, n) && !isInColumn(c, n) && !isInBox(r, c, n);
    }

    /**
     * Check if number is in the row
     * @param row
     * @param n
     * @return
     */
    public boolean isInRow(int row, int n) {
        for (int c = 0; c < sudokuSize; c++) {
            if(problem[row][c] == n){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if number is in the column
     * @param column
     * @param n
     * @return
     */
    public boolean isInColumn(int column, int n) {
        for (int r = 0; r < sudokuSize; r++) {
            if(problem[r][column] == n){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if number is in the 3x3 box.
     * @param row
     * @param column
     * @param n
     * @return
     */
    public boolean isInBox(int row, int column, int n) {
        int starRow = row/3 * 3;
        int startCol = column/3 * 3;    // expect 0, 3, 6

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if(problem[r+starRow][c+startCol] == n){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Printing sudoku
     * @param sudoku -> 2-d array
     */
    public void printSudoku(int[][] sudoku){
        for (int r = 0; r < sudokuSize; r++) {
            for (int c = 0; c < sudokuSize; c++) {
                System.out.print(sudoku[r][c]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        SudokuSolver s = new SudokuSolver();
    }
}
