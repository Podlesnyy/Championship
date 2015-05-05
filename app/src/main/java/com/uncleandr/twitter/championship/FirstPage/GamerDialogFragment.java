package com.uncleandr.twitter.championship.FirstPage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import comuncleandr.twitter.championship.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class GamerDialogFragment extends DialogFragment
{
    private String gamerName;
    private DialogInterface.OnClickListener onOk;
    private EditText gamerNameEdit;

    public static GamerDialogFragment newInstance( String gamerName, DialogInterface.OnClickListener onOk )
    {
        GamerDialogFragment fragment = new GamerDialogFragment();
        fragment.setGamerName( gamerName );
        fragment.onOk = onOk;

        return fragment;
    }


    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        View view = getActivity().getLayoutInflater()
                .inflate( R.layout.dialog_gamer, null );

        gamerNameEdit = ( EditText ) view.findViewById( R.id.editTextGamerName );
        gamerNameEdit.setText( gamerName );
        Dialog dlg = new AlertDialog.Builder( getActivity() ).setTitle( "Игрок" ).setView( view ).setPositiveButton( android.R.string.ok,
                onOk ).create();
        dlg.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE );
        return dlg;
    }

    public String getGamerName()
    {
        return gamerNameEdit.getText().toString();
    }

    public void setGamerName( String gamerName )
    {
        this.gamerName = gamerName;
    }
}
