package ar.davinci.edu.infraestructure.flight;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.models.Flight;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FlightAdapter extends BaseAdapter {

    private Context context;
    private List<Flight> flightList;

    private FlightAdapter() {
    }

    public FlightAdapter(Context context, List<Flight> flightList) {
        this.flightList = flightList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return flightList.size();
    }

    @Override
    public Object getItem(int i) {
        return flightList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(flightList.get(i).getId());
    }

    @Override
    public View getView(int i, View flightView, ViewGroup viewGroup) {

        flightView = LayoutInflater.from(context).inflate(R.layout.fragment_item_list, viewGroup, false);

        TextView itemTitle = (TextView) flightView.findViewById(R.id.itemFlightTitleTxt);
        ImageView imageFlight = (ImageView) flightView.findViewById(R.id.itemFlightImg);

        TextView itemOrigin = (TextView) flightView.findViewById(R.id.destinationFromTxt);
        TextView itemDestination = (TextView) flightView.findViewById(R.id.destinationToTxt);
        RatingBar itemRatingBar = (RatingBar) flightView.findViewById(R.id.ratingBarItem);
        TextView itemPrice = (TextView) flightView.findViewById(R.id.priceTxt);

        Flight flight = flightList.get(i);

        itemTitle.setText(flight.getPatent());
        Picasso.with(context).load(flight.getImgURL())
                .fit()
                .centerCrop()
                .into(imageFlight);

        itemOrigin.setText(flight.getRoute().getOrigin());
        itemDestination.setText(flight.getRoute().getDestination());

        /*
            TODO No anda el ratingBar
        */
        itemRatingBar.setRating(flight.getRating());
        itemPrice.setText("$" + String.valueOf(flight.getPrice()));


        flightView.setOnClickListener(new FlightItemOnClickListener(context, flight));

        return flightView;
    }
}
