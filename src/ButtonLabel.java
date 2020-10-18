import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ButtonLabel extends BaseLabel{
    private final GamePanel gamePanel;
    private final Settings nextMode;
    private Settings introMode;

    public ButtonLabel(GamePanel gamePanel, Settings nextMode, ImageIcon IMAGE_ICON){
        this.gamePanel = gamePanel;
        this.nextMode = nextMode;
        setBackground(Color.RED);
        setIcon(IMAGE_ICON);
        addMouseListener(this);
    }

    public ButtonLabel(GamePanel gamePanel, Settings nextMode, Settings introMode, ImageIcon IMAGE_ICON){
        this.gamePanel = gamePanel;
        this.nextMode = nextMode;
        this.introMode = introMode; //Settings.INTRODUCTION
        setBackground(Color.RED);
        setIcon(IMAGE_ICON);
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        gamePanel.removeAll();
        if(nextMode == null){
            System.exit(0);
        }



        switch (nextMode){
            case START:
                gamePanel.setScreenMode(Settings.START);
                break;
            case NORMAL:
                gamePanel.introduction(Settings.NORMAL);
                break;
            case NO_MISTAKES:
                gamePanel.introduction(Settings.NO_MISTAKES);
                break;
            case TIME_ATTACK:
                gamePanel.introduction(Settings.TIME_ATTACK);
                break;
            case GIVE_UP:
                gamePanel.reset();
                gamePanel.setScreenMode(Settings.START);
                break;
        }

        if(introMode == Settings.INTRODUCTION){
            gamePanel.setUp(nextMode);
        }


    }
}
