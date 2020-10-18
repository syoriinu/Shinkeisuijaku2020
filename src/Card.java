public class Card {
    private final String SUIT;
    private final int RANK;

    public Card(String SUIT,int RANK){
        this.SUIT = SUIT;
        this.RANK = RANK;
    }

    public int getRANK() {
        return RANK;
    }

    @Override
    public String toString() {
        switch(RANK){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return SUIT +"0"+ RANK;
            default:
                return SUIT + RANK;
        }
    }
}
