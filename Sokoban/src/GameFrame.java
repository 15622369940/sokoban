import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameFrame extends JFrame {

JPanel panel = new JPanel();
//[2.3] 加入工人
JLabel worker = new JLabel(new ImageIcon("workerLeft.png"));
public static final int SPEED = 12;
//[2.6] 加入围墙
int[] pos = new int[]{0,0, 1,0, 2,0, 3,0, 4,0, 5,0, 6,0, 7,0, 8,0, 9,0, 10,0, 0,1, 0,2, 0,3, 0,4, 0,5, 0,6, 0,7, 0,8, 0,9, 0,10, 0,11, 0,12, 1,12, 2,12, 3,12, 4,12, 5,12, 6,12, 7,12, 8,12, 9,12, 10,12, 10,1, 10,2, 10,3, 10,4, 10,5, 10,6, 10,7, 10,8, 10,9, 10,10, 10,11, 10,12, 1,3, 2,3, 3,3, 4,3, 5,3, 6,3, 	5,8, 6,8, 7,8, 8,8, 9,8, 10,8 ,11,8 };
JLabel[] walls = new JLabel[pos.length/2];
//[3.1] 加入箱子与胜利标志
JLabel box = new JLabel(new ImageIcon("box.png"));
JLabel goal = new JLabel(new ImageIcon("goal.png"));

public GameFrame() {
   //[2.2] 完善窗体
   this.setTitle("趣味推箱子");
   this.setBounds(200, 60, 535, 650);
   this.setContentPane(panel);
   panel.setLayout(null);
   panel.setBackground(Color.white);
   //[2.3] 加入工人
   panel.add(worker);
   worker.setBounds(240, 240, 48, 48);
   //[2.6] 加入围墙
   for (int i = 0; i < walls.length; i++) {
    walls[i] = new JLabel(new ImageIcon("wall.png"));
    panel.add(walls[i]);
    walls[i].setBounds(pos[i * 2] * 48, pos[i * 2 + 1] * 48, 48 , 48);
   
   }
   //[3.1] 加入箱子与胜利标志
   panel.add(box);
   panel.add(goal);
   box.setBounds(240, 96, 48, 48);
   goal.setBounds(336, 432, 48, 48);
   this.setVisible(true);
   //[2.4] 监听键盘事件
   this.addKeyListener(new KeyListener() {
    //键盘按下事件
    public void keyPressed(KeyEvent e) {
     //[2.5] 使工人可以移动
     int xSpeed = 0, ySpeed = 0;
     switch (e.getKeyCode()) {
     case KeyEvent.VK_LEFT : 
      xSpeed = -SPEED;
      worker.setIcon(new ImageIcon("workerLeft.png"));
      break;
     case KeyEvent.VK_RIGHT : 
      xSpeed = SPEED;
      worker.setIcon(new ImageIcon("workerRight.png"));
      break;
     case KeyEvent.VK_UP : 
      ySpeed = -SPEED;
      worker.setIcon(new ImageIcon("workerUp.png"));
      break;
     case KeyEvent.VK_DOWN : 
      ySpeed = SPEED;
      worker.setIcon(new ImageIcon("workerDown.png"));
      break;
     default:
      return;
     }
     worker.setBounds(worker.getX() + xSpeed, worker.getY() + ySpeed, worker.getWidth(), worker.getHeight());
     //[2.7] 判断工人是否撞到墙壁
for (int i = 0; i < walls.length; i++) {
      if (worker.getBounds().intersects(walls[i].getBounds())) {
       worker.setBounds(worker.getX() - xSpeed, worker.getY() - ySpeed, worker.getWidth(), worker.getHeight());
      }
     }
     //[3.2] 使工人可以推动箱子
     if (worker.getBounds().intersects(box.getBounds())) {
      box.setBounds(box.getX() + xSpeed, box.getY() + ySpeed, box.getWidth(), box.getHeight());
      //[3.3] 判断箱子是否撞到墙壁
      for (int i = 0; i < walls.length; i++) {
       if (box.getBounds().intersects(walls[i].getBounds())) {
        worker.setBounds(worker.getX() - xSpeed, worker.getY() - ySpeed, worker.getWidth(), worker.getHeight());
        box.setBounds(box.getX() - xSpeed, box.getY() - ySpeed, box.getWidth(), box.getHeight());
       }
      }
      //[3.4] 判断是否胜利
      if (box.getX()==goal.getX() && box.getY()==goal.getY()) {
       JOptionPane.showMessageDialog(null, "您赢啦！");
      }
     }
    }

    public void keyReleased(KeyEvent e) {
    }
public void keyTyped(KeyEvent e) {
    }});
}
public static void main(String[] args) {
   //[2.1]创建窗体
   new GameFrame();
}
}


