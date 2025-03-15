public class Human extends Player {

    public Human (String name, char symbol){
        super(name,symbol);
    }

    public Human (){
        super();
    }
    public Human (Human copy){
        super(copy);
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
    
    public void placeMark(int hCoordinate, char vCoordinate, Board board) throws GameExceptions.WrongInputException, GameExceptions.SquareFullException {
        board.validateMove(hCoordinate, vCoordinate);{
            board.getSquare(hCoordinate, vCoordinate).setState(getSymbol());
        }
    }
    
}
   

