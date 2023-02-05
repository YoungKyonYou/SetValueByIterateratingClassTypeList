package jpabook.jpashop.testsomething;

import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldIterateTest {

    List<Tracking> listBefore = new ArrayList<>();
    List<Tracking> listAfter = new ArrayList<>();
    Tracking trackingBefore = null;
    Tracking trackingAfter = null;
    static class Tracking{
        String wblNo;
        String rtnlNo;
        String empno;
        String branCd;
        String scanDcd;
        int cnt;
        Integer num;

        public Tracking(String wblNo, String rtnlNo, String empno, String branCd, String scanDcd, int cnt, Integer num, LocalDateTime modDateTime, double price) {
            this.wblNo = wblNo;
            this.rtnlNo = rtnlNo;
            this.empno = empno;
            this.branCd = branCd;
            this.scanDcd = scanDcd;
            this.cnt = cnt;
            this.num = num;
            this.modDateTime = modDateTime;
            this.price = price;
        }

        LocalDateTime modDateTime;
        double price;

    }

    @BeforeEach
    public void addTestData(){

        listBefore.add(new Tracking(" A ", " A "," A "," A "," A ", 10, 200, LocalDateTime.now(), 123.34));
        listBefore.add(new Tracking(" B ", " B "," B "," B "," B ", 20, 300, LocalDateTime.now(), 234.45));
        listBefore.add(new Tracking(" C ", " C "," C "," C "," C ", 30, 400, LocalDateTime.now(), 345.56));
        listBefore.add(new Tracking(" D ", " D "," D "," D "," D ", 40, 300, LocalDateTime.now(), 456.67));
        listBefore.add(new Tracking(" E ", " E "," E "," E "," E ", 50, 400, LocalDateTime.now(), 567.78));

        trackingBefore = new Tracking(" Z ", " Z "," Z "," Z "," Z ", 90, 900, LocalDateTime.now(), 789.89);
    }
    @Test
    public void testMain(){
        //리스트일 경우
        listAfter = (List<Tracking>)trimStringData(listBefore);

        for (Tracking tracking : listAfter) {
            System.out.println("tracking = " + tracking.branCd+", "+tracking.empno+", "+tracking.rtnlNo+", "+tracking.scanDcd+", "+tracking.wblNo+", "+tracking.cnt+", "+tracking.num+", "+tracking.modDateTime+", "+tracking.price);
        }

        //단일 값일 경우
        trackingAfter = (Tracking)trimStringData(trackingBefore);

        System.out.println("tracking = " + trackingAfter.branCd+", "+trackingAfter.empno+", "+trackingAfter.rtnlNo+", "+trackingAfter.scanDcd+", "+trackingAfter.wblNo+", "+trackingAfter.cnt+", "+trackingAfter.num+", "+trackingAfter.modDateTime+", "+trackingAfter.price);

    }

    public <T> Object trimStringData(Object object){
        System.out.println(object.getClass());
        Field[] fields = Tracking.class.getDeclaredFields();

        //리스트 일경우
        if(object instanceof List){
            for(T obj : (List<T>)object){
                for (Field field : fields) {

                    field.setAccessible(true);
                    try {
                        if(field.get(obj) instanceof String){
                            field.set(obj,((String)field.get(obj)).trim());
                            // System.out.println("타입:"+field.get(obj).getClass());
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            //단일 값일 경우
            for (Field field : fields) {

                field.setAccessible(true);
                try {
                    if(field.get(object) instanceof String){
                        field.set(object,((String)field.get(object)).trim());
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return object;
    }
}
