package com.solvd.pageranked.dao.jdbc.mysql.Impl;

import com.solvd.pageranked.connection.ConnectionUtil;
import com.solvd.pageranked.dao.INodes;
import com.solvd.pageranked.models.Nodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NodesDAO implements INodes {
    private static final Logger LOGGER = LogManager.getLogger(NodesDAO.class);
    private static final String INSERT = "INSERT INTO Nodes VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Nodes SET pageRank = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Nodes WHERE id = ?";
    private static final String GET_BY_NAME = "SELECT * FROM Nodes WHERE name = ?";
    private static final String GET_ALL = "SELECT * FROM Nodes";

    @Override
    public Nodes getByName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_NAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Nodes nodes = new Nodes();
                nodes.setId(resultSet.getInt("id"));
                nodes.setQuantityIn(resultSet.getInt("quantity_In"));
                nodes.setQuantityOut(resultSet.getInt("quantity_Out"));
                nodes.setName(resultSet.getString("name"));
                nodes.setPageRank(resultSet.getDouble("pageRank"));
                return nodes;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public List<Nodes> getAllNodes() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            List<Nodes> nodes = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Nodes node = new Nodes();
                node.setId(resultSet.getInt("id"));
                node.setQuantityIn(resultSet.getInt("quantity_In"));
                node.setQuantityOut(resultSet.getInt("quantity_Out"));
                node.setName(resultSet.getString("name"));
                node.setPageRank(resultSet.getDouble("pageRank"));
                nodes.add(node);
            }
            return nodes;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public void addNode(Nodes nodes) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, nodes.getId());
            preparedStatement.setInt(2, nodes.getQuantityIn());
            preparedStatement.setInt(3, nodes.getQuantityOut());
            preparedStatement.setString(4, nodes.getName());
            preparedStatement.setDouble(5, nodes.getPageRank());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void updateNode(Nodes nodes) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDouble(1, nodes.getPageRank());
            preparedStatement.setInt(2, nodes.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void deleteNode(int id) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE + id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }
}
