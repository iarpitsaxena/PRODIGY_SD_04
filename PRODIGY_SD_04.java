public class PRODIGY_SD_04 {

    public static void main(String[] args) {
        int[][] puzzle = {
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };

        if (solvePuzzle(puzzle)) {
            System.out.println("Sudoku puzzle solved successfully:");
            displayPuzzle(puzzle);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }

    private static boolean solvePuzzle(int[][] grid) {
        int[] emptySpot = findEmptySpot(grid);

        if (emptySpot == null) {
            // No empty spot found, puzzle is solved
            return true;
        }

        int row = emptySpot[0];
        int col = emptySpot[1];

        for (int num = 1; num <= 9; num++) {
            if (isValidPlacement(grid, row, col, num)) {
                // Try placing the number
                grid[row][col] = num;

                // Recursively attempt to solve the rest of the puzzle
                if (solvePuzzle(grid)) {
                    return true;
                }

                // If placing the number didn't lead to a solution, backtrack
                grid[row][col] = 0;
            }
        }

        // No valid number found, need to backtrack
        return false;
    }

    private static int[] findEmptySpot(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    return new int[] { row, col };
                }
            }
        }
        return null;
    }

    private static boolean isValidPlacement(int[][] grid, int row, int col, int num) {
        return !isInRow(grid, row, num) &&
                !isInColumn(grid, col, num) &&
                !isInBox(grid, row - row % 3, col - col % 3, num);
    }

    private static boolean isInRow(int[][] grid, int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] grid, int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInBox(int[][] grid, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void displayPuzzle(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }
}
