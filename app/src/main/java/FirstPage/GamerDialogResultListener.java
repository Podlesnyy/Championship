package FirstPage;

import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import BO.Gamer;

/**
 * Created by Heisenberg on 28.04.2015.
 */
public class GamerDialogResultListener implements DialogInterface.OnClickListener
{
    GamerDialogFragment dialogFragment;
    private ArrayAdapter< Gamer > adapter;
    private Gamer gamer;
    private Boolean addToAdapter;

    public GamerDialogResultListener( ArrayAdapter< Gamer > adapter, Gamer gamer, Boolean addToAdapter )
    {
        this.adapter = adapter;
        this.gamer = gamer;
        this.addToAdapter = addToAdapter;
    }

    @Override
    public void onClick( DialogInterface dialog, int which )
    {
        String name = dialogFragment.getGamerName();
        if ( name.isEmpty() )
        {
            return;
        }

        gamer.setName( name );
        if ( addToAdapter )
        {
            adapter.add( gamer );
        }
        else
        {
            adapter.notifyDataSetChanged();
        }
    }
}
