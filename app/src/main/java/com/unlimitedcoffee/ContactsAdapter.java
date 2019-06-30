package com.unlimitedcoffee;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ContactsAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private SearchView searchView;

    public ContactsAdapter(Context context, Cursor cursor, SearchView sv) {
        super(context, cursor, false);
        mContext = context;
        searchView = sv;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.contact_item_layout, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
        String phone = cursor.getString(cursor.getColumnIndex(
                ContactsContract.Contacts.HAS_PHONE_NUMBER));

        String hasPhone = "Has Phone Number";
        if(phone.equals(0)){
            hasPhone = "Without Phone Number";
        }

        TextView nameTv = (TextView) view.findViewById(R.id.tv_name);
        nameTv.setText(name);

        TextView phoneNo = (TextView) view.findViewById(R.id.tv_phone);
        phoneNo.setText(hasPhone);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView pName = (TextView) view.findViewById(R.id.tv_name);
                searchView.setIconified(true);
                //get contact details and display
                Toast.makeText(context, "Selected Contact "+pName.getText(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}