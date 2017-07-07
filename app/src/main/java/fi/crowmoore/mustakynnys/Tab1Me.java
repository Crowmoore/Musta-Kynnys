package fi.crowmoore.mustakynnys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Crowmoore on 07-Jul-17.
 */

public class Tab1Me extends Fragment {

    View rootView;
    DistanceCalculator distanceCalculator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab1_me, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        String name = bundle.getString("name");
        String surname = bundle.getString("surname");
        String imageUrl = bundle.getString("imageUrl");

        TextView nameView = (TextView)rootView.findViewById(R.id.fullname);
        nameView.setText(String.format("%1$s %2$s", name, surname));

        new DownloadImage((ImageView)rootView.findViewById(R.id.profilePic)).execute(imageUrl);

        createDistanceText();
        return rootView;
    }

    private void createDistanceText() {
        distanceCalculator = new DistanceCalculator(rootView.getContext());
        double kynyDistance = distanceCalculator.DistanceToKynnys();
        String message = "You are now " + Double.toString(kynyDistance) + " meters away from Kyny";

        TextView tv = (TextView)rootView.findViewById(R.id.DistanceText);
        tv.setText(message);
    }
}
