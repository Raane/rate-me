package Database;

//foreign key

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
    private static final String RATEABLE_TABLE = "Rateables";
    private static final String CATEGORY_TABLE = "Categories";
    private static final String KEY_ID = "id";
    private static final String KEY_ID_FOREIGN = "category_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ELO = "elo";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	String CREATE_CATEGORY_TABLE = "CREATE TABLE " + CATEGORY_TABLE + "("
    			+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
    	String CREATE_RATEABLE_TABLE = "CREATE TABLE " + RATEABLE_TABLE + "("
    			+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_ID_FOREIGN + " INTEGER," + KEY_NAME + " TEXT,"
    			+ KEY_ELO + " TEXT," + "FOREIGN KEY("+ KEY_ID_FOREIGN + ") " + "REFERENCES "
    			+ RATEABLE_TABLE + "(" + KEY_ID + ")" + ")";
    	db.execSQL(CREATE_CATEGORY_TABLE);
    	db.execSQL(CREATE_RATEABLE_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
    	db.execSQL("DROP TABLE IF EXISTS " + RATEABLE_TABLE);
        onCreate(db);
    }
    
    public void createCategory(Category category) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, category.getName());
    	
    	db.insert(CATEGORY_TABLE, null, values);
    	db.close();
    }
  
    
    public void createRateableEntry(Category category, Rateable rateable) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_ID_FOREIGN, String.valueOf(category.getId()) );
    	values.put(KEY_NAME, rateable.getName());
    	values.put(KEY_ELO, String.valueOf(rateable.getElo()));
    	
    	db.insert(RATEABLE_TABLE, null, values);
    	db.close();
    }
    
    public void updateRateableEntry(Rateable rateable) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, rateable.getName());
    	values.put(KEY_ELO, String.valueOf(rateable.getElo()));
    	
    	db.update(RATEABLE_TABLE, values, KEY_ID + " = ?", new String[] { String.valueOf(rateable.getId())});
    	db.close();
    }
    
    public void deleteRateableEntry(Rateable rateable) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(RATEABLE_TABLE, KEY_ID + " = ?", new String[] { String.valueOf(rateable.getId())});
    	db.close();
    }
    
    public void deleteCategoryEntry(String nameCategory) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	db.close();
    }
    
    public ArrayList<Category> getCategories() {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ArrayList<Category> categories = new ArrayList<Category>();
    	String selectQuery = "SELECT * FROM " + CATEGORY_TABLE;
    	Cursor cursor = db.rawQuery(selectQuery, null);	
    	
    	if(cursor.moveToFirst()){
    		do {
    			Category category = new Category(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
    			categories.add(category);
    		}while(cursor.moveToNext());
    	}
    	db.close();
    	return categories;
    }
    
    public ArrayList<Rateable> getRateables(Category category) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ArrayList<Rateable> rateables = new ArrayList<Rateable>();
    	String selectQuery = "SELECT * FROM " + RATEABLE_TABLE + " WHERE = " + KEY_ID_FOREIGN + " == " + category.getId();
    	Cursor cursor = db.rawQuery(selectQuery, null);
    	
    	if(cursor.moveToFirst()){
    		do {
    			Rateable rateable = new Rateable(Integer.parseInt(cursor.getString(0)),cursor.getString(2),
    					Integer.parseInt(cursor.getString(3)));
    			rateables.add(rateable);
    		}while(cursor.moveToNext());
    	}
    	db.close();
    	return rateables;
    }
 
}