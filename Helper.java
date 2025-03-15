import java.util.Random;
import java.util.Scanner;

public class Helper {
    private static int player1Score = 0;
    private static int player2Score = 0;
    public static final int YES = 1; 
    public static final int NO = 2;
    public static final int GO_BACK = 1;
    public static final int EXIT = 2;
    public static final String CONTINUE_PROMPT = "Would you like to continue playing? \n1. Yes \n2. No";
    

    public static Player chooseRandomPlayer(Player player1, Player player2) {
        Random random = new Random();
        return random.nextBoolean() ? player1 : player2; 
    }

    public static Player switchPlayer(Player currentPlayer, Player player1, Player player2) {
        if (currentPlayer == player1){
            return player2;
        }
        else { 
            return player1;
        } 
    }

    public static char getValidSymbol(Scanner scanner, String playerName) {
        char symbol;
        boolean validInput = false;
        while (!validInput){
            System.out.println(playerName + ", choose a symbol (X or O):");
            String input = scanner.nextLine();
            if (input.length() == 1) { 
                symbol = input.charAt(0);
                if (symbol == 'X' || symbol == 'O') {
                        validInput = true;
                        return symbol;
                }
            }
            System.out.println("Invalid symbol! Please choose 'X' or 'O'.");
        }
        return ' ';  
    }

    public static char getOppositeSymbol(char symbol) {
        if (symbol == 'X') {
            symbol= 'O';
        } 
        else {
            symbol= 'X';
        }
        return symbol;
    }

    public static boolean handleEndGame(Board board, Player currentPlayer) throws GameExceptions.WrongInputException{
        if (board.winGame(currentPlayer.getSymbol())) {
            System.out.println("Game Over! " + currentPlayer.getName() + " Won, congratulations!");
            return true; 
        } else if (board.tieGame()) {
            System.out.println("Game Over! You tied");
            return true; 
        }
        return false; 
    }

    public static void updateScore(char symbol){
        if (symbol == 'X') {
            player1Score++;
        } else if (symbol == 'O') {
            player2Score++;
        }
    }
    
    public static void resetScore (char symbol) {
        if (symbol == 'X'){
            player1Score = 0;
        }
        else if (symbol == 'O'){
            player2Score = 0;
        }
    }

    public static void displayScoreboard() {
        System.out.println("Scoreboard:");
        System.out.println("Player 1 (X): " + player1Score);
        System.out.println("Player 2 (O): " + player2Score);
    }
    public static Human createPlayer(Scanner scanner, String order) {
        System.out.println("What's the name of the " + order + " Player?");
        String name = scanner.nextLine();
        char symbol = Helper.getValidSymbol(scanner, name);
        return new Human(name, symbol);
    }
    public static Human createPlayer(Scanner scanner, String order, char existingSymbol) {
        System.out.println("What's the name of the " + order + " Player?");
        String name = scanner.nextLine();
        char symbol = Helper.getOppositeSymbol(existingSymbol);
        return new Human(name, symbol);
    }
    
    public static boolean endPlaying(Scanner scanner, String choiceprompt) {
        System.out.println(choiceprompt);
        int userInput = scanner.nextInt();
        scanner.nextLine();
        while (userInput != GO_BACK && userInput != EXIT){
            System.out.println("invalid choice. " + choiceprompt);
            userInput = scanner.nextInt();
        scanner.nextLine();
        }
        return userInput == GO_BACK;
    }

    public static boolean askToContinue(Scanner scanner) {
        System.out.println(CONTINUE_PROMPT);
        int userInput = scanner.nextInt();
        scanner.nextLine();
    
        while (userInput != YES && userInput != NO) {
            System.out.println("Please choose between 1 and 2.\n" + CONTINUE_PROMPT);
            userInput = scanner.nextInt();
            scanner.nextLine();
        }
    
        if (userInput == NO) {
            return false; 
        } else {
            return true; 
        }
    }

}
