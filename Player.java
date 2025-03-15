public abstract class Player {
    
    public static final int ARRAY_SIZE = 3;
    protected String name; 
    protected char symbol; 
    
        public Player(String name, char symbol){ 
            this.name = name; 
            this.symbol = symbol; 
        }

        public Player(){
            this.name = "Player"; 
            this.symbol = ' ';
        }

        public Player(Player copy) {
            this.name = copy.name; 
            this.symbol = copy.symbol; 
        }

        public String getName(){
            return this.name;
        }
        
        public char getSymbol(){
            return this.symbol;
        }
    
        public void setName (String name){
            this.name = name;
        }
        
        public void setSymbol (char symbol){
            this.symbol = symbol;
        }
    
        public abstract void placeMark (int hCoordinate, char vCoordinate, Board board) throws GameExceptions.WrongInputException, GameExceptions.SquareFullException;
}
