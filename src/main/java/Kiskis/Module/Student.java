package Kiskis.Module;

import org.slf4j.event.SubstituteLoggingEvent;

import java.util.List;
import javax.xml.bind.annotation.*;
//student klase kur talpins is xml i paprasta object viska kas bus tame XML'e

@XmlRootElement
public class Student
{

    private String Name;
    private String Subject;

    public String getName()
    {
        return Name;
    }
    public void setName(String Name)
    {
        this.Name=Name;
    }
    public String getSubject()
    {
        return Subject;
    }
    public void setSubject(String Subject)
    {
        this.Subject=Subject;
    }

    @Override
    public String toString()
    {
        return " StudentName: " + Name + " StudentSubject: " + Subject + " ";
    }

}
