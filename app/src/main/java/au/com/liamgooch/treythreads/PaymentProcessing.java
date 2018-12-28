package au.com.liamgooch.treythreads;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.exceptions.InvalidArgumentException;

public class PaymentProcessing{
    private BraintreeFragment mBraintreeFragment;
    private Activity home;

    public PaymentProcessing(Activity home){

        this.home = home;
        
    }

    public void openFragment(){
        //Generate payment fragment
        //BRAINTREE PAYPAL
        try {
            mBraintreeFragment = BraintreeFragment.newInstance(home, "");
            // mBraintreeFragment is ready to use!
        } catch (InvalidArgumentException e) {
            // There was an issue with your authorization string.
            Log.i("OHERE", "onCreate: ");
        }
    }
}
