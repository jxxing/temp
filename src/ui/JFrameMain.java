package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import biz.Guess;


/**
 * 主窗口
 * 
 * @author xiaoE
 */
public class JFrameMain extends JFrame{
	
	/**
	 * 业务对象
	 */
	private Guess guess = new Guess();
	
	/**
	 * 按钮再来一次
	 */
	private JButton btnReset = new JButton("再来一次");
	
	/**
	 * 姓名列表按钮(第一次)
	 */
	private CardButton[] btnCards = new CardButton[25];

	/**
	 * 用户信息
	 */
	private JLabel lbMessage = new JLabel();
	
	public JFrameMain() {
		// 初始化
		this.init();
		// 控件初始化
		this.addCom();
		// 开始忽悠
		this.reset();
	}

	private void init() {
		// 窗口大小
		this.setSize(1024, 768);
		// 窗口剧中
		this.setLocationRelativeTo(null);
		// 默认关闭窗口
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 边界布局
		this.setLayout(new BorderLayout());
		// 标题
		this.setTitle("小翼的算命摊：不开口，知道你姓什么");
		// 显示
		this.setVisible(true);
	}
	
	private void addCom() {
		this.add(this.cardPanel(), BorderLayout.CENTER);
		this.add(this.messagePanel(), BorderLayout.SOUTH);
	}

	private JPanel cardPanel() {
		JPanel jp = new JPanel(new GridLayout(5, 5));
		for (int i = 0; i < this.btnCards.length; i++) {
			this.btnCards[i] = new CardButton();
			this.btnCards[i].addActionListener(new CardEvent(i, this));
			jp.add(this.btnCards[i]);
		}
		return jp;
	}

	private void reset() {
		try {
			this.btnReset.setEnabled(false);
			this.guess.init();
			this.lbMessage.setText("请从以上小卡片中选择一张含有你姓氏的");
			this.resetCardPanel(20, "X");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void resetCardPanel(int btnCount, String method) throws Exception {
		final Method getMethod = this.guess.getClass().getMethod("get" + method + "List", int.class);
		for (int i = 0; i < this.btnCards.length; i++) {
			if(i >= btnCount) {
				this.btnCards[i].clearNames();
				continue;
			}
			String[] names = (String[]) getMethod.invoke(this.guess, i);
			this.btnCards[i].createNameCard(names);
		}
	}
	
	private JPanel messagePanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		jp.add(this.btnReset);
		this.lbMessage.setForeground(Color.RED);
		jp.add(this.lbMessage);
		return jp;
	}
	
	private class CardEvent implements ActionListener{
		
		private final int eventCode;
		
		private final Component messageFather;
		
		public CardEvent(int eventCode, Component messageFather) {
			this.eventCode = eventCode;
			this.messageFather = messageFather;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(guess.setIndex(eventCode)) {
				for (CardButton btn : btnCards) {
					btn.clearNames();
				}
				JOptionPane.showMessageDialog(messageFather,
						"你姓" + "【" + guess.getName() + "】",
						"我知道啦！", JOptionPane.INFORMATION_MESSAGE);
				btnReset.setEnabled(true);
			} else {
				try {
					resetCardPanel(25, "Y");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
