package com.uncleandr.twitter.championship.FirstPage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

/**
 * Created by Leomana on 07.06.2015.
 */
public class GamerFromContactsDialogFragment extends DialogFragment implements GamerName
{
    private static final int CONTACT_PICK_RESULT = 1001;

    private String gamerName;
    private DialogInterface.OnClickListener onOk;

    public static GamerFromContactsDialogFragment newInstance( DialogInterface.OnClickListener onOk )
    {
        GamerFromContactsDialogFragment fragment = new GamerFromContactsDialogFragment();
        fragment.onOk = onOk;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        Intent contactPickerIntent = new Intent( Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        startActivityForResult( contactPickerIntent, CONTACT_PICK_RESULT );
        Dialog dlg = new AlertDialog.Builder( getActivity() ).setTitle( "Игрок" ).create();
        return dlg;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if ( resultCode == Activity.RESULT_OK )
        {
            switch ( requestCode )
            {
                case CONTACT_PICK_RESULT:
                    Uri contactData = data.getData();
                    Cursor c = getActivity().getContentResolver().query( contactData, null, null, null, null );
                    if ( c.moveToNext() )
                    {
                        gamerName = c.getString( c.getColumnIndexOrThrow(
                                ContactsContract.Contacts.DISPLAY_NAME ) );
                    }
                    onOk.onClick( this.getDialog(), 0 );
                    break;
            }
        }
    }

    public String getGamerName()
    {
        return gamerName;
    }
}
