========================
BUILD OUTPUT DESCRIPTION
========================
To run the project from the command line, go to the dist folder and
type the following:

java -jar "ACME.jar" 

the file that has data about employee y schedule by pay, should be n
the "c:/app/" folder. The name file should be "scheludeWorked.txt".
IMPORTANT!!!
"c:/app/scheludeWorked.txt"

Semantic of programation: 
	Format Camel
Architecture:
	3 layer (Model, Service and Front)

Model:
	Layer that contains classes models of project. This layer have two model:
	1) Horario 
	   Have four atributtes
	   1) horaDesde   (String)	Indicate the time start
	   2) horaHasta	  (String)	Indicate the time end
	   3) valorHora	  (double)	Indicate value by hour
	   4) valorMoneda (String)	Indicate type money
	
	2) DiaLaboral: 
	   Have two atributtes
	   1) dia	  (String)		  Specify the day of schedule
	   2) horario (List<Horario>) Have a list de data type Horario

Service:
	Layer that contains logic of process. Have one file:
	1) ServicioConfiguracionPagos
		Layer that contains logic of process, load configurations, 
		inicializate data of schedule, calculate total by employeer.
		Have four Functionalities:
		getPayForWorked 		.- Gets total pay for one day. Receives day and hours worked
		configurarValoresPorDia .- Setting hours and value of pay for day. To improve, this information 
								   can be taken from a JSON file or service remote, serializing 
								   the model classes of project
		readFileEmployess 		.- Read the file of employees, than containt information about day and 
								   hours worked; the reading is line by line.
		minutosEntreDosFechas	.- Gets diffetents between two date. Return the minutes of difference

Front:

	Invoces the Functionalities of services  layer (class ServicioConfiguracionPagos), and show the 
	pay total for employees. Here it's send the file path of hours worked of employees.



