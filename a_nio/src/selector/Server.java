package selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost",8070));
        ssc.configureBlocking(false);
        Selector selc = Selector.open();
        ssc.register(selc, SelectionKey.OP_ACCEPT);

        while (true){
            selc.select();
            Set<SelectionKey> keys = selc.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()){
                SelectionKey sk = it.next();
                if (sk.isAcceptable()){
                    System.out.println("Acceptable...");
                    // ServerSocketChannel sscx = (ServerSocketChannel) sk.channel();
                    SocketChannel scx = ssc.accept();
                    scx.configureBlocking(false);
                    scx.register(selc,SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }
                if (sk.isReadable()){
                    System.out.println("Readable...");
                    SocketChannel scx = (SocketChannel) sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    scx.read(buffer);
                    System.out.println(new String(buffer.array(),0,buffer.position()));
                    scx.register(selc,sk.interestOps() ^ SelectionKey.OP_READ);
                }
                if (sk.isWritable()){
                    System.out.println("Writable...");
                    SocketChannel scx = (SocketChannel) sk.channel();
                    scx.write(ByteBuffer.wrap("hello client".getBytes()));
                    scx.register(selc,sk.interestOps() ^ SelectionKey.OP_WRITE);
                }
                it.remove();
            }
        }
    }
}
