package iag.practica18;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Practica18App {
	final static int TABLA_CLIENTES = 0;
	final static int TABLA_POBLACION = 1;
	final static int TABLA_PROVINCIAS = 2;
	final static int TABLA_PRODUCTOS = 3;
	final static int TABLA_CATEGORIAS = 4;
	final static String URL = "jdbc:sqlite:E:\\practica18.db";

	public static void main(String[] args) {

		int opc = 0;
		final String[] OPCIONES_MENU = { "Crear tablas", "Rellenar datos aleatorios", " Insertar nuevo registro",
				"Mostrar registros", "Eliminar registro", "Actualizar precios de productos", "Salir" };
		do {
			opc = JOptionPane.showOptionDialog(null, "Elige una opción: ", "Menú principal", JOptionPane.DEFAULT_OPTION,
					JOptionPane.DEFAULT_OPTION, null, OPCIONES_MENU, OPCIONES_MENU[0]);

			switch (opc) {
			case 0:
				String[] opcionesCrearTablas = { "Crear tabla clientes", "Crear tabla población",
						"Crear tabla provincias", "Crear tabla productos", "Crear tabla categorías" };
				int opcionTabla = JOptionPane.showOptionDialog(null, "¿Que tabla quieres crear?", "Crear tablas",
						JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, opcionesCrearTablas,
						opcionesCrearTablas[0]);
				crearTablas(opcionTabla);
				break;
			case 1:
				rellenarDatos();
				break;
			case 2:
				String[] opcionesCrearRegistro = { "Tabla clientes", "Tabla población", "Tabla provincias",
						"Tabla productos", "Tabla categorías" };
				opcionTabla = JOptionPane.showOptionDialog(null, "¿De que tabla quieres crear el registro?",
						"Insertar nuevo registro", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null,
						opcionesCrearRegistro, opcionesCrearRegistro[0]);
				crearRegistro(opcionTabla);
				break;
			case 3:
				String[] opcionesMostrarRegistros = { "Mostrar clientes con su población y provincia",
						"Mostrar productos con su categoría" };
				int opcion = JOptionPane.showOptionDialog(null, "Elige una opción", "Mostrar registros",
						JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, opcionesMostrarRegistros,
						opcionesMostrarRegistros[0]);
				mostrarRegistro(opcion);
				break;
			case 4:
				int idCliente = Integer.parseInt(
						JOptionPane.showInputDialog(null, "Introduce el ID del cliente que quieres eliminar: ",
								"Eliminar Registro", JOptionPane.QUESTION_MESSAGE));
				eliminarRegistro(idCliente);
				break;
			case 5:
				double precioLimite = Double.parseDouble(JOptionPane.showInputDialog(null,
						"Introduce el precio límite: ", "Actualizar precios", JOptionPane.QUESTION_MESSAGE));
				actualizarPrecios(precioLimite);
				break;
			case 6:
				System.exit(0);
				break;

			}

		} while (opc != -1 && opc != 6);

	}

	public static String crearTablas(int tabla) {
		String sentencia = "";
		if (tabla == TABLA_CLIENTES) {
			sentencia = "CREATE TABLE IF NOT EXISTS Clientes (" + "    ClienteID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "    Nombre VARCHAR(100) NOT NULL," + "    Apellido VARCHAR(100) NOT NULL,"
					+ "    Email VARCHAR(255) NOT NULL UNIQUE," + "    Telefono VARCHAR(15)," + "    Direccion TEXT,"
					+ "    PoblacionID INTEGER NOT NULL," + "    FechaNacimiento DATE,"
					+ "    Activo BOOLEAN NOT NULL DEFAULT TRUE,"
					+ "    FechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "    FOREIGN KEY (PoblacionID) REFERENCES Poblaciones(PoblacionID)" + ");";
		} else if (tabla == TABLA_POBLACION) {
			sentencia = "CREATE TABLE IF NOT EXISTS Poblaciones ("
					+ "    PoblacionID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "    NombrePoblacion VARCHAR(100) NOT NULL," + "    ProvinciaID INTEGER NOT NULL,"
					+ "    FOREIGN KEY (ProvinciaID) REFERENCES Provincias(ProvinciaID)" + ");";
		} else if (tabla == TABLA_PROVINCIAS) {
			sentencia = "CREATE TABLE IF NOT EXISTS Provincias (" + "    ProvinciaID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "    NombreProvincia VARCHAR(100) NOT NULL" + ");";
		} else if (tabla == TABLA_PRODUCTOS) {
			sentencia = "CREATE TABLE IF NOT EXISTS Productos (" + "    ProductoID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "    NombreProducto VARCHAR(150) NOT NULL," + "    DescripcionProducto TEXT,"
					+ "    PrecioUnitario DECIMAL(10,2) NOT NULL," + "    StockDisponible INTEGER NOT NULL DEFAULT 0,"
					+ "    CategoriaID INTEGER NOT NULL,"
					+ "    FechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "    FOREIGN KEY (CategoriaID) REFERENCES Categorias(CategoriaID)" + ");";
		} else if (tabla == TABLA_CATEGORIAS) {
			sentencia = "CREATE TABLE IF NOT EXISTS Categorias (" + "    CategoriaID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "    NombreCategoria VARCHAR(100) NOT NULL," + "    DescripcionCategoria TEXT" + ");";
		}
		Connection c = null;
		Statement st = null;
		try {
			c = DriverManager.getConnection(URL);

			st = c.createStatement();

			st.executeUpdate(sentencia);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return sentencia;
	}

	public static void rellenarDatos() {
		String SQLclientes = "INSERT INTO Clientes (Nombre, Apellido, Email, Telefono, Direccion, PoblacionID, FechaNacimiento, Activo, FechaCreacion) VALUES "
				+ "('Laura', 'Martínez', 'laura@mail.com', '600123456', 'Calle Sol 5', 1, '1990-03-14', TRUE, CURRENT_TIMESTAMP), "
				+ "('Carlos', 'Gómez', 'carlos@mail.com', NULL, NULL, 2, NULL, FALSE, CURRENT_TIMESTAMP),"
				+ "('Ana', 'López', 'ana@mail.com', '622334455', 'Av. Norte 12', 3, '1985-07-20', TRUE, CURRENT_TIMESTAMP),"
				+ "('Mario', 'Fernández', 'mario@mail.com', NULL, 'Calle Luna 3', 4, '2000-11-09', TRUE, CURRENT_TIMESTAMP),"
				+ "('Sofía', 'Ruiz', 'sofia@mail.com', '645998877', NULL, 5, NULL, FALSE, CURRENT_TIMESTAMP)";
		String SQLpoblaciones = "INSERT INTO Poblaciones (NombrePoblacion, ProvinciaID) VALUES" + "('Torrent', 1),"
				+ "('Dos Hermanas', 2)," + "('Utebo', 3)," + "('San Fernando', 4)," + "('Béjar', 5)";
		String SQLprovincias = "INSERT INTO Provincias (NombreProvincia) VALUES" + "('Valencia')," + "('Sevilla'),"
				+ "('Zaragoza')," + "('Cádiz')," + "('Salamanca')";
		String SQLproductos = "INSERT INTO Productos (NombreProducto, DescripcionProducto, PrecioUnitario, StockDisponible, CategoriaID, FechaCreacion) VALUES"
				+ "('Auriculares Bluetooth', 'Auriculares inalámbricos con cancelación de ruido', 29.99, 50, 1, CURRENT_TIMESTAMP),"
				+ "('Sofá 3 plazas', 'Sofá cómodo con tela antimanchas', 399.00, 10, 2, CURRENT_TIMESTAMP),"
				+ "('Chaqueta deportiva', 'Chaqueta impermeable con capucha', 59.95, 25, 3, CURRENT_TIMESTAMP),"
				+ "('Set LEGO Star Wars', 'Construcción temática espacial', 89.99, 15, 4, CURRENT_TIMESTAMP),"
				+ "('Libro de cocina vegana', 'Recetas saludables y sabrosas', 18.50, 40, 5, CURRENT_TIMESTAMP)";
		String SQLcategorias = "INSERT INTO Categorias (NombreCategoria, DescripcionCategoria) VALUES"
				+ "('Electrónica', 'Productos electrónicos y tecnología'),"
				+ "('Hogar', 'Artículos para el hogar y decoración')," + "('Ropa', 'Vestimenta para todas las edades'),"
				+ "('Juguetes', 'Juguetes educativos y de entretenimiento'),"
				+ "('Libros', 'Libros de diversos géneros')";
		Connection c = null;
		Statement st = null;
		try {
			c = DriverManager.getConnection(URL);
			st = c.createStatement();
			st.executeUpdate(SQLclientes);
			st.executeUpdate(SQLpoblaciones);
			st.executeUpdate(SQLprovincias);
			st.executeUpdate(SQLproductos);
			st.executeUpdate(SQLcategorias);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static boolean crearRegistro(int tabla) {
		String sentencia = "";
		if (tabla == TABLA_CLIENTES) {
			String nombre, apellido, email, telefono, direccion, fechaNacimiento;
			int poblacionID;
			nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del cliente", "Insertar nuevo cliente",
					JOptionPane.QUESTION_MESSAGE);
			apellido = JOptionPane.showInputDialog(null, "Introduce el apellido del cliente", "Insertar nuevo cliente",
					JOptionPane.QUESTION_MESSAGE);
			email = JOptionPane.showInputDialog(null, "Introduce el email del cliente", "Insertar nuevo cliente",
					JOptionPane.QUESTION_MESSAGE);
			telefono = JOptionPane.showInputDialog(null, "Introduce el teléfono del cliente", "Insertar nuevo cliente",
					JOptionPane.QUESTION_MESSAGE);
			direccion = JOptionPane.showInputDialog(null, "Introduce la direccion del cliente",
					"Insertar nuevo cliente", JOptionPane.QUESTION_MESSAGE);
			poblacionID = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el poblacionID del cliente",
					"Insertar nuevo cliente", JOptionPane.QUESTION_MESSAGE));
			fechaNacimiento = JOptionPane.showInputDialog(null, "Introduce la fecha de nacimiento del cliente",
					"Insertar nuevo cliente", JOptionPane.QUESTION_MESSAGE);
			java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaNacimiento);
			sentencia = "INSERT INTO clientes (Nombre, Apellido, Email, Telefono, Direccion, PoblacionID, FechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try {
				Connection c = DriverManager.getConnection(URL);
				PreparedStatement pst = c.prepareStatement(sentencia);
				pst.setString(1, nombre);
				pst.setString(2, apellido);
				pst.setString(3, email);
				pst.setString(4, telefono);
				pst.setString(5, direccion);
				pst.setInt(6, poblacionID);
				pst.setDate(7, fechaSQL);

				pst.executeUpdate();

				pst.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (tabla == TABLA_POBLACION) {
			String nombrePoblacion;
			int provinciaID;
			nombrePoblacion = JOptionPane.showInputDialog(null, "Introduce el nombre de la población",
					"Insertar nueva población", JOptionPane.QUESTION_MESSAGE);
			provinciaID = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el apellido del cliente",
					"Insertar nueva población", JOptionPane.QUESTION_MESSAGE));
			sentencia = "INSERT INTO Poblaciones (NombrePoblacion, provinciaID) VALUES (?, ?)";
			try {
				Connection c = DriverManager.getConnection(URL);
				PreparedStatement pst = c.prepareStatement(sentencia);
				pst.setString(1, nombrePoblacion);
				pst.setInt(2, provinciaID);

				pst.executeUpdate();

				pst.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (tabla == TABLA_PROVINCIAS) {
			String nombreProvincia;
			nombreProvincia = JOptionPane.showInputDialog(null, "Introduce el nombre de la provincia",
					"Insertar nueva provincia", JOptionPane.QUESTION_MESSAGE);
			sentencia = "INSERT INTO provincias (NombreProvincia) VALUES (?)";
			try {
				Connection c = DriverManager.getConnection(URL);
				PreparedStatement pst = c.prepareStatement(sentencia);
				pst.setString(1, nombreProvincia);

				pst.executeUpdate();

				pst.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (tabla == TABLA_PRODUCTOS) {
			String nombreProducto, descripcionProducto;
			int stockDisponible, precioUnitario, categoriaID;
			nombreProducto = JOptionPane.showInputDialog(null, "Introduce el nombre del producto",
					"Insertar nuevo producto", JOptionPane.QUESTION_MESSAGE);
			descripcionProducto = JOptionPane.showInputDialog(null, "Introduce la descripción del producto",
					"Insertar nuevo producto", JOptionPane.QUESTION_MESSAGE);
			precioUnitario = Integer
					.parseInt(JOptionPane.showInputDialog(null, "Introduce el precio unitario del producto",
							"Insertar nuevo producto", JOptionPane.QUESTION_MESSAGE));
			stockDisponible = Integer
					.parseInt(JOptionPane.showInputDialog(null, "Introduce el stock disponible del producto",
							"Insertar nuevo producto", JOptionPane.QUESTION_MESSAGE));
			categoriaID = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce la categoría del producto",
					"Insertar nuevo producto", JOptionPane.QUESTION_MESSAGE));
			sentencia = "INSERT INTO Productos (NombreProducto, DescripcionProducto, PrecioUnitario, StockDisponible, categoriaID) VALUES (?, ?, ?, ?, ?)";
			try {
				Connection c = DriverManager.getConnection(URL);
				PreparedStatement pst = c.prepareStatement(sentencia);
				pst.setString(1, nombreProducto);
				pst.setString(2, descripcionProducto);
				pst.setInt(3, precioUnitario);
				pst.setInt(4, stockDisponible);
				pst.setInt(5, categoriaID);

				pst.executeUpdate();

				pst.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (tabla == TABLA_CATEGORIAS) {
			String nombreCategoria, descripcionCategoria;
			nombreCategoria = JOptionPane.showInputDialog(null, "Introduce el nombre de la categoría",
					"Insertar nueva categoría", JOptionPane.QUESTION_MESSAGE);
			descripcionCategoria = JOptionPane.showInputDialog(null, "Introduce la descripción de la categoría",
					"Insertar nueva categoría", JOptionPane.QUESTION_MESSAGE);
			sentencia = "INSERT INTO categorias (NombreCategoria, DescripcionCategoria) VALUES (?, ?)";
			try {
				Connection c = DriverManager.getConnection(URL);
				PreparedStatement pst = c.prepareStatement(sentencia);
				pst.setString(1, nombreCategoria);
				pst.setString(2, descripcionCategoria);

				pst.executeUpdate();

				pst.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void eliminarRegistro(int id) {
		String sentencia = "DELETE FROM Clientes WHERE ClienteID = " + id + ";";
		Connection c = null;
		Statement st = null;
		try {

			c = DriverManager.getConnection(URL);
			c.setAutoCommit(false);
			st = c.createStatement();

			int filasAfectadas = st.executeUpdate(sentencia);

			if (filasAfectadas >= 1) {
				c.commit();
				JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Eliminar Registro",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				c.rollback();
				JOptionPane.showMessageDialog(null, "Registro no encontrado", "Eliminar Registro",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void mostrarRegistro(int opc) {
		if (opc == 0) {
			Connection c = null;
			Statement st = null;
			ResultSet rs = null;
			String SQL = "Select Clientes.ClienteID, Clientes.Nombre, Clientes.Apellido, Poblaciones.PoblacionID, Poblaciones.NombrePoblacion,Provincias.ProvinciaID, Provincias.NombreProvincia "
					+ "FROM Clientes JOIN Poblaciones ON Clientes.PoblacionID = Poblaciones.PoblacionID JOIN Provincias ON Provincias.ProvinciaID = Poblaciones.ProvinciaID;";
			try {
				c = DriverManager.getConnection(URL);
				st = c.createStatement();
				rs = st.executeQuery(SQL);

				System.out.printf("%-10s %-15s %-15s %-15s %-25s %-15s %-25s%n", "ClienteID", "Nombre", "Apellido",
						"PoblacionID", "NombrePoblacion", "ProvinciaID", "NombreProvincia");

				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");

				// Mostrar resultados
				while (rs.next()) {
					int clienteID = rs.getInt("ClienteID");
					String nombre = rs.getString("Nombre");
					String apellido = rs.getString("Apellido");
					int poblacionID = rs.getInt("PoblacionID");
					String nombrePoblacion = rs.getString("NombrePoblacion");
					int provinciaID = rs.getInt("ProvinciaID");
					String nombreProvincia = rs.getString("NombreProvincia");

					System.out.printf("%-10d %-15s %-15s %-15d %-25s %-15d %-25s%n", clienteID, nombre, apellido,
							poblacionID, nombrePoblacion, provinciaID, nombreProvincia);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					st.close();
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		} else if (opc == 1) {
			Connection c = null;
			Statement st = null;
			ResultSet rs = null;
			String SQL = "Select Productos.ProductoID, Productos.NombreProducto, Categorias.CategoriaID, Categorias.NombreCategoria "
					+ "FROM Productos JOIN Categorias ON Productos.CategoriaID = Categorias.CategoriaID;";
			try {
				c = DriverManager.getConnection(URL);
				st = c.createStatement();
				rs = st.executeQuery(SQL);

				System.out.printf("%-12s %-40s %-15s %-25s%n", "ProductoID", "NombreProducto", "CategoriaID",
						"NombreCategoria");
				System.out.println(
						"------------------------------------------------------------------------------------------");

				// Mostrar resultados
				while (rs.next()) {
					int productoID = rs.getInt("ProductoID");
					String nombreProducto = rs.getString("NombreProducto");
					int categoriaID = rs.getInt("CategoriaID");
					String nombreCategoria = rs.getString("NombreCategoria");

					System.out.printf("%-12d %-40s %-15d %-25s%n", productoID, nombreProducto, categoriaID,
							nombreCategoria);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					st.close();
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public static void actualizarPrecios(double precioLimite) {
		String SQL = "UPDATE Productos SET PrecioUnitario = PrecioUnitario * 1.1 WHERE PrecioUnitario < " + precioLimite
				+ ";";

		Connection c = null;
		Statement st = null;
		try {
			c = DriverManager.getConnection(URL);
			st = c.createStatement();

			int columnasAfectadas = st.executeUpdate(SQL);

			JOptionPane.showMessageDialog(null, "Columnas afectadas: " + columnasAfectadas);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
