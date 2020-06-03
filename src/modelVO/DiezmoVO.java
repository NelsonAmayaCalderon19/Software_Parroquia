/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelVO;

/**
 *
 * @author NELSON
 */
public class DiezmoVO {
    private String cedula;
    private Integer cantidad;

    public DiezmoVO() {
    }

    public DiezmoVO(String cedula,Integer cantidad) {
        this.cedula = cedula;
        this.cantidad = cantidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
 
    
}
