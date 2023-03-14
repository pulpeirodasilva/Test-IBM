import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Test_confirming {

    public static void main(String[] args) {

        // Comprobar que el usuario pasa un parámetro válido por consola.
        if (args.length < 1) {
            System.out.println("Por favor, indique un código de cliente válido como parámetro.");
            return;
        }

        int codigoCliente = Integer.parseInt(args[0]);

        try {
            // Conectarse a la base de datos.
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample_db",
                    "sample_db_user",
                    "sample_db_pass");

            // Realizar la correspondiente consulta a la base de datos
			// para extraer los proveedores asociados al código de cliente indicado por parámetro.
            PreparedStatement stmt = conexion.prepareStatement(
                    "select * from proveedores where id_cliente = ?");
            stmt.setInt(1, codigoCliente);
            ResultSet rs = stmt.executeQuery();

            // Comprobar si existen clientes con el ID pasado por parámetro.
			// En caso contrario, mostrar un mensaje de error.
            if (!rs.next()) {
                System.out.println("El cliente especificado no tiene proveedores asignados.");
                return;
            }

            // Crear un objeto FileWriter para volcar los datos obtenidos en un fichero.
            FileWriter fw = new FileWriter("Proveedores");

            // Escribir las cabeceras correspondientes en el fichero.
            fw.write("ID,Nombre,Fecha de alta,Cliente\n");

            // Recorrer los registros del objeto ResultSet, y volcar su contenido en el fichero creado.
            do {
                int idProveedor = rs.getInt("id_proveedor");
                String nombreProveedor = rs.getString("nombre");
                Date fechaAlta = rs.getDate("fecha_de_alta");
                int clienteAsociado = rs.getInt("id_cliente");

                fw.write(idProveedor + "," + nombreProveedor + "," + fechaAlta + "," + clienteAsociado + "\n");
            } while (rs.next());

            // Cerrar el objeto FileWriter, y mostrar por pantalla un mensaje indicado que la operación se realizó con éxito.
            fw.close();
            System.out.println("Los datos de los proveedores han sido exportados con éxito.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
