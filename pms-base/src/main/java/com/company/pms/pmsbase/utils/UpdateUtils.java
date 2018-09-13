package com.company.pms.pmsbase.utils;

import com.company.pms.pmsbase.domain.BaseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author zcf
 * @Create 2018/7/30 15:45
 * @Desc 将一个类对象不为null的属性值赋值给另一个对象对应的属性，应用场景：用于Jpa 的 save()
 */
public class UpdateUtils {

    /**
     * 通过反射将source中不为null的属性赋值给target中对应的属性
     * @param source
     * @param target
     */
    public static <T extends BaseEntity> void copyNotNullProperties(T source, T target){
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    private static <T extends BaseEntity> String[] getNullProperties(T source) {
        BeanWrapper srcBean = new BeanWrapperImpl(source);  //获得source的包装对象，相当于=source
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();  //获得srcBean的属性描述器(包含source的所有属性)
        Set<String> emptyName = new HashSet<>();
        for(PropertyDescriptor pd : pds) {
            System.out.println(pd.getName());
            Object value = srcBean.getPropertyValue(pd.getName());
            if (value == null) {
                emptyName.add(pd.getName());
            }
        }
        emptyName.add("dateCreated");
        emptyName.add("entityType");
        String[] result = new String[emptyName.size()];
        return  emptyName.toArray(result);
    }

    /**
     * 通过反射将target中为null的属性用source中对应的属性替换
     * @param source
     * @param target
     */
    private static <T extends BaseEntity>void copyNullProperties(T source, T target) {
        BeanUtils.copyProperties(source, target, getNotNullProperties(target));
        /**
         * copyProperties(Object source, Object target, String... ignoreProperties)
         * 第三个属性表示要忽略的属性，在此表示忽略target中不为null的属性，只更新为空的属性
         */
    }

    /**
     * 获取target中不为null的属性
     * @param target
     * @return
     */
    private static <T extends BaseEntity> String[] getNotNullProperties(T target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target); //获得target的包装对象，相当于=target
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors(); //获得srcBean的属性描述器(包含target的所有属性)
        Set<String> notEmptyName = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object value = srcBean.getPropertyValue(pd.getName());
            if (value != null) notEmptyName.add(pd.getName());
        }
        notEmptyName.add("dateCreated");
        notEmptyName.add("entityType");
        String[] result = new String[notEmptyName.size()];
        return notEmptyName.toArray(result);
    }

    /**
     *   BeanWrapper: BeanWrapper它是对Bean的包装，其接口中所定义的功能很简单包括设置获取被包装的对象，获取被包装bean的属性描述器，
     *                由于BeanWrapper接口是PropertyAccessor的子接口，因此其也可以设置以及访问被包装对象的属性值。
     *                BeanWrapper大部分情况下是在spring ioc内部进行使用，通过BeanWrapper,spring ioc容器可以用统一的方式来访问bean的属性。
     *   PropertyDescriptor: 被包装Bean的属性描述器
     *
     */

    private UpdateUtils(){ }

}