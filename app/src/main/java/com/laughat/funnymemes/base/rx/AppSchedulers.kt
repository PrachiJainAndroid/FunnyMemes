package com.laughat.funnymemes.base.rx


import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class AppSchedulers : SchedulerImpl {
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
    override fun io(): Scheduler {
        return Schedulers.io()
    }
    /** A {@link Scheduler} which executes actions on the Android main thread. */
    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
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
    override fun computation(): Scheduler {
        return Schedulers.single()
    }}