//初始化地雷
public class Bottom_Ray {
    public int check() {
        int x = 0;
        for (int i = 1; i <= GameUtil.Weight; i++) {
            for (int j = 1; j <= GameUtil.Height; j++) {
                if (GameUtil.Data_Bottom[i][j] == -1) {
                    x++;
                }
            }
        }
        return x;
    }

    public void check(int x, int y) {
        if (GameUtil.Data_Bottom[x][y] == -1) {
            while (true) {
                int xx = (int) (Math.random() * GameUtil.Weight + 1);
                int yy = (int) (Math.random() * GameUtil.Height + 1);
                if (GameUtil.Data_Bottom[xx][yy] != -1) {
                    GameUtil.Data_Bottom[xx][yy] = -1;
                    break;
                }
            }
        } else {
            GameUtil.Data_Bottom[x][y] = -1;
        }
    }

    //随机生成雷
    public void GetRay() {
        //Init();
        if (check() == GameUtil.Lei_num) {
            return;
        }

        int w = GameUtil.Weight;
        int h = GameUtil.Height;
        for (int i = 0; i < GameUtil.Lei_num; i++) {
            int x = (int) (Math.random() * w + 1);
            int y = (int) (Math.random() * h + 1);
            check(x, y);
        }
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                if (GameUtil.Data_Bottom[i][j] == -1) {
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (GameUtil.Data_Bottom[k][l] >= 0) {
                                GameUtil.Data_Bottom[k][l]++;
                            }
                        }
                    }
                }
            }
        }

    }

}
