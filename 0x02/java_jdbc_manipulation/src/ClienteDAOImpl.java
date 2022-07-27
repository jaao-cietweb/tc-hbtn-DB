import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO {

    Connection conn;
    @Override
    public Connection connect(String urlConexao) {
        try {
            conn = DriverManager.getConnection(urlConexao);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void createTable(String urlConexao) {
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE IF NOT EXISTS cliente (id integer primary key, nome varchar(40) not null, idade integer, cpf varchar(40) not null, rg varchar(40))");

        try {
            conn = connect(urlConexao);
            Statement statement =  conn.createStatement();
            statement.execute(sql.toString());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        String sql = "INSERT INTO cliente(nome,idade, cpf, rg) VALUES(?, ?, ?, ?)";
        try {
            conn = connect(url_conexao);
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
            conn = connect(urlConexao);
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
