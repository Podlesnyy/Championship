/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twitter.uncleandr.championship.MainActivity;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter< RecyclerViewAdapter.ViewHolder > implements View.OnClickListener, View.OnLongClickListener
{

    private List< Game > items;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter( List< Game > items )
    {
        this.items = items;
    }

    public void setOnItemClickListener( OnItemClickListener onItemClickListener )
    {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
    {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_recycler, parent, false );
        v.setOnClickListener( this );
        v.setOnLongClickListener( this );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position )
    {
        Game item = items.get( position );
        holder.text.setText( item.getName() );
        holder.itemView.setTag( item );
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public void onClick( final View v )
    {
        // Give some time to the ripple to finish the effect
        if ( onItemClickListener != null )
        {
            new Handler().postDelayed( new Runnable()
            {
                @Override
                public void run()
                {
                    onItemClickListener.onItemClick( v, ( Game ) v.getTag() );
                }
            }, 200 );
        }
    }

    @Override
    public boolean onLongClick( final View v )
    {
        if ( onItemClickListener != null )
        {
            new Handler().postDelayed( new Runnable()
            {
                @Override
                public void run()
                {
                    onItemClickListener.onLongClick( v, ( Game ) v.getTag() );
                }
            }, 500 );
        }

        return true;
    }

    public interface OnItemClickListener
    {
        void onItemClick( View view, Game viewModel );
        void onLongClick( View view, Game viewModel );
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView text;

        public ViewHolder( View itemView )
        {
            super( itemView );
            text = ( TextView ) itemView.findViewById( R.id.text );
        }
    }
}