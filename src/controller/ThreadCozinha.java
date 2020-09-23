package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread {
	private int idthread;
	private Semaphore semaforo;

	public ThreadCozinha(int idthread, Semaphore semaforo) {
		this.idthread = idthread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		cozinhar();
		try {
			semaforo.acquire();
			entrega(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	public void cozinhar() {
		int tempoTotal = 0;
		int tempo = 0;
		String prato = "";
		switch (this.idthread % 2) {
		case 0:
			tempoTotal = (int) ((Math.random() * 601) + 600);
			prato = "Lasanha";
			break;
		case 1:
			tempoTotal = (int) ((Math.random() * 301) + 500);
			prato = "Sopa de Cebola";
			break;
		}

		while (tempo < tempoTotal) {
			if (tempo != 0) {
				System.out.println( prato + " do pedido #" + this.idthread + " esta em " + (tempo * 100) / tempoTotal + "%");
			}
			tempo += 100;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(prato + " do pedido #" + this.idthread + " esta pronto.");
	}

	public void entrega(int tempo) {
		String prato = "";
		if (idthread % 2 == 0) {
			prato = "Lasanha";
		} else {
			prato = "Sopa de Cebola";
		}
		System.out.println("Entregando " + prato + " do pedido #" + idthread);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println(prato + " do pedido #" + idthread + " entregue");
		}
	}

}
