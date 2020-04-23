package org.sunyulstercs.supportsmeapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * @authors
 * Ethan Smitty & Sam Berman
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);

	}

	@Override
	public void onClick(View view)
	{
		Intent intent = new Intent(this, InfoActivity.class);

		switch(view.getId())
		{
			case R.id.campusResourceButton :
				//Currently only campus resources category has images
				intent.putExtra("CategoryData", R.array.resourcesArrayArray);                                           	 //String data
				intent.putExtra("CategoryImage", R.drawable.campus_resources_icon);                                              	//Int ID for drawable
				intent.putExtra("ActivityName", getResources().getString(R.string.campusResourcesButtonDesc));           											//String data
				intent.putExtra("DetailBanners", R.array.resourcesBannerArray);                                           //Array of int IDs for drawable
				break;

			case R.id.financialButton :
				intent.putExtra("CategoryData", R.array.financialArrayArray);
				intent.putExtra("CategoryImage", R.drawable.financial_icon);
				intent.putExtra("ActivityName", getResources().getString(R.string.financialButtonDesc));
				break;

			case R.id.socialButton :
				intent.putExtra("CategoryData", R.array.socialArrayArray);
				intent.putExtra("CategoryImage", R.drawable.web_social_icon);
				intent.putExtra("ActivityName", getResources().getString(R.string.socialMediaButtonDesc));
				break;

			case R.id.studentLifeButton:
				intent.putExtra("CategoryData", R.array.studentLifeArrayArray);
				intent.putExtra("CategoryImage", R.drawable.student_life_icon);
				intent.putExtra("ActivityName", getResources().getString(R.string.campusLifeButtonDesc));
				break;

			case R.id.communityResourcesButton :
				intent.putExtra("CategoryData", R.array.communityResourcesArrayArray);
				intent.putExtra("CategoryImage", R.drawable.community_resources_icon);
				intent.putExtra("ActivityName", getResources().getString(R.string.communityResourceButtonDesc));
				break;

			case R.id.libraryResourcesButton :
				intent.putExtra("CategoryData", R.array.libraryArrayArray);
				intent.putExtra("CategoryImage", R.drawable.library_resources_icon);
				intent.putExtra("ActivityName", getResources().getString(R.string.libraryButtonDesc));
				break;

			case R.id.studentSupportButton :
				intent.putExtra("CategoryData", R.array.studentSupportArrayArray);
				intent.putExtra("CategoryImage", R.drawable.student_support_icon);
				intent.putExtra("ActivityName", getResources().getString(R.string.studentSupportButtonDesc));
				break;

			case R.id.academicResourcesButton :
				intent.putExtra("CategoryData", R.array.academicResourcesArrayArray);
				intent.putExtra("CategoryImage", R.drawable.academic_resources_icon);
				intent.putExtra("ActivityName", getResources().getString(R.string.academicResourcesButtonDesc));
				break;

			case R.id.transportationButton :
				intent.putExtra("CategoryData", R.array.transportArrayArray);
				intent.putExtra("CategoryImage", R.drawable.transportation_icon);
				intent.putExtra("ActivityName", getResources().getString(R.string.transportationButtonDesc));
				break;
		}
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //If activity is in the stack somewhere, reuse it.
		startActivity(intent);




	}
}
