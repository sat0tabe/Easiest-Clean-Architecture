package jp.nuits.hatpepper.presentations;

import androidx.fragment.app.ListFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import jp.nuits.hatpepper.HatPepperApplication;
import jp.nuits.hatpepper.R;
import jp.nuits.hatpepper.usecases.Restaurant;

public class RestaurantsFragment extends ListFragment {

    @Inject
    RestaurantsViewModel restaurantsViewModel;

    private RestaurantsAdapter restaurantsAdapter;

    public static RestaurantsFragment newInstance() {
        return new RestaurantsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.restaurants_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restaurantsAdapter = new RestaurantsAdapter(getActivity());
        setListAdapter(restaurantsAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((HatPepperApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
        super.onActivityCreated(savedInstanceState);

        restaurantsViewModel.findRestaurants(
                (restaurants) -> updateRestaurants(restaurants));
    }

    public void updateRestaurants(List<Restaurant> restaurants) {
        restaurantsAdapter.clear();
        for (Restaurant restaurant : restaurants) {
            restaurantsAdapter.add(restaurant);
        }
    }
}
