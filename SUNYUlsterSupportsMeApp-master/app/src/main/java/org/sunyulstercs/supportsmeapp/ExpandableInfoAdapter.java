package org.sunyulstercs.supportsmeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

/**
 * This adapter sets up expandable recyclerview
 * @author Ethan Smith
 * @since 04/12/2019
 */
public class ExpandableInfoAdapter extends ExpandableRecyclerViewAdapter<ExpandableInfoViewHolder, ExpandableDetailViewHolder>
{
    private Context context;

    ExpandableInfoAdapter(List<? extends ExpandableGroup> groups)
    {
        super(groups);
    }

    @Override
    public ExpandableInfoViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_row,parent,false);
        return new ExpandableInfoViewHolder(view);
    }

    @Override
    public ExpandableDetailViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_row_expanded,parent,false);
        return new ExpandableDetailViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ExpandableDetailViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final InfoItem item = (InfoItem) group.getItems().get(childIndex);
        holder.OnBind(item);
    }

    @Override
    public void onBindGroupViewHolder(ExpandableInfoViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setInfoTitle(group.getTitle());
        InfoItem info = (InfoItem) group.getItems().get(0);
        if (info.hasIconID()) //if an icon exists, set it in the imageview
        {
            holder.setInfoIcon(context.getDrawable(info.getIconID()));
        }
    }
}
