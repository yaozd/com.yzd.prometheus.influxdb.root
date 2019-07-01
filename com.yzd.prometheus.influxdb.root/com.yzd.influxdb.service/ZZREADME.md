
> **参考**

- [JVM中MemoryUsage中init,committed,used,max的含义](https://blog.csdn.net/fanwu72/article/details/8936746)
    ```
    结论：
    1.init约等于xms的值，max约等于xmx的值。
    2.used是已经被使用的内存大小，
    3.committed是当前可使用的内存大小（包括已使用的），committed >= used。
    4.committed不足时jvm向系统申请，若超过max则发生OutOfMemoryError错误。
    ```