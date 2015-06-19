package study.lesson30.cto51;

public class InitializationOrder {  
    public static void main(String[] args) {   
        SubClass sb = new SubClass();   
    }   
    /**
     * 1) Java虚拟机要执行InitializationOrder类中的static 方法main()，这引起了类的初始化。开始初始化InitializationOrder类。具体的步骤略去不说。
		
		2) InitializationOrder类初始化完毕后，开始执行main()方法。语句SubClass sb = new SubClass()将创建一个SubClass对象。加载类SubClass后对其进行类初始化，因为Subclass有一个父类SuperClass，所以先初始化SuperClass类。于是看到输出“SuperClass static”。
		
		3) SuperClass类初始化完毕后，开始初始化SubClass类，输出“SubClass static”。
		
		4) 至此，类的加载工作全部完成。开始进入创建SubClass的对象过程。先为SubClass类和其父类SuperClass类分配内存空间，这时Super su 被赋值为null。
		
		5) 执行构造函数SubClass()，执行super(), 调用父类的构造函数，输出“super”。
		
		6) 初始化SubClass类的成员变量su，输出“initialization variable”。
		
		7) 继续执行构造函数的剩余部分，执行new SuperClass("new SuperClass")，输出“new SuperClass”，这时Super su 被赋值新建对象的引用。
		
		8) 而SubClass虽然实现了接口Interface，但是初始化它的时候并不会引起接口的初始化，所以接口Interface中的static SuperClass su = new SuperClass("Interface new SuperClass")自始至终都没有被执行到
     */
}
