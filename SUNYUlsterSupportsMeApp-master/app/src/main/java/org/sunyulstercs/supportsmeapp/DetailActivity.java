package org.sunyulstercs.supportsmeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

/**
 * Level 3 activity
 * @author Ethan Smith
 * @since 04/18/2019
 */
public class DetailActivity extends AppCompatActivity
{
    InfoItem info;
    ImageView departmentIcon;
    ImageView departmentBanner;
    TextView departmentLabelText;
    TextView departmentTitleText;
    TextView departmentDescText;
    TextView departmentPhoneText;
    TextView departmentEmailText;
    Button departmentWebsiteButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        info = (InfoItem) Objects.requireNonNull(getIntent().getExtras()).get("InfoItem");

        //Set up views
        departmentIcon = findViewById(R.id.department_icon);
        departmentBanner = findViewById(R.id.department_banner);
        departmentLabelText = findViewById(R.id.department_label_text);
        departmentTitleText = findViewById(R.id.department_title_text);
        departmentDescText = findViewById(R.id.department_desc_text);
        departmentPhoneText = findViewById(R.id.department_phone_text);
        departmentEmailText = findViewById(R.id.department_email_text);
        departmentWebsiteButton = findViewById(R.id.department_website_button);

        departmentWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(info.getLink()); // missing 'http://' will cause crash
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });

        departmentTitleText.setText(info.getTitle());
        departmentLabelText.setText(info.getTitle());
        departmentDescText.setText(info.getDesc());

        //if phonenumber exists, set up call button
        if (info.hasPhoneNumber())
        {
            departmentPhoneText.setText(info.getPhoneNumber());
            departmentPhoneText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(info.getFormattedPhoneNumber()));
                    v.getContext().startActivity(intent);
                }
            });
        }


        //if email exists, set up email button
        if (info.hasEmail())
        {
            departmentEmailText.setText(info.getEmail());
            departmentEmailText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    String uriText = "mailto:" + Uri.encode(info.getEmail());
                    Uri uri = Uri.parse(uriText);

                    intent.setData(uri);
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                }
            });
        }

        //if thumbnail exists, set up icon ImageView
        if (info.hasIconID())
        {
            departmentIcon.setImageDrawable(departmentIcon.getContext().getDrawable(info.getIconID()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                departmentIcon.setColorFilter(departmentIcon.getContext().getColor(R.color.colorHomeButtons));
            }
        }

        //if banner image exists, set up banner imageview
        if (info.hasBanner())
        {
            departmentBanner.setImageDrawable(departmentBanner.getContext().getDrawable(info.getBannerID()));
        }



    }

}
