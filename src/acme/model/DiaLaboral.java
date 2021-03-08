/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acme.model;
import java.util.List;

/**
 *
 * @author amca
 */
public class DiaLaboral {
    
    private String día;
    private List<Horario> horario;

    /**
     * @return the día
     */
    public String getDía() {
        return día;
    }

    /**
     * @param día the día to set
     */
    public void setDía(String día) {
        this.día = día;
    }

    /**
     * @return the horario
     */
    public List<Horario> getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }
    
    
}
