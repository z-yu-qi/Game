//绘制游戏相关组件，底层地图

import java.awt.*;

public class MapBottom {
    //判断雷数
    public void ResetGame() {
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                GameUtil.Data_Bottom[i][j] = 0;
                //GameUtil.Data_Top[i][j]=1;
            }
        }
        bottomRay.GetRay();
    }

    Bottom_Ray bottomRay = new Bottom_Ray();

    int w = GameUtil.Weight;
    int h = GameUtil.Height;
    int a = GameUtil.Offset;
    int l = GameUtil.Square_length;
    int W = 2 * a + w * l + a;
    int H = 2 * a + h * l + a;

    //绘制方法
    public void paintSelf(Graphics g) {
        bottomRay.GetRay();
        for (int i = 2 * a; i <= W - a; i = i + l) {
            g.setColor(Color.black);
            g.drawLine(i + 1, 2 * a, i + 1, 600 - a);
        }
        for (int i = 2 * a; i <= H - a; i = i + l) {
            g.setColor(Color.black);
            g.drawLine(2 * a, i + 1, 600 - a, i + 1);
        }

        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                if (GameUtil.Data_Bottom[i][j] == -1) {
                    g.drawImage(GameUtil.lei,
                            a * 2 + (i - 1) * l,
                            a * 2 + (j - 1) * l,
                            l,
                            l,
                            null);
                }
                if (GameUtil.Data_Bottom[i][j] >= 0) {
                    int s = GameUtil.Data_Bottom[i][j];
                    g.drawImage(GameUtil.images[s],
                            a * 2 + (i - 1) * l,
                            a * 2 + (j - 1) * l,
                            l,
                            l,
                            null);
                }
            }
        }
        int x = GameUtil.Lei_num - GameUtil.RayNum;
        GameUtil.DarwString(g, "" + x, a - 20, a + 20, 25, Color.black);
        long x1 = (GameUtil.end_time - GameUtil.start_time) / 1000;
        GameUtil.DarwString(g, "" + x1, 2*a+l*w+5, a + 20, 25, Color.black);
        //GameUtil.DarwString(g,"最快用时："+ GameUtil.arr.get(0),w-30,h-30,25,Color.black);

        switch (GameUtil.state) {
            case 0:
                GameUtil.end_time = System.currentTimeMillis();

                g.drawImage(GameUtil.face,
                        a + l * w / 2,
                        a / 3 + 20,
                        l,
                        l,
                        null);
                break;
            case 1:
                g.drawImage(GameUtil.yxz,
                        a + l * w / 2,
                        a / 3 + 20,
                        l,
                        l,
                        null);
                break;
            case 2:
                g.drawImage(GameUtil.sb,
                        a + l * w / 2,
                        a / 3 + 20,
                        l,
                        l,
                        null);
                break;
            default:
                break;
        }
    }
}
