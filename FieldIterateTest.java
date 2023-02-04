package jpabook.jpashop.testsomething;

import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldIterateTest {

    List<Tracking> listBefore = new ArrayList<>();
    List<Tracking> listAfter = new ArrayList<>();
    static class Tracking{
        String wblNo;
        String rtnlNo;
        String empno;
        String branCd;
        String scanDcd;

        public Tracking(String wblNo, String rtnlNo, String empno, String branCd, String scanDcd) {
            this.wblNo = wblNo;
            this.rtnlNo = rtnlNo;
            this.empno = empno;
            this.branCd = branCd;
            this.scanDcd = scanDcd;
        }
    }

    @BeforeEach
    public void addTestData(){

        listBefore.add(new Tracking(" A ", " A "," A "," A "," A "));
        listBefore.add(new Tracking(" B ", " B "," B "," B "," B "));
        listBefore.add(new Tracking(" C ", " C "," C "," C "," C "));
        listBefore.add(new Tracking(" D ", " D "," D "," D "," D "));
        listBefore.add(new Tracking(" E ", " E "," E "," E "," E "));

    }
    @Test
    public void testMain(){
        // listAfter = iterateClassList(listBefore);
       listAfter = iterateClassList(listBefore);

        for (Tracking tracking : listAfter) {
            System.out.println("tracking = " + tracking.branCd+", "+tracking.empno+", "+tracking.rtnlNo+", "+tracking.scanDcd+", "+tracking.wblNo);
        }
    }



    public <T> List<T> iterateClassList(List<T> list){
        Field[] fields = Tracking.class.getDeclaredFields();

        for(T obj : list){
            for (Field field : fields) {
                String name = field.getName();
                field.setAccessible(true);
                try {
                    field.set(obj,((String)field.get(obj)).trim());
                 //   System.out.format("%n%s: %s", name, field.get(list.get(0)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
