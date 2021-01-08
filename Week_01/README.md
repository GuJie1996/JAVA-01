学习笔记

### JVM基础

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
### 类的生命周期

1. 加载（Loading） 找Class文件
1. 连接
	1. 验证（Verification） 验证格式、依赖
	1. 准备（Preparation） 静态变量分配内存赋默认值、方法表
	1. 解析（Resolution） 常量池中的符号解析为引用
1. 初始化（Initialization）先父后子
	1. 静态代码块、变量（只一次）
	1. 动态代码块、变量
	1. 构造函数
1. 使用（Using）
1. 卸载（Unloading）

看一道题目：

```
public class Alibaba {

    public static int k = 0;
    public static Alibaba t1 = new Alibaba("t1");
    public static Alibaba t2 = new Alibaba("t2");
    public static int i = print("i");
    public static int n = 99;
    private int a = 0;
    public int j = print("j");
    {
        print("构造块");
    }
    static {
        print("静态块");
    }

    public Alibaba(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
        Alibaba t = new Alibaba("init");
    }
}
```

准备阶段静态变量i和n已经赋了初值0，

静态变量赋值和静态代码块已经开始执行，只会执行一次，

所以t1和t2创建对象的时候只会执行动态代码和构造函数，

按照静态（只一次）、动态、构造的顺序依次执行，可以推算出结果如下：

```
:j   i=0    n=0
:构造块   i=1    n=1
:t1   i=2    n=2
:j   i=3    n=3
:构造块   i=4    n=4
:t2   i=5    n=5
:i   i=6    n=6
:静态块   i=7    n=99
:j   i=8    n=100
:构造块   i=9    n=101
:init   i=10    n=102
```
