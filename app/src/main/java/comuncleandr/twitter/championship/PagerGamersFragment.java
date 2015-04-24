package comuncleandr.twitter.championship;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PagerGamersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PagerGamersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagerGamersFragment extends android.support.v4.app.Fragment
{
    private OnFragmentInteractionListener mListener;
    private ArrayList< Gamer > gamers;
    private TableLayout caseTable;

    public PagerGamersFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param gamers Parameter 1.
     * @return A new instance of fragment PagerGamersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagerGamersFragment newInstance( ArrayList< Gamer > gamers )
    {
        PagerGamersFragment fragment = new PagerGamersFragment( );
        fragment.gamers = gamers;
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setHasOptionsMenu( true );
    }

    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );

        caseTable = ( TableLayout ) getView( ).findViewById( R.id.gamers );
        for ( Gamer gamer : gamers )
        {
            AddRow( gamer );
        }
    }

    private EditText AddRow( Gamer gamer )
    {
        TableRow caseRow = new TableRow( getActivity( ) );
        EditText gamerEditText = new EditText( getActivity( ) );
        gamerEditText.setMaxLines( 1 );
        gamerEditText.setText( gamer.getName( ) );
        caseRow.addView( gamerEditText );
        caseTable.addView( caseRow, new TableLayout.LayoutParams( TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT ) );
        return gamerEditText;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        int id = item.getItemId( );

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_add_gamer )
        {
            Gamer newGamer = new Gamer( );
            gamers.add( newGamer );
            EditText editText = AddRow( newGamer );
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService( Context.INPUT_METHOD_SERVICE );
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_pager_gamers, container, false );
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater )
    {
        inflater.inflate( R.menu.menu_gamers, menu );

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed( Uri uri )
    {
        if ( mListener != null )
        {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onAttach( Activity activity )
    {
        super.onAttach( activity );
        try
        {
            //  mListener = (OnFragmentInteractionListener) activity;
        } catch ( ClassCastException e )
        {
            throw new ClassCastException( activity.toString( )
                    + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach( );
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onFragmentInteraction( Uri uri );
    }

}
