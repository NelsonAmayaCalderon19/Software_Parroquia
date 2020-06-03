/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import conexion.Conexion;
import vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import modelVO.DiezmoVO;
import modelVO.PersonaVO;
import modelDAO.DiezmoDAO;
import modelDAO.PersonaDAO;
import servicios.Consulta;

/**
 *
 * @author NELSON
 */
public class Controlador implements ActionListener {
private Vista vista=null;
         private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    private String sSQL2="";
    Consulta consulta;
    public Controlador(Vista vista){
    super();
    this.vista=vista;
    actionListener(this);
    vista.t8.setEnabled(false);
        vista.jt1.setEditable(false);
        vista.btnActualizar.setEnabled(false);
        vista.btnEliminar.setEnabled(false);
        vista.btnPagar.setEnabled(false);
    }
    private void actionListener(ActionListener controlador){
    vista.btnGuardar.addActionListener(controlador);
    vista.btnBuscar.addActionListener(controlador);
    vista.btnActualizar.addActionListener(controlador);
    vista.btnLimpiar.addActionListener(controlador);
    vista.btnEliminar.addActionListener(controlador);
    vista.btnConsultar.addActionListener(controlador);
    vista.btnPagar.addActionListener(controlador);
    vista.btnTotalizar.addActionListener(controlador);
    vista.jButton1.addActionListener(controlador);
    

    }
    void limpiar(){
vista.t1.setText("");
        vista.t2.setText("");
        vista.t3.setText("");
        vista.t4.setText("");
        vista.t5.setText("");
        vista.t6.setText("");
        vista.t1.requestFocus();
}
void limpiar2(){
    vista.t7.requestFocus();
vista.t7.setText("");
vista.t8.setText("");
vista.t8.setEnabled(false);
vista.jt1.setText("");
}
    @Override
    public void actionPerformed(ActionEvent e) {
     int cont=0;
        try{
        if(e.getSource()==vista.btnGuardar){
        if (vista.t1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la Cedula del Feligrés");
            vista.t1.requestFocus();
            return;
        }
        if (vista.t2.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Nombre del Feligrés");
            vista.t2.requestFocus();
            return;
        }

        if (vista.t3.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la Dirección del Feligrés");
            vista.t3.requestFocus();
            return;
        }

        if (vista.t4.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Telefono del Feligrés");
            vista.t4.requestFocus();
            return;
        }
        if (vista.t5.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Estrato del Feligrés");
            vista.t5.requestFocus();
            return;
        }else{
        PersonaVO dts = new PersonaVO();
        PersonaDAO func = new PersonaDAO();
        if(func.buscar(dts,vista.t1.getText())==true){            
        JOptionPane.showMessageDialog(null, "Ojo, Este Feligres, ya Esta Registrado");
        cont++;
        limpiar();
        }
        }if(cont!=1){
            cont=0;
        vista.btnActualizar.setEnabled(false);
        vista.btnEliminar.setEnabled(false);
        PersonaVO dts = new PersonaVO();
        PersonaDAO func = new PersonaDAO();
        dts.setCedula(vista.t1.getText());
        dts.setNombre(vista.t2.getText());
        dts.setDireccion(vista.t3.getText());
        dts.setTelefono(vista.t4.getText());
        dts.setEstrato(vista.t5.getText());
        dts.setEstado("Deudor");               
        if (func.insertar(dts)) {
                JOptionPane.showMessageDialog(null, "El Feligrés fue registrado satisfactoriamente");
                limpiar();                
            }
        }
        }if(e.getSource()==vista.btnBuscar){
        try {
if (vista.t1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la Cedula del Feligrés");
            vista.t1.requestFocus();
            return;
        }
PersonaVO dts = new PersonaVO();
            PersonaDAO func = new PersonaDAO();
dts.setCedula(vista.t1.getText());
if (func.buscar(dts,vista.t1.getText())) {
                JOptionPane.showMessageDialog(null, "El Feligrés fue Encontrado satisfactoriamente");
                vista.t2.setText(dts.getNombre());
                vista.t3.setText(dts.getDireccion());
                vista.t4.setText(dts.getTelefono());
                vista.t5.setText(dts.getEstrato());
                vista.t6.setText(dts.getEstado());
                vista.btnActualizar.setEnabled(true);
        vista.btnEliminar.setEnabled(true);
        vista.btnGuardar.setEnabled(false);
        vista.btnBuscar.setEnabled(false);
            }else{
JOptionPane.showMessageDialog(null, "El Feligrés No fue Encontrado ");
limpiar();
}
        } catch (Exception ev) {
            JOptionPane.showConfirmDialog(null, ev.getMessage());
        }
        }if(e.getSource()==vista.btnEliminar){
             if (vista.t1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la Cedula del Feligrés");
            vista.t1.requestFocus();
            return;
        }
        if (!vista.t1.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de Eliminar el Feligrés?","Confirmar",2);            
            if (confirmacion==0) {
                PersonaVO dts = new PersonaVO();
            PersonaDAO func = new PersonaDAO();   
                dts.setCedula(vista.t1.getText());
                func.eliminar(dts);
                JOptionPane.showMessageDialog(null, "Feligrés Eliminado Exitosamente");
                limpiar();
                vista.btnActualizar.setEnabled(false);
                vista.btnEliminar.setEnabled(false);
                vista.btnGuardar.setEnabled(true);
                vista.btnBuscar.setEnabled(true);                
            }   
        }
        }if(e.getSource()==vista.btnActualizar){
        if (vista.t1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la Cedula del Feligrés");
            vista.t1.requestFocus();
            return;
        }
        if (vista.t2.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Nombre del Feligrés");
            vista.t2.requestFocus();
            return;
        }

        if (vista.t3.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la Dirección del Feligrés");
            vista.t3.requestFocus();
            return;
        }

        if (vista.t4.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Telefono del Feligrés");
            vista.t4.requestFocus();
            return;
        }
        if (vista.t5.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Estrato del Feligrés");
            vista.t5.requestFocus();
            return;
        }
        PersonaVO dts = new PersonaVO();
            PersonaDAO func = new PersonaDAO();
        dts.setNombre(vista.t2.getText());
        dts.setDireccion(vista.t3.getText());
        dts.setTelefono(vista.t4.getText());
        dts.setEstrato(vista.t5.getText());
        dts.setEstado(vista.t6.getText());
            dts.setCedula((vista.t1.getText()));                        
            if (func.actualizar(dts)) {
                JOptionPane.showMessageDialog(null, "El Feligrés fue Actualizado Satisfactoriamente");
                limpiar();
                vista.btnActualizar.setEnabled(false);
                vista.btnEliminar.setEnabled(false);
                vista.btnGuardar.setEnabled(true);
                vista.btnBuscar.setEnabled(true);
            }        
        }if(e.getSource()==vista.btnConsultar){
             try {
if (vista.t7.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la Cedula del Feligrés");
            vista.t7.requestFocus();
            return;
        }
PersonaVO dts = new PersonaVO();
            PersonaDAO func = new PersonaDAO();
dts.setCedula(vista.t7.getText());
if (func.buscar(dts,vista.t7.getText())) {
                JOptionPane.showMessageDialog(null, "El Feligrés fue Encontrado satisfactoriamente");
if(dts.getEstrato().equals("1")){
 vista.t8.setText("250000");
}else if(dts.getEstrato().equals("2")){
 vista.t8.setText("500000");
}else{
vista.t8.setText("1000000");
}
                vista.btnPagar.setEnabled(true);
        vista.btnTotalizar.setEnabled(true);
        vista.btnConsultar.setEnabled(false);
                vista.btnActualizar.setEnabled(false);
            }else{
JOptionPane.showMessageDialog(null, "El Feligrés No fue Encontrado en la BD ");
limpiar2();
vista.btnPagar.setEnabled(false);
        vista.btnTotalizar.setEnabled(true);
        vista.btnConsultar.setEnabled(true);
                vista.btnActualizar.setEnabled(false);
                vista.t7.setText("");
}
        } catch (Exception ev) {
            JOptionPane.showConfirmDialog(null, ev.getMessage());
        }
        }if(e.getSource()==vista.btnLimpiar){
        limpiar();
                vista.btnActualizar.setEnabled(false);
                vista.btnEliminar.setEnabled(false);
                vista.btnGuardar.setEnabled(true);
                vista.btnBuscar.setEnabled(true);
        }if(e.getSource()==vista.btnPagar){
            DiezmoVO dt = new DiezmoVO();
            DiezmoDAO fun = new DiezmoDAO();
            
        dt.setCedula(vista.t7.getText());
        if(vista.t8.getText().equals("")){
        dt.setCantidad(0);
        }else{
        dt.setCantidad(Integer.parseInt(vista.t8.getText()));
        }
        if (fun.registrar(dt)==true) {
                JOptionPane.showMessageDialog(null, "El Diezmo del Feligrés fue Registrado Exitosamente");               
                        PersonaVO dts = new PersonaVO();
            PersonaDAO func = new PersonaDAO();
dts.setCedula(vista.t7.getText());
if (func.buscar(dts,vista.t7.getText())) {
    dts.setEstado("Cumplido");
            dts.setCedula((vista.t7.getText()));
             if (func.actualizarEstado(dts)) {
                JOptionPane.showMessageDialog(null, "El Estado del Feligrés fue Actualizado Exitosamente");
            }       
}
            }
        limpiar2();
        vista.btnPagar.setEnabled(false);
        vista.btnTotalizar.setEnabled(true);
        vista.btnConsultar.setEnabled(true);
        }if(e.getSource()==vista.btnTotalizar){
        sSQL = "select sum(Cantidad) as total, Cedula from diezmo";
     try{
     Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(sSQL);
        while(rs.next()){
vista.jt1.setText("El Total de Dinero en Diezmos que ha recibido la Iglesia es: $"+rs.getString("total"));       
        }           
          vista.btnPagar.setEnabled(false);
        vista.btnTotalizar.setEnabled(true);
        vista.btnConsultar.setEnabled(true);
     }catch(Exception ev){
        JOptionPane.showConfirmDialog(null, ev.getMessage());
    }
        }if(e.getSource()==vista.jButton1){
        limpiar2();
       vista.btnPagar.setEnabled(false);
        vista.btnTotalizar.setEnabled(true);
        vista.btnConsultar.setEnabled(true);
        }
        }catch(Exception ev){
        ev.getMessage();
        }
    }
    
}
