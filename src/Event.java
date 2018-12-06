public class Event implements Runnable{
	
	int N;
	String event;

	Event(String event , int N){
		this.event = event;
		this.N = N;
	}
	
	public void prime(){
		int i,j;
		for ( i = N ; i > 1 ; i--){
			for ( j = i/2 ; j > 1 ; j--){
				if ( i%j == 0){
					break;
				}
			}
			if ( j == 1){
				break;
			}
		}
		try {
			Main.prime.put(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void fact(){
		int f,i,j;
		for ( i = N; i >= 1 ; i--){
			f = 1;
			for ( j = 1 ; j <= i ; j++){
				f = f * j;
				if ( f > N ){
					break;
				}
			}
			if ( f <= N ){
				try {
					Main.fact.put(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	public void square(){
		int p = 1,i;		//p = ultimul nr care a pp <= N
		for ( i = 2; i < N ; i++){
			if ( i*i > N ){
				try {
					Main.square.put(p);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			p = i;
		}
	}
	public void fib(){
		int f0 = 0, f1 = 1,aux = 0,i = 0;
		if ( N == 0 ){
			try {
				Main.fib.put(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
		while ( f0 + f1 < N ){
			aux = f0;
			f0 = f1;
			f1 = aux + f1;
			i = i + 1;
		}
		try {
			Main.fib.put(i + 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		if ( event.compareTo("PRIME") == 0){
			prime();
		}
		if ( event.compareTo("FACT") == 0){
			fact();
		}
		if ( event.compareTo("SQUARE") == 0){
			square();
		}
		if ( event.compareTo("FIB") == 0){
			fib();
		}
	}
}
