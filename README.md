### Adding beans to spring context Programmatically

Since Spring version 5, it is possible to add beans to spring context programmatically. This approach is mainly used
when we want to add beans based on certain condition. eg, 
```
    if (condition) {
        registerBean(b1);
    } else {
         registerBean(b2);
    }
```

To add a bean to the Spring context using a programmatic approach, you just need to call 
the registerBean() method of the ApplicationContext instance. 
The registerBean() has four parameters, as presented in the next code snippet:

```
    
<T> void registerBean(
    String beanName, // name of the bean, can be null if no name is needed
    Class<T> beanClass, // The class that defines the bean you add to the context eg. Parrot.class for a class Parrot
    Supplier<T> supplier, // An instance of a Supplier. The implementation of this Supplier needs to return the value of the instance you add to the context.
    BeanDefinitionCustomizer... customizers); // A varargs of BeanDefinitionCustomizer, can be zero or more.
```

As shown in the code example, the class of which we wanted to add an instance, or bean to the context is just a POJO, like below

```

    public class Parrot {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Parrot{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
```

Then, the bean registration is done like below:

```
    public class Main {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(Config.class);
    
            Parrot parrot1 = new Parrot();
            parrot1.setName("JJ");
            
            Supplier<Parrot> parrotSupplier = () -> parrot1; // We define a Supplier to return this instance.
    
            context.registerBean("parrot1", Parrot.class, parrotSupplier,
                    new BeanDefinitionCustomizer[]{
                            bc -> bc.setPrimary(true),
                            bc -> bc.setAutowireCandidate(true)
                    });
    
            System.out.println("parrot1 = " + parrot1);
        }
    }

```

The fourth parameter to the registerBean is optional. In the above case, we have two BeanDefinitionCustomizers, 
and we added them as an array. We could have also added them as comma separated parameters.

If we have only one BeanDefinitionCustomizer, we just add like below.

```
    ...
    context.registerBean("parrot1", Parrot.class, parrot1,
                    (BeanDefinitionCustomizer) bc -> bc.setPrimary(true));
```
