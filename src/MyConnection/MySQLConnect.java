package MyConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MySQLConnect {
    String user = "root";
    String password="";
    String url="jdbc:mysql://localhost:3306/chatproject?useLegacyDatetimeCode=false&serverTimezone=UTC";
    public Connection conn = null;
    public Statement st = null;
    public ResultSet rs = null;
    public MySQLConnect(){
        if(conn==null) {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
            }
            catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,e.toString());
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,e.toString());
            }
        }
    }
    public  void  MySQLDisconnect(){
        try{
            if(rs!=null)rs.close();
            if(st!=null)st.close();
            if(conn!=null)conn.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
}