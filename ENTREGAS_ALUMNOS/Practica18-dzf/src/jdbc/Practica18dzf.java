package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Practica18dzf {

	public static void main(String[] args) {
		
		String [] opcionesMenu= {"CrearTablas","RellenadoAutomatico","InsertarNuevoCliente",
				 "ElminarRegistro","MostrarRegistro","ActualizarPrecios","Salir"};
		
		int seleccionMenu;
		
		do {
			
			seleccionMenu= JOptionPane.showOptionDialog(
	                null, 
	                "Seleccione la opcion de la Base de Datos:", 
	                "Base de Datos DZ", 
	                JOptionPane.DEFAULT_OPTION, 
	                JOptionPane.QUESTION_MESSAGE, 
	                null, 
	                opcionesMenu, 
	                opcionesMenu[0]);
			
			switch(seleccionMenu) {
			
			case 0 :
				
				crearTablas();
			break;
			case 1:
				insertarDatos();
				
			break;	
			case 2:
				insertarCliente();
				
			break;
			case 3 :
				
				eliminarRegistro();
			break;	
			case 4:
				mostrarClientesConPoblacionYProvincia();
				mostrarProductosConCategoria();
				
			break;	
			case 5 :
				actualizarPrecios();
				
			break;
			case 6:
				JOptionPane.showMessageDialog(null, "Adioss");
			break;
			default :
				JOptionPane.showMessageDialog(null, "Adioss");
			}
			
				
			
		}while(seleccionMenu !=6 && seleccionMenu != JOptionPane.CLOSED_OPTION);
		
		
		
	}		
	
	public static void crearTablas() {
		try {
			//1.conexion
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\\\Practica18.db");
			
			
			
			//2.Crear statement
			Statement miStatement = miConexion.createStatement();
			
			//3. sentencia SQL
			
			String tablaClientes ="CREATE TABLE IF NOT EXISTS Clientes23 (" +
                    "ClienteID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Nombre TEXT NOT NULL, " +
                    "Apellido TEXT NOT NULL, " +
                    "Email TEXT NOT NULL UNIQUE, " +
                    "Telefono TEXT, " +
                    "Direccion TEXT, " +
                    "PoblacionID INTEGER NOT NULL, " +
                    "FechaNacimiento TEXT, " + // Usamos TEXT para fechas en SQLite
                    "Activo INTEGER NOT NULL DEFAULT 1, " + // Usamos INTEGER (0/1) para BOOLEAN
                    "FechaCreacion TEXT DEFAULT (datetime('now')), " + // Usamos TEXT y datetime('now') para TIMESTAMP
                    "FOREIGN KEY (PoblacionID) REFERENCES Poblaciones(PoblacionID)" +
                    ")";
			
			
			
			
			
			String tablaPoblaciones="CREATE TABLE IF NOT EXISTS Poblaciones23 (" +
                    "PoblacionID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NombrePoblacion VARCHAR(100) NOT NULL, " +
                    "ProvinciaID INTEGER NOT NULL, " +
                    "FOREIGN KEY (ProvinciaID) REFERENCES Provincias23(ProvinciaID)" +
                    ")";
			
			
			
			
			
			
			String tablaProvincias="CREATE TABLE IF NOT EXISTS Provincias23 (" +
                    "ProvinciaID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NombreProvincia VARCHAR(100) NOT NULL" +
                    ")";
			
			
			
			
			String tablaProductos="CREATE TABLE IF NOT EXISTS Productos23 (" +
                    "ProductoID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NombreProducto VARCHAR(150) NOT NULL, " +
                    "DescripcionProducto TEXT, " +
                    "PrecioUnitario DECIMAL(10,2) NOT NULL, " +
                    "StockDisponible INTEGER NOT NULL DEFAULT 0, " +
                    "CategoriaID INTEGER NOT NULL, " +
                    "FechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (CategoriaID) REFERENCES Categorias23(CategoriaID)" +
                    ")";
			
			
			
			String tablaCategorias="CREATE TABLE IF NOT EXISTS Categorias23 (" +
					                 "CategoriaID INTEGER PRIMARY KEY AUTOINCREMENT," +
					                  "NombreCategoria VARCHAR(100) NOT NULL," +
					                  "DescripcionCategoria TEXT" +
					                  ")";
			
			
			
			
			//4. ejecutar sql
			//no me va la tabla categorias ni la tabla clientes
			
			
			
			
			
			
			
			
			
			
			miStatement.executeUpdate(tablaClientes);
			miStatement.executeUpdate(tablaPoblaciones);
			miStatement.executeUpdate(tablaProvincias);
			miStatement.executeUpdate(tablaProductos);
			miStatement.executeUpdate(tablaCategorias);
			System.out.println("registros insertados correctamente:" + tablaClientes);
			System.out.println("registros insertados correctamente:" + tablaPoblaciones);
			System.out.println("registros insertados correctamente:" + tablaProvincias);
			System.out.println("registros insertados correctamente:" + tablaProductos);
			System.out.println("registros insertados correctamente:" + tablaCategorias);
			
			miStatement.close();
			miConexion.close();
			System.out.println("Conexion Cerrada");
			
		} catch (Exception e) {
			System.out.println("error conexion");
			e.printStackTrace();
			
		}
	
		
	}

	
	public static void  insertarDatos() {
		

		try {
			//1.conexion
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");
			
			
			
			//2.Crear statement
			Statement miStatement = miConexion.createStatement();
			
			//3. sentencia SQL
			miConexion.setAutoCommit(false);
			
			

			miStatement.executeUpdate("INSERT INTO Clientes23 (Nombre, Apellido, Email, Telefono, Direccion, PoblacionID, FechaNacimiento, Activo,FechaCreacion)"+
			"VALUES ('Luis', 'Martínez', 'luis.martinez@example.com', '987654321', 'Avenida Sol', 2, '1985-03-22', 1,'now()'),"+
			"('María', 'López', 'maria.lopez@example.com', NULL, 'Plaza España 3', 3, '1992-07-30', 1,'now()'),"+
			"('Carlos', 'Rodríguez', 'carlos.rodriguez@example.com', '555123456', NULL, 4, '1988-11-10', 1,'now()'),"+
			"('Sofía', 'Pérez', 'sofia.perez@example.com', '666789123', 'Calle Luna 5', 5, NULL, 1,'now()'),"+
			"('Javier', 'Sánchez', 'javier.sanchez@example.com', NULL, 'Avenida Mar 6', 6, '1995-01-25', 1,'now()'),"+
			"('Laura', 'Gómez', 'laura.gomez@example.com', '777456123', 'Calle Río 7', 7, '1993-09-18', 0,'now()'),"+
			"('Pedro', 'Fernández', 'pedro.fernandez@example.com', '888321654', NULL, 8, '1980-12-05', 1,'now()'),"+
			"('Elena', 'Díaz', 'elena.diaz@example.com', NULL, 'Plaza Central 9', 9, '1998-04-12', 1,'now()'),"+
			"('Miguel', 'Ruiz', 'miguel.ruiz@example.com', '999654321', 'Calle Estrella 10', 10, '1987-06-20', 1,'now()')");
				
				
			miStatement.executeUpdate("INSERT INTO Poblaciones23 (NombrePoblacion, ProvinciaID)"+
					" VALUES 	('Alcalá de Henares', 1),"+
					"('Sabadell', 2),"+
					"('Gandía', 3),"+
					"('Jerez de la Frontera', 4),"+
					"('Marbella', 5),"+
					"('Getxo', 6),"+
					"('Teruel', 7),"+
					"('Elche', 8),"+
					"('Lucena', 9),"+
					"('Motril', 10)");
			
			
			miStatement.executeUpdate( "INSERT INTO Categorias23 (NombreCategoria, DescripcionCategoria)"+
					"VALUES ('Electrónica', 'Dispositivos electrónicos y gadgets'),"+
					"('Ropa', 'Ropa y accesorios de moda'),"+
					"('Hogar', 'Productos para el hogar'),"+
					"('Deportes', 'Equipos y ropa deportiva'),"+
					"('Juguetes', 'Juguetes para niños'),"+
					"('Libros', 'Libros de diversos géneros'),"+
					"('Alimentación', 'Productos alimenticios'),"+
					"('Joyería', 'Joyas y accesorios'),"+
					"('Belleza', 'Productos de cuidado personal'),"+
					"('Automóviles', 'Piezas y accesorios para autos')");
			
			
			miStatement.executeUpdate("INSERT INTO Provincias23 (NombreProvincia)"+
			"VALUES('Madrid'),"+
			"('Barcelona'),"+
			"('Valencia'),"+
			"('Sevilla'),"+
			"('Málaga'),"+
			"('Bilbao'),"+
			"('Zaragoza'),"+
			"('Alicante'),"+
			"('Córdoba'),"+
			"('Granada')");
			
			
			
			miStatement.executeUpdate("INSERT INTO Productos23 (NombreProducto, DescripcionProducto, PrecioUnitario, StockDisponible, CategoriaID, FechaCreacion)"+
			"VALUES('Smartphone X100', 'Teléfono inteligente de última generación', 599.99, 50, 1, 'now()'),"+
					"('Camiseta Deportiva', 'Camiseta para ejercicio', 29.99, 100, 2, 'now()'),"+
					"('Sofá Moderno', 'Sofá de diseño contemporáneo', 499.99, 20, 3, 'now()'),"+
					"('Balón de Fútbol', 'Balón oficial tamaño 5', 19.99, 150, 4, 'now()'),"+
					"('Muñeca Interactiva', 'Muñeca con funciones avanzadas', 39.99, 80, 5, 'now()'),"+
					"('Novela Aventura', 'Libro de aventuras emocionante', 14.99, 200, 6, 'now()'),"+
					"('Cereales Saludables', 'Cereales integrales sin azúcar', 4.99, 300, 7, 'now()'),"+
					"('Anillo de Plata', 'Anillo elegante de plata', 89.99, 30, 8, 'now()'),"+
					"('Perfume Floral', 'Perfume con notas florales', 59.99, 60, 9, 'now()'),"+
					"('Neumático 205/55R16', 'Neumático para auto', 79.99, 40, 10, 'now()')");
			
			
			
			
			//4. ejecutar sql
			System.out.println("datos insertados correctamente");		
				
			miConexion.commit();
			
			miStatement.close();
			miConexion.close();
			System.out.println("Conexion Cerrada");
		} catch (Exception e) {
			System.out.println("error conexion");
			e.printStackTrace();
			
		}
	
		
		
		

		
		

}

	public static void insertarCliente() {

		JOptionPane.showMessageDialog(null, "Introducimos los datos del cliente");
		String nombre = JOptionPane.showInputDialog(null, "Nombre: ");
		
		String apellido = JOptionPane.showInputDialog(null, "Apellido: ");
		
		String email = JOptionPane.showInputDialog(null, "Email: ");
		
		String telefono = JOptionPane.showInputDialog(null, "Telefono: ");
		
		String direccion = JOptionPane.showInputDialog(null, "Direccion: ");
		
		String poblacionID = JOptionPane.showInputDialog(null, "PoblacionID: ");
		
		String fechaNacimiento = JOptionPane.showInputDialog(null, "FechaNacimiento: ");
		
		String activo = JOptionPane.showInputDialog(null, "Debes introducir 1 | 0"+"\n"+"Activo: ");
		
		String fechaCreacion = JOptionPane.showInputDialog(null, "Debes introducir now()"+"\n"+"FechaCreacion: ");
		
		
	
		try {
			//1.conexion
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");
			
			
			
	
			
			String insertDatos = "INSERT INTO Clientes23 (Nombre, Apellido, Email, Telefono, Direccion, PoblacionID,FechaNacimiento,Activo,FechaCreacion) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement miPrepareStatement = miConexion.prepareStatement(insertDatos);
			
			//4. establecer valores 
			miPrepareStatement.setString(1, nombre);
			miPrepareStatement.setString(2, apellido);
			miPrepareStatement.setString(3, email);
			miPrepareStatement.setString(4, telefono);
			miPrepareStatement.setString(5, direccion);
			miPrepareStatement.setString(6, poblacionID);
			miPrepareStatement.setString(7, fechaNacimiento);
			miPrepareStatement.setString(8, activo);
			miPrepareStatement.setString(9, fechaCreacion);
		
			
			
			miPrepareStatement.execute();
			JOptionPane.showMessageDialog(null, miPrepareStatement);
			JOptionPane.showMessageDialog(null, "Se han introducido el cliente correctamente");
			miPrepareStatement.close();
			miConexion.close();
			
			System.out.println("Conexion Cerrada");
		} catch (Exception e) {
			System.out.println("error conexion");
			e.printStackTrace(); 
			
		}

		
		
	}
	public static void insertarPoblacion() {
		JOptionPane.showMessageDialog(null, "Introducimos los datos de la Poblacion");
		
		String nombrePoblacion = JOptionPane.showInputDialog(null, "Nombre Poblacion: ");
		String provinciaID = JOptionPane.showInputDialog(null, "Provincia: ");

		
		try {
			//1.conexion
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");
			
			
			
	
			
			String insertDatos = "INSERT INTO Poblaciones23 (NombrePoblacion,ProvinciaID) " +
	                "VALUES (?, ?)";
			
			PreparedStatement miPrepareStatement = miConexion.prepareStatement(insertDatos);
			
			//4. establecer valores 
			miPrepareStatement.setString(1, nombrePoblacion);
			miPrepareStatement.setString(2, provinciaID);
		
		
			
			
			miPrepareStatement.execute();
			JOptionPane.showMessageDialog(null, miPrepareStatement);
			JOptionPane.showMessageDialog(null, "Se ha introducido la poblacion correctamente");
			miPrepareStatement.close();
			miConexion.close();
			
			System.out.println("Conexion Cerrada");
		} catch (Exception e) {
			System.out.println("error conexion");
			e.printStackTrace(); 
			
		}

		
		

	}
	
	public static void insertarProvincia() {
	JOptionPane.showMessageDialog(null, "Introducimos los datos de la Provincia");
		
		String nombreProvincia = JOptionPane.showInputDialog(null, "Nombre Provincia: ");
		
		
		try {
			//1.conexion
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");
			
			
			
	
			
			String insertDatos = "INSERT INTO Provincias23 (NombreProvincia " +
	                "VALUES (?)";
			
			PreparedStatement miPrepareStatement = miConexion.prepareStatement(insertDatos);
			
			//4. establecer valores 
			miPrepareStatement.setString(1, nombreProvincia);
			
		
		
			
			
			miPrepareStatement.execute();
			JOptionPane.showMessageDialog(null, miPrepareStatement);
			JOptionPane.showMessageDialog(null, "Se ha introducido la provincia correctamente");
			miPrepareStatement.close();
			miConexion.close();
			
			System.out.println("Conexion Cerrada");
		} catch (Exception e) {
			System.out.println("error conexion");
			e.printStackTrace(); 
			
		}

		
	}
	
	
	public static void insertarProducto() {

		JOptionPane.showMessageDialog(null, "Introducimos los datos del Producto");
		
		String nombreProducto = JOptionPane.showInputDialog(null, "Nombre Producto: ");
		String descripcionProducto = JOptionPane.showInputDialog(null, "Descripcion Producto: ");
		String precioUnitario = JOptionPane.showInputDialog(null, "Precio Producto: ");
		String stockDisponible = JOptionPane.showInputDialog(null, "Stock Disponible Producto: ");
		String categoriaID = JOptionPane.showInputDialog(null, "Categoria ID Producto: ");
		String fechaCreacion = JOptionPane.showInputDialog(null, "Fecha Creacion Producto: ");

		try {
			//1.conexion
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");
			
			
			
	
			
			String insertDatos = "INSERT INTO Productos23 (NombreProducto,DescripcionProducto,PrecioUnitario,StockDisponible,CategoriaID,FechaCreacion) " +
	                "VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement miPrepareStatement = miConexion.prepareStatement(insertDatos);
			
			//4. establecer valores 
			miPrepareStatement.setString(1, nombreProducto);
			miPrepareStatement.setString(2, descripcionProducto);		
			miPrepareStatement.setString(3, precioUnitario);
			miPrepareStatement.setString(4, stockDisponible);	
			miPrepareStatement.setString(5, categoriaID);
			miPrepareStatement.setString(6, fechaCreacion);	
			
			
			
			
			
			
			miPrepareStatement.execute();
			JOptionPane.showMessageDialog(null, miPrepareStatement);
			JOptionPane.showMessageDialog(null, "Se ha introducido el producto correctamente");
			miPrepareStatement.close();
			miConexion.close();
			
			System.out.println("Conexion Cerrada");
		} catch (Exception e) {
			System.out.println("error conexion");
			e.printStackTrace(); 
			
		}
		
	}
	
	
	
public static void insertarCategoria() {

		JOptionPane.showMessageDialog(null, "Introducimos los datos de la Categoria");
		
		String nombreCategoria = JOptionPane.showInputDialog(null, "Nombre Categoria: ");
		String descripcionCategoria = JOptionPane.showInputDialog(null, "Descripcion Categoria: ");
		

		try {
			//1.conexion
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");
			
			
			
	
			
			String insertDatos = "INSERT INTO Categorias23 (NombreCategoria,DescripcionCategoria) " +
	                "VALUES (?, ?)";
			
			PreparedStatement miPrepareStatement = miConexion.prepareStatement(insertDatos);
			
			//4. establecer valores 
			miPrepareStatement.setString(1, nombreCategoria);
			miPrepareStatement.setString(2, descripcionCategoria);		
			
			
			
			
			
			
			miPrepareStatement.execute();
			JOptionPane.showMessageDialog(null, miPrepareStatement);
			JOptionPane.showMessageDialog(null, "Se ha introducido la categoria correctamente");
			miPrepareStatement.close();
			miConexion.close();
			
			System.out.println("Conexion Cerrada");
		} catch (Exception e) {
			System.out.println("error conexion");
			e.printStackTrace(); 
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	public static void eliminarRegistro() {
		String opcionesRegistro[]= {"Clientes","Provincias","Poblaciones",
				"Productos","Categorias","Volver"};
	
int seleccionMenuRegistro ;
do {
	
	seleccionMenuRegistro= JOptionPane.showOptionDialog(
            null, 
            "Seleccione la opcion que desea eliminar:", 
            "Base de Datos DZ", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opcionesRegistro, 
            opcionesRegistro[0]);
	
	
	if (seleccionMenuRegistro == 5 || seleccionMenuRegistro == JOptionPane.CLOSED_OPTION) {
        JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
        return;
	}
	String id = JOptionPane.showInputDialog(null, "Ingrese el ID del registro a eliminar:");
	try (Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db")) {
        miConexion.setAutoCommit(false); 

        String tabla = "";
        String idColumna = "";
        switch (seleccionMenuRegistro) {
            case 0:
                tabla = "Clientes23";
                idColumna = "ClienteID";
                break;
            case 1:
                tabla = "Provincias23";
                idColumna = "ProvinciaID";
                break;
            case 2:
                tabla = "Poblaciones23";
                idColumna = "PoblacionID";
                break;
            case 3:
                tabla = "Productos23";
                idColumna = "ProductoID";
                break;
            case 4:
                tabla = "Categorias23";
                idColumna = "CategoriaID";
                break;
        }

        String sql = "DELETE FROM " + tabla + " WHERE " + idColumna + " = ?";
        
        
        try (PreparedStatement miPrepareStatement = miConexion.prepareStatement(sql)) {
        	miPrepareStatement.setInt(1, Integer.parseInt(id));
        	
            int filasAfectadas = miPrepareStatement.executeUpdate();

            if (filasAfectadas > 0) {
                miConexion.commit();
                JOptionPane.showMessageDialog(null, "Registro eliminado de " + tabla + ".");
            } else {
                miConexion.rollback();
                JOptionPane.showMessageDialog(null, "No se encontró el ID " + id + " en " + tabla + ".");
            }
        } catch (SQLException e) {
            miConexion.rollback();
            JOptionPane.showMessageDialog(null, "Error: No se pudo eliminar. " + 
                    "El registro puede estar en uso.");
        } catch (NumberFormatException e) {
            miConexion.rollback();
            JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
  }

	
	
}while(seleccionMenuRegistro !=5 && seleccionMenuRegistro != JOptionPane.CLOSED_OPTION);



		
	}
	
	public static void mostrarClientesConPoblacionYProvincia() {
	    try {
	        Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");

	        String sql = "SELECT c.ClienteID, c.Nombre, c.Apellido, c.Email, p.NombrePoblacion, pr.NombreProvincia " +
	                     "FROM Clientes23 c " +
	                     "JOIN Poblaciones23 p ON c.PoblacionID = p.PoblacionID " +
	                     "JOIN Provincias23 pr ON p.ProvinciaID = pr.ProvinciaID";

	        PreparedStatement miPrepareStatement = miConexion.prepareStatement(sql);
	        ResultSet statement = miPrepareStatement.executeQuery();

	        StringBuilder sBuilder = new StringBuilder();
	        sBuilder.append("ID | Nombre | Apellido | Email | Población | Provincia\n");
	        sBuilder.append("-------------------------------------------------------\n");

	        while (statement.next()) {
	        	 sBuilder.append(statement.getInt("ClienteID")).append(" | ")
                 .append(statement.getString("Nombre")).append(" | ")
                 .append(statement.getString("Apellido")).append(" | ")
                 .append(statement.getString("Email")).append(" | ")
                 .append(statement.getString("NombrePoblacion")).append(" | ")
                 .append(statement.getString("NombreProvincia")).append("\n");
	        }

	        JOptionPane.showMessageDialog(null, sBuilder.toString());

	        statement.close();
	        miPrepareStatement.close();
	        miConexion.close();
	    } catch (Exception e) {
	        System.out.println("Error al mostrar clientes");
	        e.printStackTrace();
	    }
	}
	public static void mostrarProductosConCategoria() {
	    String sql = """
	        SELECT p.ProductoID, p.NombreProducto, p.PrecioUnitario, c.NombreCategoria
	        FROM Productos23 p
	        JOIN Categorias23 c ON p.CategoriaID = c.CategoriaID
	    """;

	    try (Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");
	         PreparedStatement conexion = miConexion.prepareStatement(sql);
	         ResultSet result = conexion.executeQuery()) {

	        StringBuilder sBuilder = new StringBuilder();
	        sBuilder.append("ID | Producto | Precio | Categoría\n");
	        sBuilder.append("----------------------------------\n");

	        while (result.next()) {
	        	 sBuilder.append(result.getInt("ProductoID")).append(" | ")
                 .append(result.getString("NombreProducto")).append(" | ")
                 .append(result.getDouble("PrecioUnitario")).append("€ | ")
                 .append(result.getString("NombreCategoria")).append("\n");
	        }

	        JOptionPane.showMessageDialog(null, sBuilder.toString(), "Productos", JOptionPane.INFORMATION_MESSAGE);

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al mostrar productos: " + e.getMessage());
    }
	    
	 
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}

	public static void actualizarPrecios() {
	    try {
	      
	        String input = JOptionPane.showInputDialog(null, "Introduce el precio límite (ej: 50.00):");
	        double limite = Double.parseDouble(input);

	       
	        Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\Practica18.db");

	    
	        String sql = "UPDATE Productos23 SET PrecioUnitario = PrecioUnitario * 1.10 WHERE PrecioUnitario < ?";

	        PreparedStatement miPrepareStatement = miConexion.prepareStatement(sql);
	        miPrepareStatement.setDouble(1, limite);

	     
	        int productosAfectados = miPrepareStatement.executeUpdate();

	      
	        JOptionPane.showMessageDialog(null, "Se actualizaron " + productosAfectados + " productos con un 10%.");

	        
	        miPrepareStatement.close();
	        miConexion.close();
	        System.out.println("Conexión cerrada");
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Debes introducir un número válido para el precio.");
	    } catch (Exception e) {
	        System.out.println("Error al actualizar precios");
	        e.printStackTrace();
	    }
	}
	
	
	
}




