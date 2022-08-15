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


public class Depunere {
	private JFrame frame;
	private JTextField txtCNP, txtSuma;
	private JButton btnDepuneRON, btnDepuneEUR, btnInapoi;
	
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Depunere window = new Depunere();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Depunere() {
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
		
		JLabel lblSuma = new JLabel("Suma");
		lblSuma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSuma.setBounds(12, 150, 253, 35);
		frame.getContentPane().add(lblSuma);
		
		txtSuma = new JTextField();
		txtSuma.setBackground(new Color(255, 255, 255));
		txtSuma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSuma.setBounds(12, 200, 253, 35);
		frame.getContentPane().add(txtSuma);
		txtSuma.setColumns(10);
		
		btnDepuneRON = new JButton("Depune RON");
		btnDepuneRON.setBackground(new Color(255, 255, 255));
		btnDepuneRON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CNP = txtCNP.getText();
				String suma = txtSuma.getText();
				boolean b;
				b = verificare(CNP);
				if (b==false) {
					JLabel lblError = new JLabel("Contul nu exista");
					lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblError.setBounds(12, 500, 253, 35);
					frame.getContentPane().add(lblError);
					frame.repaint();
				}
				else {
					int[] v = interogare(CNP);
					if(v[0]+Integer.valueOf(suma)>=1000)
					{
					depunere(CNP, Integer.valueOf(suma), false);
					  
					frame.getContentPane().removeAll();
					
					JLabel lblSucces = new JLabel("Suma depusa cu succes");
					lblSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblSucces.setBounds(12, 50, 253, 35);
					frame.getContentPane().add(lblSucces);
					
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
					else {
						JLabel lblError = new JLabel("Soldul nu poate fi mai mic de 1000 RON");
						lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						lblError.setBounds(12, 500, 500, 35);
						frame.getContentPane().add(lblError);
						frame.repaint();
					}
				}
			}
		});
		btnDepuneRON.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDepuneRON.setBounds(12, 300, 150, 25);
		frame.getContentPane().add(btnDepuneRON);
		
		btnDepuneEUR = new JButton("Depune EUR");
		btnDepuneEUR.setBackground(new Color(255, 255, 255));
		btnDepuneEUR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CNP = txtCNP.getText();
				String suma = txtSuma.getText();
				boolean b;
				b = verificare(CNP);
				if (b==false) {
					JLabel lblError = new JLabel("Contul nu exista");
					lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblError.setBounds(12, 500, 253, 35);
					frame.getContentPane().add(lblError);
					frame.repaint();
				}
				else {
					int[] v = interogare(CNP);
					if(v[1]+Integer.valueOf(suma)>=1000)
					{
					depunere(CNP, Integer.valueOf(suma), true);
					  
					frame.getContentPane().removeAll();
					
					JLabel lblSucces = new JLabel("Suma depusa cu succes");
					lblSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblSucces.setBounds(12, 50, 253, 35);
					frame.getContentPane().add(lblSucces);
					
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
					else {
						JLabel lblError = new JLabel("Soldul nu poate fi mai mic de 1000 EUR");
						lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						lblError.setBounds(12, 500, 500, 35);
						frame.getContentPane().add(lblError);
						frame.repaint();
					}
				}
			}
		});
		btnDepuneEUR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDepuneEUR.setBounds(200, 300, 150, 25);
		frame.getContentPane().add(btnDepuneEUR);
		
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
		btnInapoi.setBounds(400, 300, 97, 25);
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
	public void depunere(String CNP, int suma, boolean k) {
		try {
			String sql;
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banca","root","");
			Statement stmt = con.createStatement();
			if (k) {
				sql = "update client set euro = euro +"+suma+", verificat=2 where cnp ='"+CNP+"'";
			}
			else {
				sql = "update client set ron = ron +"+suma+", verificat=1 where cnp ='"+CNP+"'";
			}
			stmt.executeUpdate(sql);
			con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
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
