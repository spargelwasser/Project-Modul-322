package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JList;

import controller.ClearListener;
import controller.DeleteSelectedListener;
import controller.ExitListener;
import ressource.MenuConstants;

public class MyMenu extends JMenuBar {

    private static final long serialVersionUID = -6309418513306345186L;

    public MyMenu(JTable table, JList<String> locationList, JList<String> timeList) {
        super();
        
        createToolsMenu(table, locationList, timeList);
    }
    
    private void createToolsMenu(JTable table, JList<String> locationList, JList<String> timeList) {
        final JMenu menuData = new JMenu(MenuConstants.Menu_Data);
        final JMenuItem itemClear = new JMenuItem(MenuConstants.Item_Clear);
        final JMenuItem itemDelSlct = new JMenuItem(MenuConstants.Item_Del_Slct);
        final JMenuItem itemClose = new JMenuItem(MenuConstants.Item_Close);
        
        itemClear.addActionListener(new ClearListener(table));
        itemDelSlct.addActionListener(new DeleteSelectedListener(table));
        itemClose.addActionListener(new ExitListener());
        
        menuData.add(itemClear);
        menuData.add(itemDelSlct);
        this.add(menuData);
        this.add(itemClose);
    }
}
