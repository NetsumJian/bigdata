package channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8090));
        socketChannel.configureBlocking(false);

        while (!socketChannel.isConnected())
            socketChannel.finishConnect();

        socketChannel.write(ByteBuffer.wrap("hello server".getBytes()));
        socketChannel.close();
    }
}
