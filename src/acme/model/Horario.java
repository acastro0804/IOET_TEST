/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acme.model;
/**
 *
 * @author amca
 */
public class Horario {
    
    private String horaDesde;
    private String horaHasta;
    private double valorHora;
    private String valorMoneda;

    /**
     * @return the horaDesde
     */
    public String getHoraDesde() {
        return horaDesde;
    }

    /**
     * @param horaDesde the horaDesde to set
     */
    public void setHoraDesde(String horaDesde) {
        this.horaDesde = horaDesde;
    }

    /**
     * @return the horaHasta
     */
    public String getHoraHasta() {
        return horaHasta;
    }

    /**
     * @param horaHasta the horaHasta to set
     */
    public void setHoraHasta(String horaHasta) {
        this.horaHasta = horaHasta;
    }

    /**
     * @return the valorHora
     */
    public double getValorHora() {
        return valorHora;
    }

    /**
     * @param valorHora the valorHora to set
     */
    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    /**
     * @return the valorMoneda
     */
    public String getValorMoneda() {
        return valorMoneda;
    }

    /**
     * @param valorMoneda the valorMoneda to set
     */
    public void setValorMoneda(String valorMoneda) {
        this.valorMoneda = valorMoneda;
    }
    
    
}
