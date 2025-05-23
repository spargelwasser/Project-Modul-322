package util;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

public class ReferenceFinder {

	public static JFrame findFrame(Component c) {

		if (c instanceof JFrame) {
			return (JFrame) c;
		} else if (c instanceof JPopupMenu) {
			JPopupMenu pop = (JPopupMenu) c;
			return findFrame(pop.getInvoker());
		} else {
			Container parent = c.getParent();
			return parent == null ? null : findFrame(parent);
		}
	}

	public static JDialog findDialog(Component c) {

		if (c instanceof JDialog) {
			return (JDialog) c;
		} else if (c instanceof JPopupMenu) {
			JPopupMenu pop = (JPopupMenu) c;
			return findDialog(pop.getInvoker());
		} else {
			Container parent = c.getParent();
			return parent == null ? null : findDialog(parent);
		}
	}

}
