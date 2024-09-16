package main;

import java.util.Scanner;

import Login.LoginController;
import produtos.Produto;
import produtos.ProdutoDAO;

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
        
        ProdutoDAO produtoDAO = new ProdutoDAO();

        System.out.print("Nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Preço do produto: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade do produto: ");
        int quantidade = scanner.nextInt();

        Produto produto = new Produto(nomeProduto, preco, quantidade);
        produtoDAO.cadastrarProduto(produto);

        
        scanner.close();
    }
}

