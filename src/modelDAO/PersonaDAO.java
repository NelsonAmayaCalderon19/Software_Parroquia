/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

//import servicios.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelVO.*;
import conexion.Conexion;
/**
 *
 * @author NELSON
 */
public class PersonaDAO {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    private String sSQL2="";
    public Integer totalregistros;
    
    
    public boolean insertar(PersonaVO dts){
    
        sSQL ="INSERT INTO FELIGRES (Cedula,Nombre,Direccion,Telefono,Estrato,Estado)"+ 
                "VALUES (?,?,?,?,?,?)";
        
      try{ 
          PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getCedula());
          pst.setString(2, dts.getNombre());
          pst.setString(3, dts.getDireccion());
          pst.setString(4, dts.getTelefono());
          pst.setString(5, dts.getEstrato());
          pst.setString(6, dts.getEstado());
          
          
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
}
    
    public boolean actualizar(PersonaVO dts){
    sSQL ="UPDATE FELIGRES SET Nombre=?,Direccion=?,Telefono=?,Estrato=?,Estado=?" +
            " WHERE Cedula=?";
    
    
    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getNombre());
          pst.setString(2, dts.getDireccion());
          pst.setString(3, dts.getTelefono());
          pst.setString(4, dts.getEstrato());
          pst.setString(5, dts.getEstado());
          pst.setString(6, dts.getCedula());
          
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
}
    public boolean eliminar(PersonaVO dts){
    sSQL ="DELETE FROM FELIGRES WHERE Cedula=?";
    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);
          
          pst.setString(1, dts.getCedula());
          
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
}
    public boolean buscar(PersonaVO dts,String buscar){
        int cont=0;
    sSQL = "SELECT * FROM FELIGRES WHERE Cedula LIKE "+ buscar +" ";
    try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(sSQL);
        
        while(rs.next()){
            dts.setNombre(rs.getString("Nombre"));
            dts.setDireccion(rs.getString("Direccion"));
            dts.setTelefono(rs.getString("Telefono"));
            dts.setEstrato(rs.getString("Estrato"));
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
    
    public boolean editar(PersonaVO dts){
    sSQL ="UPDATE feligres SET Estado=?";
    
    
    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getEstado());
         
          
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
}
    
     public boolean actualizarEstado(PersonaVO dts){
    String sSQL ="UPDATE FELIGRES SET Estado=?" +
            " WHERE Cedula=?";

    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getEstado());
          pst.setString(2, dts.getCedula());
          
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
}
}

