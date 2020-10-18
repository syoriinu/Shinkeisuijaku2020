import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ImgData {
    private final Map<String, ImageIcon> imgMap;

    public ImgData(){
        imgMap = new HashMap<>();
        ReadingImage ri = new ReadingImage();
        imgMap.put("END",new ImageIcon(ri.readItemImage("END.png")));
        imgMap.put("GIVE_UP",new ImageIcon(ri.readItemImage("GIVE_UP.png")));
        imgMap.put("INTRO_NO_MISTAKES",new ImageIcon(ri.readItemImage("INTRO_NO_MISTAKES.png")));
        imgMap.put("INTRO_NORMAL",new ImageIcon(ri.readItemImage("INTRO_NORMAL.png")));
        imgMap.put("INTRO_TIME_ATTACK",new ImageIcon(ri.readItemImage("INTRO_TIME_ATTACK.png")));
        imgMap.put("NO_MISTAKES",new ImageIcon(ri.readItemImage("NO_MISTAKES.png")));
        imgMap.put("NORMAL",new ImageIcon(ri.readItemImage("NORMAL.png")));
        imgMap.put("START",new ImageIcon(ri.readItemImage("START.png")));
        imgMap.put("TIME_ATTACK",new ImageIcon(ri.readItemImage("TIME_ATTACK.png")));
    }

    public Map<String, ImageIcon> getImgMap() {
        return imgMap;
    }

}
