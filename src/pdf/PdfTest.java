package pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class PdfTest {
	public static void main(String[] args) throws IOException {
//		new PdfTest().b();
//		new PdfTest().a();
		new PdfTest().c();
	}
	
	public void a(){
		PDDocument doc;
		try {
//			PDDocument doc = PDDocument.load(new ByteArrayInputStream(result));
//			PDFTextStripper stripper = new PDFTextStripper();
//			returnStr = stripper.getText(doc);
//			LOGGER.info("合同内容："+returnStr);
			doc = PDDocument.load("D:\\增信函.pdf");
			PDFTextStripper stripper = new PDFTextStripper();
			System.out.println(stripper.getText(doc));;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void b() throws IOException{
		PdfReader reader = new PdfReader("D:\\增信函.pdf");  
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);  
		StringBuffer buff = new StringBuffer();  
		TextExtractionStrategy strategy;  
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {  
			strategy = parser.processContent(i, new SimpleTextExtractionStrategy());  
		    buff.append(strategy.getResultantText());  
		 }    
		System.out.println(buff.toString());
	}
	
	
	public void c() throws IOException{
		InputStream in = new FileInputStream(new File("D:\\增信函.pdf"));
		PdfReader reader = new PdfReader(toByteArray(in));  
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);  
		StringBuffer buff = new StringBuffer();  
		TextExtractionStrategy strategy;  
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {  
			strategy = parser.processContent(i, new SimpleTextExtractionStrategy());  
		    buff.append(strategy.getResultantText());  
		 }    
		System.out.println(buff.toString());
	}
	
	
	
	
	
	
	
	public static byte[] toByteArray(InputStream input)
			  throws IOException
			{
			  ByteArrayOutputStream output = new ByteArrayOutputStream();
			  copy(input, output);
			  return output.toByteArray();
			}

			public static int copy(InputStream input, OutputStream output)
			  throws IOException
			{
			  long count = copyLarge(input, output);
			  if (count > 2147483647L) {
			    return -1;
			  }
			  return (int)count;
			}

			public static long copyLarge(InputStream input, OutputStream output)
			  throws IOException
			{
			  byte[] buffer = new byte[4096];
			  long count = 0L;
			  int n = 0;
			  while (-1 != (n = input.read(buffer))) {
			    output.write(buffer, 0, n);
			    count += n;
			  }
			  return count;
			}
}
