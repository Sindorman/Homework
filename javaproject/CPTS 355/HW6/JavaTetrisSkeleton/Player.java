public class Player {
    private int score;
    private int num_pieces;

    public Player(){this.score = 0; this.num_pieces = 0;}

    public void set_score(int val){ this.score = val; }
    public int get_score(){ return this.score; }
    public void set_pieces(int n){ this.num_pieces = n; }
    public int get_pieces(){ return this.num_pieces; }
    
}