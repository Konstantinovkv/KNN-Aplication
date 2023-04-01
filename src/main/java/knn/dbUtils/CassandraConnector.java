package knn.dbUtils;

import com.datastax.oss.driver.api.core.CqlSession;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

public class CassandraConnector {

    public static CqlSession connect() {
        Properties properties = loadProperties();

        String ipAddress = properties.getProperty("cassandra.ip_address");
        String username = properties.getProperty("cassandra.username");
        String password = properties.getProperty("cassandra.password");
        String localDatacenter = properties.getProperty("cassandra.local_datacenter");
        String keyspace = properties.getProperty("cassandra.keyspace");

        return CqlSession.builder()
                .addContactPoint(new InetSocketAddress(ipAddress, 9042))
                .withAuthCredentials(username, password)
                .withKeyspace(keyspace)
                .withLocalDatacenter(localDatacenter)
                .build();
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = CassandraConnector.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Error loading application.properties: " + e.getMessage());
        }
        return properties;
    }
}
