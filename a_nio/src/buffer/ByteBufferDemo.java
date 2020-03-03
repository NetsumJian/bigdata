package buffer;

import java.nio.ByteBuffer;

public class ByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.capacity());
        System.out.println(buffer.position());

        buffer.put("abc".getBytes());
        buffer.put("def".getBytes());

        System.out.println(buffer.position());

        // buffer.limit(buffer.position());
        // buffer.position(0);
        buffer.flip();

        //while (buffer.position() < buffer.limit()){
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

    }
}
