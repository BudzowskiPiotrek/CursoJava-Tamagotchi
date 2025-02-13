package tamagochi;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class App {

	// ARRAY DE 20 TAMAGOCHIS Y SCANNER
	public static Tamagochi[] tamagochis = new Tamagochi[20];
	public static Scanner sn = new Scanner(System.in);
	public static Scanner st = new Scanner(System.in);

	public static void main(String[] args) {
		inicializarTamagochis();
		menu();
	}

	// METODO CON MENU PRINCIPAL
	private static void menu() {
		int flag = 0;
		do {
			try {
				System.out.println("\nMenú de Tamagochis:\n" + "Pulsa 1. Añadir Tamagochi\n"
						+ "Pulsa 2. Listar Tamagochis\n" + "Pulsa 3. Interactuar con un Tamagochi\n"
						+ "Pulsa 4. Atender necesidades urgentes\n" + "Pulsa 5. Salir\n" + "Elige una opción: ");
				flag = sn.nextInt();
				menuDos(flag);
			} catch (InputMismatchException e) {
				System.err.println("Ingresaste de forma incorecta los datos, solo cadenas de numeros");
				sn.next();
			}
		} while (flag != 5);
	}

	// METODO CON EL SWITCH DEL MENU
	private static void menuDos(int flag) {
		switch (flag) {
		case 1:
			anadirTamagochi();
			break;
		case 2:
			listarTamagochis();
			break;
		case 3:
			interactuarConTamchochi(interactuarNumero());
			break;
		case 4:
			atenderNecesidadesUrgentes();
			break;
		case 5:
			System.out.println("Saliendo del programa. ¡Hasta pronto!");
			break;
		default:
			System.err.println("Opción no válida. Intenta de nuevo.");
		}
	}

	// METODO INICIALIZACION PRIMEROS 5 TAMAGOCHIS
	private static void inicializarTamagochis() {
		tamagochis[0] = new Tamagochi("Cleo", 60, 70, 80, 90);
		tamagochis[1] = new Tamagochi("Paul", 50, 50, 50, 50);
		tamagochis[2] = new Tamagochi("Juli", 30, 40, 20, 60);
		tamagochis[3] = new Tamagochi("Vega", 80, 60, 70, 90);
		tamagochis[4] = new Tamagochi("Toby", 40, 30, 50, 20);
		System.out.println("Se han inicializado los cinco primeros Tamagochis.");
	}

	// METODO AÑADIR TAMAGOCHI
	private static void anadirTamagochi() {
		boolean seguro = true;
		for (int i = 0; i < tamagochis.length && seguro; i++) {
			if (tamagochis[i] == null) {
				try {
					System.out.print("Introduce el nombre del nuevo Tamagochi: ");
					String nombre = st.nextLine();
					System.out.print("Estadisticas del tamagochi solo se permite ingresar numeros, rango de (1-100)\n"
							+ "en caso de poner mas o menos se restablecera con un 50\n" + "Nivel de hambre: ");
					int hambre = sn.nextInt();
					System.out.print("Nivel de sueño: ");
					int sueno = sn.nextInt();
					System.out.print("Nivel de higiene: ");
					int higiene = sn.nextInt();
					System.out.print("Nivel de diversión: ");
					int diversion = sn.nextInt();
					tamagochis[i] = new Tamagochi(nombre, hambre, sueno, higiene, diversion);
					System.out.println("Tamagochi añadido en la posición " + (i + 1));
					seguro = false;
				} catch (InputMismatchException e) {
					System.err.println("Ingresaste de forma incorecta los datos, vuelve a intentar solo con numeros");
					sn.next();
					i = 0;
				}
			}
		}
		if (seguro == true) {
			System.out.println("No hay espacio disponible para más Tamagochis.");
		}
	}

	// METODO LISTAR TAMAGOCHI
	private static void listarTamagochis() {
		System.out.println("\nLista de Tamagochis:");
		for (int i = 0; i < tamagochis.length; i++) {
			if (tamagochis[i] != null) {
				System.out.println("ID: " + (i + 1));
				tamagochis[i].mostrar();
			}
		}
	}

	// METODO PARA SABER CON QUIEN QUIERES INTERACTUAR
	private static int interactuarNumero() {
		System.out.println("Introduce el número del Tamagochi con el que deseas interactuar (1-20): ");
		int indice = sn.nextInt() - 1;
		return indice;
	}

	// METODO PARA INTERACTUAR CON EL TAMAGOCHI
	private static void interactuarConTamchochi(int indice) {
		if (indice >= 0 && indice < tamagochis.length && tamagochis[indice] != null && tamagochis[indice].vivo()) {
			System.out.println("¿Qué deseas hacer con " + tamagochis[indice].getNombre() + "?\n" + "1. Comer\n"
					+ "2. Dormir\n" + "3. Ducharse\n" + "4. Jugar\n" + "Elige una opción: ");
			try {
				int flag = sn.nextInt();
				boolean sigueVivo = true;
				switch (flag) {
				case 1:
					sigueVivo = tamagochis[indice].comer();
					break;
				case 2:
					sigueVivo = tamagochis[indice].dormir();
					break;
				case 3:
					sigueVivo = tamagochis[indice].duchar();
					break;
				case 4:
					sigueVivo = tamagochis[indice].jugar();
					break;
				default:
					System.err.println("Opción no válida. Intenta de nuevo.");
				}
				if (!sigueVivo) {
					System.err.println(tamagochis[indice].getNombre()
							+ " ha muerto. Todos sus atributos han sido establecidos a 0.");
				} else {
					tamagochis[indice].mostrar();
				}
			} catch (InputMismatchException e) {
				System.err.println("Ingresaste de forma incorecta los datos, no se efectua cambios y vuelves al menu");
				sn.next();
			}
		} else {
			System.out.println("Número de Tamagochi no válido o no existe, o esta muerto");
		}
	}

	// METODO ATENDER NECESIDADES URGENTES
	private static void atenderNecesidadesUrgentes() {
		boolean herido = false;
		for (int i = 0; i < tamagochis.length; i++) {
			if (tamagochis[i] != null && tamagochis[i].vivo()
					&& (tamagochis[i].getHambre() < 20 || tamagochis[i].getSueno() < 20
							|| tamagochis[i].getHigiene() < 20 || tamagochis[i].getDiversion() < 20)) {
				System.err.println("Atencion tamagochi con nombre:" + tamagochis[i].getNombre()
						+ " su numero en la lista es : " + (i + 1));
				tamagochis[i].mostrar();
				herido = true;
				for (int j = 0; j < 1; j++) {
					try {
						System.out.println("\nQuieres ayudarle/s\nPulsa1. Si\nPulsa2. No");
						int opcion = sn.nextInt();
						switch (opcion) {
						case 1:
							interactuarConTamchochi(i);
							break;
						case 2:
							// solo break para vovler al menu solamente si se pulsa el 2.
							break;
						default:
							System.err.println("Opción no válida. Intenta de nuevo.");
						}
					} catch (InputMismatchException e) {
						System.err.println("Ingresaste de forma incorecta los datos, vuelve a intentar");
						sn.next();
						j = 0;
					}
				}
			}
		}
		if (!herido) {
			System.out.println("Todos sanitos y coleando");
		}
	}
}
