package cl.inacap.tarea.clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AdaptadorBase {
	
	static final String DATABASE_NAME = "productos_frescos.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 1;
	// TODO: Creando los valores de la tabla.
	// SQL para crear los insert

	static final String INSERT_DB = "INSERT INTO LOGIN(ID, USERNAME, PASSWORD) VALUES(1,'herman','1234');";
	static final String INSERT_DB2 = "INSERT INTO CLIENTE(ID_CLIENTE,NOMBRE,APELLIDO,EMPRESA) VALUES(1,'Maria','Vidal','La cosita');";
	static final String INSERT_DB3 = "INSERT INTO CLIENTE(ID_CLIENTE,NOMBRE,APELLIDO,EMPRESA) VALUES(2,'Juan','Gonzalez','Tai helao Juan');";
	static final String INSERT_DB4 = "INSERT INTO CLIENTE(ID_CLIENTE,NOMBRE,APELLIDO,EMPRESA) VALUES(3,'Pedro','Urdemales','Mijita');";
	static final String INSERT_DB5 = "INSERT INTO CLIENTE(ID_CLIENTE,NOMBRE,APELLIDO,EMPRESA) VALUES(4,'Rosa','La Chora','Ta linda la Rosa');";
	static final String INSERT_DB6 = "INSERT INTO PRODUCTO(ID_PRODUCTO, NOMBRE, MARCA) VALUES(1,'arroz','Chino');";
	static final String INSERT_DB7 = "INSERT INTO PRODUCTO(ID_PRODUCTO, NOMBRE, MARCA) VALUES(2,'azuca','IANSA');";
	static final String INSERT_DB8 = "INSERT INTO PRODUCTO(ID_PRODUCTO, NOMBRE, MARCA) VALUES(3,'Te','SUPREMO');";
	static final String INSERT_DB9 = "INSERT INTO PRODUCTO(ID_PRODUCTO, NOMBRE, MARCA) VALUES(4,'Sal','Lobos');";
	static final String INSERT_DB10 = "INSERT INTO PRODUCTO(ID_PRODUCTO, NOMBRE, MARCA) VALUES(5,'Harina','La selecta');";
	
	// Objeto db de tipo SQLLite
	public  SQLiteDatabase db;
	// Context de la app usado en la base de datos
	private final Context context;
	// Un objeto dbhelper de tipo base de datos y por el constructor se abre y actualiza
	private BaseDatos dbHelper;
	public AdaptadorBase(Context context) {
		super();
		this.context = context;
		dbHelper = new BaseDatos(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public  AdaptadorBase open() throws SQLException 
	{
		db = dbHelper.getWritableDatabase();
		return this;
	}
	public void close() 
	{
		db.close();
	}
	public  SQLiteDatabase getDatabaseInstance()
	{
		return db;
	}
	
	public String validaUsuario(String userName)
	{
		Cursor cursor=db.query("LOGIN", null, "USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName no existe
        {
        	cursor.close();
        	return "NO EXISTE";
        }
	    cursor.moveToFirst();
		String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
		cursor.close();
		return password;				
	}
	public Cursor leerClientes(){
		 //SQLiteDatabase db = getReadableDatabase();
		 db = dbHelper.getReadableDatabase();
		  
		 return db.rawQuery("SELECT ID_CLIENTE AS _id, EMPRESA AS name FROM CLIENTE;", null);  
		}
	
	
	
	
	

}
