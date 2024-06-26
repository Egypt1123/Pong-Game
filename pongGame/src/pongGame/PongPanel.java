package pongGame; 
import java.awt.BasicStroke;
 import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.Stroke;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.KeyEvent;
 import java.awt.event.KeyListener;
 import javax.swing.JPanel;
 import javax.swing.Timer;
   
  public class PongPanel extends JPanel implements ActionListener, KeyListener {
       
       private final static Color BACKGROUND_COLOUR = Color.BLACK;
       private final static int TIMER_DELAY = 5;
       private final static int BALL_MOVEMENT_SPEED = 2;
       GameState gameState = GameState.INITIALISING;
         private final static int POINTS_TO_WIN = 3;
         int player1Score = 0, player2Score = 0;
         Player gameWinner;
       
       Ball ball;
       Paddle paddle1, paddle2;
       
       public PongPanel() {
           setBackground(BACKGROUND_COLOUR);
           Timer timer = new Timer(TIMER_DELAY,this);
           timer.start();
           addKeyListener(this);
            setFocusable(true);
       }
       
       private void checkWin() {
           if(player1Score >= POINTS_TO_WIN) {
               gameWinner = Player.One;
               gameState = GameState.GAMEOVER;
           } else if(player2Score >= POINTS_TO_WIN) {
               gameWinner = Player.Two;
               gameState = GameState.GAMEOVER;
           }
       }
       
       private void addScore(Player player) {
           if(player == Player.One) {
               player1Score++;
           } else if(player == Player.Two) {
               player2Score++;
           }
       }
       
       public void createObjects() {
           ball = new Ball(getWidth(), getHeight());
           paddle1 = new Paddle(Player.One, getWidth(), getHeight());
           paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
       }
       
       
       
       private void paintDottedLine(Graphics g) {
          Graphics2D g2d = (Graphics2D) g.create();
              Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
              g2d.setStroke(dashed);
              g2d.setPaint(Color.WHITE);
              g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
              g2d.dispose();
       }
       
       private void paintSprite(Graphics g, Sprite sprite) {
           g.setColor(sprite.getColor());
           g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
       }
   
       @Override
       public void keyPressed(KeyEvent event) {
           if(event.getKeyCode() == KeyEvent.VK_W) {
               paddle1.setYVelocity(-1);
           } else if(event.getKeyCode() == KeyEvent.VK_S) {
               paddle1.setYVelocity(1);
           }
           if(event.getKeyCode() == KeyEvent.VK_UP) {
               paddle2.setYVelocity(-1);
           } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
               paddle2.setYVelocity(1);
           }
       }
   
       @Override
       public void keyReleased(KeyEvent event) {
           if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
               paddle1.setYVelocity(0);
           }
           if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
               paddle2.setYVelocity(0);
           }
       }
       @Override
       public void keyTyped(KeyEvent event) {
           
       }
       
       @Override
       public void actionPerformed(ActionEvent event) {
           update();
           repaint();
       }
       private void resetBall() {
           ball.resetToInitialPosition();
       }
       private void checkWallBounce() {
           if(ball.getXPosition() <= 0) {
               // Hit left side of screen
               ball.setXVelocity(-ball.getXVelocity());
               addScore(Player.Two);
               resetBall();
           } else if(ball.getXPosition() >= getWidth() - ball.getWidth()) {
               // Hit right side of screen
               ball.setXVelocity(-ball.getXVelocity());
               addScore(Player.One);
               resetBall();
           }
           if(ball.getYPosition() <= 0 || ball.getYPosition() >= getHeight() - ball.getHeight()) {
               // Hit top or bottom of screen
               ball.setYVelocity(-ball.getYVelocity());
           }
       }
       private void moveObject(Sprite object) {
    	      object.setXPosition(object.getXPosition() + object.getXVelocity(),getWidth());
    	      object.setYPosition(object.getYPosition() + object.getYVelocity(),getHeight());
    	 }
       @Override
            public void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 paintDottedLine(g);
                 if(gameState != GameState.INITIALISING) {
                     paintSprite(g, ball);
                     paintSprite(g, paddle1);
                     paintSprite(g, paddle2);
                     paintScores(g);
                 }
             }
       private void paintScores(Graphics g) {
    	              int xPadding = 100;
    	               int yPadding = 100;
    	               int fontSize = 50; 
    	               Font scoreFont = new Font("Serif", Font.BOLD, fontSize);
    	               String leftScore = Integer.toString(player1Score);
    	               String rightScore = Integer.toString(player2Score);
    	               g.setFont(scoreFont);
    	               g.drawString(leftScore, xPadding, yPadding);
    	             g.drawString(rightScore, getWidth()-xPadding, yPadding);
    	        }
       
       
       private void checkPaddleBounce() {
    	         if(ball.getXVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
    	             ball.setXVelocity(BALL_MOVEMENT_SPEED);
    	         }
    	         if(ball.getXVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
    	             ball.setXVelocity(-BALL_MOVEMENT_SPEED);
    	         }
    	         }
       
       private final static int WINNER_TEXT_X = 200;
       private final static int WINNER_TEXT_Y = 200;
       private final static int WINNER_FONT_SIZE = 40;
       private final static String WINNER_FONT_FAMILY = "Serif";
 private final static String WINNER_TEXT = "WIN!";
   
 private void paintWinner(Graphics g) {
           if(gameWinner != null) {
               Font winnerFont = new Font(WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
              g.setFont(winnerFont);
              int xPosition = getWidth() / 2;
              if(gameWinner == Player.One) {
                  xPosition -= WINNER_TEXT_X;
              } else if(gameWinner == Player.Two) {
                  xPosition += WINNER_TEXT_X;
              }
              g.drawString(WINNER_TEXT, xPosition, WINNER_TEXT_Y);
          }
      }
       private void update() {
           switch(gameState) {
               case INITIALISING: {
                   createObjects();
                   gameState = GameState.PLAYING;
                   ball.setXVelocity(BALL_MOVEMENT_SPEED);
                   ball.setYVelocity(BALL_MOVEMENT_SPEED);
                   break;
               }
              case PLAYING: {
                  moveObject(paddle1);
                  moveObject(paddle2);
                  moveObject(ball);    // Move ball
                  checkWallBounce();    // Check for wall bounce
                  checkPaddleBounce();// Check for paddle bounce
                  checkWin();        // Check if the game has been won
                  break;
              }
              case GAMEOVER: {
                  break;
              }
          }
 }
  }