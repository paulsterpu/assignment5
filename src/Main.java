import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
	static int  corePoolSize  =    10;
	static int  maxPoolSize   =   10;
	static long keepAliveTime = 5000;
	public static ExecutorService executor;
	public static BlockingQueue<Integer> prime = new PriorityBlockingQueue<Integer>(30000);
	public static BlockingQueue<Integer> fact = new PriorityBlockingQueue<Integer>(30000);
	public static BlockingQueue<Integer> square = new PriorityBlockingQueue<Integer>(30000);
	public static BlockingQueue<Integer> fib = new PriorityBlockingQueue<Integer>(30000);
	
	public static void main(String[] args) {
		executor = new ThreadPoolExecutor(
		                corePoolSize,
		                maxPoolSize,
		                keepAliveTime,
		                TimeUnit.MILLISECONDS,
		                new MyQueue<Runnable>(Integer.parseInt(args[0]))
		                );
		Thread[] generator =  new EventGenerator[args.length - 2]; 
		
		for ( int i = 2 ; i < args.length; i++){
			generator[i-2] = new EventGenerator(i,args[i]);
			generator[i-2].start();
		}
		for ( int i = 2 ; i < args.length; i++){
			try {
				generator[i-2].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
		    PrintWriter writerPRIME = new PrintWriter("PRIME.out", "UTF-8");
		    PrintWriter writerFACT = new PrintWriter("FACT.out", "UTF-8");
		    PrintWriter writerSQUARE = new PrintWriter("SQUARE.out", "UTF-8");
		    PrintWriter writerFIB = new PrintWriter("FIB.out", "UTF-8");
		    int primeRez = prime.size();
		    int factRez = fact.size();
		    int squareRez = square.size();
		    int fibRez = fib.size();
		    for ( int i = 0 ; i < primeRez ; i++){
		    	writerPRIME.println(prime.take());
		    }
		    writerPRIME.close();
		    for ( int i = 0 ; i < factRez ; i++){
		    	writerFACT.println(fact.take());
		    }
		    writerFACT.close();
		    for ( int i = 0 ; i < squareRez ; i++){
		    	writerSQUARE.println(square.take());
		    }
		    writerSQUARE.close();
		    for ( int i = 0 ; i < fibRez ; i++){
		    	writerFIB.println(fib.take());
		    }
		    writerFIB.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
