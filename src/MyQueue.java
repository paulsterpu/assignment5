import java.util.concurrent.LinkedBlockingQueue;

public class MyQueue<E> extends LinkedBlockingQueue<E>
{
	public MyQueue(int maxSize)
    {
        super(maxSize);
    }

    @Override
    public boolean offer(E e)
    {
        // turn offer() into blocking call
        try {
            put(e);
            return true;
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return false;
    }
}
