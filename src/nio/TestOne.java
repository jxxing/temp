package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestOne {

	public static void main(String[] args) {
		RandomAccessFile aFile;
		try {
			aFile = new RandomAccessFile("d:/20151112CMBAPINV8888021652201511120001C.TXT", "rw");
		
			FileChannel inChannel = aFile.getChannel();
	
			ByteBuffer buf = ByteBuffer.allocate(48);

			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {
	
			System.out.println("Read " + bytesRead);
			buf.flip();//法将Buffer从写模式切换到读模式
			//调用flip()方法会将position设回0，并将limit设置成之前position的值
	
			while(buf.hasRemaining()){
			System.out.print((char) buf.get());
			}
	
			buf.clear();//有两种方式能清空缓冲区：调用clear()或compact()方法。clear()方法会清空整个缓冲区。
			//compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面
			//如果调用的是clear()方法，position将被设回0，limit被设置成 capacity的值。换句话说，Buffer 被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据
			//compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据
			
			bytesRead = inChannel.read(buf);
			}
			aFile.close();
			
			//通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset()方法恢复到这个position
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
