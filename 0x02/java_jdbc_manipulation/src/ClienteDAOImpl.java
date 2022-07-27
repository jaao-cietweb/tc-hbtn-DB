import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO {

    Connection conn;
    @Override
    public Connection connect(String urlConexao) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sqlite_database_2022.db");
            System.out.println("Conectou");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void createTable(String urlConexao) {
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE IF NOT EXISTS cliente (");
        sql.append("id integer PRIMARY KEY , ");
        sql.append("nome text NOT NULL, ");
        sql.append("idade integer, ");
        sql.append("cpf text NOT NULL, ");
        sql.append("rg text ");
        sql.append(")");

        try {
            Statement statement =  conn.createStatement();
            statement.execute(sql.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        String sql = "INSERT INTRO cliente(nome,idade, cpf, rg) VALUES(joao,23,00225577,113355)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setInt(2, cliente.getIdade());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getRg());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectAll(String urlConexao) {
        String sql = "SELECT id, nome, idade, cpf, rg FROM cliente";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getInt("id") + "\t" + rs.getString("nome") + "\t" + rs.getInt("idade") + "\t" +
                        rs.getString("cpf") + "\t" + rs.getString("rg"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        String sql = "UPDATE cliente SET nome = ? , idade = ?  + WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, idade);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        try {
            conn.close();
            System.out.println("Desconectou!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
