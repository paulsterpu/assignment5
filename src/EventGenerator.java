import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class EventGenerator extends Thread{
	
	int i;
	String file;
	
	public EventGenerator(int i , String file) {
		this.i= i;
		this.file = file;
	}
	
	synchronized public void run(){
		BufferedReader buffer = null;
		try {
			String line;
			String[] tokens;
			buffer = new BufferedReader(new FileReader(file));
			while ((line = buffer.readLine()) != null) {
				tokens = line.split(",");
				try {
					wait(Integer.parseInt(tokens[0]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Main.executor.execute(((new Event(tokens[1],Integer.parseInt(tokens[2])))));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null)buffer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
