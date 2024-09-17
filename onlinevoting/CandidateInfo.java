package onlinevoting;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

public class CandidateInfo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/votingdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "jiminshi";
    private Connection conn;
    JFrame jf = new JFrame();    
    JPanel jp = new JPanel();
    
    public CandidateInfo(Connection conn){
        this.conn = conn;
    }
  
    public CandidateInfo(){
        
        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setBounds(30, 20, 150, 40);
        JTextField nameField = new JTextField();
        nameField.setBounds(30, 20, 150, 40);
        
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 20, 150, 40);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(30, 20, 150, 40);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 190, 120, 40);
        
        jf.add(nameLabel);
        jf.add(nameField);
        jf.add(passLabel);
        jf.add(passField);
        jf.add(loginButton);
        
        loginButton.addActionListener(event->{
            String username = nameField.getText();
            String password = new String(passField.getPassword());
            
            if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1233")){
                candidateEntry();
            }
            else{
                JOptionPane.showMessageDialog(null, "Access Denied!");
            }    
        });
    }
        public void candidateEntry(){
        
         try{
             conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO candidateinfo(candidateId, candidatePhoto, candidateQualification, candidateBackground) VALUES(?, ?, ?, ?)");
        
        
        jp.setLayout(null);
        
        JLabel idLabel = new JLabel("Enter the canadidate id: ");
        idLabel.setBounds(30, 20, 150, 40);
        JTextField idTf = new JTextField();
        idTf.setBounds(180, 20, 150, 30);
        
        JLabel photoLabel = new JLabel("Enter the canadidate photo: ");
        photoLabel.setBounds(30, 60, 150, 40);
        JTextField photoTf = new JTextField();
        photoTf.setBounds(180, 60, 150, 30);
        
        JLabel qualifyLabel = new JLabel("Enter the canadidate qualification: ");
        qualifyLabel.setBounds(30, 100, 150, 40);
        JTextField qualifyTf = new JTextField();
        qualifyTf.setBounds(180, 100, 150, 30);
        
        JLabel backgroundLabel = new JLabel("Enter the canadidate background: ");
        backgroundLabel.setBounds(30, 140, 150, 40);
        JTextField backgroundTf = new JTextField();
        backgroundTf.setBounds(180, 140, 150, 30);
        
        JButton jb = new JButton("Enter");
        jb.setBounds(140, 190, 120, 40);
        
        jp.add(idLabel);
        jp.add(idTf);
        jp.add(photoLabel);
        jp.add(photoTf);
        jp.add(qualifyLabel);
        jp.add(qualifyTf);
        jp.add(backgroundLabel);
        jp.add(backgroundTf);
        jp.add(jb);
        
        jf.add(jp);
        jf.setSize(400, 300);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jb.addActionListener(event->{
            try{
                String partyId = idTf.getText();
                String partyPhoto = photoTf.getText();
                String partyQualification = qualifyTf.getText();
                String partyBackground = backgroundTf.getText();
                
                stmt.setString(1, partyId);
                stmt.setString(2, partyPhoto);
                stmt.setString(3, partyQualification);
                stmt.setString(4, partyBackground);
                
                stmt.executeUpdate();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Party information entered successfully");
                jf.dispose();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        });
           
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}