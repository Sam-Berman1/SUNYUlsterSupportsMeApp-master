package org.sunyulstercs.supportsmeapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * This viewholder holds the "parent" of the expandable group
 * @author Ethan Smith
 * @since 04/12/2019
 */
public class ExpandableInfoViewHolder extends GroupViewHolder
{
    private TextView infoTitle;

    ExpandableInfoViewHolder(View itemView)
    {
        super(itemView);
        infoTitle = itemView.findViewById(R.id.info_title);
    }

    void setInfoTitle(String title) {
        infoTitle.setText(title);
    }

    @Override
    public void expand()
    {
        super.expand();
        Context context = infoTitle.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            infoTitle.setTextColor(context.getColor(R.color.colorWhite));
        }
    }

    @Override
    public void collapse()
    {
        super.collapse();
        Context context = infoTitle.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            infoTitle.setTextColor(context.getColor(R.color.colorWhite));
        }
    }

}
