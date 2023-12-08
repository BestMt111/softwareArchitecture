# ParnasKWIC索引系统
## 简介

字符文件file由行（Lines）构成，行（Lines）是字（Words）有序集合，字（Words）是字符（characters）的有序集合

KWIC索引系统接受若干些行，每行有若干字，每个字由若干字符组成；每行都可以循环移位，亦即重复地把第一个字删除，然后接到行末； KWIC把所有行的各种移位情况按照字母表顺序输出（不考虑大小写）。

输入为（.txt）文件，通过指定命令行指定要处理的英文文本文件，输出结果文件的目录和文件名（.txt文件）

## 可供参考资料

https://blog.csdn.net/Albert_weiku/article/details/131765776

https://blog.csdn.net/weixin_43853097/article/details/110943379

https://zhuanlan.zhihu.com/p/620172231?utm_id=0  (划分模块的思路)

## 四种设计方案

1.  面向过程方案（一个类，基本否决）
2.  面向对象方案4个基本类（难以扩展功能，扩展功能则需要在原基础上修改代码，违背OCP原则，否认）
3.  基于事件通知（响应式）的方案（事件角度）
4.  基于管道的方案（数据角度）

## 人员分工

| 分工               | 人员   |
| ------------------ | ------ |
| 结构设计、文档编写 | 李梦韬 |
| 输入模块           | 柳洋   |
| 输出模块           | 张霞   |
| 循环移位模块       | 杨捷宁 |
| 字典排序模块       | 张秋傑 |

## 项目结构目录说明

| **doc**  | **相关文档保存目录：包含需求规格说明书（包含系统行为概述）、概要设计（包含架构图）、详细设计（包含类图、模块图）** |
| -------- | ------------------------------------------------------------ |
| **main** | **程序执行的目录，包含main(业务逻辑目录)和test（单元测试等保存目录）** |
| **file** | **输入文件、输出文件保存的目录**                             |

