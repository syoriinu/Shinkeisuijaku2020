public class CardObserver extends Observer{

    public CardObserver(CardPanel cardPanel){
        super.cardPanel = cardPanel;
    }

    public void kill(){
        cardPanel.removeMouseListener(cardPanel);
    }

    @Override
    public void update() {
        cardPanel.repaint();
    }
}
