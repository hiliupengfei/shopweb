package cn.pengfei.ssm.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class UploadFile {
	
	private static final int TIME_OUT = 10 * 1000; // 超时时间
	private static final String CHARSET = "utf-8"; // 设置编码
	private static final String BOUNDARY = UUID.randomUUID().toString(); // 边界标识
																			// 随机生成
	private static final String PREFIX = "--";
	private static final String LINE_END = "\r\n";
	private static final String CONTENT_TYPE = "multipart/form-data"; // 内容类型
	
	public static boolean uploadFile(InputStream fis, String fileName,
			String requestURL) throws IOException {
		int retCode = 0;
		if (fis == null || fileName == null || fileName.length() == 0)
			return false;

		boolean ret = false;
		URL url = null;
		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		InputStream is = null;
		try {
			url = new URL(requestURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", CHARSET); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
					+ BOUNDARY);

			dos = new DataOutputStream(conn.getOutputStream());
			StringBuffer sb = new StringBuffer();
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINE_END);

			/**
			 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
			 * filename是文件的名字，包含后缀名
			 */
			sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
					+ fileName + "\"" + LINE_END);
			sb.append("Content-Type: application/octet-stream; charset="
					+ CHARSET + LINE_END);
			sb.append(LINE_END);
			dos.write(sb.toString().getBytes());

			is = fis;
			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = is.read(bytes)) != -1) {
				dos.write(bytes, 0, len);
			}
			is.close();
			dos.write(LINE_END.getBytes());
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
					.getBytes();
			dos.write(end_data);
			dos.flush();

			ret = conn.getResponseCode() == 200 ? true : false;
		} finally {
			if (dos != null)
				dos.close();
			if (is != null)
				is.close();
			if (conn != null)
				conn = null;
		}
		return ret;
	}
}
