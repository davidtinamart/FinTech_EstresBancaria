package org.example;

public class Lanzador {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta(10000);
        int numThreads = 400 + 200 + 600 + 400 + 200 + 600; // (1000 depósitos y 1400 retiros)

        Thread[] threads = new Thread[numThreads];

        int threadIndex = 0;

        // Crear hilos para depósitos de 100 euros
        for (int i = 0; i < 400; i++) {
            threads[threadIndex++] = new Thread(new HiloCliente(cuenta, 100, true));
        }

        // Crear hilos para depósitos de 50 euros
        for (int i = 0; i < 200; i++) {
            threads[threadIndex++] = new Thread(new HiloCliente(cuenta, 50, true));
        }

        // Crear hilos para depósitos de 20 euros
        for (int i = 0; i < 600; i++) {
            threads[threadIndex++] = new Thread(new HiloCliente(cuenta, 20, true));
        }

        // Crear hilos para retiros de 100 euros
        for (int i = 0; i < 400; i++) {
            threads[threadIndex++] = new Thread(new HiloCliente(cuenta, 100, false));
        }

        // Crear hilos para retiros de 50 euros
        for (int i = 0; i < 200; i++) {
            threads[threadIndex++] = new Thread(new HiloCliente(cuenta, 50, false));
        }

        // Crear hilos para retiros de 20 euros
        for (int i = 0; i < 600; i++) {
            threads[threadIndex++] = new Thread(new HiloCliente(cuenta, 20, false));
        }

        // Iniciar todos los hilos
        for (Thread thread : threads) {
            thread.start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
                System.out.println("El thread " + thread.getId() + " ha terminado");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Verificar que el saldo final sea igual al saldo inicial
        if (cuenta.getSaldo() == 10000) {
            System.out.println("La simulación fue exitosa. Saldo final: " + cuenta.getSaldo());
            System.out.println("El numero de threadIndex es: " + threadIndex);
        } else {
            System.out.println("La simulación falló. Saldo final: " + cuenta.getSaldo());
        }
    }
}