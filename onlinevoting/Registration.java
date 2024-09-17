package onlinevoting;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

public class Registration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/votingdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "jiminshi";
      
    public void addRegistered() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");     
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connected");
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(voterId, username, email) VALUES(?, ?, ?)");

            JFrame jf = new JFrame();
            jf.setSize(400, 200);
            JPanel jp = new JPanel();
            jp.setLayout(null); 
            jf.add(jp);

            JLabel voterIdLabel = new JLabel("Enter the voter id: ");
            voterIdLabel.setBounds(30, 100, 150, 20);
            JTextField idTf = new JTextField();
            idTf.setBounds(180, 100, 150, 20);

            JLabel nameLabel = new JLabel("Enter the voter name: ");
            nameLabel.setBounds(30, 40, 150, 20);
            JTextField nameTf = new JTextField();
            nameTf.setBounds(180, 40, 150, 20);

            JLabel emailLabel = new JLabel("Enter the voter email: ");
            emailLabel.setBounds(30, 70, 150, 20);
            JTextField emailTf = new JTextField();
            emailTf.setBounds(180, 70, 150, 20);

            JButton nextJb = new JButton("Next");
            nextJb.setBounds(120, 120, 90, 30);
            
            jp.add(nameLabel);
            jp.add(nameTf);
            jp.add(emailLabel);
            jp.add(emailTf);
            jp.add(voterIdLabel);
            jp.add(idTf);
            jp.add(nextJb);
            
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setVisible(true);
            
            nextJb.addActionListener(event ->{
                try{
                    String voterId = idTf.getText();
                    String username = nameTf.getText();
                    String email = emailTf.getText();
                    
                    stmt.setString(1, voterId);
                    stmt.setString(2, username);
                    stmt.setString(3, email);
                    
                    stmt.executeUpdate();
                    stmt.close();
                    JOptionPane.showMessageDialog( null ,"User Information added successfully");
                    
                    CandidateInfoView civ = new CandidateInfoView();
                    civ.viewCandidateInfo();
                    jf.dispose();
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            );
       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error when connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
  
}
    

