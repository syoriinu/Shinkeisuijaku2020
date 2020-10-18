public enum Settings{
    START,
    PLAY_NOW,
    NORMAL(16,"NORMAL"),
    BATTLE(16,"BATTLE"),
    TIME_ATTACK(16,"TIME_ATTACK"),
    NO_MISTAKES(16,"NO_MISTAKES"),
    NO_MISTAKES_SUCCESS,
    NO_MISTAKES_FAILED,
    RESULT,
    INTRODUCTION,
    GIVE_UP,
    FRAME_WIDTH(1200),
    FRAME_HEIGHT(1000),
    FONT("Serif"),
    TITLE("神経衰弱"),
    TITLE_COLOR("#00bfff"),
    BACKGROUND_COLOR("#2e8b57");

    private int value;
    private String word;

    Settings(){}

    Settings(int value){
        this.value = value;
    }

    Settings(String word){
        this.word = word;
    }

    Settings(int value,String word){
        this.value = value;
        this.word = word;
    }

    public int getValue(){
        return value;
    }

    public String getWord() {
        return word;
    }
}
