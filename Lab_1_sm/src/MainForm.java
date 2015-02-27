import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Wed Feb 04 13:53:49 EET 2015
 */



/**
 * @author Iura Gaitur
 */
public class MainForm  {

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Iura Gaitur
		window2 = new Window(null);
		menu1 = new JMenu();
		panel1 = new JPanel();

		//======== window2 ========
		{
			window2.setLayout(new FormLayout(
				"default, $lcgap, 267dlu",
				"175dlu"));

			//======== menu1 ========
			{
				menu1.setText("text");
			}
			window2.add(menu1, CC.xy(1, 1));

			//======== panel1 ========
			{

				// JFormDesigner evaluation mark
				panel1.setBorder(new javax.swing.border.CompoundBorder(
					new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
						"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
						javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
						java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

				panel1.setLayout(new FormLayout(
					"default",
					"default"));
			}
			window2.add(panel1, CC.xy(3, 1));
			window2.pack();
			window2.setLocationRelativeTo(window2.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Iura Gaitur
	private Window window2;
	private JMenu menu1;
	private JPanel panel1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
