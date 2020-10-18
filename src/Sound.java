import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.*;

public class Sound {
    private final Clip c;
    public Sound(String fileName){
        URL url = this.getClass().getResource("sound/" + fileName);
        c = createClip(url);
    }
    public Sound(String fileName,double rate){
        URL url = this.getClass().getResource("sound/" + fileName);
        c = createClip(url);
        FloatControl control = (FloatControl)c.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float)Math.log10(rate) * 20);
    }



    public Clip createClip(URL url) {
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(url)){

            AudioFormat af = ais.getFormat();

            DataLine.Info dataLine = new DataLine.Info(Clip.class,af);

            Clip c = (Clip)AudioSystem.getLine(dataLine);

            c.open(ais);

            return c;
        } catch (MalformedURLException e) {

        } catch (UnsupportedAudioFileException e) {

        } catch (IOException e) {

        } catch (LineUnavailableException e) {

        }
        return null;
    }

    public void play(){
        c.start();
    }

    public void loop(){
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }
}