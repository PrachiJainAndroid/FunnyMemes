package com.laughat.funnymemes.base.rx


import io.reactivex.Scheduler
/**
 * A {@code Scheduler} is an object that specifies an API for scheduling
 * units of work provided in the form of {@link Runnable}s to be
 * executed without delay (effectively as soon as possible), after a specified time delay or periodically
 * and represents an abstraction over an asynchronous boundary that ensures
 * these units of work get executed by some underlying task-execution scheme
 * (such as custom Threads, event loop, {@link java.util.concurrent.Executor Executor} or Actor system)
 * with some uniform properties and guarantees regardless of the particular underlying
 * scheme.
 * <p>
 * You can get various standard, RxJava-specific instances of this class via
 * the static methods of the {@link io.reactivex.schedulers.Schedulers} utility class.
 * <p>
 * The so-called {@link Worker}s of a {@code Scheduler} can be created via the {@link #createWorker()} method which allow the scheduling
 * of multiple {@link Runnable} tasks in an isolated manner. {@code Runnable} tasks scheduled on a {@code Worker} are guaranteed to be
 * executed sequentially and in a non-overlapping fashion. Non-delayed {@code Runnable} tasks are guaranteed to execute in a
 * First-In-First-Out order but their execution may be interleaved with delayed tasks.
 * In addition, outstanding or running tasks can be cancelled together via
 * {@link Worker#dispose()} without affecting any other {@code Worker} instances of the same {@code Scheduler}.
 * <p>
 * Implementations of the {@link #scheduleDirect} and {@link Worker#schedule} methods are encouraged to call the {@link io.reactivex.plugins.RxJavaPlugins#onSchedule(Runnable)}
 * method to allow a scheduler hook to manipulate (wrap or replace) the original {@code Runnable} task before it is submitted to the
 * underlying task-execution scheme.
 * <p>
 * The default implementations of the {@code scheduleDirect} methods provided by this abstract class
 * delegate to the respective {@code schedule} methods in the {@link Worker} instance created via {@link #createWorker()}
 * for each individual {@link Runnable} task submitted. Implementors of this class are encouraged to provide
 * a more efficient direct scheduling implementation to avoid the time and memory overhead of creating such {@code Worker}s
 * for every task.
 * This delegation is done via special wrapper instances around the original {@code Runnable} before calling the respective
 * {@code Worker.schedule} method. Note that this can lead to multiple {@code RxJavaPlugins.onSchedule} calls and potentially
 * multiple hooks applied. Therefore, the default implementations of {@code scheduleDirect} (and the {@link Worker#schedulePeriodically(Runnable, long, long, TimeUnit)})
 * wrap the incoming {@code Runnable} into a class that implements the {@link io.reactivex.schedulers.SchedulerRunnableIntrospection}
 * interface which can grant access to the original or hooked {@code Runnable}, thus, a repeated {@code RxJavaPlugins.onSchedule}
 * can detect the earlier hook and not apply a new one over again.
 * <p>
 * The default implementation of {@link #now(TimeUnit)} and {@link Worker#now(TimeUnit)} methods to return current
 * {@link System#currentTimeMillis()} value in the desired time unit. Custom {@code Scheduler} implementations can override this
 * to provide specialized time accounting (such as virtual time to be advanced programmatically).
 * Note that operators requiring a {@code Scheduler} may rely on either of the {@code now()} calls provided by
 * {@code Scheduler} or {@code Worker} respectively, therefore, it is recommended they represent a logically
 * consistent source of the current time.
 * <p>
 * The default implementation of the {@link Worker#schedulePeriodically(Runnable, long, long, TimeUnit)} method uses
 * the {@link Worker#schedule(Runnable, long, TimeUnit)} for scheduling the {@code Runnable} task periodically.
 * The algorithm calculates the next absolute time when the task should run again and schedules this execution
 * based on the relative time between it and {@link Worker#now(TimeUnit)}. However, drifts or changes in the
 * system clock could affect this calculation either by scheduling subsequent runs too frequently or too far apart.
 * Therefore, the default implementation uses the {@link #clockDriftTolerance()} value (set via
 * {@code rx2.scheduler.drift-tolerance} in minutes) to detect a drift in {@link Worker#now(TimeUnit)} and
 * re-adjust the absolute/relative time calculation accordingly.
 * <p>
 * The default implementations of {@link #start()} and {@link #shutdown()} do nothing and should be overridden if the
 * underlying task-execution scheme supports stopping and restarting itself.
 * <p>
 * If the {@code Scheduler} is shut down or a {@code Worker} is disposed, the {@code schedule} methods
 * should return the {@link io.reactivex.disposables.Disposables#disposed()} singleton instance indicating the shut down/disposed
 * state to the caller. Since the shutdown or dispose can happen from any thread, the {@code schedule} implementations
 * should make best effort to cancel tasks immediately after those tasks have been submitted to the
 * underlying task-execution scheme if the shutdown/dispose was detected after this submission.
 * <p>
 * All methods on the {@code Scheduler} and {@code Worker} classes should be thread safe.
 */
interface SchedulerImpl {
    /**
     * Returns a default, shared {@link Scheduler} instance intended for IO-bound work.
     * <p>
     * This can be used for asynchronously performing blocking IO.
     * <p>
     * The implementation is backed by a pool of single-threaded {@link ScheduledExecutorService} instances
     * that will try to reuse previously started instances used by the worker
     * returned by {@link io.reactivex.Scheduler#createWorker()} but otherwise will start a new backing
     * {@link ScheduledExecutorService} instance. Note that this scheduler may create an unbounded number
     * of worker threads that can result in system slowdowns or {@code OutOfMemoryError}. Therefore, for casual uses
     * or when implementing an operator, the Worker instances must be disposed via {@link io.reactivex.Scheduler.Worker#dispose()}.
     * <p>
     * It is not recommended to perform computational work on this scheduler. Use {@link #computation()} instead.
     * <p>
     * Unhandled errors will be delivered to the scheduler Thread's {@link java.lang.Thread.UncaughtExceptionHandler}.
     * <p>
     * You can control certain properties of this standard scheduler via system properties that have to be set
     * before the {@link Schedulers} class is referenced in your code.
     * <p><strong>Supported system properties ({@code System.getProperty()}):</strong>
     * <ul>
     * <li>{@code rx2.io-keep-alive-time} (long): sets the keep-alive time of the {@link #io()} Scheduler workers, default is {@link IoScheduler#KEEP_ALIVE_TIME_DEFAULT}</li>
     * <li>{@code rx2.io-priority} (int): sets the thread priority of the {@link #io()} Scheduler, default is {@link Thread#NORM_PRIORITY}</li>
     * </ul>
     * <p>
     * The default value of this scheduler can be overridden at initialization time via the
     * {@link RxJavaPlugins#setInitIoSchedulerHandler(io.reactivex.functions.Function)} plugin method.
     * Note that due to possible initialization cycles, using any of the other scheduler-returning methods will
     * result in a {@code NullPointerException}.
     * Once the {@link Schedulers} class has been initialized, you can override the returned {@link Scheduler} instance
     * via the {@link RxJavaPlugins#setIoSchedulerHandler(io.reactivex.functions.Function)} method.
     * <p>
     * It is possible to create a fresh instance of this scheduler with a custom ThreadFactory, via the
     * {@link RxJavaPlugins#createIoScheduler(ThreadFactory)} method. Note that such custom
     * instances require a manual call to {@link Scheduler#shutdown()} to allow the JVM to exit or the
     * (J2EE) container to unload properly.
     * <p>Operators on the base reactive classes that use this scheduler are marked with the
     * &#64;{@link io.reactivex.annotations.SchedulerSupport SchedulerSupport}({@link io.reactivex.annotations.SchedulerSupport#IO IO})
     * annotation.
     * @return a {@link Scheduler} meant for IO-bound work
     */
    fun io(): Scheduler

    /** A {@link Scheduler} which executes actions on the Android main thread. */
    fun main(): Scheduler

    /**
     * Returns a default, shared {@link Scheduler} instance intended for computational work.
     * <p>
     * This can be used for event-loops, processing callbacks and other computational work.
     * <p>
     * It is not recommended to perform blocking, IO-bound work on this scheduler. Use {@link #io()} instead.
     * <p>
     * The default instance has a backing pool of single-threaded {@link ScheduledExecutorService} instances equal to
     * the number of available processors ({@link java.lang.Runtime#availableProcessors()}) to the Java VM.
     * <p>
     * Unhandled errors will be delivered to the scheduler Thread's {@link java.lang.Thread.UncaughtExceptionHandler}.
     * <p>
     * This type of scheduler is less sensitive to leaking {@link io.reactivex.Scheduler.Worker} instances, although
     * not disposing a worker that has timed/delayed tasks not cancelled by other means may leak resources and/or
     * execute those tasks "unexpectedly".
     * <p>
     * If the {@link RxJavaPlugins#setFailOnNonBlockingScheduler(boolean)} is set to true, attempting to execute
     * operators that block while running on this scheduler will throw an {@link IllegalStateException}.
     * <p>
     * You can control certain properties of this standard scheduler via system properties that have to be set
     * before the {@link Schedulers} class is referenced in your code.
     * <p><strong>Supported system properties ({@code System.getProperty()}):</strong>
     * <ul>
     * <li>{@code rx2.computation-threads} (int): sets the number of threads in the {@link #computation()} Scheduler, default is the number of available CPUs</li>
     * <li>{@code rx2.computation-priority} (int): sets the thread priority of the {@link #computation()} Scheduler, default is {@link Thread#NORM_PRIORITY}</li>
     * </ul>
     * <p>
     * The default value of this scheduler can be overridden at initialization time via the
     * {@link RxJavaPlugins#setInitComputationSchedulerHandler(io.reactivex.functions.Function)} plugin method.
     * Note that due to possible initialization cycles, using any of the other scheduler-returning methods will
     * result in a {@code NullPointerException}.
     * Once the {@link Schedulers} class has been initialized, you can override the returned {@link Scheduler} instance
     * via the {@link RxJavaPlugins#setComputationSchedulerHandler(io.reactivex.functions.Function)} method.
     * <p>
     * It is possible to create a fresh instance of this scheduler with a custom ThreadFactory, via the
     * {@link RxJavaPlugins#createComputationScheduler(ThreadFactory)} method. Note that such custom
     * instances require a manual call to {@link Scheduler#shutdown()} to allow the JVM to exit or the
     * (J2EE) container to unload properly.
     * <p>Operators on the base reactive classes that use this scheduler are marked with the
     * &#64;{@link io.reactivex.annotations.SchedulerSupport SchedulerSupport}({@link io.reactivex.annotations.SchedulerSupport#COMPUTATION COMPUTATION})
     * annotation.
     * @return a {@link Scheduler} meant for computation-bound work
     */
    fun computation(): Scheduler
}