package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Observable;
import java.util.Observer;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryView implements Observer {

	View view;
	DinnerModel model;

	public SummaryView(final View view, final DinnerModel model) {
		this.model = model;
		// store in the class the reference to the Android View
		this.view = view;
		//Subscribe to retrieve notifications when the model is updated.
		model.addObserver(this);

		insertImage(model.getSelectedDish(Dish.STARTER), (ImageView) view.findViewById(R.id.selected_starter));
		insertImage(model.getSelectedDish(Dish.MAIN), (ImageView) view.findViewById(R.id.selected_main));
		insertImage(model.getSelectedDish(Dish.DESERT), (ImageView) view.findViewById(R.id.selected_dessert));
		view.findViewById(R.id.selected_starter).setClickable(true);
		view.findViewById(R.id.selected_main).setClickable(true);
		view.findViewById(R.id.selected_dessert).setClickable(true);
	}
	
	/**
	 * Insert the image from the dish to the ImageView
	 */
	private void insertImage(Dish dish, ImageView image) {
			//Load the image from resources
			int imgID = view.getResources().getIdentifier(dish.getImage(), "drawable", view.getContext().getPackageName());
			image.setImageResource(imgID);
			image.getLayoutParams().height = 120;
			image.getLayoutParams().width = 120;

	}

	@Override
	public void update(Observable obs, Object o) {
		if(o instanceof Integer) {
			int arg = (Integer) o;
			if(arg == DinnerModel.STARTER) {
				//Mark the dish as selected
				insertImage(model.getSelectedDish(Dish.STARTER), (ImageView) view.findViewById(R.id.selected_starter));
			} else if(arg == DinnerModel.MAIN) {
				//Mark the dish as selected
				insertImage(model.getSelectedDish(Dish.MAIN), (ImageView) view.findViewById(R.id.selected_main));
			} else if(arg == DinnerModel.DESSERT) {
				//Mark the dish as selected
				insertImage(model.getSelectedDish(Dish.DESERT), (ImageView) view.findViewById(R.id.selected_dessert));
			} else if(arg == DinnerModel.GUESTS) {
				//Update the number of guests in the view
		    	TextView numGuests = (TextView) view.findViewById(R.id.summary_num_guests);
		    	numGuests.setText(model.getNumberOfGuests() + " persons");
			}
		}
		
	}

}
