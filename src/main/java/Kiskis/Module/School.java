package Kiskis.Module;

import javax.naming.Name;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "School")
@XmlAccessorType(XmlAccessType.FIELD)
public class School
{
    private String SchoolName;
    @XmlElement(name = "student")
    private List<Student> students;

    public String getSchoolName()
    {
        return SchoolName;
    }
    public void setSchoolName(String SchoolName)
    {
        this.SchoolName=SchoolName;
    }

    public void setStudents(List < Student > students)
    {
        this.students = students;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    @Override
    public String toString()
    {
        return " Schoolname: " + SchoolName +
                " Students: " + students + " ";
    }

}
