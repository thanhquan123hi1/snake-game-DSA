package CONTROLLER;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MODEL.Snake;
import VIEW.MenuPanel;
import VIEW.SnakeView;
import VIEW.TwoSnakeView;
import VIEW.TwoSnakeView_2;

public class MenuController implements ActionListener{
	private MenuPanel m;
	public MenuController(MenuPanel m)
	{
		this.m = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 String src = e.getActionCommand();
		 if(m.getButton_motNguoiChoi().isSelected()) {
			 m.getButton_motNguoiChoi().setForeground(Color.BLUE);
			 m.getButton_haiNguoiChoi().setForeground(new  Color(255, 223, 0));

		 }
		 
		 if(m.getButton_haiNguoiChoi().isSelected()) {
			 m.getButton_haiNguoiChoi().setForeground(Color.BLUE);
			 m.getButton_motNguoiChoi().setForeground(new  Color(255, 223, 0));
			 
	}
		 
		 if(m.getButton_noObstacles().isSelected()) {
			 m.getButton_noObstacles().setForeground(Color.BLUE);
			 m.getButton_withObstacles().setForeground(new  Color(255, 223, 0));

		 }
		 if(m.getButton_withObstacles().isSelected()) {
			 m.getButton_withObstacles().setForeground(Color.BLUE);
			 m.getButton_noObstacles().setForeground(new  Color(255, 223, 0));

		 }

		if (src.equals("Start")&&m.getButton_motNguoiChoi().isSelected() && m.getButton_noObstacles().isSelected() ) {
			 
			new SnakeView(false,m);
		} 
		if (src.equals("Start")&&m.getButton_haiNguoiChoi().isSelected() && m.getButton_noObstacles().isSelected() ) {
			new TwoSnakeView(m);
		} 
		if (src.equals("Start")&&m.getButton_motNguoiChoi().isSelected() && m.getButton_withObstacles().isSelected() ) {
			new SnakeView(true,m);
		} 
		
		if (src.equals("Start")&&m.getButton_haiNguoiChoi().isSelected() && m.getButton_withObstacles().isSelected() ) {
			
			new TwoSnakeView_2(m);
		} 
		
		}
	}
	

