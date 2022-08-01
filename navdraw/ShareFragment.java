
package com.example.edithapp.navdraw;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edithapp.R;

import java.util.ArrayList;
import java.util.HashSet;

public class ShareFragment extends Fragment {
    Context contaxt;
    Activity activity;
    ListView listView;
    public ShareFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navdraw_fragment_share, container, false);  
        contaxt = getContext();
        activity = getActivity();
        listView = view.findViewById(R.id.navbar_share_phone_list);

        if (ContextCompat.checkSelfPermission(contaxt, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, 100);
        } else {
            ArrayList<String> list = getAllContacts();
            ArrayAdapter<String> arr = new ArrayAdapter<String>(
                    contaxt,
                    R.layout.navdraw_share_phone_list_item,
                    list);
            listView.setAdapter(arr);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                String data[] = selectedItem.split(",");
                sendSMS(data[1]);
            }
        });
        //sendSMS();
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getAllContacts();
    }
    protected void sendSMS(String contact) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String (contact));
        smsIntent.putExtra("sms_body"  , "Expense for 4th month is 2000");
        try {
            startActivity(smsIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(contaxt,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("Range")
    private ArrayList getAllContacts() {
        ArrayList<String> arrayList = new ArrayList();
        ContentResolver contentResolver= getActivity().getContentResolver();
        String[] fieldListProjection = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER,
                ContactsContract.Contacts.HAS_PHONE_NUMBER
        };

        Cursor phones = contentResolver
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                        , fieldListProjection, null, null, null);
        HashSet<String> normalizedNumbersAlreadyFound = new HashSet<>();

        if (phones != null && phones.getCount() > 0) {
            while (phones.moveToNext()) {
                String normalizedNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
                if (Integer.parseInt(phones.getString(phones.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    if (normalizedNumbersAlreadyFound.add(normalizedNumber)) {
                        String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                        String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        arrayList.add(name+","+phoneNumber);
                    }
                }
            }
            phones.close();
        }

        return arrayList;
    }
}
