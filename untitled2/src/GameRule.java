import java.awt.*;

public class GameRule {
    //判断是否点击确认
    boolean Confirm(){
        if(GameUtil.mouse_x>280&&GameUtil.mouse_x<480&&GameUtil.mouse_y>450&&GameUtil.mouse_y<550){
            //GameUtil.level=3;
            //System.out.println(333);
            return true;
        }
        return false;
    }

    //绘制游戏说明
    void paintSelf(Graphics g){


        GameUtil.DarwString(g, "1.雷区被分为一个个方格，每个方格可以是地雷",
                80,80,30,Color.black);
        GameUtil.DarwString(g,"或是数字。每个数字表示其周围8个方格中地雷" ,80,120,30,Color.black);
        GameUtil.DarwString(g,"的数量。玩家需要左键点击方格来揭开它，右键" ,80,160,30,Color.black);
        GameUtil.DarwString(g,"点击方格来标记地雷。如果玩家点击到地雷，游" ,80,200,30,Color.black);
        GameUtil.DarwString(g,"戏结束。如果非雷方格全部被翻开，则游戏胜利" ,80,240,30,Color.black);
        GameUtil.DarwString(g,"2.双击笑脸可选择难度与查看游戏说明" ,80,300,30,Color.black);
        GameUtil.DarwString(g,"3.左上角是剩余雷数，右上角是计时" ,80,360,30,Color.black);
        GameUtil.DarwString(g,"4.失败后点击笑脸可重新开始游戏" ,80,420,30,Color.black);
        g.drawImage(GameUtil.bk,
                280,
                450,
                200,
                100,
                null);
        GameUtil.DarwString(g,"确认",350,500,30,Color.black);

    }
}
