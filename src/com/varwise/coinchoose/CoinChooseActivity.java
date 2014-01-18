package com.varwise.coinchoose;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.varwise.miningprofitabilitylib.MiningActivityBase;

public class CoinChooseActivity extends MiningActivityBase implements OnClickListener {
	public static String ABOUT_MESSAGE = "Coin Choose: BTC and LTC Mining Profitability \n\nBased on coinchoose.com API";
	private static final String LTC_API_BASE = "http://www.coinchoose.com/api.php?base=LTC";
	private static final String BTC_API_BASE = "http://www.coinchoose.com/api.php?base=BTC";
	private static String API_BASE = "http://www.coinchoose.com/api.php?base=BTC";
	
	private ImageButton BTCLogoButton;
	private ImageButton LTCLogoButton;
	private TextView tvSelected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.main_activity);
		coinTable = (TableLayout) findViewById(R.id.table);
		BTCLogoButton = (ImageButton) findViewById(R.id.imageButtonBTC);
		LTCLogoButton = (ImageButton) findViewById(R.id.imageButtonLTC);
		tvSelected = (TextView) findViewById(R.id.tvSelected);
		
		tvSelected.setText("Base Coin:\n\tBTC");
		
		BTCLogoButton.setEnabled(false);
		BTCLogoButton.setOnClickListener(this);
		LTCLogoButton.setOnClickListener(this);
		
		sortColumn = getResources().getString(R.string.profitability);
		
		executeGetCoinProfitabilityTask(API_BASE);
	}
	
	@Override
	public void onClick(View clicked) {
		if(clicked.getId() == R.id.imageButtonBTC){
			API_BASE = BTC_API_BASE;
			executeGetCoinProfitabilityTask(API_BASE);
			tvSelected.setText("Base Coin:\n\tBTC");
			BTCLogoButton.setEnabled(false);
			LTCLogoButton.setEnabled(true);

		}
		else if(clicked.getId() == R.id.imageButtonLTC){
			API_BASE = LTC_API_BASE;
			executeGetCoinProfitabilityTask(API_BASE);
			tvSelected.setText("Base Coin:\n\tLTC");
			LTCLogoButton.setEnabled(false);
			BTCLogoButton.setEnabled(true);
		}
		else{
			super.onClick(clicked);
		}
	}
	
	@Override
	public void showDialog(String title, String message) {
		super.showDialog(title, message);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(R.id.about == item.getItemId()){
			showDialog("About", ABOUT_MESSAGE);
		}
		else if(R.id.refresh == item.getItemId()){
			executeGetCoinProfitabilityTask(API_BASE);
		}
		
		return true;
	}

	@Override
	protected void removePleaseWait() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.mainView);
		ll.removeView(findViewById(R.id.pleaseWaitText));		
	}

}
