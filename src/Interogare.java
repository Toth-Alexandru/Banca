import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Interogare {
	private JFrame frame;
	private JTextField txtCNP;
	private JButton btnInterogare, btnInapoi;
	
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interogare window = new Interogare();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interogare() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(243,241,170));
		frame.setBounds(500, 200, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCNP = new JLabel("CNP");
		lblCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCNP.setBounds(12, 50, 253, 35);
		frame.getContentPane().add(lblCNP);
		
		txtCNP = new JTextField();
		txtCNP.setBackground(new Color(255, 255, 255));
		txtCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCNP.setBounds(12, 100, 253, 35);
		frame.getContentPane().add(txtCNP);
		txtCNP.setColumns(10);
		
		btnInterogare = new JButton("Vezi Sold");
		btnInterogare.setBackground(new Color(255, 255, 255));
		btnInterogare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CNP = txtCNP.getText();
				boolean b;
				int[] v;
				b = verificare(CNP);
				if (!b) {
					JLabel lblError = new JLabel("Contul nu exista");
					lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblError.setBounds(12, 200, 253, 35);
					frame.getContentPane().add(lblError);
					frame.repaint();
				}
				else {
					v = interogare(CNP);
					  
					frame.getContentPane().removeAll();
					
					JLabel lblSold = new JLabel("Sold: RON = "+v[0]+", EUR = "+v[1]);
					lblSold.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblSold.setBounds(12, 50, 500, 35);
					frame.getContentPane().add(lblSold);
					
					btnInapoi = new JButton("OK");
					btnInapoi.setBackground(new Color(255, 255, 255));
					btnInapoi.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Client client = new Client();
							client.NewScreen();
							frame.dispose();
						}
					});
					btnInapoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
					btnInapoi.setBounds(12, 100, 253, 35);
					frame.getContentPane().add(btnInapoi);
					
					frame.repaint();
				}
			}
		});
		btnInterogare.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInterogare.setBounds(12, 150, 130, 25);
		frame.getContentPane().add(btnInterogare);
		
		btnInapoi = new JButton("Inapoi");
		btnInapoi.setBackground(new Color(255, 255, 255));
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client client = new Client();
				client.NewScreen();
				frame.dispose();
			}
		});
		btnInapoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInapoi.setBounds(150, 150, 97, 25);
		frame.getContentPane().add(btnInapoi);
	}
	public boolean verificare(String CNP) {
		boolean b = false;
		try {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banca","root","");
		Statement stmt = con.createStatement(); 
		ResultSet rs=stmt.executeQuery("select * from client where cnp='"+CNP+"'");
		if (rs.next())
			b = true;
		else
			b = false;
		con.close();
		return b;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return b;
	}
	public int[] interogare(String CNP) {
		int[] v = {0,0};
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banca","root","");
			Statement stmt = con.createStatement(); 
			ResultSet rs=stmt.executeQuery("select * from client where cnp='"+CNP+"'");
			rs.next();
			v[0] = rs.getInt(2);
			v[1] =  rs.getInt(3);
			con.close();
			return v;
			} catch (Exception e) {
				// TODO: handle exception
			}
		return v;
	}
}
