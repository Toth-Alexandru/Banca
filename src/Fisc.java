import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Fisc extends Thread{

	private static JFrame frame;
	private static ArrayList<String> v = new ArrayList<>();
	private JTextField txtCNP;

	public static void main(String[] args) {
		Fisc t1 = new Fisc() {
			public void run() {
				while (true) {
					try {
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banca","root","");
						Statement stmt = con.createStatement(); 
						ResultSet rs;
						for(int i = 0; i < v.size() ; i++) {
						TimeUnit.SECONDS.sleep(1);
						rs=stmt.executeQuery("select * from client where cnp='"+v.get(i)+"' and verificat>0");
						if(rs.next())
						{
							if (rs.getInt(4)==1) {
								JOptionPane.showMessageDialog(null, "Contul in RON al clientului "+rs.getString(1)+" a fost modificat", "Cont Modificat", JOptionPane.INFORMATION_MESSAGE);
								String sql = "update client set verificat=0 where cnp ='"+rs.getString(1)+"'";
								stmt.executeUpdate(sql);
							}
							else {
								JOptionPane.showMessageDialog(null, "Contul in EUR al clientului "+rs.getString(1)+" a fost modificat", "Cont Modificat", JOptionPane.INFORMATION_MESSAGE);
								String sql = "update client set verificat=0 where cnp ='"+rs.getString(1)+"'";
								stmt.executeUpdate(sql);
							}
						
						}
						}
						TimeUnit.SECONDS.sleep(3);
						for(int i = 0; i < v.size() ; i++)
						System.out.println(v.get(i));
						con.close();
					} catch (InterruptedException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t1.setDaemon(true);
	    t1.start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fisc window = new Fisc();
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
					Fisc window = new Fisc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Fisc() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(243,241,170));
		frame.setBounds(500, 200, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnMonitorizeaza = new JButton("Monitorizeaza un client nou");
		btnMonitorizeaza.setFocusable(false);
		btnMonitorizeaza.setForeground(new Color(0, 0, 0));
		btnMonitorizeaza.setBackground(new Color(255, 255, 255));
		btnMonitorizeaza.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnMonitorizeaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
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
				
				JButton btnAdd = new JButton("Adauga");
				btnAdd.setBackground(new Color(255, 255, 255));
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!v.contains(txtCNP.getText()) && verificare(txtCNP.getText())){
						v.add(txtCNP.getText());
						try {
							String sql;
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banca","root","");
							Statement stmt = con.createStatement();
							sql = "update client set verificat=0 where cnp ='"+txtCNP.getText()+"'";
							stmt.executeUpdate(sql);
							con.close();
							} catch (Exception e1) {
								// TODO: handle exception
							}
						frame.getContentPane().removeAll();
						
						JLabel lblSucces = new JLabel("Cont adaugat");
						lblSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
						lblSucces.setBounds(12, 50, 253, 35);
						frame.getContentPane().add(lblSucces);
						
						JButton btnInapoi = new JButton("OK");
						btnInapoi.setBackground(new Color(255, 255, 255));
						btnInapoi.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame.dispose();
								Fisc fisc = new Fisc();
								fisc.NewScreen();
							}
						});
						btnInapoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
						btnInapoi.setBounds(12, 100, 253, 35);
						frame.getContentPane().add(btnInapoi);
						
						frame.repaint();
						}
						else {
							JLabel lblError = new JLabel("Contul este adaugat deja sau nu exista");
							lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							lblError.setBounds(12, 200, 500, 35);
							frame.getContentPane().add(lblError);
							frame.repaint();
						}
					}
				});
				btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnAdd.setBounds(12, 150, 110, 25);
				frame.getContentPane().add(btnAdd);
				
				JButton btnInapoi = new JButton("Inapoi");
				btnInapoi.setBackground(new Color(255, 255, 255));
				btnInapoi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						Fisc fisc = new Fisc();
						fisc.NewScreen();
					}
				});
				btnInapoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnInapoi.setBounds(150, 150, 97, 25);
				frame.getContentPane().add(btnInapoi);
				
				frame.repaint();
			}
		});
		btnMonitorizeaza.setBounds(40, 30, 500, 50);
		frame.getContentPane().add(btnMonitorizeaza);
		
		JButton btnIncetare = new JButton("Inceteaza monitorizarea unui client");
		btnIncetare.setFocusable(false);
		btnIncetare.setForeground(new Color(0, 0, 0));
		btnIncetare.setBackground(new Color(255, 255, 255));
		btnIncetare.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnIncetare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
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
				
				JButton btnAdd = new JButton("Sterge");
				btnAdd.setBackground(new Color(255, 255, 255));
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(v.contains(txtCNP.getText())){
						v.remove(txtCNP.getText());
						frame.getContentPane().removeAll();
						
						JLabel lblSucces = new JLabel("Cont sters");
						lblSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
						lblSucces.setBounds(12, 50, 253, 35);
						frame.getContentPane().add(lblSucces);
						
						JButton btnInapoi = new JButton("OK");
						btnInapoi.setBackground(new Color(255, 255, 255));
						btnInapoi.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame.dispose();
								Fisc fisc = new Fisc();
								fisc.NewScreen();
							}
						});
						btnInapoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
						btnInapoi.setBounds(12, 100, 253, 35);
						frame.getContentPane().add(btnInapoi);
						
						frame.repaint();
						}
						else {
							JLabel lblError = new JLabel("Contul nu este in lista");
							lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							lblError.setBounds(12, 200, 500, 35);
							frame.getContentPane().add(lblError);
							frame.repaint();
						}
					}
				});
				btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnAdd.setBounds(12, 150, 110, 25);
				frame.getContentPane().add(btnAdd);
				
				JButton btnInapoi = new JButton("Inapoi");
				btnInapoi.setBackground(new Color(255, 255, 255));
				btnInapoi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						Fisc fisc = new Fisc();
						fisc.NewScreen();
					}
				});
				btnInapoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnInapoi.setBounds(150, 150, 97, 25);
				frame.getContentPane().add(btnInapoi);
				
				frame.repaint();
			}
		});
		btnIncetare.setBounds(40, 130, 500, 50);
		frame.getContentPane().add(btnIncetare);
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
}
