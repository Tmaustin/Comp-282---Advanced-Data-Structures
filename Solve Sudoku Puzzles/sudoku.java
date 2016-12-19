/*
 * Taylor Austin
 * Comp 282 Mon / Wed
 * Programming Assignment #1
 * 2/7/2015
 * Inside this file is a Sudoku Solver that receives a unsolved
 * puzzle and prints out the solved board. Theres alos methods
 * to check if your board is solvable.
 */
class Spot {
    private int row, col;
    public Spot(int row, int col) {
        //sets the parameters to the private integers
        this.row = row;
        this.col = col;
    }
    public void setRow(int row) {
        this.row = row; // Sets Row
    }
    public void setCol(int col) {
        this.col = col; // Sets Col
    }
    public int getRow() {
        return row; //Returns the row value
    }
    public int getCol() {
        return col; //Retruns the col value
    }
}
class sudoku {
    private int board[][];
    //               [row][col]

    public sudoku() {
        // default constructor -- I never seem to use it....
    }
    public sudoku(String s[]) {
        board = new int[9][9]; //main board
        //Nested for loop to insert he values inside the board
        for (int row = 0; row < 9; row++) { //row
            for (int col = 0; col < 9; col++) //col
            {
                //coverts the character to an int then inserts it into the main board
                board[row][col] = (int)(s[row].charAt(col + col / 3)) - 48;
            }
        }
    }
    public sudoku(sudoku p) {
        board = new int[9][9];
        for (int row = 0; row < 9; row++) { //row
            for (int col = 0; col < 9; col++) //col
            {
                //Copies the Board
                this.board[row][col] = p.board[row][col];
            }
        }
        //copies the board
    }
    public String toString() {
        String result = new String();
        result += "";
        //This Nested for loop adds values and characters to a string in order
        //to return the board with one String
        for (int row = 0; row < 9; row++) {
            if (row == 3 || row == 6) {
                //Creates a new line after ever 3 rows to seprate the houses
                result += "---------------\n";
            }
            for (int col = 0; col < 9; col++) {
                if (col == 3 || col == 6) {
                    result += " | " + String.valueOf(board[row][col]);
                    // If the row is 3 or 6 it creates a line to seperate each house
                } else // print out the value of the board
                    result += String.valueOf(board[row][col]);
            }
            result += "\n"; // New line after ever row
        }
        return result;
    }

    // for easy checking of your answers
    public String toString2() {
        String result = new String();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                result = result + String.valueOf(board[row][col]);
            }
        }
        return result;
    }

    // create rotated sudoku puzzle used by my test programs
    public void rotate() {
        int[][] temp = new int[9][9];
        int row, col;
        for (row = 0; row < 9; row++) {
            for (col = 0; col < 9; col++) {
                temp[col][8 - row] = board[row][col];
            }
        }
        for (row = 0; row < 9; row++) {
            for (col = 0; col < 9; col++) {
                board[row][col] = temp[row][col];
            }
        }
    }

    public boolean isValid() {
        boolean isValid = true;

        //Go through row,col,house to check for 2 values of the same kind
        //checks for duplicates in each row
        //grabs the first int in the row and checks the next 8 for identical
        //then grabs the 2nd and checks the next 7 values and so forth til 8th
        //and 9th value. If there is identical values isValid is set to false.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                for (int i = col + 1; i < 9; i++) {
                    if (board[row][col] != 0)
                        if (board[row][col] == board[row][i])
                            isValid = false;
                }

            }
        }
        //checks for duplicates in each column
        //grabs the first int in the column and checks the next 8 for identical
        //then grabs the 2nd and checks the next 7 values and so forth til the 
        //8th and 9th value. If there is identical values isValid is set to false.
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                for (int i = row + 1; i < 9; i++) {
                    if (board[row][col] != 0)
                        if (board[row][col] == board[i][col])
                            isValid = false;
                }

            }
        }
        //checks for duplicates in each house
        int y = 0;
        //Puts each House into there very own row in the array 
        int boxarray[][];
        boxarray = new int[9][9];
        for (int startCol = 0; startCol < 9; startCol += 3) {
            for (int startRow = 0; startRow < 9; startRow += 3) {
                int x = 0;
                for (int col = startCol; col < startCol + 3; col++) {
                    for (int row = startRow; row < startRow + 3; row++) {
                        boxarray[y][x++] = board[row][col];

                    }

                }
                y++;
            }
        }
        //Grabs the first int in the row and checks the next 8 for identical. 
        //Then grabs the 2nd and checks the next 7 values and so forth til the 
        //8th and 9th values. If there is identical values isValid is set to false.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                for (int i = col + 1; i < 9; i++) {
                    if (boxarray[row][col] != 0)
                        if (boxarray[row][col] == boxarray[row][i])
                            isValid = false;
                }

            }
        }

        return isValid; //remove
    }
    public boolean isComplete() {
        boolean isValid = true;
        //Checks for any empty spots in the board. If its full it returns true.
        //If the board still has 0's then it returns false.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0)
                    isValid = false;
            }
        }
        return isValid; //Complete
    }
    private boolean doesRowContain(int row, int val) {
        //Returns true if the value already appears in the row
        //Returns false if the value can be placed in the row.
        boolean contain = false;
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == val)
                contain = true;
        }
        return contain; //Complete
    }
    private boolean doesColContain(int col, int val) {
        //Returns true if the value already appears in the column
        //Returns false if the value can be placed in the column.
        boolean contain = false;
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == val)
                contain = true;
        }
        return contain; //remove

    }
    private boolean doesBoxContain(int row, int col, int val) {
        boolean contain = false;
        //Divides row/column by 3 then muliplies to find the top corner in
        //Every box
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        //Returns true if the value already appears in the house
        //Returns false if the value can be placed in the house
        for (int newRow = startRow; newRow < startRow + 3; newRow++) {
            for (int newCol = startCol; newCol < startCol + 3; newCol++) {
                if (board[newRow][newCol] == val)
                    contain = true;
            }
        }
        return contain; //remove

    }

    private int fillSpot(Spot sq) {
        int counter = 0;
        int val = 0;
        //if the value is the only possible value inside the house then that
        //value is returned. If there are no possible values for the box return
        //0
        if (board[sq.getRow()][sq.getCol()] == 0) { //Empty
            for (int i = 1; i < 10; i++) {
                if (!doesRowContain(sq.getRow(), i)) {
                    if (!doesColContain(sq.getCol(), i))
                        if (!doesBoxContain(sq.getRow(), sq.getCol(), i)) {
                            val = i;
                            counter++;
                        }
                }
            }
        }
        //if the spot is not empty its skips the code
        //the counter is the number of times the value can go in a certain spot
        //if the counter does not equal 1 it returns 0
        if (counter != 1)
            val = 0;
        return val; //remove
    }
    private Spot rowFill(int row, int val) {
        Spot check = null;
        int counter = 0;
        //if the spot is valid for that 1 value in the row then return the spot
        //if the spot in invlaid return null
        if (doesRowContain(row, val) == false) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) { //Check only if a 0 is in the spot
                    if (doesColContain(col, val) == false) {
                        if (doesBoxContain(row, col, val) == false) {
                            check = new Spot(row, col);
                            counter++;
                        }
                    }
                }
            }
        }
        if (counter != 1)
            check = null;

        return check; //remove
    }
    private Spot colFill(int col, int val) {
        Spot check = null;
        int counter = 0;
        //if the spot is valid for that 1 value in the col then return the spot
        //if the spot in invlaid return null
        if (doesColContain(col, val) == false) {
            for (int row = 0; row < 9; row++) {
                if (board[row][col] == 0) { //Check only if a 0 is in the spot
                    if (doesRowContain(row, val) == false) {
                        if (doesBoxContain(row, col, val) == false) {
                            check = new Spot(row, col);
                            counter++;
                        }
                    }
                }
            }
        }
        if (counter != 1)
            check = null;
        return check; //remove
    }
    private Spot boxFill(int rowbox, int colbox, int val) {
        Spot check = null;
        int counter = 0;
        int startRow = (rowbox / 3) * 3;
        int startCol = (colbox / 3) * 3;
        //if the spot is valid for that 1 value in the Box then return the spot
        //if the spot in invlaid return null
        if (doesBoxContain(rowbox, colbox, val) == false) {
            for (int row = startRow; row < startRow + 3; row++) {
                for (int col = startCol; col < startCol + 3; col++) {
                    if (board[row][col] == 0) { //Check only if a 0 is in the spot
                        if (!doesRowContain(row, val) && !doesColContain(col, val)) {
                            check = new Spot(row, col);
                            counter++;
                        }
                    }
                }
            }
        }
        if (counter != 1)
            check = null;
        return check; //remove          
    }
    public void solve() {
        boolean NotStuck = false;
        int[][] temp = new int[9][9];
        int[][] temp2 = new int[9][9];
        //The loop will continue only if the board is still valide, is not 
        //complete, and if the board is not stuck (infinite loop)
        while (!isComplete() && isValid() && NotStuck == false) {
            //loads the board into a temperary board (checks for infinite loop)
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    temp[row][col] = board[row][col];
                }
            }
            //Checks if a spot is not null from passing rowFill a value and row
            //then place the value in the board
            for (int row = 0; row < 9; row++) {
                for (int i = 1; i < 10; i++) {
                    if (rowFill(row, i) != null)
                        board[rowFill(row, i).getRow()]
                                [rowFill(row, i).getCol()] = i;
                }
            }
            //Checks if a spot is not null from passing colFill a value and col
            //then place the value in the board
            for (int col = 0; col < 9; col++) {
                for (int i = 1; i < 10; i++) {
                    if (colFill(col, i) != null)
                        board[colFill(col, i).getRow()]
                                [colFill(col, i).getCol()] = i;
                }
            }
            //Checks if a spot is not null from passing boxFill a value, col 
            //and row then place the value in the board
            for (int row = 0; row < 9; row += 3) {
                for (int col = 0; col < 9; col += 3) {
                    for (int i = 1; i < 10; i++) {
                        if (boxFill(row, col, i) != null) {
                            board[boxFill(row, col, i).getRow()]
                                [boxFill(row, col, i).getCol()] = i;
                        }
                    }
                }
            }
            //checks for naked singles in the board. The value can be found
            //by chacking the contents of its row, column, and house. If 8
            //numbers are assinged then this will find you 9th value
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    Spot check = new Spot(row, col);
                    if (fillSpot(check) != 0)
                        board[check.getRow()][check.getCol()] = fillSpot(check);

                }
            }

            //Checks to see if the program is stuck
            //Compares the "changed" board with the temperary board and if they
            //are the same the program is stuck
            int numberSame = 0;
            for (int row = 0; row < 9; row++) { //row
                for (int col = 0; col < 9; col++) //col
                {
                    //Counts the number of times the spot is equal
                    if (temp[row][col] == board[row][col])
                        numberSame++;
                    //If all the Spots are equal its stuck or complete
                    if (numberSame == 81)
                        NotStuck = true;

                }
            }
            //End of stuck method


        }
        ///End of Printing out board
    }
    public static String myName() {
        return "Taylor M. Austin"; //prints my name
    }
}