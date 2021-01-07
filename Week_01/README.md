学习笔记

JVM是一台基于栈的计算机器，每个线程都有一个线程栈。

每次方法调用，都会在栈里创建一个栈帧，递归调用容易导致资源耗尽。

栈帧由操作数栈，局部变量数组和一个Class引用组成。

字节码由单字节（byte）指令构成，理论上支持2^8=256个操作码，字节码分为四大类：
- 栈操作指令
- 程序流程控制指令，包括与局部变量交互
- 对象操作指令，包括方法调用
- 算术运算及类型转换指令，JVM操作最小类型是int，比int短也当作int

使用 `javac` 命令将 `.java` 源代码编译成 `.class` 字节码
- `-g` 生成调试信息，包括本地变量表

使用 `javap` 命令分析 `.class` 字节码
- `-c` 反汇编
- `-v` 输出附加信息，包括版本号、常量池、行号等

简单看几个操作码：
- `invokestatic` 调用静态方法
- `invokespecial` 调用构造函数
- `invokeinterface` 调用接口方法
- `invokedynamic` 方法动态执行，jdk7新增，lambda表达式的实现基础
- `invokevirtual` 调用虚方法及没有指定操作码的其他所有方法（例如常见的实例方法）
- `aload` 从局部变量表的相应位置装载一个对象引用到栈顶；`iload`加载int...
- `aaload` 栈顶的数组下标（index）、数组引用（arrayref）出栈，并根据这两个数值取出对应的数组元素值（value）进栈；`iaload` 指定的int型数组的指定下标处的值进栈...

为了压缩字节码文件大小，也因为一个栈帧中用到的局部变量一般也就几个，所以提供了 `aload_0` 、 `aload_1` 这些指令。

例如指令 `new #2` 对应十六进制 `bb 00 02`，操作码只占一个字节，而操作数占了两个字节，有点浪费。

```
public class HelloByteCode {

    public static void main(String[] args) {
        HelloByteCode obj = new HelloByteCode();
    }

}
```

将上述文件编译成字节码：

```
Classfile /C:/Users/gujie/IdeaProjects/untitled/src/HelloByteCode.class
  Last modified 2021-1-7; size 415 bytes
  MD5 checksum 2d8d7d53684d2f9c26f99906ccad33e6
  Compiled from "HelloByteCode.java"
public class HelloByteCode
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#19         // java/lang/Object."<init>":()V
   #2 = Class              #20            // HelloByteCode
   #3 = Methodref          #2.#19         // HelloByteCode."<init>":()V
   #4 = Class              #21            // java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               LocalVariableTable
  #10 = Utf8               this
  #11 = Utf8               LHelloByteCode;
  #12 = Utf8               main
  #13 = Utf8               ([Ljava/lang/String;)V
  #14 = Utf8               args
  #15 = Utf8               [Ljava/lang/String;
  #16 = Utf8               obj
  #17 = Utf8               SourceFile
  #18 = Utf8               HelloByteCode.java
  #19 = NameAndType        #5:#6          // "<init>":()V
  #20 = Utf8               HelloByteCode
  #21 = Utf8               java/lang/Object
{
  public HelloByteCode();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 1: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LHelloByteCode;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: new           #2                  // class HelloByteCode
         3: dup
         4: invokespecial #3                  // Method "<init>":()V
         7: astore_1
         8: return
      LineNumberTable:
        line 4: 0
        line 5: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  args   [Ljava/lang/String;
            8       1     1   obj   LHelloByteCode;
}
SourceFile: "HelloByteCode.java"
```
