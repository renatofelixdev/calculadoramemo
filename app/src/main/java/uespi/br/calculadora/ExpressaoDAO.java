package uespi.br.calculadora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENATO on 01/11/2016.
 */

public class ExpressaoDAO {
    private SQLiteDatabase database;
    private  String []  columns = { CustomSQLiteOpenHelper.COLUMN_ID ,
            CustomSQLiteOpenHelper.COLUMN_NOTES  };
    private  CustomSQLiteOpenHelper  sqliteOpenHelper;

    public  ExpressaoDAO(Context context) {
        sqliteOpenHelper = new  CustomSQLiteOpenHelper(context);
    }

    public  void  open()  throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase ();
    }

    public  void  close () {
        sqliteOpenHelper.close ();
    }

    public  long  create(String  expressao) {
        ContentValues values = new  ContentValues ();
        values.put(CustomSQLiteOpenHelper.COLUMN_NOTES , expressao);
        long  insertId = database.insert(CustomSQLiteOpenHelper.TABLE_NOTES, null, values);
        return insertId;
    }

    public  long  delete(Expressao  e) {
        long id = e.getId ();
        long deleted = database.delete(CustomSQLiteOpenHelper.TABLE_NOTES, CustomSQLiteOpenHelper.COLUMN_ID + " = " + id , null);
        return deleted;
    }

    public long update(Expressao e, String exp){
        long id = e.getId();
        ContentValues args = new ContentValues();
        args.put(CustomSQLiteOpenHelper.COLUMN_NOTES, exp);
        long updated = database.update(CustomSQLiteOpenHelper.TABLE_NOTES, args,CustomSQLiteOpenHelper.COLUMN_ID + " = " + id, null);
        return updated;
    }

    public List<Expressao> getAll () {
        List <Expressao> exps = new ArrayList<Expressao>();

        Cursor  cursor = database.query(CustomSQLiteOpenHelper.TABLE_NOTES,
                columns, null, null, null, null, null);

        cursor.moveToFirst ();
        while  (! cursor.isAfterLast ()) {
            Expressao  e = new Expressao();
            e.setId(cursor.getLong (0));
            e.setExpressao(cursor.getString (1));
            exps.add(e);
            cursor.moveToNext ();
        }
        cursor.close ();
        return  exps;
    }
}
