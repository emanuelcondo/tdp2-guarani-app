package ar.edu.uba.fi.tdp2.guaraniapp.comunes;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ar.edu.uba.fi.tdp2.guaraniapp.R;

/**
 * ItemDecoration implementation that applies an inset margin
 * around each child of the RecyclerView. The inset value is controlled
 * by a dimension resource.
 */
public class InserDecoration extends RecyclerView.ItemDecoration {

    private int mInsets;

    public InserDecoration(Context context) {
        mInsets = context.getResources().getDimensionPixelSize(R.dimen.card_size);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //We can supply forced insets for each item view here in the Rect
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}
