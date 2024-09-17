package com.ufc.pi.webservice.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.database.PublisherTable;
import com.ufc.pi.webservice.models.Publisher;
import com.ufc.pi.webservice.utils.ParamReplacer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(Publisher publisher) {
        final String PARAMETERIZED_COMMAND = "INSERT INTO :tableName (:nameColumn) VALUES (?)";

        HashMap<String, String> tableStructureParams = new HashMap<>();
        tableStructureParams.put("tableName", PublisherTable.TABLE_NAME);
        tableStructureParams.put("nameColumn", PublisherTable.NAME_COLUMN);
        
        // Aqui estamos mapeando o nome da tabela e o nome das colunas retornado o seguinte comando INSERT INTO publishers (name) VALUES (?)
        final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);

        // Aqui estamos definindo qual valor será atribuído a cada '?' definido no comando inicial
        PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
                ps.setString(1, publisher.getName());
            }
        };
        
        // Aqui estamos executado o comando no banco de dados e substituindo os '?' pelos respectivos valor.
        jdbcTemplate.update(
            tableStructuredCommand,
            queryDynamicParamsSetter
        );
    }

    @Override
    public void update(Publisher publisher) {
        final String PARAMETERIZED_COMMAND = "UPDATE :tableName SET :nameColumn = ? WHERE :idColumn = ?";

        HashMap<String, String> tableStructureParams = new HashMap<>();
        tableStructureParams.put("tableName", PublisherTable.TABLE_NAME);
        tableStructureParams.put("nameColumn", PublisherTable.NAME_COLUMN);
        tableStructureParams.put("idColumn", PublisherTable.ID_COLUMN);
 
        final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);

        PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
                ps.setString(1, publisher.getName());
                ps.setLong(2, publisher.getId());
            }
        };
        
        jdbcTemplate.update(
            tableStructuredCommand,
            queryDynamicParamsSetter
        );
    }

    @Override
    public DoublyLinkedList<Publisher> findById(Long id) {
        final String query = "SELECT * FROM publishers WHERE :idColumn = ?";

        DoublyLinkedList<Publisher> publishers = new DoublyLinkedList<>();

        PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
                ps.setLong(1, id);
            }
        };

        jdbcTemplate.query(query, queryDynamicParamsSetter, (rs, rowNum) -> {
            Publisher publisher = mapRow(rs, rowNum);
            publishers.addNode(publisher);
            return publisher;
        });

        return publishers;
    }

    @Override
    public DoublyLinkedList<Publisher> findAll() {
        final String sql = "SELECT * FROM publishers";

        DoublyLinkedList<Publisher> publishers = new DoublyLinkedList<>();

        jdbcTemplate.query(
            sql,
            (rs, rowNum) -> {
                Publisher publisher = mapRow(rs, rowNum);
                publishers.addNode(publisher);
                return publisher;
            }
        );

        return publishers;
    }

    public Publisher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        Publisher publisher = new Publisher();

        publisher.setId(id);
        publisher.setName(name);

        return publisher;
    }
    
}
