import config.Config;
import domain.Parrot;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ProjectName: AddBeansProgrammatically
 * @Author: Temesgen D.
 * @Date: 1/31/22
 */

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        Parrot parrot1 = new Parrot();
        parrot1.setName("JJ");

        context.registerBean("parrot1", Parrot.class, parrot1,
                new BeanDefinitionCustomizer[]{
                        bc -> bc.setPrimary(true),
                        bc -> bc.setAutowireCandidate(true)
                });

        System.out.println("parrot1 = " + parrot1);
    }
}
