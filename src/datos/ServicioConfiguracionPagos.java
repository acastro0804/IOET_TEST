/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import acme.model.DiaLaboral;
import acme.model.Horario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

/**
 *
 * @author amca
 */
public class ServicioConfiguracionPagos {
                
    public DiaLaboral monday = new DiaLaboral();
    public DiaLaboral tuesday = new DiaLaboral();
    public DiaLaboral wednesday = new DiaLaboral();
    public DiaLaboral thursday = new DiaLaboral();
    public DiaLaboral friday = new DiaLaboral();
    public DiaLaboral saturday = new DiaLaboral();
    public DiaLaboral sunday = new DiaLaboral();
    public String pathFileEmployee;
    
    public ServicioConfiguracionPagos(String pathFileEmployee){
        this.pathFileEmployee = pathFileEmployee;
    }

    public double getPayForWorked(String dia, String horarioTrabajado){
        //MO 10:00-12:00
        Horario schedule;
        String startTimeWorked;
        String endTimeWorked;
        int hoursCalculated = 0;
        double totalPay = 0.0;
        DiaLaboral tmpDayWeek = new DiaLaboral();
              
        String[] horasEmpleado = horarioTrabajado.split("-");
        startTimeWorked = horasEmpleado[0];
        endTimeWorked = horasEmpleado[1];
        Date startTimeWorkedT = new Date("01/01/1900 "+startTimeWorked);
        Date endTimeWorkedT   = new Date("01/01/1900 "+endTimeWorked);

        switch(dia)
        {
            case "MO":
                tmpDayWeek = this.monday;
                break;
            case "TU":
                tmpDayWeek = this.tuesday;
                break;
            case "WE":
                tmpDayWeek = this.wednesday;
                break;
            case "TH":
                tmpDayWeek = this.thursday;
                break;
            case "FR":
                tmpDayWeek = this.friday;
                break;
            case "SA":
                tmpDayWeek = this.saturday;
                break;
            case "SU":
                tmpDayWeek = this.sunday;
                break;
        }
        
        //Verificamos cuantos horarios tiene este día
        for( int i=0 ; i < tmpDayWeek.getHorario().size() ; i++ )
        {
            //Access each schedule by day
            schedule = tmpDayWeek.getHorario().get(i);
            String startTime = schedule.getHoraDesde();
            String endTime   = schedule.getHoraHasta();
            double hourCost = schedule.getValorHora();
            Date startTimeT  = new Date("01/01/1900 "+startTime);
            Date endTimeT;
            if( endTime.equals("00:00")){
                endTimeT = new Date("02/01/1900 "+endTime);
            }else{
                endTimeT = new Date("01/01/1900 "+endTime);
            }
            hoursCalculated = 0;
            
            if( (startTimeWorkedT.after(startTimeT) || startTimeWorkedT.equals(startTimeT)) && 
                (startTimeWorkedT.before(endTimeT) || startTimeWorkedT.equals(endTimeT)) )
            {
                if( endTimeWorkedT.before(endTimeT) || endTimeWorkedT.equals(endTimeT) ){
                   hoursCalculated += minutosEntreDosFechas(endTimeWorkedT, startTimeWorkedT) / 60;
                }else{
                   hoursCalculated += minutosEntreDosFechas(endTimeT, startTimeWorkedT) / 60;
                   //variable startTimeWorkedT take new value
                   startTimeWorkedT = endTimeT;
                }
            }
            //Increments the variable total Pay
            totalPay += hoursCalculated * hourCost;
        }
        return totalPay;       
    }

                
    public void configurarValoresPorDia(){
        Horario horario;
        List<Horario> listaHorario = new ArrayList<Horario>();
        System.out.println("Reading work schedules...");
        //-----------------------------------------------------------
        //Monday - Friday
        //00:01 - 09:00 25 USD
        //09:01 - 18:00 15 USD
        //18:01 - 00:00 20 USD
        horario = new Horario();
        horario.setHoraDesde("00:01");
        horario.setHoraHasta("09:00");
        horario.setValorHora(25);
        horario.setValorMoneda("USD");
        listaHorario.add(horario);
        horario = new Horario();
        horario.setHoraDesde("09:01");
        horario.setHoraHasta("18:00");
        horario.setValorHora(15);
        horario.setValorMoneda("USD");
        listaHorario.add(horario);
        horario = new Horario();
        horario.setHoraDesde("18:01");
        horario.setHoraHasta("00:00");
        horario.setValorHora(20);
        horario.setValorMoneda("USD");
        listaHorario.add(horario);
        
        monday.setDía("MO");
        monday.setHorario(listaHorario);
        tuesday.setDía("TU");
        tuesday.setHorario(listaHorario);
        wednesday.setDía("WE");
        wednesday.setHorario(listaHorario);
        thursday.setDía("TH");
        thursday.setHorario(listaHorario);
        friday.setDía("FR");
        friday.setHorario(listaHorario);
        //-----------------------------------------------------------
        //Saturday and Sunday
        //00:01 - 09:00 30 USD
        //09:01 - 18:00 20 USD
        //18:01 - 00:00 25 USD
        listaHorario = new ArrayList<Horario>();;
        horario = new Horario();
        horario.setHoraDesde("00:01");
        horario.setHoraHasta("09:00");
        horario.setValorHora(30);
        horario.setValorMoneda("USD");
        listaHorario.add(horario);
        horario = new Horario();
        horario.setHoraDesde("09:01");
        horario.setHoraHasta("18:00");
        horario.setValorHora(20);
        horario.setValorMoneda("USD");
        listaHorario.add(horario);
        horario = new Horario();
        horario.setHoraDesde("18:01");
        horario.setHoraHasta("00:00");
        horario.setValorHora(25);
        horario.setValorMoneda("USD");
        listaHorario.add(horario);
        
        saturday.setDía("SA");
        saturday.setHorario(listaHorario);
        sunday.setDía("SU");
        sunday.setHorario(listaHorario);
    }
    
    
    public BufferedReader readFileEmployess()
    {
        FileReader fileReader;
        BufferedReader bufferReader;
        System.out.println("Loading file...");
        try{
            File fileSchedule = new File(this.pathFileEmployee);
            fileReader= new FileReader(fileSchedule);
            System.out.println("...");
            bufferReader = new BufferedReader(fileReader);
            System.out.println("Uploaded file!!!");
        }catch(FileNotFoundException ex){
            System.out.println("ERROR loading file... "+ex.getMessage());
            bufferReader = null;
        }
        return bufferReader;
    }
    
    
    public int minutosEntreDosFechas(Date fechaUno, Date fechaDos) {
        long diferenciaEn_ms = fechaUno.getTime() - fechaDos.getTime();
        long minutos = diferenciaEn_ms / (1000 * 60);
        return (int) minutos;
    }
}
