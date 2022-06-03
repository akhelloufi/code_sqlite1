package databasePersonnel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseAide  extends SQLiteOpenHelper {
public static int version=1;
public  final static String nombase="gestionClient";


    public DatabaseAide(@Nullable Context context){
        super(context,nombase,null,version);//creation database gestionclient
    }

    @Override
    public void onCreate(SQLiteDatabase x) {
        String sql="create table client (code INTEGER primary key,nom TEXT ,salaire REAL) ";
        x.execSQL(sql);
        System.out.println("table client cree::");
        Log.println(Log.INFO,"table :","client cree");

    }

    @Override
    public void onUpgrade(SQLiteDatabase x, int iversionold, int i1versionnew) {
        String sql="drop table client";
        x.execSQL(sql);
        onCreate(x);
    }


}
