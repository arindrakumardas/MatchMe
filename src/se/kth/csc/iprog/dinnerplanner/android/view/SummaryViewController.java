package se.kth.csc.iprog.dinnerplanner.android.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.TextView;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

public class SummaryViewController implements OnClickListener {

	DinnerModel model;
	SummaryView view;
	Dish dish;
	
	public SummaryViewController(final DinnerModel model, final SummaryView view) {
		this.model = model;
		this.view = view;
		
		findViewById(R.id.selected_instructions).setOnClickListener(this);
		findViewById(R.id.selected_starter).setOnClickListener(this);
		findViewById(R.id.selected_main).setOnClickListener(this);
		findViewById(R.id.selected_dessert).setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		// Put listeners for the imageViews or the RelativeLayout containing dish image/names
		TextView description = (TextView) findViewById(R.id.selected_ingredients_summary);
		TextView header = (TextView) findViewById(R.id.ing_sum);
		//TextView header = (TextView) findViewById(R.id.ing_sum);
		//LayoutInflater inflater = (LayoutInflater) this.view.view.getContext().getSystemService(this.view.view.getContext().LAYOUT_INFLATER_SERVICE);
//		ViewStub stub = (ViewStub) findViewById(R.id.summary_stub);
//		stub.setLayoutResource(R.layout.summary_ingredients);
//		stub.inflate();
		
		if (v == findViewById(R.id.selected_instructions)){
			header.setText("Ingredients");
			StringBuilder sb = new StringBuilder();
			for(Ingredient i : model.getAllIngredients()) {
				sb.append(i.getName()).append("    ").append(i.getQuantity()).append(" ").append(i.getUnit()).append("\n");
			}
			description.setText(sb.toString());
		}

		if (v == findViewById(R.id.selected_starter)){

			Dish dish = model.getSelectedDish(Dish.STARTER);
			header.setText(dish.getName());
			
			description.setText(dish.getDescription());
		
		}
		if (v == findViewById(R.id.selected_main)){
			Dish dish = model.getSelectedDish(Dish.MAIN);
			description.setText(dish.getDescription());
			header.setText(dish.getName());
//			description.setText(dish.description);
		}
		if (v == findViewById(R.id.selected_dessert)){
			Dish dish = model.getSelectedDish(Dish.DESERT);
			header.setText(dish.getName());
			description.setText(dish.getDescription());
//			description.setText(dish.description);
		}
	}
	
	private View findViewById(int id) {
		return view.view.findViewById(id);
	}
	
}
