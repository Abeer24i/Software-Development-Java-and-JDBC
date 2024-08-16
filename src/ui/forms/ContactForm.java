package ui.forms;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ui.common.PaddingManager;
import ui.common.PanelFactory;
import ui.components.RoundedTextField;
import ui.components.Text;
import ui.components.TextWithIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import ui.util.AppColor;

public class ContactForm extends JPanel {

    private RoundedTextField phoneNoField;
    private RoundedTextField mobilePhoneNoField;
    private RoundedTextField emailField;
    private RoundedTextField emergencyNoField;

    public ContactForm() {
        init();
    }

    private void init() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        //padding with radius border
        PaddingManager.setPaddingWithRadiusBorder(this, 20);

        Text formTitle = createFormTitle();
        initFields();
        JPanel fields = createForm();
        add(formTitle);
        add(fields);
    }

    private Text createFormTitle() throws NumberFormatException {
        Text formTitle = new Text("Contact Info", Color.decode(AppColor.TITLE));
        formTitle.setAlignmentX(CENTER_ALIGNMENT);
        return formTitle;
    }

    private JPanel createForm() throws NumberFormatException {
        JPanel fields = PanelFactory.createPanel(Color.WHITE);
        fields.setBorder(new EmptyBorder(20, 0, 0, 0));
        fields.setLayout(new GridLayout(4, 2, 8, 8));
        //phone no
        fields.add(new TextWithIcon("Phone No", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_PHONE_CLASSIC,Color.decode(AppColor.SUBTITLE)));
        fields.add(phoneNoField);
        //Mobile No
        fields.add(new TextWithIcon("Mobile No", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_PHONE,Color.decode(AppColor.SUBTITLE)));
        fields.add(mobilePhoneNoField);
        //Email
        fields.add(new TextWithIcon("Email", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_EMAIL,Color.decode(AppColor.SUBTITLE)));
        fields.add(emailField);
        //Emergency
        fields.add(new TextWithIcon("Emergency", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_AMBULANCE,Color.decode(AppColor.SUBTITLE)));
        fields.add(emergencyNoField);
        return fields;
    }

    private void initFields() {
        phoneNoField = new RoundedTextField();
        mobilePhoneNoField = new RoundedTextField();
        emailField = new RoundedTextField();
        emergencyNoField = new RoundedTextField();
    }
}
