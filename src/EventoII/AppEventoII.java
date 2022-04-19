package EventoII;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AppEventoII {

	private static ArrayList<Venta> ventas 		= new ArrayList<Venta>();
	private static ArrayList<Asiento> asientos  = new ArrayList<Asiento>();
	private static ArrayList<Cliente> clientes  = new ArrayList<Cliente>();
	private static ArrayList<Entrada> entradas  = new ArrayList<Entrada>();
	
		public final static int OPCION_MENU_COMPRAR_TICKET 			= 1;
		public final static int OPCION_MENU_VER_VENTAS				= 2;
		public final static int OPCION_MENU_ENTRADAS_DISPONIBLES 	= 3;
		public final static int OPCION_MENU_VER_RECAUDACIONES 		= 4;
		public final static int OPCION_MENU_SALIR 					= 5;
	
	public static void main(String[] args) {
		init();
		int opcionSeleccionada;		
		do {
			opcionSeleccionada = menu();
			System.out.printf("\n", opcionSeleccionada);		
			switch(opcionSeleccionada) {
				case OPCION_MENU_COMPRAR_TICKET:
					comprarTicket();
					break;
				case OPCION_MENU_VER_VENTAS:
					verEntradasVendidas();
					break;
				case OPCION_MENU_ENTRADAS_DISPONIBLES:
					verEntradasDisponibles();
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
			Asiento asiento = new Asiento(i,"Palco", true);
			asientos.add(asiento);
		}
		
		for (int i = 26; i <= 50; i++) {
			Asiento asiento = new Asiento(i,"Platea", true);
			asientos.add(asiento);
		}
		
		for (int i = 51; i <= 100; i++) {
			Asiento asiento = new Asiento(i,"Galeria", true);
			asientos.add(asiento);
		}
	}
	
	private static void comprarTicket() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese nombre del cliente");
		String nombreCliente = scanner.nextLine();
		
		System.out.println("Ingrese apellido del cliente");
		String apellidoCliente = scanner.nextLine();
		
		System.out.println("Ingrese RUT del cliente");
		String rutCliente = scanner.nextLine();
		
		System.out.println("Ingrese asiento de preferencia: \n");
		System.out.println("1. 'Palco'   - $100.000");
		System.out.println("2. 'Platea'  - $60.000");
		System.out.println("3. 'Galeria' - $30.000");
		int preferenciaAsiento = scanner.nextInt();
		String tipoAsiento = "";
		switch(preferenciaAsiento) {
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
		
		
		Cliente cliente = new Cliente(rutCliente, nombreCliente, apellidoCliente);
		LocalDate fecha = LocalDate.now();


		final String nombreEvento = "Espectaculo Principal";
		
		final LocalDate fechaEvento = LocalDate.of(2022,05,1);
		boolean estadoEntrada = false;
		
		for (Asiento asiento : asientos) {
			if (asiento.getTipo() == tipoAsiento) {
				int valor = asiento.checkTipoEntrada();
				
				if (asiento.isEstado() == true) {
					asiento.setEstado(false);
					Entrada entrada = new Entrada(valor, estadoEntrada, fechaEvento, nombreEvento ,asiento);
					Venta venta = new Venta(fecha,entrada,cliente);
					ventas.add(venta);
					
					break;
				}
			}	
		}
	}
	
	private static void verEntradasVendidas() {
		
		System.out.println("Resumen Ticket");
		System.out.println("===========================================");


		for (Venta detalle : ventas) {
			
			System.out.print(detalle.getEntrada().getNomEvento()+"\n\n");
			System.out.print("Precio : "+detalle.getEntrada().getValor()+"\n");
			System.out.print("Fecha  : "+detalle.getEntrada().getFechaEvento()+"\n");
			System.out.print("Nombre : "+detalle.getCliente().getNombre()+" "+detalle.getCliente().getApellido()+"\n");
			System.out.print("Asiento: "+detalle.getEntrada().getAsiento().getAsiento()+"\n\n");
			System.out.print("Asiento: "+detalle.getEntrada().getAsiento().getTipo()+"\n\n");
		}
	}
	
	private static void verEntradasDisponibles() {
		int entradasDisponiblesPalco = 25;
		int entradasDisponiblesPlatea = 25;
		int entradasDisponiblesGaleria = 50;
		
		for (Asiento asiento : asientos) {
			if(asiento.isEstado() == false){
				if(asiento.getTipo() == "Palco") {
					entradasDisponiblesPalco --;
				} else if(asiento.getTipo() == "Platea") {
					entradasDisponiblesPlatea --;
				} else {
					entradasDisponiblesGaleria --;
				}
			}
		}
		System.out.println("Entradas disponibles 'Palco': "+ ""+ entradasDisponiblesPalco);
		System.out.println("Entradas disponibles 'Platea': "+ ""+ entradasDisponiblesPlatea);
		System.out.println("Entradas disponibles 'Galeria': "+ ""+ entradasDisponiblesGaleria);
		
	}
	
	private static void verRecaudaciones() {
		int valorTotal = 0;
		for (Venta venta : ventas) {
			valorTotal += venta.getEntrada().getValor();
		}
		System.out.println("Recaudaciones: $"+valorTotal);
		
	}
	
	private static int menu() {
		System.out.println("\nVENTA DE ENTRADAS:\n");
		System.out.println("1. COMPRAR TICKET");
		System.out.println("2. VER VENTAS");
		System.out.println("3. ENTRADAS DISPONIBLES");
		System.out.println("4. VER RECAUDACIONES");
		System.out.println("5. Salir \n");

		System.out.println("\nPor favor digite la opcion deseada: ");
		Scanner scanner = new Scanner(System.in);
		try {
			int opcionSeleccionada = scanner.nextInt();
			return opcionSeleccionada;
		} catch (InputMismatchException ime) {
			System.out.println("Debe ingresar los datos solicitados!!!");
		}
		return 0;
	}

}
