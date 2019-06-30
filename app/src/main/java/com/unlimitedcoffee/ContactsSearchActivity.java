package com.unlimitedcoffee;
import android.Manifest;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ContactsSearchActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION = 1;

    private SearchView searchView;
    private ListView contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_search_layout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        contactsList = (ListView) findViewById(R.id.contact_list);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_CONTACTS}, REQUEST_PERMISSION);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(onQueryTextListener);

        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener onQueryTextListener =
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Cursor contacts = getListOfContactNames(query);
                    String[] cursorColumns = {
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY };
                    int[] viewIds = {R.id.tv_name};

                    SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                            ContactsSearchActivity.this,
                            R.layout.contact_item_layout,
                            contacts,
                            cursorColumns,
                            viewIds,
                            0);

                    contactsList.setAdapter(simpleCursorAdapter);
                    contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView,
                                                View view, int i, long l) {
                            //get contact details and display
                            TextView tv = (TextView) view.findViewById(R.id.tv_name);
                            Toast.makeText(ContactsSearchActivity.this,
                                    "Selected Contact "+tv.getText(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Cursor contacts = getListOfContactNames(newText);
                    ContactsAdapter cursorAdapter = new ContactsAdapter
                            (ContactsSearchActivity.this, contacts, searchView);
                    searchView.setSuggestionsAdapter(cursorAdapter);
                    return true;
                }
            };

    public Cursor getListOfContactNames(String searchText) {

        Cursor cur = null;
        ContentResolver cr = getContentResolver();

        String[] mProjection = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.LOOKUP_KEY,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};

        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        String selection = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?";
        String[] selectionArgs = new String[]{"%"+searchText+"%"};

        cur = cr.query(uri, mProjection, selection, selectionArgs, null);

        return cur;
    }

}