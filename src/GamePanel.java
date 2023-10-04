import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
  
static final int SCREEN_WIDTH = 600;
static final int SCREEN_HEIGHT = 600;
static final int UNIT_SIZE = 25;
static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
static final int DELAY = 60;
final int x[] = new int [GAME_UNITS];
final int y[] = new int [GAME_UNITS];
int bodyParts = 6;
int applesEaten;
int appleX;
int appleY;
char direction = 'R';
boolean running = false ;
Timer timer;
Random random;



  GamePanel(){
random = new Random();
this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
this.setBackground(Color.BLACK);
this.setFocusable(true);
this.addKeyListener(new MyKeyAdapter());
startGame();
  }

  public void startGame(){
newApple();
running = true ;
timer = new Timer(DELAY, this);
timer.start();
  }

  public void paintComponent(Graphics g){
super.paintComponent(g);
  }

public void draw (Graphics g){
for(int i=0; i<SCREEN_HEIGHT/UNIT_SIZE;i++){
  g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
  g.drawLine(0,  i*UNIT_SIZE, SCREEN_WIDTH , i*UNIT_SIZE);
}
g.setColor(Color.red);
g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

for(int i = 0; i<bodyParts;i++){
  if(i == 0){
    g.setColor(Color.green);
    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
  }
  else {
g.setColor(new Color(45, 180, 0));
g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

  }
}

}
  
public void move(){
for(int i = bodyParts;i>0;i--){
  x[i] = x[i-1];
  y[i] = y[i-1];
}
switch(direction){
  case 'W':
  y[0] = y[0] - UNIT_SIZE;
  break;
   case 'S':
  y[0] = y[0] + UNIT_SIZE;
  break;
   case 'A':
  x[0] = x[0] - UNIT_SIZE;
  break;
   case 'D':
  x[0] = y[0] - UNIT_SIZE;
  break;
}
}

public void newApple(){
appleX= random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
appleY= random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
}

public void checkApple(){

}

public void checkCollisions(){

  for(int i = bodyParts;i<0;--i){
    if((x[0] == x[i])&& (y[0] == y[i])){
running = false;
    }
  }
if(x[0] < 0){
  running= false;

}
if(x[0] > SCREEN_WIDTH){
  running= false;
}
if(y[0] < 0){
  running= false;
  
}
if(y[0] > SCREEN_HEIGHT){
  running= false;
  
}
if(!running){
  timer.stop();
}
}

public void gameOver(Graphics g){

}


public class MyKeyAdapter extends KeyAdapter{
  @Override
  public void keyPressed(KeyEvent e){
    
  }
}
@Override
public void actionPerformed(ActionEvent e) {
  if(running){
    move();
    checkApple();
    checkCollisions();

  }
  repaint();
}
}
