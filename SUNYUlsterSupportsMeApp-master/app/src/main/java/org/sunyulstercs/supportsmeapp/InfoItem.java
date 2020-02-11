package org.sunyulstercs.supportsmeapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A class to store the content of a single L3 page
 * @author Ethan Smith
 * @since 04/12/2019
 */
public class InfoItem implements Parcelable
{
    private String title;
    private String desc;
    private String link;
    private String phoneNumber;
    private String email;
    private int iconID;
    private int bannerID;

    /**
     * This is clunky, and I'm not happy about it, but it works.
     * @param items String array of items in the following order: title, description, link, phone number, email
     */
    InfoItem(String[] items)
    {
        this.title = items[0];

        if (items.length >= 2)
        {
            if (!items[1].isEmpty())
            {
                this.desc = items[1];

                if (items.length >= 3) {
                    if (!items[2].isEmpty()) {
                        this.link = items[2];

                        if (items.length >= 4) {
                            if (!items[3].isEmpty()) {
                                this.phoneNumber = items[3];

                                if (items.length >= 5) {
                                    if (!items[4].isEmpty()) {
                                        this.email = items[4];
                                    }
                                }
                            }
                        }
                    }


                }
            }
        }

    }

    String getTitle() {
        return title;
    }

    String getDesc() {
        return desc;
    }

    String getLink() {
        return link;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    String getEmail() {
        return email;
    }

    void setIconID(int iconID)
    {
        this.iconID = iconID;
    }

    int getIconID()
    {
        return iconID;
    }

    void setBannerID(int bannerID)
    {
        this.bannerID = bannerID;
    }

    int getBannerID()
    {
     return this.bannerID;
    }

    /**
     * @return Phone number formatted to be accepted by a Uri for Intent
     */
    String getFormattedPhoneNumber()
    {
        StringBuilder sb = new StringBuilder();
        if (this.hasPhoneNumber())
        {
            sb.append("tel:");
            sb.append(this.phoneNumber);
            //remove garbage characters
            sb.deleteCharAt(sb.indexOf("–"));
            sb.deleteCharAt(sb.indexOf("-"));
            sb.deleteCharAt(sb.indexOf(" "));
            sb.deleteCharAt(sb.indexOf("("));
            sb.deleteCharAt(sb.indexOf(")"));
        }
        return sb.toString();
    }

    boolean hasLink()
    {
        return this.link != null;
    }

    boolean hasPhoneNumber()
    {
        return this.phoneNumber != null;
    }

    boolean hasEmail()
    {
        return this.email != null;
    }

    boolean hasIconID()
    {
        return this.iconID != 0;
    }

    boolean hasBanner()
    {
        return this.bannerID != 0;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.link);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.email);
        dest.writeInt(this.iconID);
        dest.writeInt(this.bannerID);
    }

    private InfoItem(Parcel in)
    {
        this.title = in.readString();
        this.desc = in.readString();
        this.link = in.readString();
        this.phoneNumber = in.readString();
        this.email = in.readString();
        this.iconID = in.readInt();
        this.bannerID = in.readInt();
    }

    public static final Parcelable.Creator<InfoItem> CREATOR = new Parcelable.Creator<InfoItem>()
    {
        @Override
        public InfoItem createFromParcel(Parcel source) {
            return new InfoItem(source);
        }

        @Override
        public InfoItem[] newArray(int size) {
            return new InfoItem[size];
        }
    };

    boolean hasDesc()
    {
        return this.desc != null;
    }

}
