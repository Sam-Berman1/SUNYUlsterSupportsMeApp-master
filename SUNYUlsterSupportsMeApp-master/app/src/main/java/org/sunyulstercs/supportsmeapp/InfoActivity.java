package org.sunyulstercs.supportsmeapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ethan Smith ethansmitty@gmail.com
 * @since 03/04/2019
 */
public class InfoActivity extends AppCompatActivity
{

    private Bundle extras;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set up views
        setContentView(R.layout.activity_info);
        TextView categoryLabel = findViewById(R.id.categoryLabel);
        ImageView categoryImage = findViewById(R.id.department_icon);
        recyclerView = findViewById(R.id.info_list_view);

        //Set up expandable recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ExpandableInfoAdapter adapter = new ExpandableInfoAdapter(getItems());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (extras.containsKey("ActivityName"))
        {
            categoryLabel.setText(extras.getString("ActivityName"));
        }
        if (extras.containsKey("CategoryImage"))
        {
            categoryImage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), extras.getInt("CategoryImage"))); //set image view
        }
    }

    /**
     * Method to get list of InfoItems in the proper type for ExpandableRecyclerView
     * @return List of ExpandableGroups containing InfoItems
     */
    private List<? extends ExpandableGroup> getItems()
    {
        List<ExpandableGroup> egList = new ArrayList<>();
        extras = getIntent().getExtras();

        if (extras != null)
        {
            //Getting multidimensional array from resource file
            int catDataID = extras.getInt("CategoryData");
            String[][] catData = resourceIDTo2DStringArray(catDataID, getApplicationContext());

            //for (String[] array : catData)
            for (int i = 0; i < catData.length; i++)
            {
                List<InfoItem> infoItemList = new ArrayList<>();

                InfoItem newItem = new InfoItem(catData[i]);

                if (extras.containsKey("DetailIcons")) //if L2 thumbnails exist for this set
                {
                    TypedArray icons = getResources().obtainTypedArray(extras.getInt("DetailIcons"));
                    if (icons.length() > i)
                    {
                        newItem.setIconID(icons.getResourceId(i, 0));
                    }
                    icons.recycle();
                }
                if (extras.containsKey("DetailBanners")) //if L3 banners exist for this set
                {
                    TypedArray banners = getResources().obtainTypedArray(extras.getInt("DetailBanners"));
                    if (banners.length() > i)
                    {
                        newItem.setBannerID(banners.getResourceId(i, 0));
                    }
                    banners.recycle();
                }
                infoItemList.add(newItem);

                //This is kind of confusing, because it's a List containing an ExpandableGroup, which contains a List of InfoItems.
                egList.add(new ExpandableGroup<>(infoItemList.get(0).getTitle(), infoItemList));
            }
        }
        return egList;
    }

    /**
     * A very verbose name so I know exactly what it does, hopefully.
     * @param arrayId2D A resource id of a 2d String array
     * @return A 2D String array taken from the resource id
     */
    String[][] resourceIDTo2DStringArray(int arrayId2D, Context context)
    {
        TypedArray ta = context.getResources().obtainTypedArray(arrayId2D); //Getting multidimensional array from resource file
        String[][] newArray = new String[ta.length()][];
        for (int i = 0; i < ta.length(); ++i)
        { //Now we need to stuff the contents of that TypedArray into an Object[][]
            int id = ta.getResourceId(i, 0);

            if (id > 0)
            {
                newArray[i] = context.getResources().getStringArray(id);
            } else {
                Log.e("ResourceHelper", "Error getting 2d array from file");
            }
        }
        ta.recycle(); //Apparently TypedArray is one of the few java classes (that I've come across) that needs to be recycled to free the memory to be Garbage Collected
        return newArray;
    }
}
