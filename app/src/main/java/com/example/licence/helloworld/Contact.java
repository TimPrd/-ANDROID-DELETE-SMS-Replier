package com.example.licence.helloworld;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licence on 13/03/2018.
 */

public class Contact {

    private String number;
    private String name;

    public Contact(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public static List getContacts(ContentResolver resolver) {
        /*Uri uri = android.provider.Contacts.People.CONTENT_URI;
        Cursor cr = resolver.query(uri,null,null,null,null);
        cr.moveToFirst();
        List<Contact> alContacts = new ArrayList();
        do{
            String phone = cr.getString(cr.getColumnIndex(Contacts.People.NUMBER));
            String name = cr.getString(cr.getColumnIndex(Contacts.People.NAME));
            alContacts.add(new Contact(phone,name));
        }while(cr.moveToNext());*/
        List<Contact> alContacts = new ArrayList();

        Cursor cr = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (cr.moveToNext())
        {
            String name=cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            alContacts.add(new Contact(phone,name));
            Log.i("tes", "Name: " + name);
            Log.i("tes", "Phone Number: " + phone);

        }
        cr.close();

        return alContacts;
    }
}
