import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyClassloader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new MyClassloader().findClass("Hello");
            Method helloMethod = helloClass.getMethod("hello");
            helloMethod.invoke(helloClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = this.getClass().getResource("Hello.xlass").getPath();
        File file = null;
        try {
            file = new File(URLDecoder.decode(path, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes = null;
        if (file.isFile() && file.exists()) {
            try (FileChannel channel = new FileInputStream(file).getChannel()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
                channel.read(byteBuffer);
                bytes = byteBuffer.array();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("failed to find path: " + path);
        }
        return defineClass(name, decode(bytes), 0, bytes.length);
    }

    /**
     * replace each byte with x->255-x
     *
     * @param bytes
     * @return
     */
    private byte[] decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
//            bytes[i] = (byte) (255 - bytes[i]);
            bytes[i] = (byte) ~bytes[i];
        }
        return bytes;
    }
}
