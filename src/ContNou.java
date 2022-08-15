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


public class ContNou {
	private JFrame frame;
	private JTextField txtCNP;
	private JButton btnCreeaza, btnInapoi;
	
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContNou window = new ContNou();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ContNou() {
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
		
		btnCreeaza = new JButton("Creeaza");
		btnCreeaza.setBackground(new Color(255, 255, 255));
		btnCreeaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CNP = txtCNP.getText();
				boolean b;
				b = verificare(CNP);
				if (b) {
					JLabel lblError = new JLabel("Contul exista deja");
					lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblError.setBounds(12, 200, 253, 35);
					frame.getContentPane().add(lblError);
					frame.repaint();
				}
				else {
					insert(CNP);
					  
					frame.getContentPane().removeAll();
					
					JLabel lblSucces = new JLabel("Cont creat cu succes");
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
			}
		});
		btnCreeaza.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreeaza.setBounds(12, 150, 110, 25);
		frame.getContentPane().add(btnCreeaza);
		
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
	public void insert(String CNP) {
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banca","root","");
			Statement stmt = con.createStatement(); 
			String sql = "insert into client values ('"+CNP+"', 0, 0, 0)";
			stmt.executeUpdate(sql);
			con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
}
