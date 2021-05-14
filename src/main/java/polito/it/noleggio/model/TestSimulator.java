package polito.it.noleggio.model;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class TestSimulator {

	public static void main(String args[]) {
		Simulator sim = new Simulator();
		
		sim.setNumCars(10) ; //imposto il numero di auto totali
		sim.setClientFrequency(Duration.of(10, ChronoUnit.MINUTES)) ;
		//imposto la frequenza di arrivo dei clienti
		
		sim.run() ; //parte la simulazione
		
		//parametri richiesti dall'esercizio
		int totClients = sim.getTotClients() ;
		int dissatisfied = sim.getDissatisfied() ;
		
		System.out.format("Arrived %d clients, %d were dissatisfied\n", 
			totClients, dissatisfied);
	}
	
}
