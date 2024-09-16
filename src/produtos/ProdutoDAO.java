package produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import BancoDeDados.DBConnection;

public class ProdutoDAO {
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
}

