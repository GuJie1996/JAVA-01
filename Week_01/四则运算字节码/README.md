字节码分析见注释。

源代码：

```
public class Hello {

    private int count = 0;
    private double result = 1;

    public static void main(String[] args) {
        Hello hello = new Hello();
        for (; hello.autoIncrease(); ) {
            hello.calc();
        }
        System.out.println(hello.getResult());
    }

    public double getResult() {
        return result;
    }

    public void calc() {
        switch (count % 4) {
            case 0:
                result += 1;
                break;
            case 1:
                result -= 0.5;
                break;
            case 2:
                result *= result;
                break;
            case 3:
                result /= 1.25;
        }
    }

    public boolean autoIncrease() {
        return count++ < 8;
    }

}
```

字节码：

```
Classfile /C:/Users/gujie/IdeaProjects/untitled/src/Hello.class
  Last modified 2021-1-8; size 1094 bytes
  MD5 checksum 8cd5b84e6612a6dc4a7e3e9541fa1bf4
  Compiled from "Hello.java"
public class Hello
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #15.#41        // java/lang/Object."<init>":()V
   #2 = Fieldref           #4.#42         // Hello.count:I
   #3 = Fieldref           #4.#43         // Hello.result:D
   #4 = Class              #44            // Hello
   #5 = Methodref          #4.#41         // Hello."<init>":()V
   #6 = Methodref          #4.#45         // Hello.autoIncrease:()Z
   #7 = Methodref          #4.#46         // Hello.calc:()V
   #8 = Fieldref           #47.#48        // java/lang/System.out:Ljava/io/PrintStream;
   #9 = Methodref          #4.#49         // Hello.getResult:()D
  #10 = Methodref          #50.#51        // java/io/PrintStream.println:(D)V
  #11 = Double             0.5d
  #13 = Double             1.25d
  #15 = Class              #52            // java/lang/Object
  #16 = Utf8               count
  #17 = Utf8               I
  #18 = Utf8               result
  #19 = Utf8               D
  #20 = Utf8               <init>
  #21 = Utf8               ()V
  #22 = Utf8               Code
  #23 = Utf8               LineNumberTable
  #24 = Utf8               LocalVariableTable
  #25 = Utf8               this
  #26 = Utf8               LHello;
  #27 = Utf8               main
  #28 = Utf8               ([Ljava/lang/String;)V
  #29 = Utf8               args
  #30 = Utf8               [Ljava/lang/String;
  #31 = Utf8               hello
  #32 = Utf8               StackMapTable
  #33 = Class              #44            // Hello
  #34 = Utf8               getResult
  #35 = Utf8               ()D
  #36 = Utf8               calc
  #37 = Utf8               autoIncrease
  #38 = Utf8               ()Z
  #39 = Utf8               SourceFile
  #40 = Utf8               Hello.java
  #41 = NameAndType        #20:#21        // "<init>":()V
  #42 = NameAndType        #16:#17        // count:I
  #43 = NameAndType        #18:#19        // result:D
  #44 = Utf8               Hello
  #45 = NameAndType        #37:#38        // autoIncrease:()Z
  #46 = NameAndType        #36:#21        // calc:()V
  #47 = Class              #53            // java/lang/System
  #48 = NameAndType        #54:#55        // out:Ljava/io/PrintStream;
  #49 = NameAndType        #34:#35        // getResult:()D
  #50 = Class              #56            // java/io/PrintStream
  #51 = NameAndType        #57:#58        // println:(D)V
  #52 = Utf8               java/lang/Object
  #53 = Utf8               java/lang/System
  #54 = Utf8               out
  #55 = Utf8               Ljava/io/PrintStream;
  #56 = Utf8               java/io/PrintStream
  #57 = Utf8               println
  #58 = Utf8               (D)V
{
  public Hello();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iconst_0
         6: putfield      #2                  // Field count:I
         9: aload_0
        10: dconst_1
        11: putfield      #3                  // Field result:D
        14: return
      LineNumberTable:
        line 1: 0
        line 3: 4
        line 4: 9
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      15     0  this   LHello;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=2, args_size=1
         0: new           #4                  // class Hello
         3: dup
         4: invokespecial #5                  // Method "<init>":()V
         7: astore_1
         8: aload_1
         9: invokevirtual #6                  // Method autoIncrease:()Z
        12: ifeq          22
        15: aload_1
        16: invokevirtual #7                  // Method calc:()V
        19: goto          8
        22: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
        25: aload_1
        26: invokevirtual #9                  // Method getResult:()D
        29: invokevirtual #10                 // Method java/io/PrintStream.println:(D)V
        32: return
      LineNumberTable:
        line 7: 0
        line 8: 8
        line 9: 15
        line 11: 22
        line 12: 32
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      33     0  args   [Ljava/lang/String;
            8      25     1 hello   LHello;
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 8
          locals = [ class Hello ]
        frame_type = 13 /* same */

  public double getResult();
    descriptor: ()D
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: getfield      #3                  // Field result:D
         4: dreturn
      LineNumberTable:
        line 15: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LHello;

  public void calc();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=5, locals=1, args_size=1
         0: aload_0
         1: getfield      #2                  // Field count:I
         4: iconst_4
         5: irem
         6: tableswitch   { // 0 to 3
                       0: 36
                       1: 49
                       2: 64
                       3: 80
                 default: 92
            }
        36: aload_0
        37: dup
        38: getfield      #3                  // Field result:D
        41: dconst_1
        42: dadd
        43: putfield      #3                  // Field result:D
        46: goto          92
        49: aload_0
        50: dup
        51: getfield      #3                  // Field result:D
        54: ldc2_w        #11                 // double 0.5d
        57: dsub
        58: putfield      #3                  // Field result:D
        61: goto          92
        64: aload_0
        65: dup
        66: getfield      #3                  // Field result:D
        69: aload_0
        70: getfield      #3                  // Field result:D
        73: dmul
        74: putfield      #3                  // Field result:D
        77: goto          92
        80: aload_0
        81: dup
        82: getfield      #3                  // Field result:D
        85: ldc2_w        #13                 // double 1.25d
        88: ddiv
        89: putfield      #3                  // Field result:D
        92: return
      LineNumberTable:
        line 19: 0
        line 21: 36
        line 22: 46
        line 24: 49
        line 25: 61
        line 27: 64
        line 28: 77
        line 30: 80
        line 32: 92
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      93     0  this   LHello;
      StackMapTable: number_of_entries = 5
        frame_type = 36 /* same */
        frame_type = 12 /* same */
        frame_type = 14 /* same */
        frame_type = 15 /* same */
        frame_type = 11 /* same */

  public boolean autoIncrease();
    descriptor: ()Z
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=1, args_size=1
         0: aload_0
         1: dup
         2: getfield      #2                  // Field count:I
         5: dup_x1
         6: iconst_1
         7: iadd
         8: putfield      #2                  // Field count:I
        11: bipush        8
        13: if_icmpge     20
        16: iconst_1
        17: goto          21
        20: iconst_0
        21: ireturn
      LineNumberTable:
        line 35: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      22     0  this   LHello;
      StackMapTable: number_of_entries = 2
        frame_type = 20 /* same */
        frame_type = 64 /* same_locals_1_stack_item */
          stack = [ int ]
}
SourceFile: "Hello.java"
```
