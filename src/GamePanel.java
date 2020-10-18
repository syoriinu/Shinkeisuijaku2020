import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class GamePanel extends BasePanel {
    private Settings gameMode;
    private Settings screenMode;
    private Settings nmResult;
    private GameObserver gameObserver;
    private final List<CardPanel> cardPanels;
    private final Map<String,ButtonLabel> blMap;
    private final TATimer timer;

    private Timer countDownTimer;
    private int countOfTimer2;

    public GamePanel(){
        setPreferredSize(new Dimension(Settings.FRAME_WIDTH.getValue(), Settings.FRAME_HEIGHT.getValue()));
        setBackground(Color.decode(Settings.BACKGROUND_COLOR.getWord()));
        setOpaque(false);
        setLayout(null);
        Sound BGM = new Sound("BGM.wav", 0.3);
        Map<String, ImageIcon> imgMap = new ImgData().getImgMap();
        blMap = new HashMap<>();
        cardPanels = new ArrayList<>();
        blMap.put("NORMAL",new ButtonLabel(this,Settings.NORMAL, imgMap.get("NORMAL")));
        blMap.put("NO_MISTAKES",new ButtonLabel(this,Settings.NO_MISTAKES, imgMap.get("NO_MISTAKES")));
        blMap.put("TIME_ATTACK",new ButtonLabel(this,Settings.TIME_ATTACK, imgMap.get("TIME_ATTACK")));
        blMap.put("END",new ButtonLabel(this,null, imgMap.get("END")));
        blMap.put("START",new ButtonLabel(this,Settings.START, imgMap.get("START")));
        blMap.put("END2",new ButtonLabel(this,null, imgMap.get("END")));
        blMap.put("INTRO_NORMAL",new ButtonLabel(this,Settings.NORMAL,Settings.INTRODUCTION, imgMap.get("INTRO_NORMAL")));
        blMap.put("INTRO_NO_MISTAKES",new ButtonLabel(this,Settings.NO_MISTAKES,Settings.INTRODUCTION, imgMap.get("INTRO_NO_MISTAKES")));
        blMap.put("INTRO_TIME_ATTACK",new ButtonLabel(this,Settings.TIME_ATTACK,Settings.INTRODUCTION, imgMap.get("INTRO_TIME_ATTACK")));
        blMap.put("GIVE_UP",new ButtonLabel(this,Settings.GIVE_UP,imgMap.get("GIVE_UP")));

        blMap.get("NORMAL").setBounds(200,400,200,100);
        blMap.get("NO_MISTAKES").setBounds(500,400,200,100);
        blMap.get("TIME_ATTACK").setBounds(800,400,200,100);
        blMap.get("END").setBounds(500,700,200,100);
        blMap.get("START").setBounds(300,800,200,100);
        blMap.get("END2").setBounds(700,800,200,100);
        blMap.get("INTRO_NORMAL").setBounds(0,0,Settings.FRAME_WIDTH.getValue(),Settings.FRAME_HEIGHT.getValue());
        blMap.get("INTRO_NO_MISTAKES").setBounds(0,0,Settings.FRAME_WIDTH.getValue(),Settings.FRAME_HEIGHT.getValue());
        blMap.get("INTRO_TIME_ATTACK").setBounds(0,0,Settings.FRAME_WIDTH.getValue(),Settings.FRAME_HEIGHT.getValue());
        blMap.get("GIVE_UP").setBounds(950,800,200,100);

        countOfTimer2 = 10;
        timer = new TATimer();

        timer.setBounds(1000,900,200,100);
        countDownTimer = new Timer(1000, e -> {
            countOfTimer2--;
            if(countOfTimer2 == 0){
                for(CardPanel cp : cardPanels){
                    cp.setState(ModeEnum.BACK);
                }
                countOfTimer2 = 5;
                countDownTimer.stop();
            }
        });
        screenMode = Settings.START;
        BGM.loop();
    }

    public void setUp(Settings mode){
        gameObserver = new GameObserver(this);
        screenMode = Settings.PLAY_NOW;
        setGameMode(mode);
        removeAll();
        makeCards();
        arrangeCards();
        add(blMap.get("GIVE_UP"));
        gameObserver.update();
        reload();
        switch (mode){
            case TIME_ATTACK:
                timer.start();
                break;
            case NO_MISTAKES:
                nmResult = Settings.NO_MISTAKES_SUCCESS;
                for(CardPanel cp : cardPanels){
                    cp.setState(ModeEnum.FRONT);
                }
                countDownTimer.start();
                break;
        }
    }

    /**
     * GUI部分
     * */
    public void addButtonLabelsStart(){
        add(blMap.get("NORMAL"));
        add(blMap.get("NO_MISTAKES"));
        add(blMap.get("TIME_ATTACK"));
        add(blMap.get("END"));
    }
    public void addButtonLabelsEnd(){
        add(blMap.get("START"));
        add(blMap.get("END2"));
    }

    public void introduction(Settings introMode){
        add(blMap.get("INTRO_"+introMode.getWord()));

    }

    public void makeCards(){
        Deck deck = new Deck();
        List<Card> cardList = deck.pickCards(gameMode.getValue());
        for (Card card : cardList) {
            CardPanel cp = new CardPanel(this, card);
            cardPanels.add(cp);
        }
        Collections.shuffle(cardPanels);
    }

    public void arrangeCards(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,900));
        panel.setBounds(300,50,600,900);
        panel.setOpaque(false);
        for(CardPanel cp : cardPanels){
            panel.add(cp);
        }
        add(panel);
    }

    public void reset() {
        removeAll();
        cardPanels.clear();
    }

    public void reload(){
        setVisible(false);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font(Settings.FONT.getWord(),Font.BOLD,120);
        g2.setFont(font);
        g2.setColor(Color.decode(Settings.TITLE_COLOR.getWord()));

        switch(screenMode){
            case START:
                g2.drawString(Settings.TITLE.getWord(),350,300);
                addButtonLabelsStart();
                break;
            case RESULT:
                addButtonLabelsEnd();
                switch (gameMode){
                    case NORMAL:
                        g2.drawString("クリアするまで",180,300);
                        g2.drawString(gameObserver.getTotalTarn() + "ターン",400,450);
                        g2.drawString("かかりました",250,600);
                        break;
                    case BATTLE:
                        break;
                    case NO_MISTAKES:
                        if(nmResult == Settings.NO_MISTAKES_SUCCESS){
                            g2.drawString("よくできました",250,470);

                        }else{
                            g2.drawString("できないよね",250,470);
                        }

                        break;
                    case TIME_ATTACK:
                        timer.stop();
                        g2.drawString("あなたの記録は",180,300);
                        g2.drawString(timer.getText(),380,500);
                        break;
                }
                break;
        }
        reload();
    }

    /**
     * setter/getter
     */
    public void setGameMode(Settings gameMode) {
        this.gameMode = gameMode;
//        gameObserver.update();
    }

    public Settings getGameMode() {
        return gameMode;
    }

    public List<CardPanel> getCardPanels() {
        return cardPanels;
    }

    public GameObserver getGameObserver() {
        return gameObserver;
    }

    public void setScreenMode(Settings screenMode) {
        this.screenMode = screenMode;
        gameObserver.update();
    }

    public void setNmResult(Settings nmResult) {
        this.nmResult = nmResult;
    }
}
