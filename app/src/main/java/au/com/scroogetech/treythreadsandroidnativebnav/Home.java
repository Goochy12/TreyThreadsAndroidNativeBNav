package au.com.scroogetech.treythreadsandroidnativebnav;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import au.com.scroogetech.treythreadsandroidnativebnav.fragments.CartFragment;
import au.com.scroogetech.treythreadsandroidnativebnav.fragments.AccountFragment;
import au.com.scroogetech.treythreadsandroidnativebnav.fragments.ContactFragment;
import au.com.scroogetech.treythreadsandroidnativebnav.fragments.HomeFragment;
import au.com.scroogetech.treythreadsandroidnativebnav.fragments.StoreFragment;

public class Home extends AppCompatActivity {

    private MenuItem homeItem;
    private MenuItem storeItem;
    private MenuItem cartItem;
    private MenuItem accountItem;
    private MenuItem contactItem;

    public BottomNavigationView navigation;
    private Menu appBarMenu;

    //fragments
    private FragmentManager fragMan;
    private Fragment homeFrag = new HomeFragment();
    private Fragment storeFrag = new StoreFragment();
    private Fragment cartFrag = new CartFragment();
    private Fragment accountFrag = new AccountFragment();
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
                    accountItem.setIcon(R.drawable.user_default);
                    contactItem.setIcon(R.drawable.contact_clicked);

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
                    accountItem.setIcon(R.drawable.user_default);
                    contactItem.setIcon(R.drawable.contact_clicked);

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
                    accountItem.setIcon(R.drawable.user_default);
                    contactItem.setIcon(R.drawable.contact_clicked);

                    //load cart fragment
                    fragMan.beginTransaction().hide(active).show(cartFrag).commit();
                    active = cartFrag;

                    return true;
                case R.id.navigation_account:
                    //mTextMessage.setText("Contact");

                    //set icon to clicked
                    accountItem.setIcon(R.drawable.user_clicked);

                    //set other icons to not clicked
                    homeItem.setIcon(R.drawable.homedefault);
                    storeItem.setIcon(R.drawable.storedefault);
                    cartItem.setIcon(R.drawable.cartdefault);
                    contactItem.setIcon(R.drawable.contact_clicked);

                    //load contact fragment
                    fragMan.beginTransaction().hide(active).show(accountFrag).commit();
                    active = accountFrag;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");


        //fragment stuff
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        HomeFragment homeFragment = new HomeFragment();
//        fragmentTransaction.add(R.id.fragmentLayout,homeFragment);
//        fragmentTransaction.commit();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //set a null tint on the selected item
        navigation.setItemIconTintList(null);

        //get the items
        homeItem = navigation.getMenu().getItem(0);
        storeItem = navigation.getMenu().getItem(1);
        cartItem = navigation.getMenu().getItem(2);
        accountItem = navigation.getMenu().getItem(3);


        //HomeFragment homeFragment = HomeFragment.newInstance("param1","param2");

//        if (savedInstanceState == null) {
            fragMan = getSupportFragmentManager();
            fragMan.beginTransaction().add(R.id.fragmentLayout, homeFrag).commit();
            active = homeFrag;

            fragMan.beginTransaction().add(R.id.fragmentLayout, storeFrag).hide(storeFrag).commit();
            fragMan.beginTransaction().add(R.id.fragmentLayout, cartFrag).hide(cartFrag).commit();
            fragMan.beginTransaction().add(R.id.fragmentLayout, accountFrag).hide(accountFrag).commit();
            fragMan.beginTransaction().add(R.id.fragmentLayout, contactFrag).hide(contactFrag).commit();


//        }else {
//
//        }



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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
