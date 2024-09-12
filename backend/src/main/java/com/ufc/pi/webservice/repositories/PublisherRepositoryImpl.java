package com.ufc.pi.webservice.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.ufc.pi.webservice.database.PublisherMapper;
import com.ufc.pi.webservice.database.PublisherTable;
import com.ufc.pi.webservice.models.Publisher;
import com.ufc.pi.webservice.utils.ParamReplacer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PublisherMapper publisherMapper;

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
    public Optional<Publisher> findById(Long id) {
        final String PARAMETERIZED_COMMAND = "SELECT * FROM :tableName WHERE :idColumn = ?";

        HashMap<String, String> tableStructureParams = new HashMap<>();

        tableStructureParams.put("tableName", PublisherTable.TABLE_NAME);
        tableStructureParams.put("idColumn", PublisherTable.ID_COLUMN);

        final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);

        PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
                ps.setLong(1, id);
            }
        };

        var result = jdbcTemplate.query(tableStructuredCommand, queryDynamicParamsSetter, this.publisherMapper);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(result.get(0));
    }

    @Override
    public List<Publisher> findAll() {
        final String PARAMETERIZED_COMMAND = "SELECT * FROM :tableName";

        HashMap<String, String> tableStructureParams = new HashMap<>();

        tableStructureParams.put("tableName", PublisherTable.TABLE_NAME);

        final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);
        var result = jdbcTemplate.query(tableStructuredCommand, this.publisherMapper);

        return result;
    }

    
}
