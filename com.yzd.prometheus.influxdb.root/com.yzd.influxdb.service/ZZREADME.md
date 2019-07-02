
> **参考**

- [JVM中MemoryUsage中init,committed,used,max的含义](https://blog.csdn.net/fanwu72/article/details/8936746)
    ```
    结论：
    1.init约等于xms的值，max约等于xmx的值。
    2.used是已经被使用的内存大小，
    3.committed是当前可使用的内存大小（包括已使用的），committed >= used。
    4.committed不足时jvm向系统申请，若超过max则发生OutOfMemoryError错误。
    ```
 
 > **Jconsole 管理jvm中的heap**
 
 - [Jconsole 管理jvm中的heap](https://blog.csdn.net/virtualpc/article/details/83732049)
 ```
 Heap and Non-heap记忆体：JVM管理两种记忆体：heap 和non-heap 记忆体，两种记忆体都是在JVM启动时建立。
 
 Heap memory 是运行时资料区域，用于JVM为所有物件实例和伫列分配的记忆体。 Heap可能为固定值或者可变值。 垃圾收集器是一个用于回收物件占用的heap记忆体的自动化记忆体管理系统。  
 Non-heap memory 包含一个在所有执行序共用的方法区域（method area）和内部进程或JVM优化所需的记忆体。它存储了每一个类的结构，比如运行常量池，栏位和方法资料，构造函数和方法的代码。 方法区域逻辑上是heap的一部分，但是依赖于实现，JVM可能不进行垃圾收集或压缩。 像heap一样，方法区域可能为固定或可变大小。 方法区域所需要的记忆体没有必要是连续的。
 
 Heap Memory Pool： 
 A. Eden Space（heap）：大多数物件初始化时从Eden Space池分配记忆体，即是存在于此池中 
 B. Survivor Space（heap）：此池包含的物件是那些原先在eden space中，但是已经经历过垃圾回收而仍然存在的物件。 
 C. Tenured Generation（heap）：在surviver space中已经存在了一段时间之后的物件会移动到这个池中。
 
 Non-Heap Memory Pool： 
 D. Code Cache （non-heap）：储存编译的程式码和local variables。 
 E. Permanent Generation（non-heap）：包含虚拟机器自身的所有反射资料。 比如class和mothod物件。 对于使用class data sharing的JVM，分为唯读(shared-ro)和读写(shared-rw)两个区域。
 --------------------- 
 ```
 
 > **Java堆内存Heap与非堆内存Non-Heap简介和设置**
 
 - [Java堆内存Heap与非堆内存Non-Heap简介和设置](https://blog.csdn.net/u011646985/article/details/52668748)
 ```
 按照官方的说法：“Java 虚拟机具有一个堆，堆是运行时数据区域，所有类实例和数组的内存均从此处分配。
 堆是在 Java 虚拟机启动时创建的。”“在JVM中堆之外的内存称为非堆内存(Non-heap memory)”。
 可以看出JVM主要管理两种类型的内存：堆和非堆（非堆即常说的栈）。
 简单来说堆就是Java代码可及的内存，是留给开发人员使用的；
 非堆就是JVM留给 自己用的，所以方法区、JVM内部处理或优化所需的内存(如JIT编译后的代码缓存)、每个类结构(如运行时常数池、字段和方法数据)以及方法和构造方法 的代码都在非堆内存中。
 ```