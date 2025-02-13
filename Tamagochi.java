package tamagochi;

public class Tamagochi {
	// VARIABLES DE LA CLASE
	private String nombre;
	private int hambre;
	private int sueno;
	private int higiene;
	private int diversion;

	// CONSTRUCTORES, GETER, SETER Y METODO TOSTRING
	public Tamagochi() {
	}

	public Tamagochi(String nombre, int hambre, int sueno, int higiene, int diversion) {
		this.nombre = nombre;
		setHambre(hambre);
		setSueno(sueno);
		setHigiene(higiene);
		setDiversion(diversion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHambre() {
		return hambre;
	}

	public void setHambre(int hambre) {
		this.hambre = comprobarParametros(hambre);
	}

	public int getSueno() {
		return sueno;
	}

	public void setSueno(int sueno) {
		this.sueno = comprobarParametros(sueno);
	}

	public int getHigiene() {
		return higiene;
	}

	public void setHigiene(int higiene) {
		this.higiene = comprobarParametros(higiene);
	}

	public int getDiversion() {
		return diversion;
	}

	public void setDiversion(int diversion) {
		this.diversion = comprobarParametros(diversion);
	}

	@Override
	public String toString() {
		return "Tamagochi [nombre=" + nombre + ", hambre=" + hambre + ", sueno=" + sueno + ", higiene=" + higiene
				+ ", diversion=" + diversion + "]";
	}

	// METODOS DE COMPROBACION
	private int comprobarParametros(int numero) {
		if (numero <= 100 && numero > 0) {
			return numero;
		} else {
			return numero = 50;
		}
	}

	public boolean vivo() {
		boolean vivo = true;
		if (hambre <= 0 || sueno <= 0 || higiene <= 0 || diversion <= 0) {
			vivo = false;
			hambre = 0;
			sueno = 0;
			higiene = 0;
			diversion = 0;
		}
		return vivo;
	}

	// METODOS DE CAMBIO DE CARACTERISTICAS
	public boolean comer() {
		boolean vivo = true;
		if (vivo()) {
			hambre += 20;
			sueno -= 10;
			higiene -= 10;
			diversion -= 10;
			if (hambre > 100) {
				hambre = 100;
			}
			if (!vivo())
				vivo = false;
		}
		return vivo;
	}

	public boolean dormir() {
		boolean vivo = true;
		if (vivo()) {
			hambre -= 10;
			sueno += 20;
			higiene -= 10;
			diversion -= 10;
			if (sueno > 100) {
				sueno = 100;
			}
			if (!vivo()) {
				vivo = false;
			}
		}
		return vivo;
	}

	public boolean duchar() {
		boolean vivo = true;
		if (vivo()) {
			hambre -= 10;
			sueno -= 10;
			higiene += 20;
			diversion -= 10;
			if (higiene > 100) {
				higiene = 100;
			}
			if (!vivo()) {
				vivo = false;
			}
		}
		return vivo;
	}

	public boolean jugar() {
		boolean vivo = true;
		if (vivo()) {
			hambre -= 10;
			sueno -= 10;
			higiene -= 10;
			diversion += 20;
			if (diversion > 100) {
				diversion = 100;
			}
			if (!vivo()) {
				vivo = false;
			}
		}
		return vivo;
	}

	// METODO PARA IMPRIBIR LAS CARACTERISTICAS
	public void mostrar() {
		System.out.println("---  Nombre: " + getNombre());
		if(vivo()) {
			System.out.println(" * Hambre: " + getHambre());
			System.out.println(" * Sueño: " + getSueno());
			System.out.println(" * Higiene: " + getHigiene());
			System.out.println(" * Diversion: " + getDiversion());
		}else {
			System.err.println("    | M |   ");
			System.err.println(" ---  U  --- ");
			System.err.println(" ---  Y  --- ");
			System.err.println("    |   |   ");
			System.err.println(" M U|E R|T O ");
		}
		
	}

}
