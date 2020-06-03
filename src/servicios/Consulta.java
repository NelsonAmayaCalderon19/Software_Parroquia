/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Connection;
import modelDAO.PersonaDAO;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelVO.PersonaVO;
/**
 *
 * @author NELSON
 */
public class Consulta {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    int cont=0;
    public boolean calcularDiezmo(PersonaVO dts, String buscar){
//    Integer diezmo=0;
    String sSQL = "SELECT * FROM FELIGRES WHERE Cedula LIKE "+ buscar +" ";
    try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(sSQL);
        
        while(rs.next()){
            dts.setEstado(rs.getString("Estado"));
            cont++;
        }
           
          if(cont!=0){
              cont=0;
              return true;              
          }else{
              cont=0;
              return false;           
          }
        
        
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);
      cont=0;
       return false;      
    }
    }
}
    
   
