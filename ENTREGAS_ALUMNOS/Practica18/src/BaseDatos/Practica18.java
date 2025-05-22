package BaseDatos;

import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Practica18 {
    public static void main(String[] args) {


        String url = "jdbc:sqlite:E:\\Practica18\\Practica18.db";

        String[] opciones = { 
            "Crear tablas", 
            "Rellenar datos aleatorios", 
            "Insertar nuevo Dato", 
            "Mostrar registros", 
            "Eliminar registro", 
            "Actualizar precios de productos", 
            "Salir" 
        };

        String mensajeIntroduccion = "Bienvenido a la base de Datos \n"
                + "-----------------------------------------\n"
                + "¿Qué quieres hacer?";

        try (Connection miConexion = DriverManager.getConnection(url)) {

            miConexion.setAutoCommit(false);

            String opcion = "";
            do {
                int opcionSeleccionada = JOptionPane.showOptionDialog(
                        null,                           
                        mensajeIntroduccion,            
                        "Selección de Opciones",         
                        JOptionPane.DEFAULT_OPTION,      
                        JOptionPane.QUESTION_MESSAGE,   
                        new ImageIcon("img/BaseDatos.png"), 
                        opciones,                       
                        opciones[0]);                 

                opcion = opciones[opcionSeleccionada];

                switch (opcion) {
                    case "Crear tablas": {
                        String[] tablas = { "Clientes", "población", "Provincias", "Productos", "Categorías" };
                        String seleccion = (String) JOptionPane.showInputDialog(null, "¿Qué tabla quieres crear?",
                                "Crear Tablas", JOptionPane.QUESTION_MESSAGE,new ImageIcon("img/BaseDatos.png"), tablas, tablas[0]);

                        String consulta = "";
                        switch (seleccion) {
                            case "Clientes":
                                consulta = "CREATE TABLE IF NOT EXISTS Clientes (\n"
                                        + "    ClienteID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                                        + "    Nombre VARCHAR(100) NOT NULL,\n"
                                        + "    Apellido VARCHAR(100) NOT NULL,\n"
                                        + "    Email VARCHAR(255) UNIQUE NOT NULL,\n"
                                        + "    Telefono VARCHAR(15),\n"
                                        + "    Direccion TEXT,\n"
                                        + "    PoblacionID INTEGER NOT NULL,\n"
                                        + "    FechaNacimiento DATE,\n"
                                        + "    Activo BOOLEAN NOT NULL DEFAULT TRUE,\n"
                                        + "    FechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
                                        + "    FOREIGN KEY (PoblacionID) REFERENCES población (PoblacionID)\n"
                                        + ");";
                                break;
                            case "población":
                                consulta = "CREATE TABLE IF NOT EXISTS población (\n"
                                        + "    PoblacionID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                                        + "    NombrePoblacion VARCHAR(100) NOT NULL,\n"
                                        + "    ProvinciaID INTEGER NOT NULL,\n"
                                        + "    FOREIGN KEY (ProvinciaID) REFERENCES Provincias (ProvinciaID)\n"
                                        + ");";
                                break;
                            case "Provincias":
                                consulta = "CREATE TABLE IF NOT EXISTS Provincias (\n"
                                        + "    ProvinciaID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                                        + "    NombreProvincia VARCHAR(100) NOT NULL\n"
                                        + ");";
                                break;
                            case "Productos":
                                consulta = "CREATE TABLE IF NOT EXISTS Productos (\n"
                                        + "    ProductoID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                                        + "    NombreProducto VARCHAR(150) NOT NULL,\n"
                                        + "    DescripcionProducto TEXT,\n"
                                        + "    PrecioUnitario DECIMAL(10,2) NOT NULL,\n"
                                        + "    StockDisponible INTEGER NOT NULL DEFAULT 0,\n"
                                        + "    CategoriaID INTEGER NOT NULL,\n"
                                        + "    FechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
                                        + "    FOREIGN KEY (CategoriaID) REFERENCES Categorias (CategoriaID)\n"
                                        + ");";
                                break;
                            case "Categorías":
                                consulta = "CREATE TABLE IF NOT EXISTS Categorias (\n"
                                        + "    CategoriaID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                                        + "    NombreCategoria VARCHAR(100) NOT NULL,\n"
                                        + "    DescripcionCategoria TEXT\n"
                                        + ");";
                                break;
                        }

                        try (Statement st = miConexion.createStatement()) {
                            st.execute(consulta);
                            miConexion.commit();
                            JOptionPane.showMessageDialog(null, seleccion + " creada (o verificada) correctamente.");
                        } catch (Exception e) {
                            try {
                                miConexion.rollback();
                                JOptionPane.showMessageDialog(null,"Error a la hora de gestionar la tabla");

                            } catch (SQLException ex) {
                            }
                            JOptionPane.showMessageDialog(null, "Error al crear la tabla " + seleccion + ": " + e.getMessage());
                        }
                    }
                        break;
                    case "Rellenar datos aleatorios": {
                        try {
                            String sqlProvincia = "INSERT INTO Provincias (NombreProvincia) VALUES (?)";
                            try (PreparedStatement pstProvincia = miConexion.prepareStatement(sqlProvincia)) {
                                String[] provincias = { "Madrid", "Barcelona", "Valencia" };
                                for (String prov : provincias) {
                                    pstProvincia.setString(1, prov);
                                    pstProvincia.executeUpdate();
                                }
                            }

                            String sqlPoblacion = "INSERT INTO población (NombrePoblacion, ProvinciaID) VALUES (?, ?)";
                            try (PreparedStatement pstPoblacion = miConexion.prepareStatement(sqlPoblacion)) {
                                String[][] población = { 
                                    { "Madrid Capital", "1" }, 
                                    { "Barcelona Ciudad", "2" },
                                    { "Valencia Ciudad", "3" } 
                                };
                                for (String[] dato : población) {
                                    pstPoblacion.setString(1, dato[0]);
                                    pstPoblacion.setInt(2, Integer.parseInt(dato[1]));
                                    pstPoblacion.executeUpdate();
                                }
                            }

                            String sqlCategoria = "INSERT INTO Categorias (NombreCategoria, DescripcionCategoria) VALUES (?, ?)";
                            try (PreparedStatement pstCategoria = miConexion.prepareStatement(sqlCategoria)) {
                                String[][] categorias = {
                                    { "Electrónica", "Dispositivos electrónicos" },
                                    { "Ropa", "Ropa y accesorios" },
                                    { "Alimentación", "Productos alimenticios" } 
                                };
                                for (String[] dato : categorias) {
                                    pstCategoria.setString(1, dato[0]);
                                    pstCategoria.setString(2, dato[1]);
                                    pstCategoria.executeUpdate();
                                }
                            }

                            String sqlProducto = "INSERT INTO Productos (NombreProducto, DescripcionProducto, PrecioUnitario, StockDisponible, CategoriaID) VALUES (?, ?, ?, ?, ?)";
                            try (PreparedStatement pstProducto = miConexion.prepareStatement(sqlProducto)) {
                                Object[][] productos = {
                                    { "Smartphone", "Teléfono inteligente", 299.99, 15, 1 },
                                    { "Camiseta", "Camiseta de algodón", 19.99, 50, 2 },
                                    { "Pan Integral", "Pan integral orgánico", 2.50, 100, 3 } 
                                };
                                for (Object[] dato : productos) {
                                    pstProducto.setString(1, (String) dato[0]);
                                    pstProducto.setString(2, (String) dato[1]);
                                    pstProducto.setDouble(3, (Double) dato[2]);
                                    pstProducto.setInt(4, (Integer) dato[3]);
                                    pstProducto.setInt(5, (Integer) dato[4]);
                                    pstProducto.executeUpdate();
                                }
                            }

                            String sqlCliente = "INSERT INTO Clientes (Nombre, Apellido, Email, Telefono, Direccion, PoblacionID, FechaNacimiento, Activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                            try (PreparedStatement pstCliente = miConexion.prepareStatement(sqlCliente)) {
                                Object[][] clientes = {
                                    { "Juan", "Pérez", "juan.perez@mail.com", "600111222", "Calle Falsa 123", 1, "1985-03-15", true },
                                    { "Ana", "Gómez", "ana.gomez@mail.com", "600333444", "Avenida Siempre Viva 742", 2, "1992-07-30", true },
                                    { "Luis", "Martínez", "luis.martinez@mail.com", "600555666", "Plaza Mayor 10", 3, "1988-12-05", false }
                                };
                                for (Object[] dato : clientes) {
                                    pstCliente.setString(1, (String) dato[0]);
                                    pstCliente.setString(2, (String) dato[1]);
                                    pstCliente.setString(3, (String) dato[2]);
                                    pstCliente.setString(4, (String) dato[3]);
                                    pstCliente.setString(5, (String) dato[4]);
                                    pstCliente.setInt(6, (Integer) dato[5]);
                                    pstCliente.setDate(7, Date.valueOf((String) dato[6]));
                                    pstCliente.setBoolean(8, (Boolean) dato[7]);
                                    pstCliente.executeUpdate();
                                }
                            }

                            miConexion.commit();
                            JOptionPane.showMessageDialog(null, "Datos aleatorios insertados correctamente.");
                        } catch (Exception ex) {
                            try {
                                miConexion.rollback();
                            } catch (Exception e) {
                            }
                            JOptionPane.showMessageDialog(null, "Error al insertar datos aleatorios: " + ex.getMessage());
                        }
                    }
                        break;
                    case "Insertar nuevo Dato": {
                        String[] opcionesInsertar = { "Clientes", "población", "Provincias", "Productos", "Categorías" };
                        String seleccionInsert = (String) JOptionPane.showInputDialog(null,
                                "¿En qué tabla quieres insertar un nuevo dato?", "Insertar nuevo Dato",
                                JOptionPane.QUESTION_MESSAGE,new ImageIcon("img/BaseDatos.png") , opcionesInsertar, opcionesInsertar[0]);

                        switch (seleccionInsert) {
                            case "Clientes": {
                                String nombre = JOptionPane.showInputDialog("Introduce el nombre:");
                                String apellido = JOptionPane.showInputDialog("Introduce el apellido:");
                                String email = JOptionPane.showInputDialog("Introduce el email:");
                                // Comprobar que el email no exista previamente
                                String checkEmail = "SELECT COUNT(*) FROM Clientes WHERE Email = ?";
                                try (PreparedStatement psCheck = miConexion.prepareStatement(checkEmail)) {
                                    psCheck.setString(1, email);
                                    ResultSet rs = psCheck.executeQuery();
                                    rs.next();
                                    if (rs.getInt(1) > 0) {
                                        JOptionPane.showMessageDialog(null, "El email ya existe. No se puede insertar.");
                                        break;
                                    }
                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(null, "Error al validar email: " + ex.getMessage());
                                    break;
                                }
                                String telefono = JOptionPane.showInputDialog("Introduce el teléfono:");
                                String direccion = JOptionPane.showInputDialog("Introduce la dirección:");
                                int poblacionId = Integer.parseInt(
                                        JOptionPane.showInputDialog("Introduce el ID de población:"));
                                String fechaNacimiento = JOptionPane.showInputDialog(
                                        "Introduce la fecha de nacimiento (YYYY-MM-DD):");
                                int activoOption = JOptionPane.showConfirmDialog(null, "¿Está activo?",
                                        "Estado", JOptionPane.YES_NO_OPTION);
                                boolean activo = (activoOption == JOptionPane.YES_OPTION);

                                String sqlInsertCliente = "INSERT INTO Clientes "
                                        + "(Nombre, Apellido, Email, Telefono, Direccion, PoblacionID, FechaNacimiento, Activo) "
                                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                                try (PreparedStatement psInsert = miConexion.prepareStatement(sqlInsertCliente)) {
                                    psInsert.setString(1, nombre);
                                    psInsert.setString(2, apellido);
                                    psInsert.setString(3, email);
                                    psInsert.setString(4, telefono);
                                    psInsert.setString(5, direccion);
                                    psInsert.setInt(6, poblacionId);
                                    psInsert.setDate(7, Date.valueOf(fechaNacimiento));
                                    psInsert.setBoolean(8, activo);
                                    int filasAfectadas = psInsert.executeUpdate();
                                    miConexion.commit();
                                    JOptionPane.showMessageDialog(null,
                                            "Cliente insertado correctamente. Filas afectadas: " + filasAfectadas);
                                } catch (Exception ex) {
                                    try {
                                        miConexion.rollback();
                                    } catch (Exception e) {
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Error al insertar cliente: " + ex.getMessage());
                                }
                            }
                                break;
                            case "población": {
                                String nombrePoblacion = JOptionPane.showInputDialog("Introduce el nombre de la población:");
                                int provinciaId = Integer.parseInt(
                                        JOptionPane.showInputDialog("Introduce el ID de provincia:"));
                                String sqlInsertPoblacion = "INSERT INTO población (NombrePoblacion, ProvinciaID) VALUES (?, ?)";
                                try (PreparedStatement psInsert = miConexion.prepareStatement(sqlInsertPoblacion)) {
                                    psInsert.setString(1, nombrePoblacion);
                                    psInsert.setInt(2, provinciaId);
                                    int filasAfectadas = psInsert.executeUpdate();
                                    miConexion.commit();
                                    JOptionPane.showMessageDialog(null,
                                            "Población insertada correctamente. Filas afectadas: " + filasAfectadas);
                                } catch (SQLException ex) {
                                    try {
                                        miConexion.rollback();
                                    } catch (SQLException e) {
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Error al insertar población: " + ex.getMessage());
                                }
                            }
                                break;
                            case "Provincias": {
                                String nombreProvincia = JOptionPane.showInputDialog("Introduce el nombre de la provincia:");
                                String sqlInsertProvincia = "INSERT INTO Provincias (NombreProvincia) VALUES (?)";
                                try (PreparedStatement psInsert = miConexion.prepareStatement(sqlInsertProvincia)) {
                                    psInsert.setString(1, nombreProvincia);
                                    int filasAfectadas = psInsert.executeUpdate();
                                    miConexion.commit();
                                    JOptionPane.showMessageDialog(null,
                                            "Provincia insertada correctamente. Filas afectadas: " + filasAfectadas);
                                } catch (Exception ex) {
                                    try {
                                        miConexion.rollback();
                                    } catch (Exception e) {
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Error al insertar provincia: " + ex.getMessage());
                                }
                            }
                                break;
                            case "Productos": {
                                String nombreProducto = JOptionPane.showInputDialog("Introduce el nombre del producto:");
                                String descripcionProducto = JOptionPane.showInputDialog("Introduce la descripción:");
                                double precioUnitario = Double.parseDouble(
                                        JOptionPane.showInputDialog("Introduce el precio unitario:"));
                                int stockDisponible = Integer.parseInt(
                                        JOptionPane.showInputDialog("Introduce el stock disponible:"));
                                int categoriaId = Integer.parseInt(
                                        JOptionPane.showInputDialog("Introduce el ID de categoría:"));
                                String sqlInsertProducto = "INSERT INTO Productos "
                                        + "(NombreProducto, DescripcionProducto, PrecioUnitario, StockDisponible, CategoriaID) "
                                        + "VALUES (?, ?, ?, ?, ?)";
                                try (PreparedStatement psInsert = miConexion.prepareStatement(sqlInsertProducto)) {
                                    psInsert.setString(1, nombreProducto);
                                    psInsert.setString(2, descripcionProducto);
                                    psInsert.setDouble(3, precioUnitario);
                                    psInsert.setInt(4, stockDisponible);
                                    psInsert.setInt(5, categoriaId);
                                    int filasAfectadas = psInsert.executeUpdate();
                                    miConexion.commit();
                                    JOptionPane.showMessageDialog(null,
                                            "Producto insertado correctamente. Filas afectadas: " + filasAfectadas);
                                } catch (Exception ex) {
                                    try {
                                        miConexion.rollback();
                                    } catch (Exception e) {
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Error al insertar producto: " + ex.getMessage());
                                }
                            }
                                break;
                            case "Categorías": {
                                String nombreCategoria = JOptionPane.showInputDialog("Introduce el nombre de la categoría:");
                                String descripcionCategoria = JOptionPane.showInputDialog("Introduce la descripción:");
                                String sqlInsertCategoria = "INSERT INTO Categorias (NombreCategoria, DescripcionCategoria) VALUES (?, ?)";
                                try (PreparedStatement psInsert = miConexion.prepareStatement(sqlInsertCategoria)) {
                                    psInsert.setString(1, nombreCategoria);
                                    psInsert.setString(2, descripcionCategoria);
                                    int filasAfectadas = psInsert.executeUpdate();
                                    miConexion.commit();
                                    JOptionPane.showMessageDialog(null, "Categoría insertada correctamente. Filas afectadas: " + filasAfectadas);
                                } catch (Exception ex) {
                                    try {
                                        miConexion.rollback();
                                    } catch (Exception e) {
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Error al insertar categoría: " + ex.getMessage());
                                }
                            }
                                break;
                        }
                    }
                        break;
                    case "Mostrar registros": {
                        String[] opcionesMostrar = { "Clientes", "población", "Provincias", "Productos", "Categorías" };
                        String seleccionMostrar = (String) JOptionPane.showInputDialog(null,
                                "¿Qué registros deseas mostrar?", "Mostrar registros", JOptionPane.QUESTION_MESSAGE,new ImageIcon("img/BaseDatos.png"), 
                                opcionesMostrar, opcionesMostrar[0]);

                        String sqlConsulta = "";
                        switch (seleccionMostrar) {
                            case "Clientes":
                                sqlConsulta = "SELECT c.ClienteID, c.Nombre, c.Apellido, c.Email, p.NombrePoblacion, pr.NombreProvincia "
                                        + "FROM Clientes c "
                                        + "INNER JOIN población p ON c.PoblacionID = p.PoblacionID "
                                        + "INNER JOIN Provincias pr ON p.ProvinciaID = pr.ProvinciaID";
                                break;
                            case "población":
                                sqlConsulta = "SELECT p.PoblacionID, p.NombrePoblacion, pr.NombreProvincia "
                                        + "FROM población p "
                                        + "INNER JOIN Provincias pr ON p.ProvinciaID = pr.ProvinciaID";
                                break;
                            case "Provincias":
                                sqlConsulta = "SELECT ProvinciaID, NombreProvincia FROM Provincias";
                                break;
                            case "Productos":
                                sqlConsulta = "SELECT p.ProductoID, p.NombreProducto, p.DescripcionProducto, p.PrecioUnitario, "
                                        + "p.StockDisponible, c.NombreCategoria "
                                        + "FROM Productos p "
                                        + "INNER JOIN Categorias c ON p.CategoriaID = c.CategoriaID";
                                break;
                            case "Categorías":
                                sqlConsulta = "SELECT CategoriaID, NombreCategoria, DescripcionCategoria FROM Categorias";
                                break;
                        }

                        try (Statement st = miConexion.createStatement();
                                ResultSet rs = st.executeQuery(sqlConsulta)) {

                            String salida =  seleccionMostrar + ":\n";
                            while (rs.next()) {
                                switch (seleccionMostrar) {
                                    case "Clientes":
                                        salida += "ID: " + rs.getInt("ClienteID")
                                                + " | Nombre: " + rs.getString("Nombre")
                                                + " " + rs.getString("Apellido")
                                                + " | Email: " + rs.getString("Email")
                                                + " | Población: " + rs.getString("NombrePoblacion")
                                                + " | Provincia: " + rs.getString("NombreProvincia") + "\n";
                                        break;
                                    case "población":
                                        salida += "ID: " + rs.getInt("PoblacionID")
                                                + " | Población: " + rs.getString("NombrePoblacion")
                                                + " | Provincia: " + rs.getString("NombreProvincia") + "\n";
                                        break;
                                    case "Provincias":
                                        salida += "ID: " + rs.getInt("ProvinciaID")
                                                + " | Provincia: " + rs.getString("NombreProvincia") + "\n";
                                        break;
                                    case "Productos":
                                        salida += "ID: " + rs.getInt("ProductoID")
                                                + " | Producto: " + rs.getString("NombreProducto")
                                                + " | Desc.: " + rs.getString("DescripcionProducto")
                                                + " | Precio: " + rs.getDouble("PrecioUnitario")
                                                + " | Stock: " + rs.getInt("StockDisponible")
                                                + " | Categoría: " + rs.getString("NombreCategoria") + "\n";
                                        break;
                                    case "Categorías":
                                        salida += "ID: " + rs.getInt("CategoriaID")
                                                + " | Categoría: " + rs.getString("NombreCategoria")
                                                + " | Descripción: " + rs.getString("DescripcionCategoria") + "\n";
                                        break;
                                }
                            }
                            System.out.println(salida);
                            JOptionPane.showMessageDialog(null, "Registros mostrados en la consola.");
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error al mostrar registros: " + ex.getMessage());
                        }
                    }
                        break;
                    case "Eliminar registro": {
                        String[] opcionesEliminar = { "Clientes", "población", "Provincias", "Productos", "Categorías" };
                        String seleccionEliminar = (String) JOptionPane.showInputDialog(null,
                                "¿De qué tabla deseas eliminar un registro?", "Eliminar registro",
                                JOptionPane.QUESTION_MESSAGE,new ImageIcon("img/BaseDatos.png"),  opcionesEliminar, opcionesEliminar[0]);

                        String idStr = JOptionPane.showInputDialog(
                                "Introduce el ID a eliminar de " + seleccionEliminar + ":");
                        int idToDelete = Integer.parseInt(idStr);
                        String sqlDelete = "";
                        switch (seleccionEliminar) {
                            case "Clientes":
                                sqlDelete = "DELETE FROM Clientes WHERE ClienteID = ?";
                                break;
                            case "población":
                                sqlDelete = "DELETE FROM población WHERE PoblacionID = ?";
                                break;
                            case "Provincias":
                                sqlDelete = "DELETE FROM Provincias WHERE ProvinciaID = ?";
                                break;
                            case "Productos":
                                sqlDelete = "DELETE FROM Productos WHERE ProductoID = ?";
                                break;
                            case "Categorías":
                                sqlDelete = "DELETE FROM Categorias WHERE CategoriaID = ?";
                                break;
                        }
                        try (PreparedStatement psDelete = miConexion.prepareStatement(sqlDelete)) {
                            psDelete.setInt(1, idToDelete);
                            int filasAfectadas = psDelete.executeUpdate();
                            miConexion.commit();
                            JOptionPane.showMessageDialog(null,
                                    "Registro eliminado correctamente. Filas afectadas: " + filasAfectadas);
                        } catch (SQLException e) {
                            try {
                                miConexion.rollback();
                            } catch (SQLException ex) {
                            }
                            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
                        }
                    }
                        break;
                    case "Actualizar precios de productos": {
                        String precioLimiteStr = JOptionPane.showInputDialog("Introduce el precio límite:");
                        double precioLimite = Double.parseDouble(precioLimiteStr);
                        String sqlUpdate = "UPDATE Productos SET PrecioUnitario = PrecioUnitario * 1.10 "
                                + "WHERE PrecioUnitario < ?";
                        try (PreparedStatement psUpdate = miConexion.prepareStatement(sqlUpdate)) {
                            psUpdate.setDouble(1, precioLimite);
                            int filasActualizadas = psUpdate.executeUpdate();
                            miConexion.commit();
                            JOptionPane.showMessageDialog(null,
                                    "Se han actualizado " + filasActualizadas + " productos con precio inferior a " + precioLimite);
                        } catch (Exception ex) {
                            try {
                                miConexion.rollback();
                            } catch (Exception e) {
                            }
                            JOptionPane.showMessageDialog(null, "Error al actualizar precios: " + ex.getMessage());
                        }
                    }
                        break;
                    case "Salir":
                        JOptionPane.showMessageDialog(null, "Saliendo del programa.");
                        break;
                }

            } while (!opcion.equals("Salir"));

            miConexion.close();
            System.out.println("Fin Programa");

        } catch (Exception e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        }
    }
}