package org.example;

public class HiloCliente implements Runnable {
    private Cuenta cuenta;
    private double cantidad;
    private boolean deposito;

    public HiloCliente(Cuenta cuenta, double cantidad, boolean deposito) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.deposito = deposito;
    }

    @Override
    public void run() {
        if (deposito) {
            cuenta.depositar(cantidad);
            System.out.println("Depósito de " + cantidad + " euros realizado por el hilo " + Thread.currentThread().getId());
        } else {
            cuenta.retirar(cantidad);
            System.out.println("Retiro de " + cantidad + " euros realizado por el hilo " + Thread.currentThread().getId());
        }
    }
}