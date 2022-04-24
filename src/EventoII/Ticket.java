package EventoII;

import java.time.LocalDate;

public class Ticket {
	
	private int precio;
	private boolean condicionTicket;
	private LocalDate fechaEvento;
	private String nombreEvento;
	private Asiento asiento = new Asiento();
	
	public Ticket() {
	}

	public Ticket(int precio, boolean condicionTicket, LocalDate fechaEvento, String nombreEvento, Asiento asiento) {
		this.precio = precio;
		this.condicionTicket = condicionTicket;
		this.fechaEvento = fechaEvento;
		this.nombreEvento = nombreEvento;
		this.asiento = asiento;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean isCondicionTicket() {
		return condicionTicket;
	}

	public void setCondicionTicket(boolean condicionTicket) {
		this.condicionTicket = condicionTicket;
	}

	public LocalDate getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(LocalDate fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}
	
	
}
