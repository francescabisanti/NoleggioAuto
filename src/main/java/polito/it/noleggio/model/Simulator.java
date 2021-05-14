package polito.it.noleggio.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;

import polito.it.noleggio.model.Event.EventType;

public class Simulator {
	
	//Eventi
	private PriorityQueue <Event> queue;
	
	//parametri di simulazione
	private int NC; //numero di macchine
	private Duration T_IN; // intervallo di tempo tra clienti
	private LocalTime oraApertura= LocalTime.of(8, 0);
	
	private LocalTime oraChiusura=LocalTime.of(20, 0);
	
	
	//Stato del sistema
	private int nAuto; //auto attualmente presenti;
	
	
	//Misure in uscita
	private int nClienti;
	private int nClientiInsoddisfatti;
	
	// Impostazione dei parametri iniziali
	
	public void setNumCars (int NC) {
		this.NC=NC;
		
	}
	
	public void setClientFrequency (Duration d) {
		this.T_IN=d;
	}
	
	//simulazione
	public void run() {
		this.queue= new PriorityQueue <Event>();
		
		
		//Stato iniziale del sistema
		this.nAuto=NC; //Tutte le auto sono nel garage
		this.nClienti=0; //non ho ancora clienti
		this.nClientiInsoddisfatti=0;
		
		//Eventi iniziali del sistema
		LocalTime ora = this.oraApertura;
		while(ora.isBefore(this.oraChiusura)) {
			this.queue.add(new Event (ora, EventType.NUOVO_CLIENTE));
			ora=ora.plus(this.T_IN);
		}
		
		//Ciclo di simulazione vera e propria, sarà uguale sempre
		while(!this.queue.isEmpty()) { //finchè la coda non è vuota
			Event e=this.queue.poll();  //estrai il prossimo evento
			processEvent(e);
		}
	}
	
	public void processEvent (Event e) {
		switch(e.getType()) {
		case NUOVO_CLIENTE:
			this.nClienti++;
			if(this.nAuto>0) {
				
				//noleggia
				this.nAuto--;
				double num= Math.random()*3; // [0,3), numero ore da agg
				if(num<1.0) {
					//prevedo che arriva un auto tra un 'ora
					this.queue.add(new Event(e.getTime().plus(Duration.of(1, ChronoUnit.HOURS)),
							EventType.RITORNO_AUTO)
							); 
					
						
						
						//aggiungo un'ora con tutta quella sintassi ad "adesso"
					
				} else if(num<2.0) {
					this.queue.add(new Event(e.getTime().plus(Duration.of(2, ChronoUnit.HOURS)),
							EventType.RITORNO_AUTO)
							); 
				} else {
					this.queue.add(new Event(e.getTime().plus(Duration.of(3, ChronoUnit.HOURS)),
							EventType.RITORNO_AUTO)
							); 
				}
				
			}
			else {
				//insoddisfatto
				this.nClientiInsoddisfatti++;
			}
			break;
		case RITORNO_AUTO:
			this.nAuto++;
			break;
		}
	}
	
	public int getTotClients() {
		return this.nClienti;
	}
	public int getDissatisfied() {
		return this.nClientiInsoddisfatti;
	}
	

}
