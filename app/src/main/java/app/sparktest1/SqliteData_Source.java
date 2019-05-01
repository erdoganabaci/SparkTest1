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

        db.insert(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),null,val);

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
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERCURRENTDATE.databaseAttr(),player.getPlayerCurrentDate());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERHEIGHT.databaseAttr(),player.getPlayerHeight());
        val.put(Sqlite_Layer.SqlCommandEnum.DBPLAYERWEIGHT.databaseAttr(),player.getPlayerWeight());
        //update player id's rows
        db.update(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),val,Sqlite_Layer.SqlCommandEnum.DBPLAYERID.databaseAttr()+"="+player.getPlayerId(),null);

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
        Cursor cursor = db.query(Sqlite_Layer.SqlCommandEnum.DATABASENAME.databaseAttr(),playerColumns,"id="+id,null,null,null,null);
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


}
