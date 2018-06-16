package ar.davinci.edu.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.cart.Cart;
import ar.davinci.edu.infraestructure.models.Flight;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Gson jsonParser;
    private Context context;
    private Flight flight;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        context = getBaseContext();
        cart = Cart.getInstance();


        jsonParser = new Gson();
        Bundle extras = getIntent().getExtras();

        flight = jsonParser.fromJson(extras.get("flight").toString(), Flight.class);

        TextView detailTitle = (TextView) this.findViewById(R.id.txtTitle);
        ImageView imageFlight = (ImageView) this.findViewById(R.id.detailFlightImg);
        TextView detailOrigin = (TextView) this.findViewById(R.id.destinationFromTxt);
        TextView detailDestination = (TextView) this.findViewById(R.id.destinationToTxt);
        TextView descriptionTxt = (TextView) this.findViewById(R.id.descriptionTxt);
        Button backButton = (Button) this.findViewById(R.id.backBtn);
        Button addButton = (Button) this.findViewById(R.id.addBtn);

        ImageView mapsLayout = (ImageView) this.findViewById(R.id.mapView);


        detailTitle.setText(flight.getPatent());
        Picasso.with(context).load(flight.getImgURL())
                .fit()
                .centerCrop()
                .into(imageFlight);


        String lat = String.valueOf(flight.getRoute().getLocation().getLat());
        String lon = String.valueOf(flight.getRoute().getLocation().getLon());
        String location = "http://maps.google.com/maps/api/staticmap?center=" + lat + "," + lon + "&zoom=8&size=200x200&sensor=false";


        Picasso.with(context).load(location)
                .fit()
                .centerCrop()
                .into(mapsLayout);

        mapsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("route", new Gson().toJson(flight.getRoute()));
                context.startActivity(intent);
            }
        });

        detailOrigin.setText(flight.getRoute().getOrigin());
        detailDestination.setText(flight.getRoute().getDestination());
        descriptionTxt.setText(flight.getDescription());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.super.finish();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.addFlight(flight);
                Toast.makeText(getBaseContext(), "Vuelo agregado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
