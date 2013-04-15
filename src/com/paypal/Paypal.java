package com.paypal;

import java.math.BigDecimal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalPayment;

public class Paypal extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
       // Intent i = new Intent(this, PaypalExpressCheckout.class);
        //this.startActivity(i);
        Button btnPaypal=(Button)findViewById(R.id.bPaypal);
        btnPaypal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				invokeSimplePayment();
				
			}
		});
    }
    
    
    //===============================================================
    private void invokeSimplePayment(){
    	
    	try{
    		
    		PayPalPayment newPayment = new PayPalPayment();
    		newPayment.setSubtotal(BigDecimal.valueOf(10));
    		newPayment.setCurrencyType("USD");
    
    		newPayment.setRecipient("my@email.com");
    		newPayment.setMerchantName("My Company");
    		
    		PayPal pp = PayPal.getInstance();
    		if(pp==null)
    			pp = PayPal.initWithAppID(this, "APP-80W284485P519543T", PayPal.ENV_SANDBOX);//PayPal.ENV_SANDBOX
    		
    		Intent paypalIntent = pp.checkout(newPayment, this);
    		this.startActivityForResult(paypalIntent, 1);
    		
    	}catch(Exception e){e.printStackTrace();}
    }
    
}