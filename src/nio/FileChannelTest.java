package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelTest {

	public static void main(String[] args) {
//			transferFrom();
			
			transferTo();
	}


	
	public static void transferFrom(){

		try {
			RandomAccessFile fromFile = new RandomAccessFile("D:/fromFile.txt", "rw");
			FileChannel      fromChannel = fromFile.getChannel();
	
			RandomAccessFile toFile = new RandomAccessFile("D:/toFile.txt", "rw");
			FileChannel      toChannel = toFile.getChannel();
	
			long position = 0;
			long count = fromChannel.size();
	
			toChannel.transferFrom(fromChannel,position,count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void transferTo(){
		try {
			RandomAccessFile fromFile = new RandomAccessFile("D:/fromFile.txt", "rw");
			FileChannel      fromChannel = fromFile.getChannel();
	
			RandomAccessFile toFile = new RandomAccessFile("D:/toFile.txt", "rw");
			FileChannel      toChannel = toFile.getChannel();
	
			long position = 0;
			long count = fromChannel.size();
	
			fromChannel.transferTo(position, count, toChannel);
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
