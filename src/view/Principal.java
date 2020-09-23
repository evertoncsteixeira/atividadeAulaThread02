package view;
import java.util.concurrent.Semaphore;
import controller.ThreadCozinha;
public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for (int i = 0; i < 5; i++){
			ThreadCozinha tCozinha = new ThreadCozinha(i+1,semaforo);
			tCozinha.start();
		}

	}

}
