import java.util.Random;
public class Computer extends Player {

    public static final String DEFAULT_NAME = "COMPUTER";
    public final int ARRAY_SIZE = 3;

    public Computer(String name,char symbol) {
        super(name, symbol);
    }

    public Computer() {
        super();
    }

    public Computer(Computer copy) {
        super(copy.getName(), copy.getSymbol()); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =  name;
    }
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


    public int minimax(Board board, char AiSymbol, char humanSymbol, boolean maximizing) throws GameExceptions.WrongInputException {
        if (board.winGame(AiSymbol)) return 1; 
        if (board.winGame(humanSymbol)) return -1; 
        if (board.tieGame()) return 0; 

        if (maximizing) {
            int bestScore = Integer.MIN_VALUE; 
            for (int i = 0; i < ARRAY_SIZE; i++) {
                for (int j = 0;j<ARRAY_SIZE; j++) {
                    if (board.getSquare(i+1,(char)(j+65)).isEmpty()) { 
                        board.getSquare(i+1,(char)(j+65)).setState(AiSymbol); 
                        int score = minimax(board, AiSymbol, humanSymbol, false); 
                        board.getSquare(i+1,(char)(j+65)).setState(' '); 
                        bestScore = Math.max(score, bestScore); 
                    }
                }
            }
        return bestScore; 
        } 
        else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < ARRAY_SIZE; i++) {
                for (int j = 0; j < ARRAY_SIZE; j++) {
                    if (board.getSquare(i+1,(char)(j+65)).isEmpty()) { 
                        board.getSquare(i+1,(char)(j+65)).setState(humanSymbol); 
                        int score = minimax(board, AiSymbol, humanSymbol, true);
                        board.getSquare(i+1,(char)(j+65)).setState(' ');
                        bestScore = Math.min(score, bestScore); 
                    }
                }
            }
            return bestScore; 
        }
    }

    public void placeMarkAi(Board board) throws GameExceptions.WrongInputException  {
        int bestValue = Integer.MIN_VALUE;
        int bestMoveRow = -1;
        int bestMoveCol = -1;

        for (int i = 0; i < board.ARRAY_SIZE; i++) {
            for (int j = 0; j < board.ARRAY_SIZE; j++) {
                if (board.getSquare(i + 1, (char) (j + 65)).isEmpty()) {
                    board.getSquare(i + 1, (char) (j + 65)).setState(getSymbol());
                    int moveValue = minimax(board, getSymbol(), (getSymbol() == 'X' ? 'O' : 'X'), false);
                    board.getSquare(i + 1, (char) (j + 65)).setState(' ');
                    if (moveValue > bestValue) {
                        bestMoveRow = i;
                        bestMoveCol = j;
                        bestValue = moveValue;
                    }
                }
            }
        }
        if (bestMoveRow != -1 && bestMoveCol != -1) {
            board.getSquare(bestMoveRow + 1, (char) (bestMoveCol + 65)).setState(getSymbol());
        }
    }
    
    public void placeMark(int hCoordinate, char vCoordinate, Board board) throws GameExceptions.SquareFullException, GameExceptions.WrongInputException { 
        boolean place = false;
        while (!place) {
            Random random = new Random(); 
            int row = random.nextInt(board.ARRAY_SIZE) + 1; 
            char col = (char)(random.nextInt(board.ARRAY_SIZE) + 65); 
            try { 
                board.validateMove(row, col);
                board.getSquare(row, col).setState(getSymbol());
                place = true;
            } 
            catch (GameExceptions.SquareFullException e) {  
            }
        }
    
        if (!place) {
            System.out.println("No valid moves available. The board may be full.");
        }
    }
}
   

    
