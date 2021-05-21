package polito.it.noleggio.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;

import polito.it.noleggio.model.Event.EventType;



public class Simulator {
	
	//Eventi
	PriorityQueue <Event> queue;
	
	//Stato del sistema
	int nAuto;
	
	//parametri di simulazione
	int NC;
	private Duration T_IN;
	private LocalTime oraApertura= LocalTime.of(8, 0);
	private LocalTime oraChiusura=LocalTime.of(20, 0);
	
	//parametri in uscita, output
	private int nClienti;
	private int nClientiInsoddisfatti;
	
	
	public void run() {
		queue= new PriorityQueue <>();
		nAuto=NC;
		nClienti=0;
		nClientiInsoddisfatti=0;
		LocalTime ora= this.oraApertura;
		
		while(ora.isBefore(oraChiusura)) {
			queue.add(new Event(ora, EventType.NUOVO_CLIENTE));
			ora=ora.plus(T_IN);
			
		}
		
		while(!queue.isEmpty()) {
			Event e=queue.poll();
			processEvent(e);
		}
	}


	private void processEvent(Event e) {
		switch(e.getTipo()) {
		case NUOVO_CLIENTE: 
			if(nAuto>0) {
				nAuto--;
				nClienti++;
				double  num= Math.random()*3;
				if(num<1.0) {
					queue.add(new Event(e.getTime().plus(Duration.of(1, ChronoUnit.HOURS)), EventType.RITORNO_AUTO));
				}
				if(num<2.0) {
					queue.add(new Event(e.getTime().plus(Duration.of(2, ChronoUnit.HOURS)), EventType.RITORNO_AUTO));
				}
				if(num<3.0) {
					queue.add(new Event(e.getTime().plus(Duration.of(3, ChronoUnit.HOURS)), EventType.RITORNO_AUTO));
				}
				
			}
			else {
				this.nClienti++;
				this.nClientiInsoddisfatti++;
			}
			break;
		case RITORNO_AUTO:
			nAuto++;
			break;
		}
		
	}


	public void setNumCars(int NC) {
		this.NC=NC;
		
		
	}


	public void setClientFrequency(Duration of) {
		this.T_IN=of;
		
	}


	public int getTotClients() {
		return this.nClienti;
	}


	public int getDissatisfied() {
		
		return this.nClientiInsoddisfatti;
	}

}
