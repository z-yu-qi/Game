import java.awt.*;

public class Gameselec {
    boolean hard(){
        if(GameUtil.mouse_x>0&&GameUtil.mouse_x<300){
            if(GameUtil.mouse_y>0&&GameUtil.mouse_y<100){
                GameUtil.level=1;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.mouse_y>100&&GameUtil.mouse_y<200){
                GameUtil.level=2;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.mouse_y>200&&GameUtil.mouse_y<300){
                GameUtil.level=3;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.mouse_y>300&&GameUtil.mouse_y<400){
                GameUtil.state=4;
                GameUtil.level=4;
                return true;
            }
        }
        return false;
    }
    void paintSelf(Graphics g){
        g.setColor(Color.WHITE);
        //g.drawRect(100,100,300,100);
        g.drawImage(GameUtil.bk,
                0,
                0,
                300,
                100,
                null);
        GameUtil.DarwString(g,"初级(9×9)",80,50,25,Color.black);
        //g.drawRect(100,200,300,100);
        g.drawImage(GameUtil.bk,
                0,
                100,
                300,
                100,
                null);
        GameUtil.DarwString(g,"中级(16×16)",80,150,25,Color.black);

        //g.drawRect(100,300,300,100);
        g.drawImage(GameUtil.bk,
                0,
                200,
                300,
                100,
                null);
        GameUtil.DarwString(g,"高级(16×30)",80,250,25,Color.black);
        g.drawImage(GameUtil.bk,
                0,
                300,
                300,
                100,
                null);
        GameUtil.DarwString(g,"游戏说明",85,350,25,Color.black);
    }

    void hard(int level){
        switch (level){
            case 1:
                GameUtil.Lei_num=10;
                GameUtil.Weight=9;
                GameUtil.Height=9;
                break;
            case 2:
                GameUtil.Lei_num=40;
                GameUtil.Weight=16;
                GameUtil.Height=16;
                break;
            case 3:
                GameUtil.Lei_num=99;
                GameUtil.Weight=30;
                GameUtil.Height=16;
                break;
            default:
                break;
        }
    }
}
