package app.sparktest1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite_Layer extends SQLiteOpenHelper {

    public enum SqlCommandEnum{
        DATABASENAME("players"),
        DBPLAYERID("id"),
        DBPLAYERNAME("playername"),
        DBPLAYERSURNAME("playersurname"),
        DBPLAYERBIRTHDAY("playerbirthday"),
        DBPLAYERTCKNO("playertckno"),
        DBPLAYERPHONE("playerphone"),
        DBPLAYERCLUB("playerclub"),
        DBPLAYERLISENCENO("playerlicenseno"),
        DBPLAYERCURRENTDATE("playercurrentdate"),
        /*
        DBPLAYERHEIGHT("playerheight"),
        DBPLAYERWEIGHT("playerweight"),*/

        DATABASEPERIODICNAME("periodic"),
        DBPERIODICID("id"),
        DBPERIODICDATE("playerdate"),
        DBPERIODICVALUE("playervalue"),
        DBPERIODICPLAYERID("playerid"),
        DBPERIODICVALUETYPE("playervaluetype");


        //String periodicColumns [] = {"id","playerdate","playervalue","playerid","playervaluetype"};



        private String databaseAttr;
        SqlCommandEnum(String databaseAttr){
            this.databaseAttr = databaseAttr;
        }
        public String databaseAttr(){
            return databaseAttr;
        }


    }

    public Sqlite_Layer(Context context){
        // super(context, name = veritabanı ismi, factory = null, version=which version using by upgrade?); super needs them
        //super(context,"players",null,1);
        //players database adını aldım enumarate kullanarak
        super(context,SqlCommandEnum.DATABASENAME.databaseAttr,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //to easy writablelety selecting tihs string sql command.
        /*String sql = "CREATE TABLE IF NOT EXISTS players (id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "playername VARCHAR NOT NULL, playersurname VARCHAR  , playerbirthday VARCHAR,playertckno VARCHAR," +
                "playerphone VARCHAR,playerclub VARCHAR,playerlicenseno VARCHAR,playercurrentdate VARCHAR,playerheight VARCHAR," +
                "playerweight VARCHAR)";*/
       // db.execSQL(sql);
        //Enumarate ile database stringini getirdim reusability açısından daha sağlıklı ve constant değişmiyor.
        /*String sql = "CREATE TABLE IF NOT EXISTS "+ SqlCommandEnum.DATABASENAME.databaseAttr+"("+SqlCommandEnum.DBPLAYERID.databaseAttr+" INTEGER PRIMARY KEY AUTOINCREMENT , "
                +SqlCommandEnum.DBPLAYERNAME.databaseAttr+" VARCHAR NOT NULL, "+SqlCommandEnum.DBPLAYERSURNAME.databaseAttr+" VARCHAR  , "+SqlCommandEnum.DBPLAYERBIRTHDAY.databaseAttr+
                " VARCHAR,"+SqlCommandEnum.DBPLAYERTCKNO.databaseAttr+" VARCHAR," + SqlCommandEnum.DBPLAYERPHONE.databaseAttr+
                " VARCHAR,"+SqlCommandEnum.DBPLAYERCLUB.databaseAttr+" VARCHAR,"+SqlCommandEnum.DBPLAYERLISENCENO.databaseAttr+" VARCHAR,"+SqlCommandEnum.DBPLAYERCURRENTDATE.databaseAttr+
                " VARCHAR,"+SqlCommandEnum.DBPLAYERHEIGHT.databaseAttr+" VARCHAR," + SqlCommandEnum.DBPLAYERWEIGHT.databaseAttr+" VARCHAR)";*/
       //player tablosundan weight ile boy kolonu çıkarıldı bide current date de çıkarıldı
       /* String sql = "CREATE TABLE IF NOT EXISTS "+ SqlCommandEnum.DATABASENAME.databaseAttr+"("+SqlCommandEnum.DBPLAYERID.databaseAttr+" INTEGER PRIMARY KEY AUTOINCREMENT , "
                +SqlCommandEnum.DBPLAYERNAME.databaseAttr+" VARCHAR NOT NULL, "+SqlCommandEnum.DBPLAYERSURNAME.databaseAttr+" VARCHAR  , "+SqlCommandEnum.DBPLAYERBIRTHDAY.databaseAttr+
                " VARCHAR,"+SqlCommandEnum.DBPLAYERTCKNO.databaseAttr+" VARCHAR," + SqlCommandEnum.DBPLAYERPHONE.databaseAttr+
                " VARCHAR,"+SqlCommandEnum.DBPLAYERCLUB.databaseAttr+" VARCHAR,"+SqlCommandEnum.DBPLAYERLISENCENO.databaseAttr+" VARCHAR)";*/
       //player tablosundaki tablo cinsini varchardan integera çevirdiğim yer tckno,phone ve licenceno bunlar integer tipinde.
        String sql = "CREATE TABLE IF NOT EXISTS "+ SqlCommandEnum.DATABASENAME.databaseAttr+"("+SqlCommandEnum.DBPLAYERID.databaseAttr+" INTEGER PRIMARY KEY AUTOINCREMENT , "
                +SqlCommandEnum.DBPLAYERNAME.databaseAttr+" VARCHAR NOT NULL, "+SqlCommandEnum.DBPLAYERSURNAME.databaseAttr+" VARCHAR  , "+SqlCommandEnum.DBPLAYERBIRTHDAY.databaseAttr+
                " VARCHAR,"+SqlCommandEnum.DBPLAYERTCKNO.databaseAttr+" INTEGER," + SqlCommandEnum.DBPLAYERPHONE.databaseAttr+
                " INTEGER,"+SqlCommandEnum.DBPLAYERCLUB.databaseAttr+" VARCHAR,"+SqlCommandEnum.DBPLAYERLISENCENO.databaseAttr+" INTEGER)";
        db.execSQL(sql);

        //String sql2 = "CREATE TABLE IF NOT EXISTS periodic (id INTEGER PRIMARY KEY AUTOINCREMENT ,playerdate VARCHAR,playervalue FLOAT ,playerid INTEGER,playervaluetype INTEGER,FOREIGN KEY(playerid) REFERENCES players(id) )";
        String sql2 = "CREATE TABLE IF NOT EXISTS "+SqlCommandEnum.DATABASEPERIODICNAME.databaseAttr+" ("+SqlCommandEnum.DBPERIODICID.databaseAttr+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+SqlCommandEnum.DBPERIODICDATE.databaseAttr
                +" VARCHAR,"+SqlCommandEnum.DBPERIODICVALUE.databaseAttr+" FLOAT ,"+SqlCommandEnum.DBPERIODICPLAYERID.databaseAttr+" INTEGER,"+SqlCommandEnum.DBPERIODICVALUETYPE.databaseAttr
                +" INTEGER,FOREIGN KEY("+SqlCommandEnum.DBPERIODICPLAYERID.databaseAttr+") REFERENCES "+SqlCommandEnum.DATABASENAME.databaseAttr+"("+SqlCommandEnum.DBPLAYERID.databaseAttr+")"+" )";
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //will change part !! carefull
        //db.execSQL("DROP TABLE IF EXISTS players");
        db.execSQL("DROP TABLE IF EXISTS "+SqlCommandEnum.DATABASENAME.databaseAttr);
        db.execSQL("DROP TABLE IF EXISTS periodic");
    }
}
