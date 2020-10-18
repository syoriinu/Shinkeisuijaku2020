import javax.swing.*;

public class TATimer extends JLabel{
    private final Timer timer;
    private final int DELAY = 16;
    private long hours;
    private long minutes;
    private long seconds;
    private long millis;
    private String text;

    public TATimer(){
            hours = 0;
            minutes = 0;
            seconds = 0;
            millis = 0;

            timer = new Timer(DELAY,e -> {
                millis += DELAY;
                if(millis > 1000){
                    seconds++;
                    millis = 0;
                }
                if(seconds == 60){
                    minutes++;
                    seconds = 0;
                }
                if(minutes == 60){
                    hours++;
                    minutes = 0;
                }
                if(hours == 0){
                    text = String.format("%02d:%02d.%03d", minutes, seconds, millis);
                }else{
                    text = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
                }
                setText(text);
        });
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    @Override
    public String getText() {
        return text;
    }
}
