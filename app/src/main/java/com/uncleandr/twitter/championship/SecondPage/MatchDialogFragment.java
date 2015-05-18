package com.uncleandr.twitter.championship.SecondPage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.uncleandr.twitter.championship.DAO.Game;
import com.uncleandr.twitter.championship.DAO.Gamer;

import java.util.ArrayList;

import comuncleandr.twitter.championship.R;

/**
 * Created by zexir on 18.05.2015.
 */
public class MatchDialogFragment extends DialogFragment
{
    private DialogInterface.OnClickListener onOk;
    private Gamer gamer1;
    private Gamer gamer2;
    private Game game;

    public static MatchDialogFragment newInstance( Game game, Gamer gamer1, Gamer gamer2, DialogInterface.OnClickListener onOk )
    {
        MatchDialogFragment fragment = new MatchDialogFragment();
        fragment.game = game;
        fragment.gamer1 = gamer1;
        fragment.gamer2 = gamer2;
        fragment.onOk = onOk;
        return fragment;
    }

    private void prepareSpinner( final Spinner spinner, ArrayList< Gamer > gamers, Boolean first )
    {
        ArrayAdapter< Gamer > adapter = new ArrayAdapter<>( getActivity(), android.R.layout.simple_spinner_item, gamers );
        spinner.setAdapter( adapter );
        AdapterView.OnItemSelectedListener listener;
        if ( first )
        {
            listener = new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected( AdapterView< ? > parent, View view,
                                            int pos, long id )
                {
                    gamer1 = ( Gamer ) spinner.getSelectedItem();
                }

                public void onNothingSelected( AdapterView< ? > parent )
                {
                }
            };
            spinner.setSelection( adapter.getPosition( gamer1 ) );
        }
        else
        {
            listener = new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected( AdapterView< ? > parent, View view,
                                            int pos, long id )
                {
                    gamer2 = ( Gamer ) spinner.getSelectedItem();
                }

                public void onNothingSelected( AdapterView< ? > parent )
                {
                }
            };
            spinner.setSelection( adapter.getPosition( gamer2 ) );
        }
        spinner.setOnItemSelectedListener( listener );

    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        View view = getActivity().getLayoutInflater()
                .inflate( R.layout.dialog_match, null );
        ArrayList< Gamer > gamers = new ArrayList<>( game.getGamers() );
        prepareSpinner( ( Spinner ) view.findViewById( R.id.gamer1Spinner ), gamers, true );
        prepareSpinner( ( Spinner ) view.findViewById( R.id.gamer2Spinner ), gamers, false );
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
