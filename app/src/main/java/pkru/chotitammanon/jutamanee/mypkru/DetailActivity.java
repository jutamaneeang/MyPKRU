package pkru.chotitammanon.jutamanee.mypkru;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] loginStrings;
    private String latString, lngString, nameString, imageString;
    private ImageView backimageView,humanImageView;
    private TextView nameHumamTextView, nameLoginTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_detial_layout);

        //initial view
        initialView();

        //get intent
        myGetIntent();

        //show view
        showView();

        //controller
        controller();



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        aboutFragment();

    }   //main method

    private void controller() {
        backimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showView() {
        nameHumamTextView.setText(nameString);
        nameLoginTextView.setText(loginStrings[1]);
        Picasso.with(this).load(imageString).into(humanImageView);
    }

    private void initialView() {
        backimageView = (ImageView) findViewById(R.id.btnBack);
        humanImageView = (ImageView) findViewById(R.id.imvHumen);
        nameHumamTextView = (TextView) findViewById(R.id.txtNameFriend);
        nameLoginTextView = (TextView) findViewById(R.id.txtNameLogin);
    }

    private void myGetIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login");
        latString = getIntent().getStringExtra("Lat");
        lngString = getIntent().getStringExtra("Lng");
        nameString = getIntent().getStringExtra("Name");
        imageString = getIntent().getStringExtra("Image");
    }

    private void aboutFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(Double.parseDouble(latString), Double.parseDouble(lngString));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        mMap.addMarker(new MarkerOptions().position(latLng));

    }   //onMapReady
}   //Main Class
