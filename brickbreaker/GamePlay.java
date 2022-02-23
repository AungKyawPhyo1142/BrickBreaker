/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

/**
 *
 * @author Lenovo
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePlay extends JPanel implements KeyListener,ActionListener {
    
    public boolean play=false;

public int score=0;

public int totalBricks=21;

public Timer timer;

public int delay=8;

public int playerX = 310;

public int ballposX = 330; //120
public int ballposY = 450; //350

public int ballXdir = -1;
public int ballYdir = -2;

public MapGenerator map;

public GamePlay( ) {

    map = new MapGenerator(3,7);
    
addKeyListener(this);
setFocusable(true);
setFocusTraversalKeysEnabled(false);

timer = new Timer(delay,this);
timer.start();

} // constr

public void paint(Graphics g) { 

// the background
g.setColor(Color.BLACK);
g.fillRect(1,1,692,592);

// draw
map.draw((Graphics2D)g);


// the borders
g.setColor(Color.yellow);
g.fillRect(0,0,3,592);
g.fillRect(0,0,692,3);
g.fillRect(691,0,3,592);

//scores

g.setColor(Color.white);
g.setFont(new Font("Century Gothic",Font.BOLD,25));
g.drawString(""+score,590,30);

// paddle
g.setColor(Color.green);
g.fillRect(playerX,550,100,8);

// ball
g.setColor(Color.yellow);
g.fillOval(ballposX,ballposY,20,20);

if (totalBricks<=0) {

    play = false;
    ballXdir=0;
    ballYdir=0;
    
    g.setColor(Color.red);
g.setFont(new Font("Century Gothic",Font.BOLD,30));
g.drawString("YOU WON",260,350);    

g.setFont(new Font("Century Gothic",Font.BOLD,25));
g.drawString("Press Enter To Restart",250,400);    


    
}

if (ballposY > 570){

    play = false;
    ballXdir=0;
    ballYdir=0;
    
    g.setColor(Color.red);
g.setFont(new Font("Century Gothic",Font.BOLD,30));
g.drawString("Game Over , Scores : "+score,190,350);    

g.setFont(new Font("Century Gothic",Font.BOLD,25));
g.drawString("Press Enter To Restart",250,400);    



}

g.dispose();

}

public void keyTyped(KeyEvent e) { }

public void keyReleased(KeyEvent e) { }

public void actionPerformed(ActionEvent ae) {

timer.start();

if (play) { 


if (new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
	ballYdir = -ballYdir;
    }

A :        for (int i=0; i<map.map.length; i++) { 
        
            for (int j=0;j<map.map[0].length;j++) {
                
                if (map.map[i][j]>0) { 
                    
                    int brickX=j*map.brickWidth+80;
                    int brickY=i*map.brickHeight+50;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;
                    
                    Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                    Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
                    Rectangle brickRect = rect;
                    
                    if(ballRect.intersects(brickRect)) {
                        map.setBrickValue(0,i,j);
                        totalBricks--;
                        score += 5;
                        
                        if (ballposX +19 <= brickRect.x || ballposX +1 >=ballRect.x*brickRect.width) {
                            ballXdir = -ballXdir;
                        }
                        else { ballYdir = -ballYdir; }
                        break A;
                    }
                    
                }
                
            }
        }

	ballposX += ballXdir;
	ballposY += ballYdir;

		if (ballposX < 0) { ballXdir = -ballXdir; } // if
	
		if (ballposY < 0) { ballYdir = -ballYdir; } // if

		if (ballposX > 670) { ballXdir = -ballXdir; } // if

	}//play


repaint();

 } // action


public void keyPressed(KeyEvent e) {

if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 

	if (playerX >= 600) { playerX=600; }
	else 		{ moveRight(); } 

} //right

if (e.getKeyCode() == KeyEvent.VK_LEFT) { 

	if (playerX <10) { playerX=10; }
	else 		{ moveLeft(); } 

} //left

if (e.getKeyCode()== KeyEvent.VK_ENTER) {

    if (!play) { play=true;
    
    ballposX=330;
    ballposY=450;
    ballXdir=-1;
    ballYdir=-2;
    playerX=310;
    score=0;
    totalBricks=21;
    map = new MapGenerator(3,7);
    repaint();
    
    }

}

} // key event

public void moveRight( ) { 

play = true;
playerX+=20;

} // move right function

public void moveLeft( ) { 

play = true;
playerX-=20;

} // move left function



    
}
