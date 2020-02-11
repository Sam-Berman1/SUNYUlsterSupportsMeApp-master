package org.sunyulstercs.supportsmeapp;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * This view holder holds the "expanded" view of an L2 menu item
 * @author Ethan Smith
 * @since 04/12/2019
 */
class ExpandableDetailViewHolder extends ChildViewHolder
{
    private Button moreInfoButton;
    private Button websiteButton;
    private Button phoneButton;
    private TextView infoDesc;
    private InfoItem info;

    ExpandableDetailViewHolder(View view)
    {
        super(view);
        infoDesc = view.findViewById(R.id.info_desc);
        moreInfoButton = view.findViewById(R.id.info_more_button);
        websiteButton = view.findViewById(R.id.info_link_button);
        phoneButton = view.findViewById(R.id.info_phone_button);

        moreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("InfoItem", info);
                v.getContext().startActivity(intent);
            }
        });

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(info.getLink()); // missing 'http://' will cause crash
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(info.getFormattedPhoneNumber()));
                v.getContext().startActivity(intent);
            }
        });
    }

    void OnBind(InfoItem info)
    {
        this.info = info;

        if (info.hasDesc())
        {
            infoDesc.setText(info.getDesc());
        }

        if (info.hasLink())
        {
            websiteButton.setVisibility(View.VISIBLE);
        } else {
            websiteButton.setVisibility(View.INVISIBLE);
        }

        if (info.hasPhoneNumber())
        {
            phoneButton.setVisibility(View.VISIBLE);
        } else {
            phoneButton.setVisibility(View.INVISIBLE);
        }

    }

}
