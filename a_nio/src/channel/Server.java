package channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8090));

        ssc.configureBlocking(false);

        SocketChannel sc = ssc.accept();
        while (sc == null)
            sc = ssc.accept();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = sc.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(),0,read));

        sc.close();
        ssc.close();
    }
}
