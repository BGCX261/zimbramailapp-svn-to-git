package com.artesanik.zimbramail;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "mailPreferences";
 
    // Mail Accounts table name
    private static final String TABLE_ACCOUNTS = "mail_accounts";
 
    // Mail Accounts Table Columns names
    public static final String COLUMN_ID = "id";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_ACCOUNT = "account";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_SIGN = "sign";
	public static final String COLUMN_SERVER = "server";
	public static final String COLUMN_DEFAULT = "default_account";
	public static final String COLUMN_ORDER = "order_account";
	public static final String COLUMN_STATUS = "status_account";
	public static final String COLUMN_LASTSYNC = "lastsync";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
 // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MAILACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
        	    + COLUMN_ID + " integer primary key autoincrement, " 
        	    + COLUMN_DESCRIPTION + " text not null, " 
        	    + COLUMN_ACCOUNT + " text not null,"
        	    + COLUMN_PASSWORD + " text not null,"
        	    + COLUMN_SIGN + " text null,"
        	    + COLUMN_SERVER + " text null,"
        	    + COLUMN_DEFAULT + " text null,"
        	    + COLUMN_ORDER + " integer,"
        	    + COLUMN_STATUS + " text not null,"
        	    + COLUMN_LASTSYNC + " text null"
        	    + ");";
        db.execSQL(CREATE_MAILACCOUNTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
 
        // Create tables again
        onCreate(db);
    }
    
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    
    // Adding new mail account
    public void addAccount(MailAccount account) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, account.getDescription());
		values.put(COLUMN_ACCOUNT, account.getAccount());
		values.put(COLUMN_PASSWORD, account.getPassword());
		values.put(COLUMN_SIGN, account.getSign());
		values.put(COLUMN_SERVER, account.getServer());
		values.put(COLUMN_DEFAULT, account.getDefault_account());
		values.put(COLUMN_ORDER, account.getOrder());
		values.put(COLUMN_STATUS, account.getStatus());
     
        // Inserting Row
        db.insert(TABLE_ACCOUNTS, null, values);
        db.close(); // Closing database connection
    }
    
 // Getting single account info
    public MailAccount getAccount(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] { COLUMN_ID,
        		COLUMN_DESCRIPTION, COLUMN_ACCOUNT }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        MailAccount account = new MailAccount(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return account;
    }
    
 // Getting All Mail Accounts
    public List<MailAccount> getAllAccounts() {
       List<MailAccount> accountList = new ArrayList<MailAccount>();
       // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNTS;
    
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);
    
       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
        	   MailAccount account = new MailAccount();
               account.setId(Integer.parseInt(cursor.getString(0)));
               account.setDescription(cursor.getString(1));
               account.setAccount(cursor.getString(2));
               account.setPassword(cursor.getString(3));
               account.setSign(cursor.getString(4));
               account.setServer(cursor.getString(5));
               account.setDefault_account(cursor.getString(6));
               account.setOrder(Integer.parseInt(cursor.getString(7)));
               account.setLastsync(cursor.getString(8));
               // Adding contact to list
               accountList.add(account);
           } while (cursor.moveToNext());
       }
    
       // return contact list
       return accountList;
   }
    
    // Getting mail accounts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ACCOUNTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    
    // Updating single account
    public int updateContact(MailAccount account) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, account.getDescription());
		values.put(COLUMN_ACCOUNT, account.getAccount());
		values.put(COLUMN_PASSWORD, account.getPassword());
		values.put(COLUMN_SIGN, account.getSign());
		values.put(COLUMN_SERVER, account.getServer());
		values.put(COLUMN_DEFAULT, account.getDefault_account());
		values.put(COLUMN_ORDER, account.getOrder());
		values.put(COLUMN_STATUS, account.getStatus());
     
        // updating row
        int idAccount = db.update(TABLE_ACCOUNTS, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(account.getId()) });
        db.close();
		return idAccount;
        
    }
    
    // Deleting single account
    public void deleteContact(MailAccount account) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(account.getId()) });
        db.close();
    }

}
