package com.util;

/**
 * Clase encargada de la configuración de la aplicación, en su formato .TXT, que es el que está disponible.
 * En primer lugar, se presentan las configuraciones de los ficheros de texto que usa la aplicación.
 * Un poco más adelante, se encuentra la posibilidad de configurar los mensajes que se mostrarán en la aplicación
 * en caso de éxito o error en todos los eventos del negocio. Se tratan de mensajes totalmente personalizables.
 * 
 * @author Javier
 *
 */

public class Common {
	
	// Ficheros de la base de datos
	public static String ORDERS_FILE = "res/txt/Orders.txt";
	public static String SALES_FILE = "res/txt/Sales.txt";
	public static String STOCK_FILE = "res/txt/Stock.txt";
	public static String STAFF_FILE = "res/txt/Users.txt";
	public static String RECEIPT_FILE = "res/txt/Factura.txt";
	public static String LOGGEDUSERS_FILE = "res/txt/LoggedUser.txt";
    
    //Mensajes configurables
    
    // COMUNES
    
    public static String SUCCESFULL_ADDPRODUCT_MESSAGE = "Producto añadido con éxito!";
    public static String SUCCESFULL_REMOVEPRODUCT_MESSAGE = "Producto eliminado con éxito!";
    
    public static String ADDPRODUCT_ERRORMESSAGE = "Ha ocurrido un problema al añadir el producto: ";
    public static String REMOVEPRODUCT_ERRORMESSAGE = "Ha ocurrido un problema al eliminar el producto: ";
    
    // SUBSISTEMA DE PEDIDOS
    
    public static String SUCCESFULL_ADDORDER_MESSAGE = "Pedido añadido con éxito!";
    public static String SUCCESFULL_DELETEORDER_MESSAGE = "Pedido eliminado con éxito!";
    public static String SUCCESFULL_FINISHORDER_MESSAGE = "Pedido finalizado con éxito!";
    public static String SUCCESFULL_MODIFYORDER_MESSAGE = "Pedido modificado con éxito!";
    public static String SUCCESFULL_APPROVEORDER_MESSAGE = "Pedido confirmado con éxito!";
    
    public static String DELETEORDER_ERRORMESSAGE = "Ha ocurrido un problema al eliminar el pedido: ";
    public static String MODIFYORDER_ERRORMESSAGE = "Ha ocurrido un problema al modificar el pedido: ";
    public static String APPROVEORDER_ERRORMESSAGE = "Ha ocurrido un problema al confirmar el pedido: ";
    public static String SEARCHORDER_ERRORMESSAGE = "Ha ocurrido un problema en la búsqueda del pedido: ";
    
    //SUBSISTEMA DE VENTAS
    
    public static String SUCCESFULL_ADDSALE_MESSAGE = "Venta añadida con éxito!";
    public static String SUCCESFULL_DELETESALE_MESSAGE = "Venta eliminada con éxito!";
    public static String SUCCESFULL_CANCELSALE_MESSAGE = "Venta cancelada con éxito!";
    public static String SUCCESFULL_FINISHSALE_MESSAGE = "Venta finalizada con éxito!";
    
    public static String DELETESALE_ERRORMESSAGE = "Ha ocurrido un problema al eliminar la venta: ";
    public static String SEARCHSALE_ERRORMESSAGE = "Ha ocurrido un problema en la búsqueda de la venta: ";
    public static String FINISHSALE_ERRORMESSAGE = "Ha ocurrido un problema al finalizar la venta: ";
    public static String SHOWEXCHANGE_ERRORMESSAGE = "Ha ocurrido un problema al mostrar el cambio: ";
    
    //SUBSISTEMA DE USUARIOS
    
    public static String SUCCESFULL_ADDUSER_MESSAGE = "Usuario añadido con éxito!";
    public static String SUCCESFULL_DELETEUSER_MESSAGE = "Usuario eliminado con éxito!";
    public static String SUCCESFULL_MODIFYUSER_MESSAGE = "Usuario modificado con éxito!";
    
    public static String ADDUSER_ERRORMESSAGE = "Ha ocurrido un problema al añadir el usuario: ";
    public static String DELETEUSER_ERRORMESSAGE = "Ha ocurrido un problema al eliminar el usuario: ";
    public static String MODIFYUSER_ERRORMESSAGE = "Ha ocurrido un problema al modificar el usuario: ";
    public static String SEARCHUSER_ERRORMESSAGE = "Ha ocurrido un problema al buscar el usuario: ";
    
    //SUBSISTEMA DE ALMACÉN
    
    public static String SUCCESFULL_SORTPRODUCTS_MESSAGE = "Inventario clasificado con éxito!";
    
    public static String SEARCHPRODUCT_ERRORMESSAGE = "Ha ocurrido un problema al buscar el producto: ";
	
}