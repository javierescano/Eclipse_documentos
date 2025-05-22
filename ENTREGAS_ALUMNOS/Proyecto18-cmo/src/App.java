
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class App {

	final static int CREAR_TABLA = 0;
	final static int INSERTAR_DATOS_ALEATORIOS = 1;
	final static int INSERTAR_DATOS_MANUALMENTE = 2;
	final static int MOSTRAR_REGISTROS = 3;
	final static int ELIMINAR_REGISTRO = 4;
	final static int ACTUALIZAR_PRECIOS = 5;
	
	public static void main(String[] args) {

		System.out.println("Ubicacion de la base de datos: " + new File("mi_base_de_datos.db").getAbsolutePath() + "\n\n\n");
		ResultSet rs = null;
		String ruta = "jdbc:sqlite:E:\\aaaaaaaaaaaa.db";
		Connection connection = null;
		Statement statement = null;
		String SQL = "";
		String[] sqlApoyo = new String[2];

		String[] menuPrincipal = { "Crear tablas", "Rellenar datos aleatorios", "Insertar nuevos datos",
				"Mostrar registros", "Eliminar registro", "Actualizar precios", "Salir" };
		

		int resultadoMenuPrincipal;

		try {
			// Conectar a la base de datos (si no existe, la crea)
			connection = DriverManager.getConnection(ruta);

			do {
				resultadoMenuPrincipal = JOptionPane.showOptionDialog(null, "Selecciona una opcion", "MENU PRINCIPAL",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuPrincipal, null);

				if (resultadoMenuPrincipal == CREAR_TABLA) {
					SQL = crearTabla(connection);
				} else if (resultadoMenuPrincipal == INSERTAR_DATOS_ALEATORIOS) {
					SQL = datosAleatorios();
				} else if (resultadoMenuPrincipal == INSERTAR_DATOS_MANUALMENTE) {
					SQL = insertarDatos();
				} else if (resultadoMenuPrincipal == MOSTRAR_REGISTROS) {
					sqlApoyo = mostrarRegistros();
					statement = connection.createStatement();
					rs = statement.executeQuery(sqlApoyo[0]);
					mostrarRegistros(rs, sqlApoyo[1]);
				} else if (resultadoMenuPrincipal == ELIMINAR_REGISTRO) {
					statement = connection.createStatement();
					rs = statement.executeQuery("SELECT CLIENTEID,NOMBRE FROM CLIENTES");
					SQL = mostrarClientes(rs);
				} else if (resultadoMenuPrincipal == ACTUALIZAR_PRECIOS) {
					SQL = actualizarPrecio();
				}

				if (resultadoMenuPrincipal != MOSTRAR_REGISTROS) {
					// Crear una declaración SQL
					statement = connection.createStatement();

					int numeroProductosAfectados = statement.executeUpdate(SQL);
					if (resultadoMenuPrincipal == ACTUALIZAR_PRECIOS) {
						JOptionPane.showMessageDialog(null,
								"número de productos afectados: " + numeroProductosAfectados);
					}
				}
			} while (resultadoMenuPrincipal != 6 && resultadoMenuPrincipal != -1);
			JOptionPane.showMessageDialog(null, "Saliendo de la base de datos...");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
			}
		}

	}

	public static void mostrarRegistros(ResultSet rs, String SQL) throws SQLException{
		if (SQL.equals("Cliente")) {
			System.out.printf("%-10s %-15s %-15s %-20s %-15s %-25s %-12s %-20s %-20s\n",
					"ID", "Nombre", "Apellido", "Email", "Teléfono", "Dirección", "Poblac.ID", "Población", "Provincia");
			System.out.println("=".repeat(150));
			while (rs.next()) {
				System.out.printf("%-10d %-15s %-15s %-20s %-15s %-25s %-12d %-20s %-20s\n",
						rs.getInt("ClienteID"),
						rs.getString("Nombre"),
						rs.getString("Apellido"),
						rs.getString("Email"),
						rs.getString("Telefono"),
						rs.getString("Direccion"),
						rs.getInt("PoblacionID"),
						rs.getString("NombrePoblacion"),
						rs.getString("NombreProvincia"));
			}
		} else if (SQL.equals("Producto")) {
			while (rs.next()) {
				int id = rs.getInt("ProductoID");
				String nombre = rs.getString("NombreProducto");
				String descripcion = rs.getString("DescripcionProducto");
				double precio = rs.getDouble("PrecioUnitario");
				int stock = rs.getInt("StockDisponible");
				String categoria = rs.getString("NombreCategoria");

				System.out.println("ID PRODUCTO: " + id + "\n | NOMBRE PRODUCTO: " + nombre + "\n | DESCRIPCION: "
						+ descripcion + "\n | PRECIO: " + precio + " €" + "\n | STOCK DISPONIBLE: " + stock
						+ "\n | CATEGORIA: " + categoria);
			}
		}
	}

	public static boolean existeTabla(Connection connection, String nombreTabla) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + nombreTabla + "';");
			boolean existe = rs.next(); // Si existe, habrá al menos un resultado
			rs.close();
			stmt.close();
			return existe;
		} catch (SQLException e) {
			return false;
		}
	}

	public static String crearTabla(Connection connection) {

		String[] opciones = { "Clientes", "Población", "Provincias", "Productos", "Categorias" };
		String createTable = "";
		String tabla = "";
		int resultadoMenuPrincipal = JOptionPane.showOptionDialog(null, "Selecciona una opcion válida", "CREAR TABLA",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);



if (resultadoMenuPrincipal == 0) {

			// Crear la tabla de usuarios
			tabla = "Clientes";
			createTable = "CREATE TABLE IF NOT EXISTS Clientes (" + "ClienteID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "Nombre VARCHAR(100) NOT NULL, " + "Apellido VARCHAR(100) NOT NULL, "
					+ "Email VARCHAR(255) NOT NULL, " + "Telefono VARCHAR(15), " + "Direccion TEXT, "
					+ "PoblacionID INT NOT NULL, " + "FOREIGN KEY (PoblacionID) REFERENCES Poblaciones(PoblacionID)"
					+ ")";
		} else if (resultadoMenuPrincipal == 1) {
			tabla = "Poblaciones";
			createTable = "CREATE TABLE IF NOT EXISTS Poblaciones (" + "PoblacionID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "NombrePoblacion VARCHAR(100) NOT NULL, " + "ProvinciaID INT NOT NULL, "
					+ "FOREIGN KEY (ProvinciaID) REFERENCES Provincias(ProvinciaID)" + ")";
		} else if (resultadoMenuPrincipal == 2) {
			tabla = "Provincias";
			createTable = "CREATE TABLE IF NOT EXISTS Provincias (" + "ProvinciaID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "NombreProvincia VARCHAR(100) NOT NULL" + ")";

		}

		else if (resultadoMenuPrincipal == 3) {
			tabla = "Productos";
			createTable = "CREATE TABLE IF NOT EXISTS Productos (" + "ProductoID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "NombreProducto VARCHAR(150) NOT NULL, " + "DescripcionProducto TEXT, "
					+ "PrecioUnitario DECIMAL(10,2) NOT NULL, " + "StockDisponible INT NOT NULL DEFAULT 0, "
					+ "CategoriaID INT NOT NULL, " + "FOREIGN KEY (CategoriaID) REFERENCES Categorias(CategoriaID)"
					+ ")";
		} else if (resultadoMenuPrincipal == 4) {
			tabla = "Categorias";
			createTable = "CREATE TABLE IF NOT EXISTS Categorias (" + "CategoriaID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "NombreCategoria VARCHAR(100) NOT NULL, " + "DescripcionCategoria TEXT" + ")";
		}
		if (existeTabla(connection, tabla)) {
			JOptionPane.showMessageDialog(null, "La tabla '" + tabla + "' ya existe.");
		} else {
			JOptionPane.showMessageDialog(null, "Tabla '" + tabla + "' creada con éxito");
		}
		return createTable;
	}

	public static String datosAleatorios() {
		String datos;

		datos = "INSERT INTO Provincias (NombreProvincia) VALUES " + "('Madrid')," + "('Barcelona')," + "('Sevilla'),"
				+ "('Valencia')," + "('Zaragoza')," + "('Málaga')," + "('Murcia')," + "('Palma de Mallorca'),"
				+ "('Las Palmas')," + "('Bilbao')," + "('Alicante')," + "('Valladolid')," + "('Córdoba')," + "('Vigo'),"
				+ "('Gijón')," + "('La Coruña')," + "('Granada')," + "('Vitoria')," + "('Elche')," + "('Oviedo'),"
				+ "('Badalona')," + "('Cartagena')," + "('Jerez de la Frontera')," + "('Sabadell')," + "('Pamplona'),"
				+ "('Fuenlabrada')," + "('Almería');";

		datos += "INSERT INTO Poblaciones (NombrePoblacion, ProvinciaID) VALUES " + "('Alcalá de Henares', 1),"
				+ "('Móstoles', 1)," + "('Hospitalet de Llobregat', 2)," + "('Dos Hermanas', 3)," + "('Torrent', 4),"
				+ "('Utrera', 3)," + "('Getafe', 1)," + "('Tarrasa', 2)," + "('Sabadell', 2)," + "('Alcoy', 11),"
				+ "('Leganés', 1)," + "('San Fernando', 3)," + "('Torrevieja', 11)," + "('Reus', 2),"
				+ "('Sanlúcar de Barrameda', 3)," + "('Benidorm', 11)," + "('Coslada', 1)," + "('Rubí', 2),"
				+ "('Elda', 11)," + "('San Vicente del Raspeig', 11);";

		datos += "INSERT INTO Categorias (NombreCategoria, DescripcionCategoria) VALUES "
				+ "('Electrónica', 'Dispositivos electrónicos y gadgets'),"
				+ "('Hogar', 'Artículos para el hogar y cocina')," + "('Ropa', 'Prendas de vestir y accesorios'),"
				+ "('Juguetes', 'Juguetes para niños y niñas')," + "('Deportes', 'Equipamiento y ropa deportiva');";

		datos += "INSERT INTO Clientes (Nombre, Apellido, Email, Telefono, Direccion, PoblacionID) VALUES "
				+ "('Juan', 'Pérez', 'juan.perez@email.com', '600123456', 'Calle Mayor 10', 1),"
				+ "('Lucía', 'García', 'lucia.garcia@email.com', '611234567', 'Avenida del Sol 22', 2),"
				+ "('Carlos', 'Martínez', 'carlos.martinez@email.com', NULL, 'Calle Luna 45', 3),"
				+ "('Ana', 'López', 'ana.lopez@email.com', '622345678', NULL, 4),"
				+ "('Pedro', 'Sánchez', 'pedro.sanchez@email.com', NULL, 'Calle Río 8', 5);";

		datos += "INSERT INTO Productos (NombreProducto, DescripcionProducto, PrecioUnitario, StockDisponible, CategoriaID) VALUES "
				+ "('Sartén antiadherente', 'Sartén de aluminio con recubrimiento antiadherente', 25.99, 100, 2),"
				+ "('Cafetera italiana', 'Cafetera de aluminio para 6 tazas', 19.50, 75, 2),"
				+ "('Batidora de mano', 'Batidora con múltiples velocidades y accesorios', 34.90, 60, 2),"
				+ "('Tostadora compacta', 'Tostadora para 2 rebanadas con bandeja recogemigas', 22.40, 80, 2),"
				+ "('Plancha de vapor', 'Plancha con suela de cerámica y función antigoteo', 29.99, 50, 3),"
				+ "('Ventilador de torre', 'Ventilador con oscilación y temporizador', 49.95, 40, 3),"
				+ "('Cuchillo de chef', 'Cuchillo profesional de acero inoxidable', 15.75, 120, 1),"
				+ "('Tabla de cortar', 'Tabla de bambú resistente al agua', 10.20, 150, 1),"
				+ "('Juego de copas', 'Set de 6 copas de cristal para vino', 27.30, 90, 1),"
				+ "('Mopa giratoria', 'Mopa con cubo y sistema de centrifugado', 33.80, 70, 4),"
				+ "('Aspiradora sin bolsa', 'Aspiradora ciclónica de alto rendimiento', 89.99, 30, 3),"
				+ "('Freidora de aire', 'Freidora sin aceite con control digital', 79.99, 45, 2),"
				+ "('Microondas digital', 'Microondas 700W con función grill', 109.00, 25, 2),"
				+ "('Hervidor eléctrico', 'Hervidor de acero inoxidable 1.7L', 23.99, 85, 2),"
				+ "('Cubertería de acero', 'Set de 24 piezas en estuche', 39.50, 65, 1),"
				+ "('Organizador de cocina', 'Estante extensible para platos y vasos', 18.75, 95, 1),"
				+ "('Licuadora profesional', 'Licuadora de frutas y verduras con motor potente', 59.90, 35, 2),"
				+ "('Cacerola con tapa', 'Cacerola de acero esmaltado con tapa de cristal', 31.60, 55, 2),"
				+ "('Papelera de pedal', 'Papelera metálica 30L con cierre suave', 24.99, 70, 4),"
				+ "('Escoba recogedora', 'Escoba con recogedor de mano y cepillo', 14.80, 110, 4),"
				+ "('Tendedero extensible', 'Tendedero plegable para interiores', 29.90, 45, 4),"
				+ "('Despertador digital', 'Despertador con radio y alarma dual', 21.90, 60, 3),"
				+ "('Almohada viscoelástica', 'Almohada ergonómica de espuma visco', 26.70, 85, 5),"
				+ "('Funda nórdica', 'Set de funda nórdica con dos fundas de almohada', 45.00, 50, 5),"
				+ "('Cortina opaca', 'Cortina térmica y aislante de luz', 19.99, 75, 5),"
				+ "('Juego de toallas', 'Set de 4 toallas de algodón', 35.25, 90, 5),"
				+ "('Espejo de baño', 'Espejo con iluminación LED', 59.80, 40, 5),"
				+ "('Soporte TV', 'Soporte de pared para televisores', 32.90, 30, 3),"
				+ "('Caja organizadora', 'Caja apilable con tapa de 30L', 12.60, 100, 4),"
				+ "('Dispensador de jabón', 'Dispensador automático de jabón líquido', 16.95, 65, 4);";

		JOptionPane.showMessageDialog(null, "Datos aleatorios introducidos con éxito");
		return datos;

	}

	public static String insertarDatos() {

		String[] opciones = { "Clientes", "Productos" };
		int resultado = JOptionPane.showOptionDialog(null, "Selecciona una opcion", "INSERTAR DATOS",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
		String SQL = "";
		String tablaSelecionada = "";

		if (resultado == 0) {
			tablaSelecionada = "Clientes";
			String nombre = JOptionPane.showInputDialog("Introduce el nombre ");
			String apellido = JOptionPane.showInputDialog("Introduce el apellido ");
			String email = JOptionPane.showInputDialog("Introduce el email ");
			String telefono = JOptionPane.showInputDialog("Introduce el teléfono ");
			String direccion = JOptionPane.showInputDialog("Introduce la dirección ");

			Random random = new Random();
			int idPoblacion = random.nextInt(5) + 1;

			SQL = "INSERT INTO Clientes (Nombre, Apellido, Email, Telefono, Direccion, PoblacionID) VALUES " + "('"
					+ nombre + "', '" + apellido + "', '" + email + "', '" + telefono + "', '" + direccion + "', "
					+ idPoblacion + ");";
		} else if (resultado == 1) {
			tablaSelecionada = "Productos";
			String nombre = JOptionPane.showInputDialog("Introduce el nombre (Ej: Taza | Móvil | Camiseta | joya ");
			String descipcion = JOptionPane.showInputDialog("Breve descripción del producto");
			String precio2 = JOptionPane.showInputDialog("Introduce el precio del producto (Ej: 29.99");
			double precio = Double.parseDouble(precio2);
			String stock2 = JOptionPane.showInputDialog("Introduce el número de unidades");
			double stock = Double.parseDouble(stock2);

			Random random = new Random();
			int idCategoria = random.nextInt(5) + 1;

			SQL = "INSERT INTO Productos (NombreProducto, DescripcionProducto, PrecioUnitario, StockDisponible, CategoriaID) VALUES "
					+ "('" + nombre + "', '" + descipcion + "', '" + precio + "', '" + stock + "', " + idCategoria
					+ ");";

		}
		JOptionPane.showMessageDialog(null, "Datos insertados con éxito en la tabla '" + tablaSelecionada + "'");
		return SQL;
	}

	public static String[] mostrarRegistros() {
		String[] mensaje = new String[2];
		String[] opciones = { "Clientes", "Productos" };
		int resultado = JOptionPane.showOptionDialog(null, "Selecciona una de las dos opciones", "Mostrar datos",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);

		if (resultado == 0) {
			mensaje[0] = "SELECT " + "c.ClienteID," + "c.Nombre," + "c.Apellido," + "c.Telefono," + "c.Direccion,"
					+ "c.PoblacionID," + " c.Email," + " p.NombrePoblacion," + " pr.NombreProvincia" + " FROM "
					+ "  Clientes c" + " JOIN " + "  Poblaciones p ON c.PoblacionID = p.PoblacionID" + " JOIN "
					+ "   Provincias pr ON p.ProvinciaID = pr.ProvinciaID;";
			mensaje[1] = "Cliente";

		} else if (resultado == 1) {
			mensaje[0] = "SELECT p.ProductoID, p.DescripcionProducto, p.CategoriaID, p.NombreProducto, p.PrecioUnitario, "
					+ "p.StockDisponible, c.NombreCategoria " + "FROM Productos p "
					+ "JOIN Categorias c ON p.CategoriaID = c.CategoriaID";
			mensaje[1] = "Producto";
		}

		return mensaje;
	}

	public static String actualizarPrecio() {
		String precio2 = JOptionPane.showInputDialog("Introduce el precio límite");
		double precio = Double.parseDouble(precio2);

		String mensaje = "UPDATE PRODUCTOS SET PrecioUnitario = " + precio + ";"; 
//		WHERE PrecioUnitario < 50;";
		JOptionPane.showMessageDialog(null, "Precios modificados con éxito");
		return mensaje;

	}

	public static String mostrarClientes(ResultSet rs) throws SQLException {
		String mensaje = "";
		ArrayList<String> nombreClientes = new ArrayList<>();
		ArrayList<Integer> idClientes = new ArrayList<>();

		while (rs.next()) {

			String nombreCliente = rs.getString("Nombre");
			nombreClientes.add(nombreCliente);

			int idCliente = rs.getInt("ClienteID");
			idClientes.add(idCliente);

		}
		String[] nombreClientesParaOpciones = new String[nombreClientes.size()];
		int[] idClientesParaOpciones = new int[idClientes.size()];

		for (int i = 0; i < nombreClientes.size(); i++) {
			idClientesParaOpciones[i] = idClientes.get(i);
			nombreClientesParaOpciones[i] = "ID " + idClientesParaOpciones[i] + " - " + nombreClientes.get(i);
		}

		String resultado = (String) JOptionPane.showInputDialog(null, "Selecciona un cliente", "Mostrar datos",
				JOptionPane.QUESTION_MESSAGE, null, nombreClientesParaOpciones, null);
		int clienteID = Integer.parseInt(resultado.substring(3, resultado.indexOf(" -")));

		mensaje = "DELETE FROM Clientes " + "WHERE ClienteID = " + (clienteID) + ";";

		JOptionPane.showMessageDialog(null, "Éxito eliminando al cliente");
		return mensaje;
	}

}