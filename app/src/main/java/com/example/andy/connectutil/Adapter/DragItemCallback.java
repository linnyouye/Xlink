package com.example.andy.connectutil.Adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;

/**
 * Created by 95815 .
 * Date:2017/4/13.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class DragItemCallback extends ItemTouchHelper.Callback {



     private AddEquitAdapter addEquitAdapter =null;
    private OnlineDeviceAdapter onlineDeviceAdapter = null;
    public DragItemCallback(AddEquitAdapter addEquitAdapter,OnlineDeviceAdapter onlineDeviceAdapter) {
        this.addEquitAdapter= addEquitAdapter;
        this.onlineDeviceAdapter = onlineDeviceAdapter;

    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int dragFlag;
        int swipeFlag;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
            swipeFlag = 0;//只允许从右到左的侧滑
        } else {
            dragFlag = 0; //都不滑动
            swipeFlag = 0;//只允许从右到左的侧滑
        }
        return makeMovementFlags(dragFlag, swipeFlag);

    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
            Collections.swap(addEquitAdapter.getEquitmentList(),i,i+1);

            }

        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(addEquitAdapter.getEquitmentList(),i,i-1);

            }

        }
        addEquitAdapter.notifyItemMoved(fromPosition, toPosition);
        onlineDeviceAdapter.notifyItemMoved(fromPosition,toPosition);
        //通知刷新视图和OnbindviewHolder的position，否则position不刷新，OnbindviewHolder里引用position将会出错，移动后点击删除，删除的将是移动位置前的条目
        if (fromPosition < toPosition) {
            addEquitAdapter.notifyItemRangeChanged(fromPosition, toPosition-fromPosition+1);
            onlineDeviceAdapter.notifyItemRangeChanged(fromPosition, toPosition-fromPosition+1);
        } else {
            addEquitAdapter.notifyItemRangeChanged(toPosition,fromPosition-toPosition+1);
            onlineDeviceAdapter.notifyItemRangeChanged(toPosition,fromPosition-toPosition+1);
        }

        return true;


    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;  //super.isLongPressDragEnabled();
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;//          super.isItemViewSwipeEnabled();
    }
}
