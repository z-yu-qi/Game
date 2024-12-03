import java.awt.*;
import java.util.Comparator;

//顶层地图类
public class MapTop {
    Bottom_Ray bottomRay = new Bottom_Ray();

    public int temp_x;
    public int temp_y;
    int w = GameUtil.Weight;
    int h = GameUtil.Height;
    int a = GameUtil.Offset;
    int l = GameUtil.Square_length;

    public void ResetGame(){
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                GameUtil.Data_Top[i][j]=0;
            }
        }
    }

    //判断逻辑
    public void logic() {

        temp_x = 0;
        temp_y = 0;
        if (GameUtil.mouse_x > 2 * a && GameUtil.mouse_y > 2 * a) {
            temp_x = (GameUtil.mouse_x - (2 * a)) / l + 1;
            temp_y = (GameUtil.mouse_y - (2 * a)) / l + 1;
        }
        if (temp_x > 0 && temp_x <= w && temp_y > 0 && temp_y <= h) {
            if (GameUtil.left) {
                if (GameUtil.Data_Top[temp_x][temp_y] == 0) {
                    GameUtil.Data_Top[temp_x][temp_y] = -1;
                }
                SpaceOpen(temp_x, temp_y);
                GameUtil.left = false;
            }
            if (GameUtil.right) {
                if (GameUtil.Data_Top[temp_x][temp_y] == 0) {
                    GameUtil.Data_Top[temp_x][temp_y] = 1;
                    GameUtil.RayNum++;
                } else if (GameUtil.Data_Top[temp_x][temp_y] == 1) {
                    GameUtil.Data_Top[temp_x][temp_y] = 0;
                }
                else if(GameUtil.Data_Top[temp_x][temp_y]==-1){
                    numOpen(temp_x,temp_y);
                }
                GameUtil.right = false;
            }
        }
        boom();
        Victory();
    }

    //打开空格
    void SpaceOpen(int x, int y) {
        if (GameUtil.Data_Bottom[x][y] == 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.Data_Bottom[i][j] != -1 && GameUtil.Data_Top[i][j] == 0) {
                        GameUtil.Data_Top[i][j] = -1;
                        if (i >= 1 && j >= 1 && i <= GameUtil.Weight && j <= GameUtil.Height) {
                            SpaceOpen(i, j);
                        }
                    }
                }
            }
        }

    }

    public void numOpen(int x, int y) {
        int cnt = 0;//记录旗帜数量
        if (GameUtil.Data_Bottom[x][y] > 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.Data_Top[i][j] == 1) {
                        cnt++;
                    }
                }
            }
            if (cnt == GameUtil.Data_Bottom[x][y]) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (GameUtil.Data_Top[i][j] != 1) {
                            GameUtil.Data_Top[i][j]=-1;
                        }
                        if (i >= 1 && j >= 1 && i <= GameUtil.Weight && j <= GameUtil.Height) {
                            SpaceOpen(i, j);
                        }
                    }
                }
            }

        }
    }

    public boolean boom(){
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                if(GameUtil.Data_Top[i][j]==-1&&GameUtil.Data_Bottom[i][j]==-1){
                    GameUtil.state=2;
                    seeBoom();
                    return true;
                }
            }
        }
        return false;
    }
    public void seeBoom(){
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                if(GameUtil.Data_Top[i][j]!=1&&GameUtil.Data_Bottom[i][j]==-1){
                    GameUtil.Data_Top[i][j]=-1;
                }
                if(GameUtil.Data_Top[i][j]==1&&GameUtil.Data_Bottom[i][j]!=-1){
                    GameUtil.Data_Top[i][j]=2;
                }
            }
        }
    }

    public boolean Victory(){
        int cnt=0;
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                if(GameUtil.Data_Top[i][j]!=-1){
                    cnt++;
                }
            }
        }
        if(cnt==GameUtil.Lei_num){

            long time=(GameUtil.end_time - GameUtil.start_time) / 1000;
            if(GameUtil.level==1){
                GameUtil.arr1.add((int) time);
            }
            if(GameUtil.level==2){
                GameUtil.arr2.add((int) time);
            }
            if(GameUtil.level==3){
                GameUtil.arr3.add((int) time);
            }
            for (int i = 1; i <= w; i++) {
                for (int j = 1; j <= h; j++) {
                    if(GameUtil.Data_Top[i][j]!=-1){
                        GameUtil.Data_Top[i][j]=1;
                    }
                }
            }
            GameUtil.state=1;
            return true;
        }else {
            return false;
        }
    }
    public void paintSelf(Graphics g) {
        logic();
        bottomRay.GetRay();
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                if (GameUtil.Data_Top[i][j] == 0) {
                    g.drawImage(GameUtil.dc,
                            a * 2 + (i - 1) * l,
                            a * 2 + (j - 1) * l,
                            l,
                            l,
                            null);
                } else if (GameUtil.Data_Top[i][j] == 1) {
                    g.drawImage(GameUtil.flag,
                            a * 2 + (i - 1) * l,
                            a * 2 + (j - 1) * l,
                            l,
                            l,
                            null);
                } else if (GameUtil.Data_Top[i][j] == 2) {
                    g.drawImage(GameUtil.waflag,
                            a * 2 + (i - 1) * l,
                            a * 2 + (j - 1) * l,
                            l,
                            l,
                            null);
                }
            }
        }
    }
}
