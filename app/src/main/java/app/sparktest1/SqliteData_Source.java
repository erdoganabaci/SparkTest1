package app.sparktest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SqliteData_Source {

    //kullanıcı ile sqlite_kaynağı arasında köprü görevi görecek aldığım kullanici verilerini
    // db eklicem main activity de alıp göstericek listviewda
    SQLiteDatabase db;//kütüphaneden gelen verileri kullanıyoruz
    Sqlite_Layer mydb;//kendi veritabanı yapım

    public SqliteData_Source(Context context){
        //kendi database initialize ettim.
        mydb = new Sqlite_Layer(context);
    }

    public void openDatabase(){
        // db dediğimde benim database imde açılmış bağlantı oluyor. mydb db ye refer edip db kullanıyoruz mydb üzerinden
        //writable olması database dosyaya yazılabilmesi demek.
        db = mydb.getWritableDatabase();
    }
    public void closeDatabase(){
        mydb.close();
    }
    public void createPlayer(SportPlayer player){
        String sql = "CREATE TABLE IF NOT EXISTS players (id INT PRIMARY KEY AUTOINCREMENT , " +
                "playername VARCHAR , playersurname VARCHAR  , playerbirthday VARCHAR,playertckno VARCHAR," +
                "playerphone VARCHAR,playerclub VARCHAR,playerlicenseno VARCHAR,playercurrentdate VARCHAR,playerheight VARCHAR," +
                "playerweight VARCHAR)";
        //database direk eklenmiyor obje o yüzden parça parça eklicem.Ama insert into komutunu kullanmadan direk android ile database kendi bağlantısı var
        ContentValues val = new ContentValues();
        /*val.put("playername",player.getPlayerName());
        val.put("playersurname",player.getPlayerSurname());
        val.put("playerbirthday",player.getPlayerBirthday());
        val.put("playertckno",player.getPlayerTckNo());
        val.put("playerphone",player.getPlayerPhone());
        val.put("playerclub",player.getPlayerClub());
        val.put("playerlicenseno",player.getPlayerLicenseNo());
        val.put("playercurrentdate",player.getPlayerCurrentDate());
        val.put("playerheight",player.getPlayerHeight());
        val.put("playerweight",player.getPlayerWeight());
        db.insert("players",null,val);*/
        //Sqlite_Layer.SqlCommandEnum dbEnum = new Sqlite_Layer.SqlCommandEnum(); kısayolunu sor herseferinde mi böyle yazcaz
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERNAME.databaseAttr(),player.getPlayerName());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERSURNAME.databaseAttr(),player.getPlayerSurname());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERBIRTHDAY.databaseAttr(),player.getPlayerBirthday());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERTCKNO.databaseAttr(),player.getPlayerTckNo());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERPHONE.databaseAttr(),player.getPlayerPhone());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERCLUB.databaseAttr(),player.getPlayerClub());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERLISENCENO.databaseAttr(),player.getPlayerLicenseNo());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERCURRENTDATE.databaseAttr(),player.getPlayerCurrentDate());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERHEIGHT.databaseAttr(),player.getPlayerHeight());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERWEIGHT.databaseAttr(),player.getPlayerWeight());

        //db.insert(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),null,val);
        int dbPlayerId = (int) db.insert(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),null,val);
        ContentValues val2 = new ContentValues();
       /* val2.put("playerdate","23/07/1997");
        val2.put("playervalue",180);
        val2.put("playervaluetype",1);
        val2.put("playerid",dbPlayerId);*/
       val2.put(Sqlite_Layer.SqlCommandEnum.DBPERIODICDATE.databaseAttr(),player.getPlayerCurrentDate());
       val2.put(Sqlite_Layer.SqlCommandEnum.DBPERIODICVALUE.databaseAttr(),Float.parseFloat(player.getPlayerPeriodicValue()));
       val2.put(Sqlite_Layer.SqlCommandEnum.DBPERIODICVALUETYPE.databaseAttr(),Integer.parseInt(player.getPlayerPeriodicValueType()));
       val2.put(Sqlite_Layer.SqlCommandEnum.DBPERIODICPLAYERID.databaseAttr(),dbPlayerId);

        db.insert("periodic",null,val2);

    }
    public void updatePlayer(SportPlayer player){
        String sql = "CREATE TABLE IF NOT EXISTS players (id INT PRIMARY KEY AUTOINCREMENT , " +
                "playername VARCHAR , playersurname VARCHAR  , playerbirthday VARCHAR,playertckno VARCHAR," +
                "playerphone VARCHAR,playerclub VARCHAR,playerlicenseno VARCHAR,playercurrentdate VARCHAR,playerheight VARCHAR," +
                "playerweight VARCHAR)";
        //database direk eklenmiyor obje o yüzden parça parça eklicem.Ama insert into komutunu kullanmadan direk android ile database kendi bağlantısı var
        ContentValues val = new ContentValues();
        //val.put("id",player.getPlayerId()); we cant change ıd because its primary key
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERNAME.databaseAttr(),player.getPlayerName());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERSURNAME.databaseAttr(),player.getPlayerSurname());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERBIRTHDAY.databaseAttr(),player.getPlayerBirthday());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERTCKNO.databaseAttr(),player.getPlayerTckNo());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERPHONE.databaseAttr(),player.getPlayerPhone());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERCLUB.databaseAttr(),player.getPlayerClub());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERLISENCENO.databaseAttr(),player.getPlayerLicenseNo());
        //güncel zamanı sadece periodic tablosuna ekliyoruz
       // val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERCURRENTDATE.databaseAttr(),player.getPlayerCurrentDate());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERHEIGHT.databaseAttr(),player.getPlayerHeight());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERWEIGHT.databaseAttr(),player.getPlayerWeight());
        //update player id's rows
        db.update(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),val,Sqlite_Layer.SqlCommandEnum.DBPLAYERID.databaseAttr()+"="+player.getPlayerId(),null);
        //güncellleme yaparken periodic tablosuna insert edicez.Çünkü periodic tablosuna devamlı kayıt eklenicek sorgulamada max date olanı çekicez.
        ContentValues val2 = new ContentValues();
        /*
        val2.put("playerdate",player.getPlayerCurrentDate());
        val2.put("playervalue",Float.parseFloat(player.getPlayerPeriodicValue()));
        val2.put("playerid",player.getPlayerId());
        val2.put("playervaluetype",Integer.parseInt(player.getPlayerPeriodicValueType()));
        */
        val2.put(Sqlite_Layer.SqlCommandEnum.DBPERIODICDATE.databaseAttr(),player.getPlayerCurrentDate());
        val2.put(Sqlite_Layer.SqlCommandEnum.DBPERIODICVALUE.databaseAttr(),Float.parseFloat(player.getPlayerPeriodicValue()));
        val2.put(Sqlite_Layer.SqlCommandEnum.DBPERIODICVALUETYPE.databaseAttr(),Integer.parseInt(player.getPlayerPeriodicValueType()));
        val2.put(Sqlite_Layer.SqlCommandEnum.DBPERIODICPLAYERID.databaseAttr(),player.getPlayerId());
        db.insert("periodic",null,val2);


    }

    public void deletePlayer(SportPlayer player){
        int id = player.getPlayerId();
        //db.delete("players","id="+id,null);
        db.delete(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERID.databaseAttr()+"="+id,null);
    }

    public List<SportPlayer> queryPlayerwithId(int id){
        /*String playerColumns [] = {"id","playername","playersurname","playerbirthday",
                "playertckno","playerphone","playerclub","playerlicenseno","playercurrentdate","playerheight","playerweight"};*/
        String playerColumns [] = {Sqlite_Layer.SqlCommandEnum.DBPLAYERID.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERNAME.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERSURNAME.databaseAttr()
                ,Sqlite_Layer.SqlCommandEnum.DBPLAYERBIRTHDAY.databaseAttr(), Sqlite_Layer.SqlCommandEnum.DBPLAYERTCKNO.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERPHONE.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERCLUB.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERLISENCENO.databaseAttr(),
                Sqlite_Layer.SqlCommandEnum.DBPLAYERCURRENTDATE.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERHEIGHT.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERWEIGHT.databaseAttr()};
        List<SportPlayer> playerQueryList = new ArrayList<SportPlayer>();
        //Cursor cursor = db.query(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),playerColumns,"id="+id,null,null,null,null);
        Cursor cursor = db.query(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),playerColumns,Sqlite_Layer.SqlCommandEnum.DBPLAYERID.databaseAttr()+"="+id,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int playerId = cursor.getInt(0);
            String playerName = cursor.getString(1);
            String playerSurname = cursor.getString(2);
            String playerBirthday = cursor.getString(3);
            String playerTckNo = cursor.getString(4);
            String playerPhone =cursor.getString(5) ;
            String playerClub =cursor.getString(6) ;
            String playerLicenseNo =cursor.getString(7) ;
            String playerCurrentData =cursor.getString(8) ;
            String playerHeight =cursor.getString(9) ;
            String playerWeight =cursor.getString(10) ;

            SportPlayer player = new SportPlayer(playerId,playerName,playerSurname,playerBirthday,playerTckNo,
                    playerPhone,playerClub,playerLicenseNo,playerCurrentData,playerHeight,playerWeight);

            playerQueryList.add(player);
            cursor.moveToNext();
        }

        cursor.close();
        return playerQueryList;

    }

    public List<SportPlayer> queryPeriodicWithId(int id){
        //String periodicColumns [] = {"id","playerdate","playervalue","playerid","playervaluetype"};
        String periodicColumns [] = {Sqlite_Layer.SqlCommandEnum.DBPERIODICPLAYERID.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPERIODICDATE.databaseAttr(),
                Sqlite_Layer.SqlCommandEnum.DBPERIODICVALUE.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPERIODICPLAYERID.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPERIODICVALUETYPE.databaseAttr()};

        List<SportPlayer> periodicQueryList = new ArrayList<>();
        //Cursor cursor = db.query("periodic",periodicColumns,"playerid="+id,null,null,null,null);
        Cursor cursor = db.query(Sqlite_Layer.SqlCommandEnum.DATABASEPERIODICNAME.databaseAttr(),periodicColumns,Sqlite_Layer.SqlCommandEnum.DBPERIODICPLAYERID.databaseAttr() +"="+id,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            //int periodicId = cursor.getInt(0);
            String periodicDate = cursor.getString(1);
            Float periodicValue = cursor.getFloat(2);
            //int playerId = cursor.getInt(3);
            int periodicValueType = cursor.getInt(4);

            SportPlayer periodicPlayer = new SportPlayer(periodicDate,periodicValue.toString(),Integer.toString(periodicValueType));
            periodicQueryList.add(periodicPlayer);
            cursor.moveToNext();
        }
        cursor.close();
        return  periodicQueryList;
    }


    public List<SportPlayer> listPlayer(){
        /*String playerColumns [] = {"id","playername","playersurname","playerbirthday",
                "playertckno","playerphone","playerclub","playerlicenseno","playercurrentdate","playerheight","playerweight"};*/
        String playerColumns [] = {Sqlite_Layer.SqlCommandEnum.DBPLAYERID.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERNAME.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERSURNAME.databaseAttr()
                ,Sqlite_Layer.SqlCommandEnum.DBPLAYERBIRTHDAY.databaseAttr(), Sqlite_Layer.SqlCommandEnum.DBPLAYERTCKNO.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERPHONE.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERCLUB.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERLISENCENO.databaseAttr(),
                Sqlite_Layer.SqlCommandEnum.DBPLAYERCURRENTDATE.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERHEIGHT.databaseAttr(),Sqlite_Layer.SqlCommandEnum.DBPLAYERWEIGHT.databaseAttr()};
        List<SportPlayer> playerQueryList = new ArrayList<SportPlayer>();
        List<SportPlayer> playerlist = new ArrayList<SportPlayer>();
        Cursor cursor = db.query(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),playerColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            //kolonları 0. indexi id getirdi.1indexi eleman getirdi yani önce databaseden okuduk şimdi listeye eklicez.
            //int id = cursor.getInt(0);
            // String playerName = cursor.getString(1);
            //SportPlayer player = new SportPlayer(playerName,id);
            // playerlist.add(player);
            int playerId = cursor.getInt(0);
            String playerName = cursor.getString(1);
            String playerSurname = cursor.getString(2);
            SportPlayer player = new SportPlayer(playerName,playerSurname,playerId);
            playerlist.add(player);
            cursor.moveToNext();
        }
        cursor.close();
        return playerlist;
    }
    public void periodicCreate(){
        //Not using this part ı use createPlayer section.
        ContentValues val = new ContentValues();
        val.put("playerdate","23/07/1997");
        val.put("playervalue",180);
        val.put("playervaluetype",1);
        db.insert("periodic",null,val);

    }

}
