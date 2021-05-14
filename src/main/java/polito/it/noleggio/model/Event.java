package polito.it.noleggio.model;

import java.time.LocalTime;

public class Event implements Comparable <Event> {
	 //deve avere un attributo temporale, a quale istante di tempo
	//si riferisce l'evento
	
	
	public enum EventType {
		NUOVO_CLIENTE,
		RITORNO_AUTO
	}
	//le enum definiscono automaticamente delle costanti
	//associa dei numeri agli eventi, event 1, event 2...
	
	
	private LocalTime time;
	private EventType type; //numero vincolato
	
	@Override
	public int compareTo(Event o) {
		
		return this.time.compareTo(o.time);
	} //ordino cosi gli eventi per tempo.

	public Event(LocalTime time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + "]";
	}
	
	
}
