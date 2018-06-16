package ar.davinci.edu.infraestructure.cart;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.models.Flight;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private List<Flight> flightList;

    private CartAdapter() {
    }

    public CartAdapter(Context context, List<Flight> flightList) {
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
    public View getView(int i, View cartView, ViewGroup viewGroup) {

        cartView = LayoutInflater.from(context).inflate(R.layout.fragment_cart_list, viewGroup, false);

        TextView itemTitle = (TextView) cartView.findViewById(R.id.itemFlightTitleTxt);
        TextView itemOrigin = (TextView) cartView.findViewById(R.id.destinationFromTxt);
        TextView itemDestination = (TextView) cartView.findViewById(R.id.destinationToTxt);
        ImageView imageFlight = (ImageView) cartView.findViewById(R.id.itemFlightImg);

        Flight flight = flightList.get(i);

        itemTitle.setText(flight.getPatent());

        itemOrigin.setText(flight.getRoute().getOrigin());
        itemDestination.setText(flight.getRoute().getDestination());

        Picasso.with(context).load(flight.getImgURL())
                .fit()
                .centerCrop()
                .into(imageFlight);

        return cartView;
    }
}
