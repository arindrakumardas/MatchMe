package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.android.view.MenuView;
import se.kth.csc.iprog.dinnerplanner.android.view.MenuViewController;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MenuActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
    	super.onCreate(savedInstanceState);
    	
    	// Set the view for the main activity screen
    	// it must come before any call to findViewById method
        setContentView(R.layout.menu_view);

    	//Load the DinnerModel
    	DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();
    	
    	// Creating the view class instance
    	MenuView menuView = new MenuView(findViewById(R.id.menu_view), model);
    	MenuViewController menuCtrl = new MenuViewController(model, menuView);
    	
    	Button createButton = (Button) this.findViewById(R.id.create_button);
    	createButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MenuActivity.this, SummaryActivity.class);
	    		startActivity(i);
			}
    	});
    }

}
