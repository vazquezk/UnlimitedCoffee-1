package com.unlimitedcoffee;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioFocusRequest;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MessageHistoryActivity extends Activity {

    SessionPreferences session;
    FloatingActionButton newMsgBtn;

    /***/
    ArrayList <String>smsMessageList = new ArrayList<String>();
    ListView smsListView;
    MessageHistAdapter msgAdapter;
    ArrayList<String> phoneNumber = new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();
    /**
     * One create method for the message History activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Creating instance of Session preferences to store/check user login status
        session = new SessionPreferences(getApplicationContext());
        session.checkLogin();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_history);

        /*****/
        smsListView = (ListView) findViewById(R.id.lvMsg) ;

        msgAdapter = new MessageHistAdapter (this, phoneNumber , messages);
        smsListView.setAdapter(msgAdapter);
        refreshSMSInbox();

        /*****/
        // assign new message button
        newMsgBtn = (FloatingActionButton) findViewById(R.id.newMsgBtn);
        //send user to new message entry page
        newMsgBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toNewMessage = new Intent(MessageHistoryActivity.this, MainActivity.class);
            startActivity(toNewMessage);
        }
        }); // end newMsgBtn onClick

    } // end onCreate

    private void refreshSMSInbox() {

        Uri inboxURI = Uri.parse("content://sms/inbox");
        String[] requestedColumns = new String[]{"_id", "address", "body"};
        ContentResolver cr = getContentResolver();
        Cursor smsInboxCursor = cr.query(inboxURI, requestedColumns, null, null,null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");

        ArrayList <String> StoredPhoneNumbers = new ArrayList<String>();
        ArrayList <Message> StoredMessages = new ArrayList<Message>();
        ArrayList <Conversation> conversations = new ArrayList<Conversation>();
        smsInboxCursor.moveToFirst(); // last text sent

        if (indexBody < 0 ||  !smsInboxCursor.moveToNext()) return;
        do {
            if (!StoredPhoneNumbers.contains(smsInboxCursor.getString(indexAddress))){
                StoredPhoneNumbers.add(smsInboxCursor.getString(indexAddress)); // add phone number
            }
            StoredMessages.add(new Message(smsInboxCursor.getString(indexAddress),
                smsInboxCursor.getString(indexBody)));    // add message

        } while(smsInboxCursor.moveToNext());

        for (String sNumber: StoredPhoneNumbers){   // group messages per phone number
            ArrayList <String> numMessages = new ArrayList<String>();
            for (Message m: StoredMessages){
                if (m.getNumber().equals(sNumber)){
                    numMessages.add(m.getBody());
                }
            }
            conversations.add(new Conversation(sNumber, numMessages));
        }

        for (Conversation c: conversations) {
            phoneNumber.add(c.getNumber());
            messages.add(c.findLastMessage());
        }
    }
    /**
     * The following two methods create the menu of options in MainActivity
     * @param menu
     * @return true (boolean)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu2, menu);
        return true;
    }   // end onCreateOptionMenu

    /**
     * This method implemented the menu options on the message history activity
     *
     * @param item
     * @return a boolean value to validate the selection.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.newMsgBtn_settings:   // New message
                Intent toNewMessage = new Intent(MessageHistoryActivity.this, MainActivity.class);
                startActivity(toNewMessage);
                return true;
            case R.id.newGrpMsgBtn_settings:    // new Group Message
                Intent toNewGrpMessage = new Intent(MessageHistoryActivity.this, MainActivity.class);
                startActivity(toNewGrpMessage);
                return true;
            case R.id.logout:   // logout
                session.logoutUser();
                return true;
            case R.id.app_settings: // application settings
                Intent settings = new Intent(MessageHistoryActivity.this, AccountSettingsActivity.class);
                startActivity(settings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }   // end switch
    }   // end menu option selection
}   // end class
