package app.sparktest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SportRegister extends AppCompatActivity {
    EditText sportName;
    EditText sportSurname;
    EditText sportBirthday;
    EditText sportTckNo;
    EditText sportPhone;
    EditText sportClub;
    EditText sportLicenceNo;
    EditText sportCurrentDate;
    EditText sportHeight;
    EditText sportWeight;
    Button saveButton;
    Button updateButton;
    SqliteData_Source data_source ;
    String getListPlayerNameId;
    int playerIDFromIntent;
    MenuInflater menuInflater;
    String info;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (info.equalsIgnoreCase("show")){
            menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.update_sport,menu);

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.update_player){
            playerUpdate();
        }else if (item.getItemId() == R.id.delete_player){
            playerDelete();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_register);
        sportName = findViewById(R.id.SportName);
        sportSurname = findViewById(R.id.SportSurname);
        sportBirthday = findViewById(R.id.SportBirthday);
        sportTckNo = findViewById(R.id.SportTckNo);
        sportPhone = findViewById(R.id.SportPhone);
        sportClub = findViewById(R.id.SportClub);
        sportLicenceNo = findViewById(R.id.SportLicenseNo);
        sportCurrentDate = findViewById(R.id.SportCurrentDate);
        sportHeight = findViewById(R.id.SportHeight);
        sportWeight = findViewById(R.id.SportWeight);
        saveButton = findViewById(R.id.save);
        updateButton = findViewById(R.id.update);
        //edittextte içindeki yazıyı boşalt boşalt
        sportName.setText("");
        sportSurname.setText("");
        sportBirthday.setText("");
        sportTckNo.setText("");
        sportPhone.setText("");
        sportClub.setText("");
        sportLicenceNo.setText("");
        sportCurrentDate.setText("");
        sportHeight.setText("");
        sportWeight.setText("");
        //if you want to query with database you must first open db connection.
        data_source = new SqliteData_Source(this);
        data_source.openDatabase();
        Intent intent = getIntent();
        //key value take info == save or update or show
        info = intent.getStringExtra("info");
        getListPlayerNameId = intent.getStringExtra("playerListName");
        if (info.equalsIgnoreCase("save")){
            saveButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.INVISIBLE);
        }/*else if (info.equalsIgnoreCase("update")){
            // if update info come just show update button
            saveButton.setVisibility(View.INVISIBLE);
            updateButton.setVisibility(View.VISIBLE);
        }*/
        else{
            //bilgileri gösterme yeri iki button da yok save ve update harici getStringExtra ne getirirse
            saveButton.setVisibility(View.INVISIBLE);
            updateButton.setVisibility(View.INVISIBLE);
            playerIDFromIntent = Integer.parseInt(getListPlayerNameId);
            //sportName.setText(getListPlayerNameId);
            //player query getir özelliklerini id göre sorgula.
            playerQueryWithId(playerIDFromIntent);

        }
        data_source = new SqliteData_Source(this);
        data_source.openDatabase();
    }

    public void save(View view){
        SportPlayer player = new SportPlayer(sportName.getText().toString(),sportSurname.getText().toString()
                ,sportBirthday.getText().toString(),sportTckNo.getText().toString(),sportPhone.getText().toString(),
                sportClub.getText().toString(),sportLicenceNo.getText().toString(),sportCurrentDate.getText().toString(),
                sportHeight.getText().toString(),sportWeight.getText().toString());
        SportPlayer playerSingle = new SportPlayer(sportName.getText().toString());
        System.out.println("player :"+player.getPlayerName());
        data_source.createPlayer(player);
        //data_source.createPlayer(player);
        //when you add player to database change page to mainactivity's listview
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(getApplicationContext(),"Database Başarıyla Sporcu Eklendi.",Toast.LENGTH_SHORT).show();
        startActivity(intent);


    }
    public void update(View view){
        Toast.makeText(getApplicationContext(),sportName.getText(),Toast.LENGTH_SHORT).show();

    }

    public void playerQueryWithId(int id){
        //idsine göre 1 tane kullanıcı getir ve edittexte yaz göster
        List<SportPlayer> sportPlayer = data_source.queryPlayerwithId(id);
        for(SportPlayer playerQuery : sportPlayer){
            String playerColumns [] = {"id","playername","playersurname","playerbirthday",
                    "playertckno","playerphone","playerclub","playerlicenseno","playercurrentdate","playerheight","playerweight"};
            sportName.setText(playerQuery.getPlayerName());
            sportSurname.setText(playerQuery.getPlayerSurname());
            sportBirthday.setText(playerQuery.getPlayerBirthday());
            sportTckNo.setText(playerQuery.getPlayerTckNo());
            sportPhone.setText(playerQuery.getPlayerPhone());
            sportClub.setText(playerQuery.getPlayerClub());
            sportLicenceNo.setText(playerQuery.getPlayerLicenseNo());
            sportCurrentDate.setText(playerQuery.getPlayerCurrentDate());
            sportHeight.setText(playerQuery.getPlayerHeight());
            sportWeight.setText(playerQuery.getPlayerWeight());

        }

    }

    public void playerUpdate(){
        SportPlayer playerUpdate = new SportPlayer(sportName.getText().toString(),sportSurname.getText().toString()
                ,sportBirthday.getText().toString(),sportTckNo.getText().toString(),sportPhone.getText().toString(),
                sportClub.getText().toString(),sportLicenceNo.getText().toString(),sportCurrentDate.getText().toString(),
                sportHeight.getText().toString(),sportWeight.getText().toString());
        playerUpdate.setPlayerId(playerIDFromIntent);
        data_source.updatePlayer(playerUpdate);
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(getApplicationContext(),"Database Başarıyla Sporcu Güncellendi.",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    public void playerDelete(){
        //give id and delete player you dont initialize all attribute
        SportPlayer playerUpdate = new SportPlayer(playerIDFromIntent);
        data_source.deletePlayer(playerUpdate);
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(getApplicationContext(),"Databaseden Başarıyla Sporcu Silindi.",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}