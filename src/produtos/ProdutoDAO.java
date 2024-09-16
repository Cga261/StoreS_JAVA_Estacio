package produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BancoDeDados.DBConnection;

public class ProdutoDAO {

    // Método para cadastrar um produto no banco de dados
    public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome_produto, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());

            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os produtos no banco de dados
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getString("nome_produto"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade")
                );
                produto.setId(rs.getInt("id"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
 // Método para editar um produto no banco de dados
    public void editarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome_produto = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // Método para remover um produto do banco de dados
    public void removerProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
