package upday.droidconmvvm.schedulers;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Allow providing different types of {@link Scheduler}s.
 */
public interface ISchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler ui();
}
