package menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import org.lwjgl.input.Keyboard;

public class Menu extends JFrame implements KeyListener{
	public static JPanel jp = new JPanel();
	public static JLabel jl;
	public Menu(){

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);
		this.setBounds(0,0,200,200);

		JButton Start = new JButton("Start");
		JButton firekey = new JButton("firekey");
		JButton chargekey = new JButton("chargekey");
		JButton keepFiring = new JButton("keepFiring");
		JButton moveUp = new JButton("moveUp");
		JButton moveDown = new JButton("moveDown");
		JButton moveLeft = new JButton("moveLeft");
		JButton moveRight = new JButton("moveRight");
		
		Start.setSize(100, 10);
		firekey.setSize(100, 10);
		chargekey.setSize(100, 10);
		keepFiring.setSize(100, 10);
		moveUp.setSize(100, 10);
		moveDown.setSize(100, 10);
		moveLeft.setSize(100, 10);
		moveRight.setSize(100, 10);
		
		JLabel labelstart = new JLabel("Start");
		jp.add(labelstart);
		jp.add(Start, BorderLayout.CENTER);
		Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	Menu.jl = (JLabel)jp.getComponent(0);
            }
		});
		jp.add(new JLabel("firekey"));
		jp.add(firekey, BorderLayout.LINE_END);
		jp.add(new JLabel("chargekey"));
		jp.add(chargekey);
		jp.add(new JLabel("keepFiring"));
		jp.add(keepFiring);
		jp.add(new JLabel("moveUp"));
		jp.add(moveUp);
		jp.add(new JLabel("moveDown"));
		jp.add(moveDown);
		jp.add(new JLabel("moveLeft"));
		jp.add(moveLeft);
		jp.add(new JLabel("moveRight"));
		jp.add(moveRight);

		
		
		this.getContentPane().add(jp);
		this.pack();
		this.setVisible(true);
		/*
		this.add(new JButton("Start");
		this.add(new JButton("firekey"));
		this.add(new JButton("chargekey"));
		this.add(new JButton("keepFiring"));
		this.add(new JButton("moveUp"));
		this.add(new JButton("moveDown"));
		this.add(new JButton("moveLeft"));
		this.add(new JButton("moveRight"));
		*/
		/*
		firekey = Keyboard.KEY_A;
		chargekey = Keyboard.KEY_S;
		keepFiring = Keyboard.KEY_D;
		moveUp = Keyboard.KEY_UP;
		moveDown = Keyboard.KEY_DOWN;
		moveLeft = Keyboard.KEY_LEFT;
		moveRight = Keyboard.KEY_RIGHT;
		*/
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		Menu.jl.setText(new String(new char[]{e.getKeyChar()}));
		System.out.println("hello");
		
	}
}
