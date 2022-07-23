package online.visionacademy.ui.presenters;

import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.exceptions.StudentNotFoundException;

public interface StudentDetailViewListener {

    public void searchStudent() throws ServiceException, StudentNotFoundException;

}
