package com.df.test;

/**
 * Auth dongfang
 * Date 2017/3/15
 */
public class Mian {

    public static void main(String[] args) {

//         Parent p = new Child(33);


//        ArrayList<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {     //(真正的坑在这里, 即使是最后一个元素, 索引判断是跟Size比较 一旦Size改变了 hasNext为true)
//            String next = iterator.next();
//            System.out.println(next);
//            if ("1".equals(next)) {    // 这里有坑, 因为是最后一个元素了
//                // list.remove(next);
//                iterator.remove();
//            }
//        }

//        Object obj1 = new Simple();
//        Object obj2 = new Simple();
//        Simple obj3 = new Simple();
//        Simple obj4 = new Simple();
//        if (obj1.equals(obj2)) { // 这里走equal( Object )方法的
//            System.out.println("o1 equal o2");
//        }
//        if (obj3.equals(obj4)) {
//            System.out.println("o3 equal o4");
//        }

        Simple simple = new Second();
        simple.print();
        simple.hello();


    }

    static class Simple {
        private String name = "Simple";

        public Simple() {
            System.out.println(name);
        }

        public void print() {
            System.out.println("Simple print");
        }

        public static void hello() {
            System.out.println("Simple hello");
        }
    }

    static class Second extends Simple {
        private String name = "Second";

        public Second() {
            System.out.println(name);
        }

        public void print() {
            System.out.println("Second print");
        }

        public static void hello() {  // 大坑 static方法
            System.out.println("Second hello");
        }
    }
}
