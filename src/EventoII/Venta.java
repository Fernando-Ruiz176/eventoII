package EventoII;

import java.time.LocalDate;

public class Venta {
	
	private LocalDate fechaVenta;
	private Ticket ticket;
	private Cliente cliente = new Cliente();
	
	public Venta() {	
	}

	public Venta(LocalDate fechaVenta, Ticket ticket, Cliente cliente) {
		this.fechaVenta = fechaVenta;
		this.ticket = ticket;
		this.cliente = cliente;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
}
