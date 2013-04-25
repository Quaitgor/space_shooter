package menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

import org.lwjgl.input.Keyboard;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Menu extends JFrame{
	public static JPanel jp = new JPanel();
	public static JLabel jl;
	Vector<JButton> Buttons = new Vector<JButton>();
	
	private class TextSwitcher implements KeyListener{
		public JButton Owner;
		public TextSwitcher(JButton Owner){
			this.Owner = Owner;
		}
		public void keyPressed(KeyEvent arg0) {}
		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent arg0) {
			Owner.setText(new String(new char[]{arg0.get.getKeyChar()}));
		}
	}
	public Menu(){

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);
		this.setBounds(0,0,200,200);
		
		String[] Names = new String[]{
				"Start",
				"firekey",
				"chargekey",
				"keepFiring",
				"moveUp",
				"moveDown",
				"moveLeft",
				"moveRight"
		};
		Vector<JLabel> Labels = new Vector<JLabel>();
		for(int i=0;i<Names.length;++i){
			Labels.add(new JLabel(Names[i]));
			jp.add(Labels.lastElement());
			Buttons.add(new JButton(Names[i]));
			Buttons.lastElement().setSize(100, 10);
			Buttons.lastElement().addKeyListener(new TextSwitcher(Buttons.lastElement()));
			jp.add(Buttons.lastElement());
		}
		
		JButton Speichern = new JButton("Speichern");
		Speichern.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				Vector<String> Keys = new Vector<String>();
				for(JButton Button: Buttons){
					Keys.add(Button.getText());
				}
				try {
					String w = (new Gson()).toJson(Keys, (new TypeToken<Vector<String>>(){}).getType());
					BufferedWriter bw = new BufferedWriter(new FileWriter("json/menu/menu.json"));
					bw.write(w);
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		jp.add(Speichern);

		this.getContentPane().add(jp);
		this.pack();
		this.setVisible(true);
	}

}
