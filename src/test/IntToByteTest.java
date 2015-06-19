package test;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;

import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.io.IOException;

 

/**

 * @author yk3288

 *

 */

public class IntToByteTest {

 

         public static void main(String[] args) throws IOException{

                  System.out.println("application is start");

                  IntToByteTest t = new IntToByteTest();

                  t.converIn();

                  t.converStream();

                  System.out.println("convert is over!");

         }
         /**
	* 用移位吧，代码如下，因为int型式4个字节，byte为1个字节，一个字节8位
 	* 当int型转为byte型时，只截取int型的最后一个字节的数据，因此依次移位0,8,16,24就可以依次将各字节拷贝到byte类型的数组中
          * 使用移位的方式进行转换
          */
public void converIn(){

                  Byte[] b = new Byte[]{0,36,0,54};

                  //左移位

                  int i = b[3].intValue() |

                    b[2].intValue() << 8 | 

                    b[1].intValue() << 16 |

                    b[0].intValue() << 24;
                  System.out.println(b[3].intValue()<<0);
                  System.out.println(b[2].intValue() << 8);
                  System.out.println(b[1].intValue() << 16);
                  System.out.println(b[0].intValue() << 24);
                  System.out.println(54|296);//10
                  System.out.println(0x36|0x128);//16
                  System.out.println(054|296);
                  System.out.println(054|296);//8位
                  System.out.println(44|296);
                  System.out.println(i);

                  int j = 4|2|3|5;
                  System.out.println(j);
                  //右移位

                  byte[] b1 = new byte[]{

                                   (byte)(i >> 24),

                                   (byte)(i >> 16),

                                   (byte)(i >> 8),

                                   (byte)(i >> 0),

                  };

                  output(b1);

         }

         

         /**

          * 使用Java中流的操作进行转换

          * 

          * @throws IOException

          */

         public void converStream() throws IOException{

                  DataInputStream dis = new DataInputStream(

                                   new ByteArrayInputStream(new byte[]{0,36,0,54}));

                  int i = dis.readInt();

                  System.out.println(i);

                  ByteArrayOutputStream baos = new ByteArrayOutputStream();

                  DataOutputStream dos = new DataOutputStream(baos);

                  dos.writeInt(i);

                  byte[] b = baos.toByteArray();

                  output(b);

         }

         

         

         /**

          * 输出byte数组

          * 

          * @param b

          */

         public void output(byte[] b){

           for(int i = 0;i < b.length;i++){

                    System.out.print(b[i] + "/" );

           }

           System.out.println();

         }

}