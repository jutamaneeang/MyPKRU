package pkru.chotitammanon.jutamanee.mypkru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private String[] loginStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Initial View
        initialView();

        //show Name
        showName();

        //create ListView
        createListView();

    }   //main Method

    private void showName() {

        //Get value from Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        textView.setText(loginStrings[1]);
    }

    private void createListView() {

        try {

            String urlJSON = "http://swiftcodingthai.com/pkru/GetUserMaster.php";
            GetAllData getAllData = new GetAllData(this);
            getAllData.execute(urlJSON);

            String strJSON = getAllData.get();
            Log.d("26MayV1", "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] nameStrings = new String[jsonArray.length()];
            String[] imageStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                imageStrings[i] = jsonObject.getString("Image");
                Log.d("26MayV1", "Result (" + i + " ) ==> " + nameStrings[i] + " : " + imageStrings[i]);
            }   //for


            FriendAdapter friendAdapter = new FriendAdapter(this, nameStrings, imageStrings);
            listView.setAdapter(friendAdapter);

        } catch (Exception e) {
            Log.d("26MayV1", "e createListView ==>" + e.toString());
        }

    }

    private void initialView() {
        textView = (TextView) findViewById(R.id.txtNameLogin);
        listView = (ListView) findViewById(R.id.livFriend);
    }

}   //main class
