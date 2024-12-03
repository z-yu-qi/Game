//工具类，存放静态参数，工具方法

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GameUtil {
    static int Lei_num = 100;
    static int Weight = 36;  //宽,横着的格子数量
    static int Height = 36;   //高，竖着的格子数量
    static int Offset = 50;   //雷区偏移量
    static int Square_length = 50;   //格子边长

    //鼠标相关参数
    static int mouse_x;//横坐标

    static int mouse_y;//纵坐标
    static int RayNum = 0;

    //状态
    static boolean left = false;//鼠标左键
    static boolean right = false;//鼠标右键

    static int state = 3;//游戏状态,0游戏中，1胜利，2失败,3游戏难度选择,4说明

    //开始时间
    static long start_time;
    static long end_time;

    static ArrayList<Integer> arr1 = new ArrayList<>();

    static ArrayList<Integer> arr2 = new ArrayList<>();
    static ArrayList<Integer> arr3 = new ArrayList<>();
    static boolean isvictor = false;

    //游戏难度,1初级，2中级，3高级，4游戏说明
    static int level;


    //-1代表雷，0代表空格，1-8代表周围雷的数量
    //底层元素
    static int[][] Data_Bottom = new int[Weight + 2][Height + 2];

    //-1无覆盖，0代表覆盖，1插旗，2插错旗
    //顶层元素
    static int[][] Data_Top = new int[Weight + 5][Height + 5];

    static {
        for (int i = 0; i < Weight + 5; i++) {
            for (int j = 0; j < Height + 5; j++) {
                Data_Top[i][j] = 0;
            }
        }
    }

    //载入图片
    static Image flag = Toolkit.getDefaultToolkit().getImage("imgs\\game\\flag.gif");
    static Image waflag = Toolkit.getDefaultToolkit().getImage("imgs\\game\\waflag.jpg");
    static Image dc = Toolkit.getDefaultToolkit().getImage("imgs\\game\\dc.gif");

    static Image lei = Toolkit.getDefaultToolkit().getImage("imgs\\game\\lei.jpg");
    static Image face = Toolkit.getDefaultToolkit().getImage("imgs\\game\\face.jpg");
    static Image sb = Toolkit.getDefaultToolkit().getImage("imgs\\game\\sb.jpg");
    static Image yxz = Toolkit.getDefaultToolkit().getImage("imgs\\game\\yxz.jpg");
    static Image sl = Toolkit.getDefaultToolkit().getImage("imgs\\game\\sl.jpg");

    static Image bk = Toolkit.getDefaultToolkit().getImage("imgs\\game\\biank.jpg");
    static Image zlyj = Toolkit.getDefaultToolkit().getImage("imgs\\game\\zlyj.jpg");
    //载入图片
    static Image[] images = new Image[100];

    static {
        for (int i = 0; i < 9; i++) {
            images[i] = Toolkit.getDefaultToolkit().getImage("imgs\\game\\" + i + ".jpg");
        }
    }

    public static Image[] getImages() {
        return images;
    }

    static void DarwString(Graphics g, String str, int x, int y, int size, Color color) {
        g.setColor(color);
        g.setFont(new Font("幼圆", Font.BOLD, size));
        g.drawString(str, x, y);
    }
}
