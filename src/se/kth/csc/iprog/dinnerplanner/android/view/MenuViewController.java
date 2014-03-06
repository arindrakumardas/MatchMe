package se.kth.csc.iprog.dinnerplanner.android.view;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class MenuViewController implements OnClickListener {

	DinnerModel model;
	MenuView view;
	
	public MenuViewController(final DinnerModel model, final MenuView view) {
		this.model = model;
		this.view = view;
		
		//Set up the spinner
		Spinner spinner = (Spinner) view.view.findViewById(R.id.numberOfGuests);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int num = (Integer) arg0.getSelectedItem();
				model.setNumberOfGuests(num); //Update the number of guests in the model
				TextView totalCost = (TextView) findViewById(R.id.totalCost);
				totalCost.setText("Total Cost: " + model.getTotalMenuPrice() + " kr");
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});
		//Initiate the spinner
		spinner.setSelection(model.getNumberOfGuests()-1); //-1 for the index, not the number
		
		//Listen to clicks on the dishes
		LinearLayout starters = (LinearLayout) findViewById(R.id.starters);
		LinearLayout mains = (LinearLayout) findViewById(R.id.mains);
		LinearLayout desserts = (LinearLayout) findViewById(R.id.desserts);

		addListenersToDishes(starters);
		addListenersToDishes(mains);
		addListenersToDishes(desserts);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Put listeners for the imageViews or the RelativeLayout containing dish image/names
		if(v instanceof RelativeLayout) {
			RelativeLayout rl = (RelativeLayout) v;
			Dialog dialog = createDialog(rl);
			if(dialog != null) {
				dialog.show();
			}
		}
	}
	
	private Dialog createDialog(RelativeLayout rl) {
		for(int j = 0; j < rl.getChildCount(); j++) {
			View currentView = rl.getChildAt(j);
			if(currentView instanceof ImageView) {
				ImageView iv = (ImageView) currentView;
				final Dialog dialog = new Dialog(view.view.getContext());
				dialog.setContentView(R.layout.popup);
				//Set the image to the image of the clicked dish.
				ImageView image = (ImageView) dialog.findViewById(R.id.popupImage);
				image.setImageDrawable(iv.getDrawable());
				//Set the title to the name of the clicked dish.
				TextView name = (TextView) dialog.findViewById(R.id.popupName);
				name.setText((String) rl.getTag()); //The tag will be a String.
				//Set the cost to the cost of the clicked dish.
				TextView cost = (TextView) dialog.findViewById(R.id.popupCost);
				
				//Find our dish
				Dish currentDish = null;
				//We set this tag when creating the RelativeLayout that contains a meal.
				String currentString = (String) rl.getTag();
				for(Dish dish : model.getDishes()) {
					if(dish.getName().equals(currentString)) {
						currentDish = dish;
						cost.setText("Cost: " + currentDish.getCost()*model.getNumberOfGuests() + " kr\n(" + currentDish.getCost() + ") kr per person");
						break;
					}
				}
				final Dish dish = currentDish; //Should always be initialised.
				
				//Add listener to the Choose button
				Button chooseBtn = (Button) dialog.findViewById(R.id.popup_choosebutton);
				chooseBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						model.selectDish(dish);
						dialog.dismiss();
					}
					
				});
				
				//Add listener to the Cancel button
				Button cancelBtn = (Button) dialog.findViewById(R.id.popup_cancelbutton);
				cancelBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						dialog.dismiss();
					}
					
				});
				
				return dialog;
			}
		}
		return null; //Should never happen.
	}
	
	/**
	 * Adds listeners to the dishes contained in the LinearLayout
	 */
	private void addListenersToDishes(LinearLayout ll) {
		for(int i = 0; i < ll.getChildCount(); i++) {
			View currentChild = ll.getChildAt(i);
			if(currentChild instanceof RelativeLayout) {
				RelativeLayout currentLayout = (RelativeLayout) currentChild;
				currentLayout.setClickable(true);
				currentLayout.setOnClickListener(this);
				//Object currentTag = currentLayout.getTag();
				
				for(int j = 0; j < currentLayout.getChildCount(); j++) {
					View currentView = currentLayout.getChildAt(j);
					if(currentView instanceof ImageView) {
						ImageView currentImageView = (ImageView) currentView;
						
						break;
					}
				}
				
//				if(currentTag instance of String) {
//					//We set this tag when creating the RelativeLayout that contains a meal.
//					String currentString = (String) currentTag;
//					if(model.getSelectedDish(type).getName().equals(currentString)) {
//						currentLayout.setBackgroundColor(Color.rgb(188, 13, 16)); //Should be the same color as the text on the start screen!
//					}
//				}
			}
		}
	}
	
	private View findViewById(int id) {
		return view.view.findViewById(id);
	}

}
