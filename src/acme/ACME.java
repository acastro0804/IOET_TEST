/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acme;

import datos.ServicioConfiguracionPagos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author          Andy Castro A
 * Creation Date:   07/03/2021
 * @version         1.1
 * Description:
   The company ACME offers their employees the flexibility to work the hours they 
   want. They will pay for the hours worked based on the day of the week and time 
   of day, according to the following table:
   Monday - Friday
   00:01 - 09:00 25 USD
   09:01 - 18:00 15 USD
   18:01 - 00:00 20 USD
   Saturday and Sunday
   00:01 - 09:00 30 USD
   09:01 - 18:00 20 USD
   18:01 - 00:00 25 USD
   The goal of this exercise is to calculate the total that the company has to pay 
   an employee, based on the hours they worked and the times during which they 
   worked. The following abbreviations will be used for entering data:

   MO: Monday       TU: Tuesday     WE: Wednesday
   TH: Thursday     FR: Friday      SA: Saturday
   SU: Sunday

   Input: the name of an employee and the schedule they worked, indicating the 
   time and hours. This should be a .txt file with at least five sets of data. 
   You can include the data from our two examples below.

   Output: indicate how much the employee has to be paid
   For example:
   Case 1:
   INPUT
   RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00
   OUTPUT:
   The amount to pay RENE is: 215 USD

   Case 2:
   INPUT
   ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00
   OUTPUT:
   The amount to pay ASTRID is: 85 USD
 */
public class ACME {
    public static void main(String[] args) {
        // TODO code application logic here
        ServicioConfiguracionPagos servicesPay = new ServicioConfiguracionPagos("c:/config/scheludeWorked.txt");
        double total = 0.0;
        double totalByDay = 0.0;
        BufferedReader bufferReader;
        String lineFile = "";
        String nameEmployee = "";
        String scheduleEmployee;
        String[] arrayLineaEmployee;
        String[] arrayScheduleEmployee;
        try
        {
            System.out.println("#############################################");
            System.out.println("#############################################");
            System.out.println("############ ACME - WELCOME #######################");
            System.out.println("#############################################");
            System.out.println("#############################################");
            System.out.println("");
            //-----------------------------------------------------
            //Setting schedule
            servicesPay.configurarValoresPorDia();
            //-----------------------------------------------------            
            //read file of Employees
            bufferReader = servicesPay.readFileEmployess();
            System.out.println("");
            while( (lineFile = bufferReader.readLine()) != null )
            {
                arrayLineaEmployee = lineFile.split("=");
                nameEmployee     = arrayLineaEmployee[0];
                scheduleEmployee = arrayLineaEmployee[1];
                System.out.println("-----------------------------------------");
                System.out.println("Employee: "+nameEmployee);
                System.out.println("Schedule: "+scheduleEmployee);
                arrayScheduleEmployee = scheduleEmployee.split(",");
                for( int i=0 ; i<arrayScheduleEmployee.length ; i++)
                {                
                    totalByDay = servicesPay.getPayForWorked(
                                    arrayScheduleEmployee[i].substring(0, 2),
                                    arrayScheduleEmployee[i].substring(2));
                    System.out.println(arrayScheduleEmployee[i].substring(0, 2) + " -> "+totalByDay);
                    total += totalByDay;
                }
                System.out.println("The amount to pay "+nameEmployee+" is: "+total);
                total            = 0.0;
                totalByDay       = 0.0;
                nameEmployee     = "";
                scheduleEmployee = "";
            }
            System.out.println("-----------------------------------------");
            
        }catch(IOException ex){
            System.out.println("ERROR loading file... "+ex.getMessage());
        }
    }
}