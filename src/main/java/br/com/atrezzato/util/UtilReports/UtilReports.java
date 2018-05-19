package br.com.atrezzato.util.UtilReports;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class UtilReports {

    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

    /**
     * Abre um arquivo PDF em uma nova aba do navegador
     * @param file - {@link File}: Arquivo que será exibido em outra aba de navegação
     * @throws IOException
     */

    public static void abrirRelatorioNovaJanela(File file) throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance ();
        ExternalContext externalContext = facesContext.getExternalContext ();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse ();

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

            response.reset();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
        } finally {
            close(output);
            close(input);
        }
        facesContext.responseComplete();
    }

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
