package com.oty.web.app.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String header = httpServletRequest.getHeader("Accept-Encoding");
		if ("gzip".equals(header)) {
			MyResponse myResponse = new MyResponse(httpServletResponse);
			chain.doFilter(httpServletRequest, myResponse);
			byte[] out = myResponse.getBufferData();
			byte[] gzipData = gzipData(out);
			httpServletResponse.setHeader("content-encoding", "gzip");
			httpServletResponse.setHeader("content-length", gzipData.length
					+ "");
			httpServletResponse.getOutputStream().write(gzipData);
		} else {
			chain.doFilter(httpServletRequest, httpServletResponse);
		}

	}

	private byte[] gzipData(byte[] out) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(baos);
		gzipOutputStream.write(out);
		gzipOutputStream.close();
		return baos.toByteArray();
	}

	class MyResponse extends HttpServletResponseWrapper {
		private HttpServletResponse httpServletResponse;
		private ByteArrayOutputStream baos = new ByteArrayOutputStream();
		private PrintWriter printWriter;

		public MyResponse(HttpServletResponse response) {
			super(response);
			this.httpServletResponse = response;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {

			return new MyServletOutputStream(baos);
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			String encoding = httpServletResponse.getCharacterEncoding();
			System.out.println("encoding==" + encoding);
			printWriter = new PrintWriter(
					new OutputStreamWriter(baos, encoding));
			return printWriter;
		}

		public byte[] getBufferData() {
			if (printWriter != null) {
				printWriter.close();
			}
			return baos.toByteArray();
		}

	}

	class MyServletOutputStream extends ServletOutputStream {
		private ByteArrayOutputStream baos;

		public MyServletOutputStream(ByteArrayOutputStream baos) {
			super();
			this.baos = baos;
		}

		@Override
		public void write(int b) throws IOException {
			baos.write(b);
		}

		public boolean isReady() {
			return false;
		}

		@Override
		public void setWriteListener(WriteListener arg0) { 
			
		}
	}
	
}
