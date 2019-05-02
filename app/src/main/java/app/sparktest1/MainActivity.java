package app.sparktest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //AppCompatActivity
    SqliteData_Source data_source ;
    ArrayAdapter<String> adapter;
    ListView listView ;
    ArrayList<String> playerNameAndSurnameArray;
    EditText playerFilterEditText;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //to show menu sporcu ekle menüsünü gösteriyoruz inflater ile çıkart menuyü
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_sport,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_sport ){
            Intent intent = new Intent(getApplicationContext(),SportRegister.class);
            //Aynı intent page yönlendirceksek hangi menuyü tıkladığını böyle ayırt ederiz.
            intent.putExtra("info","save");
            startActivity(intent);
        }/*else if (item.getItemId() == R.id.update_sport){
            Intent intent = new Intent(getApplicationContext(),SportRegister.class);
            //Aynı intent page yönlendirceksek hangi menuyü tıkladığını böyle ayırt ederiz.
            intent.putExtra("info","update");
            startActivity(intent);
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        playerFilterEditText = findViewById(R.id.searchFilter);
        //uygulama çalışır çalışmaz database bağlantısı açılsın
        data_source = new SqliteData_Source(this);
        data_source.openDatabase();
        playerNameAndSurnameArray = new ArrayList<>();
        //kullanıları listeye aldık bunu adaptor ile listviewa bağlıcaz böylece listviewda görünecek.
        List<SportPlayer> sportPlayers = data_source.listPlayer();
        for(SportPlayer testPlayer : sportPlayers){
            System.out.println("sporcu isimler: "+testPlayer.getPlayerName());
            //playerNameArray.add(testPlayer.getPlayerName());
            String nameAndSurname = testPlayer.getPlayerId() +"-"+testPlayer.getPlayerName() + " " +testPlayer.getPlayerSurname();
            playerNameAndSurnameArray.add(nameAndSurname);
        }
        System.out.println("sporcu listem:"+sportPlayers);
        //adapter = new ArrayAdapter<SportPlayer>(this,android.R.layout.simple_list_item_1,sportPlayers);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,playerNameAndSurnameArray);
        listView.setAdapter(adapter);


        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),SportRegister.class);
                intent.putExtra("info","show");
                //take listview at position name
                //String getListPlayerPositionName = (String)parent.getItemAtPosition(position);
                String getListPositionPlayerName = (String) parent.getItemAtPosition(position);
                String getPlayerListPositionId = parserGetFirstValue(getListPositionPlayerName);//int olarak id yolla
                intent.putExtra("playerListName",getPlayerListPositionId);
                startActivity(intent);
            }
        });

        playerFilterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public String parserGetFirstValue(String word){
        //- işarete göre ikiye ayır diziye kaydet 1-Erdogan Abacı 1 , Erdogan Abacı olarak 2 ye bölüyor - işarete göre
        String tokens[] = word.split("-");
        return tokens[0];
    }


}
