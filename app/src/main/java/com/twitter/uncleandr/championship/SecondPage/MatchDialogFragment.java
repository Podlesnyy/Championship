package com.twitter.uncleandr.championship.SecondPage;

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

import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.DAO.Gamer;
import com.twitter.uncleandr.championship.R;

import java.util.ArrayList;


/**
 * Created by zexir on 18.05.2015.
 */
public class MatchDialogFragment extends DialogFragment
{
    private DialogInterface.OnClickListener onOk;
    private Gamer gamer1;
    private Gamer gamer2;
    private Game game;
    private View view;

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
        if ( first && gamer1 != null )
        {
            spinner.setSelection( item( gamers, gamer1.getId() ) );
        }
        else if ( gamer2 != null )
        {
            spinner.setSelection( item( gamers, gamer2.getId() ) );
        }
    }

    private int item( ArrayList< Gamer > gamers, int hashCode )
    {
        for ( int i = 0; i <= gamers.size(); i++ )
        {
            if ( gamers.get( i ).getId() == hashCode )
            {
                return i;
            }
        }
        return 0;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        view = getActivity().getLayoutInflater()
                .inflate( R.layout.dialog_match, null );
        ArrayList< Gamer > gamers = new ArrayList<>( game.getGamers() );
        Dialog dlg = new AlertDialog.Builder( getActivity() ).setTitle( R.string.match ).setView( view ).setPositiveButton( android.R.string.ok,
                onOk ).create();
        dlg.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE );
        prepareSpinner( ( Spinner ) view.findViewById( R.id.gamer1Spinner ), gamers, true );
        prepareSpinner( ( Spinner ) view.findViewById( R.id.gamer2Spinner ), gamers, false );
        return dlg;
    }

    public Pair< Gamer, Gamer > getGamers()
    {
        return new Pair<>( ( Gamer ) ( ( Spinner ) view.findViewById( R.id.gamer1Spinner ) ).getSelectedItem(),
                ( Gamer ) ( ( Spinner ) view.findViewById( R.id.gamer2Spinner ) ).getSelectedItem() );
    }
}
