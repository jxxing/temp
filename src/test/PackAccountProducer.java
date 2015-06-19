package test;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class PackAccountProducer {

	/**
	 * 主账号
	 */
	private final static String MAIN_ACCOUNT = "19399901040041558";//3999010400415580000000013
	private final static String BANK_CODE = "103";
	private final static Integer NUM = 201; 
	
	private final static String ACCOUNT_NAME = "子账号";
	
	/**
	 * 保存路径和名称
	 */
	private final static String FILE_NAME = "e:\\嘉兴二期_"+MAIN_ACCOUNT+"分包账号.sql";
	
	/**
	 * 格式化4位子帐号序号，不够左位补0，如12补零后变为0012
	 */
	private static final String STR_FORMAT = "0000000000";

	public static void main(String args[]) {
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		try {
			FileWriter fw = new FileWriter(FILE_NAME);
			for (int i = 200; i < 300; i++) {
				String sub_account = MAIN_ACCOUNT + df.format(i);
				String accountName = ACCOUNT_NAME + df.format(i);
				String sql = " insert into JX_PACK_ACCOUNT "
						+ " (ACCOUNT_NAME, ACCOUNT, FLAG, PACK_ID, PROJ_ID, RELATE_TIME,MAIN_ACCOUNT,BANK_CODE)"
						+ " values ( '"+accountName+"', '" + sub_account
						+ "', '00', null, null, null,'"+MAIN_ACCOUNT+"',"+BANK_CODE+");";
				fw.write(sql, 0, sql.length());
				fw.write("\n");
				if (i % 100 == 0) {
					fw.write("commit;\n");
				}
			}
			fw.flush();
			System.out.println("文件创建成功\n"+FILE_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

