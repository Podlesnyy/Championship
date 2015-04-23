package comuncleandr.twitter.championship;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


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

    public PagerGamersFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagerGamersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagerGamersFragment newInstance( String param1, String param2 )
    {
        PagerGamersFragment fragment = new PagerGamersFragment( );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setHasOptionsMenu( true );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId( );

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_add_gamer )
        {
            TableLayout caseTable = (TableLayout) getView().findViewById( R.id.gamers );
            TableRow caseRow = new TableRow(getActivity());
            EditText name = new EditText(getActivity());
            //name.setLayoutParams(new LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
            caseRow.addView(name);
            caseTable.addView(caseRow,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
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
