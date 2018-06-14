package com.test.aiup;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(this, recipeList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecorat(1, dpToPx(2), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareRecipeList();

        Snackbar snackbar =  Snackbar.make(findViewById(R.id.main_layout_id), "", Snackbar.LENGTH_INDEFINITE);

        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Inflate your custom view with an Edit Text
        LayoutInflater objLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View snackView = objLayoutInflater.inflate(R.layout.custom_snac_layout, null);
        // custom_snac_layout is your custom xml

        // button id for snackbar
       /* Button button_search = (Button) snackView.findViewById(R.id.search);
        button_search.setOnClickListener(this);
        Button button_home = (Button) snackView.findViewById(R.id.home);
        button_home.setOnClickListener(this);
        Button button_upload = (Button) snackView.findViewById(R.id.upload);
        button_upload.setOnClickListener(this);*/

        layout.addView(snackView, 0);
        snackbar.show();
    }

    /**
     * Adding few albums for testing
     */
    private void prepareRecipeList() {
        int[] covers = new int[]{
                R.drawable.image_idli,
                R.drawable.image_biryani,
                R.drawable.image_dosa,
                R.drawable.image_biscuit,
                R.drawable.image_eggomlet,
                R.drawable.image_breakfast,
                R.drawable.image_chickengravy,
                R.drawable.image_curd,
                R.drawable.image_bacon,
                R.drawable.image_soup,
                R.drawable.image_fish};

        Recipe a = new Recipe("Idli", getResources().getString(R.string.dummy_text), "Shakunthala","Vijayawada",  covers[0], 10);
        recipeList.add(a);

        a = new Recipe("Handi Biryani", getResources().getString(R.string.dummy_text),  "Shakunthala","Vijayawada",  covers[1], 10);
        recipeList.add(a);

        a = new Recipe("Special Dosa", getResources().getString(R.string.dummy_text),  "Shakunthala","Vijayawada",  covers[2], 10);
        recipeList.add(a);

        a = new Recipe("Osmania Biscuit", getResources().getString(R.string.dummy_text), "Shakunthala","Vijayawada",   covers[3], 10);
        recipeList.add(a);

        a = new Recipe("Egg omlet", getResources().getString(R.string.dummy_text), "Shakunthala","Vijayawada", covers[4], 10);
        recipeList.add(a);

        a = new Recipe("Breakfast", getResources().getString(R.string.dummy_text), "Shakunthala","Vijayawada", covers[5], 10);
        recipeList.add(a);

        a = new Recipe("Chicken Gravy", getResources().getString(R.string.dummy_text), "Shakunthala","Vijayawada", covers[6], 10);
        recipeList.add(a);

        a = new Recipe("Mixed Curd", getResources().getString(R.string.dummy_text), "Shakunthala","Vijayawada", covers[7], 10);
        recipeList.add(a);

        a = new Recipe("Tomato Soup", getResources().getString(R.string.dummy_text), "Shakunthala","Vijayawada", covers[8], 10);
        recipeList.add(a);

        a = new Recipe("Fish fry", getResources().getString(R.string.dummy_text), "Shakunthala","Vijayawada", covers[9], 10);
        recipeList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category_screen, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();



        TextView textView = (TextView) searchView.findViewById(R.id.search_src_text);
        textView.setTextColor(Color.BLACK);


        searchView.setSubmitButtonEnabled(true);
        //searchView.setOnQueryTextListener(onQueryTextListener);

        return super.onCreateOptionsMenu(menu);
        //return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bell) {
            Toast.makeText(getApplicationContext(), "Bell", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.action_search){
            //Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecorat extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecorat(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
