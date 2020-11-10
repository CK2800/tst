package integration;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class DockerContainerTest
{
    protected static final int PORT = 3306;
    protected static final String PASSWORD = "testuser123";

    @Container
    public static MySQLContainer mysql;

    // static initializer to start up a docker container.
    static
    {
        mysql = (MySQLContainer) new MySQLContainer(DockerImageName.parse("mysql")).withPassword(PASSWORD).withExposedPorts(PORT);
    }
}
