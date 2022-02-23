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
public class BrickBreaker extends JFrame {

public BrickBreaker ( ) {
    
super("Brick Breaker");

setSize(700,600);

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

setLocationRelativeTo(null);

GamePlay game = new GamePlay( );

getContentPane().add(game);

setVisible(true);

}

public static void main(String[]args) { 

BrickBreaker obj = new BrickBreaker();

}

    
}
