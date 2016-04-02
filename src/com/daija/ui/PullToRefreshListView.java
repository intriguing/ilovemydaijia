package com.daija.ui;


import com.example.ilovermydaijia.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PullToRefreshListView
  extends ListView
{
  private ImageView arrowIv;
/*  private int footerHeight;
  private View footerView;*/
/*  private TextView lastUpdateTimeTv;*/
  private RotateAnimation mFlipAnimation;
 private int mHeaderHeight;
/*  private int mHeaderPaddingBottom;
  private int mHeaderPaddingTop;*/
  private View mHeaderView;
  private RotateAnimation mReverseFlipAnimation;
  private ProgressBar progressBar;
/*  private int startY;*/
  private int state = 1;
  private TextView stateTv;
  
  public PullToRefreshListView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public PullToRefreshListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public PullToRefreshListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
/*  private void applyHeaderPadding(MotionEvent paramMotionEvent)
  {
    if (getFirstVisiblePosition() != 0) {}
    int i;
    do
    {
      do
      {
        do
        {
          do
          {
//            return;
            if (this.state == 1)
            {
              setSelection(0);
              this.state = 3;
              changeHeaderByState();
              this.startY = ((int)paramMotionEvent.getY());
            }
          } while ((this.state != 3) && (this.state != 4));
          i = (int)(((int)paramMotionEvent.getY() - this.startY) * 0.333F);
        } while (i < 0);
        if (isVerticalFadingEdgeEnabled()) {
          setVerticalScrollBarEnabled(false);
        }
        setSelection(0);
        int j = this.mHeaderHeight;
        this.mHeaderView.setPadding(0, i - j, 0, 0);
        this.mHeaderView.invalidate();
        if (this.state != 3) {
          break;
        }
      } while (i <= this.mHeaderHeight);
      this.state = 4;
      changeHeaderByState();
//      return;
    } while ((this.state != 4) || (i >= this.mHeaderHeight));
    this.state = 3;
    changeHeaderByState();
  }*/
  
  private void changeHeaderByState()
  {
    switch (this.state)
    {
    case 3: 
      this.arrowIv.setImageResource(R.drawable.ic_pulltorefresh_arrow);
      this.stateTv.setText("下拉可以刷新...");
      this.arrowIv.clearAnimation();
      this.arrowIv.startAnimation(this.mFlipAnimation);
      return;
    case 4: 
      this.arrowIv.setImageResource(R.drawable.ic_pulltorefresh_arrow);
      this.stateTv.setText("松开可以刷新...");
      this.arrowIv.clearAnimation();
      this.arrowIv.startAnimation(this.mReverseFlipAnimation);
      return;
    case 2: 
      this.stateTv.setText("正在刷新...");
      this.progressBar.setVisibility(0);
      this.arrowIv.setVisibility(8);
      this.arrowIv.setImageDrawable(null);
      this.mHeaderView.setPadding(0, 0, 0, 0);
      setVerticalScrollBarEnabled(true);
      return;
      default:    
      this.progressBar.setVisibility(8);
      this.arrowIv.setVisibility(0);
      this.arrowIv.setImageResource(R.drawable.ic_pulltorefresh_arrow);
      this.stateTv.setText("下拉可以刷新...");
      this.mHeaderView.setPadding(0, -this.mHeaderHeight, 0, 0);
      setVerticalScrollBarEnabled(true);
    }

  }
  
  private void init(Context paramContext)
  {
    this.mFlipAnimation = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.mFlipAnimation.setInterpolator(new LinearInterpolator());
    this.mFlipAnimation.setDuration(250L);
    this.mFlipAnimation.setFillAfter(true);
    this.mReverseFlipAnimation = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
    this.mReverseFlipAnimation.setDuration(250L);
    this.mReverseFlipAnimation.setFillAfter(true);
    this.mHeaderView = LayoutInflater.from(paramContext).inflate(R.layout.pull_to_refresh_header, null);
    this.stateTv = ((TextView)this.mHeaderView.findViewById(R.id.state_tv));
/*    this.lastUpdateTimeTv = ((TextView)this.mHeaderView.findViewById(R.id.last_update_time_tv));*/
    this.arrowIv = ((ImageView)this.mHeaderView.findViewById(R.id.pull_to_refresh_image));
    this.progressBar = ((ProgressBar)this.mHeaderView.findViewById(R.id.pull_to_refresh_progress));
/*    measureView(this.mHeaderView);*/
    addHeaderView(this.mHeaderView, null, false);
    this.mHeaderHeight = this.mHeaderView.getMeasuredHeight();
/*    this.mHeaderPaddingTop = this.mHeaderView.getPaddingTop();
    this.mHeaderPaddingBottom = this.mHeaderView.getPaddingBottom();*/
    resetHeader();
  }
  
/*  private void measureView(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams2 = paramView.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null) {
      localLayoutParams1 = new ViewGroup.LayoutParams(-1, -2);
    }
    int j = ViewGroup.getChildMeasureSpec(0, 0, localLayoutParams1.width);
    int i = localLayoutParams1.height;
    for (i = View.MeasureSpec.makeMeasureSpec(i, 1073741824);; j = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(j, i);
      return;
    }
  }*/
  
  private void resetHeader()
  {
    this.mHeaderView.setPadding(this.mHeaderView.getPaddingLeft(), -this.mHeaderHeight, this.mHeaderView.getPaddingRight(), this.mHeaderView.getPaddingBottom());
    this.mHeaderView.invalidate();
  }
  
  /*  public void T(String paramString)
  {
    Log.i("JJ", paramString);
  }
  
  public void addFooterView(View paramView)
  {
    this.footerView = paramView;
    measureView(this.footerView);
    this.footerHeight = this.footerView.getMeasuredHeight();
    super.addFooterView(paramView);
  }
  
  public void hideFooterView()
  {
    if (this.footerView != null)
    {
      this.footerView.setPadding(0, -this.footerHeight, 0, 0);
      this.footerView.invalidate();
    }
  }*/
  
  public void onRefreshComplete()
  {
    this.state = 1;
    changeHeaderByState();
  }
  
 /* public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = 0;
    if (this.state != 2)
    {
      i = getFirstVisiblePosition();
      switch (paramMotionEvent.getAction())
      {
      }
    }
      //return super.onTouchEvent(paramMotionEvent);
      if ((i == 0) && (this.state == 1))
      {
        setSelection(0);
        this.state = 3;
        changeHeaderByState();
        this.startY = ((int)paramMotionEvent.getY());
//        continue;
        applyHeaderPadding(paramMotionEvent);
//        continue;
        if (this.state == 4)
        {
          this.state = 2;
          changeHeaderByState();
          notifyRefresh();
        }
        else if (this.state == 3)
        {
          this.state = 1;
          changeHeaderByState();
        }
      }
      return super.onTouchEvent(paramMotionEvent);
  }*/
  
  public void requestRefresh()
  {
    this.state = 2;
    setSelection(0);
    changeHeaderByState();
  }
  
/*  public void setLastUpdateTime(String paramString)
  {
    this.lastUpdateTimeTv.setText(paramString);
  }
  
  public void showFooterView()
  {
    if (this.footerView != null)
    {
      this.footerView.setPadding(0, 0, 0, 0);
      this.footerView.invalidate();
    }
  }
  */
}
