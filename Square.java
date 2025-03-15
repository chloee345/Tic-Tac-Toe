public class Square {
    private int hCoordinate; 
    private char vCoordinate; 
    private char state;

    public Square (int hCoordinate, char vCoordinate, char state){
        this.hCoordinate = hCoordinate; 
        this.vCoordinate = vCoordinate; 
        this.state = state;
    }
    public Square() {
        this.hCoordinate = 1;
        this.vCoordinate = 'A'; // This should be initialized correctly
        this.state = ' ';
    }
    public Square(Square copy) { 
        this.hCoordinate = copy.hCoordinate; 
        this.vCoordinate = copy.vCoordinate; 
        this.state = copy.state;
    }
    public int getHCoordinate (){
        return hCoordinate;
    }
    public char getVCoordinate (){
        return vCoordinate;
    }
    public void setState (char state){
        this.state = state;
    }
    public char getState () {
        return state;
    }
    public boolean isEmpty() {
        return state == ' ';
    }
    
}
