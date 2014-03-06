package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.android.view.MenuView;
import se.kth.csc.iprog.dinnerplanner.android.view.MenuViewController;
import se.kth.csc.iprog.dinnerplanner.android.view.SummaryView;
import se.kth.csc.iprog.dinnerplanner.android.view.SummaryViewController;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class SummaryActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
    	super.onCreate(savedInstanceState);
    	
    	// Set the view for the main activity screen
    	// it must come before any call to findViewById method
        setContentView(R.layout.summary_view);

    	//Load the DinnerModel
    	DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();
    	
    	TextView totalCost = (TextView) findViewById(R.id.summary_total_cost);
		totalCost.setText("Total Cost: " + model.getTotalMenuPrice() + " kr");
    	TextView numGuests = (TextView) findViewById(R.id.summary_num_guests);
    	numGuests.setText(model.getNumberOfGuests() + " persons");
    	
    	TextView description = (TextView) findViewById(R.id.selected_ingredients_summary);
    	StringBuilder sb = new StringBuilder();
    	for(Ingredient i : model.getAllIngredients()) {
    		sb.append(i.getName()).append("    ").append(i.getQuantity()).append(" ").append(i.getUnit()).append("\n");
    	}
    	description.setText(sb.toString());
    	// Creating the view class instance
    	SummaryView summaryView = new SummaryView(findViewById(R.id.summary_view), model);
    	SummaryViewController summaryCtrl = new SummaryViewController(model, summaryView);
    	
    	Button backButton = (Button) this.findViewById(R.id.back_button);
    	backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(SummaryActivity.this, MenuActivity.class);
	    		startActivity(i);
			}
    	});
    }

}
