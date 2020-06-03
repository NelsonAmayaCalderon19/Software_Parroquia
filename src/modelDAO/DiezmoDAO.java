/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelVO.DiezmoVO;



/**
 *
 * @author NELSON
 */
public class DiezmoDAO {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    private String sSQL2="";
    public boolean registrar(DiezmoVO dts){
    
        sSQL ="INSERT INTO DIEZMO (Cedula,Cantidad)"+ 
                "VALUES (?,?)";
        
      try{ 
          PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getCedula());
          pst.setInt(2, dts.getCantidad());                    
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
      JOptionPane.showMessageDialog(null,"Ojo Este Feligres, ya Pagó su diezmo");
        return false;
    }
}

}
