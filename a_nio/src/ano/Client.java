package ano;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

public class Client implements Runnable {

    private AsynchronousSocketChannel channel;

    public Client() throws IOException {
        channel = AsynchronousSocketChannel.open();
    }

    public void connect() {
        channel.connect(new InetSocketAddress("127.0.0.1", 8379));
    }

    public void write(String data) {
        try {
            Future<Integer> write = channel.write(ByteBuffer.wrap(data.getBytes()));
            write.get();
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            channel.read(buffer).get();
            System.out.println(new String(buffer.array(),0,buffer.position()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        try {
            Client c1 = new Client();
            Client c2 = new Client();
            Client c3 = new Client();

            c1.connect();
            c2.connect();
            c3.connect();

            c1.write("c1 aaa");
            c2.write("c2 bbbb");
            c3.write("c3 ccccc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        //http://mp3.qiaomeigift.com/31/%E6%BC%82%E4%BA%AE%E5%A6%88%E5%A6%88%E5%BC%A0%E7%90%B3%E7%9A%84%E6%95%85%E4%BA%8B20.mp3
        String s = "http://mp3.qiaomeigift.com/31/%E6%BC%82%E4%BA%AE%E5%A6%88%E5%A6%88%E5%BC%A0%E7%90%B3%E7%9A%84%E6%95%85%E4%BA%8B";
        for (int i = 1; i < 30; i++) {
            if (i < 10)
                System.out.println(s+"0"+i+".mp3");
            else
                System.out.println(s+i+".mp3");
        }
    }
}