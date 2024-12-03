import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {
    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    Gameselec gameselec = new Gameselec();
    Quicktime quicktime = new Quicktime();
    GameRule gameRule=new GameRule();

    boolean begin = false;   //判断是否双击小黄脸
    boolean victor;     //判断是否胜利
    boolean res = false;
    boolean rule=false;
    boolean confirm=false;


    static Image offGraphics = null;  //创建一个画布
    int w = GameUtil.Weight;
    int h = GameUtil.Height;
    int a = GameUtil.Offset;
    int l = GameUtil.Square_length;
    int W = 2 * a + w * l + a;
    int H = 2 * a + h * l + a;

    void checkbegin(MouseEvent e) {
        if (e.getButton() == 1) {
            if (e.getClickCount() == 2) {
                if (e.getX() > a + l * w / 2 && e.getX() < a + l * w / 2 + l &&
                        e.getY() > a / 3 + 20 && e.getY() < a / 3 + 20 + l) {
                    GameUtil.state = 3;
                    begin = true;
                }
            }
        }
    }

    void IsStarAgain(MouseEvent e) {
        if (e.getButton() == 1) {
            if (e.getX() > a + l * w / 2 && e.getX() < a + l * w / 2 + l &&
                    e.getY() > a / 3 + 20 && e.getY() < a / 3 + 20 + l) {
                GameUtil.state = 0;
                mapBottom.ResetGame();
                mapTop.ResetGame();
                GameUtil.RayNum = 0;
                GameUtil.start_time = System.currentTimeMillis();
            }
        }
    }

    //开始游戏
    void Launch() {
        GameUtil.start_time = System.currentTimeMillis();
        this.setVisible(true);//设置窗口大小可见

        if(GameUtil.level==4){
            this.setTitle("游戏说明");
            this.setSize(800,600);
        }else{
            if (GameUtil.state == 1) {
                victor = true;
                this.setTitle("扫雷");
                this.setSize(500, 600);
            } else if (GameUtil.state == 3) {
                this.setTitle("选择难度");
                this.setSize(300, 400);
            } else {
                this.setTitle("扫雷");
                this.setSize(W, H);//设置窗口大小
            }
        }


        this.setLocationRelativeTo(null);//设置窗体位置
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置窗体关闭方法

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                switch (GameUtil.state) {
                    case 0:
                        //左键被点击
                        if (e.getButton() == 1) {
                            GameUtil.mouse_x = e.getX();//记录鼠标点击时的横坐标
                            GameUtil.mouse_y = e.getY();//记录鼠标点击时的纵坐标
                            GameUtil.left = true;
                        }
                        //右键被点击
                        if (e.getButton() == 3) {
                            GameUtil.mouse_x = e.getX();//记录鼠标点击时的横坐标
                            GameUtil.mouse_y = e.getY();//记录鼠标点击时的纵坐标
                            GameUtil.right = true;
                        }
                        IsStarAgain(e);
                        checkbegin(e);
                        break;
                    case 1:
                        //System.out.println(victor);
                        res = true;
                        if (e.getButton() == 1) {
                            GameUtil.mouse_x = e.getX();//记录鼠标点击时的横坐标
                            GameUtil.mouse_y = e.getY();//记录鼠标点击时的纵坐标
                            if (quicktime.restart()) {
                                victor = false;
                                GameUtil.state = 0;
                                mapBottom.ResetGame();
                                mapTop.ResetGame();
                                GameUtil.RayNum = 0;
                                GameUtil.start_time = System.currentTimeMillis();
                            }
                        }
                    case 2:
                        checkbegin(e);
                        IsStarAgain(e);
                        break;
                    case 3:
                        if (e.getButton() == 1) {
                            GameUtil.mouse_x = e.getX();//记录鼠标点击时的横坐标
                            GameUtil.mouse_y = e.getY();//记录鼠标点击时的纵坐标
                            begin = gameselec.hard();
                            GameUtil.state=0;
                            if(GameUtil.level==4){
                                GameUtil.state=4;
                                rule=true;
                            }
                        }

                        break;
                    case 4:
                        if (e.getButton() == 1) {
                            GameUtil.mouse_x = e.getX();//记录鼠标点击时的横坐标
                            GameUtil.mouse_y = e.getY();//记录鼠标点击时的纵坐标
                            //System.out.println(111);
                            confirm = gameRule.Confirm();
                            if (confirm) {
                                res = true;
                                GameUtil.state = 3;
                                GameUtil.level=0;
                                //System.out.println(222);
                                confirm = false;
                            }
                        }
                        break;
                    default:
                        break;
                }

            }
        });

        while (true) {
            repaint();
            victortime();
            begin();
            Rule();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //弹出胜利窗口
    void victortime() {
        if (res) {
            res = false;
            //System.out.println(111);
            victor = true;
            dispose();
            GameWin gameWin = new GameWin();
            gameWin.Launch();
        }
    }
    //游戏规则窗口
    void Rule(){
        if(rule){
            rule=false;
            //GameUtil.level=0;
            dispose();
            GameWin gameWin = new GameWin();
            gameWin.Launch();
        }
    }

    //选择难度窗口
    void begin() {
        if (begin) {
            begin = false;
            dispose();
            gameselec.hard(GameUtil.level);
            GameWin gameWin = new GameWin();
            GameUtil.RayNum = 0;
            GameUtil.start_time = System.currentTimeMillis();
            mapBottom.ResetGame();
            mapTop.ResetGame();
            gameWin.Launch();
        }
    }

    //设置雷区
    @Override
    public void paint(Graphics g) {
        offGraphics = this.createImage(W, H);//初始化画布，使其与窗口大小一致
        Graphics glmage = offGraphics.getGraphics();//创建画笔
        if(GameUtil.level==4){
            glmage.setColor(Color.white);
            glmage.fillRect(0, 0, 800, 600);
            gameRule.paintSelf(glmage);
            g.drawImage(offGraphics, 0, 0, null);
        }
        else if (victor) {
            glmage.setColor(Color.white);
            glmage.fillRect(0, 0, W, H);
            quicktime.paintSelf(glmage);
            g.drawImage(offGraphics, 0, 0, null);
        }else if (GameUtil.state == 3) {
            glmage.setColor(Color.white);
            glmage.fillRect(0, 0, W, H);
            gameselec.paintSelf(glmage);
            g.drawImage(offGraphics, 0, 0, null);
        } else {
            glmage.setColor(Color.LIGHT_GRAY);
            glmage.fillRect(0, 0, W, H);
            mapBottom.paintSelf(glmage);
            mapTop.paintSelf(glmage);
            g.drawImage(offGraphics, 0, 0, null);
        }
    }


    public static void main(String[] args) {

        GameWin gameWin = new GameWin();
        gameWin.Launch();

    }
}