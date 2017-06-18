package edu.pucmm.programacionweb2017.database;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class BootstrapServices {
    private static final Logger logger = LoggerFactory.getLogger(BootstrapServices.class);

    /**
     *
     * @throws SQLException
     */
    public static void iniciarServer() {
        try {
            Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
        } catch (SQLException e) {
            logger.debug("Error al inicializar el servidor.", e);
        }
    }

    /**
     *
     * @throws SQLException
     */
    public static void pararServer() {
        try {
            Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
        } catch (SQLException e) {
            logger.debug("Error al detener el servidor.");
        }
    }
}
