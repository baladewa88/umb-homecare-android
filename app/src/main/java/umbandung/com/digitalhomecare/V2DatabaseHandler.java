package umbandung.com.digitalhomecare;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class V2DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "homecare";
 
    // Contacts table name
    private static final String TABLE_CONTACTS = "fav";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TANGGAL = "tanggal";
    
    private static final String KEY_NAMA = "btp_id";
    private static final String KEY_RM = "btp_nama";
    private static final String KEY_HARGA = "btp_sub";

 
    public V2DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," 
                + KEY_TANGGAL + " TEXT,"
         		+ KEY_NAMA + " TEXT,"
        		+ KEY_RM + " TEXT,"
        		+ KEY_HARGA + " TEXT)";
        
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.e("CREATE TABLE", CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // Create tables again
        onCreate(db);
    }
	
 // Adding new contact
    void addData(V2SaveUtil data) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TANGGAL, data.getTanggal());
        values.put(KEY_NAMA, data.getNama()); // Contact Name
        values.put(KEY_RM, data.getRm()); // Contact Name
        values.put(KEY_HARGA, data.getHarga()); // Contact Name

        
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
    
 // Getting single contact
    V2SaveUtil getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(
        		TABLE_CONTACTS, 
        		new String[] { 
        				KEY_ID,KEY_TANGGAL,
        				KEY_NAMA, KEY_RM, KEY_HARGA,
        				}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        V2SaveUtil contact = new V2SaveUtil(
        		Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), 
                cursor.getString(2),
                cursor.getString(3), 
                cursor.getString(4));
        // return contact
        return contact;
    }
    
    // Getting All Contacts
    public ArrayList<V2SaveUtil> getAllContacts() {
        ArrayList<V2SaveUtil> contactList = new ArrayList<V2SaveUtil>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	V2SaveUtil contact = new V2SaveUtil();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTanggal(cursor.getString(1));
                contact.setNama(cursor.getString(2));
                contact.setRm(cursor.getString(3));
                contact.setHarga(cursor.getString(4));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
 // Deleting single contact
    public void deleteContact(V2SaveUtil contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
 

}
