
package onlinevoting;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

public class CandidateInfoView {
    private Connection conn;
    public CandidateInfoView(Connection conn){
        this.conn = conn;
    }
    public void viewCandidateInfo(){
        JFrame jf = new JFrame();    
        JPanel jp = new JPanel();
        
        JLabel idLabel = new JLabel("Enter the candidate id: ");
        JTextField idTf = new JTextField();
        idTf.setBounds(20, 30, 40, 50);
        
        JButton jb = new JButton("Display");
        jb.setBounds(20, 30, 40, 50);
        
        jp.add(idLabel);
        jp.add(idTf);
        jf.add(jp);
        
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM canadidateinfo WHERE canadidateId = ?");
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                String candidateId = result.getString("candidateId");
            }
            Voting vote = new Voting();
            vote.voting();
            jf.dispose();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
