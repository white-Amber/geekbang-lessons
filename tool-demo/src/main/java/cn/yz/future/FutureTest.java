package cn.yz.future;

import cn.yz.future.pojo.MedalInfo;
import cn.yz.future.pojo.UserInfo;
import cn.yz.future.service.MedalService;
import cn.yz.future.service.UserInfoService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Date 2023/4/18
 * @Author yuze
 */
public class FutureTest {

    public static void main(String[] args) throws Exception {
//        futureDemo();
//        completableFutureDemo();
//        thenRunDemo();
//        thenAcceptDemo();
//        thenApplyDemo();
//        exceptionallyDemo();
//        whenCompleteDemo();
//        handleDemo();
//        thenCombineAsyncDemo();
//        thenAcceptBothDemo();
//        runAfterBothDemo();
//        allOfDemo();
//        anyOfDemo();
        long startTime = System.currentTimeMillis();
        CompletableFuture<Integer> cf = new CompletableFuture<>();
        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync " + (System.currentTimeMillis()-startTime));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cf.complete(123);
            return null;
        });
        System.out.println("cf.get start..." + (System.currentTimeMillis()-startTime));
        Integer integer = cf.get();
        System.out.println("cf.get end..." + (System.currentTimeMillis()-startTime));
        System.out.println(integer);


    }

    private static void anyOfDemo() throws InterruptedException, ExecutionException {
        // 任意一个任务执行完，就执行anyOf返回的CompletableFuture，并且会返回执行成功任务的结果。如果执行的任务异常，anyOf的CompletableFuture，执行get方法，会抛出异常
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行完了");
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "第一个任务结果";
        });
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> {
            System.out.println("第二个任务执行完了");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            throw new RuntimeException();
            return "第二个任务结果";
        });
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(a, b);
        System.out.println(anyOf.get());
        System.out.println("耗时："+ (System.currentTimeMillis()-startTime));
    }

    private static void allOfDemo() throws InterruptedException, ExecutionException {
        // 所有任务都执行完成后，才执行 allOf返回的CompletableFuture。如果任意一个任务异常，allOf的CompletableFuture，执行get方法，会抛出异常.
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行完了");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "第一个任务结果";
        });
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> {
            System.out.println("第二个任务执行完了");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            throw new RuntimeException();
            return "第二个任务结果";
        });
        CompletableFuture<Void> allOf = CompletableFuture.allOf(a, b);
        System.out.println(allOf.get());
        System.out.println("耗时："+ (System.currentTimeMillis()-startTime));
    }

    private static void runAfterBothDemo() throws InterruptedException, ExecutionException {
        // runAfterBoth 不会把执行结果当做方法入参，且没有返回值。
        CompletableFuture<String> firstTask = CompletableFuture.completedFuture("第一个人任务的结果");
        CompletableFuture<Void> runAfterBoth = CompletableFuture.supplyAsync(() -> "第二个任务的结果").runAfterBoth(firstTask, () -> {
            // 相当于是在两个异步任务执行完成之后，再执行一个回调函数。
            System.out.println("runAfterBoth执行完成");
        });
        System.out.println(runAfterBoth.get());
    }

    private static void thenAcceptBothDemo() throws InterruptedException, ExecutionException {
        // thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
        CompletableFuture<String> firstTask = CompletableFuture.completedFuture("第一个任务的结果");
        CompletableFuture<Void> thenAcceptBoth = CompletableFuture.supplyAsync(() -> "第二个任务的结果").thenAcceptBoth(firstTask, (s, f) -> {
            System.out.println("one= " + f);
            System.out.println("two= " + s);
            System.out.println("thenAcceptBoth执行完成");
        });
        System.out.println(thenAcceptBoth.get());
    }


    private static void thenCombineAsyncDemo() throws InterruptedException, ExecutionException {
        // thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
        CompletableFuture<String> firstTask = CompletableFuture.completedFuture("第一个任务的结果");
        CompletableFuture<String> thenCombine = CompletableFuture.supplyAsync(() -> "第二个任务的结果").thenCombineAsync(firstTask, (s, f) -> {
            System.out.println("one= " + f);
            System.out.println("two= " + s);
            System.out.println("thenCombine执行完成");
            return "两个异步任务的组合";
        });
        System.out.println(thenCombine.get());
    }

    private static void handleDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "firstTaskResult";
        }).handle((lastTaskResult, throwable) -> {
            System.out.println("上个任务执行完啦，还把" + lastTaskResult + "传过来");
            System.out.println("handle执行完了");
            return "handleResult";
        });
        System.out.println(handle.get());
    }

    private static void whenCompleteDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<String> whenComplete = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "firstTaskReturn";
        }, Executors.newFixedThreadPool(1)).whenCompleteAsync((firstTaskResult, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦，还把" + firstTaskResult + "传过来");
            if (firstTaskResult.equals("firstTaskReturn")) {
                System.out.println("第一个任务的返回结果是：" + firstTaskResult);
            }
            System.out.println("whenComplete执行完成");
        });
        System.out.println(whenComplete.get());
    }

    private static void exceptionallyDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Object> exceptionallyDemo = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            throw new RuntimeException();
        }).exceptionally((e) -> {
            e.printStackTrace();
            return "您的程序异常了";
        });
        System.out.println(exceptionallyDemo.get());
    }

    private static void thenApplyDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<String> thenApply = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行");
            return "firstTaskReturn";
        }).thenApply((firstTaskResult) -> {
            if (firstTaskResult.equals("firstTaskReturn")) {
                System.out.println("第一个任务的返回结果是：" + firstTaskResult);
            }
            System.out.println("第二个任务执行完毕");
            return "secondTaskReturn";
        });
        System.out.println(thenApply.get());
    }

    private static void thenAcceptDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行");
            return "firstTaskReturn";
        }).thenAccept((firstTaskResult) -> {
            if (firstTaskResult.equals("firstTaskReturn")) {
                System.out.println("第一个任务的返回结果是：" + firstTaskResult);
            }
            System.out.println("第二个任务执行完毕");
        });
        System.out.println(thenAccept.get());
    }

    private static void thenRunDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<String> firstFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("先执行第一个CompletableFuture方法任务");
            return "第一个CompletableFuture方法返回";
        });
        CompletableFuture<Void> secondFuture = firstFuture.thenRun(() -> {
            System.out.println("执行第二个任务");
        });
        System.out.println(firstFuture.get());
        System.out.println(secondFuture.get());
    }

    private static void completableFutureDemo() throws InterruptedException, ExecutionException, TimeoutException {
        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId = 666L;
        long startTime = System.currentTimeMillis();
        CompletableFuture<UserInfo> userInfoCompletableFuture = CompletableFuture.supplyAsync(() -> userInfoService.getUserInfo(userId));
        Thread.sleep(300);
        CompletableFuture<MedalInfo> medalInfoCompletableFuture = CompletableFuture.supplyAsync(() -> medalService.getMedalInfo(userId));

        UserInfo userInfo = userInfoCompletableFuture.get(2, TimeUnit.SECONDS);
        MedalInfo medalInfo = medalInfoCompletableFuture.get();

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static void futureDemo() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId = 666L;
        long startTime = System.currentTimeMillis();
        FutureTask<UserInfo> userInfoFutureTask = new FutureTask<>(() -> userInfoService.getUserInfo(userId));
        executorService.submit(userInfoFutureTask);
        Thread.sleep(300);

        FutureTask<MedalInfo> medalInfoFutureTask = new FutureTask<>(() -> medalService.getMedalInfo(userId));
        executorService.submit(medalInfoFutureTask);

        UserInfo userInfo = userInfoFutureTask.get();
        MedalInfo medalInfo = medalInfoFutureTask.get();

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
        executorService.shutdown();
    }

}
