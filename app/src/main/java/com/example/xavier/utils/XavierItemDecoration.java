package com.example.xavier.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;

import static com.example.xavier.app.api.ConstantPool.DIVIDE_DISTANCE;
import static com.example.xavier.app.api.ConstantPool.EDGE_DISTANCE;

/**
 * https://www.jianshu.com/p/a011145c5a1b
 */
public class XavierItemDecoration extends RecyclerView.ItemDecoration {

    private int shortDivide = SizeUtils.dp2px(DIVIDE_DISTANCE);
    private int longDivide = SizeUtils.dp2px(EDGE_DISTANCE);
    // existHeader == 0 == 无 Header
    // existHeader == 1 == 有 Header
    private int existHeader;

    public XavierItemDecoration() {
    }

    public XavierItemDecoration(int existHeader) {
        this.existHeader = existHeader;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int totalCount = parent.getAdapter().getItemCount();
        // Log.i("XavierItemDecoration", "getItemOffsets: " + "position = " + position + " --- totalCount = " + totalCount);
        // 无 Header position == 0 == 第一个
        // 有 Header position == 1 == 第一个
        // position == totalCount - 1 == 最后一个
        if (existHeader == 0) {
            if (position >= 0) {
                int oddEven = position % 2;
                if (position != totalCount - 1) {
                    usualDivide(outRect, oddEven);
                } else {
                    lastDivide(outRect, oddEven);
                }
            }
        } else {
            if (position >= 1) {
                int oddEven = position % 2;
                if (position != totalCount - 1) {
                    usualDivide(outRect, oddEven);
                } else {
                    lastDivide(outRect, oddEven);
                }
            }
        }
    }

    private void usualDivide(@NonNull Rect outRect, int oddEven) {
        divide(outRect,oddEven);
        outRect.top = longDivide;
        outRect.bottom = 0;
    }

    private void lastDivide(@NonNull Rect outRect, int oddEven) {
        divide(outRect,oddEven);
        outRect.top = longDivide;
        outRect.bottom = longDivide;
    }

    private void divide(@NonNull Rect outRect, int oddEven) {
        if (oddEven == existHeader) {
            outRect.left = longDivide;
            outRect.right = shortDivide;
        } else {
            outRect.left = shortDivide;
            outRect.right = longDivide;
        }
    }

}