package au.com.liamgooch.treythreads;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.exceptions.InvalidArgumentException;

import au.com.liamgooch.treythreads.fragments.AboutFragment;
import au.com.liamgooch.treythreads.fragments.CartFragment;
import au.com.liamgooch.treythreads.fragments.AccountFragment;
import au.com.liamgooch.treythreads.fragments.ContactFragment;
import au.com.liamgooch.treythreads.fragments.HomeFragment;
import au.com.liamgooch.treythreads.fragments.StoreFragment;

public class Home extends AppCompatActivity {

    private MenuItem homeItem;
    private MenuItem storeItem;
    private MenuItem cartItem;
    private MenuItem accountItem;
    private MenuItem contactItem;

//    public AHBottomNavigation navigation;
    public AHBottomNavigation navigation;
    private Menu appBarMenu;

    //fragments
    private FragmentManager fragMan;
    private Fragment homeFrag = new HomeFragment();
    private Fragment storeFrag = new StoreFragment();
    private Fragment cartFrag = new CartFragment();
    private Fragment accountFrag = new AccountFragment();
    private Fragment contactFrag = new ContactFragment();
    private Fragment aboutFrag = new AboutFragment();
//    private Fragment settingsFrag = new SettingsFragment();
    private Fragment active;


    private static final String TAG = "OHERE";
    private BraintreeFragment mBraintreeFragment;
    private static final String BRAINTREE_ACCESS_TOKEN = "access_token$sandbox$rrrnpdfp5mpm83zq$c4626caef58f28a29f18fdece41febc7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.TreySplashTheme);

        super.onCreate(savedInstanceState);

        setTheme(R.style.TreyThreadsTheme);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setTitle("");


        //fragment stuff
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        HomeFragment homeFragment = new HomeFragment();
//        fragmentTransaction.add(R.id.fragmentLayout,homeFragment);
//        fragmentTransaction.commit();



//        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
//        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_3);
//        navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);


        //HomeFragment homeFragment = HomeFragment.newInstance("param1","param2");

//        if (savedInstanceState == null) {
        fragMan = getSupportFragmentManager();
        fragMan.beginTransaction().add(R.id.fragmentLayout, homeFrag).commit();
        active = homeFrag;

        fragMan.beginTransaction().add(R.id.fragmentLayout, storeFrag).hide(storeFrag).commit();
        fragMan.beginTransaction().add(R.id.fragmentLayout, cartFrag).hide(cartFrag).commit();
        fragMan.beginTransaction().add(R.id.fragmentLayout, accountFrag).hide(accountFrag).commit();
        fragMan.beginTransaction().add(R.id.fragmentLayout, contactFrag).hide(contactFrag).commit();
        fragMan.beginTransaction().add(R.id.fragmentLayout, aboutFrag).hide(aboutFrag).commit();

        navigation = (AHBottomNavigation) findViewById(R.id.navigation);
        AHBottomNavigationItem homeItem = new AHBottomNavigationItem(null,R.drawable.homeclicked);
        AHBottomNavigationItem storeItem = new AHBottomNavigationItem(null, R.drawable.storedefault);
        AHBottomNavigationItem cartItem = new AHBottomNavigationItem(null, R.drawable.cartdefault);
        AHBottomNavigationItem userItem = new AHBottomNavigationItem(null, R.drawable.user_default);

        navigation.addItem(homeItem);
        navigation.addItem(storeItem);
        navigation.addItem(cartItem);
        navigation.addItem(userItem);

        navigation.setAccentColor(Color.parseColor("#000000"));
        navigation.setInactiveColor(Color.parseColor("#737373"));
        navigation.setDefaultBackgroundColor(Color.parseColor("#F2F2F2"));
        navigation.setBehaviorTranslationEnabled(false);
        navigation.setCurrentItem(0);
//
//        navigation.setColored(false);
//        navigation.setColoredModeColors(Color.WHITE,ContextCompat.getColor(this, R.color.grey));
//
        navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                return itemSelcted(position);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_nav, menu);

        this.appBarMenu = menu;
        contactItem = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.navigation_contact:

                //set icon to clicked
                contactItem.setIcon(R.drawable.contactdefault);

                //set other icons to not clicked
                accountItem.setIcon(R.drawable.user_default);
                homeItem.setIcon(R.drawable.homedefault);
                storeItem.setIcon(R.drawable.storedefault);
                cartItem.setIcon(R.drawable.cartdefault);

                //load contact fragment
                fragMan.beginTransaction().hide(active).show(contactFrag).commit();
                active = contactFrag;

                return true;
            case R.id.navigation_settings:

                return true;
            case R.id.navigation_about:
                contactItem.setIcon(R.drawable.contact_clicked);
                accountItem.setIcon(R.drawable.user_default);
                homeItem.setIcon(R.drawable.homedefault);
                storeItem.setIcon(R.drawable.storedefault);
                cartItem.setIcon(R.drawable.cartdefault);

                //load about fragment
                fragMan.beginTransaction().hide(active).show(aboutFrag).commit();
                active = aboutFrag;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public boolean itemSelcted(int position){
        switch (position) {
            case 0:
                //mTextMessage.setText(R.string.title_home);


                //load home fragment
                fragMan.beginTransaction().hide(active).show(homeFrag).commit();
                active = homeFrag;

                return true;
            case 1:
                //mTextMessage.setText("Store");

                //set icon to clicked

                //load store fragment
                fragMan.beginTransaction().hide(active).show(storeFrag).commit();
                active = storeFrag;

                return true;
            case 2:
                //mTextMessage.setText("Cart");

                //set icon to clicked

                //load cart fragment
                fragMan.beginTransaction().hide(active).show(cartFrag).commit();
                active = cartFrag;

                return true;
            case 3:
                //mTextMessage.setText("Contact");


                //load contact fragment
                fragMan.beginTransaction().hide(active).show(accountFrag).commit();
                active = accountFrag;

                return true;
        }
        return false;
    }
}
