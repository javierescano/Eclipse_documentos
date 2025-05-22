package dmm.metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Utilidades {

	public static final String URL_BASEDATOS = "jdbc:sqlite:././basesDatos/Practica18.db";
	
	public static final String OPCION_1 = "1.- Crear Tablas";
	public static final String OPCION_2 = "2.- Rellenar Datos Aleatorios";
	public static final String OPCION_3 = "3.- Insertar nuevos datos";
	public static final String OPCION_4 = "4.- Mostrar Registros";
	public static final String OPCION_5 = "5.- Eliminar Registro";
	public static final String OPCION_6 = "6.- Actualizar Precios de Productos";
	public static final String OPCION_7 = "7.- Salir";
	
	
	public static void inicioPrograma() {
		
		try {
			Connection con = DriverManager.getConnection(URL_BASEDATOS);
			Statement st = con.createStatement();
			
			String eleccion = mostrarMenuPrincipal();
			
			if (eleccion == OPCION_1) {
				crearTablas(st);
			} else if (eleccion == OPCION_2) {
				
			} else if (eleccion == OPCION_3) {
				insertarDatos(st);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	private static void crearTablas(Statement st) {
		
		String[] opcionesTablas = {"Clientes", "Poblacion", "Provincias", "Productos", "Categorias"};
		
		String eleccion = (String) JOptionPane.showInputDialog(null, "Seleccione que tabla desea crear", "CREAR TABLAS", JOptionPane.PLAIN_MESSAGE, null, opcionesTablas, opcionesTablas[0]);
		
		if (eleccion == opcionesTablas[0]) {
			generarTablaClientes(st);
		} else if (eleccion == opcionesTablas[1]) {
			generarTablaPoblaciones(st);
		} else if (eleccion == opcionesTablas[2]) {
			generarTablaProvincias(st);
		} else if (eleccion == opcionesTablas[3]) {
			generarTablaProductos(st);
		} else if (eleccion == opcionesTablas[4]) {
			generarTablaCategorias(st);
		}
	}
	
	
	private static void generarTablaClientes(Statement st) {
		try {
			
			if (comprobarTablaExistente(st, "Clientes") == 0) {
				JOptionPane.showMessageDialog(null, "Tabla Clientes creada con éxito", "CREAR TABLA: CLIENTES", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Tabla Clientes no se ha podido crear porque ya existe", "CREAR TABLA: CLIENTES", JOptionPane.ERROR_MESSAGE);
			}
			
			st.execute("CREATE TABLE IF NOT EXISTS \"Clientes\" (\r\n"
					+ "	\"ClienteID\"	INTEGER NOT NULL UNIQUE,\r\n"
					+ "	\"Nombre\"	varchar(100) NOT NULL,\r\n"
					+ "	\"Apellido\"	varchar(100) NOT NULL,\r\n"
					+ "	\"Email\"	varchar(255) NOT NULL UNIQUE,\r\n"
					+ "	\"Telefono\"	varchar(15),\r\n"
					+ "	\"Direccion\"	TEXT,\r\n"
					+ "	\"PoblacionID\"	INTEGER NOT NULL,\r\n"
					+ "	\"FechaNacimiento\"	DATE,\r\n"
					+ "	\"Activo\"	BOOLEAN NOT NULL DEFAULT 'TRUE',\r\n"
					+ "	\"FechaCreacion\"	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n"
					+ "	PRIMARY KEY(\"ClienteID\" AUTOINCREMENT),\r\n"
					+ "	CONSTRAINT \"poblacionid\" FOREIGN KEY(\"PoblacionID\") REFERENCES \"Poblaciones\"(\"PoblacionID\")\r\n"
					+ ");");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void generarTablaPoblaciones(Statement st) {
		try {
			
			if (comprobarTablaExistente(st, "Poblaciones") == 0) {
				JOptionPane.showMessageDialog(null, "Tabla Poblaciones creada con éxito", "CREAR TABLA: POBLACIONES", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Tabla Poblaciones no se ha podido crear porque ya existe", "CREAR TABLA: POBLACIONES", JOptionPane.ERROR_MESSAGE);
			}
			
			st.execute("CREATE TABLE IF NOT EXISTS \"Poblaciones\" (\r\n"
					+ "	\"PoblacionID\"	INTEGER NOT NULL UNIQUE,\r\n"
					+ "	\"NombrePoblacion\"	varchar(100) NOT NULL,\r\n"
					+ "	\"ProvinciaID\"	INTEGER NOT NULL,\r\n"
					+ "	PRIMARY KEY(\"PoblacionID\" AUTOINCREMENT),\r\n"
					+ "	CONSTRAINT \"provinciaID\" FOREIGN KEY(\"ProvinciaID\") REFERENCES \"Provincias\"(\"ProvinciaID\")\r\n"
					+ ");");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void generarTablaProvincias(Statement st) {
		try {
						
			if (comprobarTablaExistente(st, "Provincias") == 0) {
				JOptionPane.showMessageDialog(null, "Tabla Provincias creada con éxito", "CREAR TABLA: PROVINCIAS", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Tabla Provincias no se ha podido crear porque ya existe", "CREAR TABLA: PROVINCIAS", JOptionPane.ERROR_MESSAGE);
			}
			
			st.executeUpdate("CREATE TABLE IF NOT EXISTS \"Provincias\" (\r\n"
					+ "	\"ProvinciaID\"	INTEGER NOT NULL UNIQUE,\r\n"
					+ "	\"NombreProvincia\"	varchar(100) NOT NULL,\r\n"
					+ "	PRIMARY KEY(\"ProvinciaID\" AUTOINCREMENT)\r\n"
					+ ");");

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void generarTablaProductos(Statement st) {
		try {
			
			if (comprobarTablaExistente(st, "Productos") == 0) {
				JOptionPane.showMessageDialog(null, "Tabla Productos creada con éxito", "CREAR TABLA: PRODUCTOS", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Tabla Productos no se ha podido crear porque ya existe", "CREAR TABLA: PRODUCTOS", JOptionPane.ERROR_MESSAGE);
			}
			
			st.executeUpdate("CREATE TABLE IF NOT EXISTS \"Productos\" (\r\n"
					+ "	\"ProductoID\"	INTEGER NOT NULL UNIQUE,\r\n"
					+ "	\"NombreProducto\"	varchar(150) NOT NULL,\r\n"
					+ "	\"DescripcionProducto\"	TEXT,\r\n"
					+ "	\"PrecioUnitario\"	DECIMAL(10, 2) NOT NULL,\r\n"
					+ "	\"StockDisponible\"	INTEGER NOT NULL DEFAULT 0,\r\n"
					+ "	\"CategoriaID\"	INTEGER NOT NULL,\r\n"
					+ "	\"FechaCreacion\"	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n"
					+ "	PRIMARY KEY(\"ProductoID\" AUTOINCREMENT),\r\n"
					+ "	CONSTRAINT \"categoriaid\" FOREIGN KEY(\"CategoriaID\") REFERENCES \"Categorias\"(\"CategoriaID\")\r\n"
					+ ");");

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void generarTablaCategorias(Statement st) {
		try {
			
			if (comprobarTablaExistente(st, "Categorias") == 0) {
				JOptionPane.showMessageDialog(null, "Tabla Categorias creada con éxito", "CREAR TABLA: CATEGORIAS", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Tabla Categorias no se ha podido crear porque ya existe", "CREAR TABLA: CATEGORIAS", JOptionPane.ERROR_MESSAGE);
			}
			
			st.executeUpdate("CREATE TABLE IF NOT EXISTS \"Categorias\" (\r\n"
					+ "	\"CategoriaID\"	INTEGER NOT NULL UNIQUE,\r\n"
					+ "	\"NombreCategoria\"	varchar(100) NOT NULL,\r\n"
					+ "	\"DescripcionCategoria\"	TEXT,\r\n"
					+ "	PRIMARY KEY(\"CategoriaID\" AUTOINCREMENT)\r\n"
					+ ");");

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static int comprobarTablaExistente(Statement st, String nombre) {
	    ResultSet rs = null;
	    
	    final int BUENO = 1;
	    final int MALO = 0;
	    
	    int resultado = MALO;
	    
	    try {
	        rs = st.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + nombre + "';");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    try {
	        if (rs.next()) {
	            resultado = BUENO;
	        } else {
	            resultado = MALO;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return resultado;
	}



	
	private static void insertarDatos(Statement st) {
		
		String[] opcionesTablas = {"Clientes", "Poblacion", "Provincias", "Productos", "Categorias"};
		
		String eleccion = (String) JOptionPane.showInputDialog(null, "Seleccione en que tabla desea insertar datos", "INSERTAR DATOS", JOptionPane.PLAIN_MESSAGE, null, opcionesTablas, opcionesTablas[0]);
		
		if (eleccion == opcionesTablas[0]) {
			insertarClientes(st);
		} else if (eleccion == opcionesTablas[1]) {
			generarTablaPoblaciones(st);
		} else if (eleccion == opcionesTablas[2]) {
			generarTablaProvincias(st);
		} else if (eleccion == opcionesTablas[3]) {
			generarTablaProductos(st);
		} else if (eleccion == opcionesTablas[4]) {
			generarTablaCategorias(st);
		}
	}
	
	
	private static void insertarClientes(Statement st) {
		
		int clienteID;
		String nombre;
		String apellido;
		String email;
		String telefono;
		String direccion;
		int poblacionID;
		String fechaNacimiento;
		boolean activo;
		String fechaCreacion;
		
//		clienteID = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el número identificativo de cada cliente", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE));
		nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre:", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE);
		apellido = JOptionPane.showInputDialog(null, "Introduzca el apellido:", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE);
//		email = JOptionPane.showInputDialog(null, "Introduzca el email:", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE);
//		telefono = JOptionPane.showInputDialog(null, "Introduzca el telefono:", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE);
//		direccion = JOptionPane.showInputDialog(null, "Introduzca la direccion:", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE);
//		poblacionID = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el número identificativo de la población donde reside", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE));
//		fechaNacimiento = JOptionPane.showInputDialog(null, "Introduzca la fecha de nacimiento:", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE);



		
		try {
			
			ResultSet num = st.executeQuery("SELECT COUNT(*) FROM Clientes;");
			System.out.println(num.getString(1));
			int numID = Integer.parseInt(num.getString(1));
			
			st.execute("INSERT INTO \"main\".\"Clientes\"\r\n"
					+ "(\"ClienteID\", \"Nombre\", \"Apellido\", \"Email\", \"Telefono\", \"Direccion\", \"PoblacionID\", \"FechaNacimiento\", \"Activo\", \"FechaCreacion\")\r\n"
					+ "VALUES (" + (numID + 1) + ", '" + nombre + "', '" + apellido + "', " + (numID + 1) + ", 'telefono', 'direccion', 1, 'fechanacimiento', 'true', 'CURRENT_TIMESTAMP');");
			
			JOptionPane.showMessageDialog(null, "Datos insertados con éxito", "INSERTAR DATOS: CLIENTES", JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		
	
	
	private static String mostrarMenuPrincipal() {
		String[] opciones = {OPCION_1,
							 OPCION_2,
							 OPCION_3,
							 OPCION_4,
							 OPCION_5,
							 OPCION_6,
							 OPCION_7};
		
		String eleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción de las siguientes", "MENÚ", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		return eleccion;
	}
	
}
