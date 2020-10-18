import java.util.List;
import java.util.Objects;

public class GameObserver extends Observer{
    private int totalTarn;

    public GameObserver(GamePanel gamePanel){
        super.gamePanel = gamePanel;
        totalTarn = 0;
    }

    public void countFrontCard(){
        int count = 0;
        for(CardPanel cardPanel : gamePanel.getCardPanels()){
            if(cardPanel.getState() == ModeEnum.FRONT && cardPanel.getExistence() == ModeEnum.EXISTENCE){
                count++;
            }
        }
        if(count ==2){
            moveCard();
        }
    }

    public void moveCard(){
        List<CardPanel> list = gamePanel.getCardPanels();
        CardPanel cp1 = null;
        CardPanel cp2 = null;
        Sound get = new Sound("get.wav",0.3);
        Sound failed = new Sound("failed.wav",0.3);

        for(CardPanel cardPanel : list){
            if(cardPanel.getExistence() == ModeEnum.EXISTENCE && cardPanel.getState() == ModeEnum.FRONT && cp1 == null){
                cp1 = cardPanel;
            }else if(cardPanel.getExistence() == ModeEnum.EXISTENCE && cardPanel.getState() == ModeEnum.FRONT && cp1 != null){
                cp2 = cardPanel;
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Objects.requireNonNull(cp1).getCARD().getRANK() == Objects.requireNonNull(cp2).getCARD().getRANK()){
            cp1.setExistence(ModeEnum.UN_EXISTENCE);
            cp2.setExistence(ModeEnum.UN_EXISTENCE);
            get.play();
        }else{
            cp1.setState(ModeEnum.BACK);
            cp2.setState(ModeEnum.BACK);
            failed.play();
            if(gamePanel.getGameMode() == Settings.NO_MISTAKES){
                gamePanel.setNmResult(Settings.NO_MISTAKES_FAILED);
                gamePanel.getCardPanels().clear();
            }
        }
        totalTarn++;
        checkGame();
    }

    public void checkGame(){
        int numberOfCards = gamePanel.getCardPanels().size();
        for(CardPanel cardPanel : gamePanel.getCardPanels()){
            if(cardPanel.getExistence() == ModeEnum.UN_EXISTENCE){
                numberOfCards--;
            }
        }
        if(numberOfCards == 0){
            Sound end = new Sound("dodon.wav",0.5);
            gamePanel.reset();
            gamePanel.setScreenMode(Settings.RESULT);
            end.play();
        }
    }

    @Override
    public void update() {
        gamePanel.repaint();
    }

    public int getTotalTarn() {
        return totalTarn;
    }
}
