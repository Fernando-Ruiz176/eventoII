package EventoII;

public class Asiento {
	
	private int asiento;
	private String tipo;
	private boolean estado;
	
	public Asiento() {
	}

	public Asiento(int asiento, String tipo, boolean estado) {
		this.asiento = asiento;
		this.tipo = tipo;
		this.estado = estado;
	}
	
	public int checkTipoTicket() {
		if (this.tipo == "PALCO") {
			return 100000;
		}else if (this.tipo == "PLATEA") {
			return 60000;
		}else {
			return 30000;
		}
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
