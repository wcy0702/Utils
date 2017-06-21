package com.wcy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * ����������
 *
 * @ClassName ParallelStreams 
 * @author wangcongyi
 * @date 2017��6��12�� ����3:03:20
 */
public class ParallelStreams {
	static int max = 1000_000;  
    static List<String> values;  
    
    public static void main(String[] args) {
    	ParallelStreams();
    	sequentialSort();
    	parallelSort();
    }
    
    public static void ParallelStreams(){  
        //����һ������ΨһԪ�صĴ�������         
        values = new ArrayList<String>();  
        for(int i=max; i>0; i--){  
            UUID uuid = UUID.randomUUID();  
            values.add(uuid.toString());              
        }  
    }  
    //����������ЩԪ����Ҫ�೤ʱ�䡣  
      
    //Sequential Sort, ����˳������������  
    public static void sequentialSort(){     
       
    	long t0 = System.nanoTime();  
        long count = values.stream().sorted().count();  
        System.err.println("count = " + count);  
        long t1 = System.nanoTime();  
          
        long millis  = TimeUnit.NANOSECONDS.toMillis(t1 - t0);  
        System.out.println(String.format("sequential sort took: %d ms", millis));    
        //sequential sort took: 1932 ms  
          
    }  
      
    //parallel Sort, ���ò�������������  
    public static void parallelSort(){   
        long t0 = System.nanoTime();  
          
        long count = values.parallelStream().sorted().count();  
        System.err.println("count = " + count);  
          
        long t1 = System.nanoTime();  
          
        long millis  = TimeUnit.NANOSECONDS.toMillis(t1 - t0);  
        System.out.println(String.format("parallel sort took: %d ms", millis));    
        //parallel sort took: 1373 ms �������������ѵ�ʱ���Լ��˳�������һ�롣  
    }  
}
