package report;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Servlet implementation class TomboListReport
 */
@WebServlet("/printreport")
public class PrintReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletConfig().getServletContext();    

		/*
		 * String datainicial = request.getParameter("txtDataIni");    
		String datafinal = request.getParameter("txtDataFim");
		boolean fechado = false;
		try{
			fechado = request.getParameter("fechado").equals("on");
		}catch(NullPointerException e){}
		 */
		String reportName = request.getParameter("reportName").replace("-", ",");
		String type = request.getParameter("reportType");
		Connection con = connectDB();    

		try {    
			String reportFileName = context.getRealPath("WEB-INF/classes/report/" + reportName + ".jrxml");    
			File reportFile = new File(reportFileName);

			if (!reportFile.exists())    
				throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");

			HashMap parametros = new HashMap();
			InetAddress ipServer = java.net.InetAddress.getByName(request.getServerName());  

			parametros.put("ipserver", ipServer.getHostAddress() + ":8080");
			parametros.put("SUBREPORT_DIR", context.getRealPath("WEB-INF/classes/report/")+"\\");

			JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
			JasperReport relatoriosJasper = JasperCompileManager.compileReport(jasperDesign);


			if(type.equals("RTF")){
				JasperPrint impressao = new JasperPrint();
				OutputStream outputStream = response.getOutputStream();
				impressao = JasperFillManager.fillReport(relatoriosJasper, parametros,con);
				response.setContentType("application/rtf");
				response.setHeader("Content-Disposition", "attachment; filename=relatorio.rtf");
				JRExporter saida = new JRRtfExporter();
				saida.setParameter(JRExporterParameter.JASPER_PRINT, impressao);   
				saida.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
				
				
				saida.exportReport();
				//outputStream.flush();   
				outputStream.close();
			}else{
				if(type.equals("PDF")){
					byte[] bytes =    
						JasperRunManager.runReportToPdf(    
								relatoriosJasper,    
								parametros,    
								con);
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
					ServletOutputStream ouputStream = response.getOutputStream();
					ouputStream.write(bytes, 0, bytes.length);
					ouputStream.flush();
					ouputStream.close();
				}
			}
			con.close();

		} catch (JRException e) {
			response.setContentType("text/html");    
			PrintWriter out = response.getWriter();    

			out.println("<html>");    
			out.println("<head>");    
			out.println("<title>JasperReports - Web Application Sample</title>");    
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");    
			out.println("</head>");    

			out.println("<body bgcolor=\"white\">");    

			out.println("<span class=\"bnew\">JasperReports encountered this error :</span>");    
			out.println("<pre>");    

			e.printStackTrace(out);    

			out.println("</pre>");    

			out.println("</body>");    
			out.println("</html>");   
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}    
	}
	public Connection connectDB() {

		//Cria a conexão JDBC que será utilizada pelo relatório
		Connection jdbcConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			jdbcConnection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/dsi2016?user=bob&password=bob");
		} catch (Exception ex) {
			String connectMsg = "Could not connect to the database: "
				+ ex.getMessage() + " " + ex.getLocalizedMessage();
			System.out.println(connectMsg);
		}
		return jdbcConnection;
	}
}




