import java.awt.*;
import java.util.ArrayList;

public class Quicktime {

    int FindMinNum(ArrayList<Integer> a){
        int m=100000;
        for (int i = 0; i < a.size(); i++) {
            m=Math.min(m,a.get(i));
        }
        return m;
    }
    boolean restart(){
        if(GameUtil.mouse_x>100&&GameUtil.mouse_x<400&&GameUtil.mouse_y>400&&GameUtil.mouse_y<500){
            GameUtil.state=0;
            return true;
        }
        return false;
    }
    void paintSelf(Graphics g){
        g.drawImage(GameUtil.bk,
                50,
                50,
                400,
                100,
                null);
        g.drawImage(GameUtil.bk,
                100,
                400,
                300,
                100,
                null);
        if(GameUtil.level==1&&GameUtil.state==1){
            int index=GameUtil.arr1.size()-1;
            GameUtil.DarwString(g,"恭喜通过初级",150,100,35,Color.black);
            GameUtil.DarwString(g,"本局用时     "+GameUtil.arr1.get(index),
                    150,250,30,Color.BLACK);
            GameUtil.DarwString(g,"最快用时:    "+FindMinNum(GameUtil.arr1),
                    150,300,30,Color.BLACK);

            GameUtil.DarwString(g,"再来一局",150,450,45,Color.black);
        } else if (GameUtil.level == 2&&GameUtil.state==1) {
            int index=GameUtil.arr2.size()-1;
            GameUtil.DarwString(g,"恭喜通过中级",150,100,35,Color.black);
            GameUtil.DarwString(g,"本局用时    "+GameUtil.arr2.get(index),
                    150,250,30,Color.BLACK);
            GameUtil.DarwString(g,"最快用时    "+FindMinNum(GameUtil.arr2),
                    150,300,30,Color.BLACK);
            //g.drawRect(100,400,300,100);
            GameUtil.DarwString(g,"再来一局",150,450,45,Color.black);
        }else if (GameUtil.level == 3&&GameUtil.state==1) {
            int index=GameUtil.arr3.size()-1;
            GameUtil.DarwString(g,"恭喜通过高级",150,100,35,Color.black);
            GameUtil.DarwString(g,"本局用时    "+GameUtil.arr3.get(index),
                    150,250,30,Color.BLACK);
            GameUtil.DarwString(g,"最快用时    "+FindMinNum(GameUtil.arr3),
                    150,300,30,Color.BLACK);
            //g.drawRect(100,400,300,100);
            GameUtil.DarwString(g,"再来一局",150,450,45,Color.black);
        }

    }
}
