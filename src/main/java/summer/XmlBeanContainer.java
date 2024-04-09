package summer;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import summer.exception.BeanDefinitionStoreException;

import java.io.IOException;
import java.util.List;

public class XmlBeanContainer extends BeanContainer {

    public XmlBeanContainer(String xmlResourcePath) {
        this.load(xmlResourcePath);
        this.refresh();
    }

    private void load(String xmlResourcePath) {
        // get inputstream to data
        try (var stream = getClass().getResourceAsStream(xmlResourcePath)) {
            if (stream == null) {
                throw new BeanDefinitionStoreException("XML resource `" + xmlResourcePath + "` not found");
            } else {
                // parse XML document into Java POJO
                JAXBContext jaxbContext = JAXBContext.newInstance(BeansElement.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                BeansElement beansElement = (BeansElement) jaxbUnmarshaller.unmarshal(stream);

                // check bean definitions
                beansElement.beans.forEach(b -> {
                    try {
                        this.defineBean(
                                b.name,
                                Class.forName(b.className)      // String --> Class<?>
                        );
                    } catch (ClassNotFoundException e) {
                        throw new BeanDefinitionStoreException("Class not found for bean " + b.name, e);
                    }
                });
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("Failed to read XML resource stream", e);
        } catch (JAXBException e) {
            throw new BeanDefinitionStoreException("Failed to parse XML resource stream", e);
        }
    }
}

@XmlRootElement(name = "beans")     // <beans>...</beans>
class BeansElement {
    @XmlElement(name = "bean")
    public List<BeanElement> beans; // <bean ... />
}

class BeanElement {
    @XmlAttribute(name = "name")
    public String name;
    @XmlAttribute(name = "class")
    public String className;
}
