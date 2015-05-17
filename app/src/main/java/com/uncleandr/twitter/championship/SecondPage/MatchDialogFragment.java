package com.uncleandr.twitter.championship.SecondPage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.uncleandr.twitter.championship.DAO.Gamer;
import com.uncleandr.twitter.championship.DAO.Match;

import comuncleandr.twitter.championship.R;

/**
 * Created by zexir on 18.05.2015.
 */
public class MatchDialogFragment extends DialogFragment
{
    private DialogInterface.OnClickListener onOk;
    private Gamer gamer1;
    private Gamer gamer2;

    public static MatchDialogFragment newInstance( Gamer gamer1, Gamer gamer2, DialogInterface.OnClickListener onOk )
    {
        MatchDialogFragment fragment = new MatchDialogFragment();
        fragment.gamer1 = gamer1;
        fragment.gamer2 = gamer2;
        fragment.onOk = onOk;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        View view = getActivity().getLayoutInflater()
                .inflate( R.layout.dialog_match, null );

        gamer1 = ( ( Gamer ) ( ( Spinner ) view.findViewById( R.id.gamer1Spinner ) ).getSelectedItem() );
        gamer2 = ( ( Gamer ) ( ( Spinner ) view.findViewById( R.id.gamer2Spinner ) ).getSelectedItem() );
        Dialog dlg = new AlertDialog.Builder( getActivity() ).setTitle( R.string.match ).setView( view ).setPositiveButton( android.R.string.ok,
                onOk ).create();
        dlg.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE );
        return dlg;
    }

    public Pair< Gamer, Gamer > getGamers()
    {
        return new Pair<>( gamer1, gamer2 );
    }
}
