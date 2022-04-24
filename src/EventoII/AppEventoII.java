package EventoII;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class AppEventoII {

	private static ArrayList<Venta> ventas 		= new ArrayList<Venta>();
	private static ArrayList<Asiento> asientos  = new ArrayList<Asiento>();
	
		public final static int OPCION_MENU_TICKET_DISPONIBLES 		= 1;
		public final static int OPCION_MENU_COMPRAR_TICKET 			= 2;
		public final static int OPCION_MENU_VER_VENTAS				= 3;
		public final static int OPCION_MENU_VER_RECAUDACIONES 		= 4;
		public final static int OPCION_MENU_SALIR 					= 5;
		static Scanner scanner = new Scanner(System.in);
		
	public static void main(String[] args) {
		init();
		int opcionSeleccionada;		
		do {
			opcionSeleccionada = menu();
			System.out.printf("\n", opcionSeleccionada);		
			switch(opcionSeleccionada) {
				case OPCION_MENU_TICKET_DISPONIBLES:
					verTicketDisponibles();
					break;
				case OPCION_MENU_COMPRAR_TICKET:
					comprarTicket();
					break;
				case OPCION_MENU_VER_VENTAS:
					mostrarTicketVendidos();
					break;
				case OPCION_MENU_VER_RECAUDACIONES:
					verRecaudaciones();
					break;
			}
		}while(opcionSeleccionada != OPCION_MENU_SALIR);
		System.out.printf("Selecciono la opcion %d", opcionSeleccionada);
	}

	private static void init() {
		for (int i = 1; i <= 25; i++) {
			Asiento asiento = new Asiento(i,"PALCO", true);
			asientos.add(asiento);
		}
		
		for (int i = 26; i <= 50; i++) {
			Asiento asiento = new Asiento(i,"PLATEA", true);
			asientos.add(asiento);
		}
		
		for (int i = 51; i <= 100; i++) {
			Asiento asiento = new Asiento(i,"GALERIA", true);
			asientos.add(asiento);
		}
	}
	
	private static void verTicketDisponibles() {
		int ticketDisponiblesPalco 		= 25;
		int ticketDisponiblesPlatea 	= 25;
		int ticketDisponiblesGaleria 	= 50;
		
		for (Asiento asiento : asientos) {
			if(asiento.isEstado() == false){
				if(asiento.getTipo() == "Palco") {
					ticketDisponiblesPalco --;
				} else if(asiento.getTipo() == "Platea") {
					ticketDisponiblesPlatea --;
				} else {
					ticketDisponiblesGaleria --;
				}
			}
		}
		System.out.println("Tickets disponibles para * Palco   * : "+ ticketDisponiblesPalco);
		System.out.println("Tickets disponibles para * Platea  * : "+ ticketDisponiblesPlatea);
		System.out.println("Tickets disponibles para * Galeria * : "+ ticketDisponiblesGaleria);
		
	}
	
	private static void comprarTicket() {
		scanner.nextLine();
		System.out.println("Ingrese Nombre del cliente");
		String nombre = scanner.nextLine();
		
		System.out.println("Ingrese Apellido del cliente");
		String apellido = scanner.nextLine();
		
		System.out.println("Ingrese RUT del cliente");
		String rut = scanner.nextLine();
			
		System.out.println("Ingrese tipo de asiento de su eleccion \n");
		System.out.println("1. Palco   - $100.000");
		System.out.println("2. Platea  - $60.000");
		System.out.println("3. Galeria - $30.000");
		int eleccionAsiento = scanner.nextInt();
		String tipoAsiento = "";

		switch( eleccionAsiento ) {
			case 1:
				tipoAsiento = "PALCO";
				break;
			case 2:
				tipoAsiento = "PLATEA";
				break;
			case 3:
				tipoAsiento = "GALERIA";
				break;
		}
		
		Cliente cliente = new Cliente(rut, nombre, apellido);
		LocalDate fecha = LocalDate.now();
		
		final String Evento ="GRAN CONCIERTO";
		final LocalDate fechaEvento = LocalDate.of(2022, 5, 1);
		boolean condicionTicket = false;
		for (Asiento asiento : asientos) {
			if(asiento.getTipo() == tipoAsiento) {
				int precio = asiento.checkTipoTicket();
				
				if (asiento.isEstado() == true) {
					asiento.setEstado(false);
					Ticket ticket = new Ticket(precio, condicionTicket, fechaEvento, Evento, asiento);
					Venta venta = new Venta(fecha, ticket, cliente);
					ventas.add(venta);
					break;	
				}
			}
		}
	
	}
	
	private static void mostrarTicketVendidos() {
		
		System.out.println("==================================================================");
		System.out.println("Nombre Evento || Tipo Asiento  N°  || Nombre Cliente  ||  Precio  ||");
		System.out.println("==================================================================\n");

		for (Venta detalle : ventas) {
			
			System.out.print(""+detalle.getTicket().getNombreEvento()+"\n"+detalle.getTicket().getFechaEvento()+"    ||");
			System.out.print("     "+detalle.getTicket().getAsiento().getTipo()+" "+detalle.getTicket().getAsiento().getAsiento()+"     ||");
			System.out.print("  "+detalle.getCliente().getNombre()+" "+detalle.getCliente().getApellido()+"   ||");
			System.out.print("   "+detalle.getTicket().getPrecio()+"  ||\n");
			
		}
		System.out.println("==================================================================\n");
	}
	
	
	
	private static void verRecaudaciones() {
		int valorTotal = 0;
		for (Venta venta : ventas) {
			valorTotal = valorTotal + venta.getTicket().getPrecio();
		}
		System.out.println("TOTAL DE LAS VENTAS : $ "+valorTotal);
		
	}
	
	private static int menu() {
		System.out.println("\nMENU COMPRA DE TICKET \n==================================");
   		System.out.println("1. TICKETS DISPONIBLES ");
    	System.out.println("2. COMPRAR TICKETS ");
    	System.out.println("3. TICKETS VENDIDOS ");
    	System.out.println("4. RECAUDACION TOTAL DE VENTAS");
   		System.out.println("5. SALIR ");
    	System.out.println("==================================\n");
    	System.out.println("Seleccione la opción deseada");
    	
		try {
			int opcionSeleccionada = scanner.nextInt();
			return opcionSeleccionada;
		} catch (InputMismatchException ime) {
			System.out.println("Debe ingresar los datos solicitados!!!");
		}
		return 0;
	}

}
