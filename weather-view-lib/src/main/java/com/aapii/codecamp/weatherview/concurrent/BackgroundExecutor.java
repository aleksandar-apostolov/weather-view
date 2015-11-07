package com.aapii.codecamp.weatherview.concurrent;

import com.stanfy.enroscar.goro.FutureObserver;
import com.stanfy.enroscar.goro.Goro;
import com.stanfy.enroscar.goro.ObservableFuture;

import java.util.concurrent.Callable;

/**
 * An executor that executes tasks on the background thread.
 */
public class BackgroundExecutor {
  private static final Goro goro = Goro.create();

  public static Goro getExecutor() {
    return goro;
  }

  public static <V> ObservableFuture<V> dispatch(Callable<V> callable) {
    return getExecutor().schedule(callable);
  }

  public static <V> void dispatchAndReturn(Callable<V> callable, FutureObserver<V> observer) {
    dispatch(callable).subscribe(observer);
  }
}
