package com.test.aiup;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    MultiAutoCompleteTextView autoTextView;

    private AlbumsAdapter adapter;
    private List<Album> albumList;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }

        relativeLayout = (RelativeLayout)findViewById(R.id.relative_layout);
        View view = LayoutInflater.from(this).inflate(R.layout.search_layout,null);

        String[] ingredients = { "Sugar", "salt", "Butter", "Turmeric", "Egg", "Oil",
                "Vinegar", "Cream", "Cashew", "soda", "Tomato", "Onion" };

        autoTextView = (MultiAutoCompleteTextView)view. findViewById(R.id.autocompleteEditTextView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, ingredients);
        //Used to specify minimum number of
        //characters the user has to type in order to display the drop down hint.
        //(View) getResources().getLayout(R.layout.search_layout)

        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        relativeLayout.addView(view);

        //Setting adapter
        autoTextView.setAdapter(arrayAdapter);
        autoTextView.setThreshold(1);
        autoTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

       Snackbar snackbar =  Snackbar.make(findViewById(R.id.main_layout_id), "", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Inflate your custom view with an Edit Text
        LayoutInflater objLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View snackView = objLayoutInflater.inflate(R.layout.custom_snac_layout, null);
        // custom_snac_layout is your custom xml

        // button id for snackbar
        Button button_search = (Button) snackView.findViewById(R.id.search);
        button_search.setOnClickListener(this);
        Button button_home = (Button) snackView.findViewById(R.id.home);
        button_home.setOnClickListener(this);
       // Button button_upload = (Button) snackView.findViewById(R.id.upload);
       // button_upload.setOnClickListener(this);

        layout.addView(snackView, 0);
        snackbar.show();
    }

    public void recyclerView(View view) {

        prepareAlbums();
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon,
                R.drawable.recipe_icon};

        Album a = new Album("Salad", "Salad Soup", covers[0]);
        albumList.add(a);

        a = new Album("Ginger", "Sample Ginger", covers[1]);
        albumList.add(a);

        a = new Album("Garlic", "Garlic Paste", covers[2]);
        albumList.add(a);

        a = new Album("Tomato", "Sample Tomato", covers[3]);
        albumList.add(a);

        a = new Album("Aloo", "Sample Aloo", covers[4]);
        albumList.add(a);

        a = new Album("Egg Curry", "Sample Egg Curry", covers[5]);
        albumList.add(a);

        a = new Album("Veg", "Veg Curries", covers[6]);
        albumList.add(a);

        a = new Album("Avocado", "Sample Avocado", covers[7]);
        albumList.add(a);

        a = new Album("Eister", "Sample Eister", covers[8]);
        albumList.add(a);

        a = new Album("Banana", "Mistaryzsakjs", covers[9]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.search:
                //relativeLayout.removeAllViews();
                //relativeLayout.addView(getLayoutInflater().inflate(R.layout.search_layout, null));
                break;
            case R.id.home:
              //  relativeLayout.removeAllViews();
              //  relativeLayout.addView(getLayoutInflater().inflate(R.layout.home, null));
                break;
            //case R.id.upload:
              //  relativeLayout.removeAllViews();
              //  relativeLayout.addView(getLayoutInflater().inflate(R.layout.upload_layout, null));
            //    break;
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
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

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
