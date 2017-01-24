package com.kaminski.reflection;

/**
 * @author
 *
 */
public class DynamicInstantiationExample {

   /**
    * @param args
    */
   public static void main(String[] args) throws Exception {
       Object[] values = { "Hallo", new int[] { 1, 2, 3 },
               Double.valueOf(10.0D),new Reservation("Thomas") };
       for (int i = 0; i < values.length; i++) {
           Foo.class.getDeclaredConstructor(
                   new Class[] { values[i].getClass() }).newInstance(
                   new Object[] { values[i] });
       }
   }

   static class Foo {
       public Foo(String value) {
           System.out.printf("Constructor Foo(String) invoked, Parameter = %s\n", value);
       }

       public Foo(int[] value) {
           System.out.printf("Constructor Foo(int[]) invoked, Parameter = %s\n", value[0]);
       }

       public Foo(Double value) {
           System.out.println("Constructor Foo(Double) invoked");
       }
      
       public Foo(Reservation reservation) {
           System.out.println("Constructor Foo(Reservation) invoked");
       }
   }
  
   static class Reservation{
       String data;
       public Reservation(String data){
           this.data = data;
       }
   }
}