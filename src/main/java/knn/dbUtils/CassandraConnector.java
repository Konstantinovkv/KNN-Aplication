package knn.dbUtils;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;

public class CassandraConnector {
    public static CqlSession connect() {
        CqlSessionBuilder builder = CqlSession.builder()
                .withKeyspace("knn");
        return builder.build();
    }
}
