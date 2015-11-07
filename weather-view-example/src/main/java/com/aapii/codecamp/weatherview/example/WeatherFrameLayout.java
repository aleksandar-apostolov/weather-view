package com.aapii.codecamp.weatherview.example;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.netcetera.weather.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by ivilievs on 07.11.2015.
 */
public class WeatherFrameLayout extends FrameLayout {

  private boolean isOverlayAdded;

  public WeatherFrameLayout(Context context) {
    super(context);
    isOverlayAdded = false;
  }

  public WeatherFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    isOverlayAdded = false;
  }

  public WeatherFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    isOverlayAdded = false;
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    if (!isOverlayAdded) {
      isOverlayAdded = true;
      TextView textView = new TextView(getContext());
      textView.setText("cicki");
      textView.setLayoutParams(
          new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
      try {
        GifDrawable drawable = new GifDrawable(getContext().getResources(), R.raw.giphy);
        textView.setBackgroundDrawable(drawable);
        this.addView(textView);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
