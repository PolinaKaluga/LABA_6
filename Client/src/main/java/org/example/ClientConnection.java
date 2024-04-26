package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;

public class ClientConnection {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private DatagramChannel clientChannel;

    public void connect(InetSocketAddress inetServerAddress) throws IOException {
        try {
            clientChannel = DatagramChannel.open();//Открывает новый канал для одного адреса

            clientChannel.configureBlocking(false);//неблокирующий режим

            clientChannel.connect(inetServerAddress);//явное подключение
        } catch (IllegalArgumentException ex) {
            rootLogger.error("Указан недопустимый порт: " + inetServerAddress.getPort());
        } catch (SocketException ex) {
            rootLogger.error("Установка соденинения не удалась\n" + ex);
        }
    }

    public DatagramChannel getClientChannel() {
        return clientChannel;
    }
}

