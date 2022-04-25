package org.geekbang.thinking.in.spring.denpendcy.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description
 * @Date 2022/4/22
 * @Author yuze
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        applicationContext.refresh();

        // 1. 获取层次性工厂
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 结论：默认的HierarchicalBeanFactory是没有parentBeanFactory的。
        System.out.println("当前beanFactory的parentBeanFactory=" + beanFactory.getParentBeanFactory());

        // 2. 设置 ParentBeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前beanFactory的parentBeanFactory=" + beanFactory.getParentBeanFactory());

        // 3. 验证localBean的问题
        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");

        // 4. 使用双亲委派的方式来实现层级查找
        boolean user = displayContainsBean(beanFactory, "user");
        System.out.println("使用双亲委派的方式来实现层级查找: " + user);

        applicationContext.close();


    }

    private static boolean displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = (HierarchicalBeanFactory) parentBeanFactory;
            if (!hierarchicalBeanFactory.containsLocalBean(beanName)) {
                displayContainsBean(hierarchicalBeanFactory, beanName);
            } else {
                return true;
            }
        }
        return false;
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 bean [name : %s] : %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    public static HierarchicalBeanFactory createParentBeanFactory() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        return beanFactory;
    }



}
