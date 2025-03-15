import java.util.Scanner;

public class Main {
    private static final String DEFAULT_NAME = "COMPUTER";
    private static final int PLAYER = 1;
    private static final int COMPUTER = 2;
    private static final int EASY_LEVEL = 1; 
    private static final int HARD_LEVEL = 2;
    private static final String INVALID_CHOICE_PROMPT = "Invalid choice! Please enter \n1 for Player \n2 for Computer:";
    private static final String CHOICE_PROMPT = "Would you like to... \n1. Go back to home page \n2. exit";
    public static void main(String[] args) throws GameExceptions.WrongInputException, GameExceptions.SquareFullException {
       
        Scanner scanner = new Scanner(System.in);
        boolean endGame = false;

        while (!endGame) {
            int choice = getGameChoice(scanner, PLAYER, COMPUTER, INVALID_CHOICE_PROMPT);
            if (choice == PLAYER) {
                playAgainstPlayer(scanner);
               
                
            } else if (choice == COMPUTER) {
                playAgainstComputer(scanner, DEFAULT_NAME, EASY_LEVEL, HARD_LEVEL);
                
                
            }
            endGame = !(Helper.endPlaying(scanner,CHOICE_PROMPT));
        }
        scanner.close();
    }

    private static int getGameChoice(Scanner scanner, int player, int computer, String invalidChoicePrompt) {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Would you like to play against\n1. Player \n2. Computer");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != player && choice != computer) {
            System.out.println(invalidChoicePrompt);
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    private static void playAgainstPlayer(Scanner scanner) throws GameExceptions.WrongInputException, GameExceptions.SquareFullException {
        Board board = new Board();
        Human player1 = Helper.createPlayer(scanner, "first");
        Human player2 = Helper.createPlayer(scanner, "second", player1.getSymbol());
        System.out.println(player1.getName() + " will play as " + player1.getSymbol());
        System.out.println(player2.getName() + " will play as " + player2.getSymbol() + "\nPress enter to continue");
        scanner.nextLine();
        playGame(scanner, board, player1, player2);
    }

    private static void playAgainstComputer(Scanner scanner, String defaultName, int easyLevel, int hardLevel) throws GameExceptions.WrongInputException, GameExceptions.SquareFullException {
        Board board = new Board();
        System.out.println("Choose the level of difficulty \n1. Easy \n2. Hard");
        int level = scanner.nextInt();
        scanner.nextLine();
        Human player1 = Helper.createPlayer(scanner, "first");
        char symbol2 = Helper.getOppositeSymbol(player1.getSymbol());
        Computer player2 = new Computer(defaultName, symbol2);

        System.out.println(player1.getName() + " will play as " + player1.getSymbol());
        System.out.println(player2.getName() + " will play as " + player2.getSymbol() + "\nPress enter to continue");
        scanner.nextLine();

        playGameAgainstComputer(scanner, board, player1, player2, level);
    }

    private static void playGame(Scanner scanner, Board board, Human player1, Human player2) throws GameExceptions.WrongInputException, GameExceptions.SquareFullException {
        boolean stop = false;

        while (!stop) {
            board.resetBoard();
            System.out.println("Let's Start! and Choose a Player randomly...");
            Player currentPlayer = Helper.chooseRandomPlayer(player1, player2);
            System.out.print(currentPlayer.getName() + " will be starting! \nPress enter to Continue"); 
            String input = scanner.nextLine();
            boolean gameOver = false;

            while (!gameOver) {
                System.out.println(currentPlayer.getName() + ", please choose your next move, enter the row (1-3)");
                int hCoordinate = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the column (A-C):");
                char vCoordinate = scanner.nextLine().toUpperCase().charAt(0);

                try {
                    Human humanPlayer = (Human) currentPlayer;
                    humanPlayer.placeMark(hCoordinate, vCoordinate, board);
                    System.out.println(board);
                    if (Helper.handleEndGame(board, currentPlayer)) {
                        if (board.winGame(currentPlayer.getSymbol())){
                            Helper.updateScore(currentPlayer.getSymbol());
                        }
                    Helper.displayScoreboard();
                    gameOver = true;
                    } 
                    else {
                        currentPlayer = Helper.switchPlayer(currentPlayer, player1, player2);
                    }
                    } 
                    catch (GameExceptions.WrongInputException | GameExceptions.SquareFullException e) {
                        System.out.println(e.getMessage());
                    }
                }
            stop = !Helper.askToContinue(scanner);
            if (stop){
                Helper.resetScore(currentPlayer.getSymbol());
            }
        }
    }

    private static void playGameAgainstComputer(Scanner scanner, Board board, Human player1, Computer player2, int level) throws GameExceptions.WrongInputException, GameExceptions.SquareFullException {
    
        boolean stop = false;
        while (!stop) {
            board.resetBoard();
            System.out.println("Let's Start! and Choose a Player randomly...");
            Player currentPlayer = Helper.chooseRandomPlayer(player1, player2);
            System.out.print(currentPlayer.getName() + " will be starting! \nPress enter to Continue"); 
            String input = scanner.nextLine();
            boolean gameOver = false;
            while (!gameOver) {
                if (currentPlayer instanceof Human) {
                    System.out.println(currentPlayer.getName() + ", please choose your next move, enter the row (1-3)");
                    int hCoordinate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter the column (A-C):");
                    char vCoordinate = scanner.nextLine().toUpperCase().charAt(0);
                try {
                    Human humanPlayer = (Human) currentPlayer;
                    humanPlayer.placeMark(hCoordinate, vCoordinate, board);
                    System.out.println(board);
                    if (Helper.handleEndGame(board, currentPlayer)) {
                        if (board.winGame(currentPlayer.getSymbol())){
                            Helper.updateScore(currentPlayer.getSymbol());
                        }
                        Helper.displayScoreboard();
                        gameOver = true;
                    } 
                    else {
                        currentPlayer = Helper.switchPlayer(currentPlayer, player1, player2);
                    }
                }
                catch (GameExceptions.WrongInputException | GameExceptions.SquareFullException e) {
                    System.out.println(e.getMessage());
                }
                } 
                else if (currentPlayer instanceof Computer) {
                    if (level == EASY_LEVEL) {
                        Computer computerPlayer = (Computer) currentPlayer;
                        computerPlayer.placeMark(0, ' ',board);
                    }
                    else if (level == HARD_LEVEL) {
                        Computer computerPlayer = (Computer) currentPlayer;
                        computerPlayer.placeMarkAi(board);
                    }
                    System.out.println(board);
                    if (Helper.handleEndGame(board, currentPlayer)) {
                        if (board.winGame(currentPlayer.getSymbol())){
                            Helper.updateScore(currentPlayer.getSymbol());
                        }
                    Helper.displayScoreboard();
                    gameOver = true;
                    } 
                    else {
                        currentPlayer = Helper.switchPlayer(currentPlayer, player1, player2);
                    }
                }   
            }
            stop = !Helper.askToContinue(scanner);
            if (stop){
                Helper.resetScore(currentPlayer.getSymbol());
            }
        }
    }  
}
    
       



    
      
  
        
