import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Client {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Client() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(243,241,170));
		frame.setBounds(500, 200, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnContNou = new JButton("Cont nou");
		btnContNou.setFocusable(false);
		btnContNou.setForeground(new Color(0, 0, 0));
		btnContNou.setBackground(new Color(255, 255, 255));
		btnContNou.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnContNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContNou contNou = new ContNou();
				contNou.NewScreen();
				frame.dispose();
			}
		});
		btnContNou.setBounds(200, 30, 180, 50);
		frame.getContentPane().add(btnContNou);
		
		JButton btnInterogare = new JButton("Interogare");
		btnInterogare.setFocusable(false);
		btnInterogare.setForeground(new Color(0, 0, 0));
		btnInterogare.setBackground(new Color(255, 255, 255));
		btnInterogare.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnInterogare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Interogare interogare = new Interogare();
				interogare.NewScreen();
				frame.dispose();
			}
		});
		btnInterogare.setBounds(200, 130, 180, 50);
		frame.getContentPane().add(btnInterogare);
		
		JButton btnDepunere = new JButton("Depunere");
		btnDepunere.setFocusable(false);
		btnDepunere.setForeground(new Color(0, 0, 0));
		btnDepunere.setBackground(new Color(255, 255, 255));
		btnDepunere.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnDepunere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Depunere depunere = new Depunere();
				depunere.NewScreen();
				frame.dispose();
			}
		});
		btnDepunere.setBounds(200, 230, 180, 50);
		frame.getContentPane().add(btnDepunere);
		
		JButton btnRetragere = new JButton("Retragere");
		btnRetragere.setFocusable(false);
		btnRetragere.setForeground(new Color(0, 0, 0));
		btnRetragere.setBackground(new Color(255, 255, 255));
		btnRetragere.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnRetragere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Retragere retragere = new Retragere();
				retragere.NewScreen();
				frame.dispose();
			}
		});
		btnRetragere.setBounds(200, 330, 180, 50);
		frame.getContentPane().add(btnRetragere);
		
		JButton btnLichidare = new JButton("Lichidare");
		btnLichidare.setFocusable(false);
		btnLichidare.setForeground(new Color(0, 0, 0));
		btnLichidare.setBackground(new Color(255, 255, 255));
		btnLichidare.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLichidare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Lichidare lichidare = new Lichidare();
				lichidare.NewScreen();
				frame.dispose();
			}
		});
		btnLichidare.setBounds(200, 430, 180, 50);
		frame.getContentPane().add(btnLichidare);
	}
}
