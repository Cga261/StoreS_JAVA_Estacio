package main;

import java.util.Scanner;

import Login.LoginController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        LoginController loginController = new LoginController();
        
        if (loginController.autenticar(nomeUsuario, senha)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Falha no login.");
        }
        
        scanner.close();
    }
}

