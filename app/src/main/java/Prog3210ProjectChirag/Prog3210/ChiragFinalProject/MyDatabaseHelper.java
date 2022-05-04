package Prog3210ProjectChirag.Prog3210.ChiragFinalProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private  Context context;
    private static  final String DATABASE_NAME = "TeamLibrary.db";
    private static  final  int DATABASE_VERSION = 1;

    private static  final String TABLE_NAME= "Team_library";
    private static  final String COLUMN_ID ="_id";
    private   static  final  String COLUMN_TITLE ="Work_Title";
    private static  final String COLUMN_WorkAllot = "Work_allot";
    private static  final  String ColUMN_DeadLineDays = "Required_days";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_TITLE+
                        " TEXT, "+
                        COLUMN_WorkAllot+ " TEXT, "+
                        ColUMN_DeadLineDays+ " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }
    void addTask(String title,String member, int  workDuration){
            SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_WorkAllot,member);
        cv.put(ColUMN_DeadLineDays,workDuration);
        long result= db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context,"Failed to add the record", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"Successfully added the record", Toast.LENGTH_SHORT).show();
        }
    }

     Cursor readAllData(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =null;
        if(db != null){
          cursor = db.rawQuery(query, null);

        }
        return cursor;
     }
}
