public class GameExceptions {
    public static class WrongInputException extends Exception {
        public WrongInputException() {
            super("Move is out of bounds. Please enter valid coordinates, Row (1-3), and Columns (A-C)");
        }
    }
    public static class SquareFullException extends Exception {
        public SquareFullException(){ 
            super("This square is full. Please enter other coordinates.");
          } 
    }
}
