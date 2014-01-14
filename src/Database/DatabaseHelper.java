package Database;

import java.util.ArrayList;
import java.util.List;

import com.rate_me.MainActivity;
import com.rate_me.Rateable;
import com.rate_me.Category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHelper extends SQLiteOpenHelper {
 
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CategoryManager";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ELO = "elo";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	// Mener denne skal være tom fordi tabellene ikke skal lages med en gang databasen er opprettet
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	ArrayList<String> listOfTableNames = getCategories();
        for(int i = 0; i<listOfTableNames.size(); i++) {
        	db.execSQL("DROP TABLE IF EXISTS " + listOfTableNames.get(i));
        }
        onCreate(db);
    }
    
    
    private ArrayList<String> getCategories() {
    	ArrayList<String> listOfCategories = new ArrayList<String>();
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor cursor = db.rawQuery("SELECT name FROM CategoryManager WHERE type='table'", null);
    	if(cursor.moveToFirst()) {
    		while(!cursor.isAfterLast()) {
    			listOfCategories.add(cursor.getString(cursor.getColumnIndex("name")));
    			cursor.moveToNext();
    		}
    	}
    	return listOfCategories;
    }
    
    public void createTable(String nameCategory) {
		SQLiteDatabase db = this.getWritableDatabase();
		String TABLE_CATEGORY = nameCategory;
		String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_ELO + " TEXT" + ")";
		db.execSQL(CREATE_CATEGORY_TABLE);
		db.close();
    }
    
    public void createRateableEntry(String nameCategory, Rateable rateable) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, rateable.getName());
    	values.put(KEY_ELO, rateable.getElo());
    	db.insert(nameCategory, null, values);
    	db.close();
    }
    
    public void updateRateableEntry(String nameCategory, Rateable rateable) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, rateable.getName());
    	values.put(KEY_ELO, rateable.getElo());
    	db.update(nameCategory, values, KEY_ID + " = ?", new String[] { String.valueOf(rateable.getId())});
    	db.close();
    }
    
    public void deleteRateableEntry(String nameCategory, Rateable rateable) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(nameCategory, KEY_ID + " = ?", new String[] { String.valueOf(rateable.getId())});
    	db.close();
    }
    
    public void deleteCategoryEntry(String nameCategory) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL("DROP TABLE " + nameCategory);
    	db.close();
    }
    
    
}