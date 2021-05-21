package polito.it.noleggio.model;

import java.time.LocalTime;

public class Event implements Comparable <Event> {
	 private LocalTime time;
	 
	 public enum EventType{
		 NUOVO_CLIENTE,
		 RITORNO_AUTO
		 
	 }
	 private EventType tipo;
	public Event(LocalTime time, EventType tipo) {
		super();
		this.time = time;
		this.tipo = tipo;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public EventType getTipo() {
		return tipo;
	}
	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int compareTo(Event o) {
		
		return this.time.compareTo(o.time);
	}
	 
	
}
