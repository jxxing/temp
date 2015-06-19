package study.lessontemp;
import java.io.ByteArrayInputStream;   
import java.io.ByteArrayOutputStream;   
import java.io.ObjectInputStream;   
import java.io.Serializable;

public class TestClass implements Serializable {   
    private static final long serialVersionUID = 0L;   
    public TestClass() throws Exception {   
        throw new Exception("!!!");   
    }   
  
    public static void main(String[] args) throws Exception {   
        byte[] head = { -84, -19, 0, 5, 115, 114, 0 };   
        byte[] ass = { 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 120, 112 };   
        String name = TestClass.class.getName();   
        System.out.println(name);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();   
        baos.write(head);   
        baos.write(name.length());   
        baos.write(name.getBytes());   
        baos.write(ass);   
        baos.flush();   
        baos.close();   
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));   
        TestClass o = (TestClass) ois.readObject();   
        ois.close();   
        System.out.println("Created: " + o);
        System.in.read(); // 暂停进程以便有足够时间来dump class
    }   
}
