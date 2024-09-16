package main;

import java.util.List;
import java.util.Scanner;

import Login.LoginController;
import produtos.Produto;
import produtos.ProdutoDAO;
import vendas.Venda;

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
            System.out.println("1. Cadastrar Produto Manualmente");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Editar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("5. Comprar de Fornecedor");
            System.out.println("6. Realizar Venda");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    // Cadastro de Produto Manualmente
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

                case 5:
                    // Compra de Produtos
                    System.out.print("Nome do produto para compra: ");
                    String nomeProdutoCompra = scanner.nextLine();
                    
                    Produto produtoParaCompra = produtoDAO.buscarProdutoPorNome(nomeProdutoCompra);

                    if (produtoParaCompra == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Quantidade a ser comprada: ");
                    int quantidadeCompra = scanner.nextInt();

                    System.out.print("Porcentagem de lucro: ");
                    double porcentagemLucro = scanner.nextDouble();

                    double precoCompra = produtoParaCompra.getPreco();
                    double precoVenda = precoCompra * (1 + porcentagemLucro / 100);

                    // Cria um novo produto para a lista com o preço atualizado
                    Produto produtoParaCadastro = new Produto(nomeProdutoCompra, precoVenda, quantidadeCompra);
                    produtoDAO.cadastrarProduto(produtoParaCadastro);

                    System.out.println("Compra registrada com sucesso!");
                    break;

                case 6:
                    // Venda de Produtos
                    System.out.print("Nome do produto a ser vendido: ");
                    String nomeProdutoVenda = scanner.nextLine();

                    Produto produtoParaVenda = produtoDAO.buscarProdutoPorNome(nomeProdutoVenda);

                    if (produtoParaVenda == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Quantidade a ser vendida: ");
                    int quantidadeVenda = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer

                    if (quantidadeVenda > produtoParaVenda.getQuantidade()) {
                        System.out.println("Quantidade vendida excede a quantidade em estoque.");
                        break;
                    }

                    double valorVenda = produtoParaVenda.getPreco() * quantidadeVenda;
                    Venda venda = new Venda(produtoParaVenda, quantidadeVenda, valorVenda);

                    // Atualiza o estoque após a venda
                    produtoDAO.atualizarEstoqueAposVenda(produtoParaVenda.getId(), quantidadeVenda);

                    System.out.println("Venda registrada com sucesso!");
                    System.out.println("Produto: " + venda.getProduto().getNome());
                    System.out.println("Quantidade Vendida: " + venda.getQuantidadeVendida());
                    System.out.println("Valor Total: " + venda.getValorTotal());
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
