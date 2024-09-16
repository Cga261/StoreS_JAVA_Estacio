package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BancoDeDados.DBConnection;

public class LoginController {
    public boolean autenticar(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome_usuario = ? AND senha = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nomeUsuario);
            stmt.setString(2, senha); // Em um cenário real, as senhas devem ser criptografadas
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return true; // Usuário autenticado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Falha na autenticação
    }
}
