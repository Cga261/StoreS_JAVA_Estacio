package main;

import java.util.List;
import java.util.Scanner;

import Login.LoginController;
import produtos.Produto;
import produtos.ProdutoDAO;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        LoginController loginController = new LoginController();

        // Sistema de Login
        System.out.println("Bem-vindo ao sistema StoreS!");
        System.out.print("Digite seu nome de usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        boolean autenticado = loginController.autenticar(usuario, senha);

        if (!autenticado) {
            System.out.println("Falha no login. Encerrando o programa.");
            scanner.close();
            return;
        }

        // Menu de opções
        int opcao = 0;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Editar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    // Cadastro de Produto
                    System.out.print("Nome do produto: ");
                    String nomeProduto = scanner.nextLine();

                    System.out.print("Preço do produto: ");
                    double preco = scanner.nextDouble();

                    System.out.print("Quantidade do produto: ");
                    int quantidade = scanner.nextInt();

                    Produto produto = new Produto(nomeProduto, preco, quantidade);
                    produtoDAO.cadastrarProduto(produto);
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 2:
                    // Listagem de Produtos
                    System.out.println("Lista de Produtos:");
                    List<Produto> produtos = produtoDAO.listarProdutos();

                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        for (Produto p : produtos) {
                            System.out.println("ID: " + p.getId() +
                                               ", Nome: " + p.getNome() +
                                               ", Preço: " + p.getPreco() +
                                               ", Quantidade: " + p.getQuantidade());
                        }
                    }
                    break;

                case 3:
                    // Edição de Produto
                    System.out.print("Digite o ID do produto a ser editado: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer

                    System.out.print("Novo nome do produto: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Novo preço do produto: ");
                    double novoPreco = scanner.nextDouble();

                    System.out.print("Nova quantidade do produto: ");
                    int novaQuantidade = scanner.nextInt();

                    Produto produtoEditado = new Produto(novoNome, novoPreco, novaQuantidade);
                    produtoEditado.setId(idEditar);
                    produtoDAO.editarProduto(produtoEditado);
                    break;

                case 4:
                    // Remoção de Produto
                    System.out.print("Digite o ID do produto a ser removido: ");
                    int idRemover = scanner.nextInt();
                    produtoDAO.removerProduto(idRemover);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}

