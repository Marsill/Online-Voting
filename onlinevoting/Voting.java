package onlinevoting;

import java.sql.*;
import javax.swing.*;

public class Voting {
      private static final String DB_URL = "jdbc:mysql://localhost:3306/votingdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "jiminshi";
      
    public void voting() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");     
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connected");
            
            PreparedStatement stmt = conn.prepareStatement("UPDATE votercounting SET canadidateId = ?, input = ?, total = ? where voterId = ?");
            
        JFrame jf = new JFrame();    
        JPanel jp = new JPanel();
        
        jp.setLayout(null);
        
        JLabel idLabel = new JLabel("Enter the candidate id: ");
        idLabel.setBounds(30, 20, 150, 40);
        JTextField idTf = new JTextField();
        idTf.setBounds(180, 20, 150, 30);
        
        JLabel inputLabel = new JLabel("Enter 0(Not select) or 1(To select): ");
        inputLabel.setBounds(30, 60, 150, 40);
        JTextField inputTf = new JTextField();
        inputTf.setBounds(180, 60, 150, 30);
            
        JButton enterJb = new JButton("Enter");
        enterJb.setBounds(140, 190, 120, 40);
        
        jp.add(idLabel);
        jp.add(idTf);
        jp.add(inputLabel);
        jp.add(inputTf);
        jp.add(enterJb);
        
        jf.add(jp);
        jf.setSize(400, 300);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        enterJb.addActionListener(event ->{
            try{
                String id = idTf.getText();
                String input = inputTf.getText();
            
                stmt.setString(3, input);
                
                String total = "";
                total = total + input;
                
                if(id.equals(0)){
                    stmt.setString(2, "-");
                }
                else{
                    stmt.setString(2, id);
                    stmt.setString(4, total);
                }
                stmt.executeUpdate();
                stmt.close();
                JOptionPane.showMessageDialog(null, "You have entered successfully! ");
                jf.dispose();
            }catch(Exception ex){
                ex.printStackTrace();
            }
       
        });
         
            }catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }
            
        }
}
