package com.wcy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 并行流排序
 *
 * @ClassName ParallelStreams 
 * @author wangcongyi
 * @date 2017年6月12日 下午3:03:20
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
        //创建一个包含唯一元素的大容器：         
        values = new ArrayList<String>();  
        for(int i=max; i>0; i--){  
            UUID uuid = UUID.randomUUID();  
            values.add(uuid.toString());              
        }  
    }  
    //测试排序这些元素需要多长时间。  
      
    //Sequential Sort, 采用顺序流进行排序  
    public static void sequentialSort(){     
       
    	long t0 = System.nanoTime();  
        long count = values.stream().sorted().count();  
        System.err.println("count = " + count);  
        long t1 = System.nanoTime();  
          
        long millis  = TimeUnit.NANOSECONDS.toMillis(t1 - t0);  
        System.out.println(String.format("sequential sort took: %d ms", millis));    
        //sequential sort took: 1932 ms  
          
    }  
      
    //parallel Sort, 采用并行流进行排序  
    public static void parallelSort(){   
        long t0 = System.nanoTime();  
          
        long count = values.parallelStream().sorted().count();  
        System.err.println("count = " + count);  
          
        long t1 = System.nanoTime();  
          
        long millis  = TimeUnit.NANOSECONDS.toMillis(t1 - t0);  
        System.out.println(String.format("parallel sort took: %d ms", millis));    
        //parallel sort took: 1373 ms 并行排序所花费的时间大约是顺序排序的一半。  
    }  
}
