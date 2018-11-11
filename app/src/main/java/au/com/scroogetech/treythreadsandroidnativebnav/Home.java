package au.com.scroogetech.treythreadsandroidnativebnav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Home extends AppCompatActivity {

    private MenuItem homeItem;
    private MenuItem storeItem;
    private MenuItem cartItem;
    private MenuItem contactItem;

    //fragments
    private FragmentManager fragMan = getSupportFragmentManager();
    private Fragment homeFrag = new HomeFragment();
    private Fragment storeFrag = new StoreFragment();
    private Fragment cartFrag = new CartFragment();
    private Fragment contactFrag = new ContactFragment();
    private Fragment active;


    private static final String TAG = "Home";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);

                    //set icon to clicked
                    homeItem.setIcon(R.drawable.homeclicked);

                    //set other icons to not clicked
                    storeItem.setIcon(R.drawable.storedefault);
                    cartItem.setIcon(R.drawable.cartdefault);
                    contactItem.setIcon(R.drawable.contactdefault);

                    //load home fragment
                    fragMan.beginTransaction().hide(active).show(homeFrag).commit();
                    active = homeFrag;

                    return true;
                case R.id.navigation_store:
                    //mTextMessage.setText("Store");

                    //set icon to clicked
                    storeItem.setIcon(R.drawable.storeclicked);

                    //set other icons to not clicked
                    homeItem.setIcon(R.drawable.homedefault);
                    cartItem.setIcon(R.drawable.cartdefault);
                    contactItem.setIcon(R.drawable.contactdefault);

                    //load store fragment
                    fragMan.beginTransaction().hide(active).show(storeFrag).commit();
                    active = storeFrag;

                    return true;
                case R.id.navigation_cart:
                    //mTextMessage.setText("Cart");

                    //set icon to clicked
                    cartItem.setIcon(R.drawable.cartclicked);

                    //set other icons to not clicked
                    homeItem.setIcon(R.drawable.homedefault);
                    storeItem.setIcon(R.drawable.storedefault);
                    contactItem.setIcon(R.drawable.contactdefault);

                    //load cart fragment
                    fragMan.beginTransaction().hide(active).show(cartFrag).commit();
                    active = cartFrag;

                    return true;
                case R.id.navigation_contact:
                    //mTextMessage.setText("Contact");

                    //set icon to clicked
                    contactItem.setIcon(R.drawable.contactclicked);

                    //set other icons to not clicked
                    homeItem.setIcon(R.drawable.homedefault);
                    storeItem.setIcon(R.drawable.storedefault);
                    cartItem.setIcon(R.drawable.cartdefault);

                    //load contact fragment
                    fragMan.beginTransaction().hide(active).show(contactFrag).commit();
                    active = contactFrag;

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.TreySplashTheme);

        super.onCreate(savedInstanceState);

        setTheme(R.style.TreyThreadsTheme);
        setContentView(R.layout.activity_home);

        //fragment stuff
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        HomeFragment homeFragment = new HomeFragment();
//        fragmentTransaction.add(R.id.fragmentLayout,homeFragment);
//        fragmentTransaction.commit();

        //HomeFragment homeFragment = HomeFragment.newInstance("param1","param2");
        fragMan.beginTransaction().add(R.id.fragmentLayout,homeFrag).commit();
        active = homeFrag;

        fragMan.beginTransaction().add(R.id.fragmentLayout,storeFrag).hide(storeFrag).commit();
        fragMan.beginTransaction().add(R.id.fragmentLayout,cartFrag).hide(cartFrag).commit();
        fragMan.beginTransaction().add(R.id.fragmentLayout,contactFrag).hide(contactFrag).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //set a null tint on the selected item
        navigation.setItemIconTintList(null);
        //get the items
        homeItem = navigation.getMenu().getItem(0);
        storeItem = navigation.getMenu().getItem(1);
        cartItem = navigation.getMenu().getItem(2);
        contactItem = navigation.getMenu().getItem(3);


    }

}
