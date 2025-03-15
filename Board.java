public class Board {
    
    private Square[][] squares;
    public final int ARRAY_SIZE = 3;

    public Board(){ 
        this.squares = new Square [ARRAY_SIZE][ARRAY_SIZE];
        initializeBoard();
    }
    public void initializeBoard(){
        for (int i = 0; i < ARRAY_SIZE; i++) { 
            for (int j = 0; j < ARRAY_SIZE; j++){ 
                this.squares[i][j] = new Square (i+1, (char)(j+65), ' '); 
            }
        }
    }
    public void resetBoard() {
        initializeBoard();
    }
    public Square getSquare (int hCoordinate, char vCoordinate) throws GameExceptions.WrongInputException{ 
        int row = hCoordinate - 1;
        int col = vCoordinate - 'A';
        if (row < 0 || row >= ARRAY_SIZE || col < 0 || col >= ARRAY_SIZE) {
            throw new GameExceptions.WrongInputException(); 
        }
        return this.squares[row][col];
    }

    public String displayBoard(){ 
        StringBuilder board = new StringBuilder();
        board.append(String.format("%-7s %-10s %-10s %-10s%n","", "A", "B", "C"));
        board.append("\n");
        for (int i = 0; i < ARRAY_SIZE; i++){
            board.append(String.format("%-3s ", i + 1));
            for (int j = 0; j < ARRAY_SIZE; j++){
                char input = squares[i][j].getState();
                board.append(String.format("%1s %3s", "", input ));
                if (j < ARRAY_SIZE-1){
                    board.append(String.format("%-3s %-2s", "","|"));
                }
            }
            board.append("\n");
            if (i < ARRAY_SIZE-1){ 
                board.append(String.format("%3s %1s" , "", "---------+----------+-----------\n"));
            }  
        }
        return board.toString();
    }
    
    public String toString() { 
        return displayBoard(); 
    }
        
    public boolean isSquareFull(int hCoordinate, char vCoordinate) throws GameExceptions.WrongInputException {
        int row = hCoordinate - 1;
        int col = vCoordinate - 'A';
            if (row < 0 || row >= ARRAY_SIZE || col < 0 || col >= ARRAY_SIZE) {
                throw new GameExceptions.WrongInputException(); 
            }
            return !this.squares[row][col].isEmpty();
    }
    
    public boolean winGame (char input){
        for (int i = 0; i<ARRAY_SIZE; i++){
            if (squares[0][i].getState() == input && squares[1][i].getState() == input && squares[2][i].getState() == input){
                return true;
            }
            if (squares[i][0].getState() == input && squares[i][1].getState() == input && squares [i][2].getState() == input){
                return true;
            }
        }
        if (squares[0][0].getState() == input && squares [1][1].getState() == input && squares[2][2].getState() == input){
            return true;
        }
        if (squares[0][2].getState() == input && squares [1][1].getState() == input && squares[2][0].getState() == input){
            return true;
        }
        return false;
    } 

    public boolean tieGame() throws GameExceptions.WrongInputException{
        if (winGame('X') || winGame('O')) {
            return false;
        }
        for (int i = 0; i < ARRAY_SIZE; i++){ 
            for(int j = 0; j < ARRAY_SIZE; j++){ 
            char col = (char) (j + 65);
                if (!isSquareFull(i+1,col)) { 
                    return false;
            }
        }  
    }
        return true;
    }
    
    public void validateMove(int hCoordinate, char vCoordinate) throws GameExceptions.WrongInputException, GameExceptions.SquareFullException {
        int row = hCoordinate - 1;
        int col = vCoordinate - 'A';
        if (row < 0 || row >= ARRAY_SIZE || col < 0 || col >= ARRAY_SIZE ) {
            throw new GameExceptions.WrongInputException();
        }
        if (!this.squares[row][col].isEmpty()) {
            throw new GameExceptions.SquareFullException();
        } 
    }
}

