package ui.routes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;
import ui.views.CommingSoonView;
import ui.views.CourseDashboardView;
import ui.views.CoursetListView;
import ui.views.Dashboard;
import ui.views.StudentDashboardView;

public class Router {

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private static Router instance;

    private Router() {

    }

    public JPanel getActiveView() {
        return Navigator.getInstance().top();
    }

    public void back() {
        Navigator.getInstance().pop();
        pcs.firePropertyChange("stack", 0, 1);
    }

    public boolean isBackBtnActive() {
        return Navigator.getInstance().size() > 1;
    }

    public void navigate(NavigationItem des) {

        JPanel old = getActiveView();

        JPanel destinationView;
        switch (des) {
            case DASHBOARD:
                destinationView = new Dashboard();
                break;

            case STUDENT_DASHBOARD:
                destinationView = new StudentDashboardView();
                break;

            case ADD_STUDENT:
                destinationView = new CommingSoonView();
                break;

            case DISPLAY_STUDENT:
                destinationView = new CommingSoonView();
                break;

            case LIST_STUDENTS:
                destinationView = new CoursetListView();
                break;

            case COURSE_DASHBOARD:
                destinationView = new CourseDashboardView();
                break;

            case COMMING_SOON:
                destinationView = new CommingSoonView();
                break;

            default:
                destinationView = new CommingSoonView();
        }

        Navigator.getInstance().push(destinationView);

        JPanel newView = getActiveView();
        pcs.firePropertyChange("stack", old, newView);
    }

    public String[] getNavigationPath() {
        return Navigator.getInstance().getNavigationPath();
    }

    public static Router getInstance() {
        if (instance == null) {
            instance = new Router();
        }
        return instance;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
