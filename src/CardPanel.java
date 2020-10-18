import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CardPanel extends BasePanel{
    private final Card CARD;
    private final GameObserver gameObserver;
    private final CardObserver cardObserver;
    private final BufferedImage CARD_FRONT_IMG;
    private final BufferedImage CARD_BACK_IMG;
    private ModeEnum state;
    private ModeEnum existence;
    private Sound turn;
    private ReadingImage ri;

    public CardPanel(GamePanel gamePanel,Card CARD){
        super();
        ri = new ReadingImage();
        this.CARD = CARD;
        CARD_FRONT_IMG = ri.readImage(CARD.toString() + ".png");
        CARD_BACK_IMG = ri.readImage("card_back.png");
        turn = new Sound("turn.wav");
        int WIDTH = CARD_FRONT_IMG.getWidth();
        int HEIGHT = CARD_FRONT_IMG.getHeight();
        state = ModeEnum.BACK;
        existence = ModeEnum.EXISTENCE;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setOpaque(false);
        gameObserver = gamePanel.getGameObserver();
        cardObserver = new CardObserver(this);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        if(existence == ModeEnum.EXISTENCE){
            if(state == ModeEnum.FRONT){
                g2.drawImage(CARD_FRONT_IMG,0,0,null);
            }else{
                g2.drawImage(CARD_BACK_IMG,0,0,null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && state == ModeEnum.BACK){
            Sound s = new Sound("turn.wav",0.3);
            s.play();
            setState(ModeEnum.FRONT);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gameObserver.countFrontCard();
    }

    public void setState(ModeEnum state) {
        this.state = state;
        gameObserver.update();
    }

    public Card getCARD() {
        return CARD;
    }

    public ModeEnum getState() {
        return state;
    }

    public void setExistence(ModeEnum existence) {
        this.existence = existence;
        cardObserver.update();
        if(existence == ModeEnum.UN_EXISTENCE){
            cardObserver.kill();
        }
    }

    public ModeEnum getExistence() {
        return existence;
    }
}
